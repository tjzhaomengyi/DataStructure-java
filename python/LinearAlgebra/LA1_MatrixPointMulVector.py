import sys


def matrix_vector_dot_product(matrix, vector):
    """
        计算矩阵和向量的点积
        参数：
        matrix：二维列表，表示矩阵
        vector：一维列表，表示向量
        注意：输入数组 [[1,1],[2,2],[3,3]]
        np.array(输入数组)得到3行两列的矩阵
        1   1
        2   2
        3   3
    """
    import numpy as np
    # 将输入转换为numpy数组
    mat = np.array(matrix)
    vec = np.array(vector)

    # 获取矩阵的维度
    m, n = mat.shape  # m行n列
    # 检查维度是否匹配：矩阵的列数应该等于向量的长度
    if n != len(vec):
        return -1

    # 计算点积
    result = np.dot(mat, vec)

    # 将结果转换为list
    return result.tolist()


if __name__ == '__main__':
    import ast

    mat = ast.literal_eval(sys.stdin.readline().strip())
    vec = ast.literal_eval(sys.stdin.readline().strip())
    output = matrix_vector_dot_product(mat, vec)
    print(output)
