package liudu.network.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {

  public static void main(String[] args) throws IOException {
    File file = new File("file01.txt");
    FileInputStream fileInputStream = new FileInputStream(file);

    FileChannel inputStreamChannel = fileInputStream.getChannel();

    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
    String s = new String(byteBuffer.array());

    FileOutputStream fileOutputStream = new FileOutputStream("file02.txt");

    FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

    while (true) {
      byteBuffer.clear();
      int read = inputStreamChannel.read(byteBuffer);
      if (read == -1) {
        break;
      }
      byteBuffer.flip();
      fileOutputStreamChannel.write(byteBuffer);
    }

    fileOutputStream.close();
    fileInputStream.close();

  }

}
