package com.mikemyzhao.list;

public class LinkedList {
	public Node head; //定义一个头节点
	private int pos = 0;
	
	public LinkedList() {
		this.head = null;
	}
	
	//插入一个头节点
	public void addFirstNode(int data){
		Node node = new Node(data);
		node.next = head;
		head = node;
	}
	
	//在任意位置插入节点,在index后面插入
	public void add(int index,int data){
		Node node = new Node(data);
		Node current = head;
		Node previos = head;
		while (pos != index){
			previos = current;
			current = current.next;
			pos++;
		}
		node.next = current;
		previos.next = node;
		pos = 0;
	}
	//显示出所有节点的信息
	public void displayAllNodes(){
		Node current = head;
		while (current != null){
			current.display();
			current = current.next;
		}
		//System.out.print(" ");
		
	}
	
	//根据数据查找节点信息
	public Node findByData(int data){
		Node current = head;
		while (current.data != data){
			if(current.next == null){
				return null;
			}
			current = current.next;
		}
		return current;
	}
	//根据位置查找节点信息
	public Node findByPost(int index){
		Node current = head;
		if(pos != index){
			current = current.next;
			pos++;
		}
		return current;
	}
	//统计节点个数
	public int getListLength(){
		Node current = head;
		int len = 0;
		if(current != null){
			len = 1;
			while(current.next != null){
				current = current.next;
				len=len+1;
			}
			return len;
		}else{
			return 0;
		}
		
	}
	
	//删除头节点，并返回被删除的头节点
	public Node deleteHeadNode(){
		Node tempNode = head;
		head = tempNode.next;
		return tempNode;
	}
	
	//删除某位置的节点，返回被删除的节点
	public Node deleteByPos(int index){
		Node current = head;
		Node previos = head;
		while(pos != index){
			pos++;
			previos = current;
			current = current.next;
		}
		if(current == head){
			head = head.next;
		}else{
			previos.next = current.next;
			pos = 0;
		}
		return current;
	}
	
	
	public static void main(String[] args){
		LinkedList linkedlist = new LinkedList();
		linkedlist.addFirstNode(0);
		for (int i = 1; i < 10; i++){
			linkedlist.add(i, i);
		}
		linkedlist.displayAllNodes();
		System.out.println();
		Node head = linkedlist.findByPost(0);
		System.out.print("list head is ");
		head.display();
		System.out.println("");
		int listlen = linkedlist.getListLength();
		System.out.println("list length is "+listlen);
		Node delnode = linkedlist.deleteByPos(3);
		System.out.print("Del Node is ");
		delnode.display();
		System.out.println();
		linkedlist.displayAllNodes();
				
	}
}
