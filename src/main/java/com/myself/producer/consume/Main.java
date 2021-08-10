package com.myself.producer.consume;


/**
 * @ClassName Main
 * @Description
 * @Author wb
 * @Date 2021/7/29 0029 下午 6:24
 */
public class Main {

    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 20;
        System.out.println("输出交换前的两个数:a=" + a + ",b=" + b);
        changeNum(a, b);
        System.out.println("输出交换后的两个数:a=" + a + ",b=" + b);
    }

    public static void changeNum(Integer num1, Integer num2) {
        int t;
        t = num1;
        num1 = num2;
        num2 = t;
    }
}
