package com.droptable.tipsservice.security;

class SecurityConstants {
	static final String SECRET = "SecretKeyToGenJWTs";
	static final int EXPIRATION_TIME = 864_000_000; // 10 days
	static final String TOKEN_PREFIX = "Bearer";
	static final String TOKEN_HEADER = "Authorization";
	static final String TOKEN_PARAM= "token";
}
