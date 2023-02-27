package com.point2offerspecial.twelve_sort.quick_sort;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 12:31
 * @Description:
 */
public class QuickSortByNetherLands {
  //太难了~直接记住例子吧：这个partition就是荷兰国旗问题，直接背即可，
  // less区域在L - 1 ，more区域在R + 1都在范围外，L到R是最终荷兰国旗的中间部分，最后返回也是中间部分
  // 中间逻辑三个条件，最终返回灵活掌握，如果是中间部分就是L到R
  //从大到小的partition排序,荷兰国旗，最终more和less卡住的是中间区域
  public  int[] partition(int[] nums, int L, int R, int pivot){
    int less = L - 1;
    int more = R + 1;
    int cur = L;
    //荷兰国旗
    while(cur < more){
      if(nums[cur] < pivot){
        swap(nums, cur++, ++less);
//        swap(nums, cur, --less);
      } else if(nums[cur] > pivot) {
        swap(nums, cur, --more);
      } else{
        cur++;
      }
    }
    //返回等于区域的左边界和等于区域的右边界
    return new int[]{less + 1, more - 1};//技巧：这部分看题目如何要求，默认是大于区域和小于区域的起始
  }

  private void swap(int[] arr, int i, int j){
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
