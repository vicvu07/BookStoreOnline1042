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
-- Table structure for table `dbo_order_book`
--

DROP TABLE IF EXISTS `dbo_order_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dbo_order_book` (
  `order_book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`order_book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_order_book`
--

LOCK TABLES `dbo_order_book` WRITE;
/*!40000 ALTER TABLE `dbo_order_book` DISABLE KEYS */;
INSERT INTO `dbo_order_book` VALUES (1,2,1,4,131.00),(2,3,1,1,24.85),(3,1,1,1,49.50),(4,3,2,1,24.85),(5,2,2,1,32.75),(6,1,2,1,49.50),(7,3,3,1,24.85),(8,3,4,1,24.85),(9,2,5,1,32.75),(10,1,5,1,49.50),(11,2,6,1,32.75),(12,3,7,1,24.85),(13,2,8,1,32.75),(14,3,8,1,24.85),(15,1,8,7,346.50),(16,1,9,1,49.50),(17,1,10,4,198.00),(18,1,11,11,544.50),(19,2,11,12,393.00),(20,3,11,8,198.80),(21,3,12,1,24.85),(22,3,13,15,372.75),(23,2,14,1,32.75),(24,4,15,500,90850.00),(25,3,16,1,24.85),(26,2,17,1,32.75),(27,6,18,1,19.75),(28,2,19,1,32.75),(29,7,19,2,257.70);
/*!40000 ALTER TABLE `dbo_order_book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-22  4:23:20
