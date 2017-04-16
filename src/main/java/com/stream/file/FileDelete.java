package com.stream.file;

import java.io.File;

/**
 * Created by zsj on 2017/4/16.
 * 文件删除
 * 删除文件夹所有内容(子文件夹和文件)
 */
public class FileDelete {
    public static void main(String[] args) {
        File file = new File("D:\\workplace\\dictionary2");
        if(!file.exists())
            file.mkdir();
        else
            file.delete();

        File file2 = new File("D:\\workplace\\dictionary3");
        File[] fileArray = file2.listFiles();
        for(int i=0;i<fileArray.length;i++){
            if(fileArray[i].isFile())
                fileArray[i].delete();  //如果是普通文件则直接删除
            if(fileArray[i].isDirectory())
                deleteDir(fileArray[i]);  //如果是子文件夹则调用删除文件夹的方法
        }

    }


    public static void deleteDir(File file){  //删除文件夹的方法
        File[] fileArray = file.listFiles();
        for(int i=0;i<fileArray.length;i++){
            if(fileArray[i].isFile())
                fileArray[i].delete();
            if(fileArray[i].isDirectory())
                deleteDir(fileArray[i]);  //递归调用
        }
        file.delete();



    }
}
