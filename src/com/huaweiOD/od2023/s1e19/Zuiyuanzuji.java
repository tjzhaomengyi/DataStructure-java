package com.huaweiOD.od2023.s1e19;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 18:04
 * @Description:
 */
public class Zuiyuanzuji {
public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  while (in.hasNextLine()) {
    String str = in.nextLine();
    ArrayList<String> zuobiaos = new ArrayList<>();
    boolean isZuobiao = false;
    int zuobiaoStart = -1;
    for(int i = 0; i < str.length(); i++){
      if(str.charAt(i) == '('){
        isZuobiao = true;
        zuobiaoStart = i;
      } else if(str.charAt(i) == ')' && isZuobiao == true){
        zuobiaos.add(str.substring(zuobiaoStart, i + 1));
      }
    }

    if(zuobiaos.isEmpty()){
      System.out.println("(0,0)");
    }
    int max = 0;
    String ans_pos = "";
    for(int i = 0; i < zuobiaos.size(); i++){
      String strzb = zuobiaos.get(i);
      if(isHefa(strzb)){
        int x = Integer.valueOf(strzb.substring(1, strzb.length() - 1).split(",")[0]);
        int y = Integer.valueOf(strzb.substring(1, strzb.length() - 1).split(",")[1]);
        int dis = calDistance(x, y);
        if(dis > max){
          max = dis;
          ans_pos = strzb;
        }
      }
    }
    if(!ans_pos.equals("")){
      System.out.println(ans_pos);
    } else {
      System.out.println("(0,0)");
    }
  }
}

public static boolean isHefa(String pos){
  String str = pos.substring(1, pos.length() - 1);
  String[] splits = str.split(",");
  if(splits.length != 2){
    return false;
  }
  for(String s : splits){
    if(s.startsWith("0")){
      return false;
    } else if(Integer.valueOf(s) <=0 || Integer.valueOf(s) >= 1000){
      return false;
    }
  }
  return true;
}

public static int calDistance(int x, int y){
  return x * x + y * y;
}

}
