package com.shanggugui.deadlock;

/**
 * @ClassName Demo1
 * @Description
 * @Author wb
 * @Date 2021/8/10 0010 下午 4:53
 */
public class Demo1 {
    static Object o = new Object();
    static Object ob = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (o){
                System.out.println(Thread.currentThread().getName()+"持有锁o想要试图获取锁ob");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(ob){
                    System.out.println(Thread.currentThread().getName()+"获取锁ob");
                }
            }
        },"AA").start();

        new Thread(() -> {
            synchronized (ob){
                System.out.println(Thread.currentThread().getName()+"持有锁ob想要试图获取锁o");
                synchronized(o){
                    System.out.println(Thread.currentThread().getName()+"获取锁o");
                }
            }
        },"BB").start();


    }
}
