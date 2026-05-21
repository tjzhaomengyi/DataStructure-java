from typing import List, Union

"""
    计算矩阵的标量乘法
"""
def scalar_multiply(matrix: List[List[Union[int, float]]], scalar: Union[int, float]) -> List[List[Union[int, float]]]:
    import numpy as np
    return (np.array(matrix) * scalar).tolist()
    pass


def main():
    matrix = eval(input())
    scalar = float(input())
    result = scalar_multiply(matrix, scalar)
    print(result)


if __name__ == "__main__":
    main()
