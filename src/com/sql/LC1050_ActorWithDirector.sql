ActorDirector
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| actor_id    | int     |
| director_id | int     |
| timestamp   | int     |
+-------------+---------+
timestamp 是这张表的主键.

写一条SQL查询语句获取合作过至少三次的演员和导演的 id 对 (actor_id, director_id)

SELECT actor_id, director_id FROM ActorDirector GROUP BY actor_id, director_id HAVING count(timestamp) >= 3

--不用having更快
select actor_id, director_id FROM
(SELECT actor_id, director_id, count(timestamp) cnt FROM ActorDirector GROUP BY actor_id, director_id) a
WHERE a.cnt >=3