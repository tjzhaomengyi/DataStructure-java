package com.huaweiOD.od2023.s60e79;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 19:58
 * @Description:
 */
public class TeyiShuangduanDuilie {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.parseInt(in.nextLine());
      String[] cmds = new String[n * 2];
      for(int i = 0; i < cmds.length; i++){
        cmds[i] = in.nextLine();
      }

      int count = 0;
      int input = 0;
      int output = 0;
      LinkedList<Integer> linkedList = new LinkedList<>();
      for(String cmd :cmds){
        char c = cmd.charAt(0);
        if(c == 'h'){
          linkedList.add(++input);
        } else if(c == 't'){
          linkedList.addLast(++input);
        } else if(c == 'r'){
          if(!linkedList.getFirst().equals(++output)){
            count++;
            linkedList.sort((o1, o2) -> o1 - o2);
          }
          linkedList.removeFirst();
        }
      }
      System.out.println(count);
    }
  }
}
