package com.weeklyproblems.weekly2021_12_4;



import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/7 19:41
 * @Description:
 */
public class Code02_AreaStatics {

    public static class AreaResource{
        public String area;
        public String spliter;
        public long count;

        public AreaResource(String a, String s, long c){
            area = a;
            spliter = s;
            count = c;
        }
    }
    public static class Area {
        public String name;
        public HashMap<String, Area> next;
        public long count;
        public Area(String n, long c){
            name = n;
            count = c;
            next = new HashMap<>();
        }

        public String toString() {
            StringBuilder ans = new StringBuilder();
            if(!name.equals("")){
                ans.append("\"" + name + "\"" + ":");
            }
            if(next.isEmpty()){ //把数字加入到json中
                ans.append(count);
            } else {
                ans.append("{");//开始节点
                for(Area child : next.values()){
                    //让子节点递归去吧
                    ans.append(child.toString() + ",");
                }
                ans.setCharAt(ans.length() - 1, '}');
            }
            return ans.toString();
        }
    }
    public static String mergeCount(List<AreaResource> areas){
        Area all = new Area("", 0);//宇宙根节点，什么也不放
        for(AreaResource r : areas){
            String[] path = r.area.split(r.spliter);
            long count = r.count;
            f(path, 0 , all, count);
        }
        return all.toString();
    }
    //使用递归方法构建前缀树
    public static void f(String[] path, int index, Area pre, long count){
        if(index == path.length){
            //递归越界了，把最后一个点赋值
            pre.count += count;
        } else {
            String cur = path[index];//当前节点
            if(!pre.next.containsKey(cur)){
                pre.next.put(cur, new Area(cur, 0)); //不到最后一个节点前缀树不做统计
            }
            f(path, index + 1, pre.next.get(cur), count);//
        }
    }




}
