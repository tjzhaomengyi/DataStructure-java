package com.huaweiOD.od2023.s1e19;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-25 18:51
 * @Description:
 */
public class MinganZiduanJiami {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int index = Integer.valueOf(in.nextLine());
      String str = in.nextLine().replaceAll("_+", "_");
      String[] splits = str.split("_");
      StringBuilder pwd = new StringBuilder();
      for(int i = index; i < splits.length; i++){
        if(!splits[i].equals("timeout")){
          pwd.append(splits[i]);
        } else if (splits[i].equals("timeout")){
          break;
        }
      }
      String pwdStr = pwd.toString().replaceAll("[a-zA-Z0-9]", "*");
      StringBuilder sb = new StringBuilder();
      boolean isPwd = false;
      for(int i = 0; i < splits.length; i++){
        if(i == index){
          sb.append(pwdStr + "_");
          isPwd = true;
        } else if(i > index && isPwd && !splits[i].equals("timeout")){
          continue;
        } else if(i > index && isPwd && splits[i].equals("timeout")){
          isPwd = false;
          sb.append(splits[i] + "_");
        } else {
          sb.append(splits[i] + "_");
        }
      }
      System.out.println(sb.toString().substring(0,sb.length() - 1));
    }
  }


}
