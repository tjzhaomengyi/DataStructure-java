package com.MCAAlgorithm.weeklyfactory.class_2023_05_4_week;

// 来自美团
// 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，
// 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，
// 返回 true；否则，返回 false 。
// 测试链接 : https://leetcode.cn/problems/validate-stack-sequences/
public class Code03_ValidateStackSequences {

	//1234567 4352761
	//数学结论：如果没有和要弹出一样的，就一直押入栈中，只要栈定和target一样就弹出，不一样就压栈，能模拟完true，不能模拟完false
	// 如何不申请一个栈完成这个操作，就用两个数组，复用空间来做栈。利用输入数组的空间做栈
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		int n = pushed.length;
		int size = 0;//输入空间的前size个做栈！
		//技巧：用输入空间自己的域来做栈！！！
		for (int i = 0, j = 0; i < n; i++) {
			// i : 入栈数组，哪个位置的数要进栈
			// j : 出栈数组，对比的位置
			pushed[size++] = pushed[i];//只要不一样就进栈！pushed从下到上1234，栈顶在size-1的位置size-1=3，栈顶一样了
			while (size > 0 && j < n && pushed[size - 1] == popped[j]) { //size-1我退一个，你进一个，进栈size减少一个；poped往后走
				size--;
				j++;
			}
		}
		return size == 0;
	}

}
