
CREATE DATABASE `generate` 

USE `generate`;


DROP TABLE IF EXISTS `database`;

CREATE TABLE `database` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `database_type` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据库类型',
  `ip` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ip地址',
  `port` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '端口号',
  `database_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数据库名称',
  `username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `driver` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '驱动名称',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态（1、启动 2、禁用）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

