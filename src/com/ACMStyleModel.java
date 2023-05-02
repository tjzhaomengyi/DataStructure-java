package com;

import java.io.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-03 15:45
 * @Description:MCA
 */
public class ACMStyleModel {

  public static void main(String[] args) throws IOException{
    //ACM风格
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /**
     * 【左神解释】后台有多组数据(可以想成是后台的文件)，br把文件接过来。
     * 这种方法快的关键使用StreamTokenizer把文件的部分文件一直加载到内存，而不是每次都使用IO，
     * 使用IO会卡死你，这样写不会因为IO卡时间,常用拿数的方法
     *     如果要拿数据
     *     double num = in.nval;
     *     String str = in.sval;
     *     int num = (int) in.nval;
     */
    StreamTokenizer in = new StreamTokenizer(br);
    PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    /**
     * 如果是整体读一行参数
     */
    String str = br.readLine();
    //数据有多组，如果没有遇到文件结尾就一直处理
    while(in.nextToken() != StreamTokenizer.TT_EOF){
      int M = (int) in.nval;
      in.nextToken();
      int N = (int) in.nval;
      //生成输入用的矩阵
      int[][] matrix = new int[M][N];
      for(int i = 0; i < M; i++){
        for(int j = 0; j < N; j++){
          in.nextToken();
          matrix[i][j] = (int)in.nval;
        }
      }
      out.println();//括号中填写要打印的结果。括号中写方法xxx(matrix)
      out.flush();//把out刷一下，进入下一个方法
    }
  }
  //public XXX()写方法
}
