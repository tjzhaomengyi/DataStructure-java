package com.book.zuoshen.InterviewGuide.chpt4.biaodashi;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/8 16:32
 * @Description: 表示式要获得expire结果的组成方法数,分别记录两张表得到最终结果
 */
public class ExpressExpireWays {
    //验证表达式是否合理（1）长度是奇数（2）偶数位字符是0或1 （3）奇数为是& | 或者 ^
    public boolean isValid(char[] exp){
        if((exp.length & 1) == 0){
            return false;
        }
        for(int i = 0; i < exp.length; i = i + 2){
            if((exp[i] != '1') && (exp[i] != '0')){
                return false;
            }
        }
        for(int i = 1; i < exp.length; i = i + 2){
            if((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')){
                return false;
            }
        }
        return true;
    }

    //一个表达式中有几个逻辑符号就有把相应划分部分的方法相加起来，比如一个逻辑符号的表达式，方法只有1种。两个表达符号p1 + p2
    /**要通过符号结论来完成递归解法
     * 1、划分符号^:
     * (1)desired=true， ways = 左部分为真的种数 * 右部分为假的种数 + 左部分为假的种数 * 右部分为真的种数
     * (2)desired=false，ways = 左部分为真的种数 * 右部分为真的种数 + 左部分为假的种数 * 右部分为假的种数
     * 2、划分符号是&:
     * (1)desired=true, ways = 左侧部分为真的种数 * 右侧部分为真的种数
     * (2)desired=false, ways = 左部分为真的种数 * 右部分为假的种数 + 左部分为假的种数 * 右部分为假的种数 + 左部分为假的种数 * 右部分为真的种数
     * 3、划分符号是|：
     * (1)desired=true, ways = 左部分为真的种数 * 右部分为假的种数 + 左部分为真的种数 * 右部分为真的种数 + 左部分为假的种数 * 右部分为真的种数
     * (2)desired=false, ways = 左部分为假的种数 * 右部分为假的种数
     */
    public int num(String express, boolean desired){
        if(express == null || express.equals("")){
            return 0;
        }
        char[] exp = express.toCharArray();
        if(!isValid(exp)){
            return 0;
        }
        return p(exp, desired, 0, exp.length - 1);
    }

    public int p(char[] exp, boolean desired, int l, int r){
        if(l == r){
            if(exp[l] == '1'){
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }
        int res = 0;
        if(desired){
            for(int i = l + 1; i <= r; i += 2){
                switch (exp[i]){
                    case '&':
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                    case '|':
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                    case '^':
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                }
            }
        } else {
            for(int i = l + 1; i <= r; i += 2){
                switch (exp[i]){
                    case '&':
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                    case '|':
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                    case '^':
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                }
            }
        }
        return res;
    }
    //用t[j][i]表示express[j..i]组成true的方法数，f[j][i]表示express[j..i]组成false的方法数
    public int numDP(String express, int result){
        boolean desired = result == 0 ? false : true;
        if(express == null || express.equals("")){
            return 0;
        }
        char[] exp = express.toCharArray();
        if(!isValid(exp)){
            return 0;
        }
        int[][] t = new int[exp.length][exp.length];
        int[][] f = new int[exp.length][exp.length];
        t[0][0] = exp[0] == '1' ? 1 : 0;
        f[0][0] = exp[0] == '0' ? 1 : 0;
        //填充对角线
        for(int i = 2; i < exp.length; i += 2){
            t[i][i] = exp[i] == '1' ? 1 : 0;//i到i组成自己
            f[i][i] = exp[i] == '0' ? 1 : 0;
        }

        //i是列， j是行
        for(int i = 2; i < exp.length; i += 2){
            for(int j = i - 2; j >= 0; j -= 2){
                for(int k = j; k < i; k += 2){
                    if(exp[k + 1] == '&'){
                        t[j][i] += t[j][k] * t[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * f[k + 2][i] + f[j][k] * t[k + 2][i];
                    } else if(exp[k + 1] == '|'){
                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i];
                    } else {
                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
                    }
                }
            }
        }
        return desired ? t[0][t.length - 1] : f[0][f.length - 1];
    }

    public static void main(String[] args) {
        ExpressExpireWays ways = new ExpressExpireWays();
        System.out.println(ways.numDP("0", 0));
    }
}
