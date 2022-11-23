package com.mikemyzhao.simulate_reality_13.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-04 9:02
 * @最操你妈的题，操你妈没够！
 * @Description:洗牌问题:将[L1,L2,L3,L4,L5,L6,R1,R2,R3,R4,R5,R6]洗成固定格式
 * [R1,L1,R2,L2,R3,L3,R4,L4,R5,L5,R6,L6],已知牌的个数是偶数个,
 * 不得不说这道题是空间压缩的极致的一道题，O(1)啊，返回的数组不能算额外空间，哎！！！
 */
public class ShuffleCards_20 {
  public static void shuffle(int[] arr){
    if(arr != null && arr.length != 0 && (arr.length & 1) == 0){
      shuffle(arr, 0, arr.length - 1);
    }
  }

  //在arr[L...R]上做洗牌调整，L到R默认是偶数个数字
  //数学结论：当数组长度为 3^k-1 的时候，可以通过计算得到交换的下标起始点，分别为3^i(i<k)
  public static void shuffle(int[] arr, int L, int R){
    while(R - L + 1 > 0){
      int len = R - L + 1;
      int base = 3;
      int k = 1;
      //数学结论：使用
      //技巧：平方往上垒
      while( 3 * base <= len + 1){
        base *= 3;
        k++;
      }
      //已经找到3^k-1的长度,选块的时候要
      int ruleLen = base - 1;
      int halfRuleLen = ruleLen / 2;
      int mid = (L + R) / 2;//以中间为基准进行旋转
      rotate(arr, L + halfRuleLen, mid, mid + halfRuleLen);
      //数学结论：使用
      //技巧：下标连环怼，以当前L为起始点ruleLen长度，找到对应环路下比较，进行节点计算交换位置省去申请空间的额外开销
      cycles(arr, L, ruleLen , k);

      //runlen-1部分解决了
      L = L + ruleLen;

    }
  }

  //技巧：旋转函数,[L..M]和[M+1 ...R],使用三逆序进行旋转
  public static void rotate(int[] arr, int L, int M, int R){
      //先reverse两侧，再整体reverse
      reverse(arr, L, M);
      reverse(arr, M + 1, R);
      reverse(arr, L, R);
  }
  //技巧：在arr中的[L..R]进行逆序
  public static void reverse(int[] arr, int L, int R){
    while(L < R){
      int tmp = arr[L];
      arr[L++] = arr[R];
      arr[R--] = tmp;
    }
  }

  //技巧：如何下标连环怼
  public static void cycles(int[] arr, int start, int len, int k){
    //找到每个位触发位置的trigger,一共k个0到k-1
    for (int i = 0, trigger = 1; i < k; i++, trigger *= 3){//技巧：这里trigger的位置为1,3,9，所以对应数组下标要相应减1，然后start相当于一个平移位置
      int preValue = arr[trigger + start -1];//技巧：永远记着上一个被怼出来的值！！！，不能忘
      int cur = caoNiMa(trigger,len);//数学结论：要怼的理论位置，实际位置是cur-1+start
      while(cur != trigger){//3-6这个位置不在下面中，所以俩人算了一个都会被卡进来，真是一步一步卡点
        int tmp = arr[cur + start - 1];//数学结论：找到要怼的位置
        arr[cur + start - 1] = preValue;//数学结论：要怼的位置变成prevalue之前的值
        preValue = tmp;//数学结论：怼下来的值，放在tmp
        cur = caoNiMa(cur, len);//技巧：我草！我草！我草草偶次哦啊哦！！！
      }
      arr[cur + start - 1] = preValue;
    }
  }

  //数学结论：使用根据len=3^k-1的公式推出如何用公式推的方法，得到自己要的下标
  //数学结论：操你妈的竟然是：2*cur % (len+1)我操你妈！我操你妈！我草你妈！！！！
  //例子：L1 L2 L3 L4 R1 R2 R3 R4 L5 L6 R5 R6
  //      1  2  3  4  5  6  7  8  9 10 11 12
  // 1->2->4->8->7->5-->>>>1 , 3->6->3
  public static int caoNiMa(int cur, int len){
    return 2*cur % (len + 1);
  }
}
