package liudu.network.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NIOFileChannel01 {

  public static void main(String[] args) throws IOException {
    String str = "hello,刘度";

    FileOutputStream fileOutputStream = new FileOutputStream("file01.txt");

    // 通过 fileOutputStream 获取 channel
    FileChannel channel = fileOutputStream.getChannel();

    // 创建一个缓冲区
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    //将 str 放入到 byteBuffer 中
    byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));

    //反转
    byteBuffer.flip();

    //将 byteBuffer 的数据写入到 FileChannel
    channel.write(byteBuffer);

    fileOutputStream.close();

  }

}
