package org.jvm;

public class Jvm {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("1----------------");
        Thread.sleep(30000);
        byte[] array = new byte[2024*1024*10];
        System.out.println("2----------------");
        Thread.sleep(30000);
        array = null;
        System.gc();
        System.out.println("3----------------");
        Thread.sleep(100000000000L);
    }
}
