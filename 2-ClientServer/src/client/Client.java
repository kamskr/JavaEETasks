package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try{
            System.out.println("Starting client...");

            Scanner in = new Scanner(System.in);
            String message;
            SocketChannel client = SocketChannel.open(new InetSocketAddress(8089));

            while (!(message = in.nextLine()).equalsIgnoreCase("exit")){
                System.out.println("Prepared message: " + message);

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put(message.getBytes());
                buffer.flip();
                int bytesWritten = client.write(buffer);
                System.out.println(String.format("Sending Message: %s\nbufforBytes: %d", message, bytesWritten));

                if(message.split(" ")[0].equalsIgnoreCase("add")){

                }

            }

            client.close();
            System.out.println("Client connection closed");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
