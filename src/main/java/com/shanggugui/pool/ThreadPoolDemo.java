package com.shanggugui.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPoolDemo
 * @Description
 * @Author wb
 * @Date 2021/8/12 0012 下午 5:11
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
            for (int i = 1; i < 6; i++) {
                int temp = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "号窗口执行了" + temp + "操作");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

      /*  for (int i = 1; i < 6; i++) {
            int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号窗口执行了" + temp + "操作");
            }, String.valueOf(i)).start();
        }*/
    }
}
