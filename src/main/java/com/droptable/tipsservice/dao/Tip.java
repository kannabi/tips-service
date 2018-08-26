package com.droptable.tipsservice.dao;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tips")
public class Tip {
    @Id
    private Long time;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private Waiter waiter;
}
