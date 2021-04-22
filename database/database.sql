-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.23 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table core.account
CREATE TABLE IF NOT EXISTS `account` (
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `user_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1 for normal user, 2 for PCO, 3 for critic and 4 for admin',
  `user_approved` tinyint(1) DEFAULT NULL,
  `country` varchar(45) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `birth_year` year NOT NULL,
  `zip_code` varchar(6) NOT NULL DEFAULT '',
  `phone_number` varchar(14) DEFAULT NULL,
  `production_company` int DEFAULT NULL,
  `token` char(88) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `FK_account_production_company` (`production_company`),
  CONSTRAINT `FK_account_production_company` FOREIGN KEY (`production_company`) REFERENCES `production_company` (`proco_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table core.account: ~2 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`username`, `password`, `email`, `user_type`, `user_approved`, `country`, `gender`, `first_name`, `last_name`, `birth_year`, `zip_code`, `phone_number`, `production_company`, `token`) VALUES
	('geralt', '$2a$12$U9XemjS0D0K4Jy.082f.0et5Gm6BzYJ9WMzSQ2Wi2cuijk5W2xjMm', 'geralt@rivia.com', 4, 1, 'Australia', 'Male', 'Geralt', 'Rivia', '1999', '190001', '9419060000', NULL, 'abc'),
	('pco', '$2a$12$AZKZ4E9D3Lld8HWKcIIIXuQbHQsW0xAxzRSuaG9ZrR69GzmxS6PLm', 'pco@rivia.com', 2, 0, 'Australia', 'Male', 'PCO', 'Rivia', '1999', '190001', '9419060000', 7, '');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table core.credits_roll
