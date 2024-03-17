package com.book.zuoshen.InterviewGuide.chpt8.matrix;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/24 19:04
 * @Description:找到以1围成的正方形的最大边长,这道题挺傻逼
 */
public class SquareSurroundByOne {
    //获取所有点的右侧信息和下侧信息
    public void setBorderMap(int[][] m, int[][] right, int[][] down){
        int r = m.length;
        int c = m[0].length;
        if(m[r - 1][c - 1] == 1){
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        //最右一列
        for(int i = r- 2; i >= 0; i--){
            if(m[i][c - 1] == 1){
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        //最底一行
        for(int i = c - 2; i >= 0; i--){
            if(m[r - 1][i] == 1){
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }

        //确定其他位置
        for (int i = r - 2; i >= 0; i--){
            for(int j = c - 2; j >= 0; j--){
                if(m[i][j] == 1){
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }


    //1、先找到每个点的右侧和下侧是否都是连续的1，时间复杂度O(n²),注意每次只看下侧和右侧，size大小是到底或者到最右边距离的最小值
    public boolean hasSizeOfBorder(int size, int[][] right, int[][] down){
        for(int i = 0; i < right.length - size + 1; i++){
            for(int j = 0; j < right[0].length - size + 1; j++){
                //通过三个点判断是否能组成一个围成1的正方形
                if(right[i][j] >= size && down[i][j] >= size
                        && right[i + size - 1][j] >= size && down[i][j + size - 1] >= size){
                    return true;
                }
            }
        }
        return false;
    }

    public int getMaxSizeSquare(int[][] m){
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
        for(int size = Math.min(m.length, m[0].length); size > 0; size--){
            if(hasSizeOfBorder(size, right, down)){
                return size;
            }
        }
        return 0;
    }

}
