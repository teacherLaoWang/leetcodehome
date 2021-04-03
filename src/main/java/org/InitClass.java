package org;

public class InitClass {
    static {
        System.out.println("init class");
    }
    public static final int a = 1;
}

class Test{
    public static void main(String[] args) {
        System.out.println(InitClass.a);
    }
}