import numpy as np

"""
实现Gauss-Seidel迭代法求解线性方程组  Ax=b。Gauss-Seidel法是一种迭代算法，通过使用已经计算出的最新值来更新解向量，从而加快收敛速度。
把 A 分解为 A = D + L + U
D:对角矩阵
L：下三角矩阵
U： 上三角矩阵
(D + L)x^(k+1) = b - Ux^K
"""

"""这个是chatgpt 给的答案，不符合题目给的公式"""


def gauss_seidel(A, b, n, x_ini=None):
    A = np.array(A, dtype=float)
    b = np.array(b, dtype=float)

    len = len(b)
    x = np.zeros(len)
    for _ in range(n):
        for i in range(len):
            s1 = np.dot(A[i, :i], x[:i])  # 用新值
            s2 = np.dot(A[i, i + 1:], x[i + 1:])
            x[i] = (b[i] - s1 - s2) / A[i, i]
    return np.round(x, 6).tolist()


"""这个是牛客给的，顺着公式给的"""


def gauss_seidel_it(A, b, x):
    rows, cols = A.shape
    for i in range(rows):
        x_new = b[i]
        for j in range(cols):
            if i != j:
                x_new -= A[i, j] * x[j]  # x[j]是上一次的
        x[i] = x_new / A[i, i]
    return x


def gauss_seidel(A, b, n, x_ini=None):
    x = x_ini or np.zeros_like(b)
    for _ in range(n):
        x = gauss_seidel_it(A, b, x)
    return x


if __name__ == "__main__":
    A = np.array(eval(input()), dtype=float)
    b = np.array(eval(input()), dtype=float)
    n = int(input())
    print(gauss_seidel(A, b, n).tolist())
