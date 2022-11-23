package com.mikemyzhao.TreeOperations_2.bigshua;


/**
 * @Author: zhaomengyi
 * @Date: 2022-03-19 10:30
 * @Description: https://leetcode.com/problems/binary-tree-cameras/
 */
public class MinCameraCover_7 {
  public static class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;
  }
  //当前节点，并且当前节点下方的点都被覆盖
  public static class Info{
    public long uncovered;//当前节点没有被覆盖，当前节点为头需要多少相机
    public long coveredNoCamara;//当前节点被覆盖，但是没有相机，当前节点为头需要多少相机
    public long coveredHasCamara;//当前节点有相机被覆盖，当前节点为头需要多少相机

    public Info(long un, long no, long has){
      uncovered = un;
      coveredNoCamara = no;
      coveredHasCamara = has;
    }
  }

  public static int minCamera(TreeNode root){
    //树的遍历
    Info data = process(root);
    return (int) Math.min(data.uncovered + 1,Math.min(data.coveredHasCamara,data.coveredNoCamara));
  }

  public static Info process(TreeNode X){
    if(X == null){//这个节点没有相机并且被覆盖
      return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
    }
    Info left = process(X.left);
    Info right = process(X.right);
    //当前节点没有被覆盖到,但是左右节点都被覆盖到了,X节点就是不覆盖交给上一层处理
    long uncoverd = left.coveredNoCamara + right.coveredNoCamara;

    //当前节点被覆盖，并且下方节点也都覆盖，但是当前节点x没有相机
    long coveredNoCamara = Math.min(left.coveredHasCamara+right.coveredHasCamara,
        Math.min(left.coveredHasCamara + right.coveredNoCamara,left.coveredNoCamara + right.coveredHasCamara));

    //当前节点被覆盖，下方节点肯定都被覆盖，当前节点x有相机
    long coveredHashCamera = 1 + Math.min(left.uncovered,Math.min(left.coveredHasCamara,left.coveredNoCamara))
        + Math.min(right.uncovered,Math.min(right.coveredHasCamara,right.coveredNoCamara));

    return new Info(uncoverd, coveredNoCamara, coveredHashCamera);
  }

  //使用贪心进行优化
  public static enum Status{
    UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA;
  }
  public static class Data{
    public Status status;
    public int cameras;

    public Data(Status status, int cameras){
      this.status = status;
      this.cameras = cameras;
    }

  }

  public static int minCameraCover2(TreeNode root){
    Data data = process2(root);
    return data.cameras + (data.status == Status.UNCOVERED ? 1 : 0);
  }

  public static Data process2(TreeNode X){
    if(X == null){
      return new Data(Status.COVERED_NO_CAMERA, 0);
    }
    Data left = process2(X.left);
    Data right = process2(X.right);
    int cameras = left.cameras + right.cameras;

    //左右只要有一个没覆盖，赶紧找个点就要加摄像头
    if(left.status == Status.UNCOVERED || right.status == Status.UNCOVERED){
      return new Data(Status.COVERED_HAS_CAMERA , cameras + 1);
    }

    //如果左右孩子不存在没有覆盖的情况
    if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA){
      return new Data(Status.COVERED_NO_CAMERA,cameras);
    }
    //左右孩子都被覆盖了，当前这个点可以不用覆盖，贪心，交给上一层取覆盖吧
    return new Data(Status.UNCOVERED, cameras);
  }
}
