package com.huaweiOD.nowcoder;

import java.util.Scanner;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class RecordError {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    LinkedHashMap<String, FileError> map = new LinkedHashMap<>();
    int index = 0;
    while (in.hasNextLine()) { // 注意 while 处理多个 case
      String str = in.nextLine();

      makerecords(str, index, map);
      index++;
    }
    if (map.size() > 8) {
      int start_index = map.size() - 8;
      int i = 0;
      for (String key : map.keySet()) {
        if (i != start_index) {
          i++;
        } else if (i >= start_index && i <= index) {
          FileError fe = map.get(key);
          System.out.println(fe.file + " " + fe.error + " " + fe.cnt);
        }
      }
    } else {
      for (String key : map.keySet()) {
        FileError fe = map.get(key);
        System.out.println(fe.file + " " + fe.error + " " + fe.cnt);
      }
    }


  }

  public static void makerecords(String file_cnt, int index,
                                 HashMap<String, FileError> map) {
    String str = file_cnt.split(" ")[0];
    int error = Integer.parseInt(file_cnt.split(" ")[1]);
    String[] split = str.split("\\\\");
    String file = split[split.length - 1];
    if (file.length() > 16) {
      int start = file.length() - 16;
      file = file.substring(start, file.length());
    }
    String file_error_key = file + "_" + error; //file_error做key
    if (map.containsKey(file_error_key)) {
      FileError tmp = map.get(file_error_key);
      tmp.cnt++;
    } else {
      FileError newFileError = new FileError(index, file, error, 1);
      map.put(file_error_key, newFileError);
    }
  }
}

class FileError {
  int index;
  String file;
  int error;
  int cnt;
  public FileError(int index, String file, int error, int cnt) {
    this.index = index;
    this.file = file;
    this.error = error;
    this.cnt = cnt;
  }
}