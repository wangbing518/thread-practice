package com.myself.demo1;

import java.util.concurrent.Callable;

/**
 * 实现callable接口 并通过FutureTask包装器来实现接口
 *
 * @ClassName myCallThrad
 * @Description
 * @Author wb
 * @Date 2021/7/28 0028 下午 6:17
 */
public class MyCallThrad implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("callable线程开始运行，线程名" + Thread.currentThread().getName());
        boolean flag = true;
        int j = 0;
        Integer i = 1;
        while (flag) {
            i++;
            if (j > 8) {
                flag = false;
            }
            j++;
        }
        return i.toString();
    }
}
