package liudu.network.nio;

import java.nio.ByteBuffer;

public class NOIByteBufferPutGet {

  public static void main(String[] args) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(64);

    byteBuffer.putInt(100);
    byteBuffer.putLong(9);
    byteBuffer.putChar('刘');
    byteBuffer.putShort((short) 5);

    byteBuffer.flip();

    System.out.println(byteBuffer.getInt());
    System.out.println(byteBuffer.getLong());
    System.out.println(byteBuffer.getChar());
    System.out.println(byteBuffer.getShort());
  }
}
