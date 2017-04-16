package com.stream.file;

import java.io.*;

/**
 * Created by zsj on 2017/4/16.
 * 文件内容复制2  基于BufferedReader和BufferedWriter
 * 解决中文乱码问题
 */
public class FileCopy2 {
    public static void main(String[] args) {
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("D:\\workplace\\" +
                    "dictionary1\\cp1.txt"),"utf-8");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("D:\\workplace\\" +
                    "dictionary1\\cp2.txt"),"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            String str ;
            while((str = bufferedReader.readLine()) != null){
                System.out.println(str);
                bufferedWriter.write(str);
            }
            bufferedWriter.flush();

            bufferedReader.close();
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
