package com.hotinterview.Nanti;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-27 7:24 下午
 * @Description: 荷兰国旗 + 完美洗牌
 */
public class Code0324_WiggleSort {
  //太难了~直接记住例子吧
  //思路：先使用 荷兰国旗 的方法找到2/N大的数对数组进行分半，然后使用完美洗牌形成 "大小大小...."之后反转得到wigglesort结果
  public void wiggleSort(int[] nums) {
    if(nums == null || nums.length == 0) return;
    int N = nums.length;
    findKthIndex(nums, 0 , N - 1, N/2);//找到中间值，借助荷兰国旗生成的半截数组，再shuffle
    if((N & 1) == 0){
      shuffle(nums, 0, nums.length - 1);
      reverse(nums, 0, nums.length - 1);
    } else {
      shuffle(nums, 1, nums.length - 1);
    }

  }

  public int findKthIndex(int[] nums, int L, int R, int target_index){
    int pivot = 0;
    int[] range = null;
    while(L < R){
      pivot = nums[L + (int)(Math.random() * (R - L + 1))];//随机出一个中心位置，然后不断调整，O(N)
      range = netherland(nums, 0, nums.length - 1, pivot);
      if(target_index >= range[0] && target_index <= range[1]){
        return nums[target_index];
      } else if(target_index < range[0]){
        R = range[0] - 1;
      } else {
        L = range[1] + 1;
      }
    }
    return nums[L];
  }

  public int[] netherland(int[] nums, int L, int R, int pivot){
    int less = L - 1;
    int more = R + 1;
    int cur = L;
    while (cur < more){
      if(nums[cur] < pivot){
        swap(nums, ++less, cur++);
      } else if (nums[cur] > pivot){
        swap(nums, --more, cur);//这里不要动，因为是从后面过来的要比较一次
      } else{
        cur++;
      }
    }
    return new int[]{less + 1, more - 1};//返回pivot的起始位置
  }

  public void swap(int[] nums, int i, int j){
    int tmp = nums[j];
    nums[j] = nums[i];
    nums[i] = tmp;
  }



  public void reverse(int[] nums, int L, int R){
    while (L < R){
      swap(nums, L++, R--);
    }
  }
  //完美洗牌，[L1，L2，L3，M，M，R1，R2，R3] -> [R3,]
  //思路：完美洗牌要使用三逆序问题 【a，b，c，e,f】->[e,f,a,b,c]
  //数学结论：里面在计算循环怼环大小的时候使用了结论 N只有在等于3^k-1才能循环，但是如果
  // N的长度不等于这个值，那么就要从最接近的开始拆，比如N=12
  // L1 L2 L3 L4 L5 L6 | R1 R2 R3 R4 R5 R6 (3^2-1=8)
  // L1 L2 L3 L4 | R1 R2 R3 R4 | L5 L6 R5 R6 （先处理前面两段，再处理后面一段）
  //再L到R上进行调整,L到R一定是偶数个数字
  public void shuffle(int[] nums, int l, int r){
    while(r - l + 1 > 1){//如果l到r的长度一直大于1即可，k=1时候满足两两交换
      //太难了~直接记住例子吧 围绕着公式构建以下代码len = 3^k-1,且k>1保证两两交换即可
      int lenAndOne = r - l + 2;
      int bloom = 3;//我要开花
      int k = 1;
      while( bloom <= lenAndOne / 3){
        bloom *=  3;
        k++;
      }
      int m = (bloom - 1) / 2; //能调整长度的一半
      int mid = (l + r) / 2;
      //太难了~直接记住例子吧 L1 L2 L3 L4 |L5 L6  R1 R2 R3 R4 |R5 R6 ，对L5到R4进行三反转
      rotate(nums, l + m, mid, mid + m);
      //太难了~直接记住例子吧 对 L1 L2 L3 L4 R1 R2 R3 R4 | L5 L6 R5 R6 的前半部分进行洗牌调整
      // 洗牌后结果为 L1 R1 L2 R2 L3 R3 L4 R4 | L5 L6 R5 R6
      cycles(nums, l, bloom - 1 , k);//bloom位L1到R4的长度
      l = l + bloom - 1;//n
    }
  }

  public void rotate(int[] nums, int l, int m, int r){
    reverse(nums, l, m);
    reverse(nums, m + 1, r);
    reverse(nums, l, r);
  }

  //每三个数使用下标循环怼，找到三个数要去的位置
  //长度3^k-1中有k个数可以用公式进行调整，比如len=8的时候就有，k=2，所以i从0到2，正好3个凑成一组
  public void cycles(int[] nums, int start, int len, int k){
    //i是组内的循环，比如 0 1 2 3 4 5 6 7  （）
    for(int i = 0, trigger = 1; i < k; i++,trigger *= 3){
      //start从0开始，len = 8 的 trigger变化是第一组0开始，第二组2开始
      int preValue = nums[start + trigger - 1];
      int cur = modifyIndex(trigger, len);//计算跳到的位置，思路：因为3^k-1每组都是从1开始进行查询下一个所以要用trigger=1开始进行查询，然后用L+偏移量！
      while(cur != trigger){//下一个位置始终不等于上一个位置
        int tmp = nums[cur + start - 1]; // 被替换位置的数
        nums[cur + start - 1] = preValue;//替换的位置更新为上一个位置的数
        preValue = tmp;
        cur = modifyIndex(cur, len);
      }
      nums[cur + start - 1] = preValue;
    }
  }

  public int modifyIndex(int i , int len){ //计算的是i即trigger所以按照位置加1
    //思路：数组从1开始，到右边的位置是2*i。右边的话是2*（i - len / 2） - 1
    if(i <= len / 2){
      return 2 * i;
    } else {
      return 2 * (i - (len / 2)) - 1;
    }
  }
  public static void main(String[] args) {
    int[] sorted = new int[]{1,3,2,2,3,1};
    new Code0324_WiggleSort().wiggleSort(sorted);
    for(int i = 0; i < sorted.length; i++) {
      System.out.print(sorted[i] + " ");
    }
  }
}
