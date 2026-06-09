

"""
    1.    读取输入矩阵，为一个二维列表，表示灰度图像的像素值矩阵。

    2.    读取卷积核矩阵，为一个二维列表，尺寸为 (k×k)，其中 ( k ) 为奇数。

    3.    对图像矩阵进行卷积操作，卷积过程中需要对边缘进行适当的填充（使用零填充）。

    4.    输出卷积后的矩阵，每个元素保留两位小数（使用 round(x, 2)）。
    
    输入描述：
    •    第一行包含两个整数 ( m ) 和 ( n )，表示图像矩阵的行数和列数。
    •    接下来的 ( m ) 行，每行包含 ( n ) 个整数，表示图像矩阵的元素，元素之间用空格分隔。
    •    接下来一行包含一个整数 ( k )，表示卷积核的尺寸(k*k)，且 ( k ) 为奇数）。
    •    接下来的 ( k ) 行，每行包含 ( k ) 个浮点数，表示卷积核矩阵的元素，元素之间用空格分隔。
"""
import numpy as np
def main():
    # 读入输入矩阵
    # 技巧：先通过input().split()将输入拆分，然后将拆分后的每个元素转换为int类型
    m, n = map(int, input().split())
    image = []
    for _ in range(m):
        image.append(list(map(int, input().split())))

    # 读入卷积矩阵
    k = int(input())
    kernel = []
    for _ in range(k):
        kernel.append(list(map(float, input().split())))

    img = np.array(image, dtype=float)
    ker = np.array(kernel, dtype=float)

    pad = k // 2
    padded = np.pad(img, pad, mode='constant', constant_values=0)

    out = np.zeros((m, n))
    for i in range(m):
        for j in range(n):
            region = padded[i:i+k, j:j+k]
            out[i][j] = np.sum(region * ker)

    for i in range(m):
        row = []
        for j in range(n):
            row.append(str(round(out[i][j], 2)))
        print(' '.join(row))

main()
