/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.8-log : Database - metro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`metro` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `metro`;

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `trip_id` int(11) DEFAULT NULL,
  `rate_id` int(11) DEFAULT NULL,
  `no_seats` int(15) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`booking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `booking` */

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `feedback` varchar(50) DEFAULT NULL,
  `date_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `user_type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`user_type`) values (1,'admin','admin','admin'),(3,'master','master','master'),(4,'staff','staff','staff'),(5,'user','u','user');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `sender_type` varchar(20) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `receiver_type` varchar(20) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date_time` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `message` */

insert  into `message`(`message_id`,`sender_id`,`sender_type`,`receiver_id`,`receiver_type`,`message`,`reply`,`date_time`) values (1,5,'user',3,'master','hloo','hai','2-2-2021'),(2,5,'user',4,'staff','hai','hai how are you','6-7-2001');

/*Table structure for table `rate` */

DROP TABLE IF EXISTS `rate`;

CREATE TABLE `rate` (
  `rate_id` int(11) NOT NULL AUTO_INCREMENT,
  `min_charge` varchar(30) DEFAULT NULL,
  `stage_charge` varchar(30) DEFAULT NULL,
  `last_updated_on` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`rate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `rate` */

insert  into `rate`(`rate_id`,`min_charge`,`stage_charge`,`last_updated_on`) values (1,'500','4','6'),(4,'100','500','250'),(5,'200','500','250');

/*Table structure for table `staffs` */

DROP TABLE IF EXISTS `staffs`;

CREATE TABLE `staffs` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `station_id` int(11) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `phone` int(12) DEFAULT NULL,
  `place` varchar(30) DEFAULT NULL,
  `qualification` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `staffs` */

insert  into `staffs`(`staff_id`,`login_id`,`station_id`,`first_name`,`last_name`,`email`,`address`,`phone`,`place`,`qualification`) values (6,4,1,'fdsfsfgs','krishna','shyam@gmail.com','shivalayam road',2147483647,'palakkad','mca'),(7,17,1,'sreenath','kamath','sreenathskamath@gmail.com','thusiparambil house',2147483647,'dafsafsa','sadsa');

/*Table structure for table `station_masters` */

DROP TABLE IF EXISTS `station_masters`;

CREATE TABLE `station_masters` (
  `master_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `station_id` int(11) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `phone` int(12) DEFAULT NULL,
  `place` varchar(30) DEFAULT NULL,
  `qualification` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `station_masters` */

insert  into `station_masters`(`master_id`,`login_id`,`station_id`,`first_name`,`last_name`,`email`,`address`,`phone`,`place`,`qualification`) values (1,7,1,'shyam','krishna','shyam@gmail.com','shivalayam road',2147483647,'4444','bca'),(2,8,1,'sreenath','kamath','sreenathskamath@gmail.com','thusiparambil house',2147483647,'22222','mca'),(3,9,1,'ramu','kanfdsf','ramanand123@gmail.com','alungal house',2147483647,'888888','+2'),(4,6,1,'vignesh','krishna','vignesh@gmail.com','cherai',2147483647,'','bca'),(5,7,1,'vignesh','krishna','vignesh@gmail.com','cherai',2147483647,'22222','+2'),(6,8,1,'reshma','kamath','renuka1234@gmail.com','shivalayam road',2147483647,'reshma','bca'),(7,9,1,'shyam','krishna','shyam@gmail.com','shivalayam road',2147483647,'','bca'),(8,10,1,'','','','',0,'','');

/*Table structure for table `stations` */

DROP TABLE IF EXISTS `stations`;

CREATE TABLE `stations` (
  `station_id` int(11) NOT NULL AUTO_INCREMENT,
  `station_name` varchar(30) DEFAULT NULL,
  `phone` int(12) DEFAULT NULL,
  `place` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `stations` */

insert  into `stations`(`station_id`,`station_name`,`phone`,`place`) values (1,'town hall',7869869,'town hall'),(2,'kaloor',34325,'sddsafdsg');

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `train_id` int(11) DEFAULT NULL,
  `current_station_id` int(11) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `status` */

insert  into `status`(`status_id`,`train_id`,`current_station_id`,`status`) values (1,1,0,'bgdfhfd'),(2,1,2,'cvcv'),(3,1,2,'vv'),(4,1,2,'bgdfhfd'),(5,1,1,'bgdfhfd'),(6,1,1,'bgdfhfd'),(7,1,2,'dgf'),(8,1,2,'renuka'),(9,1,1,'dfdssd'),(10,1,1,'dfdssd'),(11,3,1,'rr');

/*Table structure for table `timings` */

DROP TABLE IF EXISTS `timings`;

CREATE TABLE `timings` (
  `timing_id` int(11) NOT NULL AUTO_INCREMENT,
  `trip_id` int(11) DEFAULT NULL,
  `station_id` int(11) DEFAULT NULL,
  `arrival_time` varchar(15) DEFAULT NULL,
  `departure_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`timing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `timings` */

/*Table structure for table `trains` */

DROP TABLE IF EXISTS `trains`;

CREATE TABLE `trains` (
  `train_id` int(11) NOT NULL AUTO_INCREMENT,
  `train_name` varchar(30) DEFAULT NULL,
  `compartments` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`train_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `trains` */

insert  into `trains`(`train_id`,`train_name`,`compartments`) values (1,'ganga','4'),(3,'yamuna','s4'),(4,'bdffdd','dvgvds'),(5,'bdffdd','4');

/*Table structure for table `trip` */

DROP TABLE IF EXISTS `trip`;

CREATE TABLE `trip` (
  `trip_id` int(11) NOT NULL AUTO_INCREMENT,
  `train_id` int(11) DEFAULT NULL,
  `from_station_id` int(11) DEFAULT NULL,
  `to_station_id` int(11) DEFAULT NULL,
  `start_time` varchar(15) DEFAULT NULL,
  `end_time` varchar(15) DEFAULT NULL,
  `towards` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`trip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `trip` */

insert  into `trip`(`trip_id`,`train_id`,`from_station_id`,`to_station_id`,`start_time`,`end_time`,`towards`) values (1,1,2,2,'13:37','13:37','kakkanad'),(2,1,1,1,'12:39','13:39','kakkanad');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `phone` int(12) DEFAULT NULL,
  `place` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`user_id`,`login_id`,`first_name`,`last_name`,`email`,`address`,`phone`,`place`) values (1,5,'user','u','u','wert',987654321,'werty');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
