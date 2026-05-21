"""
使用Jacobi方法编写一个函数，在 2x2 矩阵上近似奇异值分解。
不要使用 numpy 里的svd函数
返回：旋转矩阵 、计算出的奇异值和旋转矩阵的转置 。
"""
import numpy as np


def svd_2x2(A):
    """
    1、创建一个与输入矩阵相关的矩阵a 和 a2
    2、计算旋转角度θ
    3、计算旋转矩阵
    4、计算旋转后的的矩阵
    5、更新矩阵
    6、计算奇异值
    :param A:
    :return:
    A = UΣV^T
    U是左奇异向量矩阵
    Σ是奇异值矩阵
    V是右奇异向量矩阵
    奇异值的了σi是矩阵A^TA的特征值的平方根
    """
    a = A
    a_t = np.transpose(a)
    a_2 = a_t @ a  # 先计算A^TA
    v = np.eye(2)  # 创建一个2 * 2的对角矩阵
    # 堆成矩阵的Jacobi旋转公式
    for _ in range(1):  # 这里值走一遍，因为是2*2
        # 构造旋转矩阵r
        if a_2[0, 0] == a_2[1, 1]:
            theta = np.pi / 4
        else:
            # 对称矩阵 a  b
            #        c  d
            # 旋转角 θ=0.5*arc tan(2b / (a - d))
            # 得到正交矩阵r -> 对角化矩阵
            theta = 0.5 * np.arctan2(2 * a_2[0, 1], a_2[0, 0] - a_2[1, 1])

        r = np.array([
            [np.cos(theta), -np.sin(theta)],
            [np.sin(theta), np.cos(theta)]
        ])
        d = np.transpose(r) @ a_2 @ r  # d是A^TA对角化后的矩阵，对角线元素就是奇异值平方σ1²，σ2²
        a_2 = d
        v = v @ r  # v累积旋转得到右奇异向量矩阵V
    s = np.sqrt([d[0, 0], d[1, 1]])  # 奇异值
    s_inv = np.array([[1 / s[0], 0], [0, 1 / s[1]]])  # 用来计算左奇异向量

    u = a @ v @ s_inv
    return (u, s, v.T)
