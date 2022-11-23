package com.mikemyzhao.simulate_reality_13.bigshua;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-09 21:34
 * @Description:通过调查统计最少有多少人，保证每个人的回答都是对的，但是不保证采集的信息的全的，求从文件调查结果推算最少多少人
 */
public class TShirtCalculateCountOfPeoples_27 {
  //数学结论：(1)把相同的数放在一组，比如一个人说和他穿一样衣服的人只有2个，那么穿这个衣服的肯定有3个人，然后再把这三个人放在一组里，泛化后就是有：
  // 当前报数为x，一共c个人报这个数，那么一共有 c/(x+1) ↑取整组<==> (c+x) / (x+1)人穿这样的衣服，每组的人数肯定是 x + 1人.最后这组人的总数：((c+x)/(x+1))*(x+1)
  // (2)向上取整：(a+b-1)/b

  public static int num(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    Arrays.sort(arr);
    int x = arr[0];
    int c = 1;//同样的数一组几个
    int ans = 0;
    for (int i = 1; i < arr.length; i++) {
      if (x != arr[i]) {
        ans += ((c + x) / (x + 1)) * (x + 1);
        x = arr[i];
        c = 1;
      } else {
        c++;
      }
    }
    //技巧：把最后那组算上
    return ans + ((c + x) / (x + 1)) * (x + 1);
  }
}
