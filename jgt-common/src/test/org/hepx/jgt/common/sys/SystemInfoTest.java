package org.hepx.jgt.common.sys;

import org.junit.Test;

import java.util.Properties;

/**
 * @author: Koala
 * @Date: 14-8-28 下午10:50
 * @Version: 1.0
 */
public class SystemInfoTest {

    @Test
    public void printInfo() {
        Properties props = System.getProperties();
        System.out.println("Java的运行环境版本：" + props.getProperty("java.version"));
        System.out.println("默认的临时文件路径：" + props.getProperty("java.io.tmpdir"));
        System.out.println("操作系统的名称：" + props.getProperty("os.name"));
        System.out.println("操作系统的构架：" + props.getProperty("os.arch"));
        System.out.println("操作系统的版本：" + props.getProperty("os.version"));
        System.out.println("文件分隔符：" + props.getProperty("file.separator"));   //在 unix 系统中是＂／＂
        System.out.println("路径分隔符：" + props.getProperty("path.separator"));   //在 unix 系统中是＂:＂
        System.out.println("行分隔符：" + props.getProperty("line.separator"));   //在 unix 系统中是＂/n＂
        System.out.println("用户的账户名称：" + props.getProperty("user.name"));
        System.out.println("用户的主目录：" + props.getProperty("user.home"));
        System.out.println("用户的当前工作目录：" + props.getProperty("user.dir"));

    }

    @Test
    public void printSysteInfo(){
        Properties p = System.getProperties();// 获取当前的系统属性
        System.out.println("操作系统："+p.getProperty("sun.desktop"));
        System.out.print("CPU个数:");// Runtime.getRuntime()获取当前运行时的实例
        System.out.println(Runtime.getRuntime().availableProcessors());// availableProcessors()获取当前电脑CPU数量
        System.out.print("虚拟机内存总量:");
        System.out.println(Runtime.getRuntime().totalMemory());// totalMemory()获取java虚拟机中的内存总量
        System.out.print("虚拟机空闲内存量:");
        System.out.println(Runtime.getRuntime().freeMemory());// freeMemory()获取java虚拟机中的空闲内存量
        System.out.print("虚拟机使用最大内存量:");
        System.out.println(Runtime.getRuntime().maxMemory());// maxMemory()获取java虚拟机试图使用的最大内存量
    }

    @Test
    public void listSP(){
        Properties p = System.getProperties();// 获取当前的系统属性
        p.list(System.out);
    }
}
