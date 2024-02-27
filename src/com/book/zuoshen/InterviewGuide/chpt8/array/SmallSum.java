package com.book.zuoshen.InterviewGuide.chpt8.array;


/**
 * @Author: zhaomengyi
 * @Date: 2024/2/23 19:51
 * @Description:归并排序的繁衍题
 */
public class SmallSum {
    public int getSmallSum(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        return func(arr, 0, arr.length - 1);
    }

    //让l到r排好序，并得到小和数量
    public int func(int[] s, int l, int r){
        if(l == r){ //只有一个数，没有小和
            return 0;
        }
        int mid = (l + r) / 2;
        return func(s, l, mid) + func(s, mid + 1, r) + merge(s, l, mid, r);
    }
    //在归并排序中只需要添加三个句子既可以完成小和问题
    public int merge(int[] s, int l, int m, int r){
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int res = 0;//小和特殊语句（1）
        while(p1 <= m && p2 <= r){
            //小和特殊语句（2）
            res += s[p1] < s[p2] ? (r - p2 + 1) * s[p1] : 0; //左侧的数小于右侧的数， 这样右侧的数就有(r - p2 + 1）个s[p1]小于这些有序的右侧部分
            help[i++] = s[p1] < s[p2] ? s[p1++] : s[p2++]; //正常的规定排序
        }
        while(p1 <= m){
            help[i++] = s[p1++];
        }
        while(p2 <= r){
            help[i++] = s[p2++];
        }
        for(i = 0; i < help.length; i++){
            s[l + i] = help[i];//归并排序把help数组内容还原到s数组中
        }
        return res;//小和特殊语句(3)
    }
}
