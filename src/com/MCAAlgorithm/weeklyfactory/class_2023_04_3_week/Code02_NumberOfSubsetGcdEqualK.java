package com.MCAAlgorithm.weeklyfactory.class_2023_04_3_week;

// 来自腾讯笔试
// 给定一个长度为N的正数数组，还有一个正数K
// 返回有多少子序列的最大公约数为K
// 结果可能很大，对1000000007取模
// 原题目简单转化就是如下的题目
// 测试链接 : https://www.luogu.com.cn/problem/CF803F
// 所以课上会讲怎么转化，然后就是讲测试链接里的题目
// 1 <= N <= 10^5
// 1 <= arr[i] <= 10^5
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的所有代码，并把主类名改成"Main"
// 可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


// 思路：公约数问题，转换一下
// 例子：[2,5,13,7,10,20],k=5,公约数没有5的先从数组中去掉[5,10,20],公约一下[1,2,4],最后在问以1为最大公约数的子序列有几个？到这里就是luogu原题了
// 1、首先对数组进行转化，压缩数组[1,2,1,1,2,4,4,4],用下标表示值本身，数组上的值记录次数[3 2 0 3],
// 2、数学结论：假设数组中的值最大为10，10出现了a次，5出现了b次；那么以10为公约数有2^a-1（减去的1是一个都不选的那个）个子序列，在所有子序列中有多少个以5做最大公约数的子序列?
//    2^(a+b)-1,是以5做公因子，但是不一定以5做最大公约数（因为有一部分是以10做最大公约数），那么在所有子序列中以5做最大公约数的子序列个数 2^(a+b)-2^a
// 3、2-e个、4-d个、6-c个、8-b个、10-a个，求出以2为公因子总共2^(e+d+c+b+a)-1个，然后减去单纯以4、6、8、10做最大公约数的数量得到2^(a+b+c+d+e)-2^a-2^b-2^c-2^d
// 4、数学结论：【记住就行了】时间复杂度，如果某个过程是n+n/2+n/3+n/4+...n/n,那么他的时间复杂度是O(nlogn),上面的过程虽然是两个for循环（因为内部每次都是从n/i），但是时间复杂的确实是O(nlog)
public class Code02_NumberOfSubsetGcdEqualK {

	public static int MAXN = 100001;

	public static int mod = 1000000007;

	public static long[] dp = new long[MAXN];

	public static long[] cnt = new long[MAXN]; //没个数出现了几个

	public static long[] pow2 = new long[MAXN]; //总在算2^n次方，把2（1到maxn次方）%10^7放入表中

	public static int n, v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			pow2[0] = 1;
			for (int i = 1; i <= n; i++) {
				in.nextToken();
				v = (int) in.nval;
				cnt[v]++;//记录数值出现次数
				pow2[i] = (pow2[i - 1] * 2) % mod; //求2^i，就是2^(i-1)*2的结构
			}
			for (int i = MAXN - 1; i >= 1; i--) {//这里要从后往前遍历，因为在减的时候正好当前位可以用到后面位提前求出来的结果
				long counts = 0; //统计
				for (int j = i; j < MAXN; j += i) { //j+i，j+2i，j+3i<maxn
					counts = (counts + cnt[j]) % mod;
				}
				dp[i] = (pow2[(int) counts] - 1 + mod) % mod;//数学结论里面那个总数量
				for (int j = 2 * i; j < MAXN; j += i) { //减掉2*i倍的i
					dp[i] = (dp[i] - dp[j] + mod) % mod;
				}
			}
			out.println(dp[1]);//这里根据思路中描述，把最大公约数都除掉，最后就是以1为最大公约数
			out.flush();
		}
	}

}