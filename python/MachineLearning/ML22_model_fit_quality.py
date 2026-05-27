def model_fit_quality(training_accuracy, test_accuracy):
    """
    训练准确率显著高于测试准确率，大于0.2 模型过拟合
    训练和测试准确率均低于0.7，模型欠拟合
    训练和测试准确率差异不大，模型表现良好
    """
    if training_accuracy - test_accuracy > 0.2:
        return 1 # 过拟合
    elif training_accuracy < 0.7 and test_accuracy < 0.7:
        return -1 # 欠拟合
    else:
        return 0