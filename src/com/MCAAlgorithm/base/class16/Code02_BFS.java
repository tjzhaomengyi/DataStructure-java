package com.MCAAlgorithm.base.class16;

import java.util.HashSet;
import java.util.Stack;

public class Code02_BFS {

	//注意：这种栈的惯性思路更多的是解决DFS问题，BFS第一反应还是使用Queue
	public static void bfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>();
		stack.add(node);
		set.add(node);
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
					break;
				}
			}
		}
	}
	
	
	

}
