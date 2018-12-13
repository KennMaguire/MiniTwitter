CREATE DATABASE  IF NOT EXISTS `twitterdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `twitterdb`;
-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: twitterdb
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `follow` (
  `idfollow` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `followUserID` int(11) NOT NULL,
  `dateFollowed` datetime NOT NULL,
  PRIMARY KEY (`idfollow`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (44,12,10,'2018-12-10 18:06:09'),(50,10,12,'2018-12-10 18:43:06'),(56,9,11,'2018-12-10 18:52:20'),(67,12,9,'2018-12-10 20:04:38'),(68,9,12,'2018-12-10 20:11:41'),(73,9,13,'2018-12-10 20:28:32'),(75,13,9,'2018-12-10 20:31:23'),(76,13,10,'2018-12-10 20:31:24'),(77,10,9,'2018-12-10 20:45:38'),(78,10,11,'2018-12-10 20:45:39'),(79,9,10,'2018-12-10 21:00:27');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hashtag`
--

DROP TABLE IF EXISTS `Hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Hashtag` (
  `hashtagID` int(11) NOT NULL AUTO_INCREMENT,
  `hashtagText` varchar(45) NOT NULL,
  `hashtagCount` int(11) NOT NULL,
  PRIMARY KEY (`hashtagID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hashtag`
--

LOCK TABLES `Hashtag` WRITE;
/*!40000 ALTER TABLE `Hashtag` DISABLE KEYS */;
INSERT INTO `Hashtag` VALUES (28,'#friend',10),(29,'#banana',13),(30,'#amigo',3),(31,'#yo',11),(32,'#yall',4),(33,'#bud',2),(34,'#myfriend',1),(35,'#buddy',1),(36,'#hat',2),(37,'#me',1),(38,'#sup',1),(39,'#mate',6),(40,'#whatsup',1),(41,'#whatup',2),(42,'#friendo',2),(43,'#friends',1),(44,'#yolo',0),(45,'#thuglife',0),(46,'#fun',1);
/*!40000 ALTER TABLE `Hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tweetHashtag`
--

DROP TABLE IF EXISTS `tweetHashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tweetHashtag` (
  `idtweetHashtag` int(11) NOT NULL AUTO_INCREMENT,
  `tweetID` varchar(45) NOT NULL,
  `hashtagID` varchar(45) NOT NULL,
  PRIMARY KEY (`idtweetHashtag`),
  KEY `twitID_idx` (`tweetID`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tweetHashtag`
--

LOCK TABLES `tweetHashtag` WRITE;
/*!40000 ALTER TABLE `tweetHashtag` DISABLE KEYS */;
INSERT INTO `tweetHashtag` VALUES (76,'131','28'),(77,'132','28'),(78,'133','29'),(79,'134','28'),(80,'134','29'),(81,'135','30'),(82,'136','30'),(83,'136','28'),(84,'136','29'),(88,'138','28'),(89,'139','30'),(91,'141','29'),(92,'142','28'),(93,'143','31'),(94,'144','32'),(95,'145','33'),(96,'146','34'),(97,'147','35'),(98,'148','36'),(99,'149','31'),(100,'149','37'),(101,'150','38'),(102,'151','29'),(103,'152','29'),(104,'153','29'),(105,'154','39'),(106,'155','39'),(107,'156','39'),(108,'157','39'),(109,'158','39'),(110,'159','39'),(111,'160','31'),(112,'161','31'),(113,'162','31'),(114,'162','29'),(115,'162','28'),(116,'162','32'),(121,'164','31'),(122,'164','29'),(123,'164','28'),(124,'164','32'),(133,'182','36'),(134,'184','40'),(135,'191','41'),(136,'192','41'),(137,'193','29'),(138,'194','42'),(139,'195','42'),(140,'197','43'),(142,'229','29'),(143,'230','32'),(144,'236','29'),(145,'237','33'),(146,'242','28'),(147,'244','28'),(150,'250','29'),(151,'251','31'),(152,'252','31'),(154,'254','46');
/*!40000 ALTER TABLE `tweetHashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `twits`
--

DROP TABLE IF EXISTS `twits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `twits` (
  `twitID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `twit` varchar(400) DEFAULT NULL,
  `twitDate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`twitID`),
  KEY `userID` (`userID`),
  CONSTRAINT `twits_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `twits`
--

LOCK TABLES `twits` WRITE;
/*!40000 ALTER TABLE `twits` DISABLE KEYS */;
INSERT INTO `twits` VALUES (131,10,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a> ','2018-12-10 14:45:46'),(132,10,'sup <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a> ','2018-12-10 14:45:52'),(133,10,'ehy <a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 14:45:56'),(134,10,'sup <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a>  and <a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 14:46:02'),(135,10,'yo <a href=\'userPage?action=getHashtags&amp;hashtagID=30\' class=\'bluex\'> #amigo </a> ','2018-12-10 14:46:16'),(136,10,'sup <a href=\'userPage?action=getHashtags&amp;hashtagID=30\' class=\'bluex\'> #amigo </a>  and <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a>  and <a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 14:46:28'),(138,9,'what\'s up <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a> ','2018-12-10 14:53:54'),(139,9,'yo <a href=\'userPage?action=getHashtags&amp;hashtagID=30\' class=\'bluex\'> #amigo </a> ','2018-12-10 14:53:59'),(141,9,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 14:54:07'),(142,10,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a> ','2018-12-10 14:54:59'),(143,10,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=31\' class=\'bluex\'> #yo </a> ','2018-12-10 14:56:58'),(144,10,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=32\' class=\'bluex\'> #yall </a> ','2018-12-10 15:22:42'),(145,10,'sup <a href=\'userPage?action=getHashtags&amp;hashtagID=33\' class=\'bluex\'> #bud </a> ','2018-12-10 15:22:48'),(146,10,'yo <a href=\'userPage?action=getHashtags&amp;hashtagID=34\' class=\'bluex\'> #myfriend </a> ','2018-12-10 15:22:53'),(147,10,'sup <a href=\'userPage?action=getHashtags&amp;hashtagID=35\' class=\'bluex\'> #buddy </a> ','2018-12-10 15:23:00'),(148,10,'nice <a href=\'userPage?action=getHashtags&amp;hashtagID=36\' class=\'bluex\'> #hat </a> ','2018-12-10 15:23:04'),(149,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=31\' class=\'bluex\'> #yo </a>  <a href=\'userPage?action=getHashtags&amp;hashtagID=37\' class=\'bluex\'> #me </a> ','2018-12-10 15:23:10'),(150,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=38\' class=\'bluex\'> #sup </a> ','2018-12-10 15:23:46'),(151,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 15:23:52'),(152,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 15:23:55'),(153,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 15:23:58'),(154,9,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=39\' class=\'bluex\'> #mate </a> ','2018-12-10 15:24:23'),(155,9,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=39\' class=\'bluex\'> #mate </a> ','2018-12-10 15:24:27'),(156,9,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=39\' class=\'bluex\'> #mate </a> ','2018-12-10 15:26:27'),(157,9,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=39\' class=\'bluex\'> #mate </a> ','2018-12-10 15:26:40'),(158,9,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=39\' class=\'bluex\'> #mate </a> ','2018-12-10 15:27:14'),(159,9,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=39\' class=\'bluex\'> #mate </a> ','2018-12-10 15:27:23'),(160,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=31\' class=\'bluex\'> #yo </a> ','2018-12-10 15:46:21'),(161,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=31\' class=\'bluex\'> #yo </a> ','2018-12-10 15:46:24'),(162,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=31\' class=\'bluex\'> #yo </a>  <a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a>  <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a>  <a href=\'userPage?action=getHashtags&amp;hashtagID=32\' class=\'bluex\'> #yall </a> ','2018-12-10 15:46:34'),(164,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=31\' class=\'bluex\'> #yo </a>  <a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a>  <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a>  <a href=\'userPage?action=getHashtags&amp;hashtagID=32\' class=\'bluex\'> #yall </a> ','2018-12-10 15:49:21'),(167,10,'hey','2018-12-10 15:50:11'),(168,10,'hey','2018-12-10 15:50:15'),(182,9,'sup <a href=\'userPage?action=getHashtags&amp;hashtagID=36\' class=\'bluex\'> #hat </a> ','2018-12-10 16:08:30'),(183,9,'hey <a class=\'blueX\'> @kenners </a> what\'s up','2018-12-10 16:08:38'),(184,9,'hey <a class=\'blueX\'> @kenners </a> <a href=\'userPage?action=getHashtags&amp;hashtagID=40\' class=\'bluex\'> #whatsup </a> ','2018-12-10 16:08:48'),(186,9,'hey #whatups <a class=\'blueX\'> @kenners </a>','2018-12-10 16:11:36'),(187,10,'hey <a class=\'blueX\'> @crab </a>','2018-12-10 16:14:07'),(191,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=41\' class=\'bluex\'> #whatup </a>  <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 16:17:34'),(192,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=41\' class=\'bluex\'> #whatup </a>  <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 16:17:44'),(193,9,'<a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 16:40:29'),(194,9,'<a href=\'userPage?action=getHashtags&amp;hashtagID=42\' class=\'bluex\'> #friendo </a> ','2018-12-10 16:40:47'),(195,9,'<a href=\'userPage?action=getHashtags&amp;hashtagID=42\' class=\'bluex\'> #friendo </a> ','2018-12-10 16:40:51'),(196,12,'whatup <a class=\'blueX\'> @kenners </a>','2018-12-10 17:03:53'),(197,12,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=43\' class=\'bluex\'> #friends </a> ','2018-12-10 17:04:02'),(199,10,'whatup <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 17:15:07'),(200,9,'whatup <a class=\'blueX\'> @kenners </a>','2018-12-10 17:25:47'),(201,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 17:26:06'),(202,12,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 17:26:36'),(203,10,'hey <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 17:30:54'),(204,10,'sup <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 17:31:03'),(205,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 17:33:57'),(206,9,'sup <a class=\'blueX\'> @kenners </a>','2018-12-10 17:34:06'),(207,12,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 17:34:37'),(208,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 17:37:54'),(209,9,'sup <a class=\'blueX\'> @kenners </a>','2018-12-10 17:38:02'),(210,12,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 17:38:24'),(211,9,'sup <a class=\'blueX\'> @kenners </a>','2018-12-10 17:56:34'),(213,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 17:57:21'),(214,12,'yo <a class=\'blueX\'> @kenners </a>','2018-12-10 17:57:35'),(215,12,'hey <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 17:57:45'),(216,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 18:05:51'),(217,9,'sup <a class=\'blueX\'> @kenners </a>','2018-12-10 18:05:56'),(218,12,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 18:06:14'),(219,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 19:43:15'),(220,9,'whatup <a class=\'blueX\'> @kenners </a>','2018-12-10 19:43:21'),(221,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 19:44:52'),(222,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 19:45:35'),(223,9,'sup <a class=\'blueX\'> @kenners </a>','2018-12-10 19:45:46'),(224,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 19:47:19'),(225,9,'sup <a class=\'blueX\'> @kenners </a>','2018-12-10 19:47:36'),(226,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 19:53:37'),(227,9,'sup <a class=\'blueX\'> @kenners </a>','2018-12-10 19:53:45'),(228,10,'hey <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 19:54:54'),(229,10,'hey where it at <a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 20:03:44'),(230,10,'sup <a href=\'userPage?action=getHashtags&amp;hashtagID=32\' class=\'bluex\'> #yall </a> ','2018-12-10 20:03:48'),(231,10,'hey <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 20:03:53'),(232,10,'what\'s up <a class=\'blueX\'> @kenners20000 </a>','2018-12-10 20:04:01'),(233,12,'sup <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 20:04:30'),(234,12,'hey <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 20:04:35'),(235,12,'YOOO <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 20:04:45'),(236,13,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 20:13:10'),(237,13,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=33\' class=\'bluex\'> #bud </a> ','2018-12-10 20:13:20'),(238,13,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 20:13:47'),(239,10,'hey <a class=\'blueX\'> @kenners2000 </a>','2018-12-10 20:26:41'),(240,9,'hey <a class=\'blueX\'> @kenners </a>','2018-12-10 20:27:59'),(241,9,'sup <a class=\'blueX\'> @kenners </a>','2018-12-10 20:28:04'),(242,9,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a>  miss you <a class=\'blueX\'> @kenners </a>','2018-12-10 20:28:14'),(244,9,'hey <a href=\'userPage?action=getHashtags&amp;hashtagID=28\' class=\'bluex\'> #friend </a> ','2018-12-10 20:28:27'),(250,10,'sup <a href=\'userPage?action=getHashtags&amp;hashtagID=29\' class=\'bluex\'> #banana </a> ','2018-12-10 20:33:05'),(251,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=31\' class=\'bluex\'> #yo </a>  friend','2018-12-10 20:33:16'),(252,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=31\' class=\'bluex\'> #yo </a>  friend','2018-12-10 20:33:23'),(254,10,'<a href=\'userPage?action=getHashtags&amp;hashtagID=46\' class=\'bluex\'> #fun </a> ','2018-12-10 20:34:05');
/*!40000 ALTER TABLE `twits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `emailAddress` varchar(45) NOT NULL,
  `password` varchar(300) NOT NULL,
  `birthDate` varchar(45) NOT NULL,
  `questionNo` int(11) NOT NULL,
  `answer` varchar(45) NOT NULL,
  `salt` varchar(150) NOT NULL,
  `lastLogin` datetime DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'kenners ads','kenners2000','yousir12@gmail.com','40c4c2095fd6b45f7d52d3c93d7c8145a6374ad11eddf2af606fb8c660eb841a','2018-11-05',1,'aaa','cWDXp5/JJ7/Ff20Mqe7QkzRtxugsP/Hv5mKzioX4dyw=','2018-12-10 21:00:19'),(10,'bird man1','kenners','kenneth.maguire@ucdenver.edu','363c277afab879df2dc0253b63e38b958900b9fc32f3775d1c10f6355da7c4c5','2018-11-20',1,'a','25eyQ8nSSLsG14rOSEAu+UC2aeZIijwi8SXf4CJmwC0=','2018-12-10 20:45:11'),(11,'nice face','apples','yousir12@mail.com','3c46430715c94d2c91f7fc8ba92dc91d698248e587f38997b81b12efea708bff','2018-11-26',1,'a','LzFKe+d7UVyKo5dyVMmpUIjIXofnJk5rmjQ+d5ck0LQ=',NULL),(12,'kenneth maguire','yoyoyoyo111','yousir12@bail.com','3a413ae6882059a55b92f090a1e3a7b20d9ccfed4fb644e0bbc50aeff6d46fdd','2018-12-24',1,'a','9nINmjprnFAg16SvV+HTQajNu8QeM09JfnostOTYWnc=','2018-12-10 20:04:23'),(13,'kenners fam','happybirthday','yousir12@remail.com','bd110a925e37cc4043d2b7bd382c8de9d0c99ace1d6973976693b55a851b8813','2018-12-24',1,'a','o1iy9AtjBcNAroQ0OdJmJrHYF2DwpiLwAjNEO4ShRNU=','2018-12-10 20:31:14');
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

-- Dump completed on 2018-12-10 21:08:35
