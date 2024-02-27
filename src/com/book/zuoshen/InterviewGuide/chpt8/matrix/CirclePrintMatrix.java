package com.book.zuoshen.InterviewGuide.chpt8.matrix;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/21 14:13
 * @Description:旋转打印矩阵
 */
public class CirclePrintMatrix {
    public void spiralOrderPrint(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while(tR <= dR && tC <= dC){
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public void printEdge(int[][] m, int tR, int tC, int dR, int dC){
        //子矩阵只有一行
        if(tR == dR){
            for(int i = tC; i <= dC; i++){
                System.out.println(m[tR][i] + " ");
            }
        } else if(tC == dC){ //子矩阵只有一列
            for(int i = tR; i <= dR; i++){
                System.out.print(m[i][tC] + " ");
            }
        } else { //一般情况
            int curC = tC;
            int curR = tR;
            while(curC != dC){
                System.out.print(m[tR][curC] + " ");
                curC++;
            }
            while(curR != dR){
                System.out.print(m[curR][dC] + " ");
                curR++;
            }
            while(curC != tC){
                System.out.print(m[dR][curC] + " ");
                curC--;
            }
            while(curR != tR){
                System.out.print(m[curR][tC] + " ");
                curR--;
            }
        }
    }
}
