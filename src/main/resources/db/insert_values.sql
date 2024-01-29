INSERT INTO `user` (`id`, `username`) VALUES
(1, 'user1'),
(2, 'user2'),
(3, 'user3');

INSERT INTO `todo` (`id`, `conclusion_date`, `creation_date`, `completed`, `description`, `status`, `tittle`, `conclusion_forecast`) VALUES
(1, '2024-01-28', '2024-01-01', 1, 'Task 1 description', 'DONE', 'Task 1', '2024-02-01'),
(2, NULL, '2024-01-05', 0, 'Task 2 description', 'BACKLOG', 'Task 2', '2024-01-15'),
(3, NULL, '2024-01-10', 0, 'Task 3 description', 'DOING', 'Task 3', NULL),
(4, '2024-01-20', '2024-01-15', 1, 'Task 4 description', 'DONE', 'Task 4', '2024-01-25');

INSERT INTO `user_todo` (`todo_id`, `user_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 3),
(4, 2),
(4, 3);
