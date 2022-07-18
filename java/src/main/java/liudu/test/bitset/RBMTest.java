package liudu.test.bitset;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.buffer.MutableRoaringBitmap;

/**
 * @author liudu
 * @title: RBMTest
 * @projectName liuduTest
 * @date 2022/7/18下午2:47
 */
public class RBMTest {

  public static void main(String[] args) throws IOException {

    RoaringBitmap r1 = new RoaringBitmap();
    for (int k = 4000; k < 4255; ++k) {
      r1.add(k);
    }

    for (int k = 4255000; k < 4256000; ++k) {
      r1.add(k);
    }

    byte[] array = new byte[r1.serializedSizeInBytes()];
    r1.serialize(ByteBuffer.wrap(array));

    String str = new String(array, StandardCharsets.ISO_8859_1);
    //将字符串转换为byte数组
    byte[] array1 = str.getBytes(StandardCharsets.ISO_8859_1);

    RoaringBitmap r3= new RoaringBitmap();
    r3.deserialize(ByteBuffer.wrap(array1));


    RoaringBitmap r2 = new RoaringBitmap();
    for (int k = 1000; k < 4255000; k += 1) {
      r2.add(k);
    }

    RoaringBitmap union = RoaringBitmap.or(r2, r3);
    RoaringBitmap intersection = RoaringBitmap.and(r1, r2);

    System.out.println(r3.getLongCardinality());
    System.out.println(r1.getLongCardinality());


  }

}
