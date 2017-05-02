package com.stream.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by zsj on 2017/5/1.
 * NIO的selector , 管理多个通道，使1个线程处理多个通道。
 */
public class TestSelector {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();  //创建Selector

            //监听类型
            int interestSet = SelectionKey.OP_READ | SelectionKey.OP_CONNECT | SelectionKey.OP_ACCEPT | SelectionKey.OP_WRITE;
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false); //搭配selector必须channer是非阻塞的
            channel.connect(new InetSocketAddress("localhost",9989));

            channel.register(selector,interestSet); //注册

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); //获得selectionKey的迭代器,包含多个属性
            while (iterator.hasNext()){
                SocketChannel socketChannel = (SocketChannel)iterator.next().channel();  //获得channel
            }
            selector.select() ; //进行阻塞
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
