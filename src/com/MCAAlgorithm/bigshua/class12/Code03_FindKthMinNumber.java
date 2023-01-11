package com.MCAAlgorithm.bigshua.class12;

// 本题测试链接 : https://leetcode.com/problems/median-of-two-sorted-arrays/
public class Code03_FindKthMinNumber {
	//刷题班12-79节
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int size = nums1.length + nums2.length;
		boolean even = (size & 1) == 0;
		if (nums1.length != 0 && nums2.length != 0) {
			if (even) {
				return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
			} else {
				return findKthNum(nums1, nums2, size / 2 + 1);
			}
		} else if (nums1.length != 0) {
			if (even) {
				return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
			} else {
				return nums1[size / 2];
			}
		} else if (nums2.length != 0) {
			if (even) {
				return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
			} else {
				return nums2[size / 2];
			}
		} else {
			return 0;
		}
	}

	// 进阶问题 : 在两个都有序的数组中，找整体第K小的数
	// 可以做到O(log(Min(M,N)))
	public static int findKthNum(int[] arr1, int[] arr2, int kth) {
		int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
		int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
		int l = longs.length;
		int s = shorts.length;
		if (kth <= s) { // 1)
			return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
		}
		if (kth > l) { // 3)
			if (shorts[kth - l - 1] >= longs[l - 1]) {
				return shorts[kth - l - 1];
			}
			if (longs[kth - s - 1] >= shorts[s - 1]) {
				return longs[kth - s - 1];
			}
			return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
		}
		// 2)  s < k <= l
		if (longs[kth - s - 1] >= shorts[s - 1]) {
			return longs[kth - s - 1];
		}
		return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
	}

	
	

	// LC-0004 寻找两个等长数组中位数问题
	// 思路：A[s1...e1]，s1到e1有序
	//  B[s2...e2]，s2到e2有序
	//  上面两段一定等长！!!【关键条件】
	//  返回整体的，上中位数！8（4） 10（5） 12（6）
	public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
		int mid1 = 0;
		int mid2 = 0;
		while (s1 < e1) {
			//太难了~直接记住例子吧：在纸上的推导
			// mid1 = s1 + (e1 - s1) >> 1
			mid1 = (s1 + e1) / 2; //
			mid2 = (s2 + e2) / 2;
			if (A[mid1] == B[mid2]) {
				return A[mid1];//数学结论：只要两个中点相等直接返回
			}
			// 两个中点一定不等！
			if (((e1 - s1 + 1) & 1) == 1) { // 奇数长度
				if (A[mid1] > B[mid2]) {
					if (B[mid2] >= A[mid1 - 1]) {
						return B[mid2];//太难了~直接记住例子吧：纸上推导，如果B[mid] 大于了 A[mid-1] 那么B[mid]就是最终的中位数
					}
					//太难了~直接记住例子吧：纸上推导，B[mid]没有大于A[mid - 1]，把B[mid]删掉，剩下部分继续迭代
					e1 = mid1 - 1;
					s2 = mid2 + 1;
				} else { // A[mid1] < B[mid2]
					if (A[mid1] >= B[mid2 - 1]) {
						return A[mid1];
					}
					e2 = mid2 - 1;
					s1 = mid1 + 1;
				}
			} else { // 偶数长度，直接进入二分
				if (A[mid1] > B[mid2]) {
					e1 = mid1;
					s2 = mid2 + 1;
				} else {
					e2 = mid2;
					s1 = mid1 + 1;
				}
			}
		}
		return Math.min(A[s1], B[s2]);//此时A 和 B中都只有一个数了，取最小即可
	}

}
