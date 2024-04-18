package com.MCAAlgorithm.weeklyfactory.class_2022_11_1_week;


// 给你下标从 0 开始、长度为 n 的字符串 pattern ，
// 它包含两种字符，'I' 表示 上升 ，'D' 表示 下降 。
// 你需要构造一个下标从 0 开始长度为 n + 1 的字符串，且它要满足以下条件：
// num 包含数字 '1' 到 '9' ，其中每个数字 至多 使用一次。
// 如果 pattern[i] == 'I' ，那么 num[i] < num[i + 1] 。
// 如果 pattern[i] == 'D' ，那么 num[i] > num[i + 1] 。
// 请你返回满足上述条件字典序 最小 的字符串 num。
// 测试链接 : https://leetcode.cn/problems/construct-smallest-number-from-di-string/
public class Code05_CreateMinNumberFromPattern {

	public static String smallestNumber(String pattern) {

		return String.valueOf(create(pattern.toCharArray(), 0, 0, 0));
	}

	
	// pattern I I I D
	//         0 1 2 i
	//       1 3 4 5 2
	// -1
	
	//       1589
	//        9 8    5 4 3 2 1 0
	//        1 1    1 0 0 0 1 0
	//       number = 1589
	// 返回 i... 所有数字都决定了，并且不破坏pattern，并且1~9每个数字最多用一次
	// 能出来的最小值是啥，返回
	// pattern来到index位，之前拼的整个数字number，status表示凑到当前index之前哪些数字用了，哪些数字没用
	public static int create(char[] pattern, int index, int status, int number) {
		if (index == pattern.length + 1) { //遍历完了，注意这个下标，表示i到数字长度位置决定做完，pattern.len + 1 = str_num.len
			return number; //返回这个拼成的数字
		}
		int cur = 0;//从0开始尝试，刚比cur大，并且还能找到下一位
		while ((cur = next(status, cur)) != -1) {  //【这个课上没有讲，课上也讲了哈哈，自己发现的】这里其实有一个贪心，因为这个开始是从最小开始往后找的，如果前面位置加了这些并且符合pattern，那么这个找到的肯定最小
			// cur == 0 , 当前位，1 X
			// cur == 1 , 当前位，2 X
			// cur == 2,  当前位，4 
			// partern I D >
			//         0 1 2 3
			//         ? ? ? ?
			//         D
			//         0 1
			//         5 ?
			// （1）pattern来到0位置，直接往下推，不用判断任何东西；
			// （2）pattern来到i位置，看看前面是I还是D，
			if (index == 0 //这里非常开心，不红管任何东西放就行了
					||
					//number%10把上面拼成的数的最后一位掏出来看看当前选的这个cur符不符合条件！！！！！
					(pattern[index - 1] == 'I' && number % 10 < cur) || (pattern[index - 1] == 'D' && number % 10 > cur)) {
				int ans = create(pattern, index + 1, status | (1 << cur), number * 10 + cur); //number把当前选择的cur加进去
				if (ans != -1) {
					return ans; //贪心在这里了，肯定紧着小的往前放
				}
			}
		}
		return -1; //如果破坏了pattern，直接返回-1，这一步做不到了
	}

	// status :
	//               9 8 7 6 5 4 3 2 1 0
	//               1 1 1 1 1 1 0 0 1 0
	// 在status中返回没有使用，且 > num, 最小的数字
	// num = 3，找到刚好大于3的数字且没用过的，上面的status没有这样的数返回-1
	public static int next(int status, int num) {
		for (int i = num + 1; i <= 9; i++) {
			if ((status & (1 << i)) == 0) {
				return i;
			}
		}
		return -1;
	}

}
