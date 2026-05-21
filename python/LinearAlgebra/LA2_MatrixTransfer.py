"""
矩阵转置
"""
from typing import List, Union

import numpy as np


# 使用 Union 来表示类型可以是 int 或 float
def transpose_matrix(a: List[List[Union[int, float]]]) -> List[List[Union[int, float]]]:
    arr = np.array(a)
    transposed = arr.T
    return transposed.tolist()
    pass

# 处理输入输出
def main():
    try:
        matrix_str = input().strip()
        # 去掉最外层的方括号，并分割每个子数组
        rows = matrix_str[2:-2].split('],[')
        # 将每个子数组转换为数字列表
        matrix = [list(map(int, row.split(','))) for row in rows]

        # 计算转置矩阵
        result = transpose_matrix(matrix)

        # 格式化输出
        print(str(result).replace(' ', ''))
    except Exception as e:
        print(f"输入格式错误: {e}")

if __name__ == "__main__":
    main()