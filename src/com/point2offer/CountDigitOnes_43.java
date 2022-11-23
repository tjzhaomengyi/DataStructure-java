package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 16:19
 * @Description:1-n整数中1出现的次数,https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/dong-hua-mo-ni-wo-tai-xi-huan-zhe-ge-ti-vxzwc/
 * @Solve:先整理思路：1、我们要找到每位出现1的次数，cur表示当前的数位，因子base表示当前数位的位因子，比如个位num=1，十位num=10，百位num=100；
 * 2、将cur位的左边定义为高位high，右边定义为低位low。
 * 3、当取到某一个位上，我们可以把该为数的数值分为三种情况：(1)<1，即0，(2)=1,(3)>1,举三个例子说明这三种情况:1004、1014和1024
 *  (1)1004：计算小于等于1004中，十位出现1的次数。也就是当前位为十位，数字位为0，十位出现1的次数。小于1004中最小包含十位的是10，最大是919，
 *  先给结论：1004中十位出现1的次数=高位*num=10*10=100。原因：高位=10，可变范围0到10；低位只有1位，并不能得到1.
 *  (2)1014：计算1014中十位上出现1的次数：数字位=1，num=10，1到1014中十位为1的最小值为10，最大值为1014。
 *    进一步：我们可以将1014拆成0-1004和1005-1014这两部分，0-1004我们在(1)中得出=100；1005-1014这部分就是低位部分(个位)+1;
 *    结论：次数=高位*num+低位+1
 *  (3)1024：也可以将1024分为0到1004和1005到1024这两部分，在1005到1024这部分，十位为1的情况最小值是1010，最大情况时1019，
 *    结论：次数=高位*num+num=10*10+10
 *
 *  4、初始化low=0,cur=n%10,num=1,count=0,high=n/10;
 *
 */
public class CountDigitOnes_43 {
  public int countDigitOne(int n) {
    int high = n;//高位
    int low =0;//低位
    int cur =0;//当前位
    int count=0;
    int num=1;//数位值
    while(high!=0||cur!=0){
      cur=high%10;
      high=high/10;
      //这里我们可以提取high*num，因为无论为几，都有这个
      if(cur==0){
        count=count+high*num;//3-1
      }else if(cur==1){
        count=count+high*num+1+low;//3-2
      }else if(cur>1){
        count = count + high*num + num;//3-3
      }
      low = cur*num+low;
      num=num*10;
    }
    return count;
  }
}
