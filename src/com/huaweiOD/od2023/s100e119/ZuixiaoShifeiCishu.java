package com.huaweiOD.od2023.s100e119;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 10:42
 * @Description:
 */
public class ZuixiaoShifeiCishu {
    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      while (in.hasNextInt()) {
        int m = in.nextInt();//地数
        int n = in.nextInt();//天数
        int[] fields = new int[m];
        for(int i = 0; i < m; i++){
          fields[i] = in.nextInt();
        }
        System.out.println(getAns(m, n, fields));
      }

    }

    public static int getAns(int m, int n, int[] fields){
      if(n < m){
        return - 1;
      }
      Arrays.sort(fields);
      for(int i = fields[0]; i <= fields[fields.length - 1]; i++){
        int day = 0;
        for(int k : fields){
          int field = k;
          if(field <= i){
            day++;
          } else {
            day += (field + i - 1) / i;
          }
          if(day > n){
            break;
          }
        }

        if(day == n){
          return i;
        }
      }
      return -1;
    }

}
