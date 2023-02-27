Employees
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| name        | varchar |
+-------------+---------+
employee_id 是这个表的主键。
每一行表示雇员的id 和他的姓名。

Salaries
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| employee_id | int     |
| salary      | int     |
+-------------+---------+
employee_id is 这个表的主键。
每一行表示雇员的id 和他的薪水。

写出一个查询语句，找到所有 丢失信息 的雇员id。当满足下面一个条件时，就被认为是雇员的信息丢失：

雇员的 姓名 丢失了，或者
雇员的 薪水信息 丢失了，或者
返回这些雇员的id  employee_id ， 从小到大排序 。

查询结果格式如下面的例子所示。

SELECT employee_id FROM Employees where employee_id not in
(select e.employee_id FROM Employees e join Salaries s on e.employee_id = s.employee_id)
union
SELECT employee_id FROM salary where employee_id not in
(select e.employee_id FROM Employees e join Salaries s on e.employee_id = s.employee_id)
order by employee_id

