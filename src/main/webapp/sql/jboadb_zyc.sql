#创建数据库
DROP DATABASE IF EXISTS jboadb_zyc;
CREATE DATABASE jboadb_zyc;

#进入数据库
USE jboadb_zyc;

#创建部门表
DROP TABLE IF EXISTS SYS_DEPARTMENT;
CREATE TABLE SYS_DEPARTMENT
(
  ID   INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `NAME` VARCHAR(50) NOT NULL
)CHARSET=utf8;
#创建员工表
DROP TABLE IF EXISTS SYS_EMPLOYEE;
CREATE TABLE SYS_EMPLOYEE
(
  SN            VARCHAR(50) NOT NULL PRIMARY KEY,
  POSITION_ID   INT(10) NOT NULL,
  DEPARTMENT_ID INT(10) NOT NULL,
  NAME          VARCHAR(50) NOT NULL,
  PASSWORD      VARCHAR(50) NOT NULL,
  STATUS        VARCHAR(20) NOT NULL
)CHARSET=utf8;

#创建报销单
CREATE TABLE BIZ_CLAIM_VOUCHER
(
  ID            INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NEXT_DEAL_SN  VARCHAR(50),
  CREATE_SN     VARCHAR(50) NOT NULL,
  CREATE_TIME   DATETIME NOT NULL,
  EVENT         VARCHAR(255) NOT NULL,
  TOTAL_ACCOUNT DOUBLE(20,2) NOT NULL,
  STATUS        VARCHAR(20) NOT NULL,
  MODIFY_TIME   DATETIME
)CHARSET=utf8;

#创建报销单检查表
CREATE TABLE BIZ_CHECK_RESULT
(
  ID         INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  CLAIM_ID   INT(10) NOT NULL,
  CHECK_TIME DATETIME NOT NULL,
  CHECKER_SN VARCHAR(50) NOT NULL,
  RESULT     VARCHAR(50) NOT NULL,
  COMM       VARCHAR(500) NOT NULL
)CHARSET=utf8;


#创建报销单详细项目信息表
CREATE TABLE BIZ_CLAIM_VOUCHER_DETAIL
(
  ID      INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  MAIN_ID INT(10) NOT NULL,
  ITEM    VARCHAR(20) NOT NULL,
  ACCOUNT DOUBLE(20,2) NOT NULL,
  DES     VARCHAR(200) NOT NULL
);


#创建报销单统计表
CREATE TABLE BIZ_CLAIM_VOUCHER_STATISTICS
(
  ID            INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  TOTAL_COUNT   DOUBLE(20,2) NOT NULL,
  YEAR          INT(4) NOT NULL,
  MONTH         INT(2) NOT NULL,
  DEPARTMENT_ID INT(10) NOT NULL,
  MODIFY_TIME   DATETIME NOT NULL
)CHARSET=utf8;


#报销额统计表
CREATE TABLE BIZ_CLAIM_VOUYEAR__STATISTICS
(
  ID            INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  TOTAL_COUNT   DOUBLE(30,2) NOT NULL,
  YEAR          INT(4) NOT NULL,
  MODIFY_TIME   DATETIME NOT NULL,
  DEPARTMENT_ID INT(10) NOT NULL
)CHARSET=utf8;


#创建请假条
CREATE TABLE BIZ_LEAVE
(
  ID              INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  EMPLOYEE_SN     VARCHAR(50) NOT NULL,
  STARTTIME       DATETIME NOT NULL,
  ENDTIME         DATETIME NOT NULL,
  LEAVEDAY        DOUBLE(5,1) NOT NULL,
  REASON          VARCHAR(500) NOT NULL,
  STATUS          VARCHAR(20),
  LEAVETYPE       VARCHAR(50),
  NEXT_DEAL_SN    VARCHAR(50),
  APPROVE_OPINION VARCHAR(100),
  CREATETIME      DATETIME,
  MODIFYTIME      DATETIME
)CHARSET=utf8;

#添加请假条到员工的外键引用
ALTER TABLE BIZ_LEAVE
  ADD CONSTRAINT FK_EMPLOYEE_SN FOREIGN KEY (EMPLOYEE_SN)
  REFERENCES SYS_EMPLOYEE (SN);
