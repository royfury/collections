package com.roy.lessons.mysql;

/**
 * Description: DropTruncateDelete
 *
 *
 * 1  drop table table_name 立刻释放磁盘空间 ，
 *    不管是 Innodb和MyISAM
 *
 * 2  truncate table table_name 立刻释放磁盘空间 ，
 *    不管是 Innodb和MyISAM 。
 *    truncate table其实有点类似于drop table 然后create
 *
 * 3  对于delete from table_name :
 *    删除表的全部数据
 *      MyISAM 会立刻释放磁盘空间 （应该是做了特别处理，也比较合理）
 *      InnoDB 不会释放磁盘空间
 *
 * 4  对于delete from table_name where xxx带条件的删除
 *    不管是innodb还是MyISAM都不会释放磁盘空间
 *
 * 5  delete操作以后 使用optimize table table_name 会立刻释放磁盘空间。
 *    不管是innodb还是myisam 。
 *
 * 6  delete from表以后虽然未释放磁盘空间，但是下次插入数据的时候，仍然可以使用这部分空间。
 *
 * @author lei.zhu
 * @version 2017-08-26 22:00
 */
public class DropTruncateDelete {

}
