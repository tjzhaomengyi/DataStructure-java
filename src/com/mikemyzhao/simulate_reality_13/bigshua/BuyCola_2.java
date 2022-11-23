package com.mikemyzhao.simulate_reality_13.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-15 8:42
 * @Description:
 *
 * /*
 * 	 * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
 * 	 * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
 * 	 * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
 * 	 * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
 * 	 * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
 * 	 * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
 * 	 * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
 * 	 * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
 * 	 * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
 *
 */
public class BuyCola_2 {
  //解法：从左到右遍历面值，每次买的第一瓶是比较特殊的情况，要看之前剩下的钱和张数
  //m要买可乐的数量，100元a张，50元b张，10元c张，可乐单价x
  public static int putTimes(int m, int a, int b, int c, int x)  {
    int[] qian = {100,50,10};
    int[] zhang = {c,b,a};
    //总共投币puts次
    int puts = 0;
    int preQianRest = 0;//之前面值的钱还剩的总钱数
    int preQianZhang = 0;//之前总钱数还剩的总张数
    for(int i = 0; i < 3 && m != 0; i++){
      //当前面值买一瓶(这里只处理一瓶！)可乐需要用多少张？
      int curQianFirstBuyZhang = (x - preQianRest + qian[i] - 1) / qian[i];//这里要向上取整所以-1；是个技巧
      if(zhang[i] >= curQianFirstBuyZhang){
        //这个面值可以买到当前这一瓶可乐
        //看看找钱情况
        giveRest(qian, zhang, i + 1, (preQianRest + qian[i] * curQianFirstBuyZhang) - x, 1);
        puts += curQianFirstBuyZhang + preQianZhang;
        m--;
      } else {
        preQianRest += qian[i] * zhang[i];
        preQianZhang += zhang[i];
        continue;
      }

      //凑出来第一瓶可乐就可以用当前面值买更多可乐
      int curQianBuyOneColaZhang = (x + qian[i] - 1) / qian[i];
      //用当前的面值可以买多少可乐
      int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, m);
      //用当前面值的前，每买一瓶可乐，收货突出多少零钱
      int oneTimeRest = qian[i] * curQianBuyOneColaZhang - x;
      //每次买一瓶可乐，突出的找零总数是oneTimeRest
      giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
      //当前面值买可乐，一共投币多少次
      puts += curQianBuyOneColaZhang * curQianBuyColas;
      //还剩下多少可乐要买，用后面的前搞定
      m -= curQianBuyColas;
      //当前面值可能剩下若干张，要参与到后续买可乐的过程中，更新
      zhang[i] -= curQianBuyOneColaZhang * curQianBuyColas;
      preQianRest = qian[i] * zhang[i];
      preQianZhang = zhang[i];
    }
  return m==0 ? puts : -1;
  }
  //times投币多少次
  public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times){
    for(; i < 3; i++){
      zhang[i] += (oneTimeRest / qian[i]) * times;//一次的时候这张钱数找多少，乘以次数，就是这张应该总共找多少张
      oneTimeRest %= qian[i];//求余剩下的给下次的前去求找钱数
    }
  }
}
