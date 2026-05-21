from typing import List, Union

"""
    计算特征值
    输出一个包含两个特征值的列表，特征值可以是实数或复数。按从大到小的顺序返回特征值
"""


def calculate_eigenvalues(matrix: List[List[Union[int, float]]]) -> List[float]:
    import numpy as np
    # 1、eigval返回矩阵的所有特征值，输入是一个Numpy数组，比如
    #   np.linag.eigval(np.array([[2,1],[1,2]])) ，返回array[3., 1.]
    # 2、np.sort 将数组升序
    # 3、[::-1]切片操作，将数组降序
    # eigenvalue中文：特征值
    return np.sort(np.round(np.linalg.eigvals(matrix), 2))[::-1].tolist()


def main():
    matrix = eval(input())
    result = calculate_eigenvalues(matrix)
    print(result)


if __name__ == "__main__":
    main()
