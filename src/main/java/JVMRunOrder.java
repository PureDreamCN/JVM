/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:JVM
 * Module Name:TODO:Module
 */

/**
 * what:    测试对类加载顺序的理解
 *
 * @author 郭飞 created on 2018/1/18
 */
public class JVMRunOrder {
private static JVMRunOrder jVMRunOrder = new JVMRunOrder();
    public  static int a ;
    public static int b = 0;

    private JVMRunOrder() {
        a++;
        b++;
    }
    public static JVMRunOrder getJVMRunOrder(){
        return jVMRunOrder;
    }
}
 class Test{
    public static void main(String[] args){
        JVMRunOrder jVMRunOrder = JVMRunOrder.getJVMRunOrder();
        System.out.println("a="+jVMRunOrder.a);
        System.out.println("b="+jVMRunOrder.b);
    }
}
