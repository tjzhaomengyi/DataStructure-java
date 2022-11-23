package com.point2offer;


import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 18:23
 * @Description:给一个字符串，找到最小的数字组合方式
 * @Solve：自定义排序，如果：x拼接y>y拼接x，说明x"大于"y；如果x拼接y<y拼接x,说明x"小于"y,例如【3，30】，330>303，说明3"大于"30，要放在30右边
 */
public class MinNumber_45 {
  public String minNumber(int[] nums) {
    String[] strs = new String[nums.length];
    for(int i=0;i<nums.length;i++){
      strs[i]=String.valueOf(nums[i]);
    }
    quick(strs,0,strs.length-1);
    StringBuilder res = new StringBuilder();
    for(String s:strs){
      res.append(s);
    }
    return res.toString();
    //自定义排序
  }
  void quick(String[] strs,int l,int r){
    if(l>=r) return;
    int i=l,j=r;
    String tmp=strs[i];
    while(i<j){
      while((strs[j]+strs[l]).compareTo(strs[l]+strs[j])>=0 && i<j)j--;
      while((strs[i]+strs[l]).compareTo(strs[l]+strs[i])<=0 && i<j)i++;
      tmp = strs[i];
      strs[i]=strs[j];
      strs[j]=tmp;
    }
    //todo:这两句话什么意思？
    strs[i] = strs[l];
    strs[l] = tmp;
    quick(strs,l,i-1);
    quick(strs,i+1,r);
  }

  public String minNumberII(int[] nums){
    //使用内置函数
    String[] strs = new String[nums.length];
    for(int i=0;i<nums.length;i++){
      strs[i]=String.valueOf(nums[i]);
    }
    Arrays.sort(strs,(x,y)->(x+y).compareTo(y+x));
    StringBuilder res = new StringBuilder();
    for(String s:strs){
      res.append(s);
    }
    return res.toString();
  }

}
