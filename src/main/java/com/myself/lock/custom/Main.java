package com.myself.lock.custom;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A->B->C顺序打印
 *
 * @ClassName Test01
 * @Description
 * @Author wb
 * @Date 2021/8/9 0009 下午 4:57
 */
public class Main {

    public static void main(String[] args) {
        Test01 test = new Test01();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                test.printA(i);
            }
        },"AA线程").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                test.printB(i);
            }
        },"BB线程").start();
        new Thread(() -> {//实现的runnable接口
            for (int i = 0; i < 10; i++) {
                test.printC(i);
            }
        },"CC线程").start();

    }
}

class Test01 {
    private int flag = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA(int number) {
        try {
            lock.lock();
            while (flag != 1) {
                conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"线程第"+number+"轮"+"第"+ i+"次:AA");
            }
            flag = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int number) {
        try {
            lock.lock();
            while (flag != 2) {
                conditionB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() +"线程第"+number+"轮"+"第"+ i+ "BB");
            }
            flag = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int number) {
        try {
            lock.lock();
            while (flag != 3) {
                conditionC.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "线程第"+number+"轮"+"第"+ i+"CC");
            }
            flag = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


