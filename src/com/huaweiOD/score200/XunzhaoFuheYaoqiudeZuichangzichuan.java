package com.huaweiOD.score200;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-30 10:26
 * @Description:
 */
public class XunzhaoFuheYaoqiudeZuichangzichuan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      char non_char = in.nextLine().charAt(0);
      String line = in.nextLine();
      int l = 0;
      int ans = 0;
      int[] count = new int[256];
      for(int i = 0; i < line.length(); i++){
        char tmp = line.charAt(i);
        if(tmp == non_char){ //如果是i当前字符有问题，把L直接挪到下一位
          Arrays.fill(count, 0);
          l = i + 1;
        } else {
          count[tmp]++;
          while(count[line.charAt(l)] == 3){//最左边如果有问题了，赶紧移动左边的
            count[line.charAt(l)]--;
            l++;
          }
          ans = Math.max(ans, i - l + 1);
        }
      }
      System.out.println(ans);
    }
  }
}
