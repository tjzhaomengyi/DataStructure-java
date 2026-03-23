package com.hotinterview.erfenchazhao;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-26 6:07 下午
 * @Description: 查找数组中的山峰值，这个位置的元素要比左右两侧的值都大
 */
public class Code0162_FindPeakElement {
  public int findPeakElement(int[] nums) {
    if(nums == null || nums.length == 0) return -1;
    if(nums.length == 1 || nums[0] > nums[1]){
      return 0;
    }
    if(nums[nums.length - 1] > nums[nums.length - 2]){
      return nums.length - 1;
    }

    int left = 0;
    int right = nums.length - 1;
    int mid = 0;
    while(left < right){ //这个循环最后保证是 L 和 R 指向同一个节点，就是最终找到的节点
//      mid = (left + right) >> 1;
      mid = left + (right - left) / 2;
      if(nums[mid] > nums[mid + 1]) {
        //如果[mid] > [mid +1] ，则 l 到 mid 有峰值
        right = mid;
      } else {
        //否则的话就是在mid + 1右侧有峰值
        left = mid + 1;
      }
    }
    return mid;
  }

  public static void main(String[] args) {
    int ans = new Code0162_FindPeakElement().findPeakElement(new int[]{2,4,1,2,7,8,4});
    System.out.println(ans);
  }
}
