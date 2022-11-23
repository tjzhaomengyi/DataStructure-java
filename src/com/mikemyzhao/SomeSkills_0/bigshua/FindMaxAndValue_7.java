package com.mikemyzhao.SomeSkills_0.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-19 10:19
 * @Description:找出数组总两个数值的最大与结果
 */
public class FindMaxAndValue_7 {
  public static int maxAndValue(int[] arr){
    int M = arr.length;
    int ans = 0;
    for(int bit = 30; bit >= 0; bit--){
      int i = 0;
      int tmp = M;//M用来标记垃圾区，如果该位为0放入垃圾区
      while(i < M){
        if((arr[i] & (1 << bit)) == 0){
          swap(arr, i, --M);//如果该位为0放入垃圾区，并把垃圾区往前调整
        } else {
          i++;
        }
      }
      if(M == 2){
        return arr[0] & arr[1];//这两个数很大
      }
      if(M < 2){
        //垃圾区回去这波不行
        M = tmp;
      }else{
        ans |= (1 << bit);//这一位1多于两个往后继续看下一位
      }
    }
    return ans;
  }

  public static void swap(int[] arr, int i ,int j){
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
