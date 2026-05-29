"""
Relu激活函数 f(x) = max(z, 0)
"""

def relu(z: float) -> float:
    return max(z, 0)


if __name__ == "__main__":
    z = eval(input())
    print(relu(z))
