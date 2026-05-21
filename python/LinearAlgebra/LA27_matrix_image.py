import numpy as np

"""
实现一个函数来计算矩阵的列空间column space。
列空间，也称为像image或张成空间span，是由矩阵的列向量的所有线性组合构成的向量空间
使用行阶梯RREF方法来找出构成列空间基底的线性无关向量
本质就是找到RREF矩阵的主元列

"""


def rref(matrix):
    A = np.array(matrix, dtype=float)
    rows, cols = A.shape
    r = 0  # 当前主元行
    pivot_cols = []

    for c in range(cols):
        # 1、找主元
        pivot = None
        for i in range(r, rows):
            if abs(A[i][c]) > 1e-9:  # 这里1e-9 就是0，这样写更安全
                pivot = i
                break
        if pivot is None:
            continue
        # 2、交换行，Numpy这个高级索引一次性完成两行的交换，这里交换就是为了让更靠前的非0的列放在前面，早点做抵消
        A[[r, pivot]] = A[[pivot, r]]
        # 3、归一化，把发现第一个为非0的归一，然后后面几行进行消0
        A[r] = A[r] / A[r][c]
        # 4、消元
        for i in range(rows):
            if i != r:  # 如果不是找到的pivot的那一行
                A[i] -= A[i][c] * A[r]
        pivot_cols.append(c)
        r += 1
        if r == rows:
            break
    return A, pivot_cols


def matrix_image(A):
    _, pivot_cols = rref(A)
    return A[:, pivot_cols]
