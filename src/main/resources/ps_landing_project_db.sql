-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.19 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ps_landing_project_db
CREATE DATABASE IF NOT EXISTS `ps_landing_project_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ps_landing_project_db`;

-- Dumping structure for table ps_landing_project_db.catalogs
CREATE TABLE IF NOT EXISTS `catalogs` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` varchar(100) NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ps_landing_project_db.catalogs: ~0 rows (approximately)
/*!40000 ALTER TABLE `catalogs` DISABLE KEYS */;
/*!40000 ALTER TABLE `catalogs` ENABLE KEYS */;

-- Dumping structure for table ps_landing_project_db.modules
CREATE TABLE IF NOT EXISTS `modules` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` varchar(100) NOT NULL,
  `url` varchar(100) NOT NULL,
  `icon` varchar(100) NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  `creation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ps_landing_project_db.modules: ~0 rows (approximately)
/*!40000 ALTER TABLE `modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `modules` ENABLE KEYS */;

-- Dumping structure for table ps_landing_project_db.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(100) NOT NULL,
  `creation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ps_landing_project_db.roles: ~0 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table ps_landing_project_db.role_modules
CREATE TABLE IF NOT EXISTS `role_modules` (
  `id_role` int unsigned NOT NULL,
  `id_module` int unsigned NOT NULL,
  KEY `roles_to_role_modules_FK` (`id_role`),
  KEY `modules_to_role_modules_FK` (`id_module`),
  CONSTRAINT `modules_to_role_modules_FK` FOREIGN KEY (`id_module`) REFERENCES `modules` (`id`),
  CONSTRAINT `roles_to_role_modules_FK` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ps_landing_project_db.role_modules: ~0 rows (approximately)
/*!40000 ALTER TABLE `role_modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_modules` ENABLE KEYS */;

-- Dumping structure for table ps_landing_project_db.sub_catalogs
CREATE TABLE IF NOT EXISTS `sub_catalogs` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` varchar(100) NOT NULL,
  `parent` int unsigned NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sub_catalogs_parent_FK` (`parent`),
  CONSTRAINT `sub_catalogs_parent_FK` FOREIGN KEY (`parent`) REFERENCES `catalogs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ps_landing_project_db.sub_catalogs: ~0 rows (approximately)
/*!40000 ALTER TABLE `sub_catalogs` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_catalogs` ENABLE KEYS */;

-- Dumping structure for table ps_landing_project_db.sub_modules
CREATE TABLE IF NOT EXISTS `sub_modules` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` varchar(100) NOT NULL,
  `parent` int unsigned NOT NULL,
  `url` varchar(100) NOT NULL,
  `icon` varchar(100) NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  `creation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sub_modules_parent_FK` (`parent`),
  CONSTRAINT `sub_modules_parent_FK` FOREIGN KEY (`parent`) REFERENCES `modules` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ps_landing_project_db.sub_modules: ~0 rows (approximately)
/*!40000 ALTER TABLE `sub_modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_modules` ENABLE KEYS */;

-- Dumping structure for table ps_landing_project_db.system_parameters
CREATE TABLE IF NOT EXISTS `system_parameters` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `value` varchar(60) NOT NULL,
  `description` varchar(100) NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ps_landing_project_db.system_parameters: ~0 rows (approximately)
/*!40000 ALTER TABLE `system_parameters` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_parameters` ENABLE KEYS */;

-- Dumping structure for table ps_landing_project_db.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(255) NOT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` timestamp NULL DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `user_name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ps_landing_project_db.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table ps_landing_project_db.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `id_user` int unsigned NOT NULL,
  `id_role` int unsigned NOT NULL,
  KEY `id_user_FK` (`id_user`),
  KEY `id_role_FK` (`id_role`),
  CONSTRAINT `id_role_FK` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`),
  CONSTRAINT `id_user_FK` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table ps_landing_project_db.user_roles: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
