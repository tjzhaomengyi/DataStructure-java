package com.MCAAlgorithm.weeklyfactory.class_2022_08_2_week;

import java.util.Stack;

// 给定一个逆波兰式
// 转化成正确的中序表达式
// 要求只有必要加括号的地方才加括号
public class Code03_ReversePolishNotation {

	// 请保证给定的逆波兰式是正确的！
	public static int getAns(String rpn) {
		if (rpn == null || rpn.equals("")) {
			return 0;
		}
		String[] parts = rpn.split(" ");
		Stack<Integer> stack = new Stack<>();
		for (String part : parts) {
			if (part.equals("+") || part.equals("-") || part.equals("*") || part.equals("/")) {
				int right = stack.pop();
				int left = stack.pop();
				int ans = 0;
				if (part.equals("+")) {
					ans = left + right;
				} else if (part.equals("-")) {
					ans = left - right;
				} else if (part.equals("*")) {
					ans = left * right;
				} else {
					ans = left / right;
				}
				stack.push(ans);
			} else {
				stack.push(Integer.valueOf(part));
			}
		}
		// stack 只有一个数，最终的结果
		return stack.pop();
	}

	enum Operation {
		SingleNumber, AddOrMinus, MultiplyOrDivide;
	}

	// 请保证输入的逆波兰式是正确的
	// 否则该函数不保证正确性
	// 逆波兰式仅支持+、-、*、/
	// 想支持更多算术运算符自己改
	//例子3，-5， 13， + ， *
	//两个栈：一个栈保存暂时结果，一个保存"加减"或者"乘除"
	// 3，-5，13
	// 单数，如果是乘除，一旦栈中弹出两侧的数left和right中有加减，就加上括号
	public static String convert(String rpn) {
		if (rpn == null || rpn.equals("")) {
			return rpn;
		}
		String[] parts = rpn.split(" ");//切字符串
		Stack<String> stack1 = new Stack<>(); //放表达式
		Stack<Operation> stack2 = new Stack<>(); //放类型
		for (String cur : parts) {
			// cur 当前遇到的字符串
			// 情况1：+-；情况2： */；情况3： 单数
			if (cur.equals("+") || cur.equals("-")) {
				//先弹出left和right两个数
				String b = stack1.pop();
				String a = stack1.pop();
				//对应类型也不要了
				stack2.pop();
				stack2.pop();
				stack1.push(a + cur + b);//生成对应表达式
				stack2.push(Operation.AddOrMinus); //押入对应类型
			} else if (cur.equals("*") || cur.equals("/")) {
				String b = stack1.pop();
				String a = stack1.pop();
				Operation bOp = stack2.pop();
				Operation aOp = stack2.pop();
				String left = aOp == Operation.AddOrMinus ? ("(" + a + ")") : (a); //左侧是加减类型
				String right = bOp == Operation.AddOrMinus ? ("(" + b + ")") : (b); //右侧是否是加减类型
				stack1.push(left + cur + right); //押入表达式
				stack2.push(Operation.MultiplyOrDivide);
			} else { //只是数字，直接压数即可，并告诉第二个栈押入类型
				stack1.push(cur);
				stack2.push(Operation.SingleNumber);
			}
		}
		return stack1.pop();
	}

	public static void main(String[] args) {
		// 3*(-5+13)+6/(2-3+2)-4*5*3
		String rpn = "3 -5 13 + * 6 2 3 - 2 + / + 4 5 3 * * -";
		System.out.println(getAns(rpn));
		System.out.println(convert(rpn));
	}

}
