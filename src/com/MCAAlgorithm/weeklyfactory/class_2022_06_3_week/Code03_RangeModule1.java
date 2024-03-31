package com.MCAAlgorithm.weeklyfactory.class_2022_06_3_week;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// Range模块是跟踪数字范围的模块。【面试可以在这扣这个题】
// 设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
// 半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。
// 实现 RangeModule 类:
// RangeModule() 初始化数据结构的对象
// void addRange(int left, int right) : 
// 添加 半开区间 [left, right)，跟踪该区间中的每个实数。
// 添加与当前跟踪的数字部分重叠的区间时，
// 应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
// boolean queryRange(int left, int right) : 
// 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true
// 否则返回 false 。
// void removeRange(int left, int right) : 
// 停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
// 测试连接 : https://leetcode.com/problems/range-module/
public class Code03_RangeModule1 {

	class RangeModule {
		// 某个区间的开头是key，结尾是value,左闭右开
		// [3,100)
		// key 3 value 100
		// [3,100) 一旦生成就不可能有[45, 86)，如果有3到100的区间，那么就不可能出现45到86这个区间
		// [3,17) [17,23) -> [3,23),这样的形式化简到最干净的形式
		// 目标：不管怎么调用，一定要保证！
		// map里表示的区间，能合并就合并，且没有交集
		TreeMap<Integer, Integer> map;

		public RangeModule() {
			map = new TreeMap<>();
		}

		// 加入一个区间
		// 例子如果要生成[100,200)这个区间rangemodule，给出以下信息[80,85),[92,105).[120,136),[150,163),[180,210)
		//删除掉比100小的、比200大的,然后生成区间，先生成[92,200)然后一看到了210，最后生成[92,210)这个区间
		public void addRange(int left, int right) {
			// 无效区间直接返回
			if (right <= left) {
				return;
			}
			// 有效区间！
			// 当前要加入的区间是[left, right)
			// 比如 : 当前区间[34, 76)
			// 第一个if：
			// 如果小于等于34的开头，和小于等于76的开头不存在
			// 那么说明，34之前没有区间，34到75也没有区间
			// 直接加入就可以了
			// 第二个if：
			// 说明有小于等于34的开头，并且延伸的结尾大于等于34
			// 比如，之前有两个区间[30, 54), [70, 108)
			// 有小于等于34的开头，就是30
			// 并且延伸的结尾大于等于34，就是54
			// 同时，看一下小于等于76的开头，就是70，延伸到108，所以一起合并
			// 合并成[30, 108]
			// 再比如，之前有两个区间[30, 54), [70, 72)
			// 有小于等于34的开头，就是30，并且延伸的结尾大于等于34，就是54
			// 同时，看一下小于等于76的开头，就是70，延伸到72，所以一起合并
			// 合并成[30, 76]
			// 即: map.put(start, Math.max(map.get(end), right));
			// 第三个分支，最后的else：
			// 说明是前两个if的反面：
			// [34, 76)
			// 第一个if的反面: 小于等于34的开头，和小于等于76的开头，不是都不存在
			// 分成以下几种情况
			// 1) 小于等于34的开头存在，小于等于76的开头不存在，这是不可能的
			// 2) 小于等于34的开头不存在，小于等于76的开头存在，这是可能的
			// 3) 小于等于34的开头存在，小于等于76的开头也存在，这是可能的
			// 于是，第一个if的反面，分成了如下两种情况
			// a) 小于等于34的开头不存在，小于等于76的开头存在
			// b) 小于等于34的开头存在，小于等于76的开头也存在
			// 再看第二个if的反面
			// [34, 76)
			// 第二个if是，小于等于34的开头存在，并且，结尾延伸到了34以右
			// 所以第二个if的反面是：
			// c) 小于等于34的开头不存在
			// d) 小于等于34的开头存在，但是结尾没有延伸到34
			// 那么a，b，c，d结合就有四种可能
			// 1) a + c，小于等于34的开头不存在，小于等于76的开头存在
			// 2) a + d，不可能
			// 3) b + c，不可能
			// 4) b + d，小于等于34的开头存在，但是结尾没有延伸到34，小于等于76的开头也存在
			// 只有1)、4）是可能的
			// 如果是1)，那么一定会新出现一个开头为34的区间，但是结尾在哪？
			// 比如当前区间[34, 76)，情况1)是：小于等于34的开头不存在，小于等于76的开头存在
			// 比如，之前有个区间是[56,72)，小于等于76的开头是56
			// 这两个区间合并成[34, 76)
			// 在比如，之前有个区间是[56,108)，小于等于76的开头是108
			// 这两个区间合并成[34, 108)
			// 即：map.put(left, Math.max(map.get(end), right));
			// 如果是4)，小于等于34的开头存在，但是结尾没有延伸到34，小于等于76的开头也存在
			// 这种情况下，虽然小于等于34的开头存在，
			// 但是结尾没有延伸到34，那么就可以不管之前的区间啊，反正不会有影响的。
			// 于是处理和情况1)是一样的
			// 即：map.put(left, Math.max(map.get(end), right));
			// 这就是接下来三个逻辑分支的处理
			// [5, 9) ....
			// [4, 6) [7, 100)
			Integer start = map.floorKey(left);//5的开头是4
			Integer end = map.floorKey(right);//9的结束时100
			// [34, 76)
			// 都空
			// 反面1) <= 34 没有，<= 76开头，有
			// 反面2) <= 34 有, <= 76开头，没有 ，不可能 X
			// 反面3) <= 34 有, <= 76开头，有
			if (start == null && end == null) { //左右区间的开头和结尾都没有
				map.put(left, right);
			} else if (start != null && map.get(start) >= left) {//找到左侧，前面的部分已经侵入了现在要添加的部分，一定要把前面的那部分拉进到当前部分
				// [34, 76)
				// 2if : <= 34 开头 ，有，且！，结尾在34以右
				// 反面1 ) <= 34 开头 ，没有
				// 反面2 ) <= 34 开头 ，有, 结尾没在34以右
				map.put(start, Math.max(map.get(end), right));//谁能到的更远，如果有结束点map.get(end)获得end的结束点当前生成到谁
			} else { //上面那些文字就是在解释剩下的条件不想需要写另外一堆if else，直接用else代替即可，就是start有没有无所谓，反正确定start对应的结尾不穿当前区域了，直接把结束部分确定了即可
				// 1) a + c，小于等于34的开头不存在，小于等于76的开头存在
				// 4) b + d，小于等于34的开头存在，但是结尾没有延伸到34，小于等于76的开头也存在
				// [1, 2) [5, 6)
				// [1,2 ) [5, 6)
				map.put(left, Math.max(map.get(end), right));
			}
			// 上面做了合并，但是要注意可能要清理一些多余的区间
			// 【1】比如，当前区间[34, 76)
			// 之前的区间是[100, 840)
			// 这种情况，中了分支一，在合并之后，区间为：
			// [34, 76)，[100, 840)
			// 所以移除掉所有(34,76]的开头
			// 只剩下了[34, 76)，[100, 840)
			// 【2】再比如，当前区间[34, 76)
			// 之前的区间是[30, 54)、[55, 60)、[62, 65)、[70, 84)
			// 这种情况，中了分支二，在合并之后，区间为：
			// [30, 84)、[55, 60)、[62, 65)、[70, 84)
			// 所以移除掉所有(34, 76]的开头区间
			// 只剩下了[30, 84)
			// (left, right]
			// 【3】再比如，当前区间[34, 76)
			// [70, 108) 来的是[34, 76)
			// [34, 108) 以(34, 76]开头的删掉
			// 【4】再比如，当前区间[34, 76)
			// [3,17) [73,108) 来的是[34, 76)
			// [3,17) [34,108) 以3删除掉[73,108) (34, 76]
			// 【5】再比如，当前区间[34,76),之前区间[76,100)；
			// 合并后[34,100),[76,100),要把从(34,76]这些区间开头的删除掉！所以删除[76,100)，只剩下[34,100)
			//这个subMap的解释：清理掉不包含left开，right包括的，只要大于left开头小于等于right的key(起始点)全部删除掉，因为包含left的部分已经整理完成了，是我们要的结果，
			//所以我们还要保留left这个部分，right开头的
			Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
			Set<Integer> set = new HashSet<>(subMap.keySet());
			map.keySet().removeAll(set);
		}

