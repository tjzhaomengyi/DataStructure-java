package com.MCAAlgorithm.weeklyfactory.class_2022_06_3_week;

// arr是一个可能包含重复元素的整数数组，我们将这个数组分割成几个“块”，
// 并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
// 我们最多能将数组分成多少块？
// 示例 1:
// 输入: arr = [5,4,3,2,1]
// 输出: 1
// 解释:
// 将数组分成2块或者更多块，都无法得到所需的结果。
// 例如，分成 [5, 4], [3, 2, 1] 的结果是 [4, 5, 1, 2, 3]，这不是有序的数组。 
// 示例 2:
// 输入: arr = [2,1,3,4,4]
// 输出: 4
// 解释:
// 我们可以把它分成两块，例如 [2, 1], [3, 4, 4]。
// 然而，分成 [2, 1], [3], [4], [4] 可以得到最多的块数。 
// 测试链接 : https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
public class Code01_MaxChunksToMakeSortedII {

	public int maxChunksToSorted(int[] arr) {
		//5 4 3 6 7 5 8 8
		// 5这里不能单独切，因为后面有3，切了之后3永远不能按照正常排序放在5的前面了
		// 来到4，还是不行
		// 来到3，后面的最小值是5，断连后不影响。
		//结论如果想在某个数上切的话，一定要看后面是否有比自己还小的数，如果有这个数上就不能切
		int n = arr.length;
		int[] mins = new int[n];//每个点右边的最小值提前记录一下
		// i ~ 最后位置上，最小值！
		// 5  | 6...
		// 17 | 18...
		mins[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			mins[i] = Math.min(arr[i], mins[i + 1]);
		}
		int ans = 1;
		int max = arr[0];
		for (int i = 1; i < n; i++) {
			if (max <= mins[i]) {//这个点可以切
				ans++;
			}
			max = Math.max(max, arr[i]);
		}
		return ans;
	}

}
