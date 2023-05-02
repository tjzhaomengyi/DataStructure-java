package com.huaweiOD.nowcoder;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class IsValidIp {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNext()) { // 注意 while 处理多个 case
      // int a = in.nextInt();
      // int b = in.nextInt();
      String ip = in.next();
      System.out.println(isValidIp(ip) ? "YES" : "NO");

    }
  }

  public static boolean isValidIp(String ip){
    String[] parts = ip.split("\\.");
    int part_size = parts.length;
    if(part_size != 4){
      return false;
    }
    for(int i = 0 ; i < 4; i++){
      String part = parts[i];
      for(int j = 0; j < part.length(); j++){
        if(String.valueOf(part.charAt(j)).equals("")){
          return false;
        }else if(!Character.isDigit(part.charAt(j))){
          return false;
        }
      }
      int partval = Integer.valueOf(part);
      if(partval < 0 || partval > 255){
        return false;
      }
    }

    return true;
  }
}
