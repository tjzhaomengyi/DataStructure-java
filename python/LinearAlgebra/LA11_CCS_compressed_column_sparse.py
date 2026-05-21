def compressed_col_sparse_matrix(dense_matrix):
    from scipy.sparse import csc_matrix
    csc = csc_matrix(dense_matrix)
    vals = csc.data.tolist()
    row_idx = csc.indices.tolist()
    col_ptr = csc.indptr.tolist()
    return vals, row_idx, col_ptr


if __name__ == "__main__":
    dense_matrix = eval(input())
    vals, row_idx, col_ptr = compressed_col_sparse_matrix(dense_matrix)
    print(vals)
    print(row_idx)
    print(col_ptr)