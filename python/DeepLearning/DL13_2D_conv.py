"""
I是输入图像，K是卷积核， C是输出图像
"""
import numpy as np


def simple_conv2d(input_matrix: np.ndarray, kernel: np.ndarray, padding: int, stride: int):
    # input_height, input_width = input_matrix.shape
    kernel_height, kernel_width = kernel.shape

    # 给input_matrix边缘进行填充，mode='constant'表示填充的是常数 ，padding表示四周增加的大小
    padded_input = np.pad(input_matrix, ((padding, padding), (padding, padding)), mode='constant')

    input_height_padded, input_width_padded = padded_input.shape

    # 表示 input_height_padded - kernel_height 表示最后可以滑动到的位置是哪里，然后除以stride表示能够滑动多远
    output_height = (input_height_padded - kernel_height) // stride + 1
    output_width = (input_width_padded - kernel_width) // stride + 1

    output_matrix = np.zeros((output_height, output_width))
    for i in range(output_height):
        for j in range(output_width):
            region = padded_input[i * stride: i * stride + kernel_height, j * stride: j * stride + kernel_width]
            output_matrix[i, j] = w  # 视野 * 卷积核 求和

    return output_matrix
