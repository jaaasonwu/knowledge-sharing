DROP SCHEMA IF EXISTS knowledgeSharing;
CREATE SCHEMA knowledgeSharing DEFAULT CHARACTER SET utf8;
USE knowledgeSharing;
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
  id      INT(11)     UNSIGNED NOT NULL AUTO_INCREMENT,
  name    VARCHAR(64) UNIQUE NOT NULL DEFAULT '',
  password VARCHAR(128)         NOT NULL DEFAULT '',
  salt     VARCHAR(32)          NOT NULL DEFAULT '',
  head_url VARCHAR(256)         NOT NULL DEFAULT '',
  PRIMARY KEY (id),
  UNIQUE KEY name (name)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS question;
CREATE TABLE `question`
(
  id            INT(11) UNSIGNED AUTO_INCREMENT,
  title         VARCHAR(255) NOT NULL DEFAULT '',
  content      TEXT,
  user_id      INT          NOT NULL,
  create_date   DATETIME     NOT NULL,
  comment_count INT          NOT NULL,
  PRIMARY KEY (id),
  INDEX date_index (create_date ASC)
)ENGINE=InnoDB
  DEFAULT CHARSET=utf8;

