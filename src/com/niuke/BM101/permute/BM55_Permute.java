package com.niuke.BM101.permute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BM55_Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        backTrack(nums, used, path, res);
        return res;
    }

    private void backTrack(int[] nums, boolean[] used, LinkedList<Integer> path, List<List<Integer>> res){
        // 终止条件
        if(path.size() == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]) continue;
            // 选择遍历i，并放入path中
            used[i] = true;
            path.add(nums[i]);

            //递归
            backTrack(nums, used, path, res);

            //撤销当前选择，遍历下一个
            path.removeLast();
            used[i] = false;
        }
    }

    public List<List<Integer>> permuteRepeat(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];

        backTackRepeat(nums, visited, path, res);
        return res;
    }
    private void backTackRepeat(int[] nums, boolean[] visited,
                                LinkedList<Integer> path, List<List<Integer>> res){
        if(path.size() == nums.length){
            res.add(new ArrayList<Integer>(path));
        }
        for(int i = 0; i < nums.length; i++){
            //    ()
            //   /  \
            //  (1) (1)
            //上面图示中的拆分就是重复的情况，所以如果visited[i-1]
            // 表示当前层还没有使用第一个1，如果在选第二个1，就等同于在同一层中使用第二个1作为起点，但是本来已经有第一个1作为起点了
            // 上面一句话注意最后，不是visited[i-1]=true 就是用做起点了，因为在递归的时候，i-1会进行一次回退，重新把visited[i-1]设置为false
            // 如果visited[i-1]表示已经进入了下一层，就不是第一层的事情了。
            if(visited[i]) continue;
            if(i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]){
                continue;
            }
            visited[i] = true;
            path.add(nums[i]);
            backTackRepeat(nums, visited, path, res);
            path.removeLast();
            visited[i] = false;
        }

    }
}
