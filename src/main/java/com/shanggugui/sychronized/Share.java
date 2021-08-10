package com.shanggugui.sychronized;

public class Share {

    public static void main(String[] args) {
        AddSub addSub=new AddSub();
        new Thread(()->{
            for (int i=0;i<=10;i++){
                try {
                    addSub.insr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i=0;i<=10;i++){
                try {
                    addSub.desr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i=0;i<=10;i++){
                try {
                    addSub.insr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i=0;i<=10;i++){
                try {
                    addSub.desr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
class AddSub{
    private Integer number=0;

    public synchronized void insr() throws InterruptedException {
         while(number!=0){//不用if 是为了防止虚假唤醒 wait在哪等待在那唤醒
            this.wait();
        }
        number++;
         System.out.println(Thread.currentThread().getName()+":加1后为:"+number);
        this.notifyAll();
    }

    public synchronized void desr() throws InterruptedException {
        while (number!=1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+":减1后为:"+number);
        this.notifyAll();
    }

}
