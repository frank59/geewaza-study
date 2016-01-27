DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`firstname` varchar(100) NOT NULL,
	`lastname` varchar(100) NOT NULL,
	`phone` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname1','lname1','(000)000-0000','name1@gmail.com');
INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname2','lname2','(000)000-0000','name2@gmail.com');
INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname3','lname3','(000)000-0000','name3@gmail.com');
INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname4','lname4','(000)000-0000','name4@gmail.com');
INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname5','lname5','(000)000-0000','name5@gmail.com');
INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname6','lname6','(000)000-0000','name6@gmail.com');
INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname7','lname7','(000)000-0000','name7@gmail.com');
INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname8','lname8','(000)000-0000','name8@gmail.com');
INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname9','lname9','(000)000-0000','name9@gmail.com');
INSERT INTO users (firstname, lastname, phone, email) VALUES ('fname10','lname10','(000)000-0000','name10@gmail.com');
