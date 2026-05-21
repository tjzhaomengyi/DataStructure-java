"""
实现简化行阶梯形RREF函数
"""

import numpy as np

"""
1、找主元pivot，在第i列：找i行及一下最大非零元素
2、交换行
3、归一化主元行
4、消除其他行该列
"""


def rref(matrix):
    A = np.array(matrix, dtype=float)
    rows, cols = A.shape
    r = 0  # 当前主元行
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

        r += 1
        if r == rows:
            break
    return np.round(A, 1)


if __name__ == "__main__":
    print(rref(np.array(eval(input()))))
