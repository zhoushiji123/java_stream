package com.stream.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zsj on 2017/4/22.
 * 文件复制  基于字节流 FileInputStream和FileOutputStream
 * 不会中文乱码
 */
public class FileCopy3 {

    public static void main(String[] args) {

        try{
            File f1 = new File("D:\\workplace\\dictionary1\\cp1.txt");
            FileInputStream inputStream = new FileInputStream(f1);

            File f2 = new File("D:\\workplace\\dictionary1\\cp2.txt");
            if(!f2.exists())
                f2.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f2);

            byte[] contents = new byte[1024];
            int temp ;

            while((temp = inputStream.read(contents)) != -1){
                outputStream.write(contents,0,temp);
            }
            outputStream.flush();

            inputStream.close();
            outputStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
