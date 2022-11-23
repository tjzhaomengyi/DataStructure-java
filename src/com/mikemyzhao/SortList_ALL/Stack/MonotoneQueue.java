package com.mikemyzhao.HeapAndStrongHeapAndStackAndQueue_0.StackQueue;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-12 11:38
 * @Description:单调队列，单调队列可以解决滑动窗口的一些列问题
 */
public class MonotoneQueue {
  /**
   * 给一个数组，一个滑窗，求滑窗经过后的最大值
   * [1,3,-1,-3,5,3,6,7]，滑窗k=3，[3,3,5,5,6,7]
   *  这种动态场景的性质：在一堆数字中，已知最值为A，如果给这堆数添加一个数B，那么笔记一下A和B就可以得出新值,
   *  但是如果减少一个数的话，就不能直接得到最值了，因为如果减少的这个数恰好是最大值A，那么就要重新找最大值
   * */

  /**
   * 定义单调队列的一些功能
   *  因为单列所以用一个LinkedList来管理，可以实现push和pop
   * **/
  private class MQ {
    private LinkedList<Integer> q = new LinkedList<>();

    //在队尾添加
    void push(int n) {
      //将前面比自己小的元素删除
      while (!q.isEmpty() && q.getLast() < n) {
        q.pollLast();
      }
      q.addLast(n);
    }

    ;

    //返回当前队列汇总最大值
    int max() {
      return q.getFirst();
    }

    //队头元素如果是n删除，就是窗口滑动一下，删除最先进来的
    void pop(int n) {
      if (n == q.getFirst()) {
        q.pollFirst();
      }
    }
  }



  //1、先搭建滑动窗口的架子
  int[] maxSlidingWindow(int[] nums,int k){
    MQ window = new MQ();
    List<Integer> res = new ArrayList<Integer>();
    for(int i =0;i<nums.length;i++){
      if(i<k-1){
        //先把窗口的前k-1填满,
        //注意：开始滑窗不满的是先不要往res结果中添加max结果数据，因为等到滑动到最后，滑窗尾部还会漏出来
        window.push(nums[i]);
      }else{
        //窗口向前移动,移入新元素
        window.push(nums[i]);
        //将最大值记录到结果集中
        res.add(window.max());
        //移除最开始进来的元素
        window.pop(nums[i-k+1]);
      }
    }
    int[] arr = new int[res.size()];
    for(int i=0;i<res.size();i++){
      arr[i] = res.get(i);
    }
    return arr;
  }

  int[] getMaxNumsTest(int[] nums,int k){//超时做法
    int len = nums.length;
    if(k>len){
      return null;
    }
    if(k==1){
      return nums;
    }
    int vallen = len-k+1;
    int[] maxNums = new int[vallen];
    for(int i=0;i<vallen;i++){
      int max = nums[i];
      for(int j=0;j<k;j++){
        if(nums[i+j]>max){
          max=nums[i+j];
        }
      }
      maxNums[i]=max;
    }
    return maxNums;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new MonotoneQueue().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)));
  }

}
