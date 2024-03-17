package com.book.zuoshen.InterviewGuide.chpt3.binarysearchtree;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 17:25
 * @Description:
 */
public class GenerateBSTBySortArr {
    public class Node {
        int val;
        Node left;
        Node right;


        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public Node generateTree(int[] sortArr){
        if(sortArr == null){
            return null;
        }
        return generate(sortArr, 0, sortArr.length - 1);
    }

    public Node generate(int[] sortArr, int start, int end){
        if(start > end){
            return null;
        }
        int mid = (start + end) / 2;
        Node head = new Node(sortArr[mid]);
        head.left = generate(sortArr, start, mid - 1);
        head.right = generate(sortArr, mid + 1, end);
        return head;
    }
}
