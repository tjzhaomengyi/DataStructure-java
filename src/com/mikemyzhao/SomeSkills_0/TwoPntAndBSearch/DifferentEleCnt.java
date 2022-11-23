package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch;

import com.mikemyzhao.list_1.ListNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 12:27
 * @Description:给定一个数组或者链表，给出不同元素个数，不要对这个数组进行删除操作
 */
public class DifferentEleCnt {
  /**使用快慢指针**/
  int countDiff(int[] nums){
    int n = nums.length;
    if(n==0) return 0;
    int slow =0,fast=1;
    while (fast<n){
      if(nums[fast]!= nums[slow]){
        slow++;
        nums[slow]=nums[fast];//让当前这个数还是fast的
      }else{
        fast++;//等到出现下一个不一样的再替换这个slow吧
      }
    }
    return slow+1;
  }
  ListNode countDiff(ListNode node){
    if(node==null) return null;
    ListNode slow = node,fast=node.next;
    while (fast!=null){
      if(fast.val!=slow.val){
        //nums[slow]=nums[fast]
        slow.next = fast;
        slow=slow.next;
      }else {
        fast=fast.next;
      }
    }
    slow.next=null;
    return node;
  }
}
