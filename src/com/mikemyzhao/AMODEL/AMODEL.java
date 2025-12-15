package com.mikemyzhao.AMODEL;

import com.mikemyzhao.list_1.ListNode;
import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

import java.util.HashMap;
import java.util.Queue;
import java.util.Set;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-29 17:58
 * @Description:A级别的板子，各种往里套用即可。
 */
public class AMODEL {
  /**1-1、BFS模板，最小深度的题都用BFS
   * **/
  int BFS(TreeNode start, TreeNode target){
    Queue<TreeNode> q=null;//遍历树的核心数据结构
    Set<TreeNode> visited=null;//记录遍历的节点，避免回头路

    q.offer(start);//起点计入队列
    visited.add(start);
    int step = 0;//记录扩散步数

    while(!q.isEmpty()){
      int sz = q.size();
      /**将当前队列中所有节点向四周扩散**/
      for(int i = 0; i < sz; i++){
        TreeNode curr = q.poll();
        /**这里是重点判断是否到达条件**/
        if(curr == target)
          return step;
        for(TreeNode x:curr.getNeighbhood()){
          if(!visited.contains(x)){
            q.offer(x);
            visited.add(x);
          }
        }
      }
      step++;
    }
    return step;
  }

  /**
   * 1-2普通的DFS，每个节点+1
   **/
  void plusOneTree(TreeNode root) {
    if (root == null) return;
    root.val += 1;
    plusOneTree(root.left);
    plusOneTree(root.right);
  }

  /**2、DP model一维、二维**/
  int oneDimDP(String s){
    int n = s.length();
    int[] dp = new int[n+1];
    for(int i=1;i<n;i++){
      for(int j =0;j<i;j++){
        dp[i]=Math.max(dp[i-1],dp[i]);
      }
    }
    return dp[n];
  }

  int twoDimDP(String s1,String s2){
    int m = s1.length();int n = s2.length();
    int[][] dp = new int[m+1][n+1];
    /**如果一个字符串或者数组的两个指针，一前一后，最外层从后往前，里面一层从前往后**/
    for(int i=1;i<=n;i++){
      for(int j=1;j<=m;j++){
        if(dp[i-1]==dp[j-1]){
          dp[i][j]=dp[i-1][j-1];
        }else{
          dp[i][j]=Math.max(dp[i-1][j-1],dp[i][j]);
        }
      }
    }
    return dp[m][n];
  }

  /**3、链表删除节点的遍历，就是删除节点等于val的**/
  public ListNode deleteNode(ListNode head, int val) {
    if(head.val==val) return head.next;
    ListNode pre = head,cur =head.next;//删除的时候一定要给pre作为head
    while(cur!=null && cur.val!=val){//注意:在删除的时候直接遍历cur即可
      pre=cur;
      cur=cur.next;
    }
    if(cur.val==val) {
      pre.next = cur.next;//跳过当前=val的节点
    }
    return head;
  }

  /**4、反转链表骚操作
   * * 1-->2-->3-->4-->5-->6-->NULL
   *    * 进行递归反转ListNode last = reverse(head.next),除去head进行反转
   *    * 1    -->【2<--3<--4<--5<--6】除去head已经完成反转
   *    * head                    last
   *    * 这时候只需要处理一下head的指针然后返回last即可
   *    * head.next.next=head//(这个时候head=1，原来head.next执向2)表示当前head的后一个的下一个指针指向head(head.next=2,然后2.next=head【1】)
   *    * head.next=null//完事
   *
   * **/
  ListNode reverse(ListNode head) {/**这个绝对值得背**/
    if (null == head || head.next == null) {
      return head;
    }
    ListNode last = reverse(head.next);//递归反转除了头指针以外的
    //这个时候头指针没变还指向二head->2，变换方向
    head.next.next = head;//表示当前head的后一个的下一个指针指向head(head.next=2,然后2.next=head)
    head.next = null;
    return last;
  }

  /**
   * 普通反转
   * **/
  ListNode reverseNormal(ListNode a){
    ListNode pre,cur,nxt;
    pre = null;cur = a;nxt=a;//前为空，当前和后为头
    while(cur.next!=null){
      //反转三部曲
      //1、获取下一个节点位置，一会指针好移动
      nxt=cur.next;
      //2、操作一下当前的节点即可，指向前
      cur.next = pre;
      //移动指针下标往后移动
      pre = cur;
      cur = nxt;
    }
    return pre;//pre最后一个到结尾收场
  }

