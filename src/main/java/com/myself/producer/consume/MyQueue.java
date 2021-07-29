package com.myself.producer.consume;

/**
 * @ClassName MyQueue
 * @Description
 * @Author wb
 * @Date 2021/7/29 0029 上午 11:46
 */
public class MyQueue {

    /**
     * 存放的数据
     */
    private String[] data = new String[10];

    /**
     * put的中下一条要存储的坐标
     */
    private Integer putIndex = 0;

    /**
     * get的下一条坐标
     */
    private Integer getIndex = 0;

    /**
     * 数量
     */
    private Integer size = 0;

    public synchronized void production(String product) {
        if (size == data.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            put(product);
        } else {
            put(product);
        }
    }

    public synchronized String consume() {
        if (size == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return get();
        } else {
            return get();
        }
    }

    private void put(String product) {
        data[putIndex] = product;
        putIndex++;
        size++;
        if (putIndex == data.length) {
            putIndex = 0;
        }
        notify();
    }

    private String get() {
        String vlaue = data[getIndex];
        getIndex++;
        size--;
        if (getIndex == data.length) {
            getIndex = 0;
        }
        notify();
        return vlaue;
    }
}
