-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: db_bookstore
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `dbo_order`
--

DROP TABLE IF EXISTS `dbo_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dbo_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(225) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `customer_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone_number` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_order`
--

LOCK TABLES `dbo_order` WRITE;
/*!40000 ALTER TABLE `dbo_order` DISABLE KEYS */;
INSERT INTO `dbo_order` VALUES (1,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',205.35,'2019-05-12 18:33:41'),(2,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',107.10,'2019-05-12 18:35:05'),(3,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',24.85,'2019-05-12 18:42:06'),(4,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',24.85,'2019-05-12 18:43:18'),(5,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',82.25,'2019-05-12 22:40:13'),(6,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',32.75,'2019-05-12 22:49:23'),(7,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',24.85,'2019-05-12 23:01:14'),(8,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',404.10,'2019-05-12 23:01:55'),(9,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',49.50,'2019-05-12 23:02:16'),(10,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',198.00,'2019-05-13 00:52:28'),(11,'659ea42c-42e5-4723-ba72-486d3e4d49f8',NULL,'VincentFake','09478888','Hanoi','vincentfake@gmail',1136.30,'2019-05-13 01:12:11'),(12,'629d25fc-614c-4585-a685-b40bd78e3223',NULL,'','','','',24.85,'2019-05-13 01:27:21'),(13,'629d25fc-614c-4585-a685-b40bd78e3223',NULL,'','','','',372.75,'2019-05-13 01:28:35'),(14,'1d5cd889-a143-4e42-b507-3906d09c5d02',NULL,'','','','',32.75,'2019-05-17 17:34:47'),(15,'0e5b220c-720e-4470-b52b-196d359b1fba',NULL,'','','','',90850.00,'2019-05-18 22:55:11'),(16,'bdb3f4ea-4495-45f3-a37b-42faa30a7a92',NULL,'','','','',24.85,'2019-06-16 12:39:48'),(17,'bdb3f4ea-4495-45f3-a37b-42faa30a7a92',NULL,'','','','',32.75,'2019-06-16 12:41:34'),(18,'58cf3c1c-1a4e-42e5-86b9-57fe6a71bcd7',NULL,'','','','',19.75,'2019-06-22 03:47:35'),(19,'58cf3c1c-1a4e-42e5-86b9-57fe6a71bcd7',NULL,'Ano','','','',290.45,'2019-06-22 03:51:15');
/*!40000 ALTER TABLE `dbo_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-22  4:23:19
