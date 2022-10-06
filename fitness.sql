CREATE DATABASE  IF NOT EXISTS `fitness` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fitness`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: fitness
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `iDKorisnik` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `prezime` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `visina` double DEFAULT NULL,
  PRIMARY KEY (`iDKorisnik`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'Marija','Tripkovic','maki@gmail.com','admin','admin',172),(2,'Leon','Vilovic','vilovic.leon@gmail.com','Leoniss','123',174.4),(8,'Dusan','Totic','dusantotic@gmail.com','dusan','dusan',184);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planvezbanja`
--

DROP TABLE IF EXISTS `planvezbanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planvezbanja` (
  `iDPlanVezbanja` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `iDKorisnik` int DEFAULT NULL,
  PRIMARY KEY (`iDPlanVezbanja`),
  KEY `pripada_korisnku_idx` (`iDKorisnik`),
  CONSTRAINT `pripada_korisnku` FOREIGN KEY (`iDKorisnik`) REFERENCES `korisnik` (`iDKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planvezbanja`
--

LOCK TABLES `planvezbanja` WRITE;
/*!40000 ALTER TABLE `planvezbanja` DISABLE KEYS */;
INSERT INTO `planvezbanja` VALUES (1,'Plan za zagrejavanje',1),(4,'Plan za kondiciju',1),(11,'Plan za snagu',1),(21,'Leonov plan',2),(35,'Plan 4',1);
/*!40000 ALTER TABLE `planvezbanja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stavkaplanavezbanja`
--

DROP TABLE IF EXISTS `stavkaplanavezbanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stavkaplanavezbanja` (
  `iDPlanVezbanja` int DEFAULT NULL,
  `iDVezba` int DEFAULT NULL,
  `redniBroj` int DEFAULT NULL,
  KEY `sadrzi_vezbu_idx` (`iDVezba`),
  KEY `pripada_palnu_idx` (`iDPlanVezbanja`),
  CONSTRAINT `pripada_palnu` FOREIGN KEY (`iDPlanVezbanja`) REFERENCES `planvezbanja` (`iDPlanVezbanja`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sadrzi_vezbu` FOREIGN KEY (`iDVezba`) REFERENCES `vezba` (`iDVezba`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stavkaplanavezbanja`
--

LOCK TABLES `stavkaplanavezbanja` WRITE;
/*!40000 ALTER TABLE `stavkaplanavezbanja` DISABLE KEYS */;
INSERT INTO `stavkaplanavezbanja` VALUES (1,6,1),(1,10,4),(4,7,1),(4,6,2),(4,9,3),(4,7,4),(4,8,5),(11,17,1),(11,8,2),(35,8,1),(35,9,3);
/*!40000 ALTER TABLE `stavkaplanavezbanja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tezinanadan`
--

DROP TABLE IF EXISTS `tezinanadan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tezinanadan` (
  `iDTezinaNaDan` int NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `tezina` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `iDkorisnik` int DEFAULT NULL,
  PRIMARY KEY (`iDTezinaNaDan`),
  KEY `pripada_korisniku_idx` (`iDkorisnik`),
  CONSTRAINT `pripada_korisniku` FOREIGN KEY (`iDkorisnik`) REFERENCES `korisnik` (`iDKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tezinanadan`
--

LOCK TABLES `tezinanadan` WRITE;
/*!40000 ALTER TABLE `tezinanadan` DISABLE KEYS */;
INSERT INTO `tezinanadan` VALUES (25,'2020-01-01','71.0',2),(26,'2019-01-01','70.0',2),(27,'2018-01-01','69.0',2),(34,'2020-01-02','68.0',1),(36,'2020-09-10','69.9',1),(37,'2020-09-15','93.0',8),(39,'2021-07-28','71.5',2);
/*!40000 ALTER TABLE `tezinanadan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vezba`
--

DROP TABLE IF EXISTS `vezba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vezba` (
  `iDVezba` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `opis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `moguceSamoUTeretani` tinyint(1) DEFAULT NULL,
  `putDoFajla` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tezinaVezbe` enum('Laka','Srednja','Teska') CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'Laka',
  PRIMARY KEY (`iDVezba`),
  UNIQUE KEY `naziv_UNIQUE` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vezba`
--

LOCK TABLES `vezba` WRITE;
/*!40000 ALTER TABLE `vezba` DISABLE KEYS */;
INSERT INTO `vezba` VALUES (6,'Zgibovi','',0,'','Teska'),(7,'Sklekovi','Sklekovi su vežba koja primarno angažuje grudne mišiće',0,'files\\pushups.jpg','Srednja'),(8,'Propadanja','',0,'','Teska'),(9,'Trcanje','',0,'files\\run.jpg','Laka'),(10,'Čučnjevi','',0,'files\\imeFajla.jpg','Laka'),(17,'Dizanje tegova','',0,'files\\weightlifting.png','Teska');
/*!40000 ALTER TABLE `vezba` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-09 21:16:24
