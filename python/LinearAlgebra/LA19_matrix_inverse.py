import numpy as np

"""
求逆矩阵
"""


def inverse_2x2(matrix):
    # 补全代码
    matrix = np.array(matrix, dtype=float)
    if np.linalg.det(matrix) == 0:
        return None
    res = np.round(np.linalg.inv(matrix), 2)
    return res.tolist()


# 主程序
if __name__ == "__main__":
    # 输入矩阵
    matrix_input = input()

    # 处理输入
    import ast

    matrix = ast.literal_eval(matrix_input)

    # 调用函数计算逆矩阵
    output = inverse_2x2(matrix)

    # 输出结果
    print(output)
