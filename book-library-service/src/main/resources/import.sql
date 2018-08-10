--DATABASE Booklibrary


--AUTHOR
INSERT INTO author(id, first_name, last_name) VALUES(1, 'Andrzej', 'Sapkowski');
INSERT INTO author(id, first_name, last_name) VALUES(2, 'Henryk', 'Sienkiewicz');
INSERT INTO author(id, first_name, last_name) VALUES(3, 'Adam', 'Mickiewicz');
INSERT INTO author(id, first_name, last_name) VALUES(4, 'Stephen', 'King');
INSERT INTO author(id, first_name, last_name) VALUES(5, 'Peter', 'V.Brett');


--CATEGORY
INSERT INTO category(id, name) VALUES(1, 'fantastyka');
INSERT INTO category(id, name) VALUES(2, 'historyczny');
INSERT INTO category(id, name) VALUES(3, 'dramat');
INSERT INTO category(id, name) VALUES(4, 'horror');
INSERT INTO category(id, name) VALUES(5, 'edukacyjny');


--PUBLISHER
INSERT INTO publisher(id, name) VALUES(1, 'SUPERNOWA');
INSERT INTO publisher(id, name) VALUES(2, 'GREG');
INSERT INTO publisher(id, name) VALUES(3, 'Fabryka Słów');
INSERT INTO publisher(id, name) VALUES(4, 'Prószyński i S-ka');


--BOOK
INSERT INTO book(id, title, description, author_id, category_id, publisher_id) VALUES(1, 'Wiedzmin', 'Pierwsze opowiadanie o wiedzminie Geralcie autorstwa Andrzeja Sapkowskiego', 1, 1, 1);
INSERT INTO book(id, title, description, author_id, category_id, publisher_id) VALUES(2, 'Krzyzacy', 'Powiesc historyczna Henryka Sienkiewicza', 2, 2, 2);
INSERT INTO book(id, title, description, author_id, category_id, publisher_id) VALUES(3, 'Dziady', 'Cykl dramatow romantycznych Adama Mickiewicza publikowany w latach 1823-1860', 3, 3, 2);
INSERT INTO book(id, title, description, author_id, category_id, publisher_id) VALUES(4, 'To', 'Powieść Stephena Kinga wydana w 1986 roku', 4, 4, 4);
INSERT INTO book(id, title, description, author_id, category_id, publisher_id) VALUES(5, 'Malowany czlowiek', 'Pierwszy z pięciu tomów cyklu demonicznego amerykańskiego pisarza Petera V. Bretta', 5, 1, 3);

SELECT SETVAL('author_id_seq', (SELECT MAX(id) + 1 FROM author), FALSE);
SELECT SETVAL('category_id_seq', (SELECT MAX(id) + 1 FROM category), FALSE);
SELECT SETVAL('publisher_id_seq', (SELECT MAX(id) + 1 FROM publisher), FALSE);
SELECT SETVAL('book_id_seq', (SELECT MAX(id) + 1 FROM book), FALSE);


--SELECT b.*, a.*, c.*, p.*
--FROM book b, author a, category c, publisher p
--WHERE b.author_id = a.id AND b.category_id = c.id AND b.publisher_id = p.id;