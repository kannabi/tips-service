package com.droptable.tipsservice.dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "waiters")
public class Waiter {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "account_bill", nullable = false)
    private String accountBill;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "waiter")
    private List<Tip> tips;

    public Waiter(String id, String name, String code, String accountBill, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.accountBill = accountBill;
        this.restaurant = restaurant;
    }
}
