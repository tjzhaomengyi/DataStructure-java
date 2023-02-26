Logs
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| num         | varchar |
+-------------+--------
id 是这个表的主键。

编写一个 SQL 查询，查找所有至少连续出现三次的数字。

返回的结果表中的数据可以按 任意顺序 排列。

查询结果格式如下面的例子所示：

--窗口函数LAG()从当前行往前看 和 LEAD()从当前行往后看 使用
SELECT distinct num  ConsecutiveNums FROM
    (SELECT id, num,
            LAG(num, 1)  OVER(ORDER BY id) num1,
            LAG(num, 2)  OVER(ORDER BY id) num2
     FROM Logs)a
WHERE num = num1 AND num = num2;