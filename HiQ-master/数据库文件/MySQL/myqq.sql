/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : myqq

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2013-01-14 17:12:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `friendsrss`
-- ----------------------------
DROP TABLE IF EXISTS `friendsrss`;
CREATE TABLE `friendsrss` (
  `friendsRsId` int(11) NOT NULL AUTO_INCREMENT,
  `userQQ` int(11) DEFAULT NULL,
  `friendId` int(11) DEFAULT NULL,
  `groupName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`friendsRsId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friendsrss
-- ----------------------------
INSERT INTO `friendsrss` VALUES ('1', '123456', '2', '高中同学');
INSERT INTO `friendsrss` VALUES ('2', '123456', '3', '大学同学');
INSERT INTO `friendsrss` VALUES ('3', '123456', '4', '大学同学');
INSERT INTO `friendsrss` VALUES ('4', '123456', '5', '高中同学');
INSERT INTO `friendsrss` VALUES ('5', '123457', '1', '知己');
INSERT INTO `friendsrss` VALUES ('6', '123457', '3', '基友');
INSERT INTO `friendsrss` VALUES ('7', '123457', '4', '知己');
INSERT INTO `friendsrss` VALUES ('8', '123457', '5', '基友');
INSERT INTO `friendsrss` VALUES ('9', '123458', '1', '高中同学');
INSERT INTO `friendsrss` VALUES ('10', '123458', '2', '高中同学');
INSERT INTO `friendsrss` VALUES ('11', '123458', '4', '知己');
INSERT INTO `friendsrss` VALUES ('13', '123459', '1', '高中同学');
INSERT INTO `friendsrss` VALUES ('14', '123459', '2', '高中同学');
INSERT INTO `friendsrss` VALUES ('15', '123459', '3', '朋友');
INSERT INTO `friendsrss` VALUES ('17', '123460', '1', '高中同学');
INSERT INTO `friendsrss` VALUES ('18', '123460', '2', '高中同学');
INSERT INTO `friendsrss` VALUES ('19', '123460', '3', '朋友');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userQQ` int(11) DEFAULT NULL,
  `userPassword` varchar(50) DEFAULT NULL,
  `userImage` varchar(20) DEFAULT NULL,
  `userNikName` varchar(20) DEFAULT NULL,
  `userSex` int(11) DEFAULT NULL,
  `userAddress` varchar(50) DEFAULT NULL,
  `userAge` int(11) DEFAULT NULL,
  `userBirthday` varchar(50) DEFAULT NULL,
  `uesrEmail` varchar(50) DEFAULT NULL,
  `userRgDate` date DEFAULT NULL,
  `userSpeak` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '123456', '1', '123456.png', '郭宝星', '1', '山东德州', '18', '1990-11-14', 'gbx@163.com', '1999-11-02', '我相信蜗行，哈哈');
INSERT INTO `users` VALUES ('2', '123457', '1', '123457.png', '郑闯', '1', '山东枣庄', '25', '1990-11-14', 'zc@163.com', '1999-11-02', '有志者事竟成');
INSERT INTO `users` VALUES ('3', '123458', '1', '123458.png', '朱庆伟', '1', '黑龙江齐齐哈尔', '29', '1990-11-19', 'zqw@163.com', '1999-11-02', '你是我的玫瑰你是我的花，你是我的爱人是我的牵挂');
INSERT INTO `users` VALUES ('4', '123459', '1', '123459.png', '袁佑', '1', '山东青岛', '29', '1990-11-19', 'yy@163.com', '1999-11-02', '你是我的玫瑰你是我的花，你是我的爱人是我的牵挂');
INSERT INTO `users` VALUES ('5', '123460', '1', '123460.png', '徐利红', '0', '山东聊城', '29', '1990-11-19', 'xlh@163.com', '1999-11-02', '勇勇，你行');
INSERT INTO `users` VALUES ('6', '123461', '123', null, '嗨皮', '0', '山东 淄博', '19', '1992-11-15', 'gbx@163.com', '2013-01-14', '有志者事竟成');
