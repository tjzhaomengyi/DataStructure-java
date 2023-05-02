package com.huaweiOD.nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-12 12:41
 * @Description:
 */
public class MakeString {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextLine()) { // 注意 while 处理多个 case
      String str = in.nextLine();
      String str1 = str.split(" ")[0];
      String str2 = str.split(" ")[1];
      String cStr = step1(str1, str2);
      String trans = step2(cStr);
      System.out.println(trans);
    }
  }

  public static String step1(String str1, String str2){
    String cStr = str1 + str2;
    char[] arrc = new char[cStr.length()];
    ArrayList<Character> jarr = new ArrayList<>();
    ArrayList<Character> oarr = new ArrayList<>();
    for(int i = 0; i < cStr.length(); i++){
      if(i % 2 == 0){
        oarr.add(cStr.charAt(i));
      } else {
        jarr.add(cStr.charAt(i));
      }
    }
    jarr.sort(((o1, o2) -> o1 - o2));
    oarr.sort((o1, o2) -> o1 - o2);
    int jindex = 0;
    int oindex = 0;
    for(int i = 0; i < arrc.length; i++){
      if(i % 2 == 0){
        arrc[i] = oarr.get(oindex++);
      } else {
        arrc[i] = jarr.get(jindex++);
      }
    }
    return String.valueOf(arrc);
  }

  public static String step2(String str){
    char[] ch = str.toCharArray();
    char[] password = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','A','B','C','D','E','F'};
    char[] dictionary = {'0','8','4','C','2','A','6','E','1','9','5','D','3','B','7','F','5','D','3','B','7','F'};
    for(int i = 0; i < str.length();i++){
      if((ch[i]>='0'&&ch[i]<='9')||(ch[i]>='a'&&ch[i]<='f')||(ch[i]>='A'&&ch[i]<='F')) {
        ch[i]=dictionary[String.valueOf(password).indexOf(ch[i])];
      }
    }

    return String.valueOf(ch);
  }

}
