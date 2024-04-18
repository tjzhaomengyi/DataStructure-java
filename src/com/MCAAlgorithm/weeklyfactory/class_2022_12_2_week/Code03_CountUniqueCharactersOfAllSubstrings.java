package com.MCAAlgorithm.weeklyfactory.class_2022_12_2_week;

import java.util.ArrayList;
import java.util.HashMap;

// 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，
// 并返回唯一字符的个数。
// 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，
// 因为它们只出现一次，所以 countUniqueChars(s) = 5 。
// 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，
// 其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
// 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串
//（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
// 测试链接 : https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string/
public class Code03_CountUniqueCharactersOfAllSubstrings {

	//CABCABCCA ，单独以A、B、C分别开头有多少种，但是不能有重复的，到重复的位置卡住
	// 关于A的位置：（1）第一个A不要到4，有多少个子串能够"贯穿"1位置的1，这些都会收获A贡献的唯一值，得到6个
	// （2）"贯穿"4位置A的子串有多少个统计一下，然后获得泛化公式：
	// 【数学结论】A（17）。。。A（24）。。。A（30），从18到A29，贯穿24的A有（30-24）个，24的A前面有几个位置（24-17），
	//   总收益（30-24）*（24-17）【就是开头的总收益 * 结尾的总收益】，
	//   以A（前） 。。。 A（当）。。。A（后）为例，具体泛化公式就是（当前位置下标 - 前一个同样字母的下标） * （后一个同样字母的下标 - 当前位置下标）
	//   然后还得在前面补一个-1位置，在结尾补一个n位置
	public static int uniqueLetterString(String s) {
		// key : 某一种字符
		// value : 出现这种字符依次的位置
		HashMap<Character, ArrayList<Integer>> indies = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!indies.containsKey(c)) {
				indies.put(c, new ArrayList<>());
				indies.get(c).add(-1); // 补一个-1
			}
			indies.get(c).add(i);
		}
		int res = 0;
		for (HashMap.Entry<Character, ArrayList<Integer>> entry : indies.entrySet()) {
			ArrayList<Integer> arr = entry.getValue();
			arr.add(s.length()); //补一个终止的越界位置
			for (int i = 1; i < arr.size() - 1; i++) {
				res += (arr.get(i) - arr.get(i - 1)) * (arr.get(i + 1) - arr.get(i)); //（当-前） * （后-当）
			}
		}
		return res;
	}

}
