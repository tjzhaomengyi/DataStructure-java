package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/16 11:26
 * @Description: 将英语句子进行反转，每个单词不变，两种翻转方式，这道题目的就是为了使用第二种方法引出更难的完美洗牌问题
 */
public class ReversString {
    //方法1，整体翻转+内部翻转，给定一个字符数组
    public void rotateWord(char[] chars){
        if(chars == null || chars.length == 0){
            return;
        }
        reverse(chars, 0, chars.length - 1);//整体先反转
        int l = -1;
        int r = -1;
        for(int i = 0; i < chars.length; i++){
            if(chars[i] != ' '){
                l = i == 0 || chars[i - 1] == ' ' ?  i : 1;
                r = i == chars.length - 1 || chars[i + 1] == ' ' ? i : r;
            }
            if(l != -1 && r != -1){
                reverse(chars, l, r);//反转内部字符串
                l = -1;
                r = -1;
            }
        }
    }

    public void reverse(char[] chars, int start, int end){
        char tmp = 0;
        while(start < end){
            tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
    }

    //方法二，使用完美洗牌的方法进行字符串反转，不太好理解
    // 具体方法如下：将交换部分分成左右两组，以短的长度作为交换长度，如果左右部分长度相等结束交换
    //例子：1234567ABCD，size=7，（1）ABCD5671234 （2）ABCD固定，start到5，5671234对这部分进行交换，以d=lpart-rpart作为长度进行交换
    //(3)后面一次类推
    public void rotate2(char[] chars, int size){
        if(chars == null || size <= 0 || size >= chars.length){
            return;
        }
        int start = 0;
        int end = chars.length - 1;
        int lpart = size; //左部分长度
        int rpart = chars.length - size; //右侧部分长度
        int s = Math.min(lpart, rpart); //找到最小的部分作为交换长度
        int d = lpart - rpart; //左右长度的差
        while(true){
            exchange(chars, start, end, s);//以小的长度进行交换
            if(d == 0){
                break;
            } else if(d > 0){ //起始部分大于另一侧的交换部分能，要把左右剩余部分也交换一下
                start += s;//左部分长度大于右部分，移动左部分的起始位置
                lpart = d; //修改交换长度
            } else { //右侧大于左侧
                end -= s;//修改右侧部分的移动开始位置
                rpart = -d; //修改右侧的移动长度，因为交换的右侧的多余的部分，
            }
            s = Math.min(lpart, rpart);
            d = lpart - rpart;
        }
    }

    /**
     * 在start到end区间中，交换[start,start + size -1] 和 [end - size + 1, end]这两部分字符串
     * @param chars
     * @param start
     * @param end
     * @param size
     */
    public void exchange(char[] chars, int start, int end, int size){
        int i = end - size + 1; //交换后部分的起始位置
        char tmp = 0;
        while(size-- != 0){
            tmp = chars[start];
            chars[start] = chars[i];
            chars[i] = tmp;
            start++;
            i++;
        }
    }
}
