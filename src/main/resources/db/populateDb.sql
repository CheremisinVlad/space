DELETE FROM content;
DELETE FROM directories;
DELETE FROM users;


ALTER SEQUENCE content_seq RESTART WITH 100000;
ALTER SEQUENCE directories_seq RESTART WITH 10000;
ALTER SEQUENCE users_seq RESTART WITH 1000;


INSERT INTO users (name, emailMessage, password) VALUES
('vlad','cheremisinvladw@gmail.com','password'),
('vasya','vasya@mail.com','yavasya');

INSERT INTO directories (user_id,description,parent_directory_id,row_position,column_position) VALUES
(1000,'first directory for vlad',null,1,1),
(1000,'second directory for vlad',null,1,2),
(1000,'third directory for vlad',null,2,1),
(1000,'fourth directory for vlad',null,2,2),
(1001,'first directory for vasya',null,1,1),
(1001,'second directory for vasya',null,1,2),
(1001,'third directory for vasya',null,2,1),
(1001,'fourth directory for vasya',null,2,2);


INSERT INTO content (directory_id,comment,header,source) VALUES
  (10000,'it is first content for first directory','first test header','www.first-test-content.ru'),
  (10000,'it is second content for first directory','second test header','www.second-test-content.ru'),
  (10001,'it is first content for second directory','first test header','www.first-test-content.ru'),
  (10001,'it is second content for second directory','second test header','www.second-test-content.ru');