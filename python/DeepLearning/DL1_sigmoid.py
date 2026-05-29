import math
"""
Sigmoid函数将任意实数映射到(0,1)区间，常用于神经网络的二分类问题
"""

def sigmoid(z:float) -> float:
    result = 1 / (1 + math.exp(-z))
    return round(result, 4)