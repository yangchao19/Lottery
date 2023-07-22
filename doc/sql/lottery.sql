/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : lottery

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 04/12/2021 12:36:39
*/

CREATE DATABASE lottery;

USE lottery;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
                            `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                            `activity_id` BIGINT(20) NOT NULL COMMENT '活动ID',
                            `activity_name` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动名称',
                            `activity_desc` VARCHAR(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动描述',
                            `begin_date_time` DATETIME(3) DEFAULT NULL COMMENT '开始时间',
                            `end_date_time` DATETIME(3) DEFAULT NULL COMMENT '结束时间',
                            `stock_count` INT(11) DEFAULT NULL COMMENT '库存',
                            `stock_surplus_count` INT(11) DEFAULT NULL COMMENT '库存剩余',
                            `take_count` INT(11) DEFAULT NULL COMMENT '每人可参与次数',
                            `strategy_id` BIGINT(11) DEFAULT NULL COMMENT '抽奖策略ID',
                            `state` TINYINT(2) DEFAULT NULL COMMENT '活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启',
                            `creator` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
                            `create_time` DATETIME(3) DEFAULT NULL COMMENT '创建时间',
                            `update_time` DATETIME(3) DEFAULT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `unique_activity_id` (`activity_id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='活动配置';

-- ----------------------------
-- Records of activity
-- ----------------------------
BEGIN;
INSERT INTO `activity` VALUES (1, 100001, '活动名', '测试活动', '2021-10-01 00:00:00', '2021-12-30 23:59:59', 100, 94, 10, 10001, 5, 'xiaofuge', '2021-08-08 20:14:50', '2021-08-08 20:14:50');
INSERT INTO `activity` VALUES (3, 100002, '活动名02', '测试活动', '2021-10-01 00:00:00', '2021-12-30 23:59:59', 100, 100, 10, 10001, 5, 'xiaofuge', '2021-10-05 15:49:21', '2021-10-05 15:49:21');
COMMIT;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
                         `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                         `award_id` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品ID',
                         `award_type` TINYINT(4) DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
                         `award_name` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品名称',
                         `award_content` VARCHAR(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
                         `create_time` DATETIME(3) DEFAULT NULL COMMENT '创建时间',
                         `update_time` DATETIME(3) DEFAULT NULL COMMENT '更新时间',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `idx_award_id` (`award_id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='奖品配置';

