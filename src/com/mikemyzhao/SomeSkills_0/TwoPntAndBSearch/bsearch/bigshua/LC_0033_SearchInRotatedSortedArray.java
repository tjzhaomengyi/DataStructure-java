package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch.bsearch.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 17:48
 * @Description:
 */
public class LC_0033_SearchInRotatedSortedArray {
  //技巧:在旋转字符串中找target，[L,M,R]，[L] = a, [M] = b,[R] = C.
  //  (1)如果a=b=c，但是不等于num说明没有找到 ,让L++继续找
  //  (2)如果a=b=c=num,说明找到了
  public int search(int[] nums, int target) {
    int L = 0;
    int R = nums.length - 1;
    int M = 0;
    while(L <= R) {
      M = L + ((R - L) >> 1);
      if (nums[M] == target) {
        return M;
      }

      //技巧:情况1，二分一直找不到target，但是a=b=c，往下推L，说明是在L上折过来的，所以就往右推
      if (nums[L] == nums[M] && nums[M] == nums[R]) {
        while (L != M && nums[L] == nums[M]) {
          L++;
        }
        if (L == M) {//技巧:此时发现把L推到M位置，L变成M+1.
          //数学结论:这里不是像普通二分直接过来，而是一点点把L推导M位置
          L = M + 1;//一直没找到就在M+1位置开始继续二分
          continue;
        }
      }

      //技巧:情况如果a，b，c不都一样的情况选择二分逻辑
      //技巧:情况1:[M] = [R]
      if (nums[L] != nums[M]) {//此时a不等于b，但是a可能等于c
        if (nums[M] > nums[L]) {
          //数学结论:此时L到M一定有序
          if (target >= nums[L] && target < nums[M]) {
            R = M - 1;
          } else {
            L = M + 1;
          }
        } else {//[M] < [L],数学结论:说明L到M上有断点,但是M到R上是单调递增
          if (target > nums[M] && target <= nums[R]) {
            L = M + 1; //技巧:赶紧放在M+1到R上找
          } else {
            R = M - 1;//
          }
        }
      } else {//abc都不相等
        if (nums[M] < nums[R]) {
          if (target > nums[M] && target <= nums[R]) {
            L = M + 1;
          } else {
            R = M - 1;
          }
        } else {
          if (target >= nums[L] && target < nums[M]) {
            R = M - 1;
          } else {
            L = M + 1;
          }
        }
      }
    }
    return -1;
  }



  //思路：如果中间的数小于最右边的数，则右半段是有序的，
  // 若中间数大于最右边数，则左半段是有序的，
  // 我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了
  public int bestBinSearch(int[] nums, int target) {
    int len = nums.length;
    int left = 0, right = len-1;
    while(left <= right){
      int mid = (left + right) / 2;
      if(nums[mid] == target)
        return mid;
      else if(nums[mid] < nums[right]){//mid右侧完美
        if(nums[mid] < target && target <= nums[right]) //找的数在mid右侧完美
          left = mid+1;
        else
          right = mid-1;
      }
      else{//mid左侧完美
        if(nums[left] <= target && target < nums[mid])//找的数在左侧完美
          right = mid-1;
        else
          left = mid+1;
      }
    }
    return -1;
  }
}
