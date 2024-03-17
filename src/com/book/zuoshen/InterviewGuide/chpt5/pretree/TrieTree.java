package com.book.zuoshen.InterviewGuide.chpt5.pretree;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/21 13:29
 * @Description:前缀树，前缀树的值在路径上，没在节点上
 */
public class TrieTree {
    public class TrieNode {
        public int path;
        public int end;
        public TrieNode[] map;

        public TrieNode(){
            path = 0;
            end = 0;
            map = new TrieNode[26];
        }
    }

    public class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }
        public void insert(String word){
            if(word == null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.path++;
            int index = 0;
            for(int i = 0; i < chs.length; i++){
                index = chs[i] - 'a';
                if(node.map[index] == null){
                    node.map[index] = new TrieNode();
                }
                node = node.map[index];
                node.path++;
            }
            node.end++;
        }

        public void delete(String word){
            if(search(word)){
                char[] chs = word.toCharArray();
                TrieNode node = root;
                node.path++;
                int index = 0;
                for(int i = 0; i < chs.length; i++){
                    index = chs[i] - 'a';
                    if(node.map[index].path-- == 1){
                        node.map[index] = null;
                        return;
                    }
                    node = node.map[index];
                }
                node.end--;
            }

        }


        public boolean search(String word){
            if(word == null){
                return false;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i = 0; i < chs.length; i++){
                index = chs[i] - 'a';
                if(node.map[index] == null){
                    return false;
                }
                node = node.map[index];
            }
            return node.end != 0;
        }

        public int prefixNumber(String pre){
            if(pre == null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i = 0; i < chs.length; i++){
                index = chs[i] - 'a';
                if(node.map[index] == null){
                    return 0;
                }
                node = node.map[index];
            }
            return node.path;
        }
    }



}
