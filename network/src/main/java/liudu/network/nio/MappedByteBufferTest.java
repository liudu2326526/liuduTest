package liudu.network.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class MappedByteBufferTest {

  public static void main(String[] args) throws IOException {
    RandomAccessFile randomAccessFile = new RandomAccessFile("file01.txt","rw");

    FileChannel channel = randomAccessFile.getChannel();

    MappedByteBuffer mappedByteBuffer = channel.map(MapMode.READ_WRITE, 0, 5);

    mappedByteBuffer.put(0,(byte) 'H');
    mappedByteBuffer.put(3,(byte) '9');

    randomAccessFile.close();

  }

}
