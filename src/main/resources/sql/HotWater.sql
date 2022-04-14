DROP DATABASE IF EXISTS HotWater;
CREATE DATABASE HotWater DEFAULT CHARACTER SET utf8;
USE HotWater;


-- MySQL dump 10.13  Distrib 5.7.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotwater
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `advice`
--

DROP TABLE IF EXISTS `advice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` blob NOT NULL,
  `push_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `advice_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advice`
--

LOCK TABLES `advice` WRITE;
/*!40000 ALTER TABLE `advice` DISABLE KEYS */;
INSERT INTO `advice` (`id`, `content`, `push_time`) VALUES (1,_binary 'aaaaaa','2022-04-14 00:45:26');
/*!40000 ALTER TABLE `advice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diary`
--

DROP TABLE IF EXISTS `diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `privacy` varchar(10) NOT NULL DEFAULT '私密',
  `title` varchar(50) NOT NULL,
  `content` blob,
  `write_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  UNIQUE KEY `one_per_day_pk` (`id`,`write_date`),
  UNIQUE KEY `diary_id_uindex` (`id`),
  KEY `diary_user__fk` (`user_id`),
  CONSTRAINT `diary_user__fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary`
--

LOCK TABLES `diary` WRITE;
/*!40000 ALTER TABLE `diary` DISABLE KEYS */;
INSERT INTO `diary` (`id`, `user_id`, `privacy`, `title`, `content`, `write_date`, `modify_date`) VALUES (10,2,'私密','2022/4/13日记',_binary '<p>今天又喝了一桶开水</p><p>复习一下java的native关键字</p><blockquote>一个Native Method就是一个java调用非java代码的接口</blockquote><p><br></p>','2022-04-13 00:00:00','2022-04-14 02:22:27'),(12,2,'私密','2022/4/14 学习vue',_binary '<p>学习并使用了天气插件，参考使用了和风天气</p><pre class=\"ql-syntax\" spellcheck=\"false\">&lt;Card style=\"width:100%;min-height:300px;\" dis-hover :bordered=\"false\"&gt;\n  &lt;p slot=\"title\"&gt;&lt;Icon type=\"ios-cloud\" /&gt;今日天气&lt;/p&gt;\n  &lt;!-- 多喝开水日记 --&gt;\n  &lt;div id=\"he-plugin-standard\"&gt;&lt;/div&gt;\n  &lt;remote-js&gt;&lt;/remote-js&gt;\n  &lt;remote-script\n      src=\"https://widget.qweather.net/standard/static/js/he-standard-common.js?v=2.0\"\n  &gt;\n  &lt;/remote-script&gt;\n&lt;/Card&gt;\n\nmounted() {\n  window.WIDGET = {\n      \"CONFIG\": {\n      \"layout\": \"2\",\n      \"width\": \"300\",\n      \"height\": \"450\",\n      \"background\": \"1\",\n      \"dataColor\": \"FFFFFF\",\n      \"borderRadius\": \"3\",\n      \"key\": \"f30356ffe3fe4f539c2efdf1fc304586\"\n    }\n  };\n},\n</pre>','2022-04-14 00:00:00','2022-04-14 12:48:21');
/*!40000 ALTER TABLE `diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diary_tag`
--

DROP TABLE IF EXISTS `diary_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diary_tag` (
  `d_id` int NOT NULL,
  `tag` varchar(5) NOT NULL,
  UNIQUE KEY `diary_tag_pk` (`d_id`,`tag`),
  KEY `diary_tag_tag_index` (`tag`),
  CONSTRAINT `diary__fk` FOREIGN KEY (`d_id`) REFERENCES `diary` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary_tag`
--

LOCK TABLES `diary_tag` WRITE;
/*!40000 ALTER TABLE `diary_tag` DISABLE KEYS */;
INSERT INTO `diary_tag` (`d_id`, `tag`) VALUES (10,'java'),(10,'tag1'),(10,'tag2'),(12,'vue');
/*!40000 ALTER TABLE `diary_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `signs` varchar(100) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `reg_time` datetime NOT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `user_name_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `username`, `password`, `signs`, `birthday`, `reg_time`, `avatar`, `sex`) VALUES (1,'admin@nju.se','admin','admin',NULL,NULL,'2022-04-01 00:00:00',NULL,'未知'),(2,'jh@nju.se','jh','qweqwe','灰灰灰灰','1999-11-08 00:00:00','2022-04-01 00:00:00',NULL,'男性'),(3,'existed@nju.se','existed','123456',NULL,NULL,'2022-04-02 00:00:00',NULL,'未知');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-14 20:19:22
