package com.book.zuoshen.InterviewGuide.chpt3.binarytreeUnRecur;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/19 20:22
 * @Description:技巧：这个中序遍历有点恶心不好想这个进栈，就是左边全塞进去后，右边要等到中间出来，然后让右侧进入
 */
public class InorderUnRecur {
    public class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }
    //步骤依次打印左子树，然后打印右子树即可
    public void inOrderUnRecur(Node head) {
        System.out.print("in-order:");
        if(head != null){
            Stack<Node> stack = new Stack<Node>();
            //技巧这里的stack不为空为了让栈不断弹出，head!=null就是让栈一直放入head.left，head就是cur节点
            while(!stack.isEmpty() || head != null){
                if(head != null){
                    //当前遍历的节点不为空
                    stack.push(head);
                    head = head.left;//一直左到底
                } else {
                    head = stack.pop();//一旦出栈，就要让他的右侧进栈
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
    }
}
