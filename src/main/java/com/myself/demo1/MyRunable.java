package com.myself.demo1;

/**
 * @ClassName MyRunable
 * @Description
 * @Author wb
 * @Date 2021/7/28 0028 下午 2:27
 */
public class MyRunable implements Runnable {

    @Override
    public void run() {
        int i=0;
        boolean flag=true;
        while (flag){
            System.out.println("当前运行的线程是"+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i>4){
                flag=false;
            }
            i++;

        }

    }
}
