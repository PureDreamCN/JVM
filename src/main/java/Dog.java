/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:JVM
 * Module Name:TODO:Module
 */

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 郭飞 created on 2018/1/19
 */
public class Dog {
    public Dog() {
        System.out.println("dog is loaded by:"+this.getClass().getClassLoader());
    }
}
