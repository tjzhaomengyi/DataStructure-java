import numpy as np
def calculate_brightness(img):
    # 检查图像是否为空或者没有列
    if not img or not img[0]:
        return -1
    # 转换为numpy数组以便及逆行运算
    img_arr = np.array(img)

    # 检查数组维度是否正确
    if len(img_arr.shape) != 2:
        return -1

    #检查像素范围
    if np.any((img_arr < 0) | (img_arr > 255)):
        return -1
    # 明亮度就是这个图片的平均值
    return round(np.mean(img_arr), 2)

if __name__ == "__main__":
    img = eval(input())
    print(f"{calculate_brightness(img):.2f}")