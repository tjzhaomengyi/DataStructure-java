package com.MCAAlgorithm.weeklyfactory.class_2022_08_1_week;

// 找到数组中的水王数
// 本题来自，大厂刷题班，23节
// 为了讲述下一个题，才重新讲述这个题
// 比较简单
public class Code02_WaterKing {

	public static int waterKing(int[] arr) {
		int cand = 0;
		int hp = 0;
		//下面就是一次删除两个数的全过程
		//血量只要是0就表示当前没有候选
		for (int i = 0; i < arr.length; i++) {
			if (hp == 0) { //没有候选，直接把当前的数立为候选
				cand = arr[i];
				hp = 1;
			} else if (arr[i] == cand) { //如果是候选，血量+1
				hp++;
			} else { //如果不是候选，血量-1
				hp--;
			}
		}
		if (hp == 0) { //血量为0表示当前没有候选
			return -1;
		}
		hp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == cand) {
				hp++;
			}
		}
		return hp > arr.length / 2 ? cand : -1;
	}

}
