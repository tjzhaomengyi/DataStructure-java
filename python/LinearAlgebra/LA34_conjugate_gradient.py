"""
实现求解线性方程组的共轭梯度法
步骤：
1、计算初始残差量 r = b - Ax
2、计算初始搜索方向向量p， p = r
3、迭代更新x、r和p，直到满足收敛条件
 x = xap
 ri1 = ri - a(Api)
 bet = ri1·ri1 / ri ·ri
 p=ril bet pi
 共轭梯度法的关键在于使用正交搜索方向，确保每次迭代都能获得更多的信息，而不需要重复搜索。

"""
import numpy as np


def conjugate_gradient(A: np.array, b: np.array, n: int, x0: np.array = None, tol=1e-8) -> np.array:
    x = np.zeros_like(b)
    r = residual(A, b, x)
    rPlus1 = r
    p = r  # search direciton vector
    for i in range(n):
        # 沿着当前方向进行查找
        alp = alpha(A, r, p)
        x = x + alp * p
        rPlus1 = r - alp * (A @ p)
        bet = beta(r, rPlus1)
        p = rPlus1 + bet * p
        r = rPlus1
        if np.linalg.norm(residual(A, b, x)) < tol:
            break
    return x


def residual(A: np.array, b: np.array, x: np.array) -> np.array:
    return b - A @ x


def alpha(A: np.array, r: np.array, p: np.array) -> float:
    alpha_num = np.dot(r, r)
    alpha_den = np.dot(p @ A, p)
    return alpha_num / alpha_den


def beta(r: np.array, r_plus1: np.array) -> float:
    beta_num = np.dot(r_plus1, r_plus1)
    beta_den = np.dot(r, r)
    return beta_num / beta_den


if __name__ == '__main__':
    A = eval(input())
    b = eval(input())
    n = int(input())
    print(conjugate_gradient(A, b, n))
