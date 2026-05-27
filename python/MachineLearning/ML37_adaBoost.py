import numpy as np
import math
def adaboost_fit(X, y, n_clf):
    n_samples, n_features = np.shape(X)
    w = np.full(n_samples, (1 / n_samples))
    clfs = []

    for _ in range(n_clf):
        clf = {}
        min_error = float('inf')

        for feature_i in range(n_features):
            feature_values = np.expand_dims(X[:, feature_i], axis=1)
            unique_values = np.unique(feature_values)

            for threshold in unique_values:
                p = 1
                prediction = np.ones(np.shape(y))
                prediction[X[:, feature_i] < threshold] = -1
                error = sum(w[y != prediction])

                if error > 0.5:
                    error = 1 - error
                    p = -1

                if error < min_error:
                    clf['polarity'] = p
                    clf['threshold'] = threshold
                    clf['feature_index'] = feature_i
                    min_error = error

        clf['alpha'] = 0.5 * math.log((1.0 - min_error) / (min_error + 1e-10))
        predictions = np.ones(np.shape(y))
        if clf['polarity'] == 1:
            predictions[X[:, clf['feature_index']] < clf['threshold']] = -1
        else:
            predictions[X[:, clf['feature_index']] > clf['threshold']] = -1
        w *= np.exp(-clf['alpha'] * y * predictions)
        w /= np.sum(w)
        clfs.append(clf)
    return clfs

if __name__ == "__main__":
    X = np.array(eval(input()))
    y = np.array(eval(input()))
    n_clf = int(input())
    clfs = adaboost_fit(X, y, n_clf)
    print(clfs)





