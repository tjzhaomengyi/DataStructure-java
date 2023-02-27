Trips
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| id          | int      |
| client_id   | int      |
| driver_id   | int      |
| city_id     | int      |
| status      | enum     |
| request_at  | date     |
+-------------+----------+
id 是这张表的主键。
这张表中存所有出租车的行程信息。每段行程有唯一 id ，其中 client_id 和 driver_id 是 Users 表中 users_id 的外键。
status 是一个表示行程状态的枚举类型，枚举成员为(‘completed’, ‘cancelled_by_driver’, ‘cancelled_by_client’) 。

Users
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| users_id    | int      |
| banned      | enum     |
| role        | enum     |
+-------------+----------+
users_id 是这张表的主键。
这张表中存所有用户，每个用户都有一个唯一的 users_id ，role 是一个表示用户身份的枚举类型，枚举成员为 (‘client’, ‘driver’, ‘partner’) 。
banned 是一个表示用户是否被禁止的枚举类型，枚举成员为 (‘Yes’, ‘No’) 。

取消率 的计算方式如下：(被司机或乘客取消的非禁止用户生成的订单数量) / (非禁止用户生成的订单总数)。

写一段 SQL 语句查出 "2013-10-01" 至 "2013-10-03" 期间非禁止用户（乘客和司机都必须未被禁止）的取消率。非禁止用户即 banned 为 No 的用户，禁止用户即 banned 为 Yes 的用户。

返回结果表中的数据可以按任意顺序组织。其中取消率 Cancellation Rate 需要四舍五入保留 两位小数 。

查询结果格式如下例所示。
SELECT * FROM
(SELECT total.DAY as Day,TRUNCATE(IF(a is null,0,a) / b, 2) as `Cancellation Rate`  FROM
    (SELECT request_at AS Day,COUNT(id) b FROM
    (SELECT id, status, request_at FROM Trips
    WHERE client_id  IN (SELECT users_id FROM Users WHERE banned = 'NO')
    AND driver_id  IN (SELECT users_id FROM Users WHERE banned = 'NO' )
    AND request_at) f
    group by request_at) total
    LEFT JOIN
    (SELECT request_at AS Day,(COUNT(id)) a FROM
    (SELECT id, status, request_at FROM Trips
    WHERE client_id IN (SELECT users_id FROM Users WHERE banned = 'NO')
    AND driver_id  IN (SELECT users_id FROM Users WHERE banned = 'NO' )
    AND status != 'completed'
    ) f
    group by request_at) cancel
on total.DAY = cancel.DAY AND total.Day) cnm
WHERE Day BETWEEN '2013-10-01' AND '2013-10-03'