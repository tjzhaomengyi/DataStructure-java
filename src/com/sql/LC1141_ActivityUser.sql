Activity
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| session_id    | int     |
| activity_date | date    |
| activity_type | enum    |
+---------------+---------+
该表是用户在社交网站的活动记录。
该表没有主键，可能包含重复数据。
activity_type 字段为以下四种值 ('open_session', 'end_session', 'scroll_down', 'send_message')。
每个 session_id 只属于一个用户。

请写SQL查询出截至 2019-07-27（包含2019-07-27），近 30 天的每日活跃用户数（当天只要有一条活动记录，即为活跃用户）。

SELECT activity_date day, COUNT(distinct user_id) active_users FROM Activity
WHERE activity_date <= '2019-07-27' AND activity_date > DATE_SUB('2019-07-27', INTERVAL 30 DAY)
GROUP  BY activity_date

SELECT activity_date day, COUNT(distinct user_id) active_users FROM Activity
WHERE activity_date between  DATE_SUB('2019-07-27', INTERVAL 30 DAY) AND '2019-07-27'
GROUP  BY activity_date