package com.shanggugui.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockDemo
 * @Description
 * @Author wb
 * @Date 2021/8/11 0011 下午 6:04
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        Mycache mycache = new Mycache();
        for (int i = 1; i < 6; i++) {
            int temp = i;
            new Thread(() -> {
                mycache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i < 6; i++) {
            int temp = i;
            new Thread(() -> {
                mycache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

class Mycache {

    private volatile Map<String, String> map = new HashMap<>();

    //创建读写锁
    private ReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在执行写操作:" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了:" + key);
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            rwlock.writeLock().unlock();
        }

    }

    public String get(String key) {
        rwlock.readLock().lock();
        String result="";
        try {
            System.out.println(Thread.currentThread().getName() + "正在执行取操作:" + key);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "取完了:" + key);
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwlock.readLock().unlock();
        }
        return result;
    }

}
