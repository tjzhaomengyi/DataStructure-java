package com.MCAAlgorithm.bigshua.class45;

import java.util.HashSet;

public class Problem_0291_WordPatternII {

	//是好题
	public static boolean wordPatternMatch(String pattern, String str) {
		return match(str, pattern, 0, 0, new String[26], new HashSet<>());
	}

	// 题目有限制，str和pattern其中的字符，一定是a~z小写
	// p[a] -> "abc"
	// p[b] -> "fbf"
	// 需要指代的表最多26长度
	// String[] map -> new String[26]
	// p[a] -> "abc"   map[0] -> "abc"
	// p[b] -> "fbf"   map[1] -> "fbf";
	// p[z] -> "kfk"   map[25] -> "kfk"
	// HashSet<String> set -> map中指代了哪些字符串
	// 思路:str[si.......]  是不是符合  p[pi......]？符合返回true，不符合返回false。假设就是si和pi前面都符合条件
	// 之前的决定！由map和set，告诉我！不能冲突！
	// 技巧:两个匹配的递归，dfs递归清理现场 + 剪枝【重要剪枝】，这道题好想，但是代码比较恶心
	public static boolean match(String s, String p, int si, int pi, String[] map, HashSet<String> set) {
		if (pi == p.length() && si == s.length()) {
			return true;
		}
		// str和pattern，并没有都结束！只有一个结束，配不完了
		if (pi == p.length() || si == s.length()) {
			return false;
		}
		//  str和pattern，都没结束！

		char ch = p.charAt(pi);
		String cur = map[ch - 'a'];//pattern字符指代的字符串
		if (cur != null) { // 当前p[pi]已经指定过了！
			return si + cur.length() <= s.length() // 不能越界！
					&& cur.equals(s.substring(si, si + cur.length())) // 截取出来的和当前的cur一样
					&& match(s, p, si + cur.length(), pi + 1, map, set); // 往下继续匹配
		}
		// p[pi]没指定！
		int end = s.length();
		// 【优化点】剪枝！重要的剪枝！思路:就是后面有能够匹配上的把字符留够！！！
		// 思路:abc(a)fbf(b)kkk.....，匹配字符abcab，最后的ab一定要留够6个字符
		for (int i = p.length() - 1; i > pi; i--) {
			end -= map[p.charAt(i) - 'a'] == null ? 1 : map[p.charAt(i) - 'a'].length();
		}

		for (int i = si; i < end; i++) {
			//  从si出发的所有前缀串，全试
			cur = s.substring(si, i + 1);
			// 但是，只有这个前缀串，之前没占过别的坑！才能去尝试
			if (!set.contains(cur)) {
				set.add(cur);
				map[ch - 'a'] = cur;
				if (match(s, p, i + 1, pi + 1, map, set)) {
					return true;
				}
				//技巧:DFS辅助集合清空
				map[ch - 'a'] = null;//技巧:dfs清理掉现场，去搞下一个前缀串
				set.remove(cur);//技巧:dfs清理掉现场，去搞下一个前缀串
			}
		}
		return false;
	}

}
