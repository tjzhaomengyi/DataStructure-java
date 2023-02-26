Employee
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
id 是这个表的主键。
表的每一行包含员工的工资信息。
编写一个 SQL 查询，获取并返回 Employee 表中第二高的薪水 。如果不存在第二高的薪水，查询应该返回 null 。

--获取第n条记录就是limit n-1, 1

select IF(COUNT(salary) > 1, salary, null) FROM
(SELECT distinct salary
from Employee order by salary DESC) a
limit 1,1