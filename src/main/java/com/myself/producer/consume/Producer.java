package com.myself.producer.consume;

import lombok.SneakyThrows;

/**
 * @ClassName Producer
 * @Description
 * @Author wb
 * @Date 2021/7/29 0029 下午 4:34
 */
public class Producer extends Thread {

    private final MyQueue myQueue;
    private Integer i=1;

    public Producer(MyQueue myQueue) {
        this.myQueue = myQueue;
    }

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
