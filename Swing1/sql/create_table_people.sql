CREATE TABLE `people` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `occupation` varchar(100) NOT NULL,
  `age` enum('CHILD','ADULT','SENIOR') NOT NULL,
  `employment_status` enum('EMPLOYED','SELF_EMPLOYED','UNEMPLOYED','OTHER') NOT NULL,
  `tax_id` varchar(100) DEFAULT NULL,
  `us_citizen` tinyint(1) DEFAULT NULL,
  `gender` enum('FEMALE','MALE') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;