package com.MCAAlgorithm.weeklyfactory.class_2022_06_4_week;

import java.util.ArrayList;
import java.util.Stack;

// 来自微软
// 请设计一种叫做“栈的管理器”的结构，实现如下6个功能
// 1) void createNewStack() : 可以在该结构中生成一个栈结构，编号从0开始
// 2) void push(int num, int stackIndex) : 将编号为stackIndex的栈里，压入num
// 3) int pop(int stackIndex) : 从编号为stackIndex的栈里，弹出栈顶返回
// 4) int peek(int stackIndex) ：从编号为stackIndex的栈里，返回栈顶但是不弹出
// 5) boolean isEmpty(int statckIndex)：返回编号为stackIndex的栈是否为空
// 6) int stackSize() : 返回一共生成了多少个栈
// 要求：不管用户调用多少次上面的方法，只使用有限几个动态数组(常数个)，完成代码实现
public class Code02_StackNotSplit {

	public static class Stacks1 {

		public ArrayList<Stack<Integer>> stacks;

		public Stacks1() {
			stacks = new ArrayList<>();
		}

		public int stackSize() {
			return stacks.size();
		}

		public void createNewStack() {
			stacks.add(new Stack<>());
		}

		public void push(int num, int stackIndex) {
			stacks.get(stackIndex).push(num);
		}

		public int pop(int stackIndex) {
			return stacks.get(stackIndex).pop();
		}

		public boolean isEmpty(int statckIndex) {
			return stacks.get(statckIndex).isEmpty();
		}

		public int peek(int stackIndex) {
			return stacks.get(stackIndex).peek();
		}

	}

	public static class Stacks2 {
		//添加情况描述：如果用户让新建一个栈，顶数组没有数值的时候，栈定数组[0]为-1表示第0号栈什么都没有，现在在栈0中添加一个数字栈顶[0]=0,values[0]=3,lasts[0]=-1（上一个位置是-1）
		//添加5，顶[0]=1,values[0]=3,values[1]=5,lasts[0]=-1,last[0]=0
		//让系统新建一个栈，栈顶[1]=2(栈1的栈顶是values下标为2的),values[2]=9,lasts[2]=-1这个点没有栈顶
		//0号栈增加一个7,heads[0]=3,values[3]=7,heads[3]=1
		//弹出情况描述：
		// 顶：0   2
		// 值：3	  5  6
		// 上：-1 -1  1
		// 此时0号栈1一个元素，1号栈两个元素，此时弹出1号栈,返回3，但是此时会有空洞
		// 顶:-1  2
		// 值     5   6
		// 上 (这个数上面不再有东西)   -1  1
		//垃圾放入0，表示value的0位置是空洞， 此时垃圾区表示现在哪个是空的，此时只有0位置可以使用，栈1添加9,垃圾区从右往左拿，只有0位置可以用，把9填入值数组空缺位置
		// 顶:-1  0(一号栈的栈顶更新)
		// 值 9   5   6
		// 上 2   -1  1 （2表示value[0]的上一个位置是value的下标2，value[6]=2）
		// 此时垃圾区清空

		public ArrayList<Integer> heads; //顶数组（存下标）
		public ArrayList<Integer> values; //值数组
		public ArrayList<Integer> lasts;  //"上"数组（存下标）
		public ArrayList<Integer> frees; //"空闲"的值数组的下标
		// occupySize : 值数组用到哪了？
		public int occupySize;
		// freeSize : 垃圾区的大小（最先用！）
		public int freeSize;

		public Stacks2() {
			heads = new ArrayList<>();
			values = new ArrayList<>();
			lasts = new ArrayList<>();
			frees = new ArrayList<>();
			occupySize = 0;
			freeSize = 0;
		}

		public int stackSize() {
			return heads.size();
		}

		public void createNewStack() {

			//新建栈的时候什么都没有，head都为-1；
			heads.add(-1);
		}

