import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Executor implements Runnable{

    private final String FILE_NAME = "communicationFile";
    private RandomAccessFile fileAccess;
    private FileChannel channel;
    ByteBuffer buffer;
    private final int CONTINUE = 0;
    private final int RESULT_SENT = 2;
    private final int CALCULATE = 1;
    private final int STOP = -1;
    private final int BUFFER_SIZE = 20;
    private boolean flag = true;

    public Executor() {
        try {
            this.fileAccess = new RandomAccessFile(FILE_NAME, "rw");
            this.channel = fileAccess.getChannel();
            this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (flag) {
                channel.close(); // closing file channel
                fileAccess.close(); // closing RandomAccess file

                this.fileAccess = new RandomAccessFile(FILE_NAME, "rw");
                this.channel = fileAccess.getChannel();
                this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
                buffer.rewind();
                channel.read(buffer);
                buffer.rewind();
                int marker = buffer.getInt();

                if(marker == CALCULATE){


                    int a = buffer.getInt();
                    int b = buffer.getInt();
                    int sum = a + b;

                    channel.close(); // closing file channel
                    fileAccess.close(); // closing RandomAccess file

                    this.fileAccess = new RandomAccessFile(FILE_NAME, "rw");
                    this.channel = fileAccess.getChannel();
                    this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
                    buffer.rewind();
                    buffer.putInt(RESULT_SENT);
                    buffer.putInt(sum);
                    buffer.rewind();
                    channel.write(buffer); // writing into File

                    buffer.rewind();

                    System.out.println( a + " + " + b + " = " + sum);
                }
                if(marker == STOP){
                    channel.close(); // closing file channel
                    fileAccess.close(); // closing RandomAccess file

                    this.fileAccess = new RandomAccessFile(FILE_NAME, "rw");
                    this.channel = fileAccess.getChannel();
                    this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
                    buffer.putInt(CONTINUE);
                    buffer.rewind();
                    channel.write(buffer);
                    flag = false;
                }
            }
            channel.close(); // closing file channel
            fileAccess.close(); // closing RandomAccess file
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Executor().run();
    }
}
