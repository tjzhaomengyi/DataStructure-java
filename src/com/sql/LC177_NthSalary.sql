Employee
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
Id是该表的主键列。
该表的每一行都包含有关员工工资的信息。

编写一个SQL查询来报告 Employee 表中第 n 高的工资。如果没有第 n 个最高工资，查询应该报告为 null 。

查询结果格式如下所示。

--注意limit不支持后面加减法
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
RETURN (
    # Write your MySQL query statement below.
    SELECT DISTINCT salary FROM
    (SELECT salary, dense_rank() over(ORDER BY salary DESC) as rnk FROM Employee) a
    WHERE rnk = N
    );
END