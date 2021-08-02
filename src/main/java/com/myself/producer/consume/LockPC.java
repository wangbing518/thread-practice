package com.myself.producer.consume;


import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockPC
 * @Description
 * @Author wb
 * @Date 2021/8/2 0002 上午 10:30
 */
public class LockPC {

    private static Integer count = 0;

    private static final Integer full = 10;

    private ReentrantLock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();


    public static void main(String[] args) {
        LockPC lockPC = new LockPC();
        Producers producers = lockPC.new Producers();
        Consumer consumer = lockPC.new Consumer();
        new Thread(producers).start();
        new Thread(producers).start();
        new Thread(producers).start();
        new Thread(new FutureTask<>(consumer)).start();
        new Thread(new FutureTask<>(consumer)).start();
        new Thread(new FutureTask<>(consumer)).start();
    }

    /**
     * 消费者
     */
    class Consumer implements Callable {
        @Override
        public Object call() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count==0){
                        System.out.println("当前线程" + Thread.currentThread().getName() +"消费的商品已经空了无法继续消费");
                        notEmpty.await();
                    }
                    count--;
                    System.out.println("当前线程" + Thread.currentThread().getName() + "的消费者消费产品序号:" + count);
                    notFull.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
            return Thread.currentThread().getName();
        }
    }

    class Producers implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    while (count == full) {
                        System.out.println("当前线程" + Thread.currentThread().getName() +"生产的商品已满");
                        notFull.await();
                    }
                    count++;
                    System.out.println("当前线程" + Thread.currentThread().getName() + "的生产者生产产品序号:" + count);
                    notEmpty.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }
}
