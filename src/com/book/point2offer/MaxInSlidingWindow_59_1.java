package com.book.point2offer;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 22:51
 * @Description:
 */
public class MaxInSlidingWindow_59_1 {
  public int[] maxSlidingWindow(int[] nums, int k) {
    LinkedList<Integer> q = new LinkedList<>();//注意：这个数据结构的时候，LC是自己实现
    List<Integer> max_lst = new ArrayList();
    //在队列中存储最大值的下标，这样判断是否在lr范围内，如果在就保留，不在就拉鸡巴倒
    for(int i=0;i<nums.length;i++){
      //保证从大到小，如果前面数小需要依次弹出，直到满足需求,保证队列里面存最大的
      /**双端队列API：peekLast()返回队列最后元素,但是不删除，q.add(1),q.add(2),peekLast()//2；
       * pollLast()也是返回队列中最后元素，但是删除,pollLast(),[1]
       * **/
      //1、新进来的数先判断是否比队列中的最后元素大
      while(!q.isEmpty()&&nums[q.peekLast()]<=nums[i]){
        q.pollLast();//2、大的话把原来最后一个大的删掉
      }
      q.addLast(i);//3、把的大的下标添加进去这样方便后续查找
      /**双端队列API,q.peek()取得队首元素，不删除**/
      //4、判断队列中队首值是否在窗口范围内
      if(q.peek()<=i-k){
        q.poll();//不应该在窗口范围内出队
      }
      //5、判断窗口最左边是否符合要求，当窗口长度为k是，保存当前窗口中最大值，注意：最大值就是队头元素
      if (i+1>=k){
        max_lst.add(q.peek());//如果不放心可以改成mmax_lst.add(Collections.max(q));
      }
    }
    //注意使用stream将list转为int[] 数组,这个得到的是下标
//    return max_lst.stream().mapToInt(Integer::valueOf).toArray();
    int[] res = new int[max_lst.size()];
    for(int i=0;i<max_lst.size();i++){
      res[i]=nums[max_lst.get(i)];
    }
    return res;
  }

  public static void main(String[] args) {
    new MaxInSlidingWindow_59_1().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3);
  }
}
