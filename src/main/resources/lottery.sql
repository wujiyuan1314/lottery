/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : lottery

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-07-08 17:15:40
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `award_info`
-- ----------------------------
DROP TABLE IF EXISTS `award_info`;
CREATE TABLE `award_info` (
  `id` int(11) NOT NULL,
  `award_name` varchar(100) DEFAULT NULL COMMENT '奖品名字',
  `award_grade` varchar(20) DEFAULT NULL COMMENT '奖品等级',
  `award_num` int(11) DEFAULT NULL COMMENT '奖品数量',
  `probability` int(11) DEFAULT NULL COMMENT '中奖几率',
  `addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of award_info
-- ----------------------------

-- ----------------------------
-- Table structure for `award_record`
-- ----------------------------
DROP TABLE IF EXISTS `award_record`;
CREATE TABLE `award_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '手机号',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `awareid` int(11) DEFAULT NULL COMMENT '奖品ID',
  `state` varchar(20) DEFAULT NULL COMMENT '本周是否中过奖',
  `addtime` datetime DEFAULT NULL COMMENT '中奖时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of award_record
-- ----------------------------

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_title` varchar(200) DEFAULT NULL COMMENT '问题标题',
  `type` varchar(20) DEFAULT NULL COMMENT '问题类型',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for `question_answer`
-- ----------------------------
DROP TABLE IF EXISTS `question_answer`;
CREATE TABLE `question_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questionid` int(11) DEFAULT NULL COMMENT '问题的ID',
  `answerdetail` varchar(200) DEFAULT NULL COMMENT '答案详细',
  `isright` varchar(2) DEFAULT NULL COMMENT '是否正确答案（0：不是， 1：是）',
  `addtime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_answer
-- ----------------------------
