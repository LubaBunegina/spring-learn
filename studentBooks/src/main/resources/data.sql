insert into genres (id, `name`) values (1, 'genres1');
insert into genres (id, `name`) values (2, 'genres2');

insert into authors (id, `name`) values (1, 'author1');
insert into authors (id, `name`) values (2, 'author2');

insert into books (id, `name`, genre, author) values (1, 'book1', 1, 1);
insert into books (id, `name`, genre, author) values (2, 'book2', 1, 2);
insert into books (id, `name`, genre, author) values (3, 'book3', 2, 2);
insert into books (id, `name`, genre, author) values (4, 'book4', 2, 1);