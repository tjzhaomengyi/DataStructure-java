"""在机器学习中，基尼系数指的是基尼不纯度 决策树cart使用的是1-Σpi²"""


def gini_impurity(y: list[int]) -> float:
    classes = set(y)
    n = len(y)
    gini_impurity = 0
    for cls in classes:
        gini_impurity += (y.count(cls) / n) ** 2
    return round(1 - gini_impurity, 3)
