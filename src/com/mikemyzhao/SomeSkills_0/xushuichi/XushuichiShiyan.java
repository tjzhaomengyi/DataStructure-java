package com.mikemyzhao.SomeSkills_0.xushuichi;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/27 20:31
 * @Description:蓄水池问题描述，就是让1-N每个数都等概率被抽中
 */
public class XushuichiShiyan {
    //已知一个池子内，这个池子是辅助，从这个池子出来的每个数保证被抽中的概率基本相等,池子大小为i
    public static int random(int i) {
        return (int)(Math.random() * i) + 1;
    }

    //实验：把1729个数字通过这个池子“弹射”出来，看看每个数有多少次被弹射出来
    //结论：每个数次数大致一样
    //实例：等概率设计奖池，所有在今天登录的用户进入奖池进行抽奖100名幸运用户
    // 如果没有蓄水池，需要把登录名单拿到手。如果使用蓄水池，所有登录信息只与一台服务器登录，
    // 在第一次登录的时候可以抽奖，给登录用户排号，以100/i进入奖池，这样用户是等概率
    public static void main(String[] args){
        int test = 10000;
        int[] count = new int[1730]; //第一个数不算
        for(int i = 0; i < test; i++){
            int[] bag = new int[10]; //池子大小为10
            int bagi = 0;
            for(int num = 1; num <= 1729; num++){
                if(num <= 10){ //小于10的直接进袋
                    bag[bagi++] = num;
                } else { //大于10的先随机一下，如果随机数小于等于10替换掉袋子中的数
                    if(random(num) <= 10){
                        bagi = (int)(Math.random() * 10);
                        bag[bagi] = num; //完成替换
                    }
                }
            }
            for(int num : bag){ //统计个数
                count[num]++;
            }
        }
        for(int i = 0; i <= 1729; i++){
            System.out.println(count[i]);
        }
    }

}
