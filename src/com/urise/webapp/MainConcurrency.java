package com.urise.webapp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MainConcurrency {
    private volatile int counter;

    public static void main(String[] args) throws InterruptedException {
        int THREADS_NUMBER = 10000;

        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        Thread thread1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState()));
        thread1.start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
            });
            thread.start();
//            threads.add(thread);
        }

//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        latch.await(10, TimeUnit.SECONDS);
        System.out.println(mainConcurrency.counter);

//        final String lock1 = "lock1";
//        final String lock2 = "lock2";
//        deadLock(lock1, lock2);
//        deadLock(lock2, lock1);
    }

    private static void deadLock(String lock1, String lock2) {
        new Thread(() -> {
            System.out.println("Waitng " + lock1);

            synchronized (lock1) {
                System.out.println("Holding " + lock1);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2);
                synchronized (lock2) {
                    System.out.println("Holding " +  lock2);
                }
            }

        }).start();
    }

    private synchronized void inc() {
        counter++;
    }
}
