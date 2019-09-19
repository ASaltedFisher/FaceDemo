

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dr_user
-- ----------------------------
DROP TABLE IF EXISTS `t_dr_user`;
CREATE TABLE `t_dr_user`  (
  `f_uid` int(11) NOT NULL,
  `f_username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_realname` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_jurisdiction` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_ame_username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_ame_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_ame_projectid` int(8) NULL DEFAULT NULL,
  `f_ame_taskid` int(6) NULL DEFAULT NULL,
  `f_mail` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`f_uid`) USING BTREE,
  INDEX `f_ame_projectid`(`f_ame_projectid`) USING BTREE,
  INDEX `f_ame_taskid`(`f_ame_taskid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
