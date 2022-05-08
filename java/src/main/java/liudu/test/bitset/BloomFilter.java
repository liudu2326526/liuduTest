package liudu.test.bitset;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import lombok.extern.log4j.Log4j2;

/**
 * @author liudu
 * @title: BloomFilter
 * @projectName liuduTest
 * @date 2022/5/7下午4:45
 */
public class BloomFilter {


  private static final String BF_KEY_PREFIX = "bf:";

  private int numApproxElements;
  private double fpp;
  private int numHashFunctions;
  private int bitmapLength;

  private List<BitSet> bitSets;

  /**
   * 构造布隆过滤器。注意：在同一业务场景下，三个参数务必相同
   *
   * @param numApproxElements 预估元素数量
   * @param fpp               可接受的最大误差（假阳性率）
   */
  public BloomFilter(int numApproxElements, double fpp) {
    this.numApproxElements = numApproxElements;
    this.fpp = fpp;

    bitmapLength = (int) (-numApproxElements * Math.log(fpp) / (Math.log(2) * Math.log(2)));
    numHashFunctions = Math
        .max(1, (int) Math.round((double) bitmapLength / numApproxElements * Math.log(2)));

    bitSets = new ArrayList<BitSet>();
    for (int i = 0; i < numHashFunctions; i++) {
      bitSets.add(new BitSet(bitmapLength));
    }


  }

  /**
   * 取得自动计算的最优哈希函数个数
   */
  public int getNumHashFunctions() {
    return numHashFunctions;
  }

  /**
   * 取得自动计算的最优Bitmap长度
   */
  public int getBitmapLength() {
    return bitmapLength;
  }


  /**
   * 计算一个元素值哈希后映射到Bitmap的哪些bit上
   *
   * @param element 元素值
   * @return bit下标的数组
   */
  private int[] getBitIndices(String element) {
    int[] indices = new int[numHashFunctions];

    byte[] bytes = Hashing.murmur3_128()
        .hashObject(element, Funnels.stringFunnel())
        .asBytes();

    int hash1 = Ints.fromBytes(
        bytes[3], bytes[2], bytes[1], bytes[0]
    );
    int hash2 = Ints.fromBytes(
        bytes[7], bytes[6], bytes[5], bytes[4]
    );

    int combinedHash = hash1;
    for (int i = 0; i < numHashFunctions; i++) {
      indices[i] = (combinedHash & Integer.MAX_VALUE) % bitmapLength;
      combinedHash += hash2;
    }

    return indices;
  }

  /**
   * 插入元素
   *
   * @param key       原始Redis键，会自动加上'bf:'前缀
   * @param element   元素值，字符串类型
   * @param expireSec 过期时间（秒）
   */
  public void insert(String key, String element, int expireSec) {
    if (key == null || element == null) {
      throw new RuntimeException("键值均不能为空");
    }
    String actualKey = BF_KEY_PREFIX.concat(key);
    int[] bitIndices = getBitIndices(element);
    for (int i = 0; i < bitIndices.length; i++) {
      bitSets.get(i).set(bitIndices[i]);
    }
  }

  /**
   * 检查元素在集合中是否（可能）存在
   *
   * @param key     原始Redis键，会自动加上'bf:'前缀
   * @param element 元素值，字符串类型
   */
  public boolean mayExist(String key, String element) {
    if (key == null || element == null) {
      throw new RuntimeException("键值均不能为空");
    }
    String actualKey = BF_KEY_PREFIX.concat(key);
    boolean result = false;



    return result;
  }

}
