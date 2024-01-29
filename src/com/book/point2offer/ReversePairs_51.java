package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 10:52
 * @Description:逆对序的个数
 * @Solve:归并排序就是逆对序的一种使用，需要在合并的过程中统计次数，得到逆序对
 */
public class ReversePairs_51 {
  private int cnt;
  public int reversePairs(int[] nums) {
    this.cnt=0;
    merge(nums,0,nums.length-1);
    return cnt;
  }
  public void merge(int[] nums,int left,int right){
    if(left>=right) return;//注意：归并排序这一定是大于等于！
    int mid = left+(right-left)/2;
    merge(nums,left,mid);
    merge(nums,mid+1,right);
    mergeSort(nums,left,mid,right);
  }
  public void mergeSort(int[] nums,int left,int mid,int right){
    int[] res = new int[right-left+1];
    int i=0,t1=left,t2=mid+1;
    while(t1<=mid && t2<=right){
      if(nums[t1]<=nums[t2]){
        res[i++]=nums[t1++];
      }else{
        //左边数组中的从i到m的所有数都是大于tmp[j]的(因为m左右的数组都是已经排好序的，第15行代码的功劳)
        cnt += (mid-t1+1);//此时这个nums[t2]和i之后的元素全是逆序对！
        res[i++]=nums[t2++];
      }
    }
    while(t1<=mid){
      res[i++]=nums[t1++];
    }
    while(t2<=right){
      res[i++]=nums[t2++];
    }
    //注意这里，用t1指向原数组的left起始点，t2指向复制的节点即可！因为整体是一个递归的过程，所以这里不能写实
    for(t1=left,t2=0;t1<=right;t1++,t2++){
      nums[t1]=res[t2];
    }
  }
}
