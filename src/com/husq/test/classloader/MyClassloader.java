package com.husq.test.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;

public class MyClassloader extends ClassLoader{
	private String pathDir;
	
	private static void cypher(InputStream ips,OutputStream ops) throws IOException{
		int b = -1;  
        while ((b = ips.read()) != -1) {  
        	ops.write(b ^ 0xff);  
        }  
	}
	
	public static void main(String[] args){
		try {
			// 原class文件  
	        String srcPth = "F:\\workspace\\ClassloaderTest\\bin\\com\\husq\\test\\classloader\\MyClass.class";  
	        // 加密class文件存放目录  
	        String desDir = "F:\\workspace\\ClassloaderTest\\defineClass";  
			
			String descFileName = srcPth.substring(srcPth.lastIndexOf("\\"));
			String descPath = desDir+descFileName;
			
			FileInputStream ips = new FileInputStream(srcPth);
			FileOutputStream ops = new FileOutputStream(descPath);
			
			cypher(ips, ops);
			ips.close();
			ops.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String classFileName = pathDir+"\\"+name.substring(name.lastIndexOf(".")+1)+".class";
		System.out.println("classFileName:"+classFileName);
		try {
			FileInputStream ips = new FileInputStream(classFileName);
			ByteArrayOutputStream ops = new ByteArrayOutputStream();
			cypher(ips, ops);
			ips.close();
			byte[] bytes = ops.toByteArray();
			return defineClass(null, bytes, 0,bytes.length);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return super.findClass(classFileName);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		
		return super.loadClass(name);
	}

	
	public MyClassloader(){}
	
	public MyClassloader(String name){
		this.pathDir= name;
	}
}
