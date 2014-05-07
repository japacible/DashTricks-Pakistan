package com.dashtricks.pakistan.app.general;

/**
 * Created by Donohue on 5/7/14.
 */
public class RefrigeratorTypeAndCount {
    private Refrigerator type;
    private int count;

    public RefrigeratorTypeAndCount(Refrigerator r, int i) {
        type = r;
        count = i;
    }

    public Refrigerator getType() {
        return type;
    }

    public void setType(Refrigerator type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
