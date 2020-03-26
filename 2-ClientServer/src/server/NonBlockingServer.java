package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingServer {


    private ServerSocketChannel serverSocketChannel;
    public static Selector selector;

    private NonBlockingServer(int port){
        try{
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            SocketAddress address = new InetSocketAddress(port);
            serverSocketChannel.socket().bind(address);
            serverSocketChannel.configureBlocking(false);
            int ops = serverSocketChannel.validOps();
            serverSocketChannel.register(selector, ops, null);

            while(true) {
                selector.select();
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> i = selectionKeySet.iterator();

                while(i.hasNext()){
                    SelectionKey key = i.next();

//                    isAcceptable() - checking if client is requesting a connection
                    if(key.isAcceptable()){
                        ServerHandler.handleAccept(serverSocketChannel);

//                    isReadable() - method to read data when client has prepared data.
                    } else if (key.isReadable()){
                        ServerHandler.handleRead(key);
                    }
                    i.remove();
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NonBlockingServer(8089);
    }
}
