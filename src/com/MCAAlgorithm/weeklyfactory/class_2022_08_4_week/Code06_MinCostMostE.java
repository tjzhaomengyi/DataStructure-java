package com.MCAAlgorithm.weeklyfactory.class_2022_08_4_week;

// 来自网易
// 小红拿到了一个仅由r、e、d组成的字符串
// 她定义一个字符e为"好e" : 当且仅当这个e字符和r、d相邻
// 例如"reeder"只有一个"好e"，前两个e都不是"好e"，只有第三个e是"好e"
// 小红每次可以将任意字符修改为任意字符，即三种字符可以相互修改
// 她希望"好e"的数量尽可能多
// 小红想知道，自己最少要修改多少次
// 输入一个只有r、e、d三种字符的字符串
// 长度 <= 2 * 10^5
// 输出最小修改次数
public class Code06_MinCostMostE {

	//两个维度的目标：好e足够多、非e字母变的次数足够少
	//1、可以贪心，如果是奇数，奇数为全改成e，剩下就看dr/rd哪个方法省；如果是偶数长度就复杂
	//2、递归方法 ：f(i,prepre,pre)来到i位置字符，prepre是i-2做的决定，pre是i-1做的决定；例如re？，可以把？变成d/e/r都可以，但是前面两个确定
	//【i-1】==e，可以成全，返回如果能成全，从i开始形成最多的好e，数量是多少，这个最多的好e的最小代价
	/**课上讲解
	public static class Info {
		public int mostGoodE;
		public int minCost;

		public Info(int good, int cost) {
			mostGoodE = good;
			minCost = cost;
		}

	}

	// i-2做的决定 -> prepre
	// i-1做的决定 -> pre
	// i...可以去成全 i-1 pre
	// 也可以不成全 i-1 的字符
	// i 可以变成-> r e d 三种变化随意
	// 返回：i-1 被成全yes or 不被成全no + i.... 最多的好e，数量是多少
	// 最多的好e, 最小的代价
	// i 2 * 10^5
	// prepre r e d
	// pre r e d
	// 9 * 2 * 10^5
	// 10^6
	public static Info zuo(char[] s, int i, char prepre, char pre) {
		if (i == s.length) { //越界了无法成全
			return new Info(0, 0);
		}

		// 可能性1 i-1 [i] -> r，i位置的字符变成r，这样就有可能成全i-1位置变成好e，这样的话cur1Value=1，否则cur1Value=0
		int cur1Value = prepre == 'd' && pre == 'e' ? 1 : 0; //前面是可以成全，后面是不能成全
		int cur1Cost = s[i] == 'r' ? 0 : 1; //变的这下的代价
		Info info1 = zuo(s, i + 1, pre, 'r'); //后续的信息，往后递推
		int p1Value = cur1Value + info1.mostGoodE;
		int p1Cost = cur1Cost + info1.minCost;

		// 可能性2 i-1 [i] -> e ，把i位置的字符变成e
		int cur2Value = 0; //这个位置i调整不会给i-1的位置获得不到收益
		int cur2Cost = s[i] == 'e' ? 0 : 1;
		Info info2 = zuo(s, i + 1, pre, 'e');
		int p2Value = cur2Value + info2.mostGoodE;
		int p2Cost = cur2Cost + info2.minCost;

		// 可能性3 i-1 [i] -> d，同可能性1的分析
		int cur3Value = prepre == 'r' && pre == 'e' ? 1 : 0;
		int cur3Cost = s[i] == 'd' ? 0 : 1;
		Info info3 = zuo(s, i + 1, pre, 'd');
		int p3Value = cur3Value + info3.mostGoodE;
		int p3Cost = cur3Cost + info3.minCost;

		int mostE = 0;
		int minCost = Integer.MAX_VALUE;

		//分别验证三个可能性对应的结果
		if (mostE < p1Value) { //有好e
			mostE = p1Value;
			minCost = p1Cost;
		} else if (mostE == p1Value) {
			minCost = Math.min(minCost, p1Cost); //收益mostE是一样的，替换为最小代价Cost
		}

		if (mostE < p2Value) {
			mostE = p2Value;
			minCost = p2Cost;
		} else if (mostE == p2Value) {
			minCost = Math.min(minCost, p2Cost);
		}

		if (mostE < p3Value) {
			mostE = p3Value;
			minCost = p3Cost;
		} else if (mostE == p3Value) {
			minCost = Math.min(minCost, p3Cost);
		}
		return new Info(mostE, minCost);
	}
	 **/

