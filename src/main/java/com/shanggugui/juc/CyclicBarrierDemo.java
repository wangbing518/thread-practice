package com.shanggugui.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/** 循环栅栏  七龙珠许愿
 * @ClassName CyclicBarrierDemo
 * @Description
 * @Author wb
 * @Date 2021/8/11 0011 下午 3:50
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("七龙珠已集齐召唤神龙许愿");
        });
        for (int i=0;i<7;i++){
            new Thread(()->{
                try {
                    Thread.sleep(5000);
                    System.out.println("收集了第"+Thread.currentThread().getName()+"星龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
