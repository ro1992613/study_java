/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : renren_plus

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2018-12-11 17:52:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `channel_id` bigint(20) DEFAULT NULL COMMENT '栏目id',
  `type_id` bigint(20) DEFAULT NULL COMMENT '类型id',
  `title` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `publish_date` datetime NOT NULL COMMENT '发布日期',
  `summary` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '简介',
  `author_id` bigint(20) DEFAULT NULL COMMENT '作者id',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态(0:草稿;1:审核中;2:发布;3:回收站;-1:删除)',
  `views_count` int(11) NOT NULL DEFAULT '0' COMMENT '访问数',
  `comments_count` int(6) NOT NULL DEFAULT '0' COMMENT '评论数',
  `keywords` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '关键词，用逗号隔开',
  `feature` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '特征（保留）',
  PRIMARY KEY (`id`),
  KEY `fk_channel_id` (`channel_id`),
  KEY `fk_type_id` (`type_id`),
  KEY `fk_author_id` (`author_id`),
  CONSTRAINT `fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `sys_user` (`user_id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_channel_id` FOREIGN KEY (`channel_id`) REFERENCES `cms_channel` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `cms_type` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES ('1', '1', '0', '测试', '2018-12-11 09:14:46', '测试', '1', '2', '0', '0', '测试', '测试');
INSERT INTO `cms_article` VALUES ('2', '1', '0', '测试1', '2018-12-11 16:56:30', '测试1', '1', '1', '0', '0', '测试1', '测试1');

-- ----------------------------
-- Table structure for cms_channel
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sort` int(6) DEFAULT NULL,
  `channel_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `channel_info` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cms_channel
-- ----------------------------
INSERT INTO `cms_channel` VALUES ('1', '1', '娱乐', '娱乐');
INSERT INTO `cms_channel` VALUES ('2', '2', '科技', '科技');

-- ----------------------------
-- Table structure for cms_content
-- ----------------------------
DROP TABLE IF EXISTS `cms_content`;
CREATE TABLE `cms_content` (
  `id` bigint(20) NOT NULL COMMENT '文章id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '文章内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cms_content
-- ----------------------------
INSERT INTO `cms_content` VALUES ('1', 0x647367736764);
INSERT INTO `cms_content` VALUES ('2', 0xE794B5E59586E585ACE58FB8);

-- ----------------------------
-- Table structure for cms_type
-- ----------------------------
DROP TABLE IF EXISTS `cms_type`;
CREATE TABLE `cms_type` (
  `id` bigint(20) NOT NULL,
  `type_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `type_info` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cms_type
