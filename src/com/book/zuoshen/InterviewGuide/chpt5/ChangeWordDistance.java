package com.book.zuoshen.InterviewGuide.chpt5;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/18 12:33
 * @Description:WordLadderII字符串梯子给定两个字符串start和to，找到从start变成to的最短变换路径
 */
public class ChangeWordDistance {

    //先建立每个字符可能的变化
    public HashMap<String, ArrayList<String>> getNexts(List<String> words){
        Set<String> dict = new HashSet<>(words);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>(); //存储每个字符串下一个可能的字符串

        for(int i = 0; i < words.size(); i++){
            nexts.put(words.get(i), getNext(words.get(i), dict));
        }
        return nexts;
    }



    //生成每个字符串的可能生成的下一个字符串的所有集合
    private ArrayList<String> getNext(String word, Set<String> dict){
        ArrayList<String> res = new ArrayList<String>();
        char[] chs = word.toCharArray();
        for(char cur = 'a'; cur <= 'z'; cur++){
            for(int i = 0; i < chs.length; i++){
                if(chs[i] != cur){
                    char tmp = chs[i];
                    chs[i] = cur;
                    if(dict.contains(String.valueOf(chs))){ //这个字符串必须在查找列表中可以转换出来
                        res.add(String.valueOf(chs));
                    }
                    chs[i] = tmp; //把chs[i]恢复成原来的字符
                }
            }
        }
        return res;
    }

    // 技巧1：这里使用BFS计算得到最短距离
    // 从start字符串出发，利用nexts信息和BFS遍历，求出每个字符串到start的最短距离
    public HashMap<String, Integer> getDistances(String start, HashMap<String, ArrayList<String>> nexts){
        HashMap<String, Integer> distances = new HashMap<>(); //记录所有字符串到start的距离
        distances.put(start, 0);
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        HashSet<String> set = new HashSet<String>();
        set.add(start);
        while(!queue.isEmpty()){
            String cur = queue.poll();
            for(String str : nexts.get(cur)){ //第一步先找到start对应字符串的坐在的key_value对，然后再这个key-value对中进行BFS
                if(!set.contains(str)){
                    distances.put(str, distances.get(cur) + 1);
                    queue.add(str);
                    set.add(str);
                }
            }
        }
        return distances;
    }

    //技巧2：使用DFS得到转换结果，从前往后回溯，leetcode在这个上面增加了一个测试用例aaaaa到ggggg的，会超时，用下面的反向回溯不会超时
    /**
     *
     * @param cur 起始节点
     * @param to 目标节点
     * @param nexts 每个字符串的转换集合
     * @param distances 每个字符串到达to的距离集合
     * @param solution 最终路径的中间解缓存
     * @param res 所有可能的变化方法
     */
    private void getShortestPath(String cur, String to, HashMap<String, ArrayList<String>> nexts,
                                 HashMap<String, Integer> distances, LinkedList<String> solution,
                                 List<List<String>> res){
        solution.add(cur);//把当前遍历的位置放到结果中
        if(to.equals(cur)){
            res.add(new LinkedList<String>(solution)); //统计结果，注意注意注意：这里DFS一定要把原始数据深拷贝出来，否则出错
        } else {
            for(String next : nexts.get(cur)){ //根据转换集合找到cur下一个可能变成的字符串
                if(distances.get(next) == distances.get(cur) + 1){ //一定一定一定要保证distance的距离是+1到下一个字符串
                    getShortestPath(next, to, nexts, distances, solution, res);
                }
            }
        }
        solution.pollLast();//DFS的还原现场操作
    }

    //注意：逆序回溯降低时间复杂度,从后往前回溯
    private void getShortestPathReverse(String cur, String start, HashMap<String, ArrayList<String>> nexts,
                                        HashMap<String, Integer> distances, LinkedList<String> solution,
                                        List<List<String>> res){
        solution.push(cur); //使用栈，先进后出
        if(start.equals(cur)){
            res.add(new LinkedList<String>(solution));
        } else {
            if(nexts.get(cur) != null && distances.get(cur) != null) {
                for (String pre : nexts.get(cur)) {
                    if (distances.get(pre) == distances.get(cur) - 1) {
                        getShortestPathReverse(pre, start, nexts, distances, solution, res);
                    }
                }
            }
        }
        solution.pop(); //对应栈的push方法
    }

    public List<List<String>> findLadders(String start, String to, List<String> list){
        list.add(start);
        HashMap<String, ArrayList<String>> nexts = getNexts(list);
        HashMap<String, Integer> distances = getDistances(start, nexts);
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPathReverse(to, start, nexts, distances, pathList, res);
        return res;
    }

