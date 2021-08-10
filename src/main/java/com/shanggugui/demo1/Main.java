package com.shanggugui.demo1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName Main
 * @Description
 * @Author wb
 * @Date 2021/7/28 0028 下午 2:17
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        MyThread myThread = new MyThread();
//        Thread thread = new Thread(new MyRunable());
//
//        myThread.start();
//        myThread.join();
//        thread.start();
//        thread.join();//只影响主线程吗 真正阻塞的不是调用者线程，而是当前正在执行的线程 此时真正运行的是主线程
        MyCallThrad myCallThrad = new MyCallThrad();
        FutureTask<String> futureTask=new FutureTask<>(myCallThrad);
        new Thread(futureTask).start();
        String result = futureTask.get();
        System.out.println("call线程执行结果"+result);
        System.out.println("主线程已经执行结束");
    }
}
