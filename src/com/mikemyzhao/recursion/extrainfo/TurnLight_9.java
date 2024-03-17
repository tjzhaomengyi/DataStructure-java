package com.mikemyzhao.recursion.extrainfo;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-20 17:49
 * @Description:arr只有0和1表示灯灭和灯亮，每次按下arr[i]的开关可以调节i—1、i和i+1灯的状态，求灯无环/有环情况下最少开关多少次可以全点亮
 */
public class TurnLight_9 {

  /****
   * 注意 每次curstatus进行^操作说明
   */
  //无环亮灯递归
  public static int noLoopMinStep(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    if (arr.length == 1) {
      return arr[0] ^ 1;
    }
    if (arr.length == 2) {
      return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
    }
    //从0开始改变0位置的状态
    int p1 = process(arr, 2,arr[0],arr[1]);
    //改变0位置状态
    int p2 = process(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
    if(p2 != Integer.MAX_VALUE){
      p2++;
    }
    return Math.min(p1,p2);
  }

  //递归函数表示当前位置在nextIndex-1，下一位为nextIndex，当前位置【nextIndex-1】状态为curStatus，之前位置【nextIndex-2】状态为curStatus
  //这个递归的隐含语义就是nextIndex-2之前的灯都亮了，现在只考虑nextIndex-2，nextIndex-1和nextIndex三个位置的情况
  public static int process(int[] arr, int nextIndex, int preStatus, int curStatus) {
    if (nextIndex == arr.length) {
      //当前位置是arr.length-1即最后盏灯,如果arr.length-2位置状态和arr.length状态不一样说明没法变了
      return preStatus != curStatus ? (Integer.MAX_VALUE) : (curStatus ^ 1);//秀一下，-2是亮的就是0
    }

    //如果没有到最后一个按钮，就是普通的情况
    //如果i-2位置是灭的情况此时一定要给他点亮，否则过了这个位置后面再也不可能给他点亮了
    if (preStatus == 0) {
      curStatus ^= 1;//因为前面的灯为灭的所以无论如何当前的按钮得拨动一下把前面的灯点亮
      //  index-2   index-1    index  index+1
      // preStatus curStatus
      //
      //记录下一个递归的preStatus = 当前的curStatus
      //下一个递归的curStatus=arr[nextIndex] ^ 1
      int nextCur = arr[nextIndex] ^ 1;
      int next = process(arr, nextIndex + 1, curStatus, nextCur);
      return next == Integer.MAX_VALUE ? next : (next + 1);
    } else{
      return process(arr,nextIndex + 1, curStatus, arr[nextIndex]);
    }
  }

  //有环点亮全灯
  //一圈就行就是只多考虑N-1位置的灯可以改变0位置的灯，还有就是N-2这个特殊位置

  /*****
   *
   * @param arr
   * @param nextIndex:当前位置的下一位置(当前位置是nextIndex-1)
   * @param preStatus：nextIndex-2位置的状态
   * @param curStatus：nextIndex-1位置的状态
   * @param endStatus：N-1位置的状态
   * @param firstStatus：0位置的状态
   * @return
   */
  public static int processLoop(int[] arr,int nextIndex, int preStatus, int curStatus, int endStatus, int firstStatus){
    if(nextIndex == arr.length){
      //来到最后一个按钮
      return(endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (endStatus ^ 1);//只要不一样就不行
    }
    //其他普遍位置
    //当前位置：nextIndex - 1
    //当前状态：curStatus
    //如果不按当前按钮，下一步的preStatus = 当前迭代的curStatus
    //如果按下按钮，下一步的preStatus = 当前迭代的curStatus ^ 1
    //如果不按下按钮，下一步的curStatus = 当前迭代的arr[index]
    //如果按下按钮，下一步的curStatus = 当前迭代的arr[index] ^ 1
    int noNextPreStatus = 0;
    int yesNextPreSatus = 0;
    int noNextCurStatus = 0;
    int yesNextCurStatus = 0;
    //开始时0位置改变和结束位置n-1改变会改变endstatus的状态
    int noEndSatus = 0;
    int yesEndStatus  = 0;
    if(nextIndex < arr.length - 1){
      //普遍位置如何调整
      noNextPreStatus = curStatus;
      yesNextPreSatus = curStatus ^ 1;
      noNextCurStatus = arr[nextIndex];
      yesNextCurStatus = arr[nextIndex] ^ 1;
    } else if(nextIndex == arr.length - 1){//当前来到是n-2的位置，要考虑endstatus是否改变
      noNextPreStatus = curStatus;
      yesNextPreSatus = curStatus ^ 1;
      noNextCurStatus = endStatus;
      yesNextCurStatus = endStatus ^ 1;
      noEndSatus = endStatus;
      yesEndStatus = endStatus ^ 1;
    }
    if(preStatus == 0){
      //这个时候一定要把preStatus点亮,而且要考虑n-2对n-1最后位置的改变
      int next = processLoop(arr, nextIndex + 1, yesNextPreSatus,yesNextCurStatus,
          nextIndex == arr.length - 1 ? yesEndStatus : endStatus,firstStatus);
      return next == Integer.MAX_VALUE ? next : (next + 1);//因为这次点亮了
    }else{
      return processLoop(arr, nextIndex + 1,noNextPreStatus, noNextCurStatus,
          nextIndex == arr.length - 1 ? noEndSatus : endStatus, firstStatus);
    }
  }

  //因为n-1可能会对0位置灯进行改变所以0位置可以是灭的状态，反正等着n-1点亮，所以0和1的位置的灯就有四种状态组合
  // 00,01,10,11=>这个就是对应着在0位置和1位置会有相应的按键动作
  public static int loopMinStep(int[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    if(arr.length == 1){
      return arr[0] == 1 ? 0 : 1;
    }
    if(arr.length == 2){
      return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
    }
    if(arr.length == 3){
      return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
    }
    int last = arr.length-1;
    //0不变，1不变
    int p1 = processLoop(arr, 3,arr[1],arr[2],arr[last],arr[0]);
    //0变，1不变
    int p2 = processLoop(arr,3,arr[1] ^ 1,arr[2],arr[last] ^ 1,arr[0] ^1);
    //0不变，1变
    int p3 = processLoop(arr, 3,arr[1] ^ 1,arr[2] ^ 2,arr[last],arr[0] ^ 1);
    //0变，1变
    int p4 = processLoop(arr, 3, arr[1],arr[2] ^ 1,arr[last] ^ 1,arr[0]);

    p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
    p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
    p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
    return Math.min(Math.min(p1,p2),Math.min(p3,p4));
  }
}
