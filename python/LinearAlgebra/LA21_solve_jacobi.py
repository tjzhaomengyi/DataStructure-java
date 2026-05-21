import numpy as np

"""
    使用Jacobi方法迭代求解线性方程组Ax = b
    1、将矩阵A分解为对角矩阵和非对角矩阵N, A = D + N
      其中D为对角矩阵，N为非对角矩阵
    2、迭代公式
        对于每个方程i，在第k+1次迭代时，
        xi(kl) = 1/aii(bi - ∑（条件j不等于i）aijxj(k))
    aii是矩阵A的第i个对角元素
    bi是向量b的第i个元素
    aij是矩阵A的第i行第j列元素
    xj(k)是第k次迭代时x的第j个分量
    
"""


def solve_jacobi(A, b, n):
    """
        4,1,2
    A = 1,3,1
        2,1,5
    d_a = np.diag(A), [4,3,5]
                    4,0,0
    np.diag(d_a) =  0,3,0
                    0,0,5
    :param A:
    :param b:
    :param n:表示迭代n次
    :return:
    """
    d_a = np.diag(A)  # 取出对角元素 [4,3,5]
    nda = A - np.diag(d_a)  # nda就是N
    x = np.zeros(len(b))  # 初始猜测向量
    x_hold = np.zeros(len(b))
    for _ in range(n):  # 迭代
        for i in range(len(A)):  # 更新
            x_hold[i] = (1 / d_a[i]) * (b[i] - sum(nda[i] * x)) #因为不能包含对角元素，所以是nda
        x = x_hold.copy()
    return np.round(x, 4).tolist()
