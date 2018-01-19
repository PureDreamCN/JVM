/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:JVM
 * Module Name:TODO:Module
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * what:    自定义类加载器
 *
 * @author 郭飞 created on 2018/1/19
 */
public class MyClassLoader extends ClassLoader {
    private String name;//类加载器的名称
    private String path ;//j加载类的路径
    private final String fileType = ".class";//class 文件的扩展名

    public MyClassLoader(String name) {
        super();//让系统类加载器成为该类加载器的父类加载器
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent);//显示指定该类的父类加载器
        this.name = name;
    }

    /**
     * 加载class文件并将其转换为字节数组
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        byte[] data = null;
        try{
            this.name = this.name.replace(",", "\\");
            in = new FileInputStream(new File(path+name+fileType));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while(-1 != (ch= in.read())){
               baos.write(ch);
            }
            data = baos.toByteArray();
        }catch(Exception e){
           e.printStackTrace();
        }finally {
            try{
                baos.close();
                in.close();

            }catch(Exception e1){
               e1.printStackTrace();
            }
        }
        return data;
    }

    /**
     * 返回一个Class对象
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
        @Override
    public  Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] data = loadClassData(name);
            return this.defineClass(name, data, 0, data.length);
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public static  void test(ClassLoader classLoader) throws Exception {
        Class clazz = classLoader.loadClass("Sample");
        Object object = clazz.newInstance();
    }


    public static void main(String[] args)throws Exception{
        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath("D:\\myClassLoder\\serverlib\\");

        //指定类加载器为 loader1
        MyClassLoader loader2 = new MyClassLoader(loader1,"loader2");
        loader2.setPath("D:\\myClassLoder\\clientlib\\");

        //指定父类加载器为 根加载器
        MyClassLoader loader3 = new MyClassLoader(null,"loader3");
        loader3.setPath("D:\\myClassLoder\\otherlib\\");
        test(loader2);
        test(loader3);
    }
}
