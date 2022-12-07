-- MySQL dump 10.13  Distrib 8.0.31, for macos12.6 (arm64)
--
-- Host: localhost    Database: coursehub
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_user`
--
USE heroku_db3370a171a8d63;
DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1j9d9a06i600gd43uu3km82jw` (`email`),
  UNIQUE KEY `UK_3k4cplvh82srueuttfkwnylq0` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (3,'sdutta7@umd.edu','Soumosir Dutta','$2a$10$tsYKqkAfoGUzGol2NU9MtOiKgrKVmGWk1/2WRU1KWqa2XVjXA3JTK','sdutta'),(4,'soumosir@gmail.com','John Doe','$2a$10$ZjKjUA8qMWv4YcuWu6KWJ.PILDh6sJzsqTiG7BcXhCxDjdwPddWlm','jdoe'),(5,'bscscs@ycns.com','Burry Bacca','$2a$10$tH01eFPwcetDQ1JD0eCAguDLBtiMYdwJIPM7p9xO1cUU8rzGQeJKi','bbacca'),(6,'soumosirdutta@gmail.com','Vincent Yu','$2a$10$q.SCbVsHg8oGlohyq0oc2.IxGKCBrpV6vCgMVBFqPV.aImmCogqWa','vin');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user_roles`
--

DROP TABLE IF EXISTS `app_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `app_user_roles` (
  `app_user_id` bigint NOT NULL,
  `roles_id` bigint NOT NULL,
  KEY `FK23e7b5jyl3ql41rk3566gywdd` (`roles_id`),
  KEY `FKkwxexnudtp5gmt82j0qtytnoe` (`app_user_id`),
  CONSTRAINT `FK23e7b5jyl3ql41rk3566gywdd` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKkwxexnudtp5gmt82j0qtytnoe` FOREIGN KEY (`app_user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user_roles`
--

LOCK TABLES `app_user_roles` WRITE;
/*!40000 ALTER TABLE `app_user_roles` DISABLE KEYS */;
INSERT INTO `app_user_roles` VALUES (5,1),(6,1),(3,1),(3,2),(4,1);
/*!40000 ALTER TABLE `app_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `content` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (9,NULL,'Sorting Algorithms Review','video',NULL,'jdoe'),(10,NULL,'Sorting Algorithms Deep Dive','image',NULL,'jdoe'),(24,NULL,'Lecture 5','video','https://www.youtube.com/watch?v=2nd73lyvq4w&ab_channel=MrBeast','jdoe');
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` bigint NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `instructor` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remaining_seats` bigint DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `total_seats` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i60mruj0y7a7vs99dqpiye7en` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (11,'ENPM809W','A data structure is a named location that can be used to store and organize data. ','2022-12-05 22:06:55.685000','jdoe','Data Structures and Algorithm',4,'2022-12-05 22:06:55.683000',5),(12,'ENPM312','Human-computer interaction (HCI) is a multidisciplinary field of study focusing on the design of computer technology.','2022-12-05 22:03:20.427000','jdoe','Human Computer Interaction',18,'2022-12-05 22:03:20.427000',20),(13,'ENPM613','The implementation stage of software development is the process of developing an executable system for delivery to the customer. Sometimes this involves separate activities of software design and programming. .','2022-12-05 22:06:55.714000','jdoe','Software Design and Implementation',59,'2022-12-05 22:06:55.714000',60),(14,'ENPM083','The object oriented stage of software development is the process of developing an executable system for delivery to the customer. s.','2022-12-05 22:06:55.723435','jdoe','Object Oriented Programming',59,'2022-12-05 22:06:55.723360',60);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_contents`
--

DROP TABLE IF EXISTS `course_contents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `course_contents` (
  `course_id` bigint NOT NULL,
  `contents_id` bigint NOT NULL,
  UNIQUE KEY `UK_m2apua7kha7tx61opjt43gtn3` (`contents_id`),
  KEY `FKh1x609kh1paiva96xu5ysfwpj` (`course_id`),
  CONSTRAINT `FKh1x609kh1paiva96xu5ysfwpj` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKojjr4uymfcyut9ulxyucc9n8k` FOREIGN KEY (`contents_id`) REFERENCES `content` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_contents`
--

LOCK TABLES `course_contents` WRITE;
/*!40000 ALTER TABLE `course_contents` DISABLE KEYS */;
INSERT INTO `course_contents` VALUES (11,9),(11,10),(11,24);
/*!40000 ALTER TABLE `course_contents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_enrolled_users`
--

DROP TABLE IF EXISTS `course_enrolled_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `course_enrolled_users` (
  `course_id` bigint NOT NULL,
  `enrolled_users_id` bigint NOT NULL,
  KEY `FKshvfih5jjefihevi24tn3savn` (`enrolled_users_id`),
  KEY `FK529mxae2bwlw8ccxyh77jvcm7` (`course_id`),
  CONSTRAINT `FK529mxae2bwlw8ccxyh77jvcm7` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKshvfih5jjefihevi24tn3savn` FOREIGN KEY (`enrolled_users_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_enrolled_users`
--

LOCK TABLES `course_enrolled_users` WRITE;
/*!40000 ALTER TABLE `course_enrolled_users` DISABLE KEYS */;
INSERT INTO `course_enrolled_users` VALUES (14,5),(12,4);
/*!40000 ALTER TABLE `course_enrolled_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_exams`
--

DROP TABLE IF EXISTS `course_exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `course_exams` (
  `course_id` bigint NOT NULL,
  `exams_id` bigint NOT NULL,
  UNIQUE KEY `UK_iu98yprdj8lqo7i7wvxfhx8d9` (`exams_id`),
  KEY `FKfds2u7j5yjvl19vlcekfa768y` (`course_id`),
  CONSTRAINT `FKfds2u7j5yjvl19vlcekfa768y` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKg4j8jq9j08hk5bge68a370el7` FOREIGN KEY (`exams_id`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_exams`
--

LOCK TABLES `course_exams` WRITE;
/*!40000 ALTER TABLE `course_exams` DISABLE KEYS */;
INSERT INTO `course_exams` VALUES (11,7),(12,8),(12,15),(12,16),(12,17),(12,18),(12,19),(13,20),(13,21),(13,22),(13,23);
/*!40000 ALTER TABLE `course_exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_wishlist_users`
--

DROP TABLE IF EXISTS `course_wishlist_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `course_wishlist_users` (
  `course_id` bigint NOT NULL,
  `wishlist_users_id` bigint NOT NULL,
  KEY `FKdgbk5cak5u4jk9j3f8x7f1ixp` (`wishlist_users_id`),
  KEY `FKnd3djjlq92ddvayg11yc8s1dw` (`course_id`),
  CONSTRAINT `FKdgbk5cak5u4jk9j3f8x7f1ixp` FOREIGN KEY (`wishlist_users_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FKnd3djjlq92ddvayg11yc8s1dw` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_wishlist_users`
--

LOCK TABLES `course_wishlist_users` WRITE;
/*!40000 ALTER TABLE `course_wishlist_users` DISABLE KEYS */;
INSERT INTO `course_wishlist_users` VALUES (14,6);
/*!40000 ALTER TABLE `course_wishlist_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `id` bigint NOT NULL,
  `answers` varchar(255) DEFAULT NULL,
  `duration` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `questions` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (7,NULL,120,'Hash map implementation',NULL,'QUIZ','jdoe'),(8,NULL,120,'B Trees implementation Test',NULL,'EXAM','jdoe'),(15,'\"{how many bytes is char?=1, what is array?=DS, what is 1+9?=10, what is 1+1?=2}\"\n',60,'Quiz 1','\"{how many bytes is char?=[3, 2, 1, 0], what is array?=[DS, wall, io, boolean], what is 1+9?=[3, 2, 1, 10], what is 1+1?=[3, 2, 1, 0]}\"\n\n','Quiz','jdoe'),(16,'\"{how many bytes is char?=1, what is array?=DS, what is 1+9?=10, what is 1+1?=2}\"\n',60,'Quiz 1','\"{how many bytes is char?=[3, 2, 1, 0], what is array?=[DS, wall, io, boolean], what is 1+9?=[3, 2, 1, 10], what is 1+1?=[3, 2, 1, 0]}\"\n\n','Quiz','jdoe'),(17,'\"{how many bytes is char?=1, what is array?=DS, what is 1+9?=10, what is 1+1?=2}\"\n',60,'Quiz 1','\"{how many bytes is char?=[3, 2, 1, 0], what is array?=[DS, wall, io, boolean], what is 1+9?=[3, 2, 1, 10], what is 1+1?=[3, 2, 1, 0]}\"\n\n','Quiz','jdoe'),(18,'\"{how many bytes is char?=1, what is array?=DS, what is 1+9?=10, what is 1+1?=2}\"\n',60,'Quiz 1','\"{how many bytes is char?=[3, 2, 1, 0], what is array?=[DS, wall, io, boolean], what is 1+9?=[3, 2, 1, 10], what is 1+1?=[3, 2, 1, 0]}\"\n\n','Quiz','jdoe'),(19,'\"{how many bytes is char?=1, what is array?=DS, what is 1+9?=10, what is 1+1?=2}\"\n',60,'Quiz 1','\"{how many bytes is char?=[3, 2, 1, 0], what is array?=[DS, wall, io, boolean], what is 1+9?=[3, 2, 1, 10], what is 1+1?=[3, 2, 1, 0]}\"\n\n','Quiz','jdoe'),(20,NULL,60,'Quiz',NULL,'Quiz','jdoe'),(21,NULL,60,'Quiz 3',NULL,'Quiz','jdoe'),(22,NULL,60,'Quiz 3',NULL,'Quiz','jdoe'),(23,'\"{how many bytes is char?=1, what is array?=DS, what is 1+9?=10, what is 1+1?=2}\"\n',60,'Quiz 4','\"{how many bytes is char?=[3, 2, 1, 0], what is array?=[DS, wall, io, boolean], what is 1+9?=[3, 2, 1, 10], what is 1+1?=[3, 2, 1, 0]}\"\n\n','quiz','jdoe');
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_result`
--

DROP TABLE IF EXISTS `exam_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `exam_result` (
  `id` bigint NOT NULL,
  `answers` varchar(255) DEFAULT NULL,
  `marks` bigint NOT NULL,
  `exam_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmblvyjlk9x7rrm7mvqtbedycc` (`exam_id`),
  KEY `FK18las0uhcj294jwvn3umx00qn` (`user_id`),
  CONSTRAINT `FK18las0uhcj294jwvn3umx00qn` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FKmblvyjlk9x7rrm7mvqtbedycc` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_result`
--

LOCK TABLES `exam_result` WRITE;
/*!40000 ALTER TABLE `exam_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (25);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'ROLE_ADMIN'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-07 11:31:25
