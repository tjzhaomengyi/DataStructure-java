package com.mikemyzhao.TreeOperations_2.trees;
//用一个Java泛型
public class BinaryTreeNode<T> {
	//数据
	public T data;
	public BinaryTreeNode<T> leftChild;
	public BinaryTreeNode<T> rightChild;
	
	public T getData(){
		return data;
	}
	
	public void setData(T data){
		this.data = data;
	}
	
	BinaryTreeNode() {
		this.data = null;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public BinaryTreeNode(T data){
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}

	public BinaryTreeNode(T data,BinaryTreeNode<T> leftChild,BinaryTreeNode<T> rightChild){
		super();
		this.data=data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(BinaryTreeNode<T> leftChild){
		this.leftChild = leftChild;
	}
	
	public BinaryTreeNode<T> getRightChild(){
		return rightChild;
	}
	
	public void setRightChild(BinaryTreeNode<T> rightChild){
		this.rightChild = rightChild;
	}
	
	public boolean isLeaf(){
		if(this.leftChild == null && this.rightChild == null){
			return true;
		}
		return false;
	}
}
