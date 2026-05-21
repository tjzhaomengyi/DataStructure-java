from typing import List, Tuple, Union
import numpy as np


def reshape_matrix(a: List[List[Union[int, float]]], new_shape: Tuple[int, int]) -> List[List[Union[int, float]]]:
    if len(a) * len(a[0]) != new_shape[0] * new_shape[1]:
        return -1
    return np.array(a).reshape(new_shape).tolist()
    pass


def main():
    try:
        a = eval(input())
        new_shape = eval(input())
        result = reshape_matrix(a, new_shape)
        print(result)
    except Exception as e:
        print(f"输入格式错误: {e}")


if __name__ == "__main__":
    main()
