"""
将一组数值转换为概率分布，softmax函数常用于神经网络的多分类问题，将任意实数转换为(0,1)区间内的实数，并且转换后所有值的和为1
"""
import math


def softmax(scores: list[float]) -> list[float]:
    exp_scores = [math.exp(score) for score in scores]
    sum_exp_scores = sum(exp_scores)
    probabilities = [round(score / sum_exp_scores, 4) for score in exp_scores]
    return probabilities