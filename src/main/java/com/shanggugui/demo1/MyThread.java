package com.shanggugui.demo1;


/**
 * @ClassName MyThread
 * @Description
 * @Author wb
 * @Date 2021/7/28 0028 下午 2:20
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        int i=0;
        boolean flag=true;
        while (flag){
            System.out.println("当前线程"+Thread.currentThread().getName()+"在执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i>8){
                flag=false;
            }
            i++;
        }
    }
}
