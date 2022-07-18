DROP DATABASE
IF EXISTS sbb;

CREATE DATABASE sbb;

CREATE TABLE Question (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL
);

INSERT INTO Question SET
create_date = NOW(),
`subject` = '질문 1',
content = '질문내용 1';

INSERT INTO Question SET
create_date = NOW(),
`subject` = '질문 2',
content = '질문내용 2';

INSERT INTO Question SET
create_date = NOW(),
`subject` = '질문 3',
content = '질문내용 3';