package com.daybyday.trackintime;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/21 17:14
 * @Description:给定一个数n如23121；给定一组数字a如[2 4 9]求由a中元素组成的小于n的最大数
 */
public class MaxNumber {
    public static int maxNum(int[] arr, int limit){
        Arrays.sort(arr);
        limit--;//让结果追着limit
        int offset = 1;
        //让offset和当前的limit最高位对齐,
        //limit: 54321
        //offset:10000
        while(offset <= limit / 10){
            offset *= 10;
        }
        //递归目标拼出来的数字一定要和limit位数一样长，返回尽量大的数， 如果拼出的数字不行返回-1
        int ans = process(arr, limit, offset);//offset处理当前的数字位
        if(ans != -1){
            return ans;
        } else{ //拿不到最大值
            //如果这位可以就拿出offset把剩下的拼出来
            //
            offset /= 10;
            int rest = 0;
            while(offset > 0){
                rest += arr[arr.length - 1] * offset;
                offset /= 10;
            }
            return rest;
        }
    }

    //可以用arr中的数字小于等于limit的数字尽量大，并且位数和limit一样长,offset标志位index
    // limit = 876530， offset = 1000，说明8和7已经处理过了，8和7一定做的决定是追平，如果当前位小就退出这个递归，做后面else的补充
    public static int process(int[] arr, int limit, int offset){
        if(offset == 0){
            return limit;//每一位都成功追平
        }
        //还有数字要处理,8765430，offset1000
        int cur = (limit / offset) % 10;
        //当前数字已经知道了，去arr中试图追平
        //三种情况：试图追平6：（1）6（2）小于6：4，（3）拿不到返回-1
        int near = near(arr, cur);
        if(near == -1){ //268034 [268]
            return -1;
        } else if(arr[near] == cur){
            //当前位达成
            int ans = process(arr, limit, offset / 10);//下一位
            if(ans != -1){//后续计算出了结果
                return ans;
            } else if(near > 0){
                near--;
                //硬拼
                return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);

            } else {//后续搞不定，当前位无法下降
                return -1;
            }
        } else {
            //26777,268
            return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
        }
    }

    public static int rest(int[] arr, int offset){
        int rest = 0;
        while(offset > 0){
            rest += arr[arr.length - 1] * offset;
            offset /= 10;
        }
        return rest;
    }
    public static int near(int[] arr, int num){
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int near = -1;
        while(l <= r){
            m = (l + r) /2;
            if(arr[m] <= num){
                near = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return near;
    }



    public static int maxNumber(int[] arr, int target){
        int[] ans = new int[1];
        char[] target_chars = String.valueOf(target).toCharArray();
        arr = Arrays.stream(arr).boxed().sorted((a, b) -> b - a).mapToInt(Integer::intValue).toArray();
        dfs(arr, target_chars, 0, 0, 0, ans);
        return ans[0];
    }


    //index是当前处理索引
    public static void dfs(int[] arr, char[] target, int index, int pass, int temp, int[] ans) {
        if (pass == 0 && index == 0) {//第一位一直往下串
            dfs(arr, target, 1, 1, temp, ans);
        }
        if (pass == 1 && index == target.length) {
            ans[0] = Integer.max(ans[0], temp);
            return;
        }
        if (pass == 1) {
            temp += Math.pow(10, (target.length - index - 1)) * arr[0];
            dfs(arr, target, index + 1, 1, temp, ans);
            return;
        }
        if (index == target.length) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == Character.getNumericValue(target[index])) {
                temp += Math.pow(10, (target.length - index - 1)) * arr[i];
                dfs(arr, target, index + 1, 0, temp, ans);
                temp -= Math.pow(10, (target.length - index - 1)) * arr[i];
            } else if (arr[i] < Character.getNumericValue(target[index])) {
                temp += Math.pow(10, (target.length - index - 1)) * arr[i];
                dfs(arr, target, index + 1, 1, temp, ans);
                temp -= Math.pow(10, (target.length - index - 1)) * arr[i];
            }
        }


    }
    public static void main(String[] args) {
        int[] arr = new int[]{4,2,9};
        int target = 4333;
        int ans = maxNumber(arr, target);
        System.out.println(ans);
    }


}
