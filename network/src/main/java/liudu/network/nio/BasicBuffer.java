package liudu.network.nio;

import java.nio.IntBuffer;

public class BasicBuffer {

  public static void main(String[] args) {
    //举例说明 buffer 是什么
    //创建一个buffer
    IntBuffer intBuffer = IntBuffer.allocate(5);

    //向 buffer 中存放数据
//    intBuffer.put(10);
//    intBuffer.put(11);
//    intBuffer.put(12);
//    intBuffer.put(13);
//    intBuffer.put(14);
    for (int i = 0; i < intBuffer.capacity(); i++) {
      intBuffer.put(i * 2);
    }

    //不能超过 limit 的大小
//    intBuffer.put(100);

    intBuffer.flip();

    intBuffer.position(2);

    while (intBuffer.hasRemaining()){
      System.out.println(intBuffer.get());
    }

  }

}