ALTER TABLE BIZ_LEAVE
  ADD CONSTRAINT FK_NEXT_DEAL_SN FOREIGN KEY (NEXT_DEAL_SN)
  REFERENCES SYS_EMPLOYEE (SN);

#创建职位表
CREATE TABLE SYS_POSITION
(
  ID      INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME_CN VARCHAR(50) NOT NULL,
  NAME_EN VARCHAR(50) NOT NULL
)CHARSET=utf8;

#添加外建关系

#添加员工表到部门表的外键引用
ALTER TABLE SYS_EMPLOYEE
  ADD CONSTRAINT DEPART_ID FOREIGN KEY (DEPARTMENT_ID)
  REFERENCES SYS_DEPARTMENT (ID);
#添加员工表到职位表的外键引用
ALTER TABLE SYS_EMPLOYEE
  ADD CONSTRAINT FK_POSITION_ID FOREIGN KEY (POSITION_ID)
  REFERENCES SYS_POSITION (ID);

 #添加报销单创建者列到员工表的外键引用
ALTER TABLE BIZ_CLAIM_VOUCHER
  ADD CONSTRAINT E_SN FOREIGN KEY (CREATE_SN)
  REFERENCES SYS_EMPLOYEE (SN);
#添加报销单审批者到员工表的外键引用
ALTER TABLE BIZ_CLAIM_VOUCHER
  ADD CONSTRAINT F_SN FOREIGN KEY (NEXT_DEAL_SN)
  REFERENCES SYS_EMPLOYEE (SN);

#添加报销单检查表到报销单的外键引用
ALTER TABLE BIZ_CHECK_RESULT
  ADD CONSTRAINT FK_CLAIM_ID FOREIGN KEY (CLAIM_ID)
  REFERENCES BIZ_CLAIM_VOUCHER (ID);
#添加报销单检查表到员工表的外键引用
ALTER TABLE BIZ_CHECK_RESULT
  ADD CONSTRAINT FK_CHECKER_SN FOREIGN KEY (CHECKER_SN)
  REFERENCES SYS_EMPLOYEE (SN);

#添加报销单详细项目信息表到报销单的外键引用
ALTER TABLE BIZ_CLAIM_VOUCHER_DETAIL
  ADD CONSTRAINT CLAIME_ID FOREIGN KEY (MAIN_ID)
  REFERENCES BIZ_CLAIM_VOUCHER (ID);

#添加月度报销单统计表部门ID到部门的外键引用
ALTER TABLE BIZ_CLAIM_VOUCHER_STATISTICS
  ADD CONSTRAINT DEPTID_ID FOREIGN KEY (DEPARTMENT_ID)
  REFERENCES SYS_DEPARTMENT (ID);

#添加年度报销额统计表部门ID到部门的外键引用
ALTER TABLE BIZ_CLAIM_VOUYEAR__STATISTICS
  ADD CONSTRAINT VOUYEAR_DEPTID_ID FOREIGN KEY (DEPARTMENT_ID)
  REFERENCES SYS_DEPARTMENT (ID);

#添加请假条到员工的外键引用
ALTER TABLE BIZ_LEAVE
  ADD CONSTRAINT FK_EMPLOYEE_SN FOREIGN KEY (EMPLOYEE_SN)
  REFERENCES SYS_EMPLOYEE (SN);
ALTER TABLE BIZ_LEAVE
  ADD CONSTRAINT FK_NEXT_DEAL_SN FOREIGN KEY (NEXT_DEAL_SN)
  REFERENCES SYS_EMPLOYEE (SN);


