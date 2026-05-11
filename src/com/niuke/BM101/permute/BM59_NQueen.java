package com.niuke.BM101.permute;

public class BM59_NQueen {
    public int nQueen(int n){
        if(n < 1) return 0;
        int[] record = new int[n]; //保存每一行的旗子放在哪列
        return process(record, 0, n); //从0到n，如果越界了，说明正好都放好了
    }

    public int process(int[] record, int i, int n){
        if(i == n) return 1; // 所有的record的都放好了，就放进去
        int res = 0;
        //第i行放在j列
        for(int j = 0; j < n; j++){
            if(isValid(record, i, j)){
                record[i] = j;
                res += process(record, i + 1, n);
            }
        }
        return res;
    }

    public boolean isValid(int[] record, int i, int j){
        for(int k = 0; k < i; k++){
            if(record[k] == j || Math.abs(i - k) == Math.abs(record[k] - j)){
                return false;
            }
        }
        return true;
    }


}
