-- MySQL dump 10.11
--
-- Host: localhost    Database: node_eduoa
-- ------------------------------------------------------
-- Server version	5.0.87-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cms_article`
--

DROP TABLE IF EXISTS `cms_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_article` (
  `id` bigint(20) NOT NULL auto_increment,
  `channel_id` bigint(20) default NULL,
  `article_class_id` bigint(20) default NULL,
  `title` varchar(50) default NULL,
  `summary` varchar(50) default NULL,
  `content` text,
  `hits` int(11) default NULL,
  `create_time` datetime default NULL,
  `create_user_id` bigint(20) default NULL,
  `update_time` datetime default NULL,
  `update_user_id` bigint(20) default NULL,
  `article_state` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_32` (`channel_id`),
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`channel_id`) REFERENCES `cms_channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_article`
--

LOCK TABLES `cms_article` WRITE;
/*!40000 ALTER TABLE `cms_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_article_teacher`
--

DROP TABLE IF EXISTS `cms_article_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_article_teacher` (
  `id` bigint(20) NOT NULL auto_increment,
  `article_id` bigint(20) default NULL,
  `teacher_name` varchar(10) default NULL,
  `photo_url` varchar(100) default NULL,
  `attachment_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_33` (`article_id`),
  CONSTRAINT `FK_Reference_33` FOREIGN KEY (`article_id`) REFERENCES `cms_article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_article_teacher`
--

LOCK TABLES `cms_article_teacher` WRITE;
/*!40000 ALTER TABLE `cms_article_teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_article_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_channel`
--

DROP TABLE IF EXISTS `cms_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_channel` (
  `id` bigint(20) NOT NULL auto_increment,
  `channel_name` varchar(100) default NULL,
  `channel_sort` int(11) default NULL,
  `channel_url` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_channel`
--

LOCK TABLES `cms_channel` WRITE;
/*!40000 ALTER TABLE `cms_channel` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_appraisal`
--

DROP TABLE IF EXISTS `oa_appraisal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_appraisal` (
  `id` bigint(20) NOT NULL auto_increment,
  `atype` int(11) default NULL,
  `start_month` datetime default NULL,
  `end_month` datetime default NULL,
  `teacher_id` bigint(20) default NULL,
  `create_time` datetime default NULL,
  `user_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_appraisal`
--

LOCK TABLES `oa_appraisal` WRITE;
/*!40000 ALTER TABLE `oa_appraisal` DISABLE KEYS */;
/*!40000 ALTER TABLE `oa_appraisal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_certificate`
--

DROP TABLE IF EXISTS `oa_certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_certificate` (
  `id` bigint(20) NOT NULL auto_increment,
  `type_id` bigint(20) default NULL,
  `advanced` int(11) default NULL,
  `certificates_time` datetime default NULL,
  `description` varchar(200) default NULL,
  `teacher_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_31` (`type_id`),
  KEY `FK_Reference_30` (`teacher_id`),
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`teacher_id`) REFERENCES `oa_teacher_info` (`id`),
  CONSTRAINT `FK_Reference_31` FOREIGN KEY (`type_id`) REFERENCES `oa_certificate_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_certificate`
--

LOCK TABLES `oa_certificate` WRITE;
/*!40000 ALTER TABLE `oa_certificate` DISABLE KEYS */;
/*!40000 ALTER TABLE `oa_certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_certificate_type`
--

DROP TABLE IF EXISTS `oa_certificate_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_certificate_type` (
  `id` bigint(20) NOT NULL auto_increment,
  `type_name` varchar(50) default NULL,
  `description` varchar(200) default NULL,
  `type_level` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_certificate_type`
--

LOCK TABLES `oa_certificate_type` WRITE;
/*!40000 ALTER TABLE `oa_certificate_type` DISABLE KEYS */;
INSERT INTO `oa_certificate_type` VALUES (1,'阿斯达','123',23),(2,'12322231','123',222);
/*!40000 ALTER TABLE `oa_certificate_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_class`
--

DROP TABLE IF EXISTS `oa_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_class` (
  `id` bigint(20) NOT NULL auto_increment,
  `grade_id` bigint(20) default NULL,
  `class_name` varchar(50) default NULL,
  `category` int(11) default NULL,
  `description` varchar(200) default NULL,
  `number_limit` int(11) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `create_user_id` bigint(20) default NULL,
  `update_user_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_grade_class` (`grade_id`),
  CONSTRAINT `FK_Reference_grade_class` FOREIGN KEY (`grade_id`) REFERENCES `oa_grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_class`
--

LOCK TABLES `oa_class` WRITE;
/*!40000 ALTER TABLE `oa_class` DISABLE KEYS */;
INSERT INTO `oa_class` VALUES (7,13,'高一（1）班',1,'',50,'2013-07-13 22:24:19',NULL,NULL,NULL),(8,13,'高一（2）班',1,'',50,'2013-07-13 22:24:36',NULL,NULL,NULL),(9,13,'高一（3）班',1,'',50,'2013-07-13 22:24:50',NULL,NULL,NULL),(10,13,'高一（4）班',1,'',50,'2013-07-13 22:25:04',NULL,NULL,NULL),(11,14,'高一（1）班',1,'',50,'2013-07-13 22:25:28',NULL,NULL,NULL),(12,14,'高一（2）班',1,'',50,'2013-07-13 22:25:45',NULL,NULL,NULL),(13,14,'高一（3）班',1,'',50,'2013-07-13 22:25:59',NULL,NULL,NULL),(14,14,'高一（4）班',1,'',50,'2013-07-13 22:26:08',NULL,NULL,NULL),(15,15,'高二（1）班',2,'',50,'2013-07-13 22:26:28',NULL,NULL,NULL),(16,15,'高二（2）班',2,'',50,'2013-07-13 22:26:46',NULL,NULL,NULL),(17,15,'高二（3）班',3,'',50,'2013-07-13 22:27:02',NULL,NULL,NULL),(18,15,'高二（4）班',3,'',50,'2013-07-13 22:27:40',NULL,NULL,NULL),(19,16,'高二（1）班',2,'',50,'2013-07-13 22:29:09',NULL,NULL,NULL),(20,16,'高二（2）班',3,'',50,'2013-07-13 22:29:27',NULL,NULL,NULL),(21,16,'高二（3）班',2,'',50,'2013-07-13 22:29:38',NULL,NULL,NULL),(22,16,'高二（4）班',3,'',50,'2013-07-13 22:29:57',NULL,NULL,NULL);
/*!40000 ALTER TABLE `oa_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_class_teacher`
--

DROP TABLE IF EXISTS `oa_class_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_class_teacher` (
  `id` bigint(20) NOT NULL auto_increment,
  `class_id` bigint(20) default NULL,
  `teacher_id` bigint(20) default NULL,
  `start_time` datetime default NULL,
  `end_time` datetime default NULL,
  `head_teacher` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_22` (`class_id`),
  KEY `FK_Reference_21` (`teacher_id`),
  CONSTRAINT `FK_Reference_21` FOREIGN KEY (`teacher_id`) REFERENCES `oa_teacher_info` (`id`),
  CONSTRAINT `FK_Reference_22` FOREIGN KEY (`class_id`) REFERENCES `oa_class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_class_teacher`
--

LOCK TABLES `oa_class_teacher` WRITE;
/*!40000 ALTER TABLE `oa_class_teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `oa_class_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_contact`
--

DROP TABLE IF EXISTS `oa_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_contact` (
  `id` bigint(20) NOT NULL auto_increment,
  `student_id` bigint(20) default NULL,
  `nexus` varchar(20) default NULL,
  `contact_name` varchar(50) default NULL,
  `job` varchar(50) default NULL,
  `phone` varchar(20) default NULL,
  `qq` varchar(20) default NULL,
  `email` varchar(20) default NULL,
  `address` varchar(200) default NULL,
  `home_phone` varchar(20) default NULL,
  `office_phone` varchar(20) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_student_contact` (`student_id`),
  CONSTRAINT `FK_Reference_student_contact` FOREIGN KEY (`student_id`) REFERENCES `oa_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_contact`
--

LOCK TABLES `oa_contact` WRITE;
/*!40000 ALTER TABLE `oa_contact` DISABLE KEYS */;
INSERT INTO `oa_contact` VALUES (3,11,'34523432','234324','234324','23432432',NULL,NULL,NULL,NULL,NULL,'2013-07-14 00:00:00','2013-07-14 18:57:55'),(4,12,'123123','123123','','1231231',NULL,NULL,NULL,NULL,NULL,'2013-07-14 18:48:14',NULL);
/*!40000 ALTER TABLE `oa_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_dictionary`
--

DROP TABLE IF EXISTS `oa_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_dictionary` (
  `id` bigint(20) NOT NULL auto_increment,
  `dict_name` varchar(50) default NULL,
  `dict_key` varchar(50) default NULL,
  `parent_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_18` (`parent_id`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`parent_id`) REFERENCES `oa_dictionary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_dictionary`
--

LOCK TABLES `oa_dictionary` WRITE;
/*!40000 ALTER TABLE `oa_dictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `oa_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_dictionary_value`
--

DROP TABLE IF EXISTS `oa_dictionary_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_dictionary_value` (
  `id` bigint(20) NOT NULL auto_increment,
  `dictionary_id` bigint(20) default NULL,
  `dict_label` varchar(50) default NULL,
  `dict_value` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_19` (`dictionary_id`),
  CONSTRAINT `FK_Reference_19` FOREIGN KEY (`dictionary_id`) REFERENCES `oa_dictionary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_dictionary_value`
--

LOCK TABLES `oa_dictionary_value` WRITE;
/*!40000 ALTER TABLE `oa_dictionary_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `oa_dictionary_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_goods_apply`
--

DROP TABLE IF EXISTS `oa_goods_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_goods_apply` (
  `id` bigint(20) NOT NULL auto_increment,
  `leader_id` bigint(20) default NULL,
  `leader_name` varchar(30) default NULL,
  `leader_position` varchar(30) default NULL,
  `goods_id` bigint(20) default NULL,
  `goods_name` varchar(50) default NULL,
  `goods_unit` varchar(50) default NULL,
  `apply_teacher_id` bigint(20) default NULL,
  `apply_teacher_name` varchar(30) default NULL,
  `apply_organization_id` bigint(20) default NULL,
  `apply_organization_name` varchar(30) default NULL,
  `create_time` datetime default NULL,
  `apply_time` datetime default NULL,
  `apply_statue` int(11) default NULL,
  `statue` int(11) default NULL,
  `goods_count` int(11) default NULL,
  `goods_statue` int(11) default NULL,
  `sick_time` datetime default NULL,
  `commit_time` datetime default NULL,
  `lave` int(11) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_goods_apply`
--

LOCK TABLES `oa_goods_apply` WRITE;
/*!40000 ALTER TABLE `oa_goods_apply` DISABLE KEYS */;
INSERT INTO `oa_goods_apply` VALUES (3,3,'张惠妹','副校长',NULL,'笔记本','台',1,'admin',30,'办公室','2013-07-20 14:38:58',NULL,0,0,12,NULL,NULL,NULL,12),(4,3,'张惠妹','副校长',NULL,'电脑','21',1,'admin',30,'办公室','2013-07-20 13:50:47','2013-07-20 14:42:16',1,1,21,NULL,'2013-07-27 16:50:41',NULL,11),(5,3,'张惠妹','副校长',NULL,'123','123',1,'admin',30,'办公室','2013-07-20 14:38:53',NULL,0,0,123,NULL,NULL,NULL,123),(6,3,'张惠妹','副校长',NULL,'笔记本电脑','台',13,'林锋',30,'办公室','2013-07-29 20:40:41',NULL,0,0,12,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `oa_goods_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_goods_receive`
--

DROP TABLE IF EXISTS `oa_goods_receive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_goods_receive` (
  `id` bigint(20) NOT NULL auto_increment,
  `goods_apply_id` bigint(20) default NULL,
  `recipients_id` bigint(20) NOT NULL,
  `recipients_name` varchar(30) default NULL,
  `recipients_time` datetime default NULL,
  `recipients_count` int(11) default NULL,
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='物品领取';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_goods_receive`
--

LOCK TABLES `oa_goods_receive` WRITE;
/*!40000 ALTER TABLE `oa_goods_receive` DISABLE KEYS */;
INSERT INTO `oa_goods_receive` VALUES (3,4,13,'林锋','2013-07-27 16:50:51',10,'2013-07-27 16:50:52');
/*!40000 ALTER TABLE `oa_goods_receive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_grade`
--

DROP TABLE IF EXISTS `oa_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_grade` (
  `id` bigint(20) NOT NULL auto_increment,
  `grade_name` varchar(50) default NULL,
  `description` varchar(255) default NULL,
  `create_time` datetime default NULL,
  `current_year` int(11) default NULL,
  `current_half` int(11) default NULL,
  `start_time` datetime default NULL,
  `end_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_grade`
--

LOCK TABLES `oa_grade` WRITE;
/*!40000 ALTER TABLE `oa_grade` DISABLE KEYS */;
INSERT INTO `oa_grade` VALUES (13,'高一年级','','2013-07-13 22:20:14',2013,1,'2012-09-01 00:00:00','2013-02-01 00:00:00'),(14,'高一年级','','2013-07-13 22:21:08',2013,2,'2013-03-01 00:00:00','2013-06-26 00:00:00'),(15,'高二年级','','2013-07-13 22:22:41',2013,1,'2012-09-01 00:00:00','2013-02-01 00:00:00'),(16,'高二年级','','2013-07-13 22:23:08',2013,2,'2013-03-01 00:00:00','2013-06-26 00:00:00'),(17,'高三年级','','2013-07-13 22:23:28',2013,1,'2012-09-01 00:00:00','2013-02-01 00:00:00'),(18,'高三年级','','2013-07-13 22:23:44',2013,2,'2013-03-01 00:00:00','2013-06-26 00:00:00');
/*!40000 ALTER TABLE `oa_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_leave_attendance`
--

DROP TABLE IF EXISTS `oa_leave_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_leave_attendance` (
  `id` bigint(20) NOT NULL auto_increment,
  `teacher_id` bigint(20) NOT NULL,
  `teacher_name` varchar(30) NOT NULL,
  `organization_id` bigint(20) NOT NULL,
  `organization_name` varchar(30) NOT NULL,
  `leave_start_time` datetime default NULL,
  `leave_end_time` datetime default NULL,
  `leave_time` bigint(20) default NULL,
  `create_time` datetime NOT NULL,
  `attendance_date` bigint(20) default NULL,
  `leave_end` int(11) default NULL,
  `leave_start` int(11) default NULL,
  `leave_time_cn` varchar(30) default NULL,
  `attendance_date_cn` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_leave_attendance`
--

LOCK TABLES `oa_leave_attendance` WRITE;
/*!40000 ALTER TABLE `oa_leave_attendance` DISABLE KEYS */;
INSERT INTO `oa_leave_attendance` VALUES (1,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-25 23:11:07',1374422400000,NULL,NULL,NULL,'2013-7-22'),(2,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-25 23:11:07',1374508800000,NULL,NULL,NULL,'2013-7-23'),(3,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-25 23:11:07',1374595200000,NULL,NULL,NULL,'2013-7-24'),(4,13,'林锋',30,'办公室','2013-07-25 23:11:14',NULL,NULL,'2013-07-25 23:11:07',1374681600000,NULL,1,NULL,'2013-7-25'),(5,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-25 23:11:07',1374768000000,NULL,NULL,NULL,'2013-7-26'),(6,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-25 23:11:08',1374854400000,NULL,NULL,NULL,'2013-7-27'),(7,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-25 23:11:08',1374940800000,NULL,NULL,NULL,'2013-7-28'),(8,13,'林锋',30,'办公室','2013-07-29 20:47:13','2013-07-29 20:47:30',NULL,'2013-07-29 20:21:36',1375027200000,1,1,'00:00:17','2013-7-29'),(9,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-29 20:21:36',1375113600000,NULL,NULL,NULL,'2013-7-30'),(10,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-29 20:21:36',1375200000000,NULL,NULL,NULL,'2013-7-31'),(11,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-29 20:21:36',1375286400000,NULL,NULL,NULL,'2013-8-1'),(12,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-29 20:21:36',1375372800000,NULL,NULL,NULL,'2013-8-2'),(13,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-29 20:21:36',1375459200000,NULL,NULL,NULL,'2013-8-3'),(14,13,'林锋',30,'办公室',NULL,NULL,NULL,'2013-07-29 20:21:36',1375545600000,NULL,NULL,NULL,'2013-8-4');
/*!40000 ALTER TABLE `oa_leave_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_leave_permit`
--

DROP TABLE IF EXISTS `oa_leave_permit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_leave_permit` (
  `id` bigint(20) NOT NULL auto_increment,
  `leader_id` bigint(20) default NULL,
  `leader_name` varchar(30) default NULL,
  `leader_position` varchar(30) default NULL,
  `reason` varchar(200) default NULL,
  `remark` varchar(255) default NULL,
  `start_time` datetime default NULL,
  `end_time` datetime default NULL,
  `apply_time` datetime default NULL,
  `apply_teacher_id` bigint(20) default NULL,
  `apply_teacher_name` varchar(30) default NULL,
  `create_time` datetime default NULL,
  `statue` int(11) default NULL,
  `apply_statue` int(11) default NULL,
  `apply_day` int(11) default NULL,
  `real_day` int(11) default NULL,
  `sick_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_leave_permit`
--

LOCK TABLES `oa_leave_permit` WRITE;
/*!40000 ALTER TABLE `oa_leave_permit` DISABLE KEYS */;
INSERT INTO `oa_leave_permit` VALUES (1,12,'张惠妹','副校长','123','123213','2013-07-09 20:22:46','2013-07-25 20:22:50','2013-07-29 20:23:25',1,'admin','2013-07-29 20:23:25',1,1,21,NULL,NULL);
/*!40000 ALTER TABLE `oa_leave_permit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_multimedia`
--

DROP TABLE IF EXISTS `oa_multimedia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_multimedia` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(30) default NULL COMMENT '课题章节',
  `grade_id` bigint(20) default NULL COMMENT '年级ID',
  `grade_name` varchar(30) default NULL COMMENT '年级',
  `subject_id` bigint(20) default NULL COMMENT '学科',
  `subject_name` varchar(30) default NULL COMMENT '学科',
  `teacher_id` bigint(20) default NULL COMMENT '制作人ID',
  `teacher_name` varchar(30) default NULL COMMENT '制作人',
  `use_count` int(11) default '0',
  `need_score` int(11) default '5',
  `upload_time` datetime default NULL COMMENT '上传日期',
  `upload_time_long` bigint(20) default NULL COMMENT '上传时间',
  `attachment_id` bigint(20) default NULL,
  `attachment_title` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='多媒体教学';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_multimedia`
--

LOCK TABLES `oa_multimedia` WRITE;
/*!40000 ALTER TABLE `oa_multimedia` DISABLE KEYS */;
INSERT INTO `oa_multimedia` VALUES (4,'213213',15,'高二年级',3,'语文',13,'林锋',1,NULL,'2013-07-30 22:26:31',1375194391352,11,'213.txt');
/*!40000 ALTER TABLE `oa_multimedia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_organizational_structure`
--

DROP TABLE IF EXISTS `oa_organizational_structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_organizational_structure` (
  `id` bigint(20) NOT NULL auto_increment,
  `structure_name` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `user_id` bigint(20) default NULL,
  `attachment_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_organizational_structure`
--

LOCK TABLES `oa_organizational_structure` WRITE;
/*!40000 ALTER TABLE `oa_organizational_structure` DISABLE KEYS */;
INSERT INTO `oa_organizational_structure` VALUES (17,'组织机构','2013-07-07 14:46:52',1,17);
/*!40000 ALTER TABLE `oa_organizational_structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_position`
--

DROP TABLE IF EXISTS `oa_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_position` (
  `id` bigint(20) NOT NULL auto_increment,
  `position_name` varchar(50) default NULL,
  `description` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_position`
--

LOCK TABLES `oa_position` WRITE;
/*!40000 ALTER TABLE `oa_position` DISABLE KEYS */;
INSERT INTO `oa_position` VALUES (7,'校长',''),(8,'副校长',''),(9,'年级主任',''),(10,'年级教学主任',''),(11,'年级纪律主任',''),(12,'年级组长',''),(13,'备科组长',''),(14,'学科教研组长',''),(15,'教务处主任',''),(16,'政教处主任',''),(17,'办公室主任','');
/*!40000 ALTER TABLE `oa_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_purchase_apply`
--

DROP TABLE IF EXISTS `oa_purchase_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_purchase_apply` (
  `id` bigint(20) NOT NULL auto_increment,
  `leader_id` bigint(20) default NULL COMMENT '审批人ID',
  `leader_name` varchar(30) default NULL COMMENT '审批人名称',
  `leader_position` varchar(30) default NULL COMMENT '审批人职位',
  `goods_id` bigint(20) default NULL COMMENT '物品ID',
  `goods_name` varchar(50) default NULL COMMENT '物品名称',
  `goods_unit` varchar(50) default NULL COMMENT '物品单位',
  `goods_count` int(11) default NULL COMMENT '物品数量',
  `apply_teacher_id` bigint(20) default NULL COMMENT '申请人编号',
  `apply_teacher_name` varchar(30) default NULL COMMENT '申请人姓名',
  `apply_organization_id` bigint(20) default NULL COMMENT '申请部门ID',
  `apply_organization_name` varchar(30) default NULL COMMENT '申请部门名称',
  `apply_time` datetime default NULL,
  `apply_time_long` bigint(20) default NULL,
  `commit_time` datetime default NULL,
  `commit_time_long` bigint(20) default NULL,
  `apply_statue` int(11) default NULL COMMENT '审批状态（0：未审批；1：已通过；2：未通过）',
  `statue` int(11) default NULL COMMENT '提交状态（0：未提交；1：已提交）',
  `remark` varchar(300) default NULL,
  `create_time` datetime default NULL,
  `purchase` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='采购申请';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_purchase_apply`
--

LOCK TABLES `oa_purchase_apply` WRITE;
/*!40000 ALTER TABLE `oa_purchase_apply` DISABLE KEYS */;
INSERT INTO `oa_purchase_apply` VALUES (2,12,'张惠妹','副校长',NULL,'请问','王企鹅',13,1,'admin',30,'办公室',NULL,NULL,NULL,NULL,0,0,'12321','2013-07-28 10:36:26',NULL),(3,12,'张惠妹','副校长',NULL,'123','123',12312,13,'林锋',30,'办公室','2013-07-28 14:37:00',NULL,'2013-07-28 10:41:24',NULL,1,1,'3212313','2013-07-28 10:39:03',1),(4,12,'张惠妹','副校长',NULL,'444','44',44,13,'林锋',30,'办公室','2013-07-28 14:37:00',NULL,'2013-07-28 14:30:35',NULL,1,1,'444','2013-07-28 10:45:17',NULL);
/*!40000 ALTER TABLE `oa_purchase_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_purchase_conduct`
--

DROP TABLE IF EXISTS `oa_purchase_conduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_purchase_conduct` (
  `id` bigint(20) NOT NULL auto_increment,
  `purchase_id` bigint(20) default NULL,
  `conduct_teacher_id` bigint(20) default NULL,
  `conduct_teacher_name` varchar(30) default NULL,
  `conduct_organization_id` bigint(20) default NULL COMMENT '采购部门ID',
  `conduct_organization_name` varchar(30) default NULL COMMENT '采购部门名称',
  `conduct_start_time` datetime default NULL,
  `conduct_end_time` datetime default NULL,
  `conduct_start_time_long` bigint(20) default NULL,
  `conduct_end_time_long` bigint(20) default NULL,
  `goods_id` bigint(20) default NULL COMMENT '物品ID',
  `goods_name` varchar(50) default NULL COMMENT '物品名称',
  `goods_unit` varchar(50) default NULL COMMENT '物品单位',
  `goods_count` int(11) default NULL COMMENT '物品数量',
  `leader_id` bigint(20) default NULL COMMENT '审批人ID',
  `leader_name` varchar(30) default NULL COMMENT '审批人名称',
  `leader_position` varchar(30) default NULL COMMENT '审批人职位',
  `goods_unit_price` float(8,2) default NULL,
  `price_sum` float(8,2) default NULL,
  `create_time` datetime default NULL,
  `commit_time` datetime default NULL,
  `apply_time` datetime default NULL,
  `apply_statue` int(11) default NULL COMMENT '审批状态（0：未审批；1：已通过；2：未通过）',
  `statue` int(11) default NULL COMMENT '提交状态（0：未提交；1：已提交）',
  `remark` varchar(300) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_45` (`purchase_id`),
  CONSTRAINT `FK_Reference_45` FOREIGN KEY (`purchase_id`) REFERENCES `oa_purchase_apply` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='实物采购';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_purchase_conduct`
--

LOCK TABLES `oa_purchase_conduct` WRITE;
/*!40000 ALTER TABLE `oa_purchase_conduct` DISABLE KEYS */;
INSERT INTO `oa_purchase_conduct` VALUES (2,3,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,'123','123',231,12,'张惠妹','副校长',123.00,28413.00,'2013-07-28 17:24:23','2013-07-28 17:24:37',NULL,0,1,NULL);
/*!40000 ALTER TABLE `oa_purchase_conduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_registration_attendance`
--

DROP TABLE IF EXISTS `oa_registration_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_registration_attendance` (
  `id` bigint(20) NOT NULL auto_increment,
  `attendance_date` bigint(20) NOT NULL,
  `teacher_id` bigint(20) default NULL,
  `teacher_name` varchar(30) default NULL,
  `organization_id` bigint(20) default NULL,
  `organization_name` varchar(30) default NULL,
  `morning_start` int(11) default NULL COMMENT '上班状态（0：早退；1：正常；2：迟到）',
  `morning_start_time` datetime default NULL,
  `morning_end` int(11) default NULL COMMENT '上班状态（0：早退；1：正常；2：迟到）',
  `morning_end_time` datetime default NULL,
  `afternoon_start` int(11) default NULL COMMENT '上班状态（0：早退；1：正常；2：迟到）',
  `afternoon_start_time` datetime default NULL,
  `afternoon_end` int(11) default NULL COMMENT '上班状态（0：早退；1：正常；2：迟到）',
  `afternoon_end_time` datetime default NULL,
  `night_start` int(11) default NULL COMMENT '上班状态（0：早退；1：正常；2：迟到）',
  `night_start_time` datetime default NULL,
  `night_end` int(11) default NULL COMMENT '上班状态（0：早退；1：正常；2：迟到）',
  `night_end_time` datetime default NULL,
  `create_time` datetime default NULL,
  `attendance_date_cn` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='签到考勤';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_registration_attendance`
--

LOCK TABLES `oa_registration_attendance` WRITE;
/*!40000 ALTER TABLE `oa_registration_attendance` DISABLE KEYS */;
INSERT INTO `oa_registration_attendance` VALUES (22,1374422400000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-24 17:03:31','2013-7-22'),(23,1374508800000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-24 17:03:33','2013-7-23'),(24,1374595200000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-07-24 20:54:03','2013-07-24 17:03:33','2013-7-24'),(25,1374681600000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'2013-07-25 23:18:05','2013-07-24 17:03:33','2013-7-25'),(26,1374768000000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-24 17:03:33','2013-7-26'),(27,1374854400000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-24 17:03:33','2013-7-27'),(28,1374940800000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-24 17:03:33','2013-7-28'),(29,1375027200000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-29 20:21:29','2013-7-29'),(30,1375113600000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-29 20:21:29','2013-7-30'),(31,1375200000000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-29 20:21:29','2013-7-31'),(32,1375286400000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-29 20:21:29','2013-8-1'),(33,1375372800000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-29 20:21:29','2013-8-2'),(34,1375459200000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-29 20:21:29','2013-8-3'),(35,1375545600000,13,'林锋',30,'办公室',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2013-07-29 20:21:29','2013-8-4');
/*!40000 ALTER TABLE `oa_registration_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_score`
--

DROP TABLE IF EXISTS `oa_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_score` (
  `id` bigint(20) NOT NULL auto_increment,
  `exam_date` date default NULL,
  `exam_date_time` bigint(20) default NULL,
  `exam_type` int(11) default NULL COMMENT '考试批次类型',
  `student_number` int(11) default NULL COMMENT '序号',
  `exam_no` varchar(10) default NULL,
  `grade_name` varchar(30) default NULL,
  `grade_id` bigint(20) default NULL,
  `class_id` bigint(20) default NULL,
  `class_name` varchar(30) default NULL,
  `subject_id` bigint(20) default NULL,
  `subject_name` varchar(30) default NULL,
  `student_name` varchar(50) default NULL,
  `score` float default NULL,
  `teacher_id` bigint(20) default NULL,
  `teacher_name` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `remark` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='成绩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_score`
--

LOCK TABLES `oa_score` WRITE;
/*!40000 ALTER TABLE `oa_score` DISABLE KEYS */;
INSERT INTO `oa_score` VALUES (3,'2013-08-13',1376323200000,0,2,'123213','高一年级',13,9,'高一（3）班',3,'语文','张三',57,13,'林锋','2013-08-11 18:42:01','sad'),(4,'2013-08-11',1376150400000,0,3,'323','高一年级',13,9,'高一（3）班',3,'语文','林锋',34,13,'林锋','2013-08-11 18:42:44','234324'),(5,'2013-08-11',1376150400000,0,2,'23123','高一年级',13,9,'高一（3）班',3,'语文','张三',23,13,'林锋','2013-08-11 18:43:04','3213'),(6,'2013-08-11',1376150400000,0,3,'232','高一年级',13,9,'高一（3）班',3,'语文','林锋',32,13,'林锋','2013-08-11 18:43:53','23443'),(7,'2013-08-11',1376150400000,0,3,'324','高一年级',13,9,'高一（3）班',3,'语文','林锋',34,13,'林锋','2013-08-11 18:44:44','234234');
/*!40000 ALTER TABLE `oa_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_student`
--

DROP TABLE IF EXISTS `oa_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_student` (
  `id` bigint(20) NOT NULL auto_increment,
  `student_name` varchar(50) default NULL,
  `id_number` varchar(18) default NULL,
  `gender` int(11) default NULL,
  `student_number` int(11) default NULL,
  `birthday` datetime default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `operator_id` bigint(20) default NULL,
  `grade_name` varchar(30) default NULL,
  `class_name` varchar(30) default NULL,
  `current_year` int(11) default NULL,
  `category_name` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_student`
--

LOCK TABLES `oa_student` WRITE;
/*!40000 ALTER TABLE `oa_student` DISABLE KEYS */;
INSERT INTO `oa_student` VALUES (11,'林锋','3454214324',1,3,'2013-07-08 00:00:00','2013-07-14 18:57:55',NULL,NULL,'高一年级','高一（3）班',2013,'全科'),(12,'张三','76543',0,2,'2013-07-17 00:00:00','2013-07-14 18:48:14',NULL,NULL,'高一年级','高一（3）班',2013,'全科');
/*!40000 ALTER TABLE `oa_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_student_class`
--

DROP TABLE IF EXISTS `oa_student_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_student_class` (
  `id` bigint(20) NOT NULL auto_increment,
  `student_id` bigint(20) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  `start_time` datetime default NULL,
  `end_time` datetime default NULL,
  `current_year` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_29` (`class_id`),
  KEY `FK_Reference_28` (`student_id`),
  CONSTRAINT `FK_Reference_28` FOREIGN KEY (`student_id`) REFERENCES `oa_student` (`id`),
  CONSTRAINT `FK_Reference_29` FOREIGN KEY (`class_id`) REFERENCES `oa_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_student_class`
--

LOCK TABLES `oa_student_class` WRITE;
/*!40000 ALTER TABLE `oa_student_class` DISABLE KEYS */;
INSERT INTO `oa_student_class` VALUES (3,11,9,'2012-09-01 00:00:00','2013-02-01 00:00:00',2013),(4,12,9,'2012-09-01 00:00:00','2013-02-01 00:00:00',2013),(5,11,9,'2012-09-01 00:00:00','2013-02-01 00:00:00',2013);
/*!40000 ALTER TABLE `oa_student_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_student_grade`
--

DROP TABLE IF EXISTS `oa_student_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_student_grade` (
  `id` bigint(20) NOT NULL auto_increment,
  `grade_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `start_time` datetime default NULL,
  `end_time` datetime default NULL,
  `current_year` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_27` (`grade_id`),
  KEY `FK_Reference_26` (`student_id`),
  CONSTRAINT `FK_Reference_26` FOREIGN KEY (`student_id`) REFERENCES `oa_student` (`id`),
  CONSTRAINT `FK_Reference_27` FOREIGN KEY (`grade_id`) REFERENCES `oa_grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_student_grade`
--

LOCK TABLES `oa_student_grade` WRITE;
/*!40000 ALTER TABLE `oa_student_grade` DISABLE KEYS */;
INSERT INTO `oa_student_grade` VALUES (3,13,11,'2012-09-01 00:00:00','2013-02-01 00:00:00',2013),(4,13,12,'2012-09-01 00:00:00','2013-02-01 00:00:00',2013),(5,13,11,'2012-09-01 00:00:00','2013-02-01 00:00:00',2013);
/*!40000 ALTER TABLE `oa_student_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_subject`
--

DROP TABLE IF EXISTS `oa_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_subject` (
  `id` bigint(20) NOT NULL auto_increment,
  `subject_name` varchar(50) default NULL,
  `description` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_subject`
--

LOCK TABLES `oa_subject` WRITE;
/*!40000 ALTER TABLE `oa_subject` DISABLE KEYS */;
INSERT INTO `oa_subject` VALUES (3,'语文',''),(4,'数学',''),(5,'英语',''),(6,'物理',''),(7,'化学',''),(8,'生物','');
/*!40000 ALTER TABLE `oa_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_teacher_info`
--

DROP TABLE IF EXISTS `oa_teacher_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_teacher_info` (
  `id` bigint(20) NOT NULL auto_increment,
  `teacher_name` varchar(50) default NULL,
  `teacher_number` varchar(20) default NULL,
  `grade_id` bigint(20) default NULL,
  `org_id` bigint(20) default NULL,
  `id_number` varchar(18) default NULL,
  `is_teacher` int(11) default NULL,
  `position_id` bigint(20) default NULL,
  `phone` varchar(20) default NULL,
  `head_teacher` int(11) default NULL,
  `email` varchar(50) default NULL,
  `subject_id` bigint(20) default NULL,
  `gender` int(11) default NULL,
  `birthday` datetime default NULL,
  `political_landscape` int(11) default NULL,
  `join_time` datetime default NULL,
  `working_time` datetime default NULL,
  `original_education` int(11) default NULL,
  `original_education_time` datetime default NULL,
  `special` varchar(50) default NULL,
  `new_education` int(11) default NULL,
  `new_education_time` datetime default NULL,
  `certificates_time` datetime default NULL,
  `establishment` int(11) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `certificates_type` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_6` (`grade_id`),
  KEY `FK_Reference_8` (`position_id`),
  KEY `FK_Reference_9` (`subject_id`),
  KEY `FK_Reference_14` (`org_id`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`org_id`) REFERENCES `security_organization` (`id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`grade_id`) REFERENCES `oa_grade` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`position_id`) REFERENCES `oa_position` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`subject_id`) REFERENCES `oa_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_teacher_info`
--

LOCK TABLES `oa_teacher_info` WRITE;
/*!40000 ALTER TABLE `oa_teacher_info` DISABLE KEYS */;
INSERT INTO `oa_teacher_info` VALUES (2,'张慧华','100001',NULL,47,'123123',0,13,'213123',NULL,'12312321',3,0,'2013-07-17 00:00:00',0,'2013-07-11 00:00:00','2013-07-17 00:00:00',1,'2013-07-30 00:00:00',NULL,1,'2013-07-25 00:00:00',NULL,1,NULL,NULL,NULL),(3,'林锋','100002',NULL,43,'123123123',0,NULL,'12312321',NULL,'12312321',5,0,'2013-07-18 00:00:00',0,'2013-07-10 00:00:00','2013-07-09 00:00:00',1,'2013-07-26 00:00:00',NULL,1,'2013-07-27 00:00:00',NULL,1,NULL,NULL,NULL),(6,'1231','100002',NULL,39,'3123213',0,NULL,'123123',NULL,'123213',4,0,'2013-07-01 00:00:00',0,'2013-07-02 00:00:00','2013-07-01 00:00:00',1,'2013-07-02 00:00:00',NULL,1,'2013-07-24 00:00:00',NULL,1,NULL,NULL,NULL),(7,'林锋','100010',13,38,'510231231233431432',0,8,'123455432',NULL,'23456@1243.com',NULL,1,'2013-07-16 00:00:00',0,'2013-07-10 00:00:00','2013-07-15 00:00:00',1,'2013-07-25 00:00:00',NULL,1,'2013-07-18 00:00:00',NULL,1,NULL,NULL,NULL),(8,'林峰','123213',13,38,'124123',0,7,'2134215',NULL,'4342432',NULL,1,'2013-06-30 00:00:00',0,'2013-07-24 00:00:00','2013-07-09 00:00:00',1,'2013-08-02 00:00:00',NULL,1,'2013-07-03 00:00:00','2013-07-09 00:00:00',1,NULL,NULL,1),(12,'张惠妹','12341234',NULL,30,'123123123',0,8,'123123',NULL,'123123',NULL,0,'2013-07-24 00:00:00',0,'2013-07-01 00:00:00','2013-07-03 00:00:00',5,'2013-07-23 00:00:00',NULL,1,NULL,NULL,1,NULL,NULL,1),(13,'林锋','10010',14,30,'521545854558455874',0,9,'123123123123123',NULL,'123123@312312.com',3,0,'2013-07-20 00:00:00',0,NULL,NULL,1,NULL,NULL,1,NULL,NULL,1,NULL,NULL,1);
/*!40000 ALTER TABLE `oa_teacher_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_teaching_plan`
--

DROP TABLE IF EXISTS `oa_teaching_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_teaching_plan` (
  `id` bigint(20) NOT NULL auto_increment,
  `grade_id` bigint(20) default NULL,
  `grade_name` varchar(30) default NULL,
  `subject_id` bigint(20) default NULL,
  `subject_name` varchar(30) default NULL,
  `teacher_id` bigint(20) default NULL,
  `teacher_name` varchar(30) default NULL,
  `plan_title` varchar(200) default NULL,
  `attachment_id` bigint(20) default NULL,
  `attachment_title` varchar(30) default NULL,
  `statue` int(11) default NULL COMMENT '上传状态（0：未上传；1：已上传）',
  `ratings` int(11) default NULL COMMENT '评级（0：未评定；1：已评定）',
  `plan_level` int(11) default NULL COMMENT '级别（0：优秀；1：良好；2：合格；3：不合格）',
  `create_time` datetime default NULL,
  `create_time_long` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='教案';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_teaching_plan`
--

LOCK TABLES `oa_teaching_plan` WRITE;
/*!40000 ALTER TABLE `oa_teaching_plan` DISABLE KEYS */;
INSERT INTO `oa_teaching_plan` VALUES (3,14,'高一年级',4,'数学',13,'林锋','样子的的',3,'readme.txt',1,1,2,'2013-07-29 22:40:51',1375108851960);
/*!40000 ALTER TABLE `oa_teaching_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oa_worktime`
--

DROP TABLE IF EXISTS `oa_worktime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oa_worktime` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(30) NOT NULL,
  `commuting` int(11) NOT NULL COMMENT '是否上班：0：上班；1：下班',
  `work_time` varchar(30) NOT NULL,
  `work_time_long` bigint(20) default NULL,
  `time_order` int(11) default NULL COMMENT '排序',
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每天工作时间设定';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oa_worktime`
--

LOCK TABLES `oa_worktime` WRITE;
/*!40000 ALTER TABLE `oa_worktime` DISABLE KEYS */;
/*!40000 ALTER TABLE `oa_worktime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_log_entity`
--

DROP TABLE IF EXISTS `security_log_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_log_entity` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_time` datetime default NULL,
  `ip_address` varchar(16) default NULL,
  `log_level` varchar(16) default NULL,
  `message` varchar(255) default NULL,
  `username` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=430 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_log_entity`
--

LOCK TABLES `security_log_entity` WRITE;
/*!40000 ALTER TABLE `security_log_entity` DISABLE KEYS */;
INSERT INTO `security_log_entity` VALUES (1,'2013-07-16 13:47:49','127.0.0.1','TRACE','admin登录了系统。','admin'),(2,'2013-07-16 13:50:52','127.0.0.1','TRACE','添加了linfeng用户。','admin'),(3,'2013-07-16 13:51:33','127.0.0.1','TRACE','添加了林峰教师信息。','admin'),(4,'2013-07-16 13:53:12','127.0.0.1','TRACE','admin登录了系统。','admin'),(5,'2013-07-16 13:55:51','127.0.0.1','TRACE','admin登录了系统。','admin'),(6,'2013-07-16 14:11:31','127.0.0.1','TRACE','admin登录了系统。','admin'),(7,'2013-07-16 14:12:12','127.0.0.1','TRACE','添加了zhanghuimei用户。','admin'),(8,'2013-07-16 14:13:07','127.0.0.1','TRACE','添加了{0}教师信息。','admin'),(9,'2013-07-16 14:16:05','127.0.0.1','TRACE','添加了{0}教师信息。','admin'),(10,'2013-07-16 14:19:22','127.0.0.1','TRACE','admin登录了系统。','admin'),(11,'2013-07-16 14:20:18','127.0.0.1','TRACE','添加了{0}教师信息。','admin'),(12,'2013-07-16 14:21:53','127.0.0.1','TRACE','添加了{0}教师信息。','admin'),(13,'2013-07-16 14:28:41','127.0.0.1','TRACE','admin登录了系统。','admin'),(14,'2013-07-16 14:29:15','127.0.0.1','TRACE','添加了{0}教师信息。','admin'),(15,'2013-07-16 14:31:15','127.0.0.1','TRACE','admin登录了系统。','admin'),(16,'2013-07-16 14:31:49','127.0.0.1','TRACE','添加了{0}教师信息。','admin'),(17,'2013-07-16 15:21:29','127.0.0.1','TRACE','admin登录了系统。','admin'),(18,'2013-07-16 15:22:08','127.0.0.1','TRACE','添加了{0}教师信息。','admin'),(19,'2013-07-16 15:26:27','127.0.0.1','TRACE','添加了{0}教师信息。','admin'),(20,'2013-07-16 15:29:51','127.0.0.1','TRACE','添加了张惠妹教师信息。','admin'),(21,'2013-07-16 15:31:48','127.0.0.1','TRACE','添加了张惠妹教师信息。','admin'),(22,'2013-07-16 15:32:24','127.0.0.1','TRACE','admin登录了系统。','admin'),(23,'2013-07-16 15:49:17','127.0.0.1','TRACE','admin登录了系统。','admin'),(24,'2013-07-16 15:53:24','127.0.0.1','TRACE','admin登录了系统。','admin'),(25,'2013-07-17 10:06:08','127.0.0.1','TRACE','admin登录了系统。','admin'),(26,'2013-07-17 10:09:28','127.0.0.1','TRACE','添加了{0}请假申请。','admin'),(27,'2013-07-20 09:25:14','127.0.0.1','TRACE','admin登录了系统。','admin'),(28,'2013-07-20 09:25:24','127.0.0.1','TRACE','admin登录了系统。','admin'),(29,'2013-07-20 09:26:38','127.0.0.1','TRACE','删除了请假申请模块。','admin'),(30,'2013-07-20 09:27:36','127.0.0.1','TRACE','添加了请假申请模块。','admin'),(31,'2013-07-20 09:29:41','127.0.0.1','TRACE','添加了已提交申请模块。','admin'),(32,'2013-07-20 09:31:39','127.0.0.1','TRACE','添加了请假审批模块。','admin'),(33,'2013-07-20 09:31:47','127.0.0.1','TRACE','修改了请假审批模块的信息。','admin'),(34,'2013-07-20 09:33:11','127.0.0.1','TRACE','添加了已处理模块。','admin'),(35,'2013-07-20 09:33:20','127.0.0.1','TRACE','修改了管理员角色的信息。','admin'),(36,'2013-07-20 09:34:13','127.0.0.1','TRACE','admin登录了系统。','admin'),(37,'2013-07-20 09:34:51','127.0.0.1','TRACE','admin登录了系统。','admin'),(38,'2013-07-20 09:35:17','127.0.0.1','TRACE','修改了请假审批模块的信息。','admin'),(39,'2013-07-20 09:35:21','127.0.0.1','TRACE','admin登录了系统。','admin'),(40,'2013-07-20 09:36:07','127.0.0.1','TRACE','修改了已申请模块的信息。','admin'),(41,'2013-07-20 11:36:03','127.0.0.1','TRACE','会话超时， 该用户重新登录系统。','admin'),(42,'2013-07-20 11:36:54','127.0.0.1','TRACE','添加了申领物品模块。','admin'),(43,'2013-07-20 11:37:35','127.0.0.1','TRACE','添加了领物申请模块。','admin'),(44,'2013-07-20 11:37:44','127.0.0.1','TRACE','修改了管理员角色的信息。','admin'),(45,'2013-07-20 11:37:47','127.0.0.1','TRACE','admin登录了系统。','admin'),(46,'2013-07-20 11:39:10','127.0.0.1','TRACE','admin登录了系统。','admin'),(47,'2013-07-20 11:44:49','127.0.0.1','TRACE','admin登录了系统。','admin'),(48,'2013-07-20 11:48:42','127.0.0.1','TRACE','添加了林锋教师信息。','admin'),(49,'2013-07-20 11:49:01','127.0.0.1','TRACE','admin登录了系统。','admin'),(50,'2013-07-20 11:49:02','127.0.0.1','TRACE','admin登录了系统。','admin'),(51,'2013-07-20 11:55:00','127.0.0.1','TRACE','admin登录了系统。','admin'),(52,'2013-07-20 11:56:08','127.0.0.1','TRACE','admin登录了系统。','admin'),(53,'2013-07-20 11:57:25','127.0.0.1','TRACE','admin登录了系统。','admin'),(54,'2013-07-20 11:57:44','127.0.0.1','TRACE','admin登录了系统。','admin'),(55,'2013-07-20 12:10:36','127.0.0.1','TRACE','admin登录了系统。','admin'),(56,'2013-07-20 12:11:43','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(57,'2013-07-20 12:17:30','127.0.0.1','TRACE','admin登录了系统。','admin'),(58,'2013-07-20 12:18:08','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(59,'2013-07-20 12:21:15','127.0.0.1','TRACE','admin登录了系统。','admin'),(60,'2013-07-20 12:21:34','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(61,'2013-07-20 12:23:09','127.0.0.1','TRACE','admin登录了系统。','admin'),(62,'2013-07-20 12:25:24','127.0.0.1','TRACE','admin登录了系统。','admin'),(63,'2013-07-20 12:25:34','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(64,'2013-07-20 12:25:51','127.0.0.1','TRACE','admin登录了系统。','admin'),(65,'2013-07-20 12:25:57','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(66,'2013-07-20 12:26:26','127.0.0.1','TRACE','admin登录了系统。','admin'),(67,'2013-07-20 12:26:36','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(68,'2013-07-20 12:26:50','127.0.0.1','TRACE','admin登录了系统。','admin'),(69,'2013-07-20 13:37:33','127.0.0.1','TRACE','会话超时， 该用户重新登录系统。','admin'),(70,'2013-07-20 13:37:36','127.0.0.1','TRACE','删除了[admin]领物申请。','admin'),(71,'2013-07-20 13:38:10','127.0.0.1','TRACE','删除了[admin]领物申请。','admin'),(72,'2013-07-20 13:50:36','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(73,'2013-07-20 13:50:47','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(74,'2013-07-20 13:53:06','127.0.0.1','TRACE','添加了{0}模块。','admin'),(75,'2013-07-20 13:53:21','127.0.0.1','TRACE','添加了已申请模块。','admin'),(76,'2013-07-20 13:54:53','127.0.0.1','TRACE','添加了已办结模块。','admin'),(77,'2013-07-20 13:56:51','127.0.0.1','TRACE','添加了领物审批模块。','admin'),(78,'2013-07-20 13:57:03','127.0.0.1','TRACE','修改了管理员角色的信息。','admin'),(79,'2013-07-20 13:57:06','127.0.0.1','TRACE','admin登录了系统。','admin'),(80,'2013-07-20 13:59:47','127.0.0.1','TRACE','修改了已申请模块的信息。','admin'),(81,'2013-07-20 13:59:55','127.0.0.1','TRACE','修改了管理员角色的信息。','admin'),(82,'2013-07-20 13:59:57','127.0.0.1','TRACE','admin登录了系统。','admin'),(83,'2013-07-20 14:16:55','127.0.0.1','TRACE','admin登录了系统。','admin'),(84,'2013-07-20 14:17:24','127.0.0.1','TRACE','提交了[admin]领物申请。','admin'),(85,'2013-07-20 14:33:56','127.0.0.1','TRACE','admin登录了系统。','admin'),(86,'2013-07-20 14:34:30','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(87,'2013-07-20 14:34:36','127.0.0.1','TRACE','admin登录了系统。','admin'),(88,'2013-07-20 14:35:47','127.0.0.1','TRACE','admin登录了系统。','admin'),(89,'2013-07-20 14:36:14','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(90,'2013-07-20 14:36:55','127.0.0.1','TRACE','admin登录了系统。','admin'),(91,'2013-07-20 14:37:03','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(92,'2013-07-20 14:38:31','127.0.0.1','TRACE','admin登录了系统。','admin'),(93,'2013-07-20 14:38:48','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(94,'2013-07-20 14:38:53','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(95,'2013-07-20 14:38:59','127.0.0.1','TRACE','添加了admin领物申请。','admin'),(96,'2013-07-20 14:40:48','127.0.0.1','TRACE','zhanghuimei登录了系统。','zhanghuimei'),(97,'2013-07-20 14:40:58','127.0.0.1','TRACE','admin登录了系统。','admin'),(98,'2013-07-20 14:41:14','127.0.0.1','TRACE','向zhanghuimei用户分配了管理员的角色。','admin'),(99,'2013-07-20 14:41:36','127.0.0.1','TRACE','zhanghuimei登录了系统。','zhanghuimei'),(100,'2013-07-20 14:42:19','127.0.0.1','TRACE','同意了[admin]领物申请。','zhanghuimei'),(101,'2013-07-20 14:42:38','127.0.0.1','TRACE','admin登录了系统。','admin'),(102,'2013-07-20 14:42:49','127.0.0.1','TRACE','admin申领的物品进行了领取。','admin'),(103,'2013-07-20 14:44:51','127.0.0.1','TRACE','admin申领的物品进行了领取。','admin'),(104,'2013-07-20 14:46:03','127.0.0.1','TRACE','admin申领的物品进行了领取。','admin'),(105,'2013-07-22 09:53:04','127.0.0.1','TRACE','admin登录了系统。','admin'),(106,'2013-07-22 09:56:23','127.0.0.1','TRACE','添加了工作考勤模块。','admin'),(107,'2013-07-22 09:57:21','127.0.0.1','TRACE','添加了签到考勤模块。','admin'),(108,'2013-07-22 09:57:35','127.0.0.1','TRACE','修改了管理员角色的信息。','admin'),(109,'2013-07-22 09:57:37','127.0.0.1','TRACE','admin登录了系统。','admin'),(110,'2013-07-22 09:59:32','127.0.0.1','TRACE','admin登录了系统。','admin'),(111,'2013-07-22 10:00:11','127.0.0.1','TRACE','admin登录了系统。','admin'),(112,'2013-07-22 10:00:50','127.0.0.1','TRACE','admin登录了系统。','admin'),(113,'2013-07-24 09:10:55','127.0.0.1','TRACE','admin登录了系统。','admin'),(114,'2013-07-24 09:21:24','127.0.0.1','TRACE','admin登录了系统。','admin'),(115,'2013-07-24 09:22:38','127.0.0.1','TRACE','admin登录了系统。','admin'),(116,'2013-07-24 11:33:29','127.0.0.1','TRACE','admin登录了系统。','admin'),(117,'2013-07-24 11:34:30','127.0.0.1','TRACE','admin登录了系统。','admin'),(118,'2013-07-24 11:46:36','127.0.0.1','TRACE','admin登录了系统。','admin'),(119,'2013-07-24 11:47:17','127.0.0.1','TRACE','admin登录了系统。','admin'),(120,'2013-07-24 11:49:01','127.0.0.1','TRACE','admin登录了系统。','admin'),(121,'2013-07-24 11:49:08','127.0.0.1','TRACE','admin登录了系统。','admin'),(122,'2013-07-24 11:51:45','127.0.0.1','TRACE','admin登录了系统。','admin'),(123,'2013-07-24 11:52:17','127.0.0.1','INFO','林锋执行了签到。','admin'),(124,'2013-07-24 12:09:01','127.0.0.1','TRACE','admin登录了系统。','admin'),(125,'2013-07-24 12:09:38','127.0.0.1','INFO','林锋执行了签到。','admin'),(126,'2013-07-24 12:36:46','127.0.0.1','TRACE','admin登录了系统。','admin'),(127,'2013-07-24 12:38:11','127.0.0.1','TRACE','admin登录了系统。','admin'),(128,'2013-07-24 12:38:40','127.0.0.1','TRACE','admin登录了系统。','admin'),(129,'2013-07-24 13:27:22','127.0.0.1','TRACE','会话超时， 该用户重新登录系统。','admin'),(130,'2013-07-24 13:28:24','127.0.0.1','TRACE','添加了离校考勤模块。','admin'),(131,'2013-07-24 13:28:28','127.0.0.1','TRACE','修改了离校考勤模块的信息。','admin'),(132,'2013-07-24 13:28:36','127.0.0.1','TRACE','修改了管理员角色的信息。','admin'),(133,'2013-07-24 13:29:37','127.0.0.1','TRACE','admin登录了系统。','admin'),(134,'2013-07-24 14:22:06','127.0.0.1','TRACE','admin登录了系统。','admin'),(135,'2013-07-24 14:23:31','127.0.0.1','TRACE','admin登录了系统。','admin'),(136,'2013-07-24 14:24:12','127.0.0.1','TRACE','admin登录了系统。','admin'),(137,'2013-07-24 14:24:26','127.0.0.1','INFO','林锋执行了离校。','admin'),(138,'2013-07-24 14:29:02','127.0.0.1','INFO','林锋执行了返校。','admin'),(139,'2013-07-24 15:15:20','127.0.0.1','TRACE','会话超时， 该用户重新登录系统。','admin'),(140,'2013-07-24 15:19:25','127.0.0.1','TRACE','添加了签到考勤查询模块。','admin'),(141,'2013-07-24 15:20:18','127.0.0.1','TRACE','添加了{0}模块。','admin'),(142,'2013-07-24 15:20:30','127.0.0.1','TRACE','删除了签到考勤查询模块。','admin'),(143,'2013-07-24 15:20:46','127.0.0.1','TRACE','添加了签到考勤查询模块。','admin'),(144,'2013-07-24 15:22:11','127.0.0.1','TRACE','admin登录了系统。','admin'),(145,'2013-07-24 15:23:30','127.0.0.1','TRACE','admin登录了系统。','admin'),(146,'2013-07-24 15:24:37','127.0.0.1','TRACE','admin登录了系统。','admin'),(147,'2013-07-24 15:48:35','127.0.0.1','TRACE','admin登录了系统。','admin'),(148,'2013-07-24 15:50:32','127.0.0.1','TRACE','admin登录了系统。','admin'),(149,'2013-07-24 15:51:08','127.0.0.1','TRACE','admin登录了系统。','admin'),(150,'2013-07-24 15:52:13','127.0.0.1','TRACE','admin登录了系统。','admin'),(151,'2013-07-24 15:54:50','127.0.0.1','TRACE','admin登录了系统。','admin'),(152,'2013-07-24 16:10:35','127.0.0.1','TRACE','admin登录了系统。','admin'),(153,'2013-07-24 16:11:17','127.0.0.1','TRACE','admin登录了系统。','admin'),(154,'2013-07-24 16:16:00','127.0.0.1','TRACE','admin登录了系统。','admin'),(155,'2013-07-24 16:18:57','127.0.0.1','TRACE','admin登录了系统。','admin'),(156,'2013-07-24 16:20:16','127.0.0.1','TRACE','admin登录了系统。','admin'),(157,'2013-07-24 16:21:43','127.0.0.1','TRACE','admin登录了系统。','admin'),(158,'2013-07-24 16:26:41','127.0.0.1','TRACE','admin登录了系统。','admin'),(159,'2013-07-24 16:39:51','127.0.0.1','TRACE','admin登录了系统。','admin'),(160,'2013-07-24 16:52:24','127.0.0.1','TRACE','admin登录了系统。','admin'),(161,'2013-07-24 17:01:06','127.0.0.1','TRACE','admin登录了系统。','admin'),(162,'2013-07-24 17:43:46','127.0.0.1','TRACE','admin登录了系统。','admin'),(163,'2013-07-24 17:50:39','127.0.0.1','TRACE','admin登录了系统。','admin'),(164,'2013-07-24 20:53:37','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(165,'2013-07-24 20:54:03','0:0:0:0:0:0:0:1','INFO','林锋执行了签到。','admin'),(166,'2013-07-24 21:07:47','0:0:0:0:0:0:0:1','TRACE','添加了离校考勤查询模块。','admin'),(167,'2013-07-24 21:08:01','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(168,'2013-07-24 21:08:06','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(169,'2013-07-24 21:09:04','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(170,'2013-07-24 21:13:01','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(171,'2013-07-24 21:19:15','0:0:0:0:0:0:0:1','TRACE','删除了简单任务实例模块。','admin'),(172,'2013-07-24 21:19:20','0:0:0:0:0:0:0:1','TRACE','删除了开发实例模块。','admin'),(173,'2013-07-24 21:19:26','0:0:0:0:0:0:0:1','TRACE','删除了新模块26模块。','admin'),(174,'2013-07-24 21:20:42','0:0:0:0:0:0:0:1','TRACE','删除了模块测试2模块。','admin'),(175,'2013-07-24 21:20:49','0:0:0:0:0:0:0:1','TRACE','删除了多级模块测试模块。','admin'),(176,'2013-07-24 21:21:05','0:0:0:0:0:0:0:1','TRACE','删除了自定义权限测试模块。','admin'),(177,'2013-07-24 21:21:13','0:0:0:0:0:0:0:1','TRACE','删除了测试模块模块。','admin'),(178,'2013-07-24 21:21:15','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(179,'2013-07-24 21:32:27','0:0:0:0:0:0:0:1','TRACE','添加了后勤教务管理模块。','admin'),(180,'2013-07-24 21:34:00','0:0:0:0:0:0:0:1','TRACE','添加了领物申请模块。','admin'),(181,'2013-07-24 21:35:12','0:0:0:0:0:0:0:1','TRACE','添加了实物领取模块。','admin'),(182,'2013-07-24 21:36:53','0:0:0:0:0:0:0:1','TRACE','添加了校园采购模块。','admin'),(183,'2013-07-24 21:52:36','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(184,'2013-07-24 21:52:40','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(185,'2013-07-24 21:52:40','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(186,'2013-07-24 21:52:40','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(187,'2013-07-24 21:54:24','0:0:0:0:0:0:0:1','TRACE','添加了{0}模块。','admin'),(188,'2013-07-25 22:32:01','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(189,'2013-07-25 23:06:56','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(190,'2013-07-25 23:10:56','0:0:0:0:0:0:0:1','INFO','林锋执行了签到。','admin'),(191,'2013-07-25 23:11:14','0:0:0:0:0:0:0:1','INFO','林锋执行了离校。','admin'),(192,'2013-07-25 23:17:45','0:0:0:0:0:0:0:1','INFO','林锋执行了签到。','admin'),(193,'2013-07-25 23:18:11','0:0:0:0:0:0:0:1','INFO','林锋执行了签到。','admin'),(194,'2013-07-27 15:28:04','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(195,'2013-07-27 15:55:18','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(196,'2013-07-27 15:59:28','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(197,'2013-07-27 16:00:18','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(198,'2013-07-27 16:00:45','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(199,'2013-07-27 16:04:23','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(200,'2013-07-27 16:04:27','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(201,'2013-07-27 16:04:31','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(202,'2013-07-27 16:08:59','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(203,'2013-07-27 16:10:19','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(204,'2013-07-27 16:11:40','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(205,'2013-07-27 16:14:52','0:0:0:0:0:0:0:1','TRACE','admin申领的物品进行了领取。','admin'),(206,'2013-07-27 16:17:00','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(207,'2013-07-27 16:17:37','0:0:0:0:0:0:0:1','TRACE','admin申领的物品进行了领取。','admin'),(208,'2013-07-27 16:35:54','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(209,'2013-07-27 16:36:56','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(210,'2013-07-27 16:40:55','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(211,'2013-07-27 16:47:24','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(212,'2013-07-27 16:48:19','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(213,'2013-07-27 16:49:32','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(214,'2013-07-27 16:50:07','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(215,'2013-07-27 16:50:56','0:0:0:0:0:0:0:1','TRACE','admin申领的物品进行了领取。','admin'),(216,'2013-07-27 16:53:33','0:0:0:0:0:0:0:1','TRACE','删除了领物申请模块。','admin'),(217,'2013-07-27 16:53:36','0:0:0:0:0:0:0:1','TRACE','删除了实物领取模块。','admin'),(218,'2013-07-27 17:14:46','0:0:0:0:0:0:0:1','TRACE','添加了采购申请模块。','admin'),(219,'2013-07-27 17:14:55','0:0:0:0:0:0:0:1','TRACE','删除了采购申请模块。','admin'),(220,'2013-07-27 17:15:13','0:0:0:0:0:0:0:1','TRACE','添加了采购申请模块。','admin'),(221,'2013-07-27 18:34:24','0:0:0:0:0:0:0:1','TRACE','会话超时， 该用户重新登录系统。','admin'),(222,'2013-07-27 18:37:56','0:0:0:0:0:0:0:1','TRACE','修改了采购申请模块的信息。','admin'),(223,'2013-07-27 18:40:45','0:0:0:0:0:0:0:1','TRACE','添加了草稿箱模块。','admin'),(224,'2013-07-27 18:40:57','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(225,'2013-07-27 18:41:01','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(226,'2013-07-27 18:43:19','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(227,'2013-07-27 18:44:24','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(228,'2013-07-28 09:10:58','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(229,'2013-07-28 09:12:59','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(230,'2013-07-28 09:28:25','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(231,'2013-07-28 10:13:21','0:0:0:0:0:0:0:1','TRACE','添加了{0}采购申请。','admin'),(232,'2013-07-28 10:22:14','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(233,'2013-07-28 10:29:19','0:0:0:0:0:0:0:1','TRACE','添加了admin采购申请。','admin'),(234,'2013-07-28 10:36:27','0:0:0:0:0:0:0:1','TRACE','添加了admin采购申请。','admin'),(235,'2013-07-28 10:39:09','0:0:0:0:0:0:0:1','TRACE','添加了林锋采购申请。','admin'),(236,'2013-07-28 10:41:26','0:0:0:0:0:0:0:1','TRACE','提交了[林锋]采购申请。','admin'),(237,'2013-07-28 10:42:03','0:0:0:0:0:0:0:1','TRACE','添加了林锋采购申请。','admin'),(238,'2013-07-28 10:45:17','0:0:0:0:0:0:0:1','TRACE','添加了林锋采购申请。','admin'),(239,'2013-07-28 10:49:49','0:0:0:0:0:0:0:1','TRACE','添加了已申请模块。','admin'),(240,'2013-07-28 10:50:02','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(241,'2013-07-28 10:50:04','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(242,'2013-07-28 10:51:40','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(243,'2013-07-28 10:52:52','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(244,'2013-07-28 10:55:52','0:0:0:0:0:0:0:1','TRACE','添加了采购审批模块。','admin'),(245,'2013-07-28 10:57:45','0:0:0:0:0:0:0:1','TRACE','添加了待审批模块。','admin'),(246,'2013-07-28 10:58:54','0:0:0:0:0:0:0:1','TRACE','添加了已审批模块。','admin'),(247,'2013-07-28 10:59:02','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(248,'2013-07-28 10:59:06','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(249,'2013-07-28 14:12:13','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(250,'2013-07-28 14:13:32','0:0:0:0:0:0:0:1','TRACE','修改了采购申请审批模块的信息。','admin'),(251,'2013-07-28 14:14:04','0:0:0:0:0:0:0:1','TRACE','添加了实物采购申请模块。','admin'),(252,'2013-07-28 14:14:32','0:0:0:0:0:0:0:1','TRACE','添加了实物采购审核模块。','admin'),(253,'2013-07-28 14:17:25','0:0:0:0:0:0:0:1','TRACE','添加了待采购清单模块。','admin'),(254,'2013-07-28 14:18:38','0:0:0:0:0:0:0:1','TRACE','添加了草稿箱模块。','admin'),(255,'2013-07-28 14:20:09','0:0:0:0:0:0:0:1','TRACE','添加了已提交模块。','admin'),(256,'2013-07-28 14:22:14','0:0:0:0:0:0:0:1','TRACE','添加了待审批模块。','admin'),(257,'2013-07-28 14:26:47','0:0:0:0:0:0:0:1','TRACE','添加了已审批模块。','admin'),(258,'2013-07-28 14:26:57','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(259,'2013-07-28 14:27:00','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(260,'2013-07-28 14:28:47','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(261,'2013-07-28 14:30:35','0:0:0:0:0:0:0:1','TRACE','提交了[林锋]采购申请。','admin'),(262,'2013-07-28 14:30:54','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(263,'2013-07-28 14:32:58','0:0:0:0:0:0:0:1','TRACE','zhanghuimei登录了系统。','zhanghuimei'),(264,'2013-07-28 14:37:00','0:0:0:0:0:0:0:1','TRACE','同意了[林锋, 林锋]采购申请。','zhanghuimei'),(265,'2013-07-28 14:37:24','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(266,'2013-07-28 14:54:13','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(267,'2013-07-28 15:55:28','0:0:0:0:0:0:0:1','TRACE','会话超时， 该用户重新登录系统。','admin'),(268,'2013-07-28 15:59:48','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(269,'2013-07-28 16:04:11','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(270,'2013-07-28 16:16:17','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(271,'2013-07-28 16:40:29','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(272,'2013-07-28 16:45:01','0:0:0:0:0:0:0:1','TRACE','添加了{0}采购申请。','admin'),(273,'2013-07-28 16:46:12','0:0:0:0:0:0:0:1','TRACE','添加了{0}采购申请。','admin'),(274,'2013-07-28 16:47:28','0:0:0:0:0:0:0:1','TRACE','添加了林锋采购申请。','admin'),(275,'2013-07-28 17:20:51','0:0:0:0:0:0:0:1','TRACE','添加了林锋采购申请。','admin'),(276,'2013-07-28 17:21:14','0:0:0:0:0:0:0:1','TRACE','添加了林锋采购申请。','admin'),(277,'2013-07-28 17:21:25','0:0:0:0:0:0:0:1','TRACE','添加了林锋采购申请。','admin'),(278,'2013-07-28 17:23:42','0:0:0:0:0:0:0:1','TRACE','删除了[林锋]采购申请。','admin'),(279,'2013-07-28 17:24:23','0:0:0:0:0:0:0:1','TRACE','添加了林锋采购申请。','admin'),(280,'2013-07-28 17:24:42','0:0:0:0:0:0:0:1','TRACE','提交了[林锋]采购申请。','admin'),(281,'2013-07-29 20:21:09','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(282,'2013-07-29 20:23:26','0:0:0:0:0:0:0:1','TRACE','添加了admin请假申请。','admin'),(283,'2013-07-29 20:24:00','0:0:0:0:0:0:0:1','TRACE','提交了[admin]请假申请。','admin'),(284,'2013-07-29 20:26:11','0:0:0:0:0:0:0:1','TRACE','zhanghuimei登录了系统。','zhanghuimei'),(285,'2013-07-29 20:28:22','0:0:0:0:0:0:0:1','TRACE','同意了[admin]请假申请。','zhanghuimei'),(286,'2013-07-29 20:30:18','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(287,'2013-07-29 20:33:31','0:0:0:0:0:0:0:1','TRACE','添加了教学辅助中心模块。','admin'),(288,'2013-07-29 20:40:41','0:0:0:0:0:0:0:1','TRACE','添加了林锋领物申请。','admin'),(289,'2013-07-29 20:41:48','0:0:0:0:0:0:0:1','TRACE','zhanghuimei登录了系统。','zhanghuimei'),(290,'2013-07-29 20:43:44','0:0:0:0:0:0:0:1','TRACE','zhanghuimei登录了系统。','zhanghuimei'),(291,'2013-07-29 20:47:13','0:0:0:0:0:0:0:1','INFO','林锋执行了离校。','zhanghuimei'),(292,'2013-07-29 20:47:30','0:0:0:0:0:0:0:1','INFO','林锋执行了返校。','zhanghuimei'),(293,'2013-07-29 21:02:44','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','zhanghuimei'),(294,'2013-07-29 21:03:50','0:0:0:0:0:0:0:1','TRACE','向admin用户分配了财务人员的角色。','zhanghuimei'),(295,'2013-07-29 21:03:57','0:0:0:0:0:0:0:1','TRACE','撤销了admin用户的财务人员角色。','zhanghuimei'),(296,'2013-07-29 21:04:07','0:0:0:0:0:0:0:1','TRACE','zhanghuimei登录了系统。','zhanghuimei'),(297,'2013-07-29 21:07:18','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(298,'2013-07-29 21:09:38','0:0:0:0:0:0:0:1','TRACE','添加了教案管理模块。','admin'),(299,'2013-07-29 21:10:09','0:0:0:0:0:0:0:1','TRACE','删除了教案管理模块。','admin'),(300,'2013-07-29 21:10:38','0:0:0:0:0:0:0:1','TRACE','添加了教案管理模块。','admin'),(301,'2013-07-29 21:10:46','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(302,'2013-07-29 21:10:49','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(303,'2013-07-29 21:26:29','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(304,'2013-07-29 21:29:48','0:0:0:0:0:0:0:1','INFO','添加了氧气组成文件。','admin'),(305,'2013-07-29 21:30:27','0:0:0:0:0:0:0:1','TRACE','提交了[氧气组成]课件。','admin'),(306,'2013-07-29 22:05:55','0:0:0:0:0:0:0:1','TRACE','会话超时， 该用户重新登录系统。','admin'),(307,'2013-07-29 22:06:34','0:0:0:0:0:0:0:1','TRACE','添加了教案管理模块。','admin'),(308,'2013-07-29 22:06:55','0:0:0:0:0:0:0:1','TRACE','删除了教案管理模块。','admin'),(309,'2013-07-29 22:07:20','0:0:0:0:0:0:0:1','TRACE','添加了上传教案模块。','admin'),(310,'2013-07-29 22:07:28','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(311,'2013-07-29 22:07:32','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(312,'2013-07-29 22:11:39','0:0:0:0:0:0:0:1','INFO','添加了氧气的组成文件。','admin'),(313,'2013-07-29 22:12:00','0:0:0:0:0:0:0:1','TRACE','提交了[氧气的组成]课件。','admin'),(314,'2013-07-29 22:15:59','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(315,'2013-07-29 22:34:03','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(316,'2013-07-29 22:35:25','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(317,'2013-07-29 22:37:23','0:0:0:0:0:0:0:1','INFO','添加了{0}文件。','admin'),(318,'2013-07-29 22:37:42','0:0:0:0:0:0:0:1','TRACE','修改了{0}年级。','admin'),(319,'2013-07-29 22:39:02','0:0:0:0:0:0:0:1','TRACE','删除了[氧气的组成]课件。','admin'),(320,'2013-07-29 22:40:51','0:0:0:0:0:0:0:1','INFO','添加了样子的的文件。','admin'),(321,'2013-07-29 22:59:39','0:0:0:0:0:0:0:1','TRACE','添加了教案评定模块。','admin'),(322,'2013-07-29 22:59:47','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(323,'2013-07-29 22:59:49','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(324,'2013-07-29 23:00:39','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(325,'2013-07-29 23:00:56','0:0:0:0:0:0:0:1','TRACE','提交了[样子的的]课件。','admin'),(326,'2013-07-29 23:06:21','0:0:0:0:0:0:0:1','TRACE','评定了样子的的课件。','admin'),(327,'2013-07-29 23:08:16','0:0:0:0:0:0:0:1','TRACE','评定了样子的的课件。','admin'),(328,'2013-07-30 20:28:26','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(329,'2013-07-30 21:22:46','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(330,'2013-07-30 21:24:27','0:0:0:0:0:0:0:1','TRACE','添加了多媒体教学模块。','admin'),(331,'2013-07-30 21:24:34','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(332,'2013-07-30 21:24:39','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(333,'2013-07-30 21:24:39','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(334,'2013-07-30 21:40:33','0:0:0:0:0:0:0:1','INFO','添加了12312文件。','admin'),(335,'2013-07-30 22:05:09','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(336,'2013-07-30 22:11:44','0:0:0:0:0:0:0:1','TRACE','修改了12312多媒体。','admin'),(337,'2013-07-30 22:13:02','0:0:0:0:0:0:0:1','TRACE','修改了12312多媒体。','admin'),(338,'2013-07-30 22:15:27','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(339,'2013-07-30 22:16:03','0:0:0:0:0:0:0:1','TRACE','修改了12312多媒体。','admin'),(340,'2013-07-30 22:17:27','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(341,'2013-07-30 22:17:39','0:0:0:0:0:0:0:1','TRACE','删除了[12312]课件。','admin'),(342,'2013-07-30 22:19:27','0:0:0:0:0:0:0:1','TRACE','删除了[12312]课件。','admin'),(343,'2013-07-30 22:20:52','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(344,'2013-07-30 22:22:22','0:0:0:0:0:0:0:1','TRACE','删除了[12312]课件。','admin'),(345,'2013-07-30 22:26:07','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(346,'2013-07-30 22:26:42','0:0:0:0:0:0:0:1','INFO','添加了213213文件。','admin'),(347,'2013-07-31 21:26:34','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(348,'2013-07-31 21:29:48','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(349,'2013-08-05 22:22:11','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(350,'2013-08-05 22:27:37','0:0:0:0:0:0:0:1','TRACE','添加了成绩统计分析模块。','admin'),(351,'2013-08-05 22:27:46','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(352,'2013-08-05 22:29:18','0:0:0:0:0:0:0:1','TRACE','添加了成绩录入模块。','admin'),(353,'2013-08-05 22:29:30','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(354,'2013-08-05 22:29:37','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(355,'2013-08-11 16:02:53','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(356,'2013-08-11 16:07:47','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(357,'2013-08-11 16:17:24','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(358,'2013-08-11 16:18:52','0:0:0:0:0:0:0:1','TRACE','添加了张大山分数。','admin'),(359,'2013-08-11 16:27:21','0:0:0:0:0:0:0:1','TRACE','添加了111分数。','admin'),(360,'2013-08-11 16:37:37','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(361,'2013-08-11 16:38:29','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(362,'2013-08-11 16:39:05','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(363,'2013-08-11 16:39:42','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(364,'2013-08-11 16:40:28','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(365,'2013-08-11 16:40:28','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(366,'2013-08-11 16:41:03','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(367,'2013-08-11 16:42:50','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(368,'2013-08-11 16:43:31','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(369,'2013-08-11 16:44:19','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(370,'2013-08-11 16:51:19','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(371,'2013-08-11 16:51:19','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(372,'2013-08-11 16:51:19','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(373,'2013-08-11 16:51:56','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(374,'2013-08-11 16:53:55','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(375,'2013-08-11 16:57:04','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(376,'2013-08-11 17:00:16','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(377,'2013-08-11 17:01:30','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(378,'2013-08-11 17:02:48','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(379,'2013-08-11 17:03:16','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(380,'2013-08-11 17:10:15','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(381,'2013-08-11 17:28:05','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(382,'2013-08-11 17:31:42','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(383,'2013-08-11 17:34:11','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(384,'2013-08-11 17:49:54','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(385,'2013-08-11 18:03:53','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(386,'2013-08-11 18:04:32','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(387,'2013-08-11 18:06:11','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(388,'2013-08-11 18:07:28','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(389,'2013-08-11 18:08:49','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(390,'2013-08-11 18:09:06','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(391,'2013-08-11 18:12:33','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(392,'2013-08-11 18:12:40','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(393,'2013-08-11 18:12:40','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(394,'2013-08-11 18:17:52','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(395,'2013-08-11 18:18:19','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(396,'2013-08-11 18:19:23','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(397,'2013-08-11 18:21:30','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(398,'2013-08-11 18:25:59','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(399,'2013-08-11 18:26:39','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(400,'2013-08-11 18:27:26','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(401,'2013-08-11 18:32:26','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(402,'2013-08-11 18:33:59','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(403,'2013-08-11 18:34:24','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(404,'2013-08-11 18:34:46','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(405,'2013-08-11 18:35:09','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(406,'2013-08-11 18:40:35','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(407,'2013-08-11 18:40:35','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(408,'2013-08-11 18:40:57','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(409,'2013-08-11 18:42:01','0:0:0:0:0:0:0:1','TRACE','添加了张三分数。','admin'),(410,'2013-08-11 18:42:10','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(411,'2013-08-11 18:42:22','0:0:0:0:0:0:0:1','TRACE','删除了[高一年级]分数。','admin'),(412,'2013-08-11 18:42:44','0:0:0:0:0:0:0:1','TRACE','添加了林锋分数。','admin'),(413,'2013-08-11 18:42:49','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(414,'2013-08-11 18:43:04','0:0:0:0:0:0:0:1','TRACE','添加了张三分数。','admin'),(415,'2013-08-11 18:43:35','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(416,'2013-08-11 18:43:53','0:0:0:0:0:0:0:1','TRACE','添加了林锋分数。','admin'),(417,'2013-08-11 18:44:02','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(418,'2013-08-11 18:44:32','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(419,'2013-08-11 18:44:44','0:0:0:0:0:0:0:1','TRACE','添加了林锋分数。','admin'),(420,'2013-08-12 21:01:06','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(421,'2013-08-12 21:01:25','0:0:0:0:0:0:0:1','TRACE','删除了[高一年级]分数。','admin'),(422,'2013-08-12 21:06:16','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(423,'2013-08-17 23:08:10','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(424,'2013-08-17 23:20:38','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(425,'2013-08-17 23:21:11','0:0:0:0:0:0:0:1','TRACE','添加了demo模块。','admin'),(426,'2013-08-17 23:21:25','0:0:0:0:0:0:0:1','TRACE','修改了管理员角色的信息。','admin'),(427,'2013-08-17 23:21:30','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin'),(428,'2013-08-17 23:22:28','0:0:0:0:0:0:0:1','TRACE','修改了demo模块的信息。','admin'),(429,'2013-08-17 23:22:31','0:0:0:0:0:0:0:1','TRACE','admin登录了系统。','admin');
/*!40000 ALTER TABLE `security_log_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_module`
--

DROP TABLE IF EXISTS `security_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_module` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(255) default NULL,
  `name` varchar(32) NOT NULL,
  `priority` int(11) NOT NULL,
  `sn` varchar(32) NOT NULL,
  `url` varchar(255) NOT NULL,
  `parent_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK6510844BB3395F9` (`parent_id`),
  CONSTRAINT `FK6510844BB3395F9` FOREIGN KEY (`parent_id`) REFERENCES `security_module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_module`
--

LOCK TABLES `security_module` WRITE;
/*!40000 ALTER TABLE `security_module` DISABLE KEYS */;
INSERT INTO `security_module` VALUES (1,'所有模块的根节点，不能删除。','根模块',1,'Root','#',NULL),(2,'安全管理:包含权限管理、模块管理等。','系统管理',99,'Security','#',1),(3,'','用户管理',99,'User','/management/security/user/list',2),(4,'','角色管理',99,'Role','/management/security/role/list',2),(5,'','模块管理',99,'Module','/management/security/module/tree_list',2),(18,'','组织管理',99,'Organization','/management/security/organization/tree_list',2),(24,'','缓存管理',99,'CacheManage','/management/security/cacheManage/index',2),(59,'','日志管理',99,'logEntity','/management/security/logEntity/list',2),(63,'','职务管理',99,'Position','/management/eduoa/position/list',2),(64,'','年级管理',99,'Grade','/management/eduoa/grade/list',2),(65,'','班级管理',99,'Class','/management/eduoa/class/tree_list',2),(66,'','人员组成分工',99,'Teacher','#',1),(67,'','组织结构',99,'Structure','/management/eduoa/structure/create',66),(68,'','组织机构查看',99,'StructureView','/management/eduoa/structure/view',66),(69,'','科目管理',99,'Subject','/management/eduoa/subject/list',2),(70,'','职称证书',99,'CertificateType','/management/eduoa/certificatetype/list',2),(72,'','教师信息管理',99,'TeacherInfo','/management/eduoa/teacher/list',66),(74,'','学生信息管理',99,'StudentInfo','/management/eduoa/student/list',66),(75,'','日常办公助手',99,'Work','#',1),(76,'','请销假管理',99,'Leave','#',75),(78,'','请假申请',99,'LeavePermit','/management/eduoa/leavepermit/listDraft',76),(79,'','已申请',99,'listApproval','/management/eduoa/leavepermit/listApproval',76),(80,'','请假审批',99,'Permit','/management/eduoa/leavepermit/list',76),(81,'','已处理',99,'listFinish','/management/eduoa/leavepermit/listFinish',76),(82,'','申领物品',99,'Goods','#',75),(83,'','领物申请',99,'GoodsApply','/management/eduoa/goods/listDraft',82),(84,'','已申请',99,'GoodsApproval','/management/eduoa/goods/listApproval',82),(85,'','已办结',99,'GoodsFinish','/management/eduoa/goods/listFinish',82),(86,'','领物审批',99,'GoodsPermit','/management/eduoa/goods/list',82),(87,'','工作考勤',99,'WorkAttendance','#',75),(88,'','签到考勤',99,'Registration','/management/eduoa/attendance/list',87),(89,'','离校考勤',99,'LeaveAttendance','/management/eduoa/attendance/leave',87),(91,'','签到考勤查询',99,'listRegistration','/management/eduoa/attendance/listRegistration',87),(92,'','离校考勤查询',99,'listLeave','/management/eduoa/attendance/listLeave',87),(93,'','后勤教务管理',99,'Logistics','#',1),(96,'','校园采购',99,'Purch','#',93),(98,'','采购申请',99,'PurchaseApply','#',96),(99,'','草稿箱',99,'PurchaseDraft','/management/eduoa/purchase/listDraft',98),(100,'','已申请',99,'PurchaseApproval','/management/eduoa/purchase/listApproval',98),(101,'','采购申请审批',99,'PurchaseRatify','#',96),(102,'','待审批',99,'PurchasePermit','/management/eduoa/purchase/list',101),(103,'','已审批',99,'PurchaseFinish','/management/eduoa/purchase/listFinish',101),(104,'','实物采购申请',99,'Conduct','#',96),(105,'','实物采购审核',99,'ConductApply','#',96),(106,'','待采购清单',99,'PurchaseApplyFinish','/management/eduoa/conduct/applyFinish',104),(107,'','草稿箱',99,'PurchaseConductDraft','/management/eduoa/conduct/listDraft',104),(108,'','已提交',99,'PurchaseConductApproval','/management/eduoa/conduct/listApproval',104),(109,'','待审批',99,'PurchaseConductPermit','/management/eduoa/conduct/list',105),(110,'','已审批',99,'PurchaseConductList','/management/eduoa/conduct/listFinish',105),(111,'','教学辅助中心',99,'Teaching','#',1),(114,'','教案管理',99,'Teach','#',111),(115,'','上传教案',99,'TeachingPlan','/management/eduoa/teachingplan/list',114),(116,'','教案评定',99,'TeachingPlanRatings','/management/eduoa/teachingplan/listRatings',114),(117,'','多媒体教学',99,'MultiMedia','/management/eduoa/multimedia/list',111),(118,'','成绩统计分析',99,'Statistics','#',1),(119,'','成绩录入',99,'Score','/management/eduoa/score/list',118),(120,'','demo',99,'Demo','/data/demo.jsp',118);
/*!40000 ALTER TABLE `security_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_organization`
--

DROP TABLE IF EXISTS `security_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_organization` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(255) default NULL,
  `name` varchar(64) NOT NULL,
  `parent_id` bigint(20) default NULL,
  `org_order` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1DBDA7D2FCC01B00` (`parent_id`),
  CONSTRAINT `FK1DBDA7D2FCC01B00` FOREIGN KEY (`parent_id`) REFERENCES `security_organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_organization`
--

LOCK TABLES `security_organization` WRITE;
/*!40000 ALTER TABLE `security_organization` DISABLE KEYS */;
INSERT INTO `security_organization` VALUES (1,'不能删除。','根组织',NULL,NULL),(24,'','香河县第一中学',1,NULL),(25,'','高一年级',24,NULL),(26,'','高二年级',24,NULL),(27,'','高三年级',24,NULL),(28,'','教务处',24,NULL),(29,'','政教处',24,NULL),(30,'','办公室',24,NULL),(31,'','三联办',24,NULL),(32,'','后勤',24,NULL),(33,'','财务室',24,NULL),(34,'','实验室',24,NULL),(35,'','图书馆',24,NULL),(36,'','团委',24,NULL),(37,'','资料室',24,NULL),(38,'','语文组',25,NULL),(39,'','化学组',25,NULL),(40,'','英语组',25,NULL),(41,'','物理组',25,NULL),(42,'','政治组',25,NULL),(43,'','生物组',25,NULL),(44,'','语文组',26,NULL),(45,'','数学组',26,NULL),(46,'','英语组',26,NULL),(47,'','物理组',26,NULL),(48,'','化学组',26,NULL),(49,'','生物组',26,NULL);
/*!40000 ALTER TABLE `security_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_organization_role`
--

DROP TABLE IF EXISTS `security_organization_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_organization_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `priority` int(11) NOT NULL,
  `organization_id` bigint(20) default NULL,
  `role_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK557CA4C3D069FDD7` (`organization_id`),
  KEY `FK557CA4C3C592DFF7` (`role_id`),
  CONSTRAINT `FK557CA4C3C592DFF7` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`id`),
  CONSTRAINT `FK557CA4C3D069FDD7` FOREIGN KEY (`organization_id`) REFERENCES `security_organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_organization_role`
--

LOCK TABLES `security_organization_role` WRITE;
/*!40000 ALTER TABLE `security_organization_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `security_organization_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_permission`
--

DROP TABLE IF EXISTS `security_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_permission` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(255) default NULL,
  `name` varchar(32) NOT NULL,
  `short_name` varchar(16) NOT NULL,
  `module_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKBA7A9C2E334A08F7` (`module_id`),
  CONSTRAINT `FKBA7A9C2E334A08F7` FOREIGN KEY (`module_id`) REFERENCES `security_module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=327 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_permission`
--

LOCK TABLES `security_permission` WRITE;
/*!40000 ALTER TABLE `security_permission` DISABLE KEYS */;
INSERT INTO `security_permission` VALUES (25,'','增','save',2),(26,'','删','delete',2),(27,'','查','view',2),(28,'','改','edit',2),(37,'','增','save',3),(38,'','删','delete',3),(39,'','查','view',3),(40,'','改','edit',3),(45,'','增','save',4),(46,'','删','delete',4),(47,'','查','view',4),(48,'','改','edit',4),(53,'','增','save',5),(54,'','删','delete',5),(55,'','查','view',5),(56,'','改','edit',5),(57,'','增','save',18),(58,'','删','delete',18),(59,'','查','view',18),(60,'','改','edit',18),(61,'','查','view',24),(62,'','改','edit',24),(76,'重置密码、更新状态','重置','reset',3),(77,'分配、撤销角色','授权','assign',3),(78,'分配、撤销角色','授权','assign',18),(135,NULL,'增','save',59),(136,NULL,'删','delete',59),(137,NULL,'查','view',59),(138,NULL,'改','edit',59),(171,NULL,'增','save',63),(172,NULL,'删','delete',63),(173,NULL,'查','view',63),(174,NULL,'改','edit',63),(175,NULL,'增','save',64),(176,NULL,'删','delete',64),(177,NULL,'查','view',64),(178,NULL,'改','edit',64),(179,NULL,'增','save',65),(180,NULL,'删','delete',65),(181,NULL,'查','view',65),(182,NULL,'改','edit',65),(183,NULL,'增','save',66),(184,NULL,'删','delete',66),(185,NULL,'查','view',66),(186,NULL,'改','edit',66),(187,NULL,'增','save',67),(188,NULL,'查','view',67),(189,NULL,'增','save',68),(190,NULL,'删','delete',68),(191,NULL,'查','view',68),(192,NULL,'改','edit',68),(193,NULL,'增','save',69),(194,NULL,'删','delete',69),(195,NULL,'查','view',69),(196,NULL,'改','edit',69),(197,NULL,'增','save',70),(198,NULL,'删','delete',70),(199,NULL,'查','view',70),(200,NULL,'改','edit',70),(205,NULL,'增','save',72),(206,NULL,'删','delete',72),(207,NULL,'查','view',72),(208,NULL,'改','edit',72),(209,'','任课班级','teachClass',72),(210,'','带领班级','guideClass',72),(215,NULL,'增','save',74),(216,NULL,'删','delete',74),(217,NULL,'查','view',74),(218,NULL,'改','edit',74),(219,NULL,'增','save',75),(220,NULL,'删','delete',75),(221,NULL,'查','view',75),(222,NULL,'改','edit',75),(223,NULL,'增','save',76),(224,NULL,'删','delete',76),(225,NULL,'查','view',76),(226,NULL,'改','edit',76),(231,NULL,'增','save',78),(232,NULL,'删','delete',78),(233,NULL,'查','view',78),(234,NULL,'改','edit',78),(235,NULL,'查','view',79),(236,'','销假','sick',79),(237,NULL,'查','view',80),(238,'','通过','pass',80),(239,'','驳回','reject',80),(240,NULL,'查','view',81),(241,NULL,'增','save',82),(242,NULL,'删','delete',82),(243,NULL,'查','view',82),(244,NULL,'改','edit',82),(245,NULL,'增','save',83),(246,NULL,'删','delete',83),(247,NULL,'查','view',83),(248,NULL,'改','edit',83),(249,NULL,'查','view',84),(250,NULL,'查','view',85),(251,NULL,'查','view',86),(252,'','通过','pass',86),(253,'','驳回','reject',86),(254,'','领取','sick',84),(255,NULL,'增','save',87),(256,NULL,'删','delete',87),(257,NULL,'查','view',87),(258,NULL,'改','edit',87),(259,NULL,'增','save',88),(260,NULL,'查','view',88),(261,NULL,'查','view',89),(262,'','离校','leaveStart',89),(263,'','返校','leaveEnd',89),(265,NULL,'查','view',91),(266,NULL,'查','view',92),(267,NULL,'查','view',93),(270,NULL,'查','view',96),(275,NULL,'增','save',98),(276,NULL,'删','delete',98),(277,NULL,'查','view',98),(278,NULL,'改','edit',98),(279,NULL,'增','save',99),(280,NULL,'删','delete',99),(281,NULL,'查','view',99),(282,NULL,'改','edit',99),(283,NULL,'查','view',100),(284,NULL,'查','view',101),(285,NULL,'查','view',102),(286,'','通过','pass',102),(287,'','驳回','reject',102),(288,NULL,'查','view',103),(289,NULL,'查','view',104),(290,NULL,'查','view',105),(291,NULL,'查','view',106),(292,'','添加采购','add',106),(293,NULL,'删','delete',107),(294,NULL,'查','view',107),(295,NULL,'改','edit',107),(296,NULL,'查','view',108),(297,NULL,'查','view',109),(298,'','通过','pass',109),(299,'','驳回','reject',109),(300,NULL,'查','view',110),(301,NULL,'查','view',111),(307,NULL,'查','view',114),(308,NULL,'增','save',115),(309,NULL,'删','delete',115),(310,NULL,'查','view',115),(311,NULL,'改','edit',115),(312,NULL,'查','view',116),(313,'','评定','ratings',116),(314,NULL,'增','save',117),(315,NULL,'删','delete',117),(316,NULL,'查','view',117),(317,NULL,'改','edit',117),(318,NULL,'查','view',118),(319,NULL,'增','save',119),(320,NULL,'删','delete',119),(321,NULL,'查','view',119),(322,NULL,'改','edit',119),(323,NULL,'增','save',120),(324,NULL,'删','delete',120),(325,NULL,'查','view',120),(326,NULL,'改','edit',120);
/*!40000 ALTER TABLE `security_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_role`
--

DROP TABLE IF EXISTS `security_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(255) default NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role`
--

LOCK TABLES `security_role` WRITE;
/*!40000 ALTER TABLE `security_role` DISABLE KEYS */;
INSERT INTO `security_role` VALUES (3,'','管理员'),(4,NULL,'财务人员'),(5,NULL,'营销人员'),(7,NULL,'任务实例角色'),(8,'仅仅是测试角色2','test');
/*!40000 ALTER TABLE `security_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_role_permission`
--

DROP TABLE IF EXISTS `security_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_role_permission` (
  `id` bigint(20) NOT NULL auto_increment,
  `permission_id` bigint(20) default NULL,
  `role_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK679E223926E70397` (`permission_id`),
  KEY `FK679E2239C592DFF7` (`role_id`),
  CONSTRAINT `FK679E223926E70397` FOREIGN KEY (`permission_id`) REFERENCES `security_permission` (`id`),
  CONSTRAINT `FK679E2239C592DFF7` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role_permission`
--

LOCK TABLES `security_role_permission` WRITE;
/*!40000 ALTER TABLE `security_role_permission` DISABLE KEYS */;
INSERT INTO `security_role_permission` VALUES (30,27,3),(31,37,3),(32,38,3),(33,39,3),(34,40,3),(35,47,3),(36,53,3),(37,54,3),(38,55,3),(39,56,3),(40,57,3),(41,58,3),(42,59,3),(43,60,3),(44,61,3),(45,62,3),(85,25,3),(86,26,3),(87,28,3),(88,76,3),(89,77,3),(90,45,3),(91,46,3),(92,48,3),(93,78,3),(94,135,3),(95,136,3),(96,137,3),(97,138,3),(98,171,3),(99,172,3),(100,173,3),(101,174,3),(130,175,3),(131,176,3),(132,177,3),(133,178,3),(134,179,3),(135,180,3),(136,181,3),(137,182,3),(138,183,3),(139,184,3),(140,185,3),(141,186,3),(142,187,3),(143,188,3),(144,189,3),(145,190,3),(146,191,3),(147,192,3),(148,193,3),(149,194,3),(150,195,3),(151,196,3),(152,197,3),(153,198,3),(154,199,3),(155,200,3),(160,205,3),(161,206,3),(162,207,3),(163,208,3),(164,209,3),(165,210,3),(166,215,3),(167,216,3),(168,217,3),(169,218,3),(170,219,3),(171,220,3),(172,221,3),(173,222,3),(174,223,3),(175,224,3),(176,225,3),(177,226,3),(182,231,3),(183,232,3),(184,233,3),(185,234,3),(186,235,3),(187,236,3),(188,237,3),(189,238,3),(190,239,3),(191,240,3),(192,241,3),(193,242,3),(194,243,3),(195,244,3),(196,245,3),(197,246,3),(198,247,3),(199,248,3),(200,249,3),(201,250,3),(202,251,3),(203,252,3),(204,253,3),(205,254,3),(206,255,3),(207,256,3),(208,257,3),(209,258,3),(210,259,3),(211,260,3),(212,261,3),(213,262,3),(214,263,3),(215,265,3),(216,266,3),(217,267,3),(220,270,3),(221,275,3),(222,276,3),(223,277,3),(224,278,3),(225,279,3),(226,280,3),(227,281,3),(228,282,3),(229,283,3),(230,284,3),(231,285,3),(232,286,3),(233,287,3),(234,288,3),(235,289,3),(236,291,3),(237,292,3),(238,293,3),(239,294,3),(240,295,3),(241,296,3),(242,290,3),(243,297,3),(244,298,3),(245,299,3),(246,300,3),(247,301,3),(252,307,3),(253,308,3),(254,309,3),(255,310,3),(256,311,3),(257,312,3),(258,313,3),(259,314,3),(260,315,3),(261,316,3),(262,317,3),(263,318,3),(264,319,3),(265,320,3),(266,321,3),(267,322,3),(268,323,3),(269,324,3),(270,325,3),(271,326,3);
/*!40000 ALTER TABLE `security_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_user`
--

DROP TABLE IF EXISTS `security_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_time` datetime default NULL,
  `email` varchar(128) default NULL,
  `password` varchar(64) NOT NULL,
  `phone` varchar(32) default NULL,
  `realname` varchar(32) NOT NULL,
  `salt` varchar(32) NOT NULL,
  `status` varchar(16) NOT NULL,
  `username` varchar(32) NOT NULL,
  `org_id` bigint(20) default NULL,
  `teacher_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_35` (`teacher_id`),
  KEY `FKD607B56A453A1286` (`org_id`),
  CONSTRAINT `FKD607B56A453A1286` FOREIGN KEY (`org_id`) REFERENCES `security_organization` (`id`),
  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`teacher_id`) REFERENCES `oa_teacher_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user`
--

LOCK TABLES `security_user` WRITE;
/*!40000 ALTER TABLE `security_user` DISABLE KEYS */;
INSERT INTO `security_user` VALUES (1,'2012-08-03 14:58:38','scstlinfeng@gmail.com','7a8f27edd04296d1a2f484cca71c6834a87356b6','18001120497','林锋','9754469b0353a6a7','enabled','admin',24,13),(3,'2013-07-16 14:12:12','2143123213@123.sd','f03223120a94b6bf7956f3692014f0e479dca041','1413123','张惠妹','6edff6c4273a5513','enabled','zhanghuimei',30,12);
/*!40000 ALTER TABLE `security_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_user_role`
--

DROP TABLE IF EXISTS `security_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user_role` (
  `id` bigint(20) NOT NULL auto_increment,
  `priority` int(11) NOT NULL,
  `role_id` bigint(20) default NULL,
  `user_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK6DD3562BC592DFF7` (`role_id`),
  KEY `FK6DD3562B6ABDA3D7` (`user_id`),
  CONSTRAINT `FK6DD3562B6ABDA3D7` FOREIGN KEY (`user_id`) REFERENCES `security_user` (`id`),
  CONSTRAINT `FK6DD3562BC592DFF7` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_role`
--

LOCK TABLES `security_user_role` WRITE;
/*!40000 ALTER TABLE `security_user_role` DISABLE KEYS */;
INSERT INTO `security_user_role` VALUES (1,99,3,1),(2,99,3,3);
/*!40000 ALTER TABLE `security_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_task`
--

DROP TABLE IF EXISTS `ss_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(255) default NULL,
  `title` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_task`
--

LOCK TABLES `ss_task` WRITE;
/*!40000 ALTER TABLE `ss_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_attachment`
--

DROP TABLE IF EXISTS `sys_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_attachment` (
  `id` bigint(20) NOT NULL auto_increment,
  `file_name` varchar(100) default NULL,
  `file_ext` varchar(100) default NULL,
  `file_size` int(10) default NULL,
  `service_file` varchar(200) default NULL,
  `create_time` datetime default NULL,
  `user_id` bigint(20) default NULL,
  `attachment_type` int(11) default NULL,
  `url_path` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_attachment`
--

LOCK TABLES `sys_attachment` WRITE;
/*!40000 ALTER TABLE `sys_attachment` DISABLE KEYS */;
INSERT INTO `sys_attachment` VALUES (1,'213.txt','txt',361,'D:\\work\\space\\github_space\\eduoa_space\\node-eduoa\\src\\main\\webapp\\upload\\teaching_plan\\2013-7\\29\\1375104563767.txt','2013-07-29 21:29:32',1,2,'/upload/teaching_plan/2013-7/29/1375104563767.txt'),(5,'dso_api.doc','doc',67584,'D:\\work\\space\\github_space\\eduoa_space\\node-eduoa\\src\\main\\webapp\\upload\\multimedia\\2013-7\\30\\1375193752605.doc','2013-07-30 22:15:56',1,2,'/upload/multimedia/2013-7/30/1375193752605.doc'),(6,'213.txt','txt',361,'D:\\work\\space\\github_space\\eduoa_space\\node-eduoa\\src\\main\\webapp\\upload\\teaching_plan\\2013-7\\30\\1375194154956.txt','2013-07-30 22:22:34',1,2,'/upload/teaching_plan/2013-7/30/1375194154956.txt'),(7,'java仿百度文库使用SWFTools转换pdf文件.txt','txt',6115,'D:\\work\\space\\github_space\\eduoa_space\\node-eduoa\\src\\main\\webapp\\upload\\teaching_plan\\2013-7\\30\\1375194201362.txt','2013-07-30 22:23:21',1,2,'/upload/teaching_plan/2013-7/30/1375194201362.txt'),(8,'java仿百度文库使用SWFTools转换pdf文件.txt','txt',6115,'D:\\work\\space\\github_space\\eduoa_space\\node-eduoa\\src\\main\\webapp\\upload\\teaching_plan\\2013-7\\30\\1375194203097.txt','2013-07-30 22:23:23',1,2,'/upload/teaching_plan/2013-7/30/1375194203097.txt'),(9,'java仿百度文库使用SWFTools转换pdf文件.txt','txt',6115,'D:\\work\\space\\github_space\\eduoa_space\\node-eduoa\\src\\main\\webapp\\upload\\teaching_plan\\2013-7\\30\\1375194217514.txt','2013-07-30 22:23:40',1,2,'/upload/teaching_plan/2013-7/30/1375194217514.txt'),(10,'java仿百度文库使用SWFTools转换pdf文件.txt','txt',6115,'D:\\work\\space\\github_space\\eduoa_space\\node-eduoa\\src\\main\\webapp\\upload\\teaching_plan\\2013-7\\30\\1375194264809.txt','2013-07-30 22:24:24',1,2,'/upload/teaching_plan/2013-7/30/1375194264809.txt'),(11,'213.txt','txt',361,'D:\\work\\space\\github_space\\eduoa_space\\node-eduoa\\src\\main\\webapp\\upload\\teaching_plan\\2013-7\\30\\1375194387214.txt','2013-07-30 22:26:29',1,2,'/upload/teaching_plan/2013-7/30/1375194387214.txt');
/*!40000 ALTER TABLE `sys_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_type_rules`
--

DROP TABLE IF EXISTS `sys_type_rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_type_rules` (
  `id` bigint(20) NOT NULL auto_increment,
  `applay_id` bigint(20) default NULL,
  `role_type` int(11) default NULL,
  `role_name` varchar(50) default NULL,
  `content` varchar(500) default NULL,
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_Reference_23` (`applay_id`),
  CONSTRAINT `FK_Reference_23` FOREIGN KEY (`applay_id`) REFERENCES `oa_appraisal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_type_rules`
--

LOCK TABLES `sys_type_rules` WRITE;
/*!40000 ALTER TABLE `sys_type_rules` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_type_rules` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-08-18  9:44:22
