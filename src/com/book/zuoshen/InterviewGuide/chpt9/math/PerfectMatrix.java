package com.book.zuoshen.InterviewGuide.chpt9.math;

import java.util.HashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 16:04
 * @Description:几个矩形类似拼图，完美无缝拼成一个大的矩形
 */
public class PerfectMatrix {
    //结论：去顶四个定点，然后除了四个顶点，其余的点必须出现偶数次，且面积和是大面积
    public boolean isPerfectMatrix(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int mostLeft = Integer.MAX_VALUE;
        int mostRight = Integer.MIN_VALUE;
        int mostDown = Integer.MAX_VALUE;
        int mostUp = Integer.MIN_VALUE;
        HashSet<String> set = new HashSet<String>();
        int area = 0;
        for(int[] rect : matrix){
            mostLeft = Math.min(rect[0], mostLeft);
            mostDown = Math.min(rect[1], mostDown);
            mostRight = Math.max(rect[2], mostRight);
            mostUp = Math.max(rect[3], mostUp);
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
            String leftDown = rect[0] + "_" + rect[1];
            String leftUp = rect[0] + "_" + rect[3];
            String rightDown = rect[2] + "_" + rect[1];
            String rightUp = rect[2] + "_" + rect[3];
            //下面的操作就是来回增加和删除，如果没有添加成功说明set里面已经有了，直接删除掉
            if(!set.add(leftDown)){
                set.remove(leftDown);
            }
            if(!set.add(leftUp)){
                set.remove(leftUp);
            }
            if(!set.add(rightUp)){
                set.remove(rightUp);
            }
            if(!set.add(rightDown)){
                set.remove(rightDown);
            }
        }
        if(!set.contains(mostLeft + "_" + mostDown) || !set.contains(mostLeft + "_" + mostUp)
            || !set.contains(mostRight + "_" + mostDown) || !set.contains(mostRight + "_" + mostUp) || set.size()!= 4){
            return false;
        }
        return area == (mostRight - mostLeft) * (mostUp - mostDown);
    }
}
