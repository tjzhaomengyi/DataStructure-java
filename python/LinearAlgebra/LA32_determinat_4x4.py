import numpy as np
"""
使用拉普拉斯展开式的4*4矩阵的行列式
拉普拉斯展开式是一种计算行列式的方法，它通过选择矩阵的某一行或者某一列，然后
计算改行或该列的元素与它们对应的代数余子式的乘积之和
"""
def determinat_4x4(matrix):
    """
    a11  a12  a13
    a21  a22  a23
    a31  a32  a33
    det(A) = a11C11 + a12C12 + a13C13
    其中Cij=（-1）^(i+j)Mij
    Mij表示删除掉第i行第j列后的子矩阵行列式，即minor，Cij表示代数余子式

    :param matrix:
    :return:
    """
    if len(matrix) == 1: #递归终止
        return matrix[0][0]
    det = 0
    for c in range(len(matrix)):
        #依次处理matrix[0][0],matrix[0][1],matrix[0][2]
        minor = [row[:c] + row[c+1:] for row in matrix[1:]]
        cofactor = ((-1)**c) * determinat_4x4(minor)
        det += matrix[0][c] * cofactor
    return det