/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50736 (5.7.36)
 Source Host           : 127.0.0.1:3306
 Source Schema         : compkey

 Target Server Type    : MySQL
 Target Server Version : 50736 (5.7.36)
 File Encoding         : 65001

 Date: 06/12/2023 02:32:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_local
-- ----------------------------
DROP TABLE IF EXISTS `auth_local`;
CREATE TABLE `auth_local` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `account` (`account`),
  CONSTRAINT `auth_local_ibfk_1` FOREIGN KEY (`account`) REFERENCES `user` (`account`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_local
-- ----------------------------
BEGIN;
INSERT INTO `auth_local` (`id`, `account`, `username`, `password`) VALUES (1, 1, 'root', '$argon2id$v=19$m=4096,t=3,p=1$d5FLws7CQLfST/5CzkqS9w$bSrNnvhRyB5lT6/tSgVOiJ7uVSRQR1a5xd4qZbrLQEk');
INSERT INTO `auth_local` (`id`, `account`, `username`, `password`) VALUES (2, 2, 'abc', '$argon2id$v=19$m=4096,t=3,p=1$gm87yiZJC94Sc7oeVI4YDQ$nDDky3perv9hmq4V13rPJCfgIQ5kNw1esmzPzvzVzkE');
INSERT INTO `auth_local` (`id`, `account`, `username`, `password`) VALUES (3, 3, 'pr', '$argon2id$v=19$m=4096,t=3,p=1$NXLw2uEZLSifb8N+BouhCw$5rmu0gS7KL4UCLP5MybHTY+7os2jaHd0iu7AUvbekwI');
COMMIT;

-- ----------------------------
-- Table structure for auth_oauth
-- ----------------------------
DROP TABLE IF EXISTS `auth_oauth`;
CREATE TABLE `auth_oauth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11) NOT NULL,
  `oauth_name` varchar(255) NOT NULL,
  `oauth_id` varchar(255) NOT NULL,
  `oauth_access_token` varchar(255) NOT NULL,
  `oauth_expires` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account` (`account`),
  CONSTRAINT `auth_oauth_ibfk_1` FOREIGN KEY (`account`) REFERENCES `user` (`account`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_oauth
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cache
-- ----------------------------
DROP TABLE IF EXISTS `cache`;
CREATE TABLE `cache` (
  `seed` varchar(255) NOT NULL,
  `recom1` varchar(255) DEFAULT NULL,
  `recom2` varchar(255) DEFAULT NULL,
  `recom3` varchar(255) DEFAULT NULL,
  `recom4` varchar(255) DEFAULT NULL,
  `recom5` varchar(255) DEFAULT NULL,
  `recom6` varchar(255) DEFAULT NULL,
  `recom7` varchar(255) DEFAULT NULL,
  `recom8` varchar(255) DEFAULT NULL,
  `recom9` varchar(255) DEFAULT NULL,
  `recom10` varchar(255) DEFAULT NULL,
  `score1` int(11) DEFAULT NULL,
  `score2` int(11) DEFAULT NULL,
  `score3` int(11) DEFAULT NULL,
  `score4` int(11) DEFAULT NULL,
  `score5` int(11) DEFAULT NULL,
  `score6` int(11) DEFAULT NULL,
  `score7` int(11) DEFAULT NULL,
  `score8` int(11) DEFAULT NULL,
  `score9` int(11) DEFAULT NULL,
  `score10` int(11) DEFAULT NULL,
  `comp1` varchar(255) DEFAULT NULL,
  `comp2` varchar(255) DEFAULT NULL,
  `comp3` varchar(255) DEFAULT NULL,
  `comp4` varchar(255) DEFAULT NULL,
  `comp5` varchar(255) DEFAULT NULL,
  `comp6` varchar(255) DEFAULT NULL,
  `comp7` varchar(255) DEFAULT NULL,
  `comp8` varchar(255) DEFAULT NULL,
  `comp9` varchar(255) DEFAULT NULL,
  `comp10` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seed`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存已经计算出的搜索记录加快查询速度';

-- ----------------------------
-- Records of cache
-- ----------------------------
BEGIN;
INSERT INTO `cache` (`seed`, `recom1`, `recom2`, `recom3`, `recom4`, `recom5`, `recom6`, `recom7`, `recom8`, `recom9`, `recom10`, `score1`, `score2`, `score3`, `score4`, `score5`, `score6`, `score7`, `score8`, `score9`, `score10`, `comp1`, `comp2`, `comp3`, `comp4`, `comp5`, `comp6`, `comp7`, `comp8`, `comp9`, `comp10`) VALUES ('图片', '女生', '视频', '汽车', '做法', '男生', '头像', 'qq', '车价', '头像', NULL, 9, 8, 3, 7, 5, 5, 3, 10, 3, 6, '0.6172868000504438', '0.7905004842118365', '0.9875596333179247', '0.558879313579648', '0.3700344668422215', '0.9932032255392552', '0.056420060858521665', '0.8712437933974376', '0.37455830571666204', '0.7612969696161309');
INSERT INTO `cache` (`seed`, `recom1`, `recom2`, `recom3`, `recom4`, `recom5`, `recom6`, `recom7`, `recom8`, `recom9`, `recom10`, `score1`, `score2`, `score3`, `score4`, `score5`, `score6`, `score7`, `score8`, `score9`, `score10`, `comp1`, `comp2`, `comp3`, `comp4`, `comp5`, `comp6`, `comp7`, `comp8`, `comp9`, `comp10`) VALUES ('小说', '荣耀', '电脑', '下载', '官网', '头像', '单机', 'txt', '信用', '韩国', NULL, 10, 2, 10, 6, 6, 8, 9, 1, 1, 8, '0.0017536301182151121', '0.6755016964632102', '0.9389079976257942', '0.7424220906034318', '0.9179780501108412', '0.609093729973858', '0.34462336762307527', '0.3097286935054964', '0.10638242131693514', '0.650036753760685');
INSERT INTO `cache` (`seed`, `recom1`, `recom2`, `recom3`, `recom4`, `recom5`, `recom6`, `recom7`, `recom8`, `recom9`, `recom10`, `score1`, `score2`, `score3`, `score4`, `score5`, `score6`, `score7`, `score8`, `score9`, `score10`, `comp1`, `comp2`, `comp3`, `comp4`, `comp5`, `comp6`, `comp7`, `comp8`, `comp9`, `comp10`) VALUES ('手机', '视频', '女主', '在线', '下载', '小说', '观看', '图片', '下载', '倾城', NULL, 1, 8, 10, 7, 4, 5, 7, 6, 6, 1, '0.1569077811733892', '0.006007060414186735', '0.7318611189088422', '0.035472543011859564', '0.4797868807611865', '0.06585900398516935', '0.5538566862734563', '0.9349121180688144', '0.40823722016833464', '0.9662928906886772');
INSERT INTO `cache` (`seed`, `recom1`, `recom2`, `recom3`, `recom4`, `recom5`, `recom6`, `recom7`, `recom8`, `recom9`, `recom10`, `score1`, `score2`, `score3`, `score4`, `score5`, `score6`, `score7`, `score8`, `score9`, `score10`, `comp1`, `comp2`, `comp3`, `comp4`, `comp5`, `comp6`, `comp7`, `comp8`, `comp9`, `comp10`) VALUES ('电影', 'iphone7', '李晨', '图片', 'iphone', '下载', '手机', '笔记', '学院', '韩国', NULL, 6, 4, 10, 9, 2, 8, 5, 4, 7, 1, '0.7792780462459457', '0.0035302434149479898', '0.8425816324004732', '0.9500964739826475', '0.6450002842070538', '0.5020138607379178', '0.7354133592317005', '0.7453745405612276', '0.7220388676245127', '0.8813542228949761');
INSERT INTO `cache` (`seed`, `recom1`, `recom2`, `recom3`, `recom4`, `recom5`, `recom6`, `recom7`, `recom8`, `recom9`, `recom10`, `score1`, `score2`, `score3`, `score4`, `score5`, `score6`, `score7`, `score8`, `score9`, `score10`, `comp1`, `comp2`, `comp3`, `comp4`, `comp5`, `comp6`, `comp7`, `comp8`, `comp9`, `comp10`) VALUES ('苹果', '游戏', '火线', '穿越', '电影', '全文', '阅读', 'txt', '微信', '信号', NULL, 5, 6, 9, 2, 6, 6, 3, 1, 8, 9, '0.4256669184432057', '0.9996300665658248', '0.017324836009484562', '0.6440647716113038', '0.7856408094853543', '0.312492322467726', '0.015496768071778834', '0.92213637933334', '0.38548270835120485', '0.507892473142494');
COMMIT;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `searchid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `seed` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `compkeyword` varchar(255) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`searchid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of history
-- ----------------------------
BEGIN;
INSERT INTO `history` (`searchid`, `userid`, `seed`, `score`, `compkeyword`, `timestamp`) VALUES (1, 1, '图片', 7, 'qq', '2023-11-21 15:22:25');
INSERT INTO `history` (`searchid`, `userid`, `seed`, `score`, `compkeyword`, `timestamp`) VALUES (2, 1, '图片', 8, '车价', '2023-11-21 15:22:29');
INSERT INTO `history` (`searchid`, `userid`, `seed`, `score`, `compkeyword`, `timestamp`) VALUES (3, 1, '图片', 9, '头像', '2023-11-21 15:22:33');
INSERT INTO `history` (`searchid`, `userid`, `seed`, `score`, `compkeyword`, `timestamp`) VALUES (4, 1, '图片', 2, '大全', '2023-11-21 15:22:34');
INSERT INTO `history` (`searchid`, `userid`, `seed`, `score`, `compkeyword`, `timestamp`) VALUES (5, 1, '图片', 3, '女生', '2023-11-21 15:22:35');
INSERT INTO `history` (`searchid`, `userid`, `seed`, `score`, `compkeyword`, `timestamp`) VALUES (7, 1, '图片', 8, '汽车', '2023-11-21 15:22:37');
INSERT INTO `history` (`searchid`, `userid`, `seed`, `score`, `compkeyword`, `timestamp`) VALUES (8, 1, '图片', 6, '做法', '2023-11-21 15:22:37');
INSERT INTO `history` (`searchid`, `userid`, `seed`, `score`, `compkeyword`, `timestamp`) VALUES (9, 1, '图片', 2, '男生', '2023-11-21 15:22:38');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`role_id`, `role_name`) VALUES (1, 'user');
INSERT INTO `role` (`role_id`, `role_name`) VALUES (2, 'admin');
COMMIT;

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `seed_key` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `comp_key` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `score` double(3,2) NOT NULL,
  PRIMARY KEY (`seed_key`,`comp_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of score
-- ----------------------------
BEGIN;
INSERT INTO `score` (`seed_key`, `comp_key`, `score`) VALUES ('华为', '王者', 0.40);
INSERT INTO `score` (`seed_key`, `comp_key`, `score`) VALUES ('华为', '苹果', 2.56);
INSERT INTO `score` (`seed_key`, `comp_key`, `score`) VALUES ('华为', '视频', 0.40);
COMMIT;

-- ----------------------------
-- Table structure for searchtime
-- ----------------------------
DROP TABLE IF EXISTS `searchtime`;
CREATE TABLE `searchtime` (
  `seed` varchar(255) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`seed`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='保存所有用户搜索种子关键词的次数';

-- ----------------------------
-- Records of searchtime
-- ----------------------------
BEGIN;
INSERT INTO `searchtime` (`seed`, `count`) VALUES ('医生', 4);
INSERT INTO `searchtime` (`seed`, `count`) VALUES ('图片', 8);
INSERT INTO `searchtime` (`seed`, `count`) VALUES ('手机', 3);
INSERT INTO `searchtime` (`seed`, `count`) VALUES ('电影', 1);
INSERT INTO `searchtime` (`seed`, `count`) VALUES ('苹果', 1);
INSERT INTO `searchtime` (`seed`, `count`) VALUES ('音乐', 4);
COMMIT;

-- ----------------------------
-- Table structure for timecost
-- ----------------------------
DROP TABLE IF EXISTS `timecost`;
CREATE TABLE `timecost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seed` varchar(255) NOT NULL,
  `userid` int(11) NOT NULL,
  `algorithm` varchar(255) NOT NULL,
  `process` int(11) NOT NULL,
  `algtime` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of timecost
-- ----------------------------
BEGIN;
INSERT INTO `timecost` (`id`, `seed`, `userid`, `algorithm`, `process`, `algtime`, `timestamp`) VALUES (1, '图片', 1, 'snownlp', 2213, 2310, '2023-11-21 17:01:21');
INSERT INTO `timecost` (`id`, `seed`, `userid`, `algorithm`, `process`, `algtime`, `timestamp`) VALUES (2, '小说', 1, 'jieba', 3245, 4132, '2023-11-21 17:01:24');
INSERT INTO `timecost` (`id`, `seed`, `userid`, `algorithm`, `process`, `algtime`, `timestamp`) VALUES (3, '手机', 1, 'snownlp', 6630, 5344, '2023-11-21 17:01:25');
INSERT INTO `timecost` (`id`, `seed`, `userid`, `algorithm`, `process`, `algtime`, `timestamp`) VALUES (4, '电影', 1, 'jieba', 1569, 989, '2023-11-21 17:01:26');
INSERT INTO `timecost` (`id`, `seed`, `userid`, `algorithm`, `process`, `algtime`, `timestamp`) VALUES (5, '苹果', 1, 'pynlpir', 2342, 1083, '2023-11-21 17:01:40');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `account` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account`) USING BTREE,
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`account`, `role_id`, `phone`, `email`, `sex`, `school`, `description`) VALUES (1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`account`, `role_id`, `phone`, `email`, `sex`, `school`, `description`) VALUES (2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`account`, `role_id`, `phone`, `email`, `sex`, `school`, `description`) VALUES (3, 1, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
