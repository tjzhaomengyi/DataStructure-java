package com.niuke.BM101.list;

import java.util.HashMap;

public class BM40_RebuildBinTree {

    public class TreeNode {
      int val = 0;
      TreeNode left = null;
      TreeNode right = null;
      public TreeNode(int val) {
        this.val = val;
      }
    }
    private HashMap<Integer, Integer> posIn = new HashMap<Integer, Integer>();
    public TreeNode reBuildBinTree(int[] preOrder, int[] inOrder){
        if(preOrder == null || inOrder == null || preOrder.length == 0){
            return null;
        }
        for(int i = 0; i < inOrder.length; i++){
            posIn.put(inOrder[i], i);
        }
        return process(preOrder, 0, preOrder.length - 1,
                inOrder, 0, inOrder.length - 1);
    }

    private TreeNode process(int[] preOrder, int preStart, int preEnd,
                             int[] inOrder, int inStart, int inEnd){
        if(preStart > preEnd || inStart > inEnd) return null;

        int rootVal = preOrder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int pos = posIn.get(rootVal);
        int size = pos - inStart;
        root.left = process(preOrder, preStart + 1, preStart + size,
                inOrder, inStart, pos - 1);
        root.right = process(preOrder, preStart + size + 1, preEnd, inOrder, pos + 1, inEnd);
        return root;
    }

}
