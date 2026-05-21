from typing import List, Union
import numpy as np

def calculate_matrix_mean(matrix: List[List[Union[int, float]]], mode: str) -> List[float]:
    if mode == 'column':
        return np.mean(matrix, axis=0).tolist()
    elif mode == 'row':
        return np.mean(matrix, axis=1).tolist()
    else:
        raise ValueError("Mode must be 'row' or 'column'")
    pass

def main():
    matrix = eval(input())
    mode = input()
    result = calculate_matrix_mean(matrix, mode)
    print(result)

if __name__ == "__main__":
    main()