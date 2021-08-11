package com.shanggugui.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/** 信号灯
 * 六辆汽车   三个停车位
 * @ClassName SemaphoreDemo
 * @Description
 * @Author wb
 * @Date 2021/8/11 0011 下午 4:20
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);
        for (int i=0;i<6;i++){
            new Thread(() ->{
                try {
                    //抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"号抢到了车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+"停车一会后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
