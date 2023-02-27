package com.hots100.sorts;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-29 12:55 下午
 * @Description:
 */
public class Code0215_KthMinNumber {

  //方法0：思路：利用小根堆，时间复杂度O(k*logN)
  //方法1：思路：使用快排的荷兰国旗方法，只要判断。时间复杂度O(N)
  public int findKthLargest(int[] nums, int k) {
    //求第k大就是求第len-k小
    return process(nums, 0, nums.length - 1, nums.length - k);

  }

  public int process(int[] arr, int L, int R, int k){
    if(L == R){
      return arr[L];
    }
    int pivot = 0;
    pivot = arr[L + (int)(Math.random() * (R-L+1))];
    //技巧：先算出一个中间区间，看是否符合k，如果不符合在这个区间的一侧进行递归
    int[] range = partition(arr, L, R, pivot);//等于区域的左右边界
    if(k < range[0]){
      return process(arr, L, range[0] - 1, k);
    } else if(k > range[1]){
      return process(arr,range[1] + 1, R, k);
    } else if(k >= range[0] && k <= range[1]){
      return arr[k]; //这个时候k即不大于大的，也不小于小的，那就返回这个数，反正在这中间的都是相同的数
    }
    return -1;
  }

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
    return new int[]{less + 1, more - 1};
  }

  private void swap(int[] arr, int i, int j){
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
  //方法2：思路：使用BFPRT算法，装逼用。时间复杂度O(N)

  public static void main(String[] args) {
    int[] tmp = new int[]{3,2,3,1,2,4,5,5,6};//{5,4,3,2,1}
    int res =new Code0215_KthMinNumber().findKthLargest(tmp, 2);
    System.out.println(res);
    for(int i : tmp){
      System.out.print(i);
    }

  }
}