/*
alter table SYS_DEPARTMENT disable all triggers;
alter table SYS_EMPLOYEE disable all triggers;
alter table BIZ_CLAIM_VOUCHER disable all triggers;
alter table BIZ_CHECK_RESULT disable all triggers;
alter table BIZ_CLAIM_VOUCHER_DETAIL disable all triggers;
alter table BIZ_CLAIM_VOUCHER_STATISTICS disable all triggers;
alter table BIZ_CLAIM_VOUYEAR__STATISTICS disable all triggers;
alter table BIZ_LEAVE disable all triggers;
alter table SYS_POSITION disable all triggers;
alter table SYS_EMPLOYEE disable constraint DEPART_ID;
alter table BIZ_CLAIM_VOUCHER disable constraint E_SN;
alter table BIZ_CLAIM_VOUCHER disable constraint F_SN;
alter table BIZ_CHECK_RESULT disable constraint FK_CLAIM_ID;
alter table BIZ_CLAIM_VOUCHER_DETAIL disable constraint CLAIME_ID;
alter table BIZ_CLAIM_VOUCHER_STATISTICS disable constraint DEPTID_ID;
*/
#添加部门表数据
INSERT INTO SYS_DEPARTMENT (ID, NAME)
VALUES (1, '人事部');
INSERT INTO SYS_DEPARTMENT (ID, NAME)
VALUES (2, '平台研发部');
INSERT INTO SYS_DEPARTMENT (ID, NAME)
VALUES (3, '产品设计部');
INSERT INTO SYS_DEPARTMENT (ID, NAME)
VALUES (4, '财务部');
INSERT INTO SYS_DEPARTMENT (ID, NAME)
VALUES (5, '总裁办公室');
COMMIT;
#添加员工表数据
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('017', 1, 1, '李小伟', '202cb962ac59075b964b07152d234b70', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('001', 1, 2, '张平', '123', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('002', 2, 2, '叶宁', '123', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('003', 3, 3, '李伟', '123', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('004', 4, 4, '王小明', '123', '离职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('005', 1, 3, '林风', '123', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('006', 1, 3, '张大明', '123', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('011', 1, 1, '李大伟1', '123', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('007', 1, 1, '李大伟', '123', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('008', 3, 5, '张总', '123', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('009', 4, 4, '李峰', '123', '在职');
INSERT INTO SYS_EMPLOYEE (SN, POSITION_ID, DEPARTMENT_ID, NAME, PASSWORD, STATUS)
VALUES ('018', 1, 1, '李大伟', '123', '在职');
COMMIT;

#添加报销单数据
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (100, NULL, '001', DATE_FORMAT('06-09-13 13:29:30', '%d-%m-%y %H:%i:%s'), '出差', 600, '已终止', DATE_FORMAT('13-09-13 15:29:06', '%d-%m-%y %H:%i:%s'));

INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (102, '001', '001', DATE_FORMAT('06-09-13 13:41:50', '%d-%m-%y %H:%i:%s'), '公司业务', 6500.23, '新创建', DATE_FORMAT('13-09-13 15:02:09', '%d-%m-%y %H:%i:%s'));

INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (1, NULL, '001', DATE_FORMAT('02-07-13 08:30:00', '%d-%m-%y %H:%i:%s'), '出差', 200.55, '已付款', DATE_FORMAT('27-08-13', '%d-%m-%y'));

INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (2, NULL, '005', DATE_FORMAT('08-08-13 09:30:00', '%d-%m-%y %H:%i:%s'), '出差', 1200.01, '已付款', DATE_FORMAT('29-08-13', '%d-%m-%y'));

INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (3, '004', '006', DATE_FORMAT('08-08-13 10:30:00', '%d-%m-%y %H:%i:%s'), '会见客户', 8535.59, '已审批', DATE_FORMAT('18-09-13 11:18:54', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (4, NULL, '001', DATE_FORMAT('29-08-13 08:00:00', '%d-%m-%y %H:%i:%s'), '周末加班', 128.01, '已付款', DATE_FORMAT('02-09-13 09:00:00', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (7, '004', '001', DATE_FORMAT('03-09-13 05:00:00', '%d-%m-%y %H:%i:%s'), '周末加班', 5132.51, '已审批', DATE_FORMAT('18-09-13 11:19:03', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (8, '001', '001', DATE_FORMAT('03-09-13 08:00:00', '%d-%m-%y %H:%i:%s'), '周末加班', 132.51, '已打回', DATE_FORMAT('03-09-13 09:00:00', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (6, NULL, '002', DATE_FORMAT('01-07-13', '%d-%m-%y'), '周末加班', 400, '已付款', DATE_FORMAT('20-07-13', '%d-%m-%y'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (128, '001', '001', DATE_FORMAT('10-09-13 13:47:42', '%d-%m-%y %H:%i:%s'), '拜访客户', 100, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (151, NULL, '017', DATE_FORMAT('14-10-13 17:34:29', '%d-%m-%y %H:%i:%s'), '外出', 50, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (200, NULL, '001', DATE_FORMAT('23-10-13 13:47:44', '%d-%m-%y %H:%i:%s'), '123', 34, '新创建', DATE_FORMAT('23-10-13 13:47:56', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (202, NULL, '001', DATE_FORMAT('23-10-13 13:48:27', '%d-%m-%y %H:%i:%s'), '11', 22, '新创建', DATE_FORMAT('23-10-13 13:52:14', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (129, '001', '001', DATE_FORMAT('10-09-13 14:05:47', '%d-%m-%y %H:%i:%s'), '这交伯费用', 100, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (133, '001', '001', DATE_FORMAT('13-09-13 15:21:52', '%d-%m-%y %H:%i:%s'), '拜访客户', 888, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (132, '001', '001', DATE_FORMAT('13-09-13 15:18:39', '%d-%m-%y %H:%i:%s'), '拜访客户', 388, '新创建', DATE_FORMAT('13-09-13 15:19:15', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (181, NULL, '003', DATE_FORMAT('22-10-13 17:27:55', '%d-%m-%y %H:%i:%s'), '出差！', 100, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (192, NULL, '003', DATE_FORMAT('23-10-13 10:52:09', '%d-%m-%y %H:%i:%s'), '出差！', 100, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (198, NULL, '001', DATE_FORMAT('23-10-13 13:45:32', '%d-%m-%y %H:%i:%s'), '123', 44, '新创建', DATE_FORMAT('23-10-13 13:47:24', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (175, NULL, '001', DATE_FORMAT('22-10-13 16:13:03', '%d-%m-%y %H:%i:%s'), '123', 12, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (5, '002', '001', DATE_FORMAT('01-08-13', '%d-%m-%y'), '周末加班', 45.01, '已提交', DATE_FORMAT('31-08-13', '%d-%m-%y'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (107, '004', '001', DATE_FORMAT('06-09-13 16:12:58', '%d-%m-%y %H:%i:%s'), '拜访客户', 1000, '已审批', DATE_FORMAT('18-09-13 11:18:16', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (106, '002', '001', DATE_FORMAT('06-09-13 16:03:39', '%d-%m-%y %H:%i:%s'), '拜访客户', 700, '已提交', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (108, '004', '001', DATE_FORMAT('06-09-13 16:16:56', '%d-%m-%y %H:%i:%s'), '出差', 1599, '已审批', DATE_FORMAT('18-09-13 11:12:42', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (124, '001', '001', DATE_FORMAT('10-09-13 13:01:28', '%d-%m-%y %H:%i:%s'), '加班', 104, '新创建', DATE_FORMAT('13-09-13 15:20:12', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (126, '001', '001', DATE_FORMAT('10-09-13 13:41:57', '%d-%m-%y %H:%i:%s'), '拜访客户', 1000, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (130, '001', '001', DATE_FORMAT('10-09-13 14:08:25', '%d-%m-%y %H:%i:%s'), '拜访客户', 100, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (171, NULL, '003', DATE_FORMAT('22-10-13 16:06:53', '%d-%m-%y %H:%i:%s'), '出差！', 100, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (173, NULL, '001', DATE_FORMAT('22-10-13 16:12:36', '%d-%m-%y %H:%i:%s'), '撒旦发射点发', 15, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (180, NULL, '003', DATE_FORMAT('22-10-13 16:55:36', '%d-%m-%y %H:%i:%s'), '出差！', 100, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (191, NULL, '003', DATE_FORMAT('23-10-13 10:51:39', '%d-%m-%y %H:%i:%s'), '出差！', 100, '新创建', NULL);
INSERT INTO BIZ_CLAIM_VOUCHER (ID, NEXT_DEAL_SN, CREATE_SN, CREATE_TIME, EVENT, TOTAL_ACCOUNT, STATUS, MODIFY_TIME)
VALUES (196, NULL, '001', DATE_FORMAT('23-10-13 11:28:42', '%d-%m-%y %H:%i:%s'), '123123123', 369, '新创建', DATE_FORMAT('23-10-13 13:37:12', '%d-%m-%y %H:%i:%s'));
COMMIT;

#添加报销单检查结果数据
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (1, 1, DATE_FORMAT('02-09-13 09:02:02', '%d-%m-%y %H:%i:%s'), '002', '通过', '同意');
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (2, 1, DATE_FORMAT('02-09-13 11:02:02', '%d-%m-%y %H:%i:%s'), '004', '通过', '同意');
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (101, 4, DATE_FORMAT('04-09-13 17:12:27', '%d-%m-%y %H:%i:%s'), '004', '通过', '没问题啊');
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (142, 108, DATE_FORMAT('18-09-13 11:12:42', '%d-%m-%y %H:%i:%s'), '002', '通过', '同意');
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (143, 7, DATE_FORMAT('18-09-13 11:13:05', '%d-%m-%y %H:%i:%s'), '002', '通过', '同意');
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (144, 107, DATE_FORMAT('18-09-13 11:18:16', '%d-%m-%y %H:%i:%s'), '002', '通过', '同意');
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (145, 3, DATE_FORMAT('18-09-13 11:18:54', '%d-%m-%y %H:%i:%s'), '003', '通过', '同意');
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (146, 7, DATE_FORMAT('18-09-13 11:19:03', '%d-%m-%y %H:%i:%s'), '003', '通过', '同意');
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (121, 100, DATE_FORMAT('13-09-13 15:29:06', '%d-%m-%y %H:%i:%s'), '002', '拒绝', '同意');
INSERT INTO BIZ_CHECK_RESULT (ID, CLAIM_ID, CHECK_TIME, CHECKER_SN, RESULT, COMM)
VALUES (100, 4, DATE_FORMAT('04-09-13 17:00:13', '%d-%m-%y %H:%i:%s'), '002', '通过', '同意');
COMMIT;

#添加报销单详细项目数据
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (107, 108, '礼品费', 600, '送礼');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (102, 102, '城际交通费', 200, '回家');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (103, 102, '交际餐费', 300, '吃饭钱');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (104, 102, '办公费', 6000, '购买书柜');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (10, 7, '城际交通费', 4598, '往返机票');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (11, 7, '交际餐费', 534.51, '餐饮');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (1, 1, '住宿', 100, '市内住宿');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (2, 1, '交际餐费', 180, '请客户吃饭');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (3, 2, '交通', 300, '市内交通');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (4, 2, '住宿', 600, '市内住宿');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (5, 2, '餐饮', 300, '餐饮');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (6, 3, '交通', 100, '市内交通');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (7, 3, '餐饮', 700, '餐饮');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (128, 124, '城际交通费', 4, 'test');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (125, 132, '城际交通费', 111, 'test');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (126, 132, '城际交通费', 222, 'test1');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (127, 132, '城际交通费', 55, 'test2');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (129, 133, '礼品费', 888, '送礼');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (170, 202, '城际交通费', 11, '11');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (171, 202, '城际交通费', 11, '11');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (8, 1, '市内交通费', 20, '加班回家');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (9, 4, '市内交通费', 20, '加班回家');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (105, 107, '城际交通费', 300, '立');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (106, 108, '城际交通费', 999, '交通费');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (124, 130, '城际交通费', 100, '费用');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (154, 198, '城际交通费', 22, '22');
INSERT INTO BIZ_CLAIM_VOUCHER_DETAIL (ID, MAIN_ID, ITEM, ACCOUNT, DES)
VALUES (155, 198, '城际交通费', 11, '11');
COMMIT;

#添加报销单于都统计数据
INSERT INTO BIZ_CLAIM_VOUCHER_STATISTICS (ID, TOTAL_COUNT, YEAR, MONTH, DEPARTMENT_ID, MODIFY_TIME)
VALUES (1, 400, 13, 7, 2, DATE_FORMAT('15-08-13 16:34:00', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER_STATISTICS (ID, TOTAL_COUNT, YEAR, MONTH, DEPARTMENT_ID, MODIFY_TIME)
VALUES (2, 200.55, 13, 8, 2, DATE_FORMAT('18-09-13 16:34:56', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER_STATISTICS (ID, TOTAL_COUNT, YEAR, MONTH, DEPARTMENT_ID, MODIFY_TIME)
VALUES (3, 2000.02, 13, 8, 3, DATE_FORMAT('18-09-13 16:34:56', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_CLAIM_VOUCHER_STATISTICS (ID, TOTAL_COUNT, YEAR, MONTH, DEPARTMENT_ID, MODIFY_TIME)
VALUES (4, 128.01, 13, 9, 2, DATE_FORMAT('15-10-13 16:37:58', '%d-%m-%y %H:%i:%s'));
COMMIT;

#添加报销单年度统计数据
INSERT INTO BIZ_CLAIM_VOUYEAR__STATISTICS (ID, TOTAL_COUNT, YEAR, MODIFY_TIME, DEPARTMENT_ID)
VALUES (5, 728.56, 13, DATE_FORMAT('15-02-2014 16:40:36', '%d-%m-%y %H:%i:%s'), 2);
INSERT INTO BIZ_CLAIM_VOUYEAR__STATISTICS (ID, TOTAL_COUNT, YEAR, MODIFY_TIME, DEPARTMENT_ID)
VALUES (6, 2000.02, 13, DATE_FORMAT('15-02-2014 16:40:36', '%d-%m-%y %H:%i:%s'), 3);
COMMIT;

#添加请教条
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (105, '001', DATE_FORMAT('09-09-13', '%d-%m-%y'), DATE_FORMAT('10-09-13', '%d-%m-%y'), 1, '请假', '待审批', '年假', '002', NULL, DATE_FORMAT('05-09-13 16:49:56', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (121, '001', DATE_FORMAT('06-09-13', '%d-%m-%y'), DATE_FORMAT('07-09-13', '%d-%m-%y'), 1, '请假', '待审批', '事假', '002', NULL, DATE_FORMAT('06-09-13 09:02:40', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (136, '001', DATE_FORMAT('10-09-13 10:54:07', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('11-09-13 10:54:07', '%d-%m-%y %H:%i:%s'), 1, '2', '待审批', '年假', '002', NULL, DATE_FORMAT('09-09-13 10:54:20', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (139, '001', DATE_FORMAT('23-09-13 11:15:00', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('24-09-13 11:15:00', '%d-%m-%y %H:%i:%s'), 1, '1', '待审批', '年假', '002', NULL, DATE_FORMAT('09-09-13 11:15:11', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (143, '001', DATE_FORMAT('30-09-13 15:09:50', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('01-10-13 15:09:50', '%d-%m-%y %H:%i:%s'), 1, 'ttt', '待审批', '年假', '002', NULL, DATE_FORMAT('09-09-13 15:10:08', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (1, '001', DATE_FORMAT('02-08-13 08:30:00', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('02-08-13 00:30:00', '%d-%m-%y %H:%i:%s'), .5, '家里有事', '已审批', '事假', '002', '同意', DATE_FORMAT('01-08-13 10:02:58', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('02-08-13 22:58:02', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (2, '001', DATE_FORMAT('06-08-13 08:30:00', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('07-08-13 17:30:00', '%d-%m-%y %H:%i:%s'), 2, '感冒', '已打回', '病假', NULL, '同意', DATE_FORMAT('01-08-13 10:02:58', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('02-08-13 22:58:02', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (3, '001', DATE_FORMAT('09-08-13 08:30:00', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('09-08-13 17:30:00', '%d-%m-%y %H:%i:%s'), 1, '感冒', '已审批 ', '病假', '002', '同意', DATE_FORMAT('01-08-13 10:02:58', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('05-09-13 15:49:04', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (4, '004', DATE_FORMAT('13-08-13 08:30:00', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('14-08-13 17:30:00', '%d-%m-%y %H:%i:%s'), 2, '事假', '已审批', '事假', NULL, NULL, NULL, NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (101, '001', DATE_FORMAT('13-08-13', '%d-%m-%y'), DATE_FORMAT('13-08-13', '%d-%m-%y'), 1, '请假', '已审批 ', '年假', '002', '通过', DATE_FORMAT('05-09-13 14:31:19', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('05-09-13 15:21:23', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (127, '001', DATE_FORMAT('18-02-2014 09:55:21', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('20-02-2014 09:55:23', '%d-%m-%y %H:%i:%s'), 2, '44', '待审批', '年假', '002', NULL, DATE_FORMAT('18-02-2014 09:55:28', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (102, '001', DATE_FORMAT('13-08-13', '%d-%m-%y'), DATE_FORMAT('13-08-13', '%d-%m-%y'), 1, '请假大', '待审批', '事假', '002', NULL, DATE_FORMAT('05-09-13 14:38:50', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (104, '001', DATE_FORMAT('05-09-13', '%d-%m-%y'), DATE_FORMAT('06-09-13', '%d-%m-%y'), 1, '结婚', '已打回 ', '婚假', '002', '回吧', DATE_FORMAT('05-09-13 15:26:00', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('05-09-13 15:42:31', '%d-%m-%y %H:%i:%s'));
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (122, '001', DATE_FORMAT('06-09-13', '%d-%m-%y'), DATE_FORMAT('08-09-13', '%d-%m-%y'), 2, '田', '待审批', '年假', '002', NULL, DATE_FORMAT('06-09-13 09:15:18', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (124, '001', DATE_FORMAT('05-09-13 09:20:00', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('07-09-13 09:20:00', '%d-%m-%y %H:%i:%s'), 2, '年假', '待审批', '年假', '002', NULL, DATE_FORMAT('06-09-13 09:20:25', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (125, '001', DATE_FORMAT('06-09-13 14:19:47', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('07-09-13 14:19:50', '%d-%m-%y %H:%i:%s'), 1, '婚假', '待审批', '婚假', '002', NULL, DATE_FORMAT('06-09-13 14:20:10', '%d-%m-%y %H:%i:%s'), NULL);
INSERT INTO BIZ_LEAVE (ID, EMPLOYEE_SN, STARTTIME, ENDTIME, LEAVEDAY, REASON, STATUS, LEAVETYPE, NEXT_DEAL_SN, APPROVE_OPINION, CREATETIME, MODIFYTIME)
VALUES (140, '001', DATE_FORMAT('11-09-13 14:51:35', '%d-%m-%y %H:%i:%s'), DATE_FORMAT('12-09-13 14:51:35', '%d-%m-%y %H:%i:%s'), 2, 'tt', '待审批', '年假', '002', NULL, DATE_FORMAT('09-09-13 14:51:48', '%d-%m-%y %H:%i:%s'), NULL);
COMMIT;

#添加职位数据
INSERT INTO SYS_POSITION (ID, NAME_CN, NAME_EN)
VALUES (1, '员工', 'staff');
INSERT INTO SYS_POSITION (ID, NAME_CN, NAME_EN)
VALUES (2, '部门经理', 'manager');
INSERT INTO SYS_POSITION (ID, NAME_CN, NAME_EN)
VALUES (3, '总经理', 'generalmanager');
INSERT INTO SYS_POSITION (ID, NAME_CN, NAME_EN)
VALUES (4, '财务', 'cashier');
COMMIT;


/*alter table SYS_EMPLOYEE enable constraint DEPART_ID;

alter table BIZ_CLAIM_VOUCHER enable constraint E_SN;

alter table BIZ_CLAIM_VOUCHER enable constraint F_SN;

alter table BIZ_CHECK_RESULT enable constraint FK_CLAIM_ID;

alter table BIZ_CLAIM_VOUCHER_DETAIL enable constraint CLAIME_ID;

alter table BIZ_CLAIM_VOUCHER_STATISTICS enable constraint DEPTID_ID;

alter table SYS_DEPARTMENT enable all triggers;

alter table SYS_EMPLOYEE enable all triggers;

alter table BIZ_CLAIM_VOUCHER enable all triggers;

alter table BIZ_CHECK_RESULT enable all triggers;

alter table BIZ_CLAIM_VOUCHER_DETAIL enable all triggers;

alter table BIZ_CLAIM_VOUCHER_STATISTICS enable all triggers;

alter table BIZ_CLAIM_VOUYEAR__STATISTICS enable all triggers;

alter table BIZ_LEAVE enable all triggers;

alter table SYS_POSITION enable all triggers;
*/

