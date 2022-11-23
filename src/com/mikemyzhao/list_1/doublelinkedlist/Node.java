package com.mikemyzhao.list_1.doublelinkedlist;

public class Node {
	Object value;
	Node prev = this;
	Node next = this;
	Node(Object v){
		value = v;
	}
	
	public String toString(){
		return value.toString();
	}
	
}
