package com.stream.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by zsj on 2017/5/2.
 */
public class NioServer {
    private Selector selector;  //通道管理器

    /**
     * 调用初始化，返回自己
     * @param port
     * @return
     * @throws IOException
     */
    public NioServer init(int port) throws IOException{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);  //设置成非阻塞，异步
        serverSocketChannel.socket().bind(new InetSocketAddress(port));  //设置端口号

        selector = Selector.open(); //打开通道选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);  //通道注册，注册事件为accept，否则一直阻塞
        return  this;
    }


    /**
     * 监听方法
     * @throws IOException
     */
    public void listen() throws IOException{
        System.out.println("启动服务器");
        while (true){
            selector.select();  //接受到请求，否则阻塞
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();//selector的迭代器，选中项为注册事件

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();  //删除选中的key 防止重复处理

                //客户端请求连接事件
                if(selectionKey.isAcceptable()){
                    //如果是接受到了连接
                    ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);


                    socketChannel.write(ByteBuffer.wrap(new String("send message to client").getBytes()));
                    socketChannel.register(selector,SelectionKey.OP_READ);

                    System.out.println("客户端请求事件");
                }else if(selectionKey.isReadable()){  //可读数据事件
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    int read = socketChannel.read(buffer);
                    byte[] data = buffer.array();
                    String message = new String(data);
                    System.out.println("recevice from client : "+message);

                }
            }

        }
    }

    public static void main(String[] args) {
        try {
            new NioServer().init(9989).listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
