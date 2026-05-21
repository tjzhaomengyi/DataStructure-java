"""
实现压缩行系数矩阵CSR格式转换
CSR格式由三个数组组成：
1、values：按行有限顺序存储的非零元素值
2、column_indices：每个非零元素对应的列索引
3、row_pointer:每行起始位置的数组指针
输入：[[1, 0, 0], [2, 3, 0], [0, 4, 5]]
输出：
values：[1, 2, 3, 4, 5]
column_indices:[0, 0, 1, 1, 2]
row_pointer:[0, 1, 3, 5]
row_pointer解释：第0行从values[0]存在的行开始[0:1)，第1行从value[1]=2开始[1:3)，第2行从values[3]=4的行开始[3:5)，最后一个5是哨兵位表示最后一样结束位置的下一位，这个就是规定这么记录的非常方便
"""
def compressed_row_sparse_matrix(dense_matrix):
    from scipy.sparse import csr_matrix
    sparse = csr_matrix(dense_matrix)
    vals = sparse.data.tolist()
    col_idx = sparse.indices.tolist()
    row_ptr = sparse.indptr.tolist()
    return vals, col_idx, row_ptr


if __name__ == "__main__":
    dense_matrix = eval(input())
    vals, col_idx, row_ptr = compressed_row_sparse_matrix(dense_matrix)
    print(vals)
    print(col_idx)
    print(row_ptr)