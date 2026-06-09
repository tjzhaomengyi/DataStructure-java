"""
一家银行希望通过机器学习技术优化其信用卡申请者的信用评分模型。目前，银行使用的是一个基于逻辑回归的模型，但为了提高预测的准确性，银行希望尝试使用支持向量机（SVM）模型。在构建SVM模型之前，需要对特征进行选择，以确定哪些特征对于信用评分最为重要。请编写一个Python程序，实现一个基于决策树的特征选择方法，以确定最重要的特征。
输入描述：
输入数据为一个二维列表，其中每一行代表一个信用卡申请者的记录，每一列代表一个特征。其中最后一列是目标变量，其中'Good'表示信用良好，'Bad'表示信用不佳，倒数第二列也是字符串特征，其余特征值可以是整数或浮点数。
输出描述：
输出一个整数，最重要的特征的索引（从0开始计数）。

"""

import ast
import numpy as np
from sklearn.tree import DecisionTreeClassifier
from sklearn.preprocessing import LabelEncoder

data = ast.literal_eval(input())
data = np.array(data)

X = data[:, :-1]
y = data[:, -1]

le_y = LabelEncoder()
y_encoded = le_y.fit_transform(y)

X_encoded = np.zeros((X.shape[0], X.shape[1]), dtype=float)
for j in range(X.shape[1]):
    try:
        X_encoded[:, j] = X[:, j].astype(float)
    except (ValueError, TypeError):  # 如果编码的特征列不是数值型的就需要进行转化
        le = LabelEncoder()
        X_encoded[:, j] = le.fit_transform(X[:, j])

clf = DecisionTreeClassifier(random_state=42)
clf.fit(X_encoded, y_encoded)

print(np.argmax(clf.feature_importances_))
