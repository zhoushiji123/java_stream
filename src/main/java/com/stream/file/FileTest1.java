package com.stream.file;

import java.io.*;

/**
 * Created by zsj on 2017/4/16.
 * 文件和文件夹创建
 */
public class FileTest1 {

    public static void main(String[] args) {
        File file = new File("D:\\workplace\\dictionary1");
        System.out.println("文件test1是否存在："+file.exists());
        System.out.println("test1是否是目录："+file.isDirectory());
        if(!file.exists())
            file.mkdir();  //目录文件不存在时 建立目录
        System.out.println("文件test1是否存在："+file.exists());
        System.out.println("test1是否是目录："+file.isDirectory());


        File file2 = new File("D:\\workplace\\dictionary1\\test2.txt");
        System.out.println("文件test2是否存在："+file2.exists());
        System.out.println("test2是否是目录："+file2.isDirectory());
        if(!file2.exists()){
            try{
                file2.createNewFile(); //文件不存在则直接创建文件
                FileWriter fileWriter = new FileWriter(file2); //新建filewriter流
                fileWriter.write("my name is zsj \n"); //写入数据到缓冲区中
                fileWriter.write("i am 22 years old \n");
                fileWriter.write("i come from hangzhou \n");
                fileWriter.flush();  //刷新缓冲区 数据到文件中
                fileWriter.close(); //关闭流
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("文件test2是否存在："+file2.exists());
        System.out.println("test2是否是目录："+file2.isDirectory());
        System.out.println("文件test2的路径是："+file2.getPath());
        System.out.println("文件test2的内容是: ");
        int content ;
        try{
            FileReader fileReader = new FileReader(file2); //构建FileReader流
            while((content = fileReader.read())!=-1){
                System.out.print((char)content);  // read()读取一个字符 ，返回int型 结束返回-1  可转为char型打印
            }
            fileReader.close();
        }catch (FileNotFoundException e1){
            e1.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        }



    }
}
