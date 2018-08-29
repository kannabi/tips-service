package com.droptable.tipsservice.dao.db;

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

    public Tip(Long time, BigDecimal sum, Waiter waiter) {
        this.time = time;
        this.sum = sum;
        this.waiter = waiter;
    }

    public Tip() {
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
