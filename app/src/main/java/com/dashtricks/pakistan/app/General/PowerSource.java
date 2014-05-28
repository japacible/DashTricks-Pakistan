package com.dashtricks.pakistan.app.General;

// Dov file. Indentation and other stuff may be off
public enum PowerSource {
    ELECTRICITY(1), GAS(2), KEROSENE(3), SOLAR(4), UNKNOWN(5); // following the excel convention
    private int val;
    
    private PowerSource(int val) {
    this.val = val;
    }

    public int get() {
    return val;
    }
};
