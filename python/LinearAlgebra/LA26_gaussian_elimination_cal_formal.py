import numpy as np

"""
 高斯消元法求解线性方程组
 高斯消元法将系数矩阵转换为上三角矩阵，然后通过回代求解的方法
"""


def gaussian_elimination(A, b):
    """
    算法流程：
    1、前向消元，变上三角矩阵
        对每一列c，在第c列，从第c行往下找绝对值最大元素pivot
        交换行
        用pivot行消掉下面所有行
        消元过程：(1) 将当前行以下的所有行进行消元运算
        （2）使用公式： 当前行 = 当前行 - （消元系数 * 主元所在行）
        (3)消元系数 = 待消元元素 / 主元
    2、回代
    从最后一行开始 xn = bn/ann，逐步往上算
    :param A:
    :param b:
    :return:
    """
    A = np.array(A, dtype=float)
    b = np.array(b, dtype=float)

    n = len(b)

    # 前向消元
    for i in range(n):
        # 找最大主元
        pivot = i + np.argmax(np.abs(A[i:, i]))

        # 如果主元太小，说明不可解或者不稳定
        if abs(A[pivot][i]) < 1e-12:
            return None

        # 交换行
        if pivot != i:
            A[[i, pivot]] = A[[pivot, i]]
            b[[i, pivot]] = b[[pivot, i]]

        # 消元
        for j in range(i + 1, n):
            factor = A[j][i] / A[i][i]
            A[j, i:] -= factor * A[i, i:]
            b[j] -= factor * b[j]

    # 回代
    x = np.zeros(n)
    for i in range(n - 1, -1, -1):
        x[i] = (b[i] - np.dot(A[i, i + 1:], x[i + 1:])) / A[i][i]
    return np.round(x, 6).tolist()


def partial_pivoting(A_aug, row_num, col_num):
    rows, cols = A_aug.shape
    max_row = row_num
    max_val = abs(A_aug[row_num, col_num])
    for i in range(row_num, rows):
        current_val = abs(A_aug[i, col_num])
        if current_val > max_val:
            max_val = current_val
            max_row = i
    if max_row != row_num:
        A_aug[[row_num, max_row]] = A_aug[[max_row, row_num]]

    return A_aug


def gaussian_eliminaiton_standard(A, b):
    rows, cols = A.shape
    A_aug = np.hstack((A, b.reshape(-1, 1)))  # 构造增广矩阵 augmented matrix

    for i in range(rows - 1):
        A_aug = partial_pivoting(A_aug, i, i)
        for j in range(i + 1, rows):
            A_aug[j, i:] -= (A_aug[j, i] / A_aug[i, i]) * A_aug[i, i:]
    x = np.zeros_like(b, dtype=float)
    for i in range(rows - 1, -1, -1):
        x[i] = (A_aug[i, -1] - np.dot(A_aug[i, i + 1: cols], x[i + 1:])) / A_aug[i, i]
    return x
