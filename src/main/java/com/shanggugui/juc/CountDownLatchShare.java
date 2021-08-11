package com.shanggugui.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * 计数器的使用 保证线程全部使用完之后采取释放主线程
 * 例子 教室里 有六个人 都六个人都走完时 值班的才能去锁教室门
 * @ClassName CountDownLatchShare
 * @Description
 * @Author wb
 * @Date 2021/8/10 0010 下午 5:54
 */
public class CountDownLatchShare {

    public static void main(String[] args) throws InterruptedException {
        //TODO 并未有按照理解的等人走完才到主线程
       // futureTaskFinish();
        final CountDownLatch countDownLatch=new CountDownLatch(6);

        runnablFinish(countDownLatch);
        System.out.println(Thread.currentThread().getName()+"所有人都离开教室值班人员锁门");
    }

    private static void runnablFinish(CountDownLatch countDownLatch) throws InterruptedException {
        for (int i=0;i<6;i++){
            int temp=i;
            new Thread(()->{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第"+ temp +"个学生离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        System.out.println("主线程睡觉");
        System.out.println("教室里剩余人数"+countDownLatch.getCount());
        countDownLatch.await();
    }

    private static void futureTaskFinish() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i=1;i<=6;i++){
            int temp=i;
            FutureTask<Object> futureTask=new FutureTask<Object>(()->{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("第"+ temp +"个学生离开教室");
            },String.valueOf(i));
            new Thread(futureTask).start();
        }
        System.out.println("主线程睡觉");
        countDownLatch.await();
    }
}
