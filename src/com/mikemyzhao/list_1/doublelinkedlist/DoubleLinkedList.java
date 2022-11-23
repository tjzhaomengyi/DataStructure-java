package com.mikemyzhao.list.doublelinkedlist;

public class DoubleLinkedList {
	private Node head= new Node(null);
	private int size;//链表大小
	public DoubleLinkedList(){
		head = new Node(null);
	}
	public boolean addHead(Object o){
		addAfter(new Node(o),head);
		return true;
	}
	
	public boolean addLast(Object o){
		addBefore(new Node(o),head);
		return true;
	}
	
	public boolean add(Object o){
		return addLast(o);
	}
	
	public boolean add(int index, Object o){
		addBefore(new Node(o),getNode(index));
		return true;
	}
	
	public boolean remove(int index){
		removeNode(getNode(index));
		return true;
	}
	
	public boolean removeFirst(){
		removeNode(head.next);
		return true;
	}
	
	public boolean removeLast(){
		removeNode(head.prev);
		return true;
	}
	
	public Object get(int index){
		return getNode(index).value;
	}
	
	public int getSize(){
		return size;
	}
	
	public String toString(){
		StringBuffer s = new StringBuffer("[");
		Node node = head;
		for(int i = 0; i<size; i++){
			node = node.next;
			if(i > 0)
				s.append(" ,");
			s.append(node.value);
		}
		s.append("]");
		return s.toString();
	}
	
	
/**
 * 
 *	实现方法
 * 
 */
	private Node getNode(int index){
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		Node node = head.next;
		for(int i=0; i<index; i++){
			node = node.next;
		}
		return node;
	}
	
	
	private void addAfter(Node newNode,Node node){
		newNode.prev = node;
		newNode.next = node.next;
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;
		size++;
	}
	
	private void addBefore(Node newNode,Node node){
		newNode.next = node;
		newNode.prev = node.prev;
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;
		size++;
	}
	
	private void removeNode(Node node){
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.prev = null;
		node.next = null;
		size--;
	}
	
	public static void main(String[] args){
		DoubleLinkedList dll = new DoubleLinkedList();
		System.out.println(dll.toString());
		for(int i=0; i<10; i++){
			dll.add(i);
		}
		System.out.println(dll.toString());
		dll.addHead("1");
		System.out.println(dll.toString());
		dll.addLast(100);
		System.out.println(dll.toString());
		dll.removeFirst();
		System.out.println(dll.toString());
		dll.removeLast();
		System.out.println(dll.toString());	
	}
	
	
}
