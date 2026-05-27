"""
鸢尾花分类
"""
from sklearn.datasets import load_iris
from sklearn.linear_model import LogisticRegression
import numpy as np
import random

# 加载数据集
iris = load_iris()
X = iris.data
y = iris.target

n = int(input())

random.seed(42)
indices = list(range(len(X)))  # 创建索引列表
random.shuffle(indices)  # 洗牌,就是为了打乱顺序
X = X[indices]
y = y[indices]

X_train = X[:-n]
X_test = X[-n:]
y_train = y[:-n]
y_test = y[-n:]

# 使用逻辑回归训练
model = LogisticRegression(max_iter=200)
model.fit(X_train, y_train)

y_pred = model.predict(X_test)  # 预测类别
y_prob = model.predict_proba(X_test)  # 预测最大概率

for i in range(len(y_pred)):
    print(f'{iris.target_names[y_pred[i]]} {np.max(y_prob[i]):.2f}')