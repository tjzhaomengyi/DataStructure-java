from collections import Counter


def performance_metrics(actual: list[int], predicted: list[int]) -> tuple:
    # Implement your code here
    data = list(zip(actual, predicted))
    counts = Counter(tuple(pair) for pair in data)
    TP, FN, FP, TN = counts[(1, 1)], counts[(1, 0)], counts[(0,1)], counts[(0, 0)]
    confusion_matrix = [[TP, FN], [FP, TN]]
    accuracy = (TP + TN) / len(actual)
    precision = TP / (TP + FP)
    recall = TP / (TP + FN)
    f1 = 2 * precision * recall / (precision + recall)
    specificity = TN / (FP + TN)  # 特异度
    negativePredictive = TN / (TN + FN)  # 负类预测
    return confusion_matrix, round(accuracy, 3), round(f1, 3), round(specificity, 3), round(negativePredictive, 3)


if __name__ == "__main__":
    actual = eval(input())
    predicted = eval(input())
    print(performance_metrics(actual, predicted))