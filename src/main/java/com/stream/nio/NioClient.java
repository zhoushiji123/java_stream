package com.stream.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by zsj on 2017/5/2.
 */
public class NioClient {
    private Selector selector;

    public NioClient init(String ip , int port) throws IOException{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        socketChannel.connect(new InetSocketAddress(ip,port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);  //注册连接事件
        return this;
    }

    public void listen() throws IOException{
        System.out.println("客户端启动");
        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();  //删除已选择的key ，防止重复
                if(selectionKey.isConnectable()){
                    //如果是连接事件
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    if(socketChannel.isConnectionPending())
                        //如果正在连接，则完成连接
                        socketChannel.finishConnect();

                    socketChannel.configureBlocking(false);


                    socketChannel.write(ByteBuffer.wrap(new String("send message to server").getBytes()));

                    socketChannel.register(selector,SelectionKey.OP_READ); //注册接受消息的事件
                    System.out.println("客户端连接成功");
                }else if(selectionKey.isReadable()){//有可读数据的事件
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    socketChannel.read(buffer);
                    String message = new String(buffer.array());
                    System.out.println("receive from server : "+message);


                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            new NioClient().init("localhost",9989).listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
