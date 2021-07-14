package com.urise.webapp;

public class MainDeadlock {
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> lock(LOCK1, LOCK2)).start();
        new Thread(() -> lock(LOCK2, LOCK1)).start();
    }

    private static void lock(Object monitor1, Object monitor2) {
        synchronized (monitor1) {
            System.out.println("Holding " + Thread.currentThread().getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (monitor2) {
                System.out.println("Holding " + Thread.currentThread().getName());
            }
        }
    }
}
