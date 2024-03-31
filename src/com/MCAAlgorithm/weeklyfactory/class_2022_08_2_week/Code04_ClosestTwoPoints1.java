package com.MCAAlgorithm.weeklyfactory.class_2022_08_2_week;

// 给定平面上n个点，x和y坐标都是整数
// 找出其中的一对点的距离，使得在这n个点的所有点对中，该距离为所有点对中最小的
// 返回最短距离，精确到小数点后面4位
// 测试链接 : https://www.luogu.com.cn/problem/P1429
// 提交以下所有代码，把主类名改成Main，可以直接通过
// T(N) = 2*T(N/2) + O(N*logN)
// 这个表达式的时间复杂度是O(N*(logN的平方))
// 复杂度证明 : https://math.stackexchange.com/questions/159720/
// 网上大部分的帖子，答案都是这个复杂度
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

// 理论上，第二个找min的优化是个几何问题
//先把所有点根据x点排序，分成两部分，每部分N/2，
// 找到两侧真实距离的最短距离min，然后符合这些节点的按照y的坐标点从小达到排序，然后每个点进行考察，如果y方向的距离大于了min对应这个点上面的所有点都可以跳过
// 在整合左右的时候就是找有没有可能找到比min更小的点，
// 整合时候y方向的区域，以上方为例，y上方最多到"考察点y+min"，左右分别是"考察点x +/- min"，其实就是在以考察点为圆心，min/根号2
//  ------min--------｜-------min--------
// ｜           	 ｜					｜
// ｜				 ｜					｜
// ｜				 ｜					｜
// ｜				 ｜					｜
// ｜				 ｜					｜
// ｜				 ｜					｜
//2*min------------ 考察点 -------------2*min
// ｜           	 ｜					｜
// ｜				 ｜					｜
// ｜				 ｜					｜
// ｜				 ｜					｜
// ｜				 ｜					｜
// ｜				 ｜					｜
//  ------min--------｜-------min--------
// 优化来了：只要来找到距离"考察点"小于min的点，只需要在这个四个放个里面找到；只要来到一个点就把这个点的方圆min范围的点都挖掉，不考虑了！这样的点在这个格子里面只有六个
public class  Code04_ClosestTwoPoints1 {

	public static int N = 200001;

	public static Point[] points = new Point[N];//

	public static Point[] deals = new Point[N];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			for (int i = 0; i < n; i++) {
				in.nextToken();
				double x = (double) in.nval;
				in.nextToken();
				double y = (double) in.nval;
				points[i] = new Point(x, y);
			}
			Arrays.sort(points, 0, n, (a, b) -> a.x <= b.x ? -1 : 1);
			double ans = nearest(0, n - 1);
			out.println(String.format("%.4f", ans));
			out.flush();
		}
	}

	public static class Point {
		public double x;
		public double y;

		public Point(double a, double b) {
			x = a;
			y = b;
		}
	}

	public static double nearest(int left, int right) {
		double ans = Double.MAX_VALUE;
		if (left == right) {
			return ans;
		}
		int mid = (right + left) / 2;
		ans = Math.min(nearest(left, mid), nearest(mid + 1, right));
		int l = mid;
		int r = mid + 1;
		int size = 0;
		while (l >= left && points[mid].x - points[l].x <= ans) {
			deals[size++] = points[l--];
		}
		while (r <= right && points[r].x - points[mid].x <= ans) {
			deals[size++] = points[r++];
		}
		Arrays.sort(deals, 0, size, (a, b) -> a.y <= b.y ? -1 : 1);
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (deals[j].y - deals[i].y >= ans) {
					break;
				}
				ans = Math.min(ans, distance(deals[i], deals[j]));
			}
		}
		return ans;
	}

	public static double distance(Point a, Point b) {
		double x = a.x - b.x;
		double y = a.y - b.y;
		return Math.sqrt(x * x + y * y);
	}

}