package com.niuke.BM101.permute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BM58_StringPremute {
    public List<String> premutation(String s){
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        boolean[] used = new boolean[chars.length];
        backtrack(chars, new StringBuilder(), used, res);
        return res;
    }

    private void backtrack(char[] chars, StringBuilder path, boolean[] used, List<String> res){
        if(path.length() == chars.length){
            res.add(path.toString());
            return;
        }
        for(int i = 0; i < chars.length; i++){
            if(used[i]) continue;
            //同层去重核心
            //[a0,a1,b]
            //在第一个for循环中：
            // 选a0（梦境1层）
            //     选a1（梦境2层）
            //          选b（梦境3层）
            //          撤b（梦境3层）
            //      撤a1（梦境2层）removelast
            //      选b（梦境2层）
            //          选a1（梦境3层）
            //          撤a1（梦境3层）
            //       撤b（梦境2层）
            //   撤销a0
            // 第一个for循环才结束！！
            if(i > 0 && chars[i] == chars[i - 1] && !used[i - 1]){
                continue;
            }
            used[i] = true;
            path.append(chars[i]);
            backtrack(chars, path, used, res);
            path.deleteCharAt(path.length() - 1);
            used[i] = false;
        }
    }
}
