import numpy as np


def descriptive_statistics(data):
    # Your code here
    data = np.array(data)
    mean = np.mean(data)
    median = np.median(data)
    unique, counts = np.unique(data, return_counts=True)
    mode = unique[np.argmax(counts)] if len(data) > 0 else None  # 众数
    variance = np.var(data)
    std_dev = np.sqrt(variance)
    percentiles = np.percentile(data, [25, 50, 75])
    iqr = percentiles[2] - percentiles[0]  # 四分卫距 Q3 - Q1
    stats_dict = {
        "mean": mean,
        "median": median,
        "mode": mode,
        "variance": np.round(variance, 4),
        "standard_deviation": np.round(std_dev, 4),
        "25th_percentile": percentiles[0],
        "50th_percentile": percentiles[1],
        "75th_percentile": percentiles[2],
        "interquartile_range": iqr
    }
    return stats_dict


if __name__ == "__main__":
    data = eval(input())
    print(descriptive_statistics(data))
