package com.book.zuoshen.InterviewGuide.chpt8.matrix;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/21 20:40
 * @Description:把矩阵顺时针旋转90度
 */
public class MatrixRotate90 {
    public void rotate(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while(tR < dR){
            roateEdage(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public void roateEdage(int[][] m, int tR, int tC, int dR, int dC){
        int times = dC - tC;
        int tmp = 0;
        for(int i = 0; i != times; i++){ //一次循环就是一组占据调整
            tmp = m[tR][tC + i];
            m[tR][tC + i] = m[dR - i][tC];
            m[dR - i][tC] = m[dR][dC - i];
            m[dR][dC - i] = m[tR + i][dC];
            m[tR + i][dC] = tmp;

        }
    }
}
