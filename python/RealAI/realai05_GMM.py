"""
你是一家互联网大厂的数据科学家，正在参与一个客户细分项目。你的任务是基于客户的行为数据，识别出不同类型的客户群体，以便制定个性化的营销策略。由于数据具有复杂的分布形态，传统的聚类算法（如 K-Means）无法有效地捕捉数据的潜在结构。你决定使用高斯混合模型（Gaussian Mixture Model, GMM）来对数据进行聚类。
请你编写一个程序，使用 NumPy 和 SciPy 库，对给定的二维数据集进行基于期望最大化算法（EM算法）的高斯混合模型聚类。具体要求如下：



    1.    读取输入数据集，包含 ( N ) 个数据点，每个数据点有两个特征（二维坐标）。

    2.    读取高斯混合模型的参数：聚类数 ( K )、迭代次数 ( T )。

    3.    初始化模型参数：

        •    均值向量  初始化为随机从数据点中选择。

        •    协方差矩阵 初始化为单位矩阵，不需要对协方差矩阵正则化。

        •    混合系数 初始化为均等分布。

    4.    使用 EM 算法迭代 ( T ) 次，每次包括：

        •    E 步骤：计算每个数据点属于每个高斯成分的后验概率（责任度）。

        •    M 步骤：更新模型参数 ()。

    5.    在迭代完成后，根据最大后验概率，为每个数据点分配聚类标签。

    6.    输出每个数据点的聚类标签，标签为从 0 开始的整数。

输入描述：
    •    第一行包含一个整数 ( N )，表示数据点的数量。
    •    接下来的 ( N ) 行，每行包含两个浮点数，表示数据点的二维坐标，用空格分隔。
    •    最后一行包含两个整数 ( K ) 和 ( T )，用空格分隔。
输出描述：
    •    输出 ( N ) 行，每行包含一个整数，表示对应数据点的聚类标签。


 GMM的思想是整个数据集 =  k个高斯分布混合而成
 每个高斯分布有均值μ，协方差矩阵Σ 和 混合系数Π。
 EM算法负责不断迭代：E步计算每个样本属于每个高斯分布的概率，M步根据这些概率重新估计参数
"""
import numpy as np

class GMM:
    def __init__(self, N, K, T, data):
        """初始化：
            N=样本数
            K=高斯分量个数
            T=EM迭代次数
            data=数据集
        """
        self.N = N
        self.K = K
        self.T = T
        self.data = data
        self.D = self.data.shape[1]  # 表示特征维度

        np.random.seed(0)
        # 初始化均值， 从N个集合中选出K个样本，作为初始聚类中心
        self.mu = self.data[np.random.choice(self.N, self.K, replace=False)]
        # 创建协方差矩阵 K个维度为D*D的协方差矩阵
        self.Sigma = np.zeros([self.K, self.D, self.D])
        # 初始化每个协方差矩阵，为对角矩阵
        for ii in range(self.K):
            self.Sigma[ii] = np.eye(self.D)
        # 初始化混合系数
        self.pi = np.ones(self.K) / self.K
        # γ矩阵
        self.gamma = np.zeros([self.N, self.K])

    def e_step(self):
        for kk in range(self.K):  # 遍历每个高斯分量
            Sigma_det = np.linalg.det(self.Sigma[kk]) # 计算协方差行列式
            Sigma_inv = np.linalg.inv(self.Sigma[kk])  # 计算协方差逆矩阵
            div_Sigma = 1.0 / np.sqrt(Sigma_det * (2*np.pi) ** self.D) #高斯分布前半部分
            for ii in range(self.N): # 遍历样本
                x_mu = self.data[ii] - self.mu[kk]  # 计算 x-μ
                # 计算马氏距离 和 高斯概率密度，最终计算完整概率
                self.gamma[ii, kk] = self.pi[kk] * div_Sigma * np.exp(-0.5 * x_mu @ Sigma_inv @ x_mu)
        # 概率归一化
        sum_gamma = np.sum(self.gamma, axis=1)
        for ii in range(self.N):
            self.gamma[ii] = self.gamma[ii] / sum_gamma[ii] + 1e-6
        return

    def m_step(self):
        Nk = np.sum(self.gamma, axis=0) # 计算Nk，表示第k个高斯分量，实际负责多少样本
        self.pi = Nk / self.N  # 更新Π
        for kk in range(self.K):
            # 更新μ值
            self.mu[kk] = np.sum(self.gamma[:, kk:kk+1] * self.data, axis=0) / Nk[kk]
            # 更新协方差
            x_mu = self.data - self.mu[kk]
            Sigmak = self.gamma[:, kk] * x_mu.T @ x_mu
            self.Sigma[kk] = Sigmak / Nk[kk]
        return

    #EM循环
    def update_loop(self):
        for _ in range(self.T):
            self.e_step()
            self.m_step()
        return

    def det_label(self):
        self.e_step()
        # 取最大概率所属簇
        labels = np.argmax(self.gamma, axis=1)
        for ii in range(self.N):
            print(labels[ii])

if __name__ == '__main__':
    N_read = int(input())
    data_read = []
    for iii in range(N_read):
        x_read, y_read = map(float, input().split())
        data_read.append([x_read, y_read])
    data_read = np.array(data_read)
    K_read, T_read = map(int, input().split())
    gmm = GMM(N_read, K_read, T_read, data_read)
    gmm.update_loop()
    gmm.det_label()
