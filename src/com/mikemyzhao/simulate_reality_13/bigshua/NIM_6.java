package com.mikemyzhao.SomeSkills_0.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 18:13
 * @Description:NIM博弈问题，一个arr，里面有n个数大于0，先后手拿数，到谁遇见里面的数全为0谁输
 */
public class NIM_6 {
  public static void nim(int[] arr){
    if(arr == null || arr.length == 0){
      return;
    }
    int ans = 0;
    for(int i = 0; i < arr.length; i++){
      ans = ans ^ arr[i];
    }
    if(ans == 0) {
      System.out.println("后手赢");
    }else{
      System.out.println("先手赢");
    }
  }
  public static void main(String[] args) {
    nim(new int[]{1,2,3,4});
  }
}
