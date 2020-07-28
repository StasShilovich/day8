create database books;
use books;
create table Book (
id INT NOT NULL AUTO_INCREMENT,
author VARCHAR(20) NOT NULL,
title VARCHAR(50) NOT NULL,
year INT NOT NULL,
price DECIMAL(5,2) NOT NULL,
deleted TINYINT NOT NULL,
PRIMARY KEY ( id )
);
INSERT Book(author,title,year,price,deleted) VALUES
('Ernest Hemingway','For Whom the Bell Tolls',1940 ,12.99,0),
('Albert Camus','The Plague',1947,12.49,0),
('Shashi Tharoor','The Great Indian Novel',1989,24.36,0),
('Paulo Coelho','The Devil and Miss Prym',2000,9.99,0),
('Patrick Suskin','Perfume: The Story of a Murderer',1985,10.47,0);