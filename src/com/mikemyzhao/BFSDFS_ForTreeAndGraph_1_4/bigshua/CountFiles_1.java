package com.mikemyzhao.BFSDFS_ForTreeAndGraph_1_4.bigshua;

import java.io.File;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-13 9:22
 * @Description:统计文件夹下的文件个数，文件夹不算
 */
public class CountFiles_1 {
  public static int getFileNumber(String folderPath){
    File root = new File(folderPath);
    if(!root.isDirectory() && !root.isFile()){
      return 0;
    }
    if(root.isFile()){
      return 1;
    }
    //使用BFS进行遍历
    Stack<File> stack = new Stack<>();
    stack.add(root);
    int files = 0;
    while(!stack.isEmpty()){
      File folder = stack.pop();
      for(File next:folder.listFiles()){
        if(next.isFile()){
          files++;
        }
        if(next.isDirectory()){
          stack.push(next);
        }
      }
    }
    return files;
  }

  public static void main(String[] args){
    String path = "";
    System.out.println(getFileNumber(path));
  }
}
