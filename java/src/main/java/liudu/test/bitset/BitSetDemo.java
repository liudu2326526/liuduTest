package liudu.test.bitset;

import java.util.BitSet;

/**
 * @author liudu
 * @title: BitSetDemo
 * @projectName liuduTest
 * @date 2022/5/7下午4:24
 */
public class BitSetDemo {

  public static void main(String[] args) {
    BitSet bits1 = new BitSet(Integer.MAX_VALUE);
    BitSet bits2 = new BitSet(16);

    bits1.set(1);
    bits1.set(2);
    bits1.set(Integer.MAX_VALUE -1 );

    bits2.set(2);
    bits2.set(3);

    bits1.or(bits2);

    byte[] bytes = bits1.toByteArray();

    System.out.println(bytes.length);

    System.out.println(Integer.MAX_VALUE);

  }

}