	public static int minCost(String str) {
		int n = str.length();
		if (n < 3) {
			return -1;
		}
		int[] arr = new int[n];
		// d认为是0，e认为是1，r认为是2
		for (int i = 0; i < n; i++) {
			char cur = str.charAt(i);
			if (cur == 'd') {
				arr[i] = 0;
			} else if (cur == 'e') {
				arr[i] = 1;
			} else {
				arr[i] = 2;
			}
		}
		// 通过上面的转化，问题变成了：
		// 1的左右，一定要被0和2包围，这个1才是"好1"
		// 请让"好1"的尽量多，返回最少的修改代价
		int maxGood = 0;
		int minCost = Integer.MAX_VALUE;
		for (int prepre = 0; prepre < 3; prepre++) {
			for (int pre = 0; pre < 3; pre++) {
				int cost = arr[0] == prepre ? 0 : 1;
				cost += arr[1] == pre ? 0 : 1;
				Info cur = process(arr, 2, prepre, pre);
				if (cur.maxGood > maxGood) {
					maxGood = cur.maxGood;
					minCost = cur.minCost + cost;
				} else if (cur.maxGood == maxGood) {
					minCost = Math.min(minCost, cur.minCost + cost);
				}
			}
		}
		return minCost;
	}

	public static class Info {
		public int maxGood;
		public int minCost;

		public Info(int a, int b) {
			maxGood = a;
			minCost = b;
		}
	}

	// 暴力递归
	// 可以自己改成动态规划
	// arr[index-2]位置的数值是prepre
	// arr[index-1]位置的数值是pre
	// 在这种情况下，请让arr[index...]上的好1尽量多
	// 返回:
	// 尽量多的"好1"，是多少？
	// 得到尽量多的"好1"，最小代价是多少?
	public static Info process(int[] arr, int index, int prepre, int pre) {
		if (index == arr.length) {
			return new Info(0, 0);
		}
		// 可能性1，arr[index]，变成0
		int p1Value = prepre == 2 && pre == 1 ? 1 : 0;
		int p1Cost = arr[index] == 0 ? 0 : 1;
		Info info = process(arr, index + 1, pre, 0);
		p1Value += info.maxGood;
		p1Cost += info.minCost;
		// 可能性2，arr[index]，变成1
		int p2Value = 0;
		int p2Cost = arr[index] == 1 ? 0 : 1;
		info = process(arr, index + 1, pre, 1);
		p2Value += info.maxGood;
		p2Cost += info.minCost;
		// 可能性3，arr[index]，变成2
		int p3Value = prepre == 0 && pre == 1 ? 1 : 0;
		int p3Cost = arr[index] == 2 ? 0 : 1;
		info = process(arr, index + 1, pre, 2);
		p3Value += info.maxGood;
		p3Cost += info.minCost;
		// 开始决策，选出三种可能性中的最优解
		int maxGood = 0;
		int minCost = Integer.MAX_VALUE;
		if (p1Value > maxGood) {
			maxGood = p1Value;
			minCost = p1Cost;
		} else if (p1Value == maxGood) {
			minCost = Math.min(minCost, p1Cost);
		}
		if (p2Value > maxGood) {
			maxGood = p2Value;
			minCost = p2Cost;
		} else if (p2Value == maxGood) {
			minCost = Math.min(minCost, p2Cost);
		}
		if (p3Value > maxGood) {
			maxGood = p3Value;
			minCost = p3Cost;
		} else if (p3Value == maxGood) {
			minCost = Math.min(minCost, p3Cost);
		}
		return new Info(maxGood, minCost);
	}

}
