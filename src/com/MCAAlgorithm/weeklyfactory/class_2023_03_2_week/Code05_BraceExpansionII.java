package com.MCAAlgorithm.weeklyfactory.class_2023_03_2_week;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

// 如果你熟悉 Shell 编程，那么一定了解过花括号展开，它可以用来生成任意字符串。
// 花括号展开的表达式可以看作一个由 花括号、逗号 和 小写英文字母 组成的字符串
// 定义下面几条语法规则：
// 如果只给出单一的元素 x，那么表达式表示的字符串就只有 "x"。R(x) = {x}
// 例如，表达式 "a" 表示字符串 "a"。
// 而表达式 "w" 就表示字符串 "w"。
// 当两个或多个表达式并列，以逗号分隔，我们取这些表达式中元素的并集
// R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
// 例如，表达式 "{a,b,c}" 表示字符串 "a","b","c"。
// 而表达式 "{{a,b},{b,c}}" 也可以表示字符串 "a","b","c"。
// 要是两个或多个表达式相接，中间没有隔开时，
// 我们从这些表达式中各取一个元素依次连接形成字符串
// R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}
// 例如，表达式 "{a,b}{c,d}" 表示字符串 "ac","ad","bc","bd"。
// 表达式之间允许嵌套，单一元素与表达式的连接也是允许的。
// 例如，表达式 "a{b,c,d}" 表示字符串 "ab","ac","ad"​​​​​​。
// 例如，表达式 "a{b,c}{d,e}f{g,h}" 
// 可以表示字符串 : 
// "abdfg", "abdfh", "abefg", "abefh",
// "acdfg", "acdfh", "acefg", "acefh"。
// 给出表示基于给定语法规则的表达式 expression
// 返回它所表示的所有字符串组成的有序列表。
// 测试链接 : https://leetcode.cn/problems/brace-expansion-ii/
public class Code05_BraceExpansionII {

	//思路：b{c{e,f}}s
	// f(0) | {b} -> f(2) | {c} -> f(4) | {e,f} -> } -> } -> f(9)
	//                                          <-返回<-返回
	public static List<String> braceExpansionII(String expression) {
		return new ArrayList<>(process(expression.toCharArray(), 0).ans);
	}

	public static class Info {
		public TreeSet<String> ans;//按照字典序放结果
		public int end;//遍历到的位置

		public Info(TreeSet<String> a, int e) {
			ans = a;
			end = e;
		}
	}

	// exp[start..........]  遇到我的} 或者exp终止位置，停！
	public static Info process(char[] exp, int start) {
		// 最终的结果，返回
		TreeSet<String> ans = new TreeSet<>(); //每步都要往上返回treeset结果
		// 集合A X 集合B X 集合C  ,  遇到, parts清空
		// ................        遇到, parts清空
		// 每一回的结果，加入到ans
		// 例子：a{b,c}e,f{t,k}{s,t},这里会有并列关系，每部分扔到parts中，每部分处理结束后清空parts中的内容
		List<TreeSet<String>> parts = new ArrayList<>();//思路：这道题重要的数据结构之一，存放收尾parts，就是各种带括号的内容，这里面鼓捣笛卡尔积，得到结果
		// 当前收集字符，这个就类似表达式求结果得到的结果
		StringBuilder builder = new StringBuilder();//思路：这道题重要的数据结构之二，存放每部分遍历出来的纯字符串内容，最后收集到parts中，好得到笛卡尔积的结果
		while (start != exp.length && exp[start] != '}') {//start往下撸撸撸，不能是右侧括号
			if (exp[start] == '{') {//遇到了左中括号
				//builder加入到字符串中
				addStringToParts(builder, parts);
				Info next = process(exp, start + 1);//start碰到了{，让子过程往下递归获得结果，返回结果
				parts.add(next.ans);//parts把next结果加进来
				start = next.end + 1;//从返回结束的位置的下一位继续！
			} else if (exp[start] == ',') {//一旦遇到了,
				//a{f,c}atsk,
				//parts={a,{f,c},{atsk}}做笛卡尔积，加入ans 清空parts
				// a{b,c}{e,f},
				// parts={a{bc},{ef}},直接笛卡尔积，加入ans ，清空parts
				addStringToParts(builder, parts);//把builder放入parts中
				addPartsToSet(ans, parts);//parts里面的内容生成笛卡尔积放入ans
				start++;//游标继续
				parts.clear(); //清空parts
			} else { // 遇到英文字母
				builder.append(exp[start]);//往builder里面加
				start++;
			}
		}
		//start一旦遇到了右括号或者结尾，要做整理
		// a{b,c}{e,f},ct{e,s}}，假设现在到了最后的右括号，
		//此时，ans中已经加入了逗号左边的结果，当前parts中内容是{{e,s}},builder=ct,把右侧一股parts处理完，清空加入
		addStringToParts(builder, parts);//把builder放入parts，
		addPartsToSet(ans, parts); //把最后的parts内部做笛卡尔积放入ans中
		return new Info(ans, start);
	}

	//把builder加入字符串中
	public static void addStringToParts(StringBuilder builder, List<TreeSet<String>> parts) {
		if (builder.length() != 0) { //如果builder不为空
			parts.add(new TreeSet<>()); //parts加一个新集合
			parts.get(parts.size() - 1).add(builder.toString());//末尾对应的parts只有这个字符串
			builder.delete(0, builder.length());//清空builder
		}
	}

	public static void addPartsToSet(TreeSet<String> ans, List<TreeSet<String>> parts) {
		process(parts, 0, "", ans);
	}

	public static void process(List<TreeSet<String>> list, int i, String path, TreeSet<String> ans) {
		if (i == list.size()) {
			if (!path.equals("")) {
				ans.add(path);
			}
		} else {
			for (String cur : list.get(i)) {
				process(list, i + 1, path + cur, ans);
			}
		}
	}

}
