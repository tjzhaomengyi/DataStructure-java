import numpy as np
from sklearn.cluster import k_means


def k_means_clustering(points, k, initial_centroids, max_iterations):
    initial_centroids = np.array(initial_centroids)
    # 返回中心点，labels 和 簇距离
    centroid, labels, inertia = k_means(points, k, init=initial_centroids, max_iter=max_iterations)
    ans = []
    for p in centroid:
        p = p.round(4)
        ans.append(tuple(p))
    return ans
