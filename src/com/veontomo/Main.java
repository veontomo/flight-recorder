package com.veontomo;

public class Main {


    public static void main(String[] args) {
        Pool p = new Pool(10);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    Object o = p.getItem();
                    Thread.sleep(100);
                    p.putItem(o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
