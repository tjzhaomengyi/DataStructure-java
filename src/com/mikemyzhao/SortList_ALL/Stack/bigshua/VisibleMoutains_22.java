package com.mikemyzhao.HeapAndStrongHeapAndStackAndQueue_0.StackQueue.bigshua;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-05 14:07
 * @Description:可见山峰对问题，一组山峰可以形成环状，
 * 数学结论：如果山峰值不同，一共有2n-3对山峰，
 * (刨除最高和次最高两座山峰可以把两侧分为n-2座山)每侧的每座山肯定有一对，所以是2(n-2),
 * 最后2n-3
 *  思路：这个有点像一个汉诺塔的细节问题，汉诺塔怎么从柱子里面一个个出来
 */
public class VisibleMoutains_22 {
  //这道题考虑的是所有山峰都不同
  //技巧: 先找到这些山峰最大的，原则是：让后面小的都来找大的
  //技巧：流程准备一个小根堆，栈顶肯定是最小的，然后key表示山峰的高度，value表示有几个这样的山峰
  //每次有山峰进来用in表示，cur表示当前堆顶
  //(1)如果 in < cur 就把in加入堆中
  //(2)如果 in >= cur 把cur弹出，生成两对 (in,cur) (cur,次堆顶)，弹出cur，in继续和堆顶进行比较，直到把小的都弹出
  //数学结论：
  //技巧：1因为每个山峰的数量不止一个，所以在栈顶(k>1)弹出计算的时候应该分为两种情况：对内和对外.
  // 1)对内，当前高的山峰选两个 C(k,2) 2)对外是 2 * k。
  //技巧：2当没有入栈的山峰，只看栈内的时候，又分为非次底部弹出和次底部弹出三种情况.
  // 1)非次底部弹出，和遍历时候一样 C(k,2) + 2*k (左右两侧一边一个)
  // 2)次底部弹出：a.如果底部有一个=C(k,2) + k. b.如果底部不止一个 C(K,2) + 2*k 3
  // 3)最底层：C(max,2) 或者 0

  public static class Record{
    public int value;
    public int times;

    public Record(int value){
      this.value = value;
      this.times = 1;
    }
  }

  public static int getVisibleNum(int[] arr){
    if(arr == null || arr.length < 2){
      return 0;
    }
    int N = arr.length;
    int maxIndex = 0;
    //先找到最高的山峰
    for (int i = 0; i < N; i++){
      maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
    }
    Stack<Record> stack = new Stack<>();
    stack.push(new Record(arr[maxIndex]));
    //从最大位置开始沿着遍历
    int index = nextIndex(maxIndex, N);
    int res = 0;
    //技巧：1、当index等于maxIndex表示遍历一圈，结束
    while(index != maxIndex){//保证没有到栈的最底部
      //栈顶一直比cur小，成对往外跳
      //技巧：外围大的一直把小的操出来
      while(stack.peek().value < arr[index]){
        int k = stack.pop().times;
        res += getInternalSum(k) + 2 * k;//看上面数学结论,中间的盘子如果
      }

      //当前数字cur进入栈,技巧：因为是小找大，所以在上面的while循环中把小的都他妈干没了，直到里面只有比cur大和等于自己的
      if (stack.peek().value == arr[index]){
        stack.peek().times++;
      }else{
        stack.push(new Record(arr[index]));
      }
      index = nextIndex(index,N);//下一个
    }

    //技巧：2、这个时候next 等于 maxIndex了转了一圈回来了
    while(stack.size() > 2){
      int times = stack.pop().times;
      res += getInternalSum(times) + 2*times;
    }
    //技巧：3、结算最后两个
    if(stack.size() == 2){
      int times = stack.pop().times;
      res += getInternalSum(times) + (stack.peek().times == 1 ? times : 2 * times);
    }
    res += getInternalSum(stack.pop().times);
    return res;


  }

  public static int nextIndex(int i, int size){
    return (i + 1) % size;
  }

  public static int getInternalSum(int k){
    return k == 1 ? 0: (k * (k - 1) / 2) ;
  }
}
