package com.point2offer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 14:03
 * @Description:abc的全排列
 */
public class Charspermutation_38 {
  List<String> res = new LinkedList<>();
  char[] data;
  public String[] permutation(String s) {
    data = s.toCharArray();
    dfs(0);
    return res.toArray(new String[res.size()]);
  }

  void dfs(int x){
    //当函数到第三层就直接返回
    if(x==data.length-1){
      res.add(String.valueOf(data));
      return;
    }
    HashSet<Character> set = new HashSet<>();
    //让每次递归都从当前x开始
    for(int i=x;i<data.length;i++){
      //包含元素跳过
      if(set.contains(data[i])) continue;
      set.add(data[i]);
      //i=x时候不交换，此时x=1，i=2时候才会进行交换，这个时候就是增加路径了
      swap(i,x);
      //临时交换完成进入下一层递归
      dfs(x+1);
      //交换回来恢复原样，保证后续递归
      swap(i,x);

    }
  }

  void swap(int a,int b){
    char tmp = data[a];
    data[a]=data[b];
    data[b]=tmp;
  }

}
