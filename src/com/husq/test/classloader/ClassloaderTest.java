package com.husq.test.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;

public class ClassloaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			//1，使用jvm已有的appclassloader加载LoaderClass类文件；当class未加密之前正常，使用加密后class文件替换字节码文件，抛出运行时异常
//			MyClass cl = new MyClass();
//			System.out.println(cl);
			
			//2，使用自定义classloader加载LoaderClass字节码文件，并解密，运行正常；
			Class clazz = new MyClassloader("F:\\workspace\\ClassloaderTest\\bin\\com\\husq\\test\\classloader").loadClass("com.husq.test.classloader.MyClass");
			Date d = (Date)clazz.newInstance();
			System.out.println(d);
			
//			String name = "com.husq.test.classloader.MyClass";
//			System.out.println(name.substring(name.lastIndexOf(".")+1)+".class");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
