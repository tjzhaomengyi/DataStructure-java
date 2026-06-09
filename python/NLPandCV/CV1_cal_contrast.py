import numpy as np

"""
描述
编写一个函数，利用最大和最小像素值之间的差异来计算灰度图像的对比度。
输入描述：
输入一个矩阵，矩阵的元素为0-255之间的整数，代表灰度图像的像素值。
输出描述：
输出一个整数，代表灰度图像的对比度

图像对比度公式，contrast = max(img)-min(img)
"""
def calculate_contrast(img) -> int:
    """
    Calculate the contrast of a grayscale image.
    Args:
        img (numpy.ndarray): 2D array representing a grayscale image with pixel values between 0 and 255.
    """
    # Your code here
    max_pixel = np.max(img)
    min_pixel = np.min(img)
    contrast = max_pixel - min_pixel
    return contrast


if __name__ == "__main__":
    img = np.array(eval(input()))
    print(calculate_contrast(img))