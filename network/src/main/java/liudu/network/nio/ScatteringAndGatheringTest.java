package liudu.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringAndGatheringTest {

  public static void main(String[] args) throws IOException {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);

    serverSocketChannel.socket().bind(inetSocketAddress);

    ByteBuffer[] byteBuffers = new ByteBuffer[2];
    byteBuffers[0] = ByteBuffer.allocate(3);
    byteBuffers[1] = ByteBuffer.allocate(5);

    SocketChannel socketChannel = serverSocketChannel.accept();

    while (true) {
      int byteRead = 0;
      System.out.println("一次循环");
      while (byteRead < 8) {
        long read = socketChannel.read(byteBuffers);
        byteRead += read;
        System.out.println("读取字节数" + byteRead);
        Arrays.stream(byteBuffers).map(x -> "position=" + x.position() + ",limit=" + x.limit())
            .forEach(System.out::println);
      }

      Arrays.asList(byteBuffers).forEach(ByteBuffer::flip);

      long byteWrite = 0;
      while (byteWrite < 8) {
        long write = socketChannel.write(byteBuffers);
        byteWrite += write;
      }

      Arrays.asList(byteBuffers).forEach(ByteBuffer::clear);

    }
  }

}
