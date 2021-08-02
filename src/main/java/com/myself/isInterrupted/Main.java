package com.myself.isInterrupted;

/**
 * @ClassName Main
 * @Description
 * @Author wb
 * @Date 2021/8/2 0002 下午 3:35
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread=new MyThread();
        myThread.start();
        Thread.sleep(1000);
        myThread.interrupt();
        Thread.sleep(700);
        System.out.println("main中断状态检查-1：" + myThread.isInterrupted());
        System.out.println("main中断状态检查-2：" + myThread.isInterrupted());
    }
}
