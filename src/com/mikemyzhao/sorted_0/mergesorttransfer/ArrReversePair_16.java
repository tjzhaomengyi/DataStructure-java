package com.mikemyzhao.sorted_0.mergesorttransfer;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 11:17
 * @Description: * 腾讯原题
 * *
 * * 给定整数power，给定一个数组arr，给定一个数组reverse。含义如下：
 * * arr的长度一定是2的power次方，reverse中的每个值一定都在0~power范围。
 * * 例如power = 2, arr = {3, 1, 4, 2}，reverse = {0, 1, 0, 2}
 * * 任何一个在前的数字可以和任何一个在后的数组，构成一对数。可能是升序关系、相等关系或者降序关系。
 * * 比如arr开始时有如下的降序对：(3,1)、(3,2)、(4,2)，一共3个。
 * * 接下来根据reverse对arr进行调整：
 * * reverse[0] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
 * * [3,1,4,2]，此时有3个逆序对。
 * * reverse[1] = 1, 表示在arr中，划分每2(2的1次方)个数一组，然后每个小组内部逆序，那么arr变成
 * * [1,3,2,4]，此时有1个逆序对
 * * reverse[2] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
 * * [1,3,2,4]，此时有1个逆序对。
 * * reverse[3] = 2, 表示在arr中，划分每4(2的2次方)个数一组，然后每个小组内部逆序，那么arr变成
 * * [4,2,3,1]，此时有4个逆序对。
 * * 所以返回[3,1,1,4]，表示每次调整之后的逆序对数量。
 * *
 * * 输入数据状况：
 * * power的范围[0,20]
 * * arr长度范围[1,10的7次方]
 * * reverse长度范围[1,10的6次方]
 */
public class ArrReversePair_16 {
  //todo:每次n个比较是一侧n/2和另一侧n/2比较
  // 1 : 1 = 2个一组比较
  // 2 : 2 = 4个一组比较
  // 4 : 4 = 8个一组比较
  // 先求整体的逆序对，因为归并排序递归从底层到上层都是这样两组两组进行交换，这样交换的结果就是每组逆序对
  // 如果 4个一个比较的会影响两个一组的，但是不会影响8个一组比较的。影响的数据是正序对数量和逆序对数量交换
  public static int[] reversePair2(int[] originArr, int[] reverseArr, int power) {
    /**
     * 1、对原始数组求逆序对数量和正序对数量
     */
    //原始数组，长度是arr^power长度，reverseArr存反转的p指数(p<=power)，
    int[] reverse = copyArr(originArr);//用来记录反转的数组，todo:为了用同样的逆序方法求出正序的数量，其实用公式算也行
    reversePart(reverse, 0, reverse.length - 1);//真正对origin进行字符串反转
    int[] recordDown = new int[power + 1];//记录逆序
    int[] recordUp = new int[power + 1];//记录正序
    process(originArr, 0, originArr.length - 1, power, recordDown);
    process(reverseArr, 0, reverse.length - 1, power, recordUp);

    /***
     * 2、开始处理reverseArr数组中的逆序处理
     */
    int[] ans = new int[reverseArr.length];
    for (int i = 0; i < reverseArr.length; i++) {
      int curPower = reverseArr[i];//在这个大小上进行逆序
      //比如 1 2 3 4 || 5 6 7 8 即power = 2，逆序长度=4;
      //结果 4 3 2 1 || 8 7 6 5 这样一交换 power = 2和1的都受到影响，这个时候把正序对和逆序对的数量交换
      for (int p = 1; p <= curPower; p++) {
        int tmp = recordDown[p];
        recordDown[p] = recordUp[p];
        recordUp[p] = tmp;
      }
      for (int p = 1; p <= power; p++) {
        ans[i] += recordDown[p];//逆序对的数量
      }
    }
    return ans;
  }


  //originArr[L...R]完成排序
  //L...M左  M...R右进行merge
  //L...R是 2^power次方
  //todo：归并排序的递归,record数组记录了交换多少次就是多少个逆序对(merge就是进行逆序对交换的过程)，这里power帮着记录L到R的长度的根号，即L..R的距离=2^power
  public static void process(int[] originArr, int L, int R, int power, int[] record) {
    if (L == R) {
      return;
    }
    int mid = L + ((R - L) >> 1);
    //L...R长度为8，变成L...mid长度为4，所以
    process(originArr, L, mid, power - 1, record);
    process(originArr, mid + 1, R, power - 1, record);
    record[power] += merge(originArr, L, mid, R);

  }

  //对arr的l到r进行归并排序，先给一个辅助数组，把这段排好的先放在这个辅助数组中，最后再填好arr这个区间
  private static int merge(int[] arr, int l, int mid, int r) {
    int[] help = new int[r - l + 1];
    int i = 0;
    int p1 = l;
    int p2 = mid + 1;
    int ans = 0;
    while (p1 <= mid && p2 <= r) {
      //因为mergesort保证两侧的数组本身是有序的，只要交换大体上的数组保证整体有序即可
      ans += arr[p1] > arr[p2] ? (mid - p1 + 1) : 0;//左侧都有序，p1后面的肯定都比p2大
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    while (p1 <= mid) {
      help[i++] = arr[p1++];
    }
    while (p2 <= r) {
      help[i++] = arr[p2++];
    }
    for (i = 0; i < help.length; i++) { //把拍好的这部分放回原来数组中
      arr[l + i] = help[i];
    }
    return ans;
  }


  public static int[] copyArr(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  public static void reversePart(int[] arr, int L, int R) {
    while (L < R) {
      int tmp = arr[L];
      arr[L++] = arr[R];
      arr[R--] = tmp;
    }
  }
}

