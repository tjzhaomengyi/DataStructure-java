"""
使用 T-1AS求结果

"""

import numpy as np


def transform_matrix(A, T, S):
    A = np.array(A, dtype=float)
    T = np.array(T, dtype=float)
    S = np.array(S, dtype=float)
    if np.linalg.det(T) == 0 or np.linalg.det(S) == 0:
        return -1
    T_1 = np.linalg.inv(T)
    res = np.round(T_1 @ A @ S, 3)
    return res.tolist()


# 主程序
if __name__ == "__main__":
    # 输入
    ndarrayA = input()
    ndarrayT = input()
    ndarrayS = input()

    # 处理输入
    import ast

    A = ast.literal_eval(ndarrayA)
    T = ast.literal_eval(ndarrayT)
    S = ast.literal_eval(ndarrayS)

    # 调用函数计算
    output = transform_matrix(A, T, S)

    # 输出结果
    print(output)
