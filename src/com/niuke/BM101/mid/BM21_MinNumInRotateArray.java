package com.niuke.BM101.mid;

/**
 * 找到有顺序的旋转数组的最小值
 */
public class BM21_MinNumInRotateArray {
    public int minNumberInRotateArray(int[] nums){
        if(nums.length == 0) return -1;
        int left = 0;
        int right = nums.length;
        //注意：这里和找到某个值是否存在不一样，这里缩小范围就可以了，不需要对L=R时的值进行判断是否等于某个target
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[right]){
                left = mid + 1; //最小值肯定在mid右侧，所以mid不要了
            } else if(nums[mid] < nums[right]){
                //最小值在mid的左侧，范围包括mid，因为mid此时可能就是旋转后的最小值，要从mid压缩范围
                right = mid;
            } else {
                //处理数组中有相等的元素，因为这里right是一个被比较的点，即使这个点不存在，这个数组的“中间”mid还有一个mid数能够完成right这个数的意志
                right--;
            }
        }
        return nums[left];//最终L 和 R落在一个点上
    }
}
