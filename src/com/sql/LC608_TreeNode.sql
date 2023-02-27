tree
+----+------+
| id | p_id |
+----+------+
| 1  | null |
| 2  | 1    |
| 3  | 1    |
| 4  | 2    |
| 5  | 2    |
+----+------+

树中每个节点属于以下三种类型之一：

叶子：如果这个节点没有任何孩子节点。
根：如果这个节点是整棵树的根，即没有父节点。
内部节点：如果这个节点既不是叶子节点也不是根节点。

写一个查询语句，输出所有节点的编号和节点的类型，并将结果按照节点编号排序。上面样例的结果为：

--用IF，或者case when

SELECT id,IF(p_id IS NULL, 'Root',
    IF(id in (select p_id FROM tree), 'Inner', 'Leaf')) as type
FROM tree ORDER BY id

SELECT id, CASE
    WHEN p_id IS NULL THEN 'Root'
    WHEN id not in (select p_id FROM tree WHERE p_id IS NOT null) THEN 'Leaf'
    else 'Inner'
    END as type
FROM tree ORDER BY id
