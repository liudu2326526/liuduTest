package liudu.network.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NIOFileCopy {

  public static void main(String[] args) throws IOException {
    //创建相关的流
    FileInputStream fileInputStream = new FileInputStream("file02.txt");
    FileOutputStream fileOutputStream = new FileOutputStream("file03.txt");

    FileChannel outputStreamChannel = fileOutputStream.getChannel();
    FileChannel inputStreamChannel = fileInputStream.getChannel();

    outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());

    inputStreamChannel.close();
    outputStreamChannel.close();
    fileInputStream.close();
    fileOutputStream.close();
  }

}
