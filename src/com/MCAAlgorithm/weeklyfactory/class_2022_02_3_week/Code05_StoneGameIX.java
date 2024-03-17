package com.MCAAlgorithm.weeklyfactory.class_2022_02_3_week;

// 测试链接 : https://leetcode.com/problems/stone-game-ix/
public class Code05_StoneGameIX {

	//分成%3=1和%3=2的1类数和2类数，如果在没有0的情况下，A选了1类数，A后续只能选择1类数才能步数，B要使劲抢A的1类数
	//如果1类数和2类数一样，A选了1类数，A
	//此时0加入进来，并且0的个数是奇数，两者回互换1类和2类数
	public static boolean stoneGameIX(int[] stones) {
		int[] counts = new int[3];
		for (int num : stones) {
			counts[num % 3]++;
		}
		return counts[0] % 2 == 0 
				? counts[1] != 0 && counts[2] != 0 
				: Math.abs(counts[1] - counts[2]) > 2;
	}

}
