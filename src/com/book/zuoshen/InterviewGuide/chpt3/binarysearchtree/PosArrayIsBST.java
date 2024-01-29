package com.book.zuoshen.InterviewGuide.chpt3.binarysearchtree;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 13:05
 * @Description:
 */
public class PosArrayIsBST {
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

    public boolean isPostArray(int[] arr){
        if(arr == null || arr.length == 0){
            return false;
        }
        return isPost(arr, 0, arr.length - 1);
    }

    public boolean isPost(int[] arr, int start, int end){
        if(start == end){
            return true;
        }
        int less = -1;//less是小区的结尾
        int more = end;//more是大区的开头
        for(int i = start; i < end; i++){
            if(arr[end] > arr[i]){
                less = i;
            } else {
                more = more == end ? i : more;//新到的节点和end一样，要更新more的起始点
            }
        }

        //左搜索树或者右搜索树缺了一个,(1)less=-1,[6,7,5] (2)more=end,[1,2,5]
        if(less == -1 || more == end){
            return isPost(arr, start, end - 1);
        }

        if(less != more - 1){
            return false;
        }
        return isPost(arr, start, less) && isPost(arr, more, end - 1);
    }

    //将arr恢复
    public Node posArrayToBST(int[] posArr){
        if(posArr == null){
            return null;
        }
        return posToBst(posArr, 0, posArr.length - 1);
    }

    public Node posToBst(int[] posArr, int start, int end){
        if(start > end){
            return null;
        }
        Node head = new Node(posArr[end]);
        int less = -1;
        int more = end;
        for(int i = start; i < end; i++){
            if(posArr[end] > posArr[i]){
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }
        head.left = posToBst(posArr, start, less);
        head.right = posToBst(posArr, more, end - 1);
        return head;
    }
}