CREATE TABLE IF NOT EXISTS `credits_roll` (
  `person_id` int NOT NULL,
  `role` varchar(45) NOT NULL,
  `show_id` int NOT NULL,
  `start_year` int NOT NULL,
  `end_year` int DEFAULT '0',
  `character_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`show_id`,`person_id`),
  KEY `fk_person_idx` (`person_id`),
  CONSTRAINT `fk_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `fk_show` FOREIGN KEY (`show_id`) REFERENCES `show` (`show_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table core.credits_roll: ~2 rows (approximately)
/*!40000 ALTER TABLE `credits_roll` DISABLE KEYS */;
INSERT INTO `credits_roll` (`person_id`, `role`, `show_id`, `start_year`, `end_year`, `character_name`) VALUES
	(6, 'Actor', 1, 2019, 0, 'Kylo Ren'),
	(11, 'Actress', 1, 2019, 0, 'Maz Kanata');
/*!40000 ALTER TABLE `credits_roll` ENABLE KEYS */;

-- Dumping structure for table core.genre
CREATE TABLE IF NOT EXISTS `genre` (
  `genre_id` int NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table core.genre: ~0 rows (approximately)
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` (`genre_id`, `genre_name`) VALUES
	(1, 'Drama'),
	(2, 'Crime'),
	(3, 'Comedy'),
	(4, 'Thriller'),
	(5, 'Anime'),
	(6, 'Action'),
	(7, 'Adventure'),
	(8, 'History'),
	(9, 'SciFi');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;

-- Dumping structure for table core.person
CREATE TABLE IF NOT EXISTS `person` (
  `person_id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `role` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  `bio` varchar(1000) NOT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table core.person: ~12 rows (approximately)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`person_id`, `fullname`, `role`, `birthdate`, `bio`) VALUES
	(1, 'Robert De Niro', 'Actor', '1943-08-17', 'One of the greatest actors of all time, Robert De Niro was born on August 17, 1943 in Manhattan, New York City, to artists Virginia (Admiral) and Robert De Niro Sr. His paternal grandfather was of Italian descent, and his other ancestry is Irish, English, Dutch, German, and French.'),
	(2, 'Robert Pattinson', 'Actor', '1986-05-13', 'Robert Douglas Thomas Pattinson was born May 13, 1986 in London, England, to Richard Pattinson, a car dealer importing vintage cars, and Clare Pattinson (née Charlton), who worked as a booker at a model agency. He grew up in Barnes, southwest London with two older sisters and attended Tower House'),
	(3, 'Claire Foy', 'Actress', '1984-04-16', 'Claire Elizabeth Foy (born 16 April 1984) is an English actress. She studied acting at the Liverpool John Moores University and the Oxford School of Drama and made her screen debut in the pilot of the supernatural comedy series Being Human, in 2008.'),
	(4, 'Olivia Colman', 'Actress', '1974-01-30', 'Olivia Colman was born on January 30, 1974 in Norwich, Norfolk, England as Sarah Caroline Olivia Colman. She is an actress, known for The Favourite (2018), Tyrannosaur (2011) and The Lobster (2015). She has been married to Ed Sinclair since August 2001. They have three children.'),
	(5, 'Troy Baker', 'Actor', '1976-04-01', 'Troy Baker was born on April 1, 1976 in Dallas, Texas, USA as Troy Edward Baker. He is known for his work on The Last of Us (2013), BioShock Infinite (2013) and Batman: Arkham Knight (2015). He has been married to Pamela Walworth since October 13, 2012. They have one child.'),
	(6, 'Adam Driver', 'Actor', '1983-11-03', 'Adam Douglas Driver was born in San Diego, California. His mother, Nancy (Needham) Wright, is a paralegal from Mishawaka, Indiana, and his father, Joe Douglas Driver, who has deep roots in the American South, is from Little Rock, Arkansas.'),
	(7, 'Chadwick Boseman', 'Actor, Producer, Writer', '1977-11-29', 'Chadwick Boseman is an American actor. He is known for his portrayal of T\'Challa / Black Panther in the Marvel Cinematic Universe (since 2016), particularly in Black Panther (2018), and for his starring roles in as Jackie Robinson in 42 (2013), James Brown in Get on Up (2014), and Thurgood Marshall in Marshall (2017). '),
	(8, 'Gal Gadot', 'Actress, Producer', '1985-04-30', 'Gal Gadot is an Israeli actress, singer, martial artist, and model. She was born in Rosh Ha\'ayin, Israel, to a Jewish family. Her parents are Irit, a teacher, and Michael, an engineer, who is a sixth-generation Israeli. She served in the IDF for two years, and won the Miss Israel title in 2004.'),
	(9, 'Meryl Streep', 'Actress', '1949-06-22', 'Considered by many critics to be the greatest living actress, Meryl Streep has been nominated for the Academy Award an astonishing 21 times, and has won it three times. Meryl was born Mary Louise Streep in 1949 in Summit, New Jersey, to Mary Wolf (Wilkinson), a commercial artist, and Harry William Streep, Jr., a pharmaceutical executive.'),
	(10, 'Cate Blanchett', 'Actress', '1969-05-14', 'Cate Blanchett was born on May 14, 1969 in Melbourne, Victoria, Australia, to June (Gamble), an Australian teacher and property developer, and Robert DeWitt Blanchett, Jr., an American advertising executive, originally from Texas.'),
	(11, 'Lupita Nyong\'o', 'Actress, Producer', '1983-03-01', 'Lupita Amondi Nyong\'o was born March 1, 1983 in Mexico City, Mexico, to Kenyan parents, Dorothy Ogada Buyu and Peter Anyang\' Nyong\'o. Her father, a senator, was then a visiting lecturer in political science. She was raised in Kenya. At age 16, her parents sent her back to Mexico for seven months to learn Spanish.'),
	(12, 'Zoe Kravitz', 'Actress, Producer', '1988-12-01', 'Zoe Isabella Kravitz, the daughter of singer/actor Lenny Kravitz and actress Lisa Bonet, was born on December 1, 1988 in Los Angeles, California. She is of half African-American (from her father\'s mother and her mother\'s father) and half Ashkenazi Jewish (from her father\'s father and her mother\'s mother) descent.');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

-- Dumping structure for table core.production_company
CREATE TABLE IF NOT EXISTS `production_company` (
  `proco_id` int NOT NULL AUTO_INCREMENT,
  `proco_name` varchar(45) NOT NULL,
  PRIMARY KEY (`proco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table core.production_company: ~11 rows (approximately)
/*!40000 ALTER TABLE `production_company` DISABLE KEYS */;
INSERT INTO `production_company` (`proco_id`, `proco_name`) VALUES
	(1, 'Universal Pictures'),
	(2, 'Paramount Pictures'),
	(3, '20th Century Fox'),
	(4, 'Warner Bros.'),
	(5, 'DreamWorks Pictures'),
	(6, 'Metro-Goldwyn-Meyer'),
	(7, 'Miramax'),
	(8, 'Columbia Pictures'),
	(9, 'Walt Disney Pictures'),
	(10, 'Sony Pictures'),
	(11, 'New Line Cinema');
/*!40000 ALTER TABLE `production_company` ENABLE KEYS */;

-- Dumping structure for table core.show
CREATE TABLE IF NOT EXISTS `show` (
  `show_id` int NOT NULL AUTO_INCREMENT,
  `show_title` varchar(45) NOT NULL,
  `genre` int NOT NULL DEFAULT '0',
  `length` decimal(3,2) NOT NULL,
  `type` enum('MOVIE','SERIES') NOT NULL,
  `proco_id` int NOT NULL,
  `year` int NOT NULL DEFAULT '2020',
  `added_on` varchar(50) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`show_id`) USING BTREE,
  KEY `fk_proco_idx` (`proco_id`),
  KEY `fk_genre` (`genre`),
  CONSTRAINT `fk_genre` FOREIGN KEY (`genre`) REFERENCES `genre` (`genre_id`),
  CONSTRAINT `fk_proco` FOREIGN KEY (`proco_id`) REFERENCES `production_company` (`proco_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table core.show: ~0 rows (approximately)
/*!40000 ALTER TABLE `show` DISABLE KEYS */;
INSERT INTO `show` (`show_id`, `show_title`, `genre`, `length`, `type`, `proco_id`, `year`, `added_on`, `status`) VALUES
	(1, 'Star Wars: Episode IX - The Rise of Skywalker', 6, 2.22, 'SERIES', 9, 2019, NULL, NULL),
	(4, 'Witcher', 3, 1.30, 'SERIES', 10, 2020, '1619016781', 0),
	(5, 'Witcher', 3, 1.30, 'SERIES', 10, 2020, '1619016782', 0);
/*!40000 ALTER TABLE `show` ENABLE KEYS */;

-- Dumping structure for table core.user_review
CREATE TABLE IF NOT EXISTS `user_review` (
  `reviewId` int NOT NULL AUTO_INCREMENT,
  `show_id` int NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `rating` int NOT NULL DEFAULT '3',
  `review` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `fk_showid_idx` (`show_id`),
  KEY `fk_username_idx` (`user_id`),
  CONSTRAINT `fk_showid` FOREIGN KEY (`show_id`) REFERENCES `show` (`show_id`),
  CONSTRAINT `fk_username` FOREIGN KEY (`user_id`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table core.user_review: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_review` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
