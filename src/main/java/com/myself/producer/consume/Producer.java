package com.myself.producer.consume;

import lombok.SneakyThrows;

/**
 * @ClassName Producer
 * @Description
 * @Author wb
 * @Date 2021/7/29 0029 下午 4:34
 */
public class Producer extends Thread {

    MyQueue myQueue=new MyQueue();
    Integer i=1;

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            myQueue.production(i.toString());
            Thread.sleep(1000);
            System.out.println("生产商品:"+i);
            i++;
        }

    }
}
