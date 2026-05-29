"""
位置编码是Transformer模型中用于处理序列数据的一种技术，它通过将输入序列中的每个位置映射到一个固定长度的向量,
从而为模型提供位置信息，步骤：
1、初始化位置编码矩阵
    创建一个与输入序列长度相同的矩阵，用于存储每个位置的编码向量
2、计算位置编码
    对于输入序列中的每个位置pos，计算其位置编码
3、返回编码位置矩阵
"""
import numpy as np
def pos_encoding(position: int, d_model: int):
    if position == 0 or d_model <= 0:
        return np.array(-1)
    #初始化位置编码矩阵和对应索引
    pos = np.array(np.arange(position), np.float32)
    ind = np.array(np.arange(d_model), np.float32)
    pos = pos.reshape(position, 1)
    ind = ind.reshape(1, d_model)
    # 计算角度
    def get_angles(pos, i):
        angles = 1 / np.power(10000, (2 * (i // 2)) / np.float32(d_model))
        return pos * angles

    # 计算正弦和余弦
    angle1 = get_angles(pos, ind)
    sin = np.sin(angle1[:, 0::2]) # 0::2表示取偶数列
    cos = np.cos(angle1[:, 1::2]) # 1::2表示取奇数列
    pos_encoding = np.concatenate([sin, cos], axis=-1) #-1表示沿着最后一个维度拼接，按照列横向拼接
    pos_encoding = np.float16(pos_encoding)
    return pos_encoding

if __name__ == "__main__":
    position, d_model = map(int, input().split())
    print(pos_encoding(position, d_model).tolist())
