package com.mikemyzhao.SortList_ALL.Stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-12 10:28
 * @Description:单调栈，就是栈内元素保持递增或者递减
 * 对应例题就是输入一个数组，返回等长数组，记录对应位置的下一个比它大的值,没有填-1
 * [2,1,2,4,3]-->[4,2,4,-1,-1]
 *              |
 *              |     |
 *  |       |   |     |
 *  |   |   |   |     |
 *  2   1   2   4     3
 *
 */
public class MonotoneStack {
  int[] nextGreaterEle(int[] nums){
    int[] ans = new int[nums.length];//存放最终结果
    Stack<Integer> tmp_high = new Stack<>();//存放此时第一高度
    //从后向前遍历,
    for(int i=nums.length-1;i>=0;i--){//倒着往高度临时栈存入
      while(!tmp_high.empty()&& tmp_high.firstElement()<=nums[i]){
        //如果栈不为空，并且栈顶的元素小于当前入高度临时栈的元素，反正这个小的在后面，就把它删除
        tmp_high.pop();//矮个子出列，反正被挡着了
      }
      ans[i]=tmp_high.empty()?-1:tmp_high.peek();//存入这个元素的第一高
      tmp_high.push(nums[i]);//进入高度临时队列等待判定
    }
    return ans;
  }

  /**
   * 单调队列题目变形：
   * 给定一个气温，找到要等多少天才能变暖，没有的话返回0
   * T=[73,74,75,71,69,72,76,73]
   * **/

  int[] dailyTemp(int[] nums){
    int[] ans = new int[nums.length];
    Stack<Integer> tmp_high = new Stack<>();//这里放索引
    for(int i = nums.length-1;i>=0;i--){
      while(!tmp_high.empty() && nums[tmp_high.firstElement()]<=nums[i]){
        tmp_high.pop();
      }
      ans[i] = tmp_high.empty()?0:(tmp_high.firstElement()-i);
      tmp_high.push(i);//临时栈里面放索引
    }
    return ans;
  }

  /**
   * 处理循环数组
   * 还是第一个问题，如果给的数组是环形的
   * [2,1,2,4,3]-->[4,2,4,-1,4]
   * 计算机本来没有环，只能处理链式，但是用取余可以设计成有环的结构
   * int[] arr = [1,2,3,4,5];
   * int n = arr.length;index=0;
   * while(1):
   *    print(arr[index])
   *    index=(index+1)%n
   *这个问题的技巧就是double一下原来的链表，让后面的能在后面看见前面的元素
   * 但是可以不用在空间上构造出来，只需要用一下取余的tricky
   * **/
  int[] nextGreatEleCycle(int[] nums){
    int n = nums.length;
    int[] res = new int[n];
    Stack<Integer> tmp_high = new Stack<>();
    //假设这个数组翻倍了
    for(int i=2*n-1;i>0;i--){
      while(!tmp_high.isEmpty() && tmp_high.peek()<=nums[i%n]){
        tmp_high.pop();
      }
      //利用%求模防止边界溢出
      res[i%n] = tmp_high.empty()?-1:tmp_high.peek();
      tmp_high.push(nums[i%n]);
    }
    return res;
  }


}
