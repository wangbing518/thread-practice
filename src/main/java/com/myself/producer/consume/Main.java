package com.myself.producer.consume;


/**
 * @ClassName Main
 * @Description
 * @Author wb
 * @Date 2021/7/29 0029 下午 6:24
 */
public class Main {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        //TODO 有问题 还未解决
        Producer producer = new Producer(myQueue);
        Consume consumer = new Consume(myQueue);
        producer.start();
        consumer.start();

    }
}
