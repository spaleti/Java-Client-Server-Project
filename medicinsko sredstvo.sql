/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.33 : Database - medicinsko sredstvo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`medicinsko sredstvo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `medicinsko sredstvo`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `administratorID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `surname` varchar(25) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`administratorID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `administrator` */

insert  into `administrator`(`administratorID`,`name`,`surname`,`username`,`password`) values 
(1,'Dusan','Spasic','ds','ds'),
(2,'Milos','Milosevic','mm','mm'),
(3,'Nikola','Nikolic','nn','nn');

/*Table structure for table `bolnica` */

DROP TABLE IF EXISTS `bolnica`;

CREATE TABLE `bolnica` (
  `bolnicaID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nazivBolnice` varchar(30) NOT NULL,
  `ovlascenoLice` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `telefon` varchar(30) NOT NULL,
  PRIMARY KEY (`bolnicaID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bolnica` */

insert  into `bolnica`(`bolnicaID`,`nazivBolnice`,`ovlascenoLice`,`email`,`telefon`) values 
(1,'Medical centar','Petar petrovic','petar@gmail.com','064123456'),
(2,'Bolnica zutic','Bojana Bojanic','office@zutic.rs','0110123123'),
(3,'Bolnica MSB','Branko Brankovic','office@msb.rs','011789456'),
(4,'Bolnica Una','Marijana Petrovic','office@una.rs','011555333'),
(7,'Bolnica Zemun','Milos Milosevic','office@zemun.rs','011999111');

/*Table structure for table `kategorijamedicinskogsredstva` */

DROP TABLE IF EXISTS `kategorijamedicinskogsredstva`;

CREATE TABLE `kategorijamedicinskogsredstva` (
  `kategorijaID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nazivKategorije` varchar(100) NOT NULL,
  PRIMARY KEY (`kategorijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `kategorijamedicinskogsredstva` */

insert  into `kategorijamedicinskogsredstva`(`kategorijaID`,`nazivKategorije`) values 
(1,'Gaza'),
(2,'Vata'),
(3,'Zavoj'),
(4,'Lek');

/*Table structure for table `medicinskosredstvo` */

DROP TABLE IF EXISTS `medicinskosredstvo`;

CREATE TABLE `medicinskosredstvo` (
  `msID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nazivSredstva` varchar(50) NOT NULL,
  `opis` varchar(200) NOT NULL,
  `cena` decimal(10,2) NOT NULL,
  `kategorijaID` bigint unsigned NOT NULL,
  PRIMARY KEY (`msID`),
  KEY `fk_kategorija_id` (`kategorijaID`),
  CONSTRAINT `fk_kategorija_id` FOREIGN KEY (`kategorijaID`) REFERENCES `kategorijamedicinskogsredstva` (`kategorijaID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `medicinskosredstvo` */

insert  into `medicinskosredstvo`(`msID`,`nazivSredstva`,`opis`,`cena`,`kategorijaID`) values 
(1,'gaza 100x80cm','velika gaza',100.00,1),
(2,'gaza 10x80','mala gaza',50.00,1),
(3,'Gaza 1m','bas velika gaza',150.00,1),
(4,'Vata 1kg','bas velika vata',200.00,2),
(5,'Vata 50gr','malena vatica',50.00,2),
(6,'Kaliko zavoj 6x5','srednji zavoj',120.00,3),
(7,'kaliko zavoj 10x5','veliki zavoj',180.00,3),
(8,'Brufen 400mg','za glavobolju',300.00,4),
(9,'Paracetamol tablete','lek za temperaturu',350.00,4),
(10,'Analgin tablete','protiv bolova',400.00,4);

/*Table structure for table `porudzbina` */

DROP TABLE IF EXISTS `porudzbina`;

CREATE TABLE `porudzbina` (
  `porudzbinaID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `datumPorucivanja` datetime NOT NULL,
  `datumIsporuke` date NOT NULL,
  `ukupnaCena` decimal(10,2) NOT NULL,
  `bolnicaID` bigint unsigned NOT NULL,
  `administratorID` bigint unsigned NOT NULL,
  PRIMARY KEY (`porudzbinaID`),
  KEY `fk_bolnica_id` (`bolnicaID`),
  KEY `fk_admin_id` (`administratorID`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`administratorID`) REFERENCES `administrator` (`administratorID`),
  CONSTRAINT `fk_bolnica_id` FOREIGN KEY (`bolnicaID`) REFERENCES `bolnica` (`bolnicaID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `porudzbina` */

insert  into `porudzbina`(`porudzbinaID`,`datumPorucivanja`,`datumIsporuke`,`ukupnaCena`,`bolnicaID`,`administratorID`) values 
(3,'2024-09-01 21:13:34','2024-09-24',200.00,1,1),
(5,'2024-09-01 21:36:23','2024-09-24',200.00,1,1),
(6,'2024-09-02 18:18:42','2024-09-24',1450.00,4,1),
(7,'2024-09-02 18:19:20','2024-11-01',1200.00,3,1),
(8,'2024-09-03 20:33:29','2024-09-24',1660.00,2,1),
(10,'2024-09-13 00:30:58','2024-10-15',4700.00,7,1);

/*Table structure for table `stavkaporudzbine` */

DROP TABLE IF EXISTS `stavkaporudzbine`;

CREATE TABLE `stavkaporudzbine` (
  `porudzbinaID` bigint unsigned NOT NULL,
  `rbStavke` int NOT NULL,
  `kolicina` int NOT NULL,
  `cenaStavke` decimal(10,2) NOT NULL,
  `medicinskoSredstvoID` bigint unsigned NOT NULL,
  PRIMARY KEY (`porudzbinaID`,`rbStavke`),
  KEY `fk_ms_id` (`medicinskoSredstvoID`),
  CONSTRAINT `fk_ms_id` FOREIGN KEY (`medicinskoSredstvoID`) REFERENCES `medicinskosredstvo` (`msID`),
  CONSTRAINT `fk_porudzbina_id` FOREIGN KEY (`porudzbinaID`) REFERENCES `porudzbina` (`porudzbinaID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stavkaporudzbine` */

insert  into `stavkaporudzbine`(`porudzbinaID`,`rbStavke`,`kolicina`,`cenaStavke`,`medicinskoSredstvoID`) values 
(3,1,2,200.00,1),
(5,1,1,100.00,1),
(5,2,2,100.00,2),
(6,1,1,300.00,8),
(6,2,1,350.00,9),
(6,3,2,800.00,10),
(7,1,1,120.00,6),
(7,2,6,1080.00,7),
(8,1,5,500.00,1),
(8,2,1,200.00,4),
(8,3,3,360.00,6),
(8,4,2,600.00,8),
(10,1,10,1200.00,6),
(10,2,10,2000.00,4),
(10,3,15,1500.00,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
