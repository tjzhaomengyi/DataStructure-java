package com.book.zuoshen.InterviewGuide.chpt8.matrix;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/23 10:34
 * @Description:
 */
public class FindNumInSortedMatrix {
    public boolean isContains(int[][] matrix, int K){
        int row = 0;
        int col = matrix[0].length - 1;
        while(row < matrix.length && col >= 0){ // 从右上角开始，下面的大，左边的小
            if(matrix[row][col] == K){
                return true;
            } else if(matrix[row][col] > K){
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
