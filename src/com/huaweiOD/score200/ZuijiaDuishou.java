package com.huaweiOD.score200;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-23 8:55
 * @Description:
 */
public class ZuijiaDuishou {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int n = in.nextInt();
      int d = in.nextInt();
      int[] arr = new int[n];
      for(int i = 0; i < n; i++){
        arr[i] = in.nextInt();
      }
      int ans = getTotalDiff(arr, d);
      System.out.println(ans);
    }
  }

  public static int getTotalDiff(int[] arr, int d){
    Arrays.sort(arr);
    int ans = 0;
    for(int i = 1; i < arr.length; i++){
      int pre = arr[i - 1];
      int next = i == arr.length - 1 ? -1 : arr[i + 1];
      int cur = arr[i];
      if(cur == -1){
        continue;
      }
      if(pre >= 0 && cur >= 0 && cur - pre <= d) {
        ans += cur - pre;
        arr[i - 1] = -1;
        arr[i] = -1;
      } else if(cur >= 0 && next >= 0 && next - cur <= d){
        ans += next - cur;
        arr[i] = -1;
        arr[i + 1] = -1;
      }

    }
    return ans == 0 ? -1 : ans;
  }


}
