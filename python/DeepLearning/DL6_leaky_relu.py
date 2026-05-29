import numpy as np
from typing import Union

def leaky_relu(z, alpha = 0.01):
    return max(z, alpha * z)


if __name__ == "__main__":
    z = eval(input())
    alpha = eval(input())
    print(leaky_relu(z, alpha))
