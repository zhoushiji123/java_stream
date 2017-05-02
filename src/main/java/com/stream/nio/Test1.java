package com.stream.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zsj on 2017/5/1.
 */
public class Test1 {
    public static void main(String[] args) {
        try {
            FileChannel fc = new FileOutputStream("D:\\workplace\\dictionary1\\channel.txt").getChannel();
            fc.write(ByteBuffer.wrap("111111".getBytes()));
            fc.close();

            //read
            fc = new FileInputStream("D:\\workplace\\dictionary1\\channel.txt").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fc.read(byteBuffer);
            byteBuffer.flip();  //从写模式切换到读模式   读数据之前的准备操作

//            while(byteBuffer.hasRemaining()){
//                System.out.print((char)byteBuffer.get());
//            }
            System.out.println(byteBuffer.asCharBuffer());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        }
    }
}
