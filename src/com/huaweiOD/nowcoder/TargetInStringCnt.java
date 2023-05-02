package com.huaweiOD.nowcoder;
import java.io.*;
/**
 * @Author: zhaomengyi
 * @Date: 2023-04-04 13:57
 * @Description:
 */
public class TargetInStringCnt {

  // 注意类名必须为 Main, 不要有任何 package xxx 信息
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StreamTokenizer in = new StreamTokenizer(br);
      PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

      while(in.nextToken() != StreamTokenizer.TT_EOF){
        String str = in.sval;
        in.nextToken();
        String target = in.sval;
        out.print(findTargetCnt(str, target));
        out.flush();
      }
    }

  public static int findTargetCnt(String str, String target){
    int ans = 0;
    char t = target.toCharArray()[0];
    if(t <= 'Z' && t >= 'A'){
      t = (char)(t + 32); //t转小写，记住了转小写是加号
    }
    for(int i = 0; i < str.length(); i++){
      char s = str.charAt(i);
      if((s >= 'A' && s <= 'Z')){
        s = (char)(s + 32);
      }
      if(s == t){
        ans++;
      }
    }
    return ans;
  }
}
