"""
计算均方误差MSE、平均绝对误差、Huber损失和余弦损失
Huber Loss  当误差小于某个阈值时使用MSE，超过该阈值时使用MAE
cos loss=1-y·y^/||y||·||y^||
"""
import numpy as np

def calculate_loss(real_values, predicted_values, delta):
    mse = np.mean((real_values - predicted_values) ** 2)
    mae = np.mean(np.abs(real_values - predicted_values))
    hubers_loss = np.mean(np.where(np.abs(real_values - predicted_values) <= delta, mse, mae))
    #np.linalg用来计算矩阵的范数
    cos_loss = 1 - (real_values @ predicted_values) / (np.linalg.norm(real_values) * np.linalg.norm(predicted_values))
    return round(mse, 6), round(mae, 6), round(hubers_loss, 6), round(cos_loss, 6)


# 从标准输入读取数据
n = int(input())
real_values = []
predicted_values = []

for _ in range(n):
    real, predicted = map(float, input().split())
    real_values.append(real)
    predicted_values.append(predicted)

delta = float(input())  # 读取阈值

# 调用计算损失函数的函数
results = calculate_loss(np.array(real_values), np.array(predicted_values), delta)
# 输出结果
for value in results:
    print(f"{value:.6f}")