		public boolean queryRange(int left, int right) {
			// [34, 76) 整体被你的结构，有没有包含！
			// <=34 开头都没！
			Integer start = map.floorKey(left);
			if (start == null)
				return false;
			// [34, 76) 整体被你的结构，有没有包含！
			// <=34 开头有！[17，~ 60) [60 ~ 76)
			return map.get(start) >= right;
		}

		public void removeRange(int left, int right) {
			if (right <= left) {
				return;
			}
			Integer start = map.floorKey(left);
			Integer end = map.floorKey(right);
			//删除[30,100）这个区间,先找有没有小于30开始的区间，如果查到有一个[17,56)这个区间，坎掉后修改成[17,30)
			//如果右侧有比right小的开始节点，我们也要把这部分删除,这部分从right开始，并从end结束
			if (end != null && map.get(end) > right) { //right开始到end结束
				map.put(right, map.get(end));
			}
			if (start != null && map.get(start) > left) { //小于left部分的start包括，并且这个start的结束位置大于了left
				map.put(start, left);//left把这个start的结束位置截止的left位置
			}
			Map<Integer, Integer> subMap = map.subMap(left, true, right, false); //30到100这部分的中间部分一定要清理掉，这里right标志应该是true
			Set<Integer> set = new HashSet<>(subMap.keySet());
			map.keySet().removeAll(set);
		}
	}

	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap<>();
		map.put(6, "我是6");
		map.put(3, "我是3");
		map.put(9, "我是9");
		map.put(5, "我是9");
		map.put(4, "我是9");
		// 3 4 5 6 9
		// [4~6) -> 4, 5,6

		Map<Integer, String> subMap = map.subMap(4, true, 6, false);

		for (int key : subMap.keySet()) {
			System.out.println(key);
		}

	}

}
