package com.tr.BasicJava;

public class son extends Father {
    public void print() {
        //可从父类继承的范围
        int dn = super.defaultNum;
        int pn = super.protectedNum;
        int pubn = super.pulicNum;

        super.printProtected();
        super.pritnPublic();
        super.pritnDefault();

    }
}
