package com.droptable.tipsservice.dao.api;

import com.droptable.tipsservice.dao.db.Waiter;

public class WaiterWrapper {
    private Waiter stuff;

    public WaiterWrapper(Waiter stuff) {
        this.stuff = stuff;
    }

    public WaiterWrapper() {
    }

    public Waiter getStuff() {
        return stuff;
    }

    public void setStuff(Waiter stuff) {
        this.stuff = stuff;
    }
}
