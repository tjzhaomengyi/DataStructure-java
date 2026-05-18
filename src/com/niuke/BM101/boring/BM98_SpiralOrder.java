package com.niuke.BM101.boring;

import java.util.ArrayList;
import java.util.List;

public class BM98_SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix){
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        while(top <= bottom && left <= right){
            // 上边
            for(int j = left; j <= right; j++){
                res.add(matrix[top][j]);
            }
            top++;

            //右边
            for(int i = top; i <= bottom; i++){
                res.add(matrix[i][right]);
            }
            right--;

            // 注意下面两个if的判断，因为还在while循环中，但是while中的变量大小已经改变了
            //底部
            if(top <= bottom) {
                for (int j = right; j >= left; j--) {
                    res.add(matrix[bottom][j]);
                }
                bottom--;
            }
            if(left <= right) {
                for (int i = bottom; i >= top; i++) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = new BM98_SpiralOrder().spiralOrder(new int[][] {{2,3}});
        System.out.println(res);
    }
}
