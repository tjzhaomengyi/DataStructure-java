package com.book.zuoshen.InterviewGuide.chpt3.showtree;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/30 21:13
 * @Description:
 */
public class GenearatePosArrByPreAndInOrder {

    public static int[] getPosArray(int[] pre, int[] in){
        if(pre == null || in == null){
            return null;
        }
        int len = pre.length;
        int[] pos = new int[len];
        //存储每次访问的“头结点”在中序遍历的位置
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < len; i++){
            map.put(in[i], i);
        }
        setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;
    }

    /**
     *     从右往左依次填好s，si为后续数组
     * @param p 前序数组
     * @param pi 前序起始位
     * @param pj 前序结束位
     * @param n 中序数组
     * @param ni 中序起始位
     * @param nj 中序结束位
     * @param s 后续数组
     * @param si 后续数组填入位
     * @param map 中序位值对应下标字典
     * @return
     */
    public static int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s, int si, HashMap<Integer, Integer> map){
        if(pi > pj){
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map); //右子树
        return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map );//左子树
    }
}
