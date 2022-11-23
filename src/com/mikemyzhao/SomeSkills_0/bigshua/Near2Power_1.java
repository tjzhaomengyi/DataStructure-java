package com.mikemyzhao.SomeSkills_0.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-13 9:34
 * @Description:找到比当前大的最小2的次方数
 */
public class Near2Power_1 {
  public static final int tableSizeFor(int n){
    //先减掉1，最后再恢复,因为是32位所以把1往后推16位每次再与即可
    n--;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0)?1:n+1;
  }

}
