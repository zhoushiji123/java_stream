package com.stream.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by zsj on 2017/5/2.
 * nio文件读取，RandomAccessFile
 */
public class NioFile {
    public static void main(String[] args) {
        try {
            Charset charset = Charset.forName("GBK"); //处理字符编码
            CharsetDecoder decoder = charset.newDecoder();

            RandomAccessFile raf = new RandomAccessFile("D:\\workplace\\dictionary1\\test2.txt","rw");
            FileChannel fileChannel = raf.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            CharBuffer charBuffer = CharBuffer.allocate(1024);

            int count = fileChannel.read(buffer);
            while (count!=-1){
                System.out.println("count = "+count);
                buffer.flip(); //读数据准备操作
                decoder.decode(buffer,charBuffer,false);
                charBuffer.flip();  //读数据的准备操作
                while (charBuffer.hasRemaining()){
                    System.out.print(charBuffer.get());
                }
                buffer.clear();
                charBuffer.clear();
                count = fileChannel.read(buffer);
            }
            raf.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        }

    }
}
