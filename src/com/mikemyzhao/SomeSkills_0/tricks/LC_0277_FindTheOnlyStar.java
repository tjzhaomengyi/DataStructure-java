package com.mikemyzhao.SomeSkills_0.tricks;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 17:17
 * @Description:
 */
public class LC_0277_FindTheOnlyStar {
  public static boolean knows(int x, int i){
    return true;
  }
  public int findCelebrity(int n){
    //先顺着找谁也不认识的第一个人
    int cand = 0;
    for(int i = 0; i < n; i++){
      if(knows(cand, i)){
        //cand认识i，那么i就可能是cand
        cand = i;
      }
    }

    //验证cand是不是明星
    for(int i = 0; i < cand; i++){
      if(knows(cand, i)){
        //cand都认识老百姓了停了不对
        return -1;
      }
    }
    //是不是所有人都认识cand
    for(int i = 0; i < n; i++){
      if(!knows(i, cand)){
        return -1;//有不认识的就对了
      }
    }
    return cand;
  }
}
