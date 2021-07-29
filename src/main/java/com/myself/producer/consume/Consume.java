package com.myself.producer.consume;

import lombok.SneakyThrows;

/**
 * @ClassName Consume
 * @Description
 * @Author wb
 * @Date 2021/7/29 0029 下午 6:22
 */
public class Consume extends Thread {

    MyQueue myQueue=new MyQueue();


    @SneakyThrows
    @Override
    public void run() {
        while (true){
            String consume = myQueue.consume();
            Thread.sleep(1000);
            System.out.println("消耗商品:"+consume);
        }
    }
}
