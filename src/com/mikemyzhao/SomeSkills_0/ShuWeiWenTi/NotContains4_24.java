package com.mikemyzhao.SomeSkills_0.bigshua.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-06 21:23
 * @Description:里程表不能包含4，求显示的这个数是真正的多少里程
 */
public class NotContains4_24 {
  //数学结论：9进制转10进制
  public static long notContain4Num(long num){
    if(num <= 0){
      return 0;
    }
    long ans = 0;
    for(long base = 1, cur = 0; num != 0; num /= 10, base *= 9){
      cur = num % 10;
      ans += (cur < 4 ? cur : cur - 1) * base;
    }
    return ans;
  }

  //技巧：按照数位的方法求解
  public static int decimaLength(Long num){
    int len = 0;
    while(num != 0){
      len++;
      num /= 10;
    }
    return len;
  }
  public static long offset(int len){
    long offset = 1;
    for(int i = 1; i < len; i++){
      offset *= 10L;
    }
    return offset;
  }
  // arr[1] : 比1位数还少1位，有几个数(数字里可以包含0但是不可以包含4)？0个
  // arr[2] : 比2位数还少1位，有几个数(数字里可以包含0但是不可以包含4)？9个
  // 1 -> 0 1 2 3 - 5 6 7 8 9 = 9
  // arr[3] :比3位数还少1位，有几个数(数字里可以包含0但是不可以包含4)？81个
  // 1 : 0 (0 1 2 3 - 5 6 7 8 9) = 9
  // 2 :
  // 1 (0 1 2 3 - 5 6 7 8 9) = 9
  // 2 (0 1 2 3 - 5 6 7 8 9) = 9
  // 3 (0 1 2 3 - 5 6 7 8 9) = 9
  // 5 (0 1 2 3 - 5 6 7 8 9) = 9
  // ...
  // 9 (0 1 2 3 - 5 6 7 8 9) = 9
  public static long[] arr = { 0L, 1L, 9L, 81L, 729L, 6561L, 59049L, 531441L, 4782969L, 43046721L, 387420489L,
      3486784401L, 31381059609L, 282429536481L, 2541865828329L, 22876792454961L, 205891132094649L,
      1853020188851841L, 16677181699666569L, 150094635296999121L, 1350851717672992089L };

  public static long process(long num, long offset, int len){
    if(len == 0){
      return 1;
    }
    long first = num / offset;
    return (first < 4 ? first : (first - 1)) * arr[len]
        + process(num % offset, offset / 10 , len - 1);
  }
  public static long notContains4Num2(long num){
    if(num <= 0){
      return 0;
    }
    int len = decimaLength(num);
    long offset = offset(len);

    //第一位数字
    long first = num / offset;
    return arr[len] - 1
          + (first - (first < 4 ? 1 : 2)) * arr[len]
          + process(num % offset , offset / 10, len - 1);
  }
}
