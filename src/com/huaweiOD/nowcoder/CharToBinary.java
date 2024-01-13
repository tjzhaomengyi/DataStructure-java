package com.huaweiOD.nowcoder;


import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-12 14:02
 * @Description:
 */
public class CharToBinary {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextLine()) { // 注意 while 处理多个 case
      String str = in.nextLine();
      int ans16 = Integer.parseInt(str,16);//技巧:输入以16进制进行解析，因为是1位只有0到F
      String newAns16 = Integer.toBinaryString(ans16);//二进制字符串
      System.out.println(newAns16);
      if(newAns16.length() < 4){ //技巧:如果二进制前面为0，补零
        int d = 4 - newAns16.length();
        for(int i = 0; i < d; i++){
          newAns16 ="0" + newAns16;
        }
      }
      String rNewAns16 = new StringBuilder(newAns16).reverse().toString();
      System.out.println(rNewAns16);
      int newNum = Integer.parseInt(rNewAns16,2);//以二进制转成10进制，

      System.out.println(ans16 +  "binary:" + Integer.toBinaryString(ans16) + " rNewAns16:" + rNewAns16 + " newNum:" + newNum);
    }
  }
}