		//增加一个数，两种情况：垃圾区有空闲使用垃圾区，没有的话直接往里加
		public void push(int num, int stackIndex) {
			// 老头部出来，因为新头部往前跳，要能找到老头部
			int headIndex = heads.get(stackIndex);
			if (freeSize == 0) { // 垃圾区没有空闲的位置，说明value数组里面没有空洞，往后正常添加就行
				// value , occupySize, occupySize++
				heads.set(stackIndex, occupySize++);//栈顶位置设置为occupySize，然后occupySize++
				values.add(num);//增加添加的值
				lasts.add(headIndex);//当前值的lasts是老头部
			} else { // 垃圾区有空闲空间
				// 新的数 -> freeIndex
				// 假设垃圾区有17 24  26（垃圾区肯定是从左往右递增）,使用的时候从垃圾区最右拿使用
				int freeIndex = frees.get(--freeSize); //先--，拿到freeIndex
				heads.set(stackIndex, freeIndex);//当前拿的这个freeIndex作为新的头部
				values.set(freeIndex, num); //设置值
				lasts.set(freeIndex, headIndex);//新头部的上一个就是老头部啊！！！
			}
		}

		//头：0	1
		//值：3	5
		//上：-1 -1
		//现在把值3弹栈
		//头：-1	1
		//值：	5
		//上： -1
		//值清空，last对应位置清空，头部改成-1，垃圾区增加0
		public int pop(int stackIndex) {
			// 当前的头部的位置
			int headIndex = heads.get(stackIndex); //弹对应stackIndex的头
			// values -> 当前的头部的位置 -> 要返回的数！
			int ans = values.get(headIndex);
			// 当前头要弹出了！接下来的头是谁？
			int newHeadIndex = lasts.get(headIndex);//改头，栈里面次顶部的index
			heads.set(stackIndex, newHeadIndex);//把当前stack的头设置为新头（次顶）
			// 垃圾区要接受当前的头部的位置
			// frees动态数组！
			if (freeSize >= frees.size()) { //todo:感觉直接往里添加就可以了。因为垃圾区是动态调整的，freeSize可能会大于当前的垃圾区调整数组的大小
				frees.add(headIndex);
				freeSize++;
			} else {
				frees.set(freeSize++, headIndex);
			}
			return ans;
		}

		public boolean isEmpty(int statckIndex) {
			return heads.get(statckIndex) == -1;
		}

		public int peek(int stackIndex) {
			return values.get(heads.get(stackIndex));
		}

	}

	public static void main(String[] args) {
		int V = 10000;
		int testTime = 20000;
		System.out.println("测试开始");
		Stacks1 stack1 = new Stacks1();
		Stacks2 stack2 = new Stacks2();
		for (int i = 0; i < testTime; i++) {
			double decide = Math.random();
			if (decide < 0.25) {
				stack1.createNewStack();
				stack2.createNewStack();
			} else {
				int stackSize1 = stack1.stackSize();
				int stackSize2 = stack2.stackSize();
				if (stackSize1 != stackSize2) {
					System.out.println("栈的数量不一致！");
					break;
				}
				if (stackSize1 > 0) {
					int stackIndex = (int) (Math.random() * stackSize1);
					if (decide < 0.5) {
						int num = (int) (Math.random() * V);
						stack1.push(num, stackIndex);
						stack2.push(num, stackIndex);
					} else if (decide < 0.75) {
						if (stack1.isEmpty(stackIndex) != stack2.isEmpty(stackIndex)) {
							System.out.println(stackIndex + "号栈的是否为空不一致！");
							break;
						}
						if (!stack1.isEmpty(stackIndex)) {
							if (stack1.pop(stackIndex) != stack2.pop(stackIndex)) {
								System.out.println(stackIndex + "号栈的弹出数据不一致！");
								break;
							}
						}
					} else {
						if (stack1.isEmpty(stackIndex) != stack2.isEmpty(stackIndex)) {
							System.out.println(stackIndex + "号栈的是否为空不一致！");
							break;
						}
						if (!stack1.isEmpty(stackIndex)) {
							if (stack1.peek(stackIndex) != stack2.peek(stackIndex)) {
								System.out.println(stackIndex + "号栈的栈顶数据不一致！");
								break;
							}
						}
					}
				}
			}
		}
		System.out.println("测试结束");
	}

}
