#参考文章
https://www.cnblogs.com/wanghan1109/p/11195344.html
1.单实例部署按照正常注解方式实现，集群部署需要启用quartz.properties文件配置，启用StartJob.java进行任务的添加启动等操作（不需要 @EnableScheduling 注解）
2.为了保证事务有效，定时任务类中有需要事务的时候，需要对应的service类并在实际的业务处理方法上加@Transactional注解
3.项目启动前需要在对应的数据库执行建表脚本job.sql、tables_mysql_innodb.sql
4.包com.pbs.job.test是测试用例，实际项目可以去掉
5.Java类型 实现内容如此：com.pbs.job.test.scheduler.ButtonTimerJob；
  SQL类型 实现内容如此：<transaction>true</transaction><sql>INSERT INTO `pbs_qrtz_role`(`ROLE_GROUP`, `ROLE_NAME`, `ORDER_INDEX`, `ORG_ID`) VALUES ('zhangsan222', 'zhangsanggggg', '3', '99999')</sql>
  标签<transaction></transaction>内值为 true、false,代表是否需要开启事务；标签<sql></sql>内为要执行的SQL语句，按照sql规范填写。
6.本项目有定义全局异常处理，在代码实现时，如要将异常提示到前台，请做抛出异常处理


