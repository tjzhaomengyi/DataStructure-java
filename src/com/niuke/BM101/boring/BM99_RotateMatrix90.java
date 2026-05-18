package com.niuke.BM101.boring;


public class BM99_RotateMatrix90 {
/**
 * 将矩阵顺时针旋转90度，左神这道题用的递归，有点麻烦了
 */

    public int[][] rotateMatrix(int n, int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return null;
        // 左上 -> 右上 -> 右下 -> 左下
        // 外围是层数，内层是层的遍历范围
        for(int layer = 0; layer < n / 2; layer++){ //层表示行
            //从左上角开始
            int first = layer; // 每层第一行的开始位置
            int last = n - 1 - layer; //每层第一行的结束位置
            for(int i = first; i < last; i++){ //这里不能遍历到最后一个，应该是最后一个的前一个，最后一个位置留给这一行的第一个元素
                int offset = i - first; //列偏移
                int top_left = matrix[first][i]; //行固定，列游走
                int top_right = matrix[i][last];
                int bottom_left = matrix[last - offset][first];
                int bottom_right = matrix[last][last - offset];
                // top_left = bottom_left
                matrix[first][i] = matrix[last - offset][first];
                // bottom_left = bottom_right
                matrix[last - offset][first] = matrix[last][last - offset];
                // bottom_right = top_right
                matrix[last][last - offset] = matrix[i][last];
                // top_right = top_left
                matrix[i][last] = top_left;
            }
        }
        return matrix;

    }
}

