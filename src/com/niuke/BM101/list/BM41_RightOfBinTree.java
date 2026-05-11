package com.niuke.BM101.list;

import java.util.ArrayList;
import java.util.HashMap;

public class BM41_RightOfBinTree {

    public class TreeNode {
        int val = 0;
        BM40_RebuildBinTree.TreeNode left = null;
        BM40_RebuildBinTree.TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    private HashMap<Integer, Integer> posInorder = new HashMap<Integer, Integer>();
    private ArrayList<Integer> res = new ArrayList<Integer>();
    public int[] rightSight(int[] preOrder, int[] inOrder){
        if(preOrder == null || inOrder == null || preOrder.length == 0){
            return null;
        }
        for(int i = 0; i < inOrder.length; i++){
           posInorder.put(inOrder[i], i);
        }
        // 在重新构建的过程中统计层树，当前层数应该正好和res中的数量相同，当统计到第一层的时候，res中的元素个数也是为0的。
        rebuild(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1, 0);
        int[] tmp = new int[res.size()];
        for(int i = 0; i < tmp.length; i++){
            tmp[i] = res.get(i);
        }
        return tmp;
    }

    private void rebuild(int[] preOrder, int preS, int preE,
                        int[] inOrder, int inS, int inE,
                        int depth){
        if(preS > preE || inS > inE) return ;
        int rootVal = preOrder[preS];
        if(res.size() == depth){
            res.add(rootVal);
        }
        int pos = posInorder.get(rootVal);
        int size = pos - inS;
        //让右子树先进
        rebuild(preOrder, preS + 1, preS + size, inOrder, inS, pos - 1, depth + 1);
        rebuild(preOrder, preS + size + 1, preE, inOrder, pos + 1, inE, depth + 1);
        
    }
}