-- ----------------------------
INSERT INTO `cms_type` VALUES ('0', '文章', '文章');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', null, 'io.renren.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017400047465737474000672656E72656E74000FE69C89E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', null, 'io.renren.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C4607874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000574657374327074000FE697A0E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'SC-2018090401121544519671043', '1544521955905', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1544522400000', '-1', '5', 'WAITING', 'CRON', '1543393835000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158BAF593307874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017400047465737474000672656E72656E74000FE69C89E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1543395600000', '-1', '5', 'PAUSED', 'CRON', '1543393835000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000158C377C4607874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000574657374327074000FE697A0E58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000017800);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'renren', '0 0/30 * * * ?', '0', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES ('1', '1', 'testTask', 'test', 'renren', '0', null, '1012', '2018-11-28 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('2', '1', 'testTask', 'test', 'renren', '0', null, '1009', '2018-11-28 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('3', '1', 'testTask', 'test', 'renren', '0', null, '1016', '2018-11-28 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('4', '1', 'testTask', 'test', 'renren', '0', null, '1015', '2018-11-29 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('5', '1', 'testTask', 'test', 'renren', '0', null, '1026', '2018-11-29 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('6', '1', 'testTask', 'test', 'renren', '0', null, '1006', '2018-11-29 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('7', '1', 'testTask', 'test', 'renren', '0', null, '1029', '2018-11-29 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('8', '1', 'testTask', 'test', 'renren', '0', null, '1011', '2018-11-29 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('9', '1', 'testTask', 'test', 'renren', '0', null, '1006', '2018-11-29 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('10', '1', 'testTask', 'test', 'renren', '0', null, '1015', '2018-11-29 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('11', '1', 'testTask', 'test', 'renren', '0', null, '1004', '2018-11-29 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('12', '1', 'testTask', 'test', 'renren', '0', null, '1033', '2018-11-29 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('13', '1', 'testTask', 'test', 'renren', '0', null, '1006', '2018-11-29 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('14', '1', 'testTask', 'test', 'renren', '0', null, '1013', '2018-11-29 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('15', '1', 'testTask', 'test', 'renren', '0', null, '1030', '2018-12-10 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('16', '1', 'testTask', 'test', 'renren', '0', null, '1008', '2018-12-10 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('17', '1', 'testTask', 'test', 'renren', '0', null, '1005', '2018-12-10 17:30:00');
INSERT INTO `schedule_job_log` VALUES ('18', '1', 'testTask', 'test', 'renren', '0', null, '1012', '2018-12-10 18:00:00');
INSERT INTO `schedule_job_log` VALUES ('19', '1', 'testTask', 'test', 'renren', '0', null, '1010', '2018-12-11 09:30:00');
INSERT INTO `schedule_job_log` VALUES ('20', '1', 'testTask', 'test', 'renren', '0', null, '1021', '2018-12-11 10:00:00');
INSERT INTO `schedule_job_log` VALUES ('21', '1', 'testTask', 'test', 'renren', '0', null, '1017', '2018-12-11 10:30:00');
INSERT INTO `schedule_job_log` VALUES ('22', '1', 'testTask', 'test', 'renren', '0', null, '1005', '2018-12-11 11:00:00');
INSERT INTO `schedule_job_log` VALUES ('23', '1', 'testTask', 'test', 'renren', '0', null, '1015', '2018-12-11 11:30:00');
INSERT INTO `schedule_job_log` VALUES ('24', '1', 'testTask', 'test', 'renren', '0', null, '1007', '2018-12-11 12:00:00');
INSERT INTO `schedule_job_log` VALUES ('25', '1', 'testTask', 'test', 'renren', '0', null, '1012', '2018-12-11 12:30:00');
INSERT INTO `schedule_job_log` VALUES ('26', '1', 'testTask', 'test', 'renren', '0', null, '1008', '2018-12-11 13:30:00');
INSERT INTO `schedule_job_log` VALUES ('27', '1', 'testTask', 'test', 'renren', '0', null, '1007', '2018-12-11 14:00:00');
INSERT INTO `schedule_job_log` VALUES ('28', '1', 'testTask', 'test', 'renren', '0', null, '1012', '2018-12-11 14:30:00');
INSERT INTO `schedule_job_log` VALUES ('29', '1', 'testTask', 'test', 'renren', '0', null, '1011', '2018-12-11 15:00:00');
INSERT INTO `schedule_job_log` VALUES ('30', '1', 'testTask', 'test', 'renren', '0', null, '1007', '2018-12-11 15:30:00');
INSERT INTO `schedule_job_log` VALUES ('31', '1', 'testTask', 'test', 'renren', '0', null, '1007', '2018-12-11 16:00:00');
INSERT INTO `schedule_job_log` VALUES ('32', '1', 'testTask', 'test', 'renren', '0', null, '1027', '2018-12-11 16:30:00');
INSERT INTO `schedule_job_log` VALUES ('33', '1', 'testTask', 'test', 'renren', '0', null, '1006', '2018-12-11 17:00:00');
INSERT INTO `schedule_job_log` VALUES ('34', '1', 'testTask', 'test', 'renren', '0', null, '1031', '2018-12-11 17:30:00');

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '人人开源集团', '0', '0');
INSERT INTO `sys_dept` VALUES ('2', '1', '长沙分公司', '1', '0');
INSERT INTO `sys_dept` VALUES ('3', '1', '上海分公司', '2', '0');
INSERT INTO `sys_dept` VALUES ('4', '3', '技术部', '0', '0');
INSERT INTO `sys_dept` VALUES ('5', '3', '销售部', '1', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', 'admin', '修改菜单', 'io.renren.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":31,\"parentId\":1,\"name\":\"部门管理\",\"url\":\"sys/dept\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":1}]', '19', '127.0.0.1', '2018-11-28 16:31:42');
INSERT INTO `sys_log` VALUES ('2', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"createUserId\":1,\"roleId\":1,\"roleName\":\"系统管理员\",\"remark\":\"系统管理员\",\"deptId\":1,\"deptName\":\"人人开源集团\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,31,32,33,34,35,-666666],\"deptIdList\":[1,2,3,4,5,-666666],\"createTime\":\"Nov 28, 2018 4:32:25 PM\"}]', '113', '127.0.0.1', '2018-11-28 16:32:25');
INSERT INTO `sys_log` VALUES ('3', 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', '125', '127.0.0.1', '2018-11-28 17:01:14');
INSERT INTO `sys_log` VALUES ('4', 'admin', '保存用户', 'io.renren.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"root\",\"password\":\"54a8595f6c2d41e705d0f5f8b069cb435371c00600eb5f280e87356011c27de3\",\"salt\":\"EcJK1QbN0ZdnxYWVeR4N\",\"email\":\"ro@qq.com\",\"mobile\":\"13333333333\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Nov 29, 2018 9:53:10 AM\"}]', '129', '127.0.0.1', '2018-11-29 09:53:10');
INSERT INTO `sys_log` VALUES ('5', 'admin', '保存角色', 'io.renren.modules.sys.controller.SysRoleController.save()', '[{\"createUserId\":1,\"roleId\":2,\"roleName\":\"长沙分公司\",\"remark\":\"长沙分公司\",\"deptId\":2,\"deptName\":\"长沙分公司\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,31,32,33,34,35,-666666],\"deptIdList\":[2,-666666,1],\"createTime\":\"Nov 29, 2018 9:53:56 AM\"}]', '55', '127.0.0.1', '2018-11-29 09:53:56');
INSERT INTO `sys_log` VALUES ('6', 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"root\",\"salt\":\"EcJK1QbN0ZdnxYWVeR4N\",\"email\":\"ro@qq.com\",\"mobile\":\"13333333333\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1}]', '15', '127.0.0.1', '2018-11-29 09:54:06');
INSERT INTO `sys_log` VALUES ('7', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":36,\"parentId\":29,\"name\":\"t1\",\"url\":\"\",\"perms\":\"sys:test:t1\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '9', '127.0.0.1', '2018-11-29 09:57:04');
INSERT INTO `sys_log` VALUES ('8', 'admin', '修改角色', 'io.renren.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"roleId\":2,\"roleName\":\"长沙分公司\",\"remark\":\"长沙分公司\",\"deptId\":2,\"deptName\":\"长沙分公司\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,36,30,31,32,33,34,35,-666666],\"deptIdList\":[2,-666666,1]}]', '37', '127.0.0.1', '2018-11-29 09:57:49');
INSERT INTO `sys_log` VALUES ('9', 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@renren.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"deptId\":1}]', '153', '127.0.0.1', '2018-11-29 14:38:34');
INSERT INTO `sys_log` VALUES ('10', 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"root\",\"salt\":\"EcJK1QbN0ZdnxYWVeR4N\",\"email\":\"ro@qq.com\",\"mobile\":\"13333333333\",\"status\":1,\"roleIdList\":[2],\"createUserId\":1,\"deptId\":4}]', '181', '127.0.0.1', '2018-11-29 14:43:14');
INSERT INTO `sys_log` VALUES ('11', 'admin', '删除菜单', 'io.renren.modules.sys.controller.SysMenuController.delete()', '[36]', '15', '127.0.0.1', '2018-11-29 14:45:33');
INSERT INTO `sys_log` VALUES ('12', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":36,\"parentId\":0,\"name\":\"文章管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"editor\",\"orderNum\":0}]', '16', '127.0.0.1', '2018-12-10 16:26:16');
INSERT INTO `sys_log` VALUES ('13', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":37,\"parentId\":36,\"name\":\"文章列表\",\"url\":\"cms/article\",\"perms\":\"\",\"type\":1,\"icon\":\"log\",\"orderNum\":0}]', '706', '127.0.0.1', '2018-12-10 16:28:41');
INSERT INTO `sys_log` VALUES ('14', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":38,\"parentId\":36,\"name\":\"栏目管理\",\"url\":\"cms/channel\",\"perms\":\"\",\"type\":1,\"icon\":\"config\",\"orderNum\":0}]', '9', '127.0.0.1', '2018-12-10 16:30:41');
INSERT INTO `sys_log` VALUES ('15', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":39,\"parentId\":36,\"name\":\"类型管理\",\"url\":\"cms/type\",\"perms\":\"\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":0}]', '10', '127.0.0.1', '2018-12-10 16:31:12');
INSERT INTO `sys_log` VALUES ('16', 'admin', '修改角色', 'io.renren.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"roleId\":1,\"roleName\":\"系统管理员\",\"remark\":\"系统管理员\",\"deptId\":1,\"deptName\":\"人人开源集团\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,31,32,33,34,35,36,37,38,39,-666666],\"deptIdList\":[1,2,3,4,5,-666666]}]', '193', '127.0.0.1', '2018-12-10 17:59:21');
INSERT INTO `sys_log` VALUES ('17', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":40,\"parentId\":37,\"name\":\"查看\",\"perms\":\"cms:article:list\",\"type\":2,\"icon\":\"system\",\"orderNum\":0}]', '12', '127.0.0.1', '2018-12-10 18:02:14');
INSERT INTO `sys_log` VALUES ('18', 'admin', '修改角色', 'io.renren.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"roleId\":1,\"roleName\":\"系统管理员\",\"remark\":\"系统管理员\",\"deptId\":1,\"deptName\":\"人人开源集团\",\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,31,32,33,34,35,36,37,40,38,39,-666666],\"deptIdList\":[1,2,3,4,5,-666666]}]', '36', '127.0.0.1', '2018-12-10 18:02:30');
INSERT INTO `sys_log` VALUES ('19', 'admin', '保存菜单', 'io.renren.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":41,\"parentId\":37,\"name\":\"删除\",\"url\":\"\",\"perms\":\"cms:article:delete\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]', '20', '127.0.0.1', '2018-12-11 09:28:26');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user', null, '1', 'admin', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role', null, '1', 'role', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu', null, '1', 'menu', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'http://localhost:8080/renren-plus/druid/sql.html', null, '1', 'sql', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'job/schedule', null, '1', 'job', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'log', '7');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'oss/oss', 'sys:oss:all', '1', 'oss', '6');
INSERT INTO `sys_menu` VALUES ('31', '1', '部门管理', 'sys/dept', null, '1', 'zonghe', '1');
INSERT INTO `sys_menu` VALUES ('32', '31', '查看', null, 'sys:dept:list,sys:dept:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'sys:dept:save,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'sys:dept:update,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'sys:dept:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('36', '0', '文章管理', null, null, '0', 'editor', '0');
INSERT INTO `sys_menu` VALUES ('37', '36', '文章列表', 'cms/article', null, '1', 'log', '0');
INSERT INTO `sys_menu` VALUES ('38', '36', '栏目管理', 'cms/channel', null, '1', 'config', '0');
INSERT INTO `sys_menu` VALUES ('39', '36', '类型管理', 'cms/type', null, '1', 'daohang', '0');
INSERT INTO `sys_menu` VALUES ('40', '37', '查看', null, 'cms:article:list', '2', 'system', '0');
INSERT INTO `sys_menu` VALUES ('41', '37', '删除', null, 'cms:article:delete', '2', null, '0');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` int(11) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '系统管理员', '1', '1', '2018-11-28 16:32:25');
INSERT INTO `sys_role` VALUES ('2', '长沙分公司', '长沙分公司', '2', '1', '2018-11-29 09:53:56');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('10', '2', '2');
INSERT INTO `sys_role_dept` VALUES ('11', '2', '-666666');
INSERT INTO `sys_role_dept` VALUES ('12', '2', '1');
INSERT INTO `sys_role_dept` VALUES ('19', '1', '1');
INSERT INTO `sys_role_dept` VALUES ('20', '1', '2');
INSERT INTO `sys_role_dept` VALUES ('21', '1', '3');
INSERT INTO `sys_role_dept` VALUES ('22', '1', '4');
INSERT INTO `sys_role_dept` VALUES ('23', '1', '5');
INSERT INTO `sys_role_dept` VALUES ('24', '1', '-666666');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('71', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('72', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('73', '2', '15');
INSERT INTO `sys_role_menu` VALUES ('74', '2', '16');
INSERT INTO `sys_role_menu` VALUES ('75', '2', '17');
INSERT INTO `sys_role_menu` VALUES ('76', '2', '18');
INSERT INTO `sys_role_menu` VALUES ('77', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('78', '2', '19');
INSERT INTO `sys_role_menu` VALUES ('79', '2', '20');
INSERT INTO `sys_role_menu` VALUES ('80', '2', '21');
INSERT INTO `sys_role_menu` VALUES ('81', '2', '22');
INSERT INTO `sys_role_menu` VALUES ('82', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('83', '2', '23');
INSERT INTO `sys_role_menu` VALUES ('84', '2', '24');
INSERT INTO `sys_role_menu` VALUES ('85', '2', '25');
INSERT INTO `sys_role_menu` VALUES ('86', '2', '26');
INSERT INTO `sys_role_menu` VALUES ('87', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('88', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('89', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('90', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('91', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('92', '2', '10');
INSERT INTO `sys_role_menu` VALUES ('93', '2', '11');
INSERT INTO `sys_role_menu` VALUES ('94', '2', '12');
INSERT INTO `sys_role_menu` VALUES ('95', '2', '13');
INSERT INTO `sys_role_menu` VALUES ('96', '2', '14');
INSERT INTO `sys_role_menu` VALUES ('97', '2', '27');
INSERT INTO `sys_role_menu` VALUES ('98', '2', '29');
INSERT INTO `sys_role_menu` VALUES ('100', '2', '30');
INSERT INTO `sys_role_menu` VALUES ('101', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('102', '2', '32');
INSERT INTO `sys_role_menu` VALUES ('103', '2', '33');
INSERT INTO `sys_role_menu` VALUES ('104', '2', '34');
INSERT INTO `sys_role_menu` VALUES ('105', '2', '35');
INSERT INTO `sys_role_menu` VALUES ('106', '2', '-666666');
INSERT INTO `sys_role_menu` VALUES ('146', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('147', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('148', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('149', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('150', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('151', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('152', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('153', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('154', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('155', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('156', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('157', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('158', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('159', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('160', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('161', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('162', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('163', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('164', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('165', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('166', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('167', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('168', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('169', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('170', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('171', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('172', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('173', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('174', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('175', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('176', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('177', '1', '33');
INSERT INTO `sys_role_menu` VALUES ('178', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('179', '1', '35');
INSERT INTO `sys_role_menu` VALUES ('180', '1', '36');
INSERT INTO `sys_role_menu` VALUES ('181', '1', '37');
INSERT INTO `sys_role_menu` VALUES ('182', '1', '40');
INSERT INTO `sys_role_menu` VALUES ('183', '1', '38');
INSERT INTO `sys_role_menu` VALUES ('184', '1', '39');
INSERT INTO `sys_role_menu` VALUES ('185', '1', '-666666');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '1', '2016-11-11 11:11:11');
INSERT INTO `sys_user` VALUES ('2', 'root', '54a8595f6c2d41e705d0f5f8b069cb435371c00600eb5f280e87356011c27de3', 'EcJK1QbN0ZdnxYWVeR4N', 'ro@qq.com', '13333333333', '1', '4', '1', '2018-11-29 09:53:10');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('4', '1', '1');
INSERT INTO `sys_user_role` VALUES ('5', '2', '2');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '6e581af798c8cf0f310ceaa640afbabc', '2018-12-11 21:10:09', '2018-12-11 09:10:09');
INSERT INTO `sys_user_token` VALUES ('2', '7107e1a8ee33d896a84935bce8c4f8bf', '2018-11-29 22:27:58', '2018-11-29 10:27:58');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');
