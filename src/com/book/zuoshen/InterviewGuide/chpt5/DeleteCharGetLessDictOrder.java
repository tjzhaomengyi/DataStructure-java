package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/18 10:17
 * @Description: 删除多余字符得到字典序最小的字符串
 */
public class DeleteCharGetLessDictOrder {
    // 例子baacbaccac,关键步骤就是在访问到某个字符为0的时候，记录下标i，扩充res[index]当前位置的字符，然后从i+1往后继续找res[index+1]的字符
    //执行过程：（1）如果在遍历s每个字符的时候，出现某个字符为0的时候，对当前res[i]位置进行挑选，如果不挑选的话就会错过这个已经减少为0次数的字符，一定要挑选，
    //并且要在0到i这个范围进行挑选，比如到第二个b的时候，就要挑选出res[0]的结果，res[0]=a,（2）挑选的位置在str[1]，所以后面再从1后面的字符进行挑选acbaccac，
    //在删除掉挑选出来的a，在cbccc进行挑选，（3)建立统计cnt_b=1,cnt_c=4,(4)遍历到b一个后没有了，再在cb中选出最小ASCII字符作为res[1]='b' (5)最后只剩小3个c，
    // cnt_c=3
    //字符串中的每种字符只要一个，得到字典序最小的字符串.
    public String removeDuplicateLetters(String s){
        char[] str = s.toCharArray();
        //使用长度为26的数组左字符个数统计
        int[] map = new int[26];
        for(int i = 0; i < str.length; i++){
            map[str[i] - 'a']++;
        }
        char[] res = new char[26];
        int index = 0;
        int L = 0;
        int R = 0;
        while(R != str.length){
            //如果当前字符是不再考虑的，直接跳过，如果当前字符出现的次数减1之后，后面还能出现，直接跳过
            if(map[str[R] - 'a'] == -1 || --map[str[R] - 'a'] > 0){
                R++;
            } else {
                //当前字符需要考虑并且之后不会再出现,在str[L..R]上所有需要考虑的字符串，找到ASCII码最小字符的位置
                int pick = -1; //在原始字符串的pick位置
                for(int i = L; i <= R; i++){
                    //选出可以挑选的字符，（1）当pick=-1时候（2）当前字符的ASCII小于pick的时候，修改pick的值
                    if(map[str[i] - 'a'] != -1 && (pick == -1 || str[i] < str[pick])){
                        pick = i;
                    }
                }
                //把ASCII码最小的字符放到挑选结果中
                res[index++] = str[pick];
                //在上一个for循环中，str[L..R]范围内每种字符出现的次数都减少，需要把str[pick+1..R]中每个字符的词频重新统计
                for(int i = pick + 1; i <= R; i++){
                    if(map[str[i] - 'a'] != -1){
                        map[str[i] - 'a']++;
                    }
                }
                //选出ASCII码最小的字符以后不再考虑
                map[str[pick] - 'a'] = -1;
                L = pick + 1;
                R = L;//res[index]确定后，R从pick+1位置决定下一个字符
            }
        }
        return String.valueOf(res, 0, index);
    }

    public static void main(String[] args) {
        System.out.println(new DeleteCharGetLessDictOrder().removeDuplicateLetters("cdadabcc"));
    }
}
