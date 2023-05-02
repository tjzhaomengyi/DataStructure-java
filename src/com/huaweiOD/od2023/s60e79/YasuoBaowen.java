package com.huaweiOD.od2023.s60e79;

import com.MCAAlgorithm.bigshua.class05.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 17:35
 * @Description:这道题和公式/表达式计算是一个算法模板，都是在whie(i<str.length str[i] != ')'分条件判断，然后每一个给小栈)
 */
public class YasuoBaowen {
  //技巧:这道题用左神的公式计算的那个板子,这个递归板子挺有意思
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String line = in.nextLine();
      char[] str = line.toCharArray();
      System.out.println(f(str, 0, ""));
    }
  }


  public static String f(char[] str, int i, String ans){
    String cur = "";
    int num = 0;
    while(i < str.length && str[i] != ')'){
      //如果碰到数字，计算num的值看看有多少个字符
      if(Character.isDigit(str[i])){
        num = num * 10 + Character.getNumericValue(str[i++]);
      } else if(Character.isLetter(str[i])){
        if(num == 0){
          cur = (String.valueOf(str[i]));
        } else {
          for (int n = 0; n < num; n++) {
            cur += (str[i]);
          }
        }
        ans += cur;
        i++;
        num = 0;
        cur = "";
      } else if(str[i] == '('){
        //技巧:最后一个参数为空是因为左神上课讲的那个小栈！，这个就是小栈的意思，里面什么都没有只有自己组里的东西
        String shouji = f(str, i + 1, "");//把结果都返回给带进来的ans，这里的ans什么不要，往下递归，回来收集，
        cur = shouji.split(",")[0];
        i = Integer.parseInt(shouji.split(",")[1]) + 1;
        if(num == 0){
          ans += cur;
        }else {
          for (int n = 0; n < num; n++) {
            ans += cur;
          }
        }
        num = 0;
        cur = "";
      }
    }
    //技巧:在外围做最后一个字符的处理，不要被while过长影响到
    if(num == 0){
      ans += cur;
    } else {
      for (int n = 0; n < num; n++) {
        ans += cur;
      }
    }

    return ans+","+i;
  }


}
