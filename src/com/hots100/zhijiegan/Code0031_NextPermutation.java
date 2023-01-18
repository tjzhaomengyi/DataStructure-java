package com.hots100.zhijiegan;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-16 4:59 下午
 * @Description:
 */
public class Code0031_NextPermutation {
  //儿童趣味益智：例子 497531
  // （1）从右往左找到第一个发生降序的位置：例子中是4
  //  (2)在右侧找到刚大于4的数 ：例子是5，交换 597431
  //  (3)把原来右侧部分逆序 ： 513479
  public void nextPermutation(int[] nums){
    int N = nums.length;
    //从右往左第一个发生降序的位置
    int firstless = -1;
    for(int i = N - 2; i >= 0; i--){
      if(nums[i] < nums[i + 1]){
        firstless = i;
        break;
      }
    }
    if(firstless == -1){
      reverse(nums, 0, N - 1);
      return;
    }
    int rightCloseMore = -1;//左侧最接近nums[firstless]的数
    for(int i = N - 1; i > firstless; i--){
      if(nums[i] > nums[firstless]){
        rightCloseMore = i;
        break;
      }
    }
    swap(nums, firstless, rightCloseMore);
    reverse(nums, firstless + 1, N - 1);
  }

  public void swap(int[] nums, int i, int j){
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  public void reverse(int[] nums, int L, int R){
    while(L < R){
      swap(nums, L++, R--);
    }
  }
}
