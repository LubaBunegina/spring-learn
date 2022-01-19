insert into genres (id, `name`) values (1, 'genres1');
insert into genres (id, `name`) values (2, 'genres2');

insert into authors (id, `name`) values (1, 'author1');
insert into authors (id, `name`) values (2, 'author2');

insert into books (id, `name`, genre, author) values (1, 'book1', 1, 1);
insert into books (id, `name`, genre, author) values (2, 'book2', 1, 2);
insert into books (id, `name`, genre, author) values (3, 'book3', 2, 2);
insert into books (id, `name`, genre, author) values (4, 'book4', 2, 1);

insert into users (id, `username`, `password`, `enabled`) values (1, 'user1', '$2a$10$LT.gAO3TiKHPsKJASr9GYOdVE0YlFE78LQSzXvqiJY0x9eVVLn3pS', 1);
insert into users (id, `username`, `password`, `enabled`) values (2, 'user2', '$2a$10$vjvp2sd0KR9hbgASUTULWOxBQghsE7HtveCCLmk3l5EKXsGUWclm2', 1);

insert into authorities (id, `username`, `authority`) values (1, 'user1', 'User');
insert into authorities (id, `username`, `authority`) values (2, 'user2', 'User');