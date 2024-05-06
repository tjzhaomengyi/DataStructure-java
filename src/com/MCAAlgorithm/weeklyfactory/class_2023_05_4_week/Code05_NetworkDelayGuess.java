package com.MCAAlgorithm.weeklyfactory.class_2023_05_4_week;

import java.util.HashSet;

// 来自招商银行
// 这个题是个破题，但这可能是经常遇到的一种题，
// 讲一下思路即可，没有必要投入大多时间给这种题目
// 一共有三个服务A、B、C，网络延时分别为a、b、c
// 并且一定有：1 <= a <= b <= c <= 10^9
// 但是具体的延时数字丢失了，只有单次调用的时间
// 一次调用不可能重复使用相同的服务，
// 一次调用可能使用了三个服务中的某1个、某2个或者全部3个服务
// 比如一个调用的时间，T = 100
// 100的延时可能来自以下7种情况：
// a = 100，这次调用可能单独使用了A
// b = 100，这次调用可能单独使用了B
// c = 100，这次调用可能单独使用了C
// a + b = 100，这次调用可能组合使用了A、B
// a + c = 100，这次调用可能组合使用了A、C
// b + c = 100，这次调用可能组合使用了B、C
// a + b + c = 100，这次调用可能组合使用了A、B、C全部服务
// 那么可想而知，如果给的调用时间足够多，是可以猜测出a、b、c的
// 给定一个数组times，长度为n，并且一定有4 <= n <= 7
// times[i] = s，表示i号调用用时s，而且times中一定都是正数且没有重复值
// 请根据n次调用，猜测出a、b、c三元组可能的情况数
// 如果任何a、b、c都无法匹配上给定的调用耗时，返回0
// 测试的次数T <= 100
// 也就是说，一共最多给定100个数组，每一次让你返回a、b、c三元组可能的情况数
public class Code05_NetworkDelayGuess {

//	public static void zuo() {
//
//		// status[0] : a
//		// status[1] : b
//		// status[2] : c
//		// status[3] : a + b
//		// status[4] : a + c
//		// status[5] : b + c
//		// status[6] : a + b + c
//		// 如果status[4] = 0, 表示a + c 这种情况没被选中！
//		// status[4] > 0 , 如果 a + c = 250，给定了具体的延时
//		int[] status = new int[7];
//		int[] values = { 100, 50, 150, 250 };
//
//	}
//  i=0，value就是100，i=1，value是50；i=2，value=150；i=3，value=250
//	public static void test(int[] status, int[] values, int i) {
//		if (i == values.length) { //表示所有的数都分配完了
//			// 分配之后，a、b、c求出来
//		} else { //过程中如何分配
//			// values[i] :当前的数值是values[i]
//			for (int j = 0; j < status.length; j++) { //每一种情况全排列去尝试
//				if (status[j] == 0) { //说明没有得到过数值
//					status[j] = values[i];//把当前数值分配个status[j]
//					test(status, values, i + 1);//把下一个数值继续递归看看怎么弄好
//					status[j] = 0;//恢复现场，恢复现场，恢复现场
//				}
//			}
//		}
//
//	}

	// 这个函数在测试时最多会调用100次，100次的整体运行时间1s以内
	public static int ways(int[] times) {
		int[] status = new int[7];
		HashSet<String> ans = new HashSet<>();//把方案去重
		process(times, 0, status, ans);
		return ans.size();
	}

	// status[0] : a
	// status[1] : b
	// status[2] : c
	// status[3] : a + b
	// status[4] : a + c
	// status[5] : b + c
	// status[6] : a + b + c
	public static void process(int[] times, int i, int[] status, HashSet<String> ans) {
		if (i == times.length) {
			int a = 0;
			int b = 0;
			int c = 0;
			int cnt = counts(status); //统计单条件给出几个
			//cnt=0的情况是：a、b、c这种单条件的一个都没给；如果单条件一个都没给，说明3456条件是全的，通过345足以算出abc来，用6来做验证
			if (cnt == 0) {
				a = (status[3] + status[4] - status[5]) / 2;
				b = (status[3] + status[5] - status[4]) / 2;
				c = (status[4] + status[5] - status[3]) / 2;
			} else if (cnt == 1) { //情况2：abc单条件只给了一个
				if (status[0] != 0) {
					a = status[0];
					if (status[3] != 0) {
						b = status[3] - a;
					}
					if (status[4] != 0) {
						c = status[4] - a;
					}
					if (status[5] != 0) {
						if (b != 0 && c == 0) {
							c = status[5] - b;
						}
						if (c != 0 && b == 0) {
							b = status[5] - c;
						}
					}
				} else if (status[1] != 0) {
					b = status[1];
					if (status[3] != 0) {
						a = status[3] - b;
					}
					if (status[5] != 0) {
						c = status[5] - b;
					}
					if (status[4] != 0) {
						if (a != 0 && c == 0) {
							c = status[4] - a;
						}
						if (c != 0 && a == 0) {
							a = status[4] - c;
						}
					}
				} else { //给了3个
					c = status[2];
					if (status[4] != 0) {
						a = status[4] - c;
					}
					if (status[5] != 0) {
						b = status[5] - c;
					}
					if (status[3] != 0) {
						if (a != 0 && b == 0) {
							b = status[3] - a;
						}
						if (b != 0 && a == 0) {
							a = status[3] - b;
						}
					}
				}
			} else if (cnt == 2) {
				if (status[0] != 0) {
					a = status[0];
				}
				if (status[1] != 0) {
					b = status[1];
				}
				if (status[2] != 0) {
					c = status[2];
				}
				if (a == 0) {
					if (status[3] != 0) {
						a = status[3] - b;
					} else if (status[4] != 0) {
						a = status[4] - c;
					} else {
						a = status[6] - b - c;
					}
				} else if (b == 0) {
					if (status[3] != 0) {
						b = status[3] - a;
					} else if (status[5] != 0) {
						b = status[5] - c;
					} else {
						b = status[6] - a - c;
					}
				} else {
					if (status[4] != 0) {
						c = status[4] - a;
					} else if (status[5] != 0) {
						c = status[5] - b;
					} else {
						c = status[6] - a - b;
					}
				}
			} else {
				a = status[0];
				b = status[1];
				c = status[2];
			}
			//验证
			if (verify(status, a, b, c)) {
				ans.add(a + "_" + b + "_" + c);
			}
		} else {
			//七种状态
			for (int j = 0; j < 7; j++) {
				if (status[j] == 0) { //当前这个状态没有分配
					status[j] = times[i];//把times[i]时间分配给这个状态
					process(times, i + 1, status, ans);
					status[j] = 0;//恢复现场
				}
			}
		}
	}

	public static int counts(int[] status) {
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (status[i] != 0) {
				cnt++;
			}
		}
		return cnt;
	}

	// a <= b <= c
	public static boolean verify(int[] s, int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0 || a > b || b > c) {
			return false;
		}
		if (s[0] != 0 && s[0] != a) {//给过值，如果和猜测的不一样，就是失败
			return false;
		}
		if (s[1] != 0 && s[1] != b) {
			return false;
		}
		if (s[2] != 0 && s[2] != c) {
			return false;
		}
		if (s[3] != 0 && s[3] != a + b) {
			return false;
		}
		if (s[4] != 0 && s[4] != a + c) {
			return false;
		}
		if (s[5] != 0 && s[5] != b + c) {
			return false;
		}
		if (s[6] != 0 && s[6] != a + b + c) {
			return false;
		}
		return true;
	}

}
