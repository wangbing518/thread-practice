package com.shanggugui.lock;


import java.util.concurrent.locks.ReentrantLock;

public class LScaleTicket {
    public static void main(String[] args) {
        LockScale lockScale = new LockScale();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lockScale.saleTicket();
            }
        }, "第一个售票员").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lockScale.saleTicket();
            }
        }, "第二个售票员").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                lockScale.saleTicket();
            }
        }, "第三个售票员").start();
    }
}

class LockScale {
    private Integer tickets = 30;
    private final ReentrantLock lock = new ReentrantLock();

    public void saleTicket() {
        try {
            lock.lock();
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "当前票数:" + tickets-- + "剩余票数:" + tickets);
            }
        } finally {
            lock.unlock();
        }
    }
}