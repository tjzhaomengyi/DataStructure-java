package com.huaweiOD.od2023.s80e99;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 10:40
 * @Description:
 */
public class Njinzhijianfa {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] splits = str.split(" ");
      int radix = Integer.parseInt(splits[0]);
      BigInteger a = new BigInteger(splits[1], radix);
      BigInteger b = new BigInteger(splits[2], radix);
      BigInteger diff = a.subtract(b);
      int sign = diff.compareTo(BigInteger.ZERO) >= 0 ? 0 : 1;
      String ans = diff.abs().toString(radix);
      System.out.println(sign + " " + ans);
    }
  }

}
