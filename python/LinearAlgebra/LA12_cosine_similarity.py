import numpy as np

def cosine_similarity(v1, v2):
    # Implement your code here
    import numpy as np
    v1 = np.array(v1)
    v2 = np.array(v2)
    dot = v1 @ v2
    norm_v1 = np.linalg.norm(v1, ord=2)
    norm_v2 = np.linalg.norm(v2, ord=2)
    norm = norm_v1 * norm_v2
    val = dot / norm
    return round(val, 3)
def cosine_similarity_easily(v1, v2):
    from scipy.spatial.distance import cosine
    val = 1 - cosine(v1, v2)  # cosine是余弦距离，相似度需要用1减去距离
    return round(val, 3)


if __name__ == "__main__":
    v1 = np.array(eval(input()))
    v2 = np.array(eval(input()))
    print(cosine_similarity(v1, v2))