import numpy as np


def matrixmul(a, b):
    # 补全代码
    a = np.array(a)
    b = np.array(b)
    shape_a = a.shape
    shape_b = b.shape
    if shape_a[1] != shape_b[0]:
        return -1
    res = a @ b
    return res.tolist()


# 主程序
if __name__ == "__main__":
    # 输入矩阵
    matrix_inputa = input()
    matrix_inputb = input()

    # 处理输入
    import ast

    matrixa = ast.literal_eval(matrix_inputa)
    matrixb = ast.literal_eval(matrix_inputb)

    # 调用函数计算逆矩阵
    output = matrixmul(matrixa, matrixb)

    # 输出结果
    print(output)
