package com.mikemyzhao.LRU;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-10 14:09
 * @Description:
 */
public class LRUNode {
  public int key,val;
  public LRUNode next,prev;
  public LRUNode(int k,int v){
    this.key = k;
    this.val = v;
  }
}
