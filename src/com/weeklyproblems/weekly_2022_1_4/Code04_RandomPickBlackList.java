package com.weeklyproblems.weekly_2022_1_4;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/12 09:27
 * @Description:在一个大范围上等概率输出一个数字，黑名单范围10万，尽量少使用random()函数
 */
public class Code04_RandomPickBlackList {
    //把黑名单的数字用范围中的最后几个数字替换进去[3,17,29,56]，要求从0到99进行随机，如果roll到了3，就硬性让99代替3，roll了17，让98代替；
    // 为了等概率，我们只roll0到99

    class Solution{
        private int size;//0-99都可以随机，可能有若干个黑名单数,size表示调整后的大小，从0到size-1
        private HashMap<Integer, Integer> convert = new HashMap<>();
        //[1,2,3,4,5,6](0-9) [9,8,7,],替换的不够用了
        //[0~100)范围上，黑名单【13，17，23，98】，先在黑明单的最右侧立一个R指针，只要被替换的数是R指针指向的数，就让倒退范围--，然后R--
        // 13->99,17->98,不对了R指针--一下变成97，17->97
        public Solution(int n, int[] blackArray){
            Arrays.sort(blackArray);
            int m = blackArray.length;
            //[1,2,3,4,5,6](0-9) [9,8,7,],替换的不够用了,blackArray[i]<n感觉这个条件可以不加,
            for(int i = 0; i < m ; i++){ //省略中间条件&& blackArray[i] < n
                //因为上来不包括右区间，所以上来先n--
                for(n--; n > blackArray[i]; n--){ //保证n右边界一直大于黑名单
                    if(n == blackArray[m - 1]){//正好是可以作废掉的数不要了
                        m--;//把黑名单缩小
                    } else {
                        convert.put(blackArray[i], n);
                        break;
                    }
                }

            }
            size = n;//把size调整成修正完的
        }

        public int pick(){
            int ans = (int)(Math.random() * size);
            return convert.containsKey(ans) ? convert.get(ans) : ans;//使用convert替换
        }
    }
}
