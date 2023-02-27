Employee
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
Id是该表的主键。
该表的每一行都表示雇员的ID、姓名、工资和经理的ID。
编写一个SQL查询来查找收入比经理高的员工。

以 任意顺序 返回结果表。

查询结果格式如下所示。

SELECT  name Employee from
    Employee left join (select id mid, salary msal from Employee) m on managerId = m.mid
where salary > m.msal