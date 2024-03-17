package com.weeklyproblems.weekly_2022_1_4;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/12 09:23
 * @Description:
 */
public class Code03_CountBoat {
    //判断左上角有没有点即可。。。
    public static int countBouat(char[][] m){
        int ans = 0;
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[0].length; j++){
                if( (m[i][j] == 'X') &&
                        (i == 0 || m[i - 1][j] != 'X') &&
                        (j == 0 || m[i][j - 1] != 'X')){
                    ans++;
                }
            }
        }
        return ans;
    }
}
