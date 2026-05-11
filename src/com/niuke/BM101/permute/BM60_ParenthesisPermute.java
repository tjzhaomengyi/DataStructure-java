package com.niuke.BM101.permute;

import java.util.ArrayList;

public class BM60_ParenthesisPermute {

    ArrayList<String> parentthesisPermute(int n){
        if(n == 0) return null;
        ArrayList<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        // 第一个 n 表示剩下n个左括号
        // 第二个 n 表示剩下n个右括号
        backtrack(n, n, path, res);
        return res;
    }

    private void backtrack(int left, int right, StringBuilder path, ArrayList<String> res){
        if(left < 0 || right < 0) return;
        if(left > right) return; //左括号多了，说明这个生成不合法
        if(left == 0 && right == 0){
            res.add(path.toString());
            return;
        }
        // 尝试添加一个左括号,因为传入的数不变，所以left-1后不需要手写代码恢复，因为跳出“梦境”后还是原来的 left 和 right
        path.append('(');
        backtrack(left - 1, right, path, res);
        path.deleteCharAt(path.length() - 1);

        //尝试添加一个右括号
        path.append(')');
        backtrack(left, right - 1, path, res);
        path.deleteCharAt(path.length() - 1);
    }
}
