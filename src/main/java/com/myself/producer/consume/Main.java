package com.myself.producer.consume;


/**
 * @ClassName Main
 * @Description
 * @Author wb
 * @Date 2021/7/29 0029 下午 6:24
 */
public class Main {

    public static void main(String[] args) {
        //TODO 有问题 还未解决
        Producer producer=new Producer();
        Consume consumer=new Consume();
        for (int i=0;i<10;i++){
            producer.start();
            consumer.start();
        }
        System.exit(0);
    }
}
