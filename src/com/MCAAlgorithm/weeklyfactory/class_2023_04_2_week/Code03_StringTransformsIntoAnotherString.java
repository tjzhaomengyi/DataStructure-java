package com.MCAAlgorithm.weeklyfactory.class_2023_04_2_week;

import java.util.Arrays;

// 来自谷歌、亚马逊、微软、蔚来、腾讯、字节跳动、Uber
// 给出两个长度相同的字符串 str1 和 str2
// 请你帮忙判断字符串 str1 能不能在 零次 或 多次 转化 后变成字符串 str2
// 每一次转化时，你可以将 str1 中出现的 所有 相同字母变成其他 任何 小写英文字母
// 只有在字符串 str1 能够通过上述方式顺利转化为字符串 str2 时才能返回 true 。​​
// 测试链接 : https://leetcode.cn/problems/string-transforms-into-another-string/
public class Code03_StringTransformsIntoAnotherString {
	// abaab 不能变成 kckkt，这个就不行b没法变成c和t两种
	//又是一道类似连连看的题目
	//
	public static boolean canConvert(String str1, String str2) {
		if (str1.equals(str2)) {
			return true;
		}
		int[] map = new int[26];
		int kinds = 0;
		for (int i = 0; i < str2.length(); i++) {
			if (map[str2.charAt(i) - 'a']++ == 0) {
				kinds++;
			}
		}
		if (kinds == 26) { //目标有26种没有可以交换的，作为临时变量的空间，如果是例子
			return false;
		}
		Arrays.fill(map, -1);//复用map，表示当前字符上一个位置在哪
		for (int i = 0; i < str1.length(); i++) {
			int cur = str1.charAt(i) - 'a';
			if (map[cur] != -1 && str2.charAt(map[cur]) != str2.charAt(i)) {//如果str1和str2对应下标位置不一样的化，变不了
				return false;
			}
			map[cur] = i;
		}
		return true;
	}

}
