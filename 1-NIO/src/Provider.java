import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class Provider implements Runnable{

    private static final int SLEEP_TIME = 1000;
    private static final Random RANDOM = new Random();
    private final int CONTINUE = 0;
    private final int WAIT = 1;
    private final int GOT_RESULT = 2;
    private final int STOP = -1;
    private final boolean RUN_FOREVER = true;
    private final int BUFFER_SIZE = 20;

    private int iterationCount;
    private final String FILE_NAME = "communicationFile";

    private RandomAccessFile fileAccess;

    private FileChannel channel;

    private ByteBuffer buffer;

    public Provider(int iterationCount) {
        this.iterationCount = iterationCount;

        try {
            this.fileAccess = new RandomAccessFile(FILE_NAME, "rw");
            this.channel = fileAccess.getChannel();
            this.buffer = ByteBuffer.allocate(BUFFER_SIZE);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (iterationCount > 0) {
                channel.close(); // closing file channel
                fileAccess.close(); // closing RandomAccess file

                this.fileAccess = new RandomAccessFile(FILE_NAME, "rw");
                this.channel = fileAccess.getChannel();
                this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
                channel.read(buffer);

                buffer.rewind();
                int marker = buffer.getInt();

                if(marker == CONTINUE){
                    channel.close(); // closing file channel
                    fileAccess.close(); // closing RandomAccess file

                    this.fileAccess = new RandomAccessFile(FILE_NAME, "rw");
                    this.channel = fileAccess.getChannel();
                    this.buffer = ByteBuffer.allocate(BUFFER_SIZE);

                    int a = RANDOM.nextInt(10);
                    int b = RANDOM.nextInt(10);
                    writeToFile(a, b);
                    System.out.println("Info: Sent: a = " + a + " b = " + b);

                }
                if(marker == GOT_RESULT){
                    int sum = buffer.getInt();


                    channel.close(); // closing file channel
                    fileAccess.close(); // closing RandomAccess file

                    this.fileAccess = new RandomAccessFile(FILE_NAME, "rw");
                    this.channel = fileAccess.getChannel();
                    this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
                    buffer.putInt(CONTINUE);
                    channel.write(buffer);


                    System.out.println("INFO: Result = " + sum);
                    if(!RUN_FOREVER) {
                        iterationCount--;
                    }
                }
                sleep();
            }

            channel.close(); // closing file channel
            fileAccess.close(); // closing RandomAccess file

            this.fileAccess = new RandomAccessFile(FILE_NAME, "rw");
            this.channel = fileAccess.getChannel();
            this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
            buffer.putInt(STOP);
            buffer.rewind();
            channel.write(buffer);

            channel.close(); // closing file channel
            fileAccess.close(); // closing RandomAccess file
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void writeToFile(int a, int b){
        try {

            buffer.rewind();
            buffer.putInt(WAIT);
            buffer.putInt(a);
            buffer.putInt(b);
            buffer.rewind();
            channel.write(buffer); // writing into File

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Provider fileWriter = new Provider(2);
        fileWriter.run();
    }

    private void sleep(){
        try {
            Thread.sleep(SLEEP_TIME);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