    public static void main(String[] args) {
//        List<String> dict = new ArrayList<String>(Arrays.asList("aaaaa","caaaa","cbaaa","daaaa","dbaaa","eaaaa","ebaaa","faaaa","fbaaa","gaaaa","gbaaa","haaaa","hbaaa","iaaaa","ibaaa","jaaaa","jbaaa","kaaaa","kbaaa","laaaa","lbaaa","maaaa","mbaaa","naaaa","nbaaa","oaaaa","obaaa","paaaa","pbaaa","bbaaa","bbcaa","bbcba","bbdaa","bbdba","bbeaa","bbeba","bbfaa","bbfba","bbgaa","bbgba","bbhaa","bbhba","bbiaa","bbiba","bbjaa","bbjba","bbkaa","bbkba","bblaa","bblba","bbmaa","bbmba","bbnaa","bbnba","bboaa","bboba","bbpaa","bbpba","bbbba","abbba","acbba","dbbba","dcbba","ebbba","ecbba","fbbba","fcbba","gbbba","gcbba","hbbba","hcbba","ibbba","icbba","jbbba","jcbba","kbbba","kcbba","lbbba","lcbba","mbbba","mcbba","nbbba","ncbba","obbba","ocbba","pbbba","pcbba","ccbba","ccaba","ccaca","ccdba","ccdca","cceba","cceca","ccfba","ccfca","ccgba","ccgca","cchba","cchca","cciba","ccica","ccjba","ccjca","cckba","cckca","cclba","cclca","ccmba","ccmca","ccnba","ccnca","ccoba","ccoca","ccpba","ccpca","cccca","accca","adcca","bccca","bdcca","eccca","edcca","fccca","fdcca","gccca","gdcca","hccca","hdcca","iccca","idcca","jccca","jdcca","kccca","kdcca","lccca","ldcca","mccca","mdcca","nccca","ndcca","occca","odcca","pccca","pdcca","ddcca","ddaca","ddada","ddbca","ddbda","ddeca","ddeda","ddfca","ddfda","ddgca","ddgda","ddhca","ddhda","ddica","ddida","ddjca","ddjda","ddkca","ddkda","ddlca","ddlda","ddmca","ddmda","ddnca","ddnda","ddoca","ddoda","ddpca","ddpda","dddda","addda","aedda","bddda","bedda","cddda","cedda","fddda","fedda","gddda","gedda","hddda","hedda","iddda","iedda","jddda","jedda","kddda","kedda","lddda","ledda","mddda","medda","nddda","nedda","oddda","oedda","pddda","pedda","eedda","eeada","eeaea","eebda","eebea","eecda","eecea","eefda","eefea","eegda","eegea","eehda","eehea","eeida","eeiea","eejda","eejea","eekda","eekea","eelda","eelea","eemda","eemea","eenda","eenea","eeoda","eeoea","eepda","eepea","eeeea","ggggg","agggg","ahggg","bgggg","bhggg","cgggg","chggg","dgggg","dhggg","egggg","ehggg","fgggg","fhggg","igggg","ihggg","jgggg","jhggg","kgggg","khggg","lgggg","lhggg","mgggg","mhggg","ngggg","nhggg","ogggg","ohggg","pgggg","phggg","hhggg","hhagg","hhahg","hhbgg","hhbhg","hhcgg","hhchg","hhdgg","hhdhg","hhegg","hhehg","hhfgg","hhfhg","hhigg","hhihg","hhjgg","hhjhg","hhkgg","hhkhg","hhlgg","hhlhg","hhmgg","hhmhg","hhngg","hhnhg","hhogg","hhohg","hhpgg","hhphg","hhhhg","ahhhg","aihhg","bhhhg","bihhg","chhhg","cihhg","dhhhg","dihhg","ehhhg","eihhg","fhhhg","fihhg","ghhhg","gihhg","jhhhg","jihhg","khhhg","kihhg","lhhhg","lihhg","mhhhg","mihhg","nhhhg","nihhg","ohhhg","oihhg","phhhg","pihhg","iihhg","iiahg","iiaig","iibhg","iibig","iichg","iicig","iidhg","iidig","iiehg","iieig","iifhg","iifig","iighg","iigig","iijhg","iijig","iikhg","iikig","iilhg","iilig","iimhg","iimig","iinhg","iinig","iiohg","iioig","iiphg","iipig","iiiig","aiiig","ajiig","biiig","bjiig","ciiig","cjiig","diiig","djiig","eiiig","ejiig","fiiig","fjiig","giiig","gjiig","hiiig","hjiig","kiiig","kjiig","liiig","ljiig","miiig","mjiig","niiig","njiig","oiiig","ojiig","piiig","pjiig","jjiig","jjaig","jjajg","jjbig","jjbjg","jjcig","jjcjg","jjdig","jjdjg","jjeig","jjejg","jjfig","jjfjg","jjgig","jjgjg","jjhig","jjhjg","jjkig","jjkjg","jjlig","jjljg","jjmig","jjmjg","jjnig","jjnjg","jjoig","jjojg","jjpig","jjpjg","jjjjg","ajjjg","akjjg","bjjjg","bkjjg","cjjjg","ckjjg","djjjg","dkjjg","ejjjg","ekjjg","fjjjg","fkjjg","gjjjg","gkjjg","hjjjg","hkjjg","ijjjg","ikjjg","ljjjg","lkjjg","mjjjg","mkjjg","njjjg","nkjjg","ojjjg","okjjg","pjjjg","pkjjg","kkjjg","kkajg","kkakg","kkbjg","kkbkg","kkcjg","kkckg","kkdjg","kkdkg","kkejg","kkekg","kkfjg","kkfkg","kkgjg","kkgkg","kkhjg","kkhkg","kkijg","kkikg","kkljg","kklkg","kkmjg","kkmkg","kknjg","kknkg","kkojg","kkokg","kkpjg","kkpkg","kkkkg","ggggx","gggxx","ggxxx","gxxxx","xxxxx","xxxxy","xxxyy","xxyyy","xyyyy","yyyyy","yyyyw","yyyww","yywww","ywwww","wwwww","wwvww","wvvww","vvvww","vvvwz","avvwz","aavwz","aaawz","aaaaz"));
        List<String> dict = new ArrayList<String>(Arrays.asList("hot","dot","dog","tog","cog"));
        List<List<String>> ans = new ChangeWordDistance().findLadders("hit", "cog", dict);
        for(int i = 0; i < ans.size(); i++){
            List<String> trans = ans.get(i);
            for(int j = 0; j < trans.size(); j++){
                System.out.print(trans.get(j)+ "-->");
            }
            System.out.println();
        }
    }

}