-- ----------------------------
-- Records of award
-- ----------------------------
BEGIN;
INSERT INTO `award` VALUES (1, '1', 1, 'IMac', 'Code', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `award` VALUES (2, '2', 1, 'iphone', 'Code', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `award` VALUES (3, '3', 1, 'ipad', 'Code', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `award` VALUES (4, '4', 1, 'AirPods', 'Code', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `award` VALUES (5, '5', 1, 'Book', 'Code', '2021-08-15 15:38:05', '2021-08-15 15:38:05');
COMMIT;

-- ----------------------------
-- Table structure for rule_tree
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree`;
CREATE TABLE `rule_tree` (
                             `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                             `tree_name` VARCHAR(64) DEFAULT NULL COMMENT '规则树Id',
                             `tree_desc` VARCHAR(128) DEFAULT NULL COMMENT '规则树描述',
                             `tree_root_node_id` BIGINT(20) DEFAULT NULL COMMENT '规则树根ID',
                             `create_time` DATETIME(3) DEFAULT NULL COMMENT '创建时间',
                             `update_time` DATETIME(3) DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2110081903 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rule_tree
-- ----------------------------
BEGIN;
INSERT INTO `rule_tree` VALUES (2110081902, '抽奖活动规则树', '用于决策不同用户可参与的活动', 1, '2021-10-08 15:38:05', '2021-10-08 15:38:05');
COMMIT;

-- ----------------------------
-- Table structure for rule_tree_node
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node`;
CREATE TABLE `rule_tree_node` (
                                  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `tree_id` INT(2) DEFAULT NULL COMMENT '规则树ID',
                                  `node_type` INT(2) DEFAULT NULL COMMENT '节点类型；1子叶、2果实',
                                  `node_value` VARCHAR(32) DEFAULT NULL COMMENT '节点值[nodeType=2]；果实值',
                                  `rule_key` VARCHAR(16) DEFAULT NULL COMMENT '规则Key',
                                  `rule_desc` VARCHAR(32) DEFAULT NULL COMMENT '规则描述',
                                  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rule_tree_node
-- ----------------------------
BEGIN;
INSERT INTO `rule_tree_node` VALUES (1, 2110081902, 1, NULL, 'userGender', '用户性别[男/女]');
INSERT INTO `rule_tree_node` VALUES (11, 2110081902, 1, NULL, 'userAge', '用户年龄');
INSERT INTO `rule_tree_node` VALUES (12, 2110081902, 1, NULL, 'userAge', '用户年龄');
INSERT INTO `rule_tree_node` VALUES (111, 2110081902, 2, '100001', NULL, NULL);
INSERT INTO `rule_tree_node` VALUES (112, 2110081902, 2, '100002', NULL, NULL);
INSERT INTO `rule_tree_node` VALUES (121, 2110081902, 2, '100003', NULL, NULL);
INSERT INTO `rule_tree_node` VALUES (122, 2110081902, 2, '100004', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for rule_tree_node_line
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node_line`;
CREATE TABLE `rule_tree_node_line` (
                                       `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                       `tree_id` BIGINT(20) DEFAULT NULL COMMENT '规则树ID',
                                       `node_id_from` BIGINT(20) DEFAULT NULL COMMENT '节点From',
                                       `node_id_to` BIGINT(20) DEFAULT NULL COMMENT '节点To',
                                       `rule_limit_type` INT(2) DEFAULT NULL COMMENT '限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围];7:果实',
                                       `rule_limit_value` VARCHAR(32) DEFAULT NULL COMMENT '限定值',
                                       PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rule_tree_node_line
-- ----------------------------
BEGIN;
INSERT INTO `rule_tree_node_line` VALUES (1, 2110081902, 1, 11, 1, 'man');
INSERT INTO `rule_tree_node_line` VALUES (2, 2110081902, 1, 12, 1, 'woman');
INSERT INTO `rule_tree_node_line` VALUES (3, 2110081902, 11, 111, 3, '25');
INSERT INTO `rule_tree_node_line` VALUES (4, 2110081902, 11, 112, 4, '25');
INSERT INTO `rule_tree_node_line` VALUES (5, 2110081902, 12, 121, 3, '25');
INSERT INTO `rule_tree_node_line` VALUES (6, 2110081902, 12, 122, 4, '25');
COMMIT;

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy` (
                            `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                            `strategy_id` BIGINT(11) NOT NULL COMMENT '策略ID',
                            `strategy_desc` VARCHAR(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '策略描述',
                            `strategy_mode` TINYINT(2) DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
                            `grant_type` TINYINT(2) DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
                            `grant_date` DATETIME(3) DEFAULT NULL COMMENT '发放奖品时间',
                            `ext_info` VARCHAR(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '扩展信息',
                            `create_time` DATETIME(3) DEFAULT NULL  COMMENT '创建时间',
                            `update_time` DATETIME(3) DEFAULT NULL  COMMENT '修改时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `strategy_strategyId_uindex` (`strategy_id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='策略配置';

-- ----------------------------
-- Records of strategy
-- ----------------------------
BEGIN;
INSERT INTO `strategy` VALUES (1, 10001, 'test', 2, 1, NULL, '', '2021-09-25 08:15:52', '2021-09-25 08:15:52');
COMMIT;

-- ----------------------------
-- Table structure for strategy_detail
-- ----------------------------
DROP TABLE IF EXISTS `strategy_detail`;
CREATE TABLE `strategy_detail` (
                                   `id` BIGINT(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                   `strategy_id` BIGINT(11) NOT NULL COMMENT '策略ID',
                                   `award_id` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品ID',
                                   `award_name` VARCHAR(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品描述',
                                   `award_count` INT(11) DEFAULT NULL COMMENT '奖品库存',
                                   `award_surplus_count` INT(11) DEFAULT '0' COMMENT '奖品剩余库存',
                                   `award_rate` DECIMAL(5,2) DEFAULT NULL COMMENT '中奖概率',
                                   `create_time` DATETIME(3) DEFAULT NULL COMMENT '创建时间',
                                   `update_time` DATETIME(3) DEFAULT NULL COMMENT '修改时间',
                                   PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='策略明细';

-- ----------------------------
-- Records of strategy_detail
-- ----------------------------
BEGIN;
INSERT INTO `strategy_detail` VALUES (1, 10001, '1', 'IMac', 10, 0, 0.05, '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `strategy_detail` VALUES (2, 10001, '2', 'iphone', 20, 19, 0.15, '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `strategy_detail` VALUES (3, 10001, '3', 'ipad', 50, 43, 0.20, '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `strategy_detail` VALUES (4, 10001, '4', 'AirPods', 100, 70, 0.25, '2021-08-15 15:38:05', '2021-08-15 15:38:05');
INSERT INTO `strategy_detail` VALUES (5, 10001, '5', 'Book', 500, 389, 0.35, '2021-08-15 15:38:05', '2021-08-15 15:38:05');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
