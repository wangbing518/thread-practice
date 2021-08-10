package com.shanggugui.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName UnsafeList
 * @Description
 * @Author wb
 * @Date 2021/8/9 0009 下午 5:59
 */
public class UnsafeList {

    /**
     * 此时报错
     *
     * @param args
     */
    public static void main(String[] args) {
        //不安全的版本 并发修改异常  java.util.ConcurrentModificationException
        //List<String> list=new ArrayList<>();
        //安全的版本 不常用
        //List<String> list=new Vector<>();
        //安全古老的版本 不常用
        //List<String> list= Collections.synchronizedList(new ArrayList<>());
        //最常用的写时复制技术 就是写入的时候先复制原先的数组 在复制好的进行写操作 最后合并 不影响读操作
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
