package com.stream.file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zsj on 2017/4/16.
 * 文件内容复制  基于FileWriter和FileReader
 */
public class FileCopy {
    public static void main(String[] args) {
        FileWriter fileWriter = null;
        FileReader fileReader = null;
        try{
            fileReader = new FileReader("D:\\workplace\\dictionary1\\cp1.txt");
            fileWriter = new FileWriter("D:\\workplace\\dictionary1\\cp2.txt");
            char contents[] = new char[1024];
            fileReader.read(contents);
            fileWriter.write(contents);
            fileWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                fileReader.close();
                fileWriter.close();
            }catch (IOException e2){
                e2.printStackTrace();
            }

        }
    }
}
