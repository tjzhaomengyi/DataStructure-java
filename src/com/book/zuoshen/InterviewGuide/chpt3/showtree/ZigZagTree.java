package com.book.zuoshen.InterviewGuide.chpt3.showtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 10:45
 * @Description:
 */
public class ZigZagTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

        public List<List<Integer>> zigZagLevelOrder(TreeNode root){
            List<List<Integer>> ans = new ArrayList<>();
            if(root == null){
                return ans;
            }
            LinkedList<TreeNode> dq = new LinkedList<>();
            dq.add(root);
            int size = 0;
            boolean isHeadPool = true;
            while(!dq.isEmpty()){
                size = dq.size();//记录queue中当前层的大小
                List<Integer> curLevelAns = new ArrayList<>();
                for(int i = 0; i < size; i++){
                    TreeNode cur = isHeadPool ? dq.pollFirst() : dq.pollLast(); //从左到右打印，或者从右到左打印
                    curLevelAns.add(cur.val);
                    if(isHeadPool){ //当前是从左往右打印，下层是从右往左打印，左节点和右节点从底部依次进入
                        if(cur.left != null){
                            dq.addLast(cur.left);
                        }
                        if(cur.right != null){
                            dq.addLast(cur.right);
                        }
                    } else {//当前是从右往左打印，下层是从左往右打印
                        if(cur.left != null){
                            dq.addFirst(cur.left);
                        }
                        if(cur.right != null){
                            dq.addFirst(cur.right);
                        }
                    }
                }
                ans.add(curLevelAns);
                isHeadPool = !isHeadPool;
            }
            return ans;
        }
}
