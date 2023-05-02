package com.huaweiOD.od2023.s120e138;


import java.sql.PseudoColumnUsage;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 14:39
 * @Description:
 */
public class FiveKeyboards {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] list = str.split(" ");
      StringBuilder sb = new StringBuilder();
      String select = "";
      String copy = "";
      for(String op : list){
        if(op.equals("1")){
          select = empty(sb, select);
          sb.append("A");
        } else if(op.equals("2")){
          copy = !select.isEmpty() ? select : copy;
        } else if(op.equals("3")){
          if(!select.isEmpty()){
            copy = select;
            select = "";
            sb = new StringBuilder();
          }
        } else if(op.equals("4")){
          select = empty(sb, select);
          sb.append(copy);
        } else if(op.equals("5")){
          select = sb.length() != 0 ? sb.toString() : select;
        }
      }

      System.out.println(sb.length());
    }
  }

  private static String empty(StringBuilder sb, String select){
    if(!select.isEmpty()){
      sb.replace(0, sb.length(), "");
      select = "";
    }
    return select;
  }


}
