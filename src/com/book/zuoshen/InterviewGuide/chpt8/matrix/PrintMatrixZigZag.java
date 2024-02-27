package com.book.zuoshen.InterviewGuide.chpt8.matrix;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/21 20:53
 * @Description:
 */
public class PrintMatrixZigZag {
    public void printMatrixZigZag(int[][] matrix){
        //有两个点AB都在左上角，让后每次A往右走，B往下走，每次两人一起走路
        int tR = 0; //A的row坐标
        int tC = 0; //A的col坐标
        int dR = 0; //B的row坐标
        int dC = 0; //B的col坐标
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while(tR != endR + 1){
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR; //向右走的A走到右侧头了，开始往下走一下
            tC = tC == endC ? tC : tC + 1; // 向右走的A如果没有到头，tC向右移动，如果已经到头了tC不要动了
            dC = dR == endR ? dC + 1 : dC; // 向下走的B如果到头了，dC向右移动，否则不动
            dR = dR == endR ? dR : dR + 1; //向下走的B如果到头了，就不要动了，如果没到头就向下移动
            fromUp = !fromUp;
        }
        System.out.println();
    }

    //下面函数中的++操作不会影响四个参数的，就是函数内部自++，参数本身不会改变
    public void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f){
        if(f){
            while(tR != dR + 1){
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while(dR != tR - 1){
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }
}
