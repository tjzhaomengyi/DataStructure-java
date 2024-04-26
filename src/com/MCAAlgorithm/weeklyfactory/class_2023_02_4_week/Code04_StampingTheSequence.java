package com.MCAAlgorithm.weeklyfactory.class_2023_02_4_week;

import java.util.ArrayList;
import java.util.Arrays;

// 你想要用小写字母组成一个目标字符串 target。 
// 开始的时候，序列由 target.length 个 '?' 记号组成
// 而你有一个小写字母印章 stamp。
// 在每个回合，你可以将印章放在序列上，并将序列中的每个字母替换为印章上的相应字母
// 你最多可以进行 10 * target.length  个回合
// 举个例子，如果初始序列为 "?????"，而你的印章 stamp 是 "abc"
// 那么在第一回合，你可以得到 "abc??"、"?abc?"、"??abc"
//（请注意，印章必须完全包含在序列的边界内才能盖下去。）
// 如果可以印出序列，那么返回一个数组，该数组由每个回合中被印下的最左边字母的索引组成
// 如果不能印出序列，就返回一个空数组。
// 例如，如果序列是 "ababc"，印章是 "abc"
// 那么我们就可以返回与操作 "?????" -> "abc??" -> "ababc" 相对应的答案 [0, 2]
// 另外，如果可以印出序列，那么需要保证可以在 10 * target.length 个回合内完成
// 任何超过此数字的答案将不被接受
// 测试链接 : https://leetcode.cn/problems/stamping-the-sequence/
public class Code04_StampingTheSequence {

	//思路：找到最后盖成的点，这个是一个拓扑排序的问题，入度为0的值最后盖，这道题挺难
	//stamp：abc，target：ababc，以abc作为入度为0的节点，如果忽略掉2开头的ab就和stamp一样，如果忽略掉
	// abc是拓扑排序的初始节点，最后盖这个章，2、3、4位置开头往下的影响可以忽略了，aba就不需要考虑最后一个a，ab
	// stamp：abca， aabcaca，入度为0的点abca以1开头的，以3开头的caca，需要用3或者4入度
	// abca ->aabc
	// abca ->caca
	public static int[] movesToStamp(String stamp, String target) {
		char[] s = stamp.toCharArray();
		char[] t = target.toCharArray();
		int m = s.length;
		int n = t.length; //目标长度
		int[] inDegrees = new int[n - m + 1]; //准备多少个开头位置，n-m+1越界的那些不用考虑
		// 所有在target里开头的字符串，认为和stamp，每个位置都不一样
		Arrays.fill(inDegrees, m);//所有在target的字符串，认为和stamp每个位置都不一样
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		// 入度为0的开头，进入到queue里
		int[] queue = new int[n - m + 1];
		int l = 0;
		int r = 0;
		for (int i = 0; i <= n - m; i++) {
			// i....撸m个字符
			for (int j = 0; j < m; j++) {
				if (t[i + j] == s[j]) { //target的i+j位置字符和stamp的j位置字符一样
					if (--inDegrees[i] == 0) {//思路：i这个字符的degree--，不需要从对应j位置过来，i对应的入度--，如果为0，添加入度为0的节点
						queue[r++] = i; //拓扑排序的开始点！！！
					}
				} else {
					graph.get(i + j).add(i);//思路：不一样的时候加边，需要从j位置这个边搞出i这个字符，
				}
			}
		}
		//思路：跑拓扑排序
		// visited[17] == true，说明17位置的字符搞定了，不要把重复影响消除掉，如果【15 16 17 18 19】的17搞定了，
		// 那么 【16 17 18 19 20】这个17就不要算了，每次只算一次入度到0即可
		boolean[] visited = new boolean[n];
		int[] path = new int[n - m + 1];
		int size = 0;
		while (l < r) {
			// cur位置开头的字符串，弹出了！入度为0！恭喜啊！！！！
			int cur = queue[l++];
			path[size++] = cur;//弹出
			for (int i = 0; i < m; i++) { //todo：这块太不好理解了cur到m点的那些点消除影响
				if (!visited[cur + i]) {//没有消除过的点
					visited[cur + i] = true;//todo：这块太不好理解了cur到m点的那些点消除影响
					for (int next : graph.get(cur + i)) {//当前的cur + i影响哪些字符，在图里也要消减一下入度，拓扑排序的正常操作
						if (--inDegrees[next] == 0) { //思路：如果入度为0了，把入度为0的点放入队列
							queue[r++] = next;
						}
					}
				}
			}
		}
		//到达了几个开头，如果不够n-m+1是凑不出来的！
		if (size != n - m + 1) {
			return new int[0];
		}
		// 如果收集到了粘贴结果，结果存在path里，但是从思路开始的时候，是一个倒序存储
		for (int i = 0, j = size - 1; i < j; i++, j--) {
			int tmp = path[i];
			path[i] = path[j];
			path[j] = tmp;
		}
		return path;
	}

}
