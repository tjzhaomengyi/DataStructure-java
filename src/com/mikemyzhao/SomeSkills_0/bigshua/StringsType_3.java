package com.mikemyzhao.SomeSkills_0.bigshua;

import java.util.HashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-15 13:07
 * @Description:  /*
 * 	 * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
 * 	 * 如果其中某两个字符串，所含有的字符种类完全一样，就将两个字符串算作一类 比如：baacba和bac就算作一类
 * 	 * 虽然长度不一样，但是所含字符的种类完全一样（a、b、c） 返回arr中有多少类？
 * 	 *
 */
public class StringsType_3 {
  //一共26个字符，用int[26]表示某个字符是否出现过，出现就标1，然后一种形式就是一个数
  public static int type1(String[] arr){
    HashSet<Integer> types = new HashSet<>();
    for(String str : arr){
      char[] chs = str.toCharArray();
      int key = 0;
      for(int i=0; i < chs.length; i++){
        key |= (1 << (chs[i] - 'a'));
      }
      types.add(key);
    }
    return types.size();
  }
}
