package com.niuke.BM101.sort;

public class BM47_FindKthMax {
    //找出第K大的元素，使用荷兰国旗的解法
    public int findKth(int[] a, int n, int K){
        return quickSelect(a, 0, n - 1, K - 1);
    }
    public int quickSelect(int[] arr, int L, int R, int K){
        if(L == R){
            return arr[L];
        }
        int randomIndex = L + (int)(Math.random() * (R - L + 1));
        swap(arr, randomIndex, R);
        int[] equalArea = netherFlag(arr, L, R);
        int leftBound = equalArea[0];
        int rightBound = equalArea[1];
        if(K < leftBound){
            return quickSelect(arr, L, leftBound - 1, K);
        } else if(K > rightBound){
            return quickSelect(arr, rightBound + 1, R, K);
        } else {
            return arr[K];
        }

    }

    public int[] netherFlag(int[] arr, int L, int R){
        if(L == R) return new int[]{L, R};
        if(L > R) return new int[]{-1, -1};
        int less = L - 1; //less区域放的是大于pivot的元素
        int more = R; // more区域放的是小于pivot的元素
        int index = L;
        while(index < more){
            if(arr[index] == arr[R]){
                index++;
            } else if(arr[index] > arr[R]){
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[]{less+ 1, more};
    }

    public void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
