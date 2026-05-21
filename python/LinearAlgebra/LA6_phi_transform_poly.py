"""
实现一个函数执行phi变换，通过生成多项式特征将摄入映射到更高维度空间。比如输入特征x，degree=2，返回[1, x, x²]
"""
import numpy as np

def phi_transform(data: list[float], degree: int) -> list[list[float]]:
    """
    Perform a Phi Transformation to map input features into a higher-dimensional space by generating polynomial features.

    Args:
        data (list[float]): A list of numerical values to transform.
        degree (int): The degree of the polynomial expansion.

    Returns:
        list[list[float]]: A nested list where each inner list represents the transformed features of a data point.
    """
    if degree < 0 or not data:
        return []

    result = []

    for x in data:
        for d in range(degree + 1):
            result.append(x ** d)  # 一口气全部添加到结果中
    result_float = np.array(result, dtype=float)
    return result_float.reshape(-1, degree + 1).tolist() #最后根据列整理成二维数组

def phi_transform_easy(data: list[float], degree: int) -> list[list[float]]:
    if not data or degree < 0:
        return []
    return [[float(x ** d) for d in range(degree + 1)] for x in data]

if __name__ == "__main__":
    data = eval(input())
    degree = int(input())
    print(phi_transform(data, degree))

