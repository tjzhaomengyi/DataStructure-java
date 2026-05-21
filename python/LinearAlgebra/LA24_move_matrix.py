import numpy as np


"""
实现矩阵平移
"""
def translate_object(points, tx, ty):
    pts = np.array(points, dtype=np.float64)
    pts += np.array([tx, ty])
    return pts.tolist()


if __name__ == "__main__":
    points = eval(input())
    tx, ty = map(float, input().split())
    print(translate_object(points, tx, ty))
