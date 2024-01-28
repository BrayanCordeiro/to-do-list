CREATE TABLE `todo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `conclusion_date` date DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `completed` bit(1) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `status` enum('BACKLOG','DOING','DONE') DEFAULT NULL,
  `tittle` varchar(120) NOT NULL,
  `conclusion_forecast` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE user_todo (
  todo_id bigint NOT NULL,
  user_id bigint NOT NULL,
  PRIMARY KEY (todo_id,user_id),
  KEY FK_user_todo_user (user_id),
  CONSTRAINT FK_user_todo_user FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT FK_user_todo_todo FOREIGN KEY (todo_id) REFERENCES todo (id)
);