  /**5、滑动窗口模板**/
  /**滑窗模板**/
  void slidingWindow(String s,String t){
    HashMap<Character,Integer> need=new HashMap<>();
    HashMap<Character,Integer> window = new HashMap<>();
    for(Character c:t.toCharArray()){
      need.put(c,need.getOrDefault(c,0)+1);
    }
    int left =0,right=0;
    int valid = 0;
    while(right<s.length()){
      char c = s.charAt(right);//c是将窗口移入的字符
      right++;
      /**进行数据窗口的更新window的操作,略**/
      /** debug输出位置 **/
      System.out.println("window:"+Integer.toString(left+right));
      //判断左边是否要进行收缩,伪代码
      /**
       while(window is need to shrink){
       //d移除窗口
       char d =s[left];
       left++;
       //进行窗口操作
       }
       **/
    }
  }

  /**6、mapreduce的mergesort不背一下么？,双指针的应用**/
  //1、取中间位置，中间点即可
  //2、归并排序分解的左边和右边
  //3、将左右边归并在一起，合二为一；方法，两组数一遍一个指针，一个指针指一个，比较，把小的放在最后的结果里
  void mergeSort(int q[],int l, int r){
    if(l>=r) return;//注意这一定是大于等于！！！
    int mid = l+(r-l)/2; //1、取中间位置
    mergeSort(q,l,mid);//2、归并左边
    mergeSort(q,mid+1,r);//2、归并右边

    //3、左右两边数组一边一个指针，合并
    int[] tmp = new int[q.length];
    int k=0,i=l,j=mid+1;
    //3-1把两边小的放tmp里
    while(i<=mid && j<=r){//关键,双指针将两边进行归并
      if(q[i]<=q[j]) tmp[k++]=q[i++];
      else tmp[k++] = q[j++];
    }
    while(i<=mid){tmp[k++]=q[i++];}
    while(j<=r){tmp[k++]=q[j++];}
    for(i=l,j=0;i<=r;i++,j++){q[i]=tmp[j];}

  }

  /**7、哪里都用的quicksort，不背一下吗？**/
  //1、确定分界点x
  //2、调整区间,大的放在x右边，小的放在x左边，这里比较重要，方法(1)开两个数组(2)
  //3、递归处理左右两端
  void quick_sort(int[] q,int l ,int r){
    if(l>=r) return;
    int i = l-1,j=r+1,x=q[l+(r-l)/2];//注意：这个指针是关键
    while(i<j){
      do i++;while(q[i]<x);
      do j--;while(q[j]>x);
      if(i<j){ //上面保证了小于/大于就不交换直接++或者--
        int tmp=q[i];
        q[i]=q[j];
        q[j]=tmp;
      }

      quick_sort(q,l,j);
      quick_sort(q,j+1,r);
    }
  }

  /**8、二分查找**/
  int binarySearch(int[] nums,int target){
    int left = 0;
    int right = nums.length-1;
    while(left <= right){//注意：while加等号不用后续打补丁！！！
      int mid = left+(right-left)/2;//注意：这里防止边界溢出
      if(nums[mid] == target) return mid;
      else if(nums[mid]<target) left = mid+1;
      else if(nums[mid]>target) right = mid-1;
    }
    return -1;
  }

  /**9、回溯模板*
   *  第一类问题：求子集,输入一个不包含重复数字的数组，求出这些数字的所有子集，包含空集和本身
   *  回溯算法的基础模板就是一颗决策树
   *   result = []
   *   def backtrack(路径,选择列表){
   *     if 满足结束条件：
   *       result.add(路径)
   *       return
   *     for 选择 in 选择列表：
   *       做选择
   *       backTrack(路径,选择列表)
   *       撤销选择
   *
   * */

  /**10、模板：求格子每位数字之和**/
  private int getCeilSum(int x){
    int s=0;
    while(x!=0){
      s+=x%10;
      x=x/10;
    }
    return s;
  }

}
