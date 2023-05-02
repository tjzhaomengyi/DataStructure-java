package com.huaweiOD.od2023.s40e59;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 18:42
 * @Description:
 */
public class KuohaoPiepei {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      Stack<Character> stack = new Stack<>();
      List<Character> zuokuohaolist = Arrays.asList('(',  '[', '{');
      List<Character> youkuohaolist = Arrays.asList(')', ']', '}');
      String ans = "true";
      for(char c : str.toCharArray()){
        if(zuokuohaolist.contains(c)){
          stack.push(c);
        } else if(youkuohaolist.contains(c)){
          char peek = stack.peek();
          if(zuokuohaolist.indexOf(peek) == youkuohaolist.indexOf(c)){
            stack.pop();
          } else {
            ans = "false";
            break;
          }
        }
      }
      System.out.println(ans);
    }
  }
}
