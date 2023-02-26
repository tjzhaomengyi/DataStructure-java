Users
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| name          | varchar |
+---------------+---------+
id 是该表单主键。
name 是用户名字。

Rides
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| user_id       | int     |
| distance      | int     |
+---------------+---------+
id 是该表单主键。
user_id 是本次行程的用户的 id, 而该用户此次行程距离为 distance 。
写一段 SQL , 报告每个用户的旅行距离。

返回的结果表单，以 travelled_distance 降序排列 ，如果有两个或者更多的用户旅行了相同的距离, 那么再以 name 升序排列 。

查询结果格式如下例所示。

--太难了直接记住例子吧，【注意】From后面要隔开看，是User表示Join 后面那个一部分临时表，这个是分join的关键
select  name,IF(travelled_distance is null,0,travelled_distance) travelled_distance  from
Users left join (SELECT user_id, sum(distance) travelled_distance from Rides group by user_id) b
 on id = user_id
order by travelled_distance desc,name
