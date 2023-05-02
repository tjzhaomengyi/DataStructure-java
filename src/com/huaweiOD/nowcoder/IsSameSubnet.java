package com.huaweiOD.nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-12 15:29
 * @Description:
 */
public class IsSameSubnet {
  // 注意类名必须为 Main, 不要有任何 package xxx 信息
    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      // 注意 hasNext 和 hasNextLine 的区别
      while (in.hasNextLine()) { // 注意 while 处理多个 case
          String mask = in.nextLine();
          String ip1 = in.nextLine();
          String ip2 = in.nextLine();
          String sub1 = getSubNet(mask, ip1);
          String sub2 = getSubNet(mask, ip2);
          System.out.println(sub1);
          System.out.println(sub2);
          if(sub1.equals("") || sub2.equals("")){
            System.out.println("1");
          } else if(sub1.equals(sub2)){
            System.out.println("0");
          } else {
            System.out.println("2");
          }
      }
    }

    public static String getSubNet(String mask, String ip){
      String subNetAns = "";
      if(!isValidMask(mask) || !isValid(ip)) return "";
      if(isValidMask(mask) && isValid(ip)){
       String[] maskarr = mask.split("\\.");
       String[] iparr = ip.split("\\.");
        for(int i = 0; i < maskarr.length; i++){
          String maskp = makePartToString(maskarr[i]);
          String ipp = makePartToString(iparr[i]);
          for(int j = 0; j < 8; j++){
            int b = Integer.valueOf(String.valueOf(maskp.charAt(j))) & Integer.valueOf(String.valueOf(ipp.charAt(j))); //注意这里一定要先把char转成String
            subNetAns = subNetAns + b;
          }
        }
      }
      return subNetAns;
    }

    public static boolean isValid(String mask){
       String[] part = mask.split("\\.");
       for(int i = 0; i < 4;i++){

         if(Integer.valueOf(part[i]) > 255 || Integer.valueOf(part[i]) < 0){
           return false;
         }
       }
       return true;
    }

  public static boolean isValidMask(String mask){
    if(!isValid(mask)) {
      return false;
    }
    String[] parts = mask.split("\\.");
    String allbin = "";
    for(int i = 0 ; i < 4; i++){
      String binStr = Integer.toBinaryString(Integer.parseInt(parts[i]));
      if(binStr.length() < 8){
        for(int j = 0; j < 8 - binStr.length(); j++){
          binStr = "0" + binStr;
        }
      }
      allbin = allbin + binStr;
    }

      if(allbin.lastIndexOf("1") > allbin.indexOf("0")){
        return false;
      }

    return true;
  }

    public static String makePartToString(String ipPart){
      String tmp = Integer.toBinaryString(Integer.parseInt(ipPart));
      String ans = tmp;
      if(tmp.length() < 8){
        int d = 8 - tmp.length();
        for(int i = 0; i < d; i++){
          ans = "0" + ans;
        }
      }
      return ans;
    }
}
