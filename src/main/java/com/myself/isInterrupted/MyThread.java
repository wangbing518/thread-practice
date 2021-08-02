package com.myself.isInterrupted;


/**
 * @ClassName MyThread
 * @Description
 * @Author wb
 * @Date 2021/8/2 0002 下午 3:22
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        int i = 0;
        while (true) {
            boolean isInterrupted = isInterrupted();
            System.out.println("中断标记：" + isInterrupted);
            i++;
            if (i>10){
                boolean interrupted = Thread.interrupted();
                System.out.println("重置中断状态：" + interrupted);
                interrupted = Thread.interrupted();
                System.out.println("重置中断状态：" + interrupted);
                isInterrupted = isInterrupted();
                System.out.println("再次查询中断标记：" + isInterrupted);
                break;
            }

        }
    }
}
