package com.mikemyzhao.TreeOperations_2.trees;

import java.util.LinkedList;

/*
 * 缺点这里的方法基本都是Static方法，参数传BinaryTree的头节点，但是BinaryTree的内容没有限制
 */

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTree<T>{
	
	private BinaryTreeNode<T> root;
	
	public BinaryTree(BinaryTreeNode root) {
		setRoot(root);
	}
	
	public boolean isEmpty(){
		if( root == null){
			return true;
		}
		return false;
	}
	
	public BinaryTreeNode<T> getRoot(){
		return this.root;
	}
	
	public void setRoot(BinaryTreeNode root){
		this.root = root;
	}
	
	public  static BinaryTreeNode createTree(){
//		if(root == null){
//			root = new BinaryTreeNode<T>();
//		}else{
//			if(Math.random() > 0.5){ //采用随机方法创建二叉树
//				if(node.leftChild == null){
//					node.leftChild = new BinaryTreeNode<T>(data);
//				}else{
//					createTree(node.leftChild,data);
//				}
//			}else{
//				if(node.rightChild == null){
//					node.rightChild = new BinaryTreeNode<T>(data);
//				}else{
//					createTree(node.rightChild,data);
//				}
//			}
//		}
		//BinaryTreeNode root = new BinaryTreeNode(1);
		//BinaryTree<T> bt = new BinaryTree(root);
		
		BinaryTreeNode head = new BinaryTreeNode(1);
		BinaryTreeNode headLeft = new BinaryTreeNode(2);
		BinaryTreeNode headRight = new BinaryTreeNode(3);
		BinaryTreeNode leftLeft = new BinaryTreeNode(4);
		BinaryTreeNode leftRitht = new BinaryTreeNode(5);
		BinaryTreeNode rightLeft = new BinaryTreeNode(6);
		BinaryTreeNode rightRight = new BinaryTreeNode(7);
		
		head.setLeftChild(headLeft);
		head.setRightChild(headRight);
		
		headLeft.setLeftChild(leftLeft);
		headLeft.setRightChild(leftRitht);
		headRight.setLeftChild(rightLeft);
		return head;
	}
	
	/*
	 * 访问当前节点
	 */
	public static <T> void visit(BinaryTreeNode<T> current){
		if(current != null && current.getData()!=null){
			System.out.println(current.getData());
		}else{
			System.out.println("null");
		}
	}
	/*
	 * 获取二叉树的高度
	 */
	public static int getHeight(BinaryTreeNode root){
		if(root == null){
			return 0;
		}
		return Math.max(getHeight(root.leftChild),getHeight(root.rightChild))+1;
	} 
	
	/*
	 * 广度遍历二叉树,就是一行一行的遍历
	 */
	public static void breadthFirstTraverse(BinaryTreeNode root){
		if(root == null)return;
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		BinaryTreeNode currentNode = null;
		queue.offer(root);
		while(!queue.isEmpty()){
			currentNode = queue.poll();
			System.out.println(currentNode.data + " ");
			if(currentNode.leftChild != null)
				queue.offer(currentNode.leftChild);
			if(currentNode.rightChild != null)
				queue.offer(currentNode.rightChild);
		}
	}
	
	/*
	 * 广度优先遍历二叉树
	 * 
	 */
	public static <T> void levelOrder(BinaryTreeNode<T> root){
		System.out.println("广度优先遍历二叉树");
		Queue<BinaryTreeNode<T>> queue = new LinkedBlockingQueue<BinaryTreeNode<T>>();
		BinaryTreeNode<T> pointer = root;
		
		// 当前节点不为空时，放入队首
		if(pointer != null){
			queue.add(pointer);
		}
		
		//队列不为空时，先访问中间节点，访问完成后弹出队首节点；然后是左节点，再是右节点
		while(!queue.isEmpty()){
			pointer = queue.peek();
			visit(pointer);
			queue.remove();
			if(pointer.leftChild != null){
				queue.add(pointer.leftChild);
			}
			if(pointer.rightChild != null){
				queue.add(pointer.rightChild);
			}
		}
	}
	
	/*
	 *递归实现先序遍历，中左右
	 */
	public void recurseFont(BinaryTreeNode<T> head){
		System.out.println();
		
		if(head == null){
			return;
		}
		System.out.println("当前节点值:"+head.getData());
		recurseFont(head.getLeftChild());
		recurseFont(head.getRightChild());
	}
	
	/*
	 * 递归实现中序遍历，左中右
	 */
	public void recurseMid(BinaryTreeNode<T> head){
		System.out.println();
		if(head == null) {
			return;
		}
		recurseMid(head.getLeftChild());
		System.out.println("当前节点值"+head.getData());
		recurseMid(head.getRightChild());
	}
	
	
	/*
	 * 递归实现后续遍历,左右中
	 */
	public void recurseEnd(BinaryTreeNode<T> head){
		System.out.println();
		if(head == null){
			return;
		}
		recurseEnd(head.getLeftChild());
		recurseEnd(head.getRightChild());
		System.out.println("当前节点为："+head.getData());
	}
	
	/*
	 *非递归实现二叉树先序遍历, 中左右，先把head入栈，再入head的右节点，再入head的左节点，把弹出作为新head
	 */
	
	public static <T> void font(BinaryTreeNode<T> head){
		System.out.println("非递归先序遍历");
		if(head == null)
			return;
		//初始化用于存放节点顺序的栈结构
		Stack<BinaryTreeNode<T>> nodes = new Stack<BinaryTreeNode<T>>();
		//先把head节点放入节点放入栈中，
		nodes.add(head);
		
		while(!nodes.isEmpty()){
			//取出栈顶元素，判断是否有子节点
			BinaryTreeNode<T> temp = nodes.pop();
			System.out.println("当前节点的值"+temp.getData());
			if(temp.getRightChild() != null){
				//先放入右边子节点的原因是先序遍历的话输出的时候是左节点先于右节点输出，而栈的特性决定了要先放右边的节点
				nodes.push(temp.getRightChild());				
			}
			if(temp.getLeftChild() != null){
				nodes.push(temp.getLeftChild());
			}
		}
	}
	
	/*
	 * 非递归实现二叉树中序遍历，左中右，先把head压在栈的底部，然后不停的左左左访问，然后把左节点作为head，然后没有左了开始弹，先左最后弹head，
	 *
	 */
	public static <T> void mid(BinaryTreeNode<T> head){
		System.out.println("非递归中序遍历");
		if( head == null){
			return;
		}else{
			Stack<BinaryTreeNode<T>> nodes = new Stack<BinaryTreeNode<T>>();
			//使用或的方式是因为第一次的时候栈中元素为空，head的非null特性可以保证程序可以执行下去
			while(!nodes.isEmpty() || head!=null){
				//当前节点元素值不为空，则放入栈中，否则输出当前节点的值，然后将头节点变为当前节点的右子节点
				if(head != null){
					nodes.push(head);
					head=head.getLeftChild();
				}else{
					BinaryTreeNode<T> temp = nodes.pop();
					System.out.println("当前节点的值："+temp.getData());
					head = temp.getRightChild();
				}
			}
		}
	}
	
	/*
	 *非递归实现二叉树的后续遍历，左右中，一个栈实现 
	 */
	public static <T> void endWithOneStack(BinaryTreeNode<T> head){
		System.out.println("后序遍历用一个栈");
		if( head == null){
			return;
		}else{
			Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
			stack.push(head);
			//该节点代表已经打印过的节点，待会会及时进行更新
			BinaryTreeNode<T> printedNode = null;
			while(!stack.isEmpty()){
				//获取栈顶的元素的值，而不是pop掉栈顶的值
				head = stack.peek();
				//如果当前栈顶元素的左节点不为空，左右节点均未被打印过，说明该节点是全新的，所以压入栈中
				if(head.getLeftChild() != null && printedNode != head.getLeftChild() && printedNode != head.getRightChild()){
					stack.push(head.getLeftChild());
				}else if(head.getRightChild() != null && printedNode != head.getRightChild()){
					//第一层不满足，则说明该节点的左子树已经被打印过了。如果栈顶元素的右节点未被打印过，则将右节点压入栈中
					stack.push(head.getRightChild());
				}else{
					//上面两种情况均不满足的时候则说明左右子树都被打印过，此时只需要弹栈即可
					System.out.println("当前值为:"+stack.pop().getData());
					//记得实时地更新打印过的节点的值
					printedNode = head;
				}
			}
		}
	}
	
	/*
	 * 非递归实现二叉树的后序遍历，借助两个栈
	 */
	
	public static <T> void endWith2Stack(BinaryTreeNode head){
		System.out.println("后续遍历使用两个栈");
		if(head == null){
			return;
		}else{
			Stack<BinaryTreeNode<T>> stack1 = new Stack<BinaryTreeNode<T>>();
			Stack<BinaryTreeNode<T>> stack2 = new Stack<BinaryTreeNode<T>>();
			
			stack1.push(head);
			//对每个头节点进行判断，先将头节点放入栈2中，然后依次将该节点的子元素放入栈1，顺序为left->>right这是因为后续遍历为左右中
			while(!stack1.isEmpty()){
				head = stack1.pop();
				stack2.push(head);
				if(head.getLeftChild() != null){
					stack1.push(head.getLeftChild());
				}
				
				if(head.getRightChild() != null){
					stack1.push(head.getRightChild());
				}
			}
			
			//直接遍历输出栈2，即可以实现后序遍历的节点值的输出
			while(!stack2.isEmpty()){
				System.out.println("当前节点值" + stack2.pop().getData());
			}
		}
	}
	
	public static void main(String[] args){
		BinaryTreeNode head = createTree();
		font(head);
		mid(head);
		endWithOneStack(head);
		endWith2Stack(head);	
		levelOrder(head);
	}
		
}
