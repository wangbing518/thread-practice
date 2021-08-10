package com.shanggugui.sychronized;

public class ScaleTicket {

    public static void main(String[] args) {
        ScaleTic scaleTic = new ScaleTic();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<=40;i++){
                    scaleTic.sale();
                }
            }
        },"第一个售票员").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<=40;i++){
                    scaleTic.sale();
                }
            }
        },"第二个售票员").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<=40;i++){
                    scaleTic.sale();
                }
            }
        },"第二个售票员").start();
    }

}
class ScaleTic{
    private Integer ticket=30;

    public synchronized void sale(){
        if (ticket>0){
            System.out.println(Thread.currentThread().getName()+"卖出前票数是:"+ticket--+"剩余票数:"+ticket);
        }
    }
}
