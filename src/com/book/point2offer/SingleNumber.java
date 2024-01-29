package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 17:01
 * @Description:
 */
public class SingleNumber {
  /**思路：对于这个数组整体取异或，最后得到的就是只出现1次的数字，因为如果出现两次都化成了0
   * 注意：0异或上任何一个数等于这个数！！！！**/
  public int[] singleNumbers(int[] nums) {
    //1、将数组整体取异或，得到两个只出现1次的数的异或结果
    int res =0;
    for(int num:nums){
      res= res ^num;//最终得到x^y的结果
    }
    /**假设最后的结果时10和2**/
    //根据异或的性质我们知道：res中至少有1位为1，否则x和y就相等了。
    //我们通过一个辅助变量m来保存z中哪一位为1.可能有多个位为1，我们找到最低位即可.
    //例如：10^2=1010^0010=1000,第四位是1
    //我们将m初始化为1，如果z&m结果等于0，说明z的最低位时0.
    //我们每次将m左移以为，然后和z做&操作，直到结果不为0
    //此时m的二进制表示应该是1000，即10
    int m=1;
    while((res&m)==0) m=m<<1;

    //遍历数组，将每个数和m进行&操作，结果为0的作为一组，结果不为0的作为一组
    //例如对于数组[1,2,10,4,1,4,3,3]，我们可以把每个数字和二进制1000进行比较分为两组：
    //nums1[1,2,4,1,4,3,3],nums2存放结果为1的是[10]，但是如果元素组匹配的值更大就不会这么快乐。
    //此时我们发现问题已经退化成了数组中只有一个数字出现了一次
    //分别对nums1和nums2遍历异或就能找到我们预期的x和y
    int x=0,y=0;
    for(int i:nums){
      //通过if else将nums分成两组，一遍遍历，一遍异或
      if((i&m)==0) x^=i;
      else y^=i;
    }
    return new int[]{x,y};
  }

  //只有一个数字出现一次，其他都出现了三次，出现三次的数字的位数和是3的倍数，然后每位1求和取余再拼接，就是最后出现1次的数字
  public int singleNumber(int[] nums) {
    int[] counts = new int[32];
    for(int num:nums){
      for(int j=0;j<32;j++){
        counts[j]+=num&1;
        num=num>>1;
      }
    }
    int res = 0;
    for(int i=0;i<32;i++){
      res= res <<1;
      res = res|counts[31-i]%3;//技巧，每位先移动，再拼接成数字
    }
    return res;
  }
  public static void main(String[] args) {
    new SingleNumber().singleNumbers(new int[]{1,1,13,2});
  }
}
