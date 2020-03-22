package server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerHandler {


    public static void handleAccept(ServerSocketChannel socket, SelectionKey key) throws IOException {
        System.out.println("Connection accepted");

        //accept the connection and set to non-blocking mode
        SocketChannel client = socket.accept();
        client.configureBlocking(false);
        client.register(NonBlockingServer.selector, SelectionKey.OP_READ);

    }

    public static void handleRead(SelectionKey key) throws IOException{
        System.out.println("INFO: Reading form client");

        //create a serverSocketChannel to read the request
        SocketChannel client = (SocketChannel) key.channel();

        // create buffer to read data
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        client.read(byteBuffer);

        //Parse data from buffer to string
        String data = new String(byteBuffer.array()).trim();
        if (data.length() > 0){
            String[] stringAdd;

            System.out.println("INFO: Received message: " + data);

            if(data.equalsIgnoreCase("exit")){
                client.close();
                System.out.println("Connection closed");
            }else if((stringAdd = data.split(" "))[0].equalsIgnoreCase("add")){
                int a = Integer.parseInt(stringAdd[1]);
                int b = Integer.parseInt(stringAdd[2]);

                System.out.println("Adding two values: " + a + " + " + b);
                int sum = a + b;

                byteBuffer.rewind();
                byteBuffer.putInt(sum);
                byteBuffer.flip();
                client.write(byteBuffer);
            }
        }
    }
}
