package com.roy.lessons.mysql;

/**
 * Description: IsolationLevel
 *
 * @author lei.zhu
 * @version 2017-08-26 17:55
 *
 *
 * READ_UNCOMMITTED : 未提交读。
 *
 * 另一个事务修改了数据，但尚未提交，
 * 而本事务中的SELECT会读到这些未被提交的数据（脏读）。
 *
 *
 * READ_COMMITTED : 提交读。
 *
 * 本事务读取到的是最新的数据（其他事务提交后的）。
 * 问题是，在同一个事务里，
 * 前后两次相同的SELECT会读到不同的结果（不重复读）。
 *
 *
 * REPEATABLE_READ : 可重复读。
 * 在同一个事务里，SELECT的结果是事务开始时时间点的状态，
 * 因此，同样的SELECT操作读到的结果会是一致的。
 * 但是，会有幻读现象（稍后解释）。
 *
 *
 * SERIALIZABLE : 串行化。
 * 读操作会隐式获取共享锁，可以保证不同事务间的互斥
 *
 *
 *
 * 幻读 : 解决了不重复读，保证了同一个事务里，查询的结果都是事务开始时的状态（一致性）。
 * 但是，如果另一个事务同时提交了新数据，本事务再更新时，
 * 就会“惊奇的”发现了这些新数据，貌似之前读到的数据是“鬼影”一样的幻觉。
 * 比如，duplicate key
 *
 *
 * InnoDB 通过 gap锁 解决幻读，但是只有 当前读 会加 gap锁
 * 当前读：
 * select * from table for update;
 * select * from table lock in share mode;
 *
 * for update : X 锁，不允许其他事务进行当前读
 * lock in share mode : S 锁，允许其他事务进行当前读(lock in share mode)
 *
 *
 *
 * mysql> show create table test;
 * +-------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------+
 * | Table | Create Table                                                                          |
 * +-------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------+
 * | test  | CREATE TABLE `test` (
 *  `id` int(10) NOT NULL AUTO_INCREMENT,
 *  `code` varchar(20) NOT NULL DEFAULT '',
 *  `name` varchar(20) NOT NULL DEFAULT '',
 *  `idx` int(10) NOT NULL DEFAULT '0',
 *  PRIMARY KEY (`id`),
 *  UNIQUE KEY `uniq_code` (`code`),
 *  UNIQUE KEY `uniq_idx` (`idx`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8 |
 * +-------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------+
 * 1 row in set (0.00 sec)
 *
 *
 * Transaction A                                                        * Transaction B
 *                                                                      *
 * begin;                                                               * begin;
 *                                                                      *
 * mysql> select * from test;                                           *
 * +-----+--------+--------+-----+                                      *
 * | id  | code   | name   | idx |                                      *
 * +-----+--------+--------+-----+                                      *
 * | 249 | haha   | fury   |   3 |                                      *
 * | 250 | test   | test   |   0 |                                      *
 * | 251 | hehe   | fury   |  10 |                                      *
 * | 252 | test1  | test1  |   6 |                                      *
 * | 253 | heihei | fury   |   8 |                                      *
 * | 256 | test2  | test2  |   5 |                                      *
 * | 257 | hoho   | cooper |   9 |                                      *
 * +-----+--------+--------+-----+                                      *
 * 7 rows in set (0.00 sec)                                             *
 *                                                                      *
 *                                                                      *
 * 进行当前读，加 X 锁                                                     *
 * 该操作会在索引 idx 上加 row lock & gap lock                             *
 * 同时会在 主键 索引上加 row lock                                          *
 * mysql> select * from test where idx >=5 and idx <= 10 for update;    * 等待锁超时
 * +-----+--------+--------+-----+                                      * insert into test(id, code, name, idx) values(254, 'test5', 'test5', 1);
 * | id  | code   | name   | idx |                                      * ERROR 1205 (HY000): Lock wait timeout exceeded; try restarting transaction
 * +-----+--------+--------+-----+                                      *
 * | 251 | hehe   | fury   |  10 |                                      *
 * | 252 | test1  | test1  |   6 |                                      *
 * | 253 | heihei | fury   |   8 |                                      *
 * | 256 | test2  | test2  |   5 |                                      *
 * | 257 | hoho   | cooper |   9 |                                      *
 * +-----+--------+--------+-----+                                      *
 * 5 rows in set (0.00 sec)                                             *
 *
 *
 *
 *
 *
 *
 *
 */

public class IsolationLevel {

}
