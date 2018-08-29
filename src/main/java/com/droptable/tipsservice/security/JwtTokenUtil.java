package com.droptable.tipsservice.security;

import com.droptable.tipsservice.crm.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private Clock clock = DefaultClock.INSTANCE;

	public String getLoginFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	private Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
						.setSigningKey(SecurityConstants.SECRET)
						.parseClaimsJws(token)
						.getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = this.getExpirationDateFromToken(token);
		return expiration.before(clock.now());
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Date createdDate = clock.now();
		final Date expirationDate = this.calculateExpirationDate(createdDate);

		return Jwts.builder()
						.setClaims(claims)
						.setSubject(subject)
						.setIssuedAt(createdDate)
						.setExpiration(expirationDate)
						.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
						.compact();
	}

	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = this.getIssuedAtDateFromToken(token);
		return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
						&& (!this.isTokenExpired(token));
	}

	public String refreshToken(String token) {
		final Date createdDate = clock.now();
		final Date expirationDate = this.calculateExpirationDate(createdDate);

		final Claims claims = this.getAllClaimsFromToken(token);
		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);

		return Jwts.builder()
						.setClaims(claims)
						.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
						.compact();
	}

	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

	public String formatToken(String bearerToken) {
		if (bearerToken != null && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX + " ")) {
			return bearerToken.substring(7);
		} else {
			throw new InvalidTokenException("Invalid authentication token");
		}
	}

	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + SecurityConstants.EXPIRATION_TIME);
	}
}
