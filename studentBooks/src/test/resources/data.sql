insert into genres (id, `name`) values (1, 'genreTest');

insert into authors (id, `name`) values (1, 'authorTest');

insert into books (id, `name`, genre, author) values (1, 'bookTest', 1, 1);

insert into users (id, `username`, `password`, `enabled`) values (1, 'user1', '$2a$10$LT.gAO3TiKHPsKJASr9GYOdVE0YlFE78LQSzXvqiJY0x9eVVLn3pS', 1);

insert into authorities (id, `username`, `authority`) values (1, 'user1', 'User');

