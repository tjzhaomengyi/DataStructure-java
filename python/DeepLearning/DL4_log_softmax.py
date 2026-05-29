import numpy as np
import math

def log_softmax(scores: list) -> np.ndarray:
    exp_score = [math.exp(s) for s in scores]
    sum_e = sum(exp_score)
    res = np.array([np.log(es / sum_e) for es in exp_score])
    return res

if __name__ == "__main__":
    scores = eval(input())
    print(log_softmax(scores))
