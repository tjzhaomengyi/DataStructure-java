package com.mikemyzhao.AMODEL;

import java.util.Scanner;

public class PATestingStdinStdOut {
    public class Main {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别 ，处理输入
            while (in.hasNextInt()) { // 注意 while 处理多个 case
                int a = in.nextInt();
                int b = in.nextInt();
                System.out.println(a + b);
                // 1、一行多个整数
                //  1 2 3 4 5
                String line = in.nextLine();
                String[] parts = line.split(" ");

                // 2、读数组
                int n = in.nextInt();
                int[] arr = new int[n];
                for(int i = 0; i < n; i++){
                    arr[i] = in.nextInt();
                }

                // 3、读矩阵
                int x = in.nextInt();
                int y = in.nextInt();
                int[][] matrix = new int[x][y];
                for(int i = 0; i < x; i++){
                    for(int j = 0; j < y; j++){
                        matrix[i][j] = in.nextInt();
                    }
                }
                // 调用算法
                int res = solve(n);

                // 输出
                System.out.println(res);
            }
        }

        public static int solve(int n){
            return 0;
        }


    }
}
