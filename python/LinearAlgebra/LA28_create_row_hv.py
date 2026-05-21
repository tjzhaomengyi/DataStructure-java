import numpy as np

"""
实现一个函数来生成数据集合的复合超向量Composite Hypervector。这是一个使用超维计算HDC的任务，需要通过以下步骤处理数据：
1、为每个特征创建两个基本超向量：
一个表示特征名称，一个表示特征值
2、使用绑定操作bind组合特征名称和值的超向量
3、使用捆绑操作bundle将所有特征的超向量组合成一个复合超向量
"""


def create_hv(dim):
    """生成 ±1 的高维向量"""
    return np.random.choice([-1, 1], dim)


def create_col_hvs(dim, seed):
    """生成一个特征的两个 HV: 特征名和特征值"""
    np.random.seed(seed) # 设置全局随机数生成器的种子，同一个seed 生成的随机访问向量总是一样，每个特征键值对都使用这个种子生成一下
    feat_hv = create_hv(dim)
    val_hv = create_hv(dim)
    return feat_hv, val_hv


def bind(hv1, hv2):
    """绑定操作，逐个元素相乘"""
    return hv1 * hv2

# def sign(vector):
#     return np.array([1 if v >= 0 else -1 for v in vector])

def bundle(hvs):
    """捆绑操作，将读懂个HV加起来再取符号"""
    summed = np.sum(list(hvs.values()), axis=0)
    return np.where(summed >= 0, 1, -1)


def create_row_hv(data_row: dict, dim: int, seeds: dict) -> np.ndarray:
    """
        生成行数据的复合超向量
    :param data_row: {feature_name: feature_value}
    :param dim: 超向量维度
    :param seeds: {feature_name: seed_value, feature_value: seed_value}
    :return:
    """
    # rng = np.random.default_rng()  # 使用新的numpy随机生成器
    row_hvs = {}
    for col in row.keys():
        feat_hv, val_hv = create_col_hvs(dim, seeds[col])
        row_hvs[col] = bind(feat_hv, val_hv)
    return bundle(row_hvs)


if __name__ == "__main__":
    # 输入
    row = {"age": 25, "gender": "male", "income": "high"}
    dim = 1000
    seeds = {
        "age": 42, "25": 100,
        "gender": 7, "male": 200,
        "income": 3, "high": 400
    }

    chv = create_row_hv(row, dim, seeds)
    print(chv)
    print(chv.shape)  # (1000,)
