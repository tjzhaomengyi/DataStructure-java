package com.hotinterview.Nanti;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-07 2:40 下午
 * @Description:
 */
public class ZuinantiCode004_SameLengthArrMidNum {
  //思路：参考Code03_FindKthMinNumber || 这道题一定要看白纸的推导【1、求上中位数】【2、利用"求上中位数"的模型，求第k小的数】
  // 【关键条件】s1到e1 与 s2到e2 必须等长！！！！
  public int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2){
    int mid1 = 0;
    int mid2 = 0;
    while(s1 < e1){
      mid1 = (s1 + e1) / 2;
      mid2 = (s2 + e2) / 2;
      if(A[mid1] == B[mid2]){
        return A[mid1];
      }
      //两个中点不相等
      if(((e1 - s1 + 1) & 1) == 1){//讨论基数长度
        if(A[mid1] > B[mid2]){
          //手动比一下,A[mid1]肯定不是中位数，如果总长为5，取A的2个，B的3个
          if(B[mid2] >= A[mid1 - 1]){
            return B[mid2];
          }
          //如果不满足上面情况，把B[mid2]扔掉
          e1 = mid1 - 1;
          s2 = mid2 + 1;
        } else {
          //和上面对称写法
          if(A[mid1] >= B[mid2 - 1]){
            return A[mid1];
          }
          e2 = mid2 - 1;
          s1 = mid1 + 1;
        }
      } else {
        //偶数长度直接进入二分
        if(A[mid1] > B[mid2]){
          e1 = mid1;
          s2 = mid2 + 1;
        } else {
          e2 = mid2;
          s1 = mid1 + 1;
        }
      }
    }
    System.out.println(A[s1] + " " + B[s2]);
    return Math.min(A[s1], B[s2]);//最后只剩两个数
//    return ((e1 - s1 + 1) & 1) == 1 ? Math.min(A[s1],B[s2]):(A[s1] * 1.0 + B[s2] * 1.0) / 2;
  }

  public int findKthMin(int[] nums1, int[] nums2,int k){
    //这里已经保证nums1是长的
    int l = nums1.length;
    int s = nums2.length;
    //太难了~直接记住例子吧：见稿纸推导，这个可以通过稿纸的例子直接换算下标：当前是17 + 10 / 2 = 第22个，我们要求第23
    if(k <= s){
      return getUpMedian(nums1,0, k - 1, nums2, 0, k - 1);
    } else if(k > l & k <= l + s){
      //判断长的截取部分的第一个是否大于短的截取部分的最后一个，如果大于直接返回结果
      if(nums1[k - s - 1] >= nums2[s - 1]){
        return nums1[k - s - 1];
      } else if(nums2[k - l - 1] >= nums1[l - 1]){ //判断短的第一个是否大于长的截取部分的最后一个，如果大于也是直接返回
        return nums2[k - l - 1];
      } else {//长短截取的两个都不能满足上面两个条件，把这两个放在小的里面，求19 + 8 / 2 = 第23个 是我们要求的
        return getUpMedian(nums1, k - s, l - 1, nums2, k - l, s - 1);
      }
    } else { //s < k <= l
      //长的肯定比短的多一个出来，手动对比一下，其实就是和前面第二种情况对应上
      if(nums1[k - s - 1] >= nums2[s - 1]){
        return nums1[k - s - 1];
      } else {
        return getUpMedian(nums1, k - s , k - 1, nums2, 0, s - 1);//注意结束位
      }
    }
  }

  //这个问题要利用不等长的数组求第K大的数
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    if(n == 0){
      return (m & 1) == 1 ? nums2[m / 2] * 1.0 : (nums2[(m - 1) / 2] * 1.0 + nums2[(m - 1) / 2 + 1] * 1.0) / 2;
    }
    if(m == 0){
      return (n & 1) == 1 ? nums1[n / 2] * 1.0 : (nums1[(n - 1) / 2] * 1.0 + nums1[(n - 1)  / 2 + 1] * 1.0) / 2;
    }
    int[] long_nums = n > m ? nums1 : nums2;
    int[] short_nums = long_nums == nums1 ? nums2 : nums1;
    int mid = (m + n) >> 1;
    if(((m + n) & 1) == 1){
      return findKthMin(long_nums, short_nums, mid + 1); //长5找第3小
    } else {
      int up = findKthMin(long_nums, short_nums, mid);
      int follow = findKthMin(long_nums, short_nums, mid + 1);
      return (up * 1.0 + follow * 1.0) / 2;
    }
  }




  public static void main(String[] args) {
    //System.out.println((2*1.0 + 3*1.0) / 2 + " " +1/2);
//    int[] nums1 = new int[]{2,2,4,4};
//    int[] nums2 = new int[]{2,2,4,4};
//    int[] nums1 = new int[]{1,3};
//    int[] nums2 = new int[]{2};
    int[] nums1 = new int[]{2};
    int[] nums2 = new int[]{1,3,4};

    int[] nums3 = new int[]{3,4,5};
    int[] nums4 = new int[]{1,2,6};


    double ans = new ZuinantiCode004_SameLengthArrMidNum().findMedianSortedArrays(nums3,nums4);
    System.out.println(ans);
  }
}
