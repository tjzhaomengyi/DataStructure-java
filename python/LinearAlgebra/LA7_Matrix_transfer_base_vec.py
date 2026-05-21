import numpy as np
"""在线性代数中，同一个向量可以在不同的基下表示。给定 R3空间中两组基向量 
B 和 C，实现一个函数来计算从基 C  到基B 的变换矩阵 P。
输入描述：
函数`transform_basis`接收两个参数：
1. B：3×3矩阵，表示第一组基向量（每列是一个基向量）
2. C：3×3矩阵，表示第二组基向量（每列是一个基向量）"""
def transform_basis(B, C):
    '''
    思路：B = PC  -> P = BC^(-1)
    :param B:
    :param C:
    :return:
    '''
    C = np.array(C)
    B = np.array(B)
    C_inv = np.linalg.inv(C)
    P = B @ C_inv  # 矩阵的点积dot_product用这个就行了，省事
    return P.tolist()
    pass


if __name__ == "__main__":
    B = np.array(eval(input()))
    C = np.array(eval(input()))
    print(transform_basis(B, C))

