package com.ACMStyle.test;


import java.io.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-03 17:14
 * @Description:
 */
public class NC_ReverseListPart {
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public static void main(String args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StreamTokenizer in = new StreamTokenizer(br);
    PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
  }
}
