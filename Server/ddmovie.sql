# SQL-Front 5.1  (Build 4.16)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: ddmovie
# ------------------------------------------------------
# Server version 5.0.22-community-nt

#
# Source for table collection
#

DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `movieCollection` bigint(20) NOT NULL,
  `whoCollect` bigint(20) NOT NULL,
  PRIMARY KEY  (`movieCollection`,`whoCollect`),
  KEY `FK9835AE9E271AF184` (`movieCollection`),
  KEY `FK9835AE9E6C5E4631` (`whoCollect`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table collection
#

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES (1,26);
INSERT INTO `collection` VALUES (1,28);
INSERT INTO `collection` VALUES (1,32);
INSERT INTO `collection` VALUES (3,24);
INSERT INTO `collection` VALUES (3,27);
INSERT INTO `collection` VALUES (3,33);
INSERT INTO `collection` VALUES (8,23);
INSERT INTO `collection` VALUES (9,31);
INSERT INTO `collection` VALUES (11,31);
INSERT INTO `collection` VALUES (11,32);
INSERT INTO `collection` VALUES (12,31);
INSERT INTO `collection` VALUES (13,31);
INSERT INTO `collection` VALUES (14,31);
INSERT INTO `collection` VALUES (15,23);
INSERT INTO `collection` VALUES (15,33);
INSERT INTO `collection` VALUES (16,31);
INSERT INTO `collection` VALUES (17,31);
INSERT INTO `collection` VALUES (20,25);
INSERT INTO `collection` VALUES (25,25);
INSERT INTO `collection` VALUES (25,27);
INSERT INTO `collection` VALUES (25,31);
INSERT INTO `collection` VALUES (26,31);
INSERT INTO `collection` VALUES (34,29);
INSERT INTO `collection` VALUES (40,23);
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table discusses
#

DROP TABLE IF EXISTS `discusses`;
CREATE TABLE `discusses` (
  `id` bigint(20) NOT NULL auto_increment,
  `whoDiss` bigint(20) default NULL,
  `dissTo` varchar(30) default NULL,
  `indexOfFreshNews` bigint(20) default NULL,
  `content` varchar(100) default NULL,
  `timestamp` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table discusses
#

LOCK TABLES `discusses` WRITE;
/*!40000 ALTER TABLE `discusses` DISABLE KEYS */;
INSERT INTO `discusses` VALUES (14,33,'FilmReview',33,'dddd',1285157750375);
INSERT INTO `discusses` VALUES (15,33,'FilmReview',36,'dddd',1285157750421);
INSERT INTO `discusses` VALUES (16,33,'FilmReview',38,'dddd',1285157750421);
INSERT INTO `discusses` VALUES (17,33,'FilmReview',41,'dddd',1285157750437);
INSERT INTO `discusses` VALUES (18,31,'UserGibberish',33,'评论我自己的心情',1285214349609);
INSERT INTO `discusses` VALUES (19,31,'UserGibberish',36,'评论我自己的心情',1285214349609);
INSERT INTO `discusses` VALUES (20,31,'UserGibberish',38,'评论我自己的心情',1285214349609);
INSERT INTO `discusses` VALUES (21,31,'UserGibberish',41,'评论我自己的心情',1285214349609);
INSERT INTO `discusses` VALUES (22,31,'UserGibberish',104,'评论我自己的心情',1285214349609);
INSERT INTO `discusses` VALUES (23,31,'UserGibberish',105,'评论我自己的心情',1285214349609);
INSERT INTO `discusses` VALUES (24,31,'UserGibberish',106,'评论我自己的心情',1285214349625);
INSERT INTO `discusses` VALUES (25,31,'UserGibberish',107,'评论我自己的心情',1285214349625);
INSERT INTO `discusses` VALUES (26,31,'UserGibberish',108,'评论我自己的心情',1285214349625);
INSERT INTO `discusses` VALUES (27,31,'UserGibberish',109,'评论我自己的心情',1285214349625);
INSERT INTO `discusses` VALUES (28,31,'UserGibberish',110,'评论我自己的心情',1285214349625);
INSERT INTO `discusses` VALUES (29,31,'UserGibberish',111,'评论我自己的心情',1285214349625);
INSERT INTO `discusses` VALUES (30,31,'UserGibberish',112,'评论我自己的心情',1285214349625);
INSERT INTO `discusses` VALUES (31,31,'UserGibberish',113,'评论我自己的心情',1285214349625);
INSERT INTO `discusses` VALUES (32,31,'UserGibberish',33,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (33,31,'UserGibberish',36,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (34,31,'UserGibberish',38,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (35,31,'UserGibberish',41,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (36,31,'UserGibberish',104,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (37,31,'UserGibberish',105,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (38,31,'UserGibberish',106,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (39,31,'UserGibberish',107,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (40,31,'UserGibberish',108,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (41,31,'UserGibberish',109,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (42,31,'UserGibberish',110,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (43,31,'UserGibberish',111,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (44,31,'UserGibberish',112,'xxx',1285214465468);
INSERT INTO `discusses` VALUES (45,31,'UserGibberish',113,'xxx',1285214465484);
INSERT INTO `discusses` VALUES (46,31,'UserGibberish',33,'fafasdfasdfasdasdfas',1285214893593);
INSERT INTO `discusses` VALUES (47,31,'UserGibberish',36,'fafasdfasdfasdasdfas',1285214893640);
INSERT INTO `discusses` VALUES (48,31,'UserGibberish',38,'fafasdfasdfasdasdfas',1285214893640);
INSERT INTO `discusses` VALUES (49,31,'UserGibberish',41,'fafasdfasdfasdasdfas',1285214893640);
INSERT INTO `discusses` VALUES (50,31,'UserGibberish',104,'fafasdfasdfasdasdfas',1285214893640);
INSERT INTO `discusses` VALUES (51,31,'UserGibberish',105,'fafasdfasdfasdasdfas',1285214893656);
INSERT INTO `discusses` VALUES (52,31,'UserGibberish',106,'fafasdfasdfasdasdfas',1285214893656);
INSERT INTO `discusses` VALUES (53,31,'UserGibberish',107,'fafasdfasdfasdasdfas',1285214893656);
INSERT INTO `discusses` VALUES (54,31,'UserGibberish',108,'fafasdfasdfasdasdfas',1285214893656);
INSERT INTO `discusses` VALUES (55,31,'UserGibberish',109,'fafasdfasdfasdasdfas',1285214893656);
INSERT INTO `discusses` VALUES (56,31,'UserGibberish',110,'fafasdfasdfasdasdfas',1285214893656);
INSERT INTO `discusses` VALUES (57,31,'UserGibberish',111,'fafasdfasdfasdasdfas',1285214893656);
INSERT INTO `discusses` VALUES (58,31,'UserGibberish',112,'fafasdfasdfasdasdfas',1285214893656);
INSERT INTO `discusses` VALUES (59,31,'UserGibberish',113,'fafasdfasdfasdasdfas',1285214893656);
INSERT INTO `discusses` VALUES (60,31,'UserGibberish',33,'额',1285214970140);
INSERT INTO `discusses` VALUES (61,31,'UserGibberish',36,'额',1285214970140);
INSERT INTO `discusses` VALUES (62,31,'UserGibberish',38,'额',1285214970140);
INSERT INTO `discusses` VALUES (63,31,'UserGibberish',41,'额',1285214970140);
INSERT INTO `discusses` VALUES (64,31,'UserGibberish',104,'额',1285214970140);
INSERT INTO `discusses` VALUES (65,31,'UserGibberish',105,'额',1285214970140);
INSERT INTO `discusses` VALUES (66,31,'UserGibberish',106,'额',1285214970156);
INSERT INTO `discusses` VALUES (67,31,'UserGibberish',107,'额',1285214970156);
INSERT INTO `discusses` VALUES (68,31,'UserGibberish',108,'额',1285214970156);
INSERT INTO `discusses` VALUES (69,31,'UserGibberish',109,'额',1285214970156);
INSERT INTO `discusses` VALUES (70,31,'UserGibberish',110,'额',1285214970156);
INSERT INTO `discusses` VALUES (71,31,'UserGibberish',111,'额',1285214970156);
INSERT INTO `discusses` VALUES (72,31,'UserGibberish',112,'额',1285214970156);
INSERT INTO `discusses` VALUES (73,31,'UserGibberish',113,'额',1285214970156);
INSERT INTO `discusses` VALUES (74,31,'UserGibberish',33,'恩恩',1285215472812);
INSERT INTO `discusses` VALUES (75,31,'UserGibberish',36,'恩恩',1285215472812);
INSERT INTO `discusses` VALUES (76,31,'UserGibberish',38,'恩恩',1285215472812);
INSERT INTO `discusses` VALUES (77,31,'UserGibberish',41,'恩恩',1285215472812);
INSERT INTO `discusses` VALUES (78,31,'UserGibberish',104,'恩恩',1285215472812);
INSERT INTO `discusses` VALUES (79,31,'UserGibberish',105,'恩恩',1285215472828);
INSERT INTO `discusses` VALUES (80,31,'UserGibberish',106,'恩恩',1285215472828);
INSERT INTO `discusses` VALUES (81,31,'UserGibberish',107,'恩恩',1285215472828);
INSERT INTO `discusses` VALUES (82,31,'UserGibberish',108,'恩恩',1285215472828);
INSERT INTO `discusses` VALUES (83,31,'UserGibberish',109,'恩恩',1285215472828);
INSERT INTO `discusses` VALUES (84,31,'UserGibberish',110,'恩恩',1285215472828);
INSERT INTO `discusses` VALUES (85,31,'UserGibberish',111,'恩恩',1285215472828);
INSERT INTO `discusses` VALUES (86,31,'UserGibberish',112,'恩恩',1285215472828);
INSERT INTO `discusses` VALUES (87,31,'UserGibberish',113,'恩恩',1285215472828);
INSERT INTO `discusses` VALUES (88,31,'UserGibberish',33,'鹅鹅鹅',1285215692187);
INSERT INTO `discusses` VALUES (89,31,'UserGibberish',36,'鹅鹅鹅',1285215692187);
INSERT INTO `discusses` VALUES (90,31,'UserGibberish',38,'鹅鹅鹅',1285215692187);
INSERT INTO `discusses` VALUES (91,31,'UserGibberish',41,'鹅鹅鹅',1285215692187);
INSERT INTO `discusses` VALUES (92,31,'UserGibberish',104,'鹅鹅鹅',1285215692187);
INSERT INTO `discusses` VALUES (93,31,'UserGibberish',105,'鹅鹅鹅',1285215692187);
INSERT INTO `discusses` VALUES (94,31,'UserGibberish',106,'鹅鹅鹅',1285215692187);
INSERT INTO `discusses` VALUES (95,31,'UserGibberish',107,'鹅鹅鹅',1285215692187);
INSERT INTO `discusses` VALUES (96,31,'UserGibberish',108,'鹅鹅鹅',1285215692187);
INSERT INTO `discusses` VALUES (97,31,'UserGibberish',109,'鹅鹅鹅',1285215692203);
INSERT INTO `discusses` VALUES (98,31,'UserGibberish',110,'鹅鹅鹅',1285215692203);
INSERT INTO `discusses` VALUES (99,31,'UserGibberish',111,'鹅鹅鹅',1285215692203);
INSERT INTO `discusses` VALUES (100,31,'UserGibberish',112,'鹅鹅鹅',1285215692203);
INSERT INTO `discusses` VALUES (101,31,'UserGibberish',113,'鹅鹅鹅',1285215692203);
INSERT INTO `discusses` VALUES (102,31,'UserGibberish',33,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (103,31,'UserGibberish',36,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (104,31,'UserGibberish',38,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (105,31,'UserGibberish',41,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (106,31,'UserGibberish',104,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (107,31,'UserGibberish',105,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (108,31,'UserGibberish',106,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (109,31,'UserGibberish',107,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (110,31,'UserGibberish',108,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (111,31,'UserGibberish',109,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (112,31,'UserGibberish',110,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (113,31,'UserGibberish',111,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (114,31,'UserGibberish',112,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (115,31,'UserGibberish',113,'fafasdfasdfasdasdfas',1285216295828);
INSERT INTO `discusses` VALUES (116,23,'UserGibberish',30,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (117,23,'UserGibberish',34,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (118,23,'UserGibberish',124,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (119,23,'UserGibberish',125,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (120,23,'UserGibberish',126,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (121,23,'UserGibberish',127,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (122,23,'UserGibberish',128,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (123,23,'UserGibberish',129,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (124,23,'UserGibberish',130,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (125,23,'UserGibberish',131,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (126,23,'UserGibberish',132,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (127,23,'UserGibberish',133,'评论下',1285247930203);
INSERT INTO `discusses` VALUES (128,24,'UserGibberish',30,'我也评论',1285247952265);
INSERT INTO `discusses` VALUES (129,24,'UserGibberish',34,'我也评论',1285247952265);
INSERT INTO `discusses` VALUES (130,24,'UserGibberish',124,'我也评论',1285247952265);
INSERT INTO `discusses` VALUES (131,24,'UserGibberish',125,'我也评论',1285247952265);
INSERT INTO `discusses` VALUES (132,24,'UserGibberish',126,'我也评论',1285247952265);
INSERT INTO `discusses` VALUES (133,24,'UserGibberish',127,'我也评论',1285247952281);
INSERT INTO `discusses` VALUES (134,24,'UserGibberish',128,'我也评论',1285247952281);
INSERT INTO `discusses` VALUES (135,24,'UserGibberish',129,'我也评论',1285247952281);
INSERT INTO `discusses` VALUES (136,24,'UserGibberish',130,'我也评论',1285247952281);
INSERT INTO `discusses` VALUES (137,24,'UserGibberish',131,'我也评论',1285247952281);
INSERT INTO `discusses` VALUES (138,24,'UserGibberish',132,'我也评论',1285247952281);
INSERT INTO `discusses` VALUES (139,24,'UserGibberish',133,'我也评论',1285247952281);
INSERT INTO `discusses` VALUES (140,25,'UserGibberish',30,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (141,25,'UserGibberish',34,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (142,25,'UserGibberish',124,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (143,25,'UserGibberish',125,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (144,25,'UserGibberish',126,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (145,25,'UserGibberish',127,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (146,25,'UserGibberish',128,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (147,25,'UserGibberish',129,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (148,25,'UserGibberish',130,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (149,25,'UserGibberish',131,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (150,25,'UserGibberish',132,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (151,25,'UserGibberish',133,'哈哈',1285247967828);
INSERT INTO `discusses` VALUES (152,26,'UserGibberish',30,'。。。',1285247980703);
INSERT INTO `discusses` VALUES (153,26,'UserGibberish',34,'。。。',1285247980703);
INSERT INTO `discusses` VALUES (154,26,'UserGibberish',124,'。。。',1285247980703);
INSERT INTO `discusses` VALUES (155,26,'UserGibberish',125,'。。。',1285247980703);
INSERT INTO `discusses` VALUES (156,26,'UserGibberish',126,'。。。',1285247980703);
INSERT INTO `discusses` VALUES (157,26,'UserGibberish',127,'。。。',1285247980718);
INSERT INTO `discusses` VALUES (158,26,'UserGibberish',128,'。。。',1285247980718);
INSERT INTO `discusses` VALUES (159,26,'UserGibberish',129,'。。。',1285247980718);
INSERT INTO `discusses` VALUES (160,26,'UserGibberish',130,'。。。',1285247980718);
INSERT INTO `discusses` VALUES (161,26,'UserGibberish',131,'。。。',1285247980718);
INSERT INTO `discusses` VALUES (162,26,'UserGibberish',132,'。。。',1285247980718);
INSERT INTO `discusses` VALUES (163,26,'UserGibberish',133,'。。。',1285247980718);
INSERT INTO `discusses` VALUES (164,27,'UserGibberish',30,'…………',1285247996484);
INSERT INTO `discusses` VALUES (165,27,'UserGibberish',34,'…………',1285247996484);
INSERT INTO `discusses` VALUES (166,27,'UserGibberish',124,'…………',1285247996484);
INSERT INTO `discusses` VALUES (167,27,'UserGibberish',125,'…………',1285247996484);
INSERT INTO `discusses` VALUES (168,27,'UserGibberish',126,'…………',1285247996484);
INSERT INTO `discusses` VALUES (169,27,'UserGibberish',127,'…………',1285247996484);
INSERT INTO `discusses` VALUES (170,27,'UserGibberish',128,'…………',1285247996484);
INSERT INTO `discusses` VALUES (171,27,'UserGibberish',129,'…………',1285247996500);
INSERT INTO `discusses` VALUES (172,27,'UserGibberish',130,'…………',1285247996500);
INSERT INTO `discusses` VALUES (173,27,'UserGibberish',131,'…………',1285247996500);
INSERT INTO `discusses` VALUES (174,27,'UserGibberish',132,'…………',1285247996500);
INSERT INTO `discusses` VALUES (175,27,'UserGibberish',133,'…………',1285247996500);
INSERT INTO `discusses` VALUES (176,32,'UserGibberish',135,'来评论',1285248262671);
INSERT INTO `discusses` VALUES (177,32,'UserGibberish',136,'来评论',1285248262671);
INSERT INTO `discusses` VALUES (178,31,'UserGibberish',30,'啊 哈哈你好啊 ',1285292619406);
INSERT INTO `discusses` VALUES (179,31,'UserGibberish',34,'啊 哈哈你好啊 ',1285292619406);
INSERT INTO `discusses` VALUES (180,31,'UserGibberish',132,'啊 哈哈你好啊 ',1285292619406);
INSERT INTO `discusses` VALUES (181,31,'UserGibberish',133,'啊 哈哈你好啊 ',1285292619406);
/*!40000 ALTER TABLE `discusses` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table filmreview
#

DROP TABLE IF EXISTS `filmreview`;
CREATE TABLE `filmreview` (
  `id` bigint(20) NOT NULL auto_increment,
  `userID` bigint(20) default NULL,
  `movieID` bigint(20) default NULL,
  `content` longtext,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table filmreview
#

LOCK TABLES `filmreview` WRITE;
/*!40000 ALTER TABLE `filmreview` DISABLE KEYS */;
INSERT INTO `filmreview` VALUES (1,33,1,'我也写影评了！！！22222');
/*!40000 ALTER TABLE `filmreview` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table freshnews
#

DROP TABLE IF EXISTS `freshnews`;
CREATE TABLE `freshnews` (
  `id` bigint(20) NOT NULL auto_increment,
  `userID` bigint(20) default NULL,
  `happenTo` bigint(20) default NULL,
  `newsOfEntity` varchar(20) default NULL,
  `newsID` bigint(20) default NULL,
  `newsType` varchar(20) default NULL,
  `timestamp` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table freshnews
#

LOCK TABLES `freshnews` WRITE;
/*!40000 ALTER TABLE `freshnews` DISABLE KEYS */;
INSERT INTO `freshnews` VALUES (27,23,23,'Movie',8,'collectFilm',1316681362328);
INSERT INTO `freshnews` VALUES (28,23,23,'Movie',15,'collectFilm',1316681473095);
INSERT INTO `freshnews` VALUES (29,23,23,'Movie',40,'collectFilm',1316681664067);
INSERT INTO `freshnews` VALUES (30,24,24,'Movie',3,'collectFilm',1316681788961);
INSERT INTO `freshnews` VALUES (31,25,25,'Movie',20,'collectFilm',1316682297331);
INSERT INTO `freshnews` VALUES (32,25,25,'Movie',25,'collectFilm',1316682321018);
INSERT INTO `freshnews` VALUES (33,26,26,'Movie',1,'collectFilm',1316682439426);
INSERT INTO `freshnews` VALUES (34,27,27,'Movie',3,'collectFilm',1316682787791);
INSERT INTO `freshnews` VALUES (35,27,27,'Movie',25,'collectFilm',1316682932051);
INSERT INTO `freshnews` VALUES (36,28,28,'Movie',1,'collectFilm',1316683430409);
INSERT INTO `freshnews` VALUES (37,29,29,'Movie',34,'collectFilm',1316683785547);
INSERT INTO `freshnews` VALUES (38,32,32,'Movie',1,'collectFilm',1316684272078);
INSERT INTO `freshnews` VALUES (39,32,32,'Movie',11,'collectFilm',1316684863604);
INSERT INTO `freshnews` VALUES (40,31,31,'Movie',9,'collectFilm',1285156844093);
INSERT INTO `freshnews` VALUES (41,33,33,'FilmReview',1,'writeFilmReview',1285156917015);
INSERT INTO `freshnews` VALUES (42,31,31,'Movie',11,'collectFilm',1285210107468);
INSERT INTO `freshnews` VALUES (43,31,31,'Movie',12,'collectFilm',1285210109796);
INSERT INTO `freshnews` VALUES (44,31,31,'Movie',13,'collectFilm',1285210112171);
INSERT INTO `freshnews` VALUES (45,31,31,'Movie',16,'collectFilm',1285210115359);
INSERT INTO `freshnews` VALUES (46,31,31,'Movie',14,'collectFilm',1285210976359);
INSERT INTO `freshnews` VALUES (47,31,31,'Movie',17,'collectFilm',1285210978343);
INSERT INTO `freshnews` VALUES (48,31,31,'Movie',26,'collectFilm',1285210981484);
INSERT INTO `freshnews` VALUES (49,31,31,'Movie',25,'collectFilm',1285210985796);
INSERT INTO `freshnews` VALUES (50,31,31,'User',23,'addFriend',1285211959875);
INSERT INTO `freshnews` VALUES (51,23,23,'User',31,'addFriend',1285211959906);
INSERT INTO `freshnews` VALUES (52,23,31,'User',24,'addFriend',1285211959921);
INSERT INTO `freshnews` VALUES (53,31,31,'User',24,'addFriend',1285211959921);
INSERT INTO `freshnews` VALUES (54,24,24,'User',31,'addFriend',1285211959921);
INSERT INTO `freshnews` VALUES (55,23,31,'User',25,'addFriend',1285211959937);
INSERT INTO `freshnews` VALUES (56,24,31,'User',25,'addFriend',1285211959937);
INSERT INTO `freshnews` VALUES (57,31,31,'User',25,'addFriend',1285211959937);
INSERT INTO `freshnews` VALUES (58,25,25,'User',31,'addFriend',1285211959953);
INSERT INTO `freshnews` VALUES (59,23,31,'User',26,'addFriend',1285211959953);
INSERT INTO `freshnews` VALUES (60,24,31,'User',26,'addFriend',1285211959953);
INSERT INTO `freshnews` VALUES (61,25,31,'User',26,'addFriend',1285211959953);
INSERT INTO `freshnews` VALUES (62,31,31,'User',26,'addFriend',1285211959968);
INSERT INTO `freshnews` VALUES (63,26,26,'User',31,'addFriend',1285211959968);
INSERT INTO `freshnews` VALUES (64,23,31,'User',27,'addFriend',1285211959984);
INSERT INTO `freshnews` VALUES (65,24,31,'User',27,'addFriend',1285211959984);
INSERT INTO `freshnews` VALUES (66,25,31,'User',27,'addFriend',1285211959984);
INSERT INTO `freshnews` VALUES (67,26,31,'User',27,'addFriend',1285211959984);
INSERT INTO `freshnews` VALUES (68,31,31,'User',27,'addFriend',1285211959984);
INSERT INTO `freshnews` VALUES (69,27,27,'User',31,'addFriend',1285211960000);
INSERT INTO `freshnews` VALUES (70,23,31,'User',28,'addFriend',1285211960000);
INSERT INTO `freshnews` VALUES (71,24,31,'User',28,'addFriend',1285211960000);
INSERT INTO `freshnews` VALUES (72,25,31,'User',28,'addFriend',1285211960015);
INSERT INTO `freshnews` VALUES (73,26,31,'User',28,'addFriend',1285211960015);
INSERT INTO `freshnews` VALUES (74,27,31,'User',28,'addFriend',1285211960015);
INSERT INTO `freshnews` VALUES (75,31,31,'User',28,'addFriend',1285211960015);
INSERT INTO `freshnews` VALUES (76,28,28,'User',31,'addFriend',1285211960015);
INSERT INTO `freshnews` VALUES (77,23,31,'User',29,'addFriend',1285211960031);
INSERT INTO `freshnews` VALUES (78,24,31,'User',29,'addFriend',1285211960031);
INSERT INTO `freshnews` VALUES (79,25,31,'User',29,'addFriend',1285211960031);
INSERT INTO `freshnews` VALUES (80,26,31,'User',29,'addFriend',1285211960031);
INSERT INTO `freshnews` VALUES (81,27,31,'User',29,'addFriend',1285211960046);
INSERT INTO `freshnews` VALUES (82,28,31,'User',29,'addFriend',1285211960046);
INSERT INTO `freshnews` VALUES (83,31,31,'User',29,'addFriend',1285211960046);
INSERT INTO `freshnews` VALUES (84,29,29,'User',31,'addFriend',1285211960046);
INSERT INTO `freshnews` VALUES (85,23,31,'User',30,'addFriend',1285211960062);
INSERT INTO `freshnews` VALUES (86,24,31,'User',30,'addFriend',1285211960062);
INSERT INTO `freshnews` VALUES (87,25,31,'User',30,'addFriend',1285211960062);
INSERT INTO `freshnews` VALUES (88,26,31,'User',30,'addFriend',1285211960062);
INSERT INTO `freshnews` VALUES (89,27,31,'User',30,'addFriend',1285211960078);
INSERT INTO `freshnews` VALUES (90,28,31,'User',30,'addFriend',1285211960078);
INSERT INTO `freshnews` VALUES (91,29,31,'User',30,'addFriend',1285211960078);
INSERT INTO `freshnews` VALUES (92,31,31,'User',30,'addFriend',1285211960078);
INSERT INTO `freshnews` VALUES (93,30,30,'User',31,'addFriend',1285211960093);
INSERT INTO `freshnews` VALUES (94,23,31,'User',32,'addFriend',1285211960093);
INSERT INTO `freshnews` VALUES (95,24,31,'User',32,'addFriend',1285211960109);
INSERT INTO `freshnews` VALUES (96,25,31,'User',32,'addFriend',1285211960109);
INSERT INTO `freshnews` VALUES (97,26,31,'User',32,'addFriend',1285211960109);
INSERT INTO `freshnews` VALUES (98,27,31,'User',32,'addFriend',1285211960109);
INSERT INTO `freshnews` VALUES (99,28,31,'User',32,'addFriend',1285211960109);
INSERT INTO `freshnews` VALUES (100,29,31,'User',32,'addFriend',1285211960109);
INSERT INTO `freshnews` VALUES (101,30,31,'User',32,'addFriend',1285211960109);
INSERT INTO `freshnews` VALUES (102,31,31,'User',32,'addFriend',1285211960125);
INSERT INTO `freshnews` VALUES (103,32,32,'User',31,'addFriend',1285211960125);
INSERT INTO `freshnews` VALUES (105,24,31,'UserGibberish',1,'recordGibberish',1285214299546);
INSERT INTO `freshnews` VALUES (114,23,31,'UserGibberish',2,'recordGibberish',1285215709921);
INSERT INTO `freshnews` VALUES (120,29,31,'UserGibberish',2,'recordGibberish',1285215709937);
INSERT INTO `freshnews` VALUES (132,32,31,'UserGibberish',3,'recordGibberish',1285216276453);
INSERT INTO `freshnews` VALUES (133,31,31,'UserGibberish',3,'recordGibberish',1285216276468);
INSERT INTO `freshnews` VALUES (134,33,33,'UserGibberish',4,'recordGibberish',1285246026906);
INSERT INTO `freshnews` VALUES (135,31,32,'UserGibberish',5,'recordGibberish',1285248237406);
INSERT INTO `freshnews` VALUES (136,32,32,'UserGibberish',5,'recordGibberish',1285248237406);
INSERT INTO `freshnews` VALUES (137,23,31,'UserGibberish',6,'recordGibberish',1285292578953);
INSERT INTO `freshnews` VALUES (138,24,31,'UserGibberish',6,'recordGibberish',1285292578953);
INSERT INTO `freshnews` VALUES (139,25,31,'UserGibberish',6,'recordGibberish',1285292578953);
INSERT INTO `freshnews` VALUES (140,26,31,'UserGibberish',6,'recordGibberish',1285292578953);
INSERT INTO `freshnews` VALUES (141,27,31,'UserGibberish',6,'recordGibberish',1285292578953);
INSERT INTO `freshnews` VALUES (142,28,31,'UserGibberish',6,'recordGibberish',1285292578953);
INSERT INTO `freshnews` VALUES (143,29,31,'UserGibberish',6,'recordGibberish',1285292578953);
INSERT INTO `freshnews` VALUES (144,30,31,'UserGibberish',6,'recordGibberish',1285292578953);
INSERT INTO `freshnews` VALUES (145,32,31,'UserGibberish',6,'recordGibberish',1285292578968);
INSERT INTO `freshnews` VALUES (146,31,31,'UserGibberish',6,'recordGibberish',1285292578968);
INSERT INTO `freshnews` VALUES (147,23,31,'User',33,'addFriend',1285293630937);
INSERT INTO `freshnews` VALUES (148,24,31,'User',33,'addFriend',1285293630937);
INSERT INTO `freshnews` VALUES (149,25,31,'User',33,'addFriend',1285293630937);
INSERT INTO `freshnews` VALUES (150,26,31,'User',33,'addFriend',1285293630937);
INSERT INTO `freshnews` VALUES (151,27,31,'User',33,'addFriend',1285293630937);
INSERT INTO `freshnews` VALUES (152,28,31,'User',33,'addFriend',1285293630937);
INSERT INTO `freshnews` VALUES (153,29,31,'User',33,'addFriend',1285293630937);
INSERT INTO `freshnews` VALUES (154,30,31,'User',33,'addFriend',1285293630937);
INSERT INTO `freshnews` VALUES (155,32,31,'User',33,'addFriend',1285293630953);
INSERT INTO `freshnews` VALUES (158,33,33,'Movie',3,'collectFilm',1285297375812);
INSERT INTO `freshnews` VALUES (159,33,33,'Movie',15,'collectFilm',1285297393687);
/*!40000 ALTER TABLE `freshnews` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table friendrelationship
#

DROP TABLE IF EXISTS `friendrelationship`;
CREATE TABLE `friendrelationship` (
  `userID` bigint(20) NOT NULL,
  `friendID` bigint(20) NOT NULL,
  PRIMARY KEY  (`userID`,`friendID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table friendrelationship
#

LOCK TABLES `friendrelationship` WRITE;
/*!40000 ALTER TABLE `friendrelationship` DISABLE KEYS */;
INSERT INTO `friendrelationship` VALUES (23,31);
INSERT INTO `friendrelationship` VALUES (24,31);
INSERT INTO `friendrelationship` VALUES (25,31);
INSERT INTO `friendrelationship` VALUES (26,31);
INSERT INTO `friendrelationship` VALUES (27,31);
INSERT INTO `friendrelationship` VALUES (28,31);
INSERT INTO `friendrelationship` VALUES (29,31);
INSERT INTO `friendrelationship` VALUES (30,31);
INSERT INTO `friendrelationship` VALUES (31,23);
INSERT INTO `friendrelationship` VALUES (31,24);
INSERT INTO `friendrelationship` VALUES (31,25);
INSERT INTO `friendrelationship` VALUES (31,26);
INSERT INTO `friendrelationship` VALUES (31,27);
INSERT INTO `friendrelationship` VALUES (31,28);
INSERT INTO `friendrelationship` VALUES (31,29);
INSERT INTO `friendrelationship` VALUES (31,30);
INSERT INTO `friendrelationship` VALUES (31,32);
INSERT INTO `friendrelationship` VALUES (32,31);
/*!40000 ALTER TABLE `friendrelationship` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table moviepreference
#

DROP TABLE IF EXISTS `moviepreference`;
CREATE TABLE `moviepreference` (
  `userID` bigint(20) NOT NULL,
  `movieID` bigint(20) NOT NULL,
  `preference` double default NULL,
  `timestamp` bigint(20) default NULL,
  PRIMARY KEY  (`userID`,`movieID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table moviepreference
#

LOCK TABLES `moviepreference` WRITE;
/*!40000 ALTER TABLE `moviepreference` DISABLE KEYS */;
INSERT INTO `moviepreference` VALUES (0,0,NULL,NULL);
INSERT INTO `moviepreference` VALUES (23,1,1,1316681268999);
INSERT INTO `moviepreference` VALUES (23,2,1,1316681668111);
INSERT INTO `moviepreference` VALUES (23,3,0.5,1316681673401);
INSERT INTO `moviepreference` VALUES (23,4,4.5,1316681339151);
INSERT INTO `moviepreference` VALUES (23,5,1,1316681678649);
INSERT INTO `moviepreference` VALUES (23,6,1,1316681685352);
INSERT INTO `moviepreference` VALUES (23,7,1,1316681689462);
INSERT INTO `moviepreference` VALUES (23,8,4.5,1316681380816);
INSERT INTO `moviepreference` VALUES (23,9,1,1316681490769);
INSERT INTO `moviepreference` VALUES (23,10,0.5,1316681429656);
INSERT INTO `moviepreference` VALUES (23,11,1,1316681411156);
INSERT INTO `moviepreference` VALUES (23,12,1,1316681497104);
INSERT INTO `moviepreference` VALUES (23,13,1,1316681417535);
INSERT INTO `moviepreference` VALUES (23,14,1,1316681502521);
INSERT INTO `moviepreference` VALUES (23,15,5,1316681473092);
INSERT INTO `moviepreference` VALUES (23,16,1.5,1316681693375);
INSERT INTO `moviepreference` VALUES (23,17,1,1316681510117);
INSERT INTO `moviepreference` VALUES (23,18,1,1316681696598);
INSERT INTO `moviepreference` VALUES (23,19,1,1316681522978);
INSERT INTO `moviepreference` VALUES (23,20,2,1316681527439);
INSERT INTO `moviepreference` VALUES (23,21,1,1316681717055);
INSERT INTO `moviepreference` VALUES (23,22,5,1316681532935);
INSERT INTO `moviepreference` VALUES (23,23,1,1316681548992);
INSERT INTO `moviepreference` VALUES (23,24,1,1316681555721);
INSERT INTO `moviepreference` VALUES (23,25,1,1316681560684);
INSERT INTO `moviepreference` VALUES (23,26,0.5,1316681574406);
INSERT INTO `moviepreference` VALUES (23,27,1,1316681568243);
INSERT INTO `moviepreference` VALUES (23,28,1,1316681599297);
INSERT INTO `moviepreference` VALUES (23,29,1,1316681585177);
INSERT INTO `moviepreference` VALUES (23,30,4.5,1316681594429);
INSERT INTO `moviepreference` VALUES (23,31,1,1316681643638);
INSERT INTO `moviepreference` VALUES (23,32,1,1316681606276);
INSERT INTO `moviepreference` VALUES (23,33,0.5,1316681612749);
INSERT INTO `moviepreference` VALUES (23,34,1,1316681623224);
INSERT INTO `moviepreference` VALUES (23,35,1,1316681629239);
INSERT INTO `moviepreference` VALUES (23,36,1.5,1316681639861);
INSERT INTO `moviepreference` VALUES (23,37,1,1316681722003);
INSERT INTO `moviepreference` VALUES (23,38,1,1316681654999);
INSERT INTO `moviepreference` VALUES (23,39,0.5,1316681660981);
INSERT INTO `moviepreference` VALUES (23,40,5,1316681664063);
INSERT INTO `moviepreference` VALUES (23,41,1,1316681728063);
INSERT INTO `moviepreference` VALUES (24,1,1,1316681777547);
INSERT INTO `moviepreference` VALUES (24,2,1,1316681781268);
INSERT INTO `moviepreference` VALUES (24,3,4.5,1316681794786);
INSERT INTO `moviepreference` VALUES (24,4,1.5,1316681808368);
INSERT INTO `moviepreference` VALUES (24,5,5,1316681815792);
INSERT INTO `moviepreference` VALUES (24,6,1,1316681823530);
INSERT INTO `moviepreference` VALUES (24,7,1,1316681835569);
INSERT INTO `moviepreference` VALUES (24,8,1.5,1316681851933);
INSERT INTO `moviepreference` VALUES (24,9,1,1316681859927);
INSERT INTO `moviepreference` VALUES (24,10,1.5,1316681875832);
INSERT INTO `moviepreference` VALUES (24,11,1,1316681882965);
INSERT INTO `moviepreference` VALUES (24,12,1.5,1316681892363);
INSERT INTO `moviepreference` VALUES (24,13,1,1316681900564);
INSERT INTO `moviepreference` VALUES (24,14,1,1316681906365);
INSERT INTO `moviepreference` VALUES (24,15,4.5,1316681912442);
INSERT INTO `moviepreference` VALUES (24,16,1,1316681917973);
INSERT INTO `moviepreference` VALUES (24,17,1.5,1316681924502);
INSERT INTO `moviepreference` VALUES (24,18,5,1316681929799);
INSERT INTO `moviepreference` VALUES (24,19,1,1316681936268);
INSERT INTO `moviepreference` VALUES (24,20,1.5,1316681944155);
INSERT INTO `moviepreference` VALUES (24,21,1.5,1316681958952);
INSERT INTO `moviepreference` VALUES (24,22,1.5,1316681964797);
INSERT INTO `moviepreference` VALUES (24,23,2,1316681969353);
INSERT INTO `moviepreference` VALUES (24,24,1,1316681973598);
INSERT INTO `moviepreference` VALUES (24,25,5,1316681981434);
INSERT INTO `moviepreference` VALUES (24,26,1,1316681990358);
INSERT INTO `moviepreference` VALUES (24,27,0.5,1316681995515);
INSERT INTO `moviepreference` VALUES (24,28,1,1316682002866);
INSERT INTO `moviepreference` VALUES (24,29,1,1316682007618);
INSERT INTO `moviepreference` VALUES (24,30,0.5,1316682017804);
INSERT INTO `moviepreference` VALUES (24,31,0.5,1316682022143);
INSERT INTO `moviepreference` VALUES (24,32,1,1316682029538);
INSERT INTO `moviepreference` VALUES (24,33,5,1316682036073);
INSERT INTO `moviepreference` VALUES (24,34,5,1316682040647);
INSERT INTO `moviepreference` VALUES (24,35,1,1316682045547);
INSERT INTO `moviepreference` VALUES (24,36,1.5,1316682052979);
INSERT INTO `moviepreference` VALUES (24,37,1,1316682057458);
INSERT INTO `moviepreference` VALUES (24,38,1.5,1316682064225);
INSERT INTO `moviepreference` VALUES (24,39,1,1316682069135);
INSERT INTO `moviepreference` VALUES (24,40,1,1316682073370);
INSERT INTO `moviepreference` VALUES (24,41,1,1316682085778);
INSERT INTO `moviepreference` VALUES (25,1,1,1316682192252);
INSERT INTO `moviepreference` VALUES (25,2,1,1316682203715);
INSERT INTO `moviepreference` VALUES (25,3,1,1316682212861);
INSERT INTO `moviepreference` VALUES (25,4,1,1316682217498);
INSERT INTO `moviepreference` VALUES (25,5,1,1316682221869);
INSERT INTO `moviepreference` VALUES (25,6,1,1316682226795);
INSERT INTO `moviepreference` VALUES (25,7,0.5,1316682232787);
INSERT INTO `moviepreference` VALUES (25,8,1,1316682237500);
INSERT INTO `moviepreference` VALUES (25,9,5,1316682247230);
INSERT INTO `moviepreference` VALUES (25,10,1,1316682251689);
INSERT INTO `moviepreference` VALUES (25,11,0.5,1316682260356);
INSERT INTO `moviepreference` VALUES (25,12,1,1316682264922);
INSERT INTO `moviepreference` VALUES (25,13,1,1316682269982);
INSERT INTO `moviepreference` VALUES (25,14,1,1316682276709);
INSERT INTO `moviepreference` VALUES (25,15,1,1316682280667);
INSERT INTO `moviepreference` VALUES (25,16,0.5,1316682285191);
INSERT INTO `moviepreference` VALUES (25,17,1,1316682289306);
INSERT INTO `moviepreference` VALUES (25,18,0.5,1316682293858);
INSERT INTO `moviepreference` VALUES (25,19,0.5,1316682301371);
INSERT INTO `moviepreference` VALUES (25,20,5,1316682297328);
INSERT INTO `moviepreference` VALUES (25,21,1,1316682307577);
INSERT INTO `moviepreference` VALUES (25,22,1,1316682314132);
INSERT INTO `moviepreference` VALUES (25,23,1,1316682317921);
INSERT INTO `moviepreference` VALUES (25,24,1,1316682326367);
INSERT INTO `moviepreference` VALUES (25,25,5,1316682321014);
INSERT INTO `moviepreference` VALUES (25,26,0.5,1316682329895);
INSERT INTO `moviepreference` VALUES (25,27,0.5,1316682335672);
INSERT INTO `moviepreference` VALUES (25,28,5,1316682340708);
INSERT INTO `moviepreference` VALUES (25,29,1,1316682345974);
INSERT INTO `moviepreference` VALUES (25,30,5,1316682350000);
INSERT INTO `moviepreference` VALUES (25,31,5,1316682353684);
INSERT INTO `moviepreference` VALUES (25,32,5,1316682357118);
INSERT INTO `moviepreference` VALUES (25,33,1,1316682362367);
INSERT INTO `moviepreference` VALUES (25,34,0.5,1316682367454);
INSERT INTO `moviepreference` VALUES (25,35,0.5,1316682371542);
INSERT INTO `moviepreference` VALUES (25,36,1,1316682377247);
INSERT INTO `moviepreference` VALUES (25,37,0.5,1316682380710);
INSERT INTO `moviepreference` VALUES (25,38,1,1316682384100);
INSERT INTO `moviepreference` VALUES (25,39,0.5,1316682387581);
INSERT INTO `moviepreference` VALUES (25,40,1,1316682391622);
INSERT INTO `moviepreference` VALUES (25,41,0.5,1316682395976);
INSERT INTO `moviepreference` VALUES (26,1,5,1316682442854);
INSERT INTO `moviepreference` VALUES (26,2,1,1316682447855);
INSERT INTO `moviepreference` VALUES (26,3,1,1316682452085);
INSERT INTO `moviepreference` VALUES (26,4,1,1316682455956);
INSERT INTO `moviepreference` VALUES (26,5,1,1316682459965);
INSERT INTO `moviepreference` VALUES (26,6,1,1316682469387);
INSERT INTO `moviepreference` VALUES (26,7,0.5,1316682473475);
INSERT INTO `moviepreference` VALUES (26,8,1,1316682478978);
INSERT INTO `moviepreference` VALUES (26,9,0.5,1316682486951);
INSERT INTO `moviepreference` VALUES (26,10,4.5,1316682497458);
INSERT INTO `moviepreference` VALUES (26,11,5,1316682501802);
INSERT INTO `moviepreference` VALUES (26,12,0.5,1316682508464);
INSERT INTO `moviepreference` VALUES (26,13,5,1316682513042);
INSERT INTO `moviepreference` VALUES (26,14,0.5,1316682517354);
INSERT INTO `moviepreference` VALUES (26,15,0.5,1316682521921);
INSERT INTO `moviepreference` VALUES (26,16,0.5,1316682531046);
INSERT INTO `moviepreference` VALUES (26,17,1,1316682540167);
INSERT INTO `moviepreference` VALUES (26,18,1,1316682547347);
INSERT INTO `moviepreference` VALUES (26,19,1,1316682550765);
INSERT INTO `moviepreference` VALUES (26,20,1,1316682555085);
INSERT INTO `moviepreference` VALUES (26,21,0.5,1316682558787);
INSERT INTO `moviepreference` VALUES (26,22,0.5,1316682562555);
INSERT INTO `moviepreference` VALUES (26,23,1,1316682566905);
INSERT INTO `moviepreference` VALUES (26,24,1,1316682570678);
INSERT INTO `moviepreference` VALUES (26,25,1,1316682574634);
INSERT INTO `moviepreference` VALUES (26,26,5,1316682585912);
INSERT INTO `moviepreference` VALUES (26,27,1,1316682645946);
INSERT INTO `moviepreference` VALUES (26,28,1,1316682649991);
INSERT INTO `moviepreference` VALUES (26,29,1,1316682655424);
INSERT INTO `moviepreference` VALUES (26,30,1,1316682667571);
INSERT INTO `moviepreference` VALUES (26,31,1,1316682671294);
INSERT INTO `moviepreference` VALUES (26,32,1,1316682675595);
INSERT INTO `moviepreference` VALUES (26,33,1,1316682679558);
INSERT INTO `moviepreference` VALUES (26,34,0.5,1316682682528);
INSERT INTO `moviepreference` VALUES (26,35,4.5,1316682688422);
INSERT INTO `moviepreference` VALUES (26,36,5,1316682692278);
INSERT INTO `moviepreference` VALUES (26,37,1,1316682698526);
INSERT INTO `moviepreference` VALUES (26,38,1,1316682703184);
INSERT INTO `moviepreference` VALUES (26,39,5,1316682707486);
INSERT INTO `moviepreference` VALUES (26,40,1,1316682732062);
INSERT INTO `moviepreference` VALUES (26,41,5,1316682736997);
INSERT INTO `moviepreference` VALUES (27,1,1,1316682777424);
INSERT INTO `moviepreference` VALUES (27,2,1,1316682784450);
INSERT INTO `moviepreference` VALUES (27,3,5,1316682793062);
INSERT INTO `moviepreference` VALUES (27,4,5,1316682797898);
INSERT INTO `moviepreference` VALUES (27,5,1,1316682802651);
INSERT INTO `moviepreference` VALUES (27,6,1,1316682810428);
INSERT INTO `moviepreference` VALUES (27,7,1,1316682814743);
INSERT INTO `moviepreference` VALUES (27,8,5,1316682820774);
INSERT INTO `moviepreference` VALUES (27,9,5,1316682824577);
INSERT INTO `moviepreference` VALUES (27,10,1,1316682829424);
INSERT INTO `moviepreference` VALUES (27,11,1,1316682840619);
INSERT INTO `moviepreference` VALUES (27,12,1,1316682845875);
INSERT INTO `moviepreference` VALUES (27,13,1,1316682850706);
INSERT INTO `moviepreference` VALUES (27,14,0.5,1316682866461);
INSERT INTO `moviepreference` VALUES (27,15,0.5,1316682874811);
INSERT INTO `moviepreference` VALUES (27,16,1,1316682879212);
INSERT INTO `moviepreference` VALUES (27,17,0.5,1316682886263);
INSERT INTO `moviepreference` VALUES (27,18,0.5,1316682890352);
INSERT INTO `moviepreference` VALUES (27,19,5,1316682894445);
INSERT INTO `moviepreference` VALUES (27,20,0.5,1316682902035);
INSERT INTO `moviepreference` VALUES (27,21,1,1316682907364);
INSERT INTO `moviepreference` VALUES (27,22,1,1316682913046);
INSERT INTO `moviepreference` VALUES (27,23,0.5,1316682916312);
INSERT INTO `moviepreference` VALUES (27,24,0.5,1316682920746);
INSERT INTO `moviepreference` VALUES (27,25,5,1316682935521);
INSERT INTO `moviepreference` VALUES (27,26,5,1316682939559);
INSERT INTO `moviepreference` VALUES (27,27,0.5,1316682947476);
INSERT INTO `moviepreference` VALUES (27,28,0.5,1316682951417);
INSERT INTO `moviepreference` VALUES (27,29,1.5,1316682956177);
INSERT INTO `moviepreference` VALUES (27,30,5,1316682960253);
INSERT INTO `moviepreference` VALUES (27,31,1.5,1316682964457);
INSERT INTO `moviepreference` VALUES (27,32,4.5,1316682968132);
INSERT INTO `moviepreference` VALUES (27,33,1.5,1316682973489);
INSERT INTO `moviepreference` VALUES (27,34,2,1316682978114);
INSERT INTO `moviepreference` VALUES (27,35,1,1316682982829);
INSERT INTO `moviepreference` VALUES (27,36,1.5,1316682986864);
INSERT INTO `moviepreference` VALUES (27,37,1.5,1316682990668);
INSERT INTO `moviepreference` VALUES (27,38,1.5,1316682995107);
INSERT INTO `moviepreference` VALUES (27,39,1,1316682998932);
INSERT INTO `moviepreference` VALUES (27,40,1.5,1316683005983);
INSERT INTO `moviepreference` VALUES (27,41,1,1316683011250);
INSERT INTO `moviepreference` VALUES (28,1,5,1316683433650);
INSERT INTO `moviepreference` VALUES (28,2,1,1316683441483);
INSERT INTO `moviepreference` VALUES (28,6,5,1316683452026);
INSERT INTO `moviepreference` VALUES (28,7,1,1316683456152);
INSERT INTO `moviepreference` VALUES (28,13,1,1316683606197);
INSERT INTO `moviepreference` VALUES (28,14,0.5,1316683476211);
INSERT INTO `moviepreference` VALUES (28,15,1,1316683481146);
INSERT INTO `moviepreference` VALUES (28,16,5,1316683485749);
INSERT INTO `moviepreference` VALUES (28,17,1,1316683491248);
INSERT INTO `moviepreference` VALUES (28,18,1,1316683495870);
INSERT INTO `moviepreference` VALUES (28,19,1,1316683503081);
INSERT INTO `moviepreference` VALUES (28,20,1,1316683508891);
INSERT INTO `moviepreference` VALUES (28,21,0.5,1316683513539);
INSERT INTO `moviepreference` VALUES (28,22,0.5,1316683518470);
INSERT INTO `moviepreference` VALUES (28,23,5,1316683522403);
INSERT INTO `moviepreference` VALUES (28,24,5,1316683526633);
INSERT INTO `moviepreference` VALUES (28,25,0.5,1316683531966);
INSERT INTO `moviepreference` VALUES (28,26,0.5,1316683536678);
INSERT INTO `moviepreference` VALUES (28,27,5,1316683540601);
INSERT INTO `moviepreference` VALUES (28,28,0.5,1316683544293);
INSERT INTO `moviepreference` VALUES (28,29,1.5,1316683548733);
INSERT INTO `moviepreference` VALUES (28,30,1.5,1316683552516);
INSERT INTO `moviepreference` VALUES (28,31,1.5,1316683557054);
INSERT INTO `moviepreference` VALUES (28,32,1.5,1316683561270);
INSERT INTO `moviepreference` VALUES (28,33,1,1316683564586);
INSERT INTO `moviepreference` VALUES (28,34,1,1316683570604);
INSERT INTO `moviepreference` VALUES (28,35,1,1316683576715);
INSERT INTO `moviepreference` VALUES (28,36,1,1316683581763);
INSERT INTO `moviepreference` VALUES (28,37,0.5,1316683585482);
INSERT INTO `moviepreference` VALUES (28,38,0.5,1316683591594);
INSERT INTO `moviepreference` VALUES (28,39,1,1316683595266);
INSERT INTO `moviepreference` VALUES (28,40,1,1316683598457);
INSERT INTO `moviepreference` VALUES (28,41,1,1316683602915);
INSERT INTO `moviepreference` VALUES (29,1,0.5,1316683645691);
INSERT INTO `moviepreference` VALUES (29,2,0.5,1316683788792);
INSERT INTO `moviepreference` VALUES (29,3,1,1316683650854);
INSERT INTO `moviepreference` VALUES (29,4,1.5,1316683656144);
INSERT INTO `moviepreference` VALUES (29,5,1,1316683660796);
INSERT INTO `moviepreference` VALUES (29,6,1,1316683667453);
INSERT INTO `moviepreference` VALUES (29,7,1,1316683674211);
INSERT INTO `moviepreference` VALUES (29,8,1,1316683678687);
INSERT INTO `moviepreference` VALUES (29,9,1,1316683683776);
INSERT INTO `moviepreference` VALUES (29,10,0.5,1316683687796);
INSERT INTO `moviepreference` VALUES (29,11,1,1316683691415);
INSERT INTO `moviepreference` VALUES (29,12,1,1316683697718);
INSERT INTO `moviepreference` VALUES (29,13,0.5,1316683700809);
INSERT INTO `moviepreference` VALUES (29,14,1,1316683707281);
INSERT INTO `moviepreference` VALUES (29,15,0.5,1316683710912);
INSERT INTO `moviepreference` VALUES (29,16,1,1316683714869);
INSERT INTO `moviepreference` VALUES (29,17,1,1316683717950);
INSERT INTO `moviepreference` VALUES (29,18,5,1316683722193);
INSERT INTO `moviepreference` VALUES (29,19,1,1316683725912);
INSERT INTO `moviepreference` VALUES (29,20,1,1316683729788);
INSERT INTO `moviepreference` VALUES (29,21,1,1316683733298);
INSERT INTO `moviepreference` VALUES (29,22,0.5,1316683737406);
INSERT INTO `moviepreference` VALUES (29,23,1,1316683740310);
INSERT INTO `moviepreference` VALUES (29,24,0.5,1316683743701);
INSERT INTO `moviepreference` VALUES (29,25,1,1316683749613);
INSERT INTO `moviepreference` VALUES (29,26,1,1316683753746);
INSERT INTO `moviepreference` VALUES (29,27,0.5,1316683758136);
INSERT INTO `moviepreference` VALUES (29,28,0.5,1316683763277);
INSERT INTO `moviepreference` VALUES (29,29,0.5,1316683767315);
INSERT INTO `moviepreference` VALUES (29,30,1,1316683771244);
INSERT INTO `moviepreference` VALUES (29,31,1,1316683774845);
INSERT INTO `moviepreference` VALUES (29,32,1,1316683778390);
INSERT INTO `moviepreference` VALUES (29,33,1,1316683781897);
INSERT INTO `moviepreference` VALUES (29,34,5,1316683785544);
INSERT INTO `moviepreference` VALUES (29,35,1,1316683792362);
INSERT INTO `moviepreference` VALUES (29,36,1,1316683797008);
INSERT INTO `moviepreference` VALUES (29,37,1,1316683801183);
INSERT INTO `moviepreference` VALUES (29,38,1,1316683807810);
INSERT INTO `moviepreference` VALUES (29,39,1,1316683811678);
INSERT INTO `moviepreference` VALUES (29,40,0.5,1316683816358);
INSERT INTO `moviepreference` VALUES (29,41,1,1316683819816);
INSERT INTO `moviepreference` VALUES (30,1,0.5,1316683889482);
INSERT INTO `moviepreference` VALUES (30,2,2.5,1316683897781);
INSERT INTO `moviepreference` VALUES (30,3,5,1316683901842);
INSERT INTO `moviepreference` VALUES (30,4,5,1316683905078);
INSERT INTO `moviepreference` VALUES (30,5,3.5,1316683908859);
INSERT INTO `moviepreference` VALUES (30,6,1.5,1316683913084);
INSERT INTO `moviepreference` VALUES (30,7,0.5,1316683962031);
INSERT INTO `moviepreference` VALUES (30,8,1.5,1316683964835);
INSERT INTO `moviepreference` VALUES (30,9,3.5,1316683969288);
INSERT INTO `moviepreference` VALUES (30,10,0.5,1316683973522);
INSERT INTO `moviepreference` VALUES (30,11,1.5,1316683979123);
INSERT INTO `moviepreference` VALUES (30,12,5,1316683981964);
INSERT INTO `moviepreference` VALUES (30,13,0.5,1316684013229);
INSERT INTO `moviepreference` VALUES (30,14,5,1316683987480);
INSERT INTO `moviepreference` VALUES (30,15,3.5,1316684019473);
INSERT INTO `moviepreference` VALUES (30,16,1.5,1316684025455);
INSERT INTO `moviepreference` VALUES (30,17,0.5,1316684029064);
INSERT INTO `moviepreference` VALUES (30,18,2.5,1316684033618);
INSERT INTO `moviepreference` VALUES (30,19,4.5,1316684042232);
INSERT INTO `moviepreference` VALUES (30,20,2,1316684045530);
INSERT INTO `moviepreference` VALUES (30,21,4,1316684052276);
INSERT INTO `moviepreference` VALUES (30,22,3,1316684056430);
INSERT INTO `moviepreference` VALUES (30,23,1.5,1316684066336);
INSERT INTO `moviepreference` VALUES (30,24,1.5,1316684073375);
INSERT INTO `moviepreference` VALUES (30,25,3.5,1316684078042);
INSERT INTO `moviepreference` VALUES (30,26,0.5,1316684093146);
INSERT INTO `moviepreference` VALUES (30,27,0.5,1316684096124);
INSERT INTO `moviepreference` VALUES (30,28,3.5,1316684106769);
INSERT INTO `moviepreference` VALUES (30,29,1,1316684186357);
INSERT INTO `moviepreference` VALUES (30,30,3.5,1316684113909);
INSERT INTO `moviepreference` VALUES (30,31,3.5,1316684117536);
INSERT INTO `moviepreference` VALUES (30,32,4,1316684120953);
INSERT INTO `moviepreference` VALUES (30,33,3.5,1316684135028);
INSERT INTO `moviepreference` VALUES (30,34,2.5,1316684183480);
INSERT INTO `moviepreference` VALUES (30,35,3,1316684139480);
INSERT INTO `moviepreference` VALUES (30,36,1,1316684172159);
INSERT INTO `moviepreference` VALUES (30,37,2.5,1316684166834);
INSERT INTO `moviepreference` VALUES (30,38,2,1316684162263);
INSERT INTO `moviepreference` VALUES (30,39,1,1316684147828);
INSERT INTO `moviepreference` VALUES (30,40,1,1316684158423);
INSERT INTO `moviepreference` VALUES (30,41,2,1316684154172);
INSERT INTO `moviepreference` VALUES (32,6,5,1316688015100);
INSERT INTO `moviepreference` VALUES (32,10,5,1316688019526);
INSERT INTO `moviepreference` VALUES (32,13,4.5,1316688022619);
INSERT INTO `moviepreference` VALUES (33,1,1,1285293590484);
INSERT INTO `moviepreference` VALUES (33,2,4,1285293552609);
INSERT INTO `moviepreference` VALUES (33,3,5,1285297375781);
INSERT INTO `moviepreference` VALUES (33,5,4.5,1285293558515);
INSERT INTO `moviepreference` VALUES (33,15,5,1285297393687);
INSERT INTO `moviepreference` VALUES (33,34,5,1285293580750);
/*!40000 ALTER TABLE `moviepreference` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table movies
#

DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `id` bigint(20) NOT NULL auto_increment,
  `movieName` varchar(20) default NULL,
  `type` varchar(30) default NULL,
  `director` varchar(20) default NULL,
  `actor` varchar(200) default NULL,
  `description` varchar(500) default NULL,
  `country` varchar(30) default NULL,
  `language` varchar(15) default NULL,
  `releaseYear` varchar(50) default NULL,
  `avatarLink` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table movies
#

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'假装情侣','喜剧/爱情','刘奋斗','黄渤/江一燕','善良的保险公司销售员陈文（黄渤 饰）在广场邂逅了一个疯疯癫癫的女孩，谁知在去精神病院的路上女孩化身成清秀美丽的佳人。虽然惹得一堆麻烦上身，不过陈文次日还是应约与这个叫做沈露（江一燕 饰）的女孩见面。沈露要求陈文做他的假装情侣，而陈文也欣然接受。在接下来的日子里，陈文变成一个随叫随到的小仆人，他按照沈露的要求做着各种各样难为情却又好玩的事情，两人度过了一段难忘的美好时光。在此期间，陈文慢慢喜欢上了这个古灵精怪的女孩，沈露的心中似乎也经历着某种矛盾纠葛。\r\n善良的保险公司销售员陈文（黄渤 饰）在广场邂逅了一个疯疯癫癫的女孩，谁知在去精神病院的路上女孩化身成清秀美丽的佳人。虽然惹得一堆麻烦上身，不过陈文次日还是应约与这个叫做沈露（江一燕 饰）的女孩见面。沈露要求陈文做他的假装情侣，而陈文也欣然接受。在接下来的日子里，陈文变成一个随叫随到的小仆人，他按照沈露的要求做着各种各样难为情却又好玩的事情，两人度过了一段难忘的美好时光。在此期间，陈文慢慢喜欢上了这个古灵精怪的女孩，沈露的心中似乎也经历着某种矛盾纠葛。\r\n善良的保险公司销售员陈文（黄渤 饰）在广场邂逅了一个疯疯癫癫的女孩，谁知在去精神病院','内地','中文','2011','upload/moviePoster/s6522737.jpg');
INSERT INTO `movies` VALUES (2,'窃听风暴','剧情 / 惊悚','弗洛里安·亨克尔·冯·多纳斯马','乌尔里希·穆埃 / 马蒂娜·戈黛特 / 塞巴斯蒂安·考奇 / 乌尔里奇·图克尔 / 托马斯·席尔梅','1984年的东德，整个社会笼罩在国家安全局的高压统治之下，特工魏斯曼（乌尔利希·穆厄 饰）奉命监听剧作家德莱曼（塞巴斯蒂安·寇治 饰）和他妻子演员克里斯蒂娜（玛蒂娜·杰蒂克 饰）的生活，监听过程中，魏斯曼渐渐对这家人的生活产生了兴趣，开始暗中帮助他们。一篇刊登在西德《明镜》报上的文章引起了特工头目的注意，他们认为这篇文章是德莱曼写的，并逮捕了克里斯蒂娜，希望能够从她口中得出她丈夫的秘密？而审问克里斯蒂娜的正是魏斯曼……   ','德国','德语','2006-03-23','upload/moviePoster/2037625643047.jpg');
INSERT INTO `movies` VALUES (3,'源代码','剧情 / 动作 / 科幻 / 惊悚','邓肯·琼斯','杰克·吉伦哈尔 / 维拉·法米加 / 米歇尔·莫娜汉 / 杰弗里·怀特','在阿富汗执行任务的美国空军飞行员科特史蒂文斯上尉（杰克•吉伦哈尔 Jake Gyllenhaal 饰）突然惊醒，发生自己在一辆高速行驶的列车上，而他的身边坐着一个素不相识的女子克里斯蒂安（米歇尔•莫娜汉 Michelle Monaghan 饰）正在与自己讲话。科尔不知自己为什么会在这辆车上，而且他发现自己居然是以另一个人的身份存在，正当他迷惑不解的时候，列车上忽然发生爆炸……\r\n　　科特又一次惊醒，发现自己身处一个密闭的太空仓里，有一位女军官古德温（维拉•法米加 Vera Farmiga 饰）正在通过视频和自己对话，并要求自己报告列车上发生的事情。一头雾水的科特还没搞明白是怎么回事时，他又一次被送上那辆列车。这次之后，科特终于明白自己在执行一件任务，负责调察芝加哥火车爆炸案找到恐怖份子并查出他的下一个目标。科特被一次又一次的送上那辆高速列车，每次只有八分钟... ','美国、法国、加拿大','英语','2011-04-01(美国) / 2011-08-30(中国大陆)','upload/moviePoster/2047916070299.jpg');
INSERT INTO `movies` VALUES (4,'哈利·波特与死亡圣器(下)','动作 / 悬疑 / 奇幻 / 家庭','大卫·叶茨','丹尼尔·雷德克里夫 ','当又一次和伏地魔（Ralph Fiennes 饰）的意识连通，哈利·波特（丹尼尔·雷德克里夫 Daniel Radcliffe 饰）断定最后一件魂器藏在霍格沃茨，于是和罗恩（鲁伯特·格林特 Rupert Grint 饰）、赫敏（爱玛·沃特森 Emma Watson 饰）一同返回阴云密布的学校。在好友们的帮助下，他们成功驱逐了斯内普（艾伦·里克曼 Alan Rickman 饰），然而觉察到哈利目的的伏地魔则率领途徒众向霍格沃茨逼近。食死徒、摄魂怪、巨人疯狂涌入这所有着悠久历史的魔法学校，正邪决战旋即爆发，一时间血雨腥风，死伤无数。从斯内普的眼泪中，哈利不仅了解到父辈的故事，也证实了藏缅于他体内最后的秘密。在此之后，他也和伏地魔迎来了最后的对决……\r\n　　本片根据英国作家J.K.罗琳的同名原著改编，也是“哈利·波特”系列影片的完结篇。  ','英国 / 美国','英语','2011-07-15 / 2011-08-04','upload/moviePoster/2188723283631.jpg');
INSERT INTO `movies` VALUES (5,'罗拉快跑','犯罪 / 惊悚','汤姆·提克威','弗兰卡·波坦特 / 莫里兹·布雷多 ','罗拉（弗兰卡•波滕特 饰）和曼尼（莫里兹•克雷多 饰）是一对年轻的恋人，其中曼尼是一个不务正业的小混混，而罗拉则是相信“天大的事情有爱情顶着”的爱情至上主义^^^^^^^^^\r\n','德国','德语','1998-08-20','upload/moviePoster/2209269961714.jpg');
INSERT INTO `movies` VALUES (6,'想爱就爱',' 喜剧','hewuj',' 奥密兹•苏查拉特','女孩Pim（索兰娜特•尤潘农 Soranut Yupanun 饰）因与前男友分手要求换宿舍，于是她和新来的学妹Kim（阿丽莎拉•唐伯里苏斯 Arisara Tongborisuth 饰）成了同居舍友。起初，Pim以为Kim是走错屋的小正太，直到对方袒露胸襟，她才赫然发现其实是同类。第一印象如此之差，让Pim怀疑其拉拉倾向严重，索性在宿舍中画定了三八线。尽管Kim百般讨好，但是Pim还是觉得她弹吉他、玩电玩很烦人。倒是Pim的闺蜜对Kim仰慕已久，大献殷勤。直到Pim随Kim回了家，夜晚听她弹吉他，两人才慢慢地敞开了心扉。Pim喜欢上了Kim做的菜。慢慢地，两人形影不离的关系，引起了Pim朋友们的嫉妒。甚至连熟识的哥哥也以男友自居，让她很受伤。在经历了多次波折之后，Pim赫然发现，原来当彼此的心心相印时，并不存在不能逾越的界限……   ',' 泰国','泰语',' 2010-12-16','upload/moviePoster/2344702919815.jpg');
INSERT INTO `movies` VALUES (7,'皮娜','歌舞','维姆·文德斯','皮娜·鲍什 ','（JAP/文）这是一部献给2009年去世的“德国现代舞第一夫人”皮娜·鲍什的电影。影片跟随皮娜·鲍什和乌珀塔尔舞蹈剧场所有成员拍摄，采用先进的3D技术，展示皮娜创造的让人振奋又无可比拟的现代舞艺术。影片将带领观众展开一次视觉感官上的发现之旅，将这个传奇的舞蹈剧场以新的维度投射到银幕之上。除此之外，文德斯的镜头还将跟随舞者们离开剧场，走进乌珀塔尔这座充满工业景观的城市，这里是皮娜的扎根之地，在超过35年的时间里，这里一直是她创作生活的中心…………\r\n','德国 / 法国 / 英国','德语 ','2011-02-24','upload/moviePoster/2344793062434.jpg');
INSERT INTO `movies` VALUES (8,'月球 Moon','剧情 / 悬疑 / 科幻','邓肯·琼斯','山姆·洛克威尔','未来世界，随着科技的飞速进步，地球的污染也越来越严重。为了遏制这种现状，一家名为月能工业有限公司的企业应运而生。该公司致力于月球能源的开发，通过采集氦-3来满足地球对能源的需求。月能公司在月球设有基地，山姆•贝尔（山姆•洛克威尔 Sam Rockwell 饰）正是该基地上唯一的工作人员。山姆是公司聘用的合同工，他已在月球孤零零地生活3年，陪伴他的只有智能机器人戈蒂（凯文•斯派西 Kevin Spacey 饰）。枯燥乏味的生活令山姆归心似箭，在还有两周就离开月球的时候，山姆偶然遭遇一起事故。醒来后的他发现戈蒂似乎对其有所隐瞒，公司高层也拒绝他的回程请求。山姆借机逃出基地，却在事故发生地点发现另一个自己……\r\n','英国','英语','2009-07-17','upload/moviePoster/2395832805519.jpg');
INSERT INTO `movies` VALUES (9,'Automan','冒险 / 科幻','Kim Manners ','Desi Arnaz Jr','Walter Nebicher is the police department\'s resident computer expert, although his immediate superior gives no respect as to his contribution to the force. To fix that, he creates a special program that creates Automan, an artificially intelligent computer construct that looks real, sounds reals and, given enough power, can have an actual physical presence outside the computer t... ','美国','英语','1983-12-15','upload/moviePoster/2400712345347.jpg');
INSERT INTO `movies` VALUES (10,'傲慢与偏见 ','剧情 / 爱情','乔·怀特','凯拉·耐特丽','根据简·奥斯汀同名小说改编。伊丽莎白·班纳特(凯拉·奈特丽 饰)出身于小地主家庭，有四个姐妹，母亲班纳特太太整天操心着为女儿物色称心如意的丈夫。新来的邻居宾格来先生和他的朋友达西（马修·麦克菲迪恩 饰）打破了她们一家人单调的乡村生活。宾格来和伊丽莎白的姐姐简·班纳特互生情愫；达西对善良聪明的伊丽莎白产生了好感，而伊丽莎白却对达西不可一世的傲慢心存偏见，不接受他的感情。然而，世事难料，宾格来和简·班纳特因为误会，关系危在旦夕；达西的种种作为，展示了性格中和伊丽莎白相同的善良一面，逐渐赢得了伊丽莎白的好感。两对有情人能否终成眷属？班纳特姐妹们能否得到自己想要的生活？\r\n','英国','英语','2005-09-16','upload/moviePoster/2527666048718.jpg');
INSERT INTO `movies` VALUES (11,'朗读者','剧情 / 爱情','史蒂芬·戴德利','凯特·温丝莱特 ','15岁的少年米夏·伯格（大卫·克劳斯 David Kross 饰）偶遇36岁的中年神秘女列车售票员汉娜（凯特·温丝莱特 Kate Winslet 饰），后来两个发展出一段秘密的情人关系。汉娜最喜欢躺在米夏怀里听米夏为他读书，她总是沉浸在那朗朗的读书声中。年轻的米夏沉溺于这种关系不能自拔的同时，却发现他自己根本不了解汉娜。忽然有一天，这个神秘女人不告而别，米夏在短暂的迷惑和悲伤之后，开始了新的生活。\r\n','德国','拉丁语','2008-12-10','upload/moviePoster/2538547845075.jpg');
INSERT INTO `movies` VALUES (12,'源代码','剧情','邓肯-琼斯','吉伦哈尔 / 维拉·法米加','在阿富汗执行任务的美国空军飞行员科特史蒂文斯上尉（杰克•吉伦哈尔 Jake Gyllenhaal 饰）突然惊醒，发生自己在一辆高速行驶的列车上，而他的身边坐着一个素不相识的女子克里斯蒂安（米歇尔•莫娜汉 Michelle Monaghan 饰）正在与自己讲话。科尔不知自己为什么会在这辆车上，而且他发现自己居然是以另一个人的身份存在，正当他迷惑不解的时候，列车上忽然发生爆炸……\r\n　　科特又一次惊醒，发现自己身处一个密闭的太空仓里，有一位女军官古德温（维拉•法米加 Vera Farmiga 饰）正在通过视频和自己对话，并要求自己报告列车上发生的事情。一头雾水的科特还没搞明白是怎么回事时，他又一次被送上那辆列车。这次之后，科特终于明白自己在执行一件任务，负责调察芝加哥火车爆炸案找到恐怖份子并查出他的下一个目标。科特被一次又一次的送上那辆高速列车，每次只有八分钟... ','法国','英语','2010-04-01','upload/moviePoster/2626186432832.jpg');
INSERT INTO `movies` VALUES (13,'音为爱','/爱情',' Chayanop Boonprakob',' Jirayu La-ongmanee','音乐永远不会过时，正如友谊永远与你我同在。\r\n　　小学男生小贝不曾听过音乐，但在一节音乐课上，同班的女孩恩的演唱让他心跳加速。他对恩懵懂的好感与动人的旋律交杂在一起，让他第一次感受到了音乐的美妙。他开始学着听歌，想要更靠近恩，但恩却与父母搬离清迈。时光如梭，高三那一年，恩重回清迈，与小贝成为了同校同学。此时校园中摇滚乐盛行，校园乐队的成员成为全校的风云人物。小贝的死党小空为了能吸引更多美眉的目光，决定组建自己的摇滚乐团，并强拉小贝入团，还找来了同样对音乐一无所知的艾克，三人决定以乐团为幌子，开始把妹，但没有音乐知识，让整个乐队的表演变成了闹剧，也让三人在全校学生面前丢尽颜面，成为彻彻底底的失败者。本以为自己能Succeed，结果却SuckSeed。失败让三人开始思考音乐本身。音乐对于他们来说到底是什么……\r\n\r\n','泰国','泰语','2011-03-17','upload/moviePoster/2634816008940.jpg');
INSERT INTO `movies` VALUES (14,'源代码','剧情 / 动作','邓肯·琼斯','杰克·吉伦哈尔','在阿富汗执行任务的美国空军飞行员科特史蒂文斯上尉（杰克•吉伦哈尔 Jake Gyllenhaal 饰）突然惊醒，发生自己在一辆高速行驶的列车上，而他的身边坐着一个素不相识的女子克里斯蒂安（米歇尔•莫娜汉 Michelle Monaghan 饰）正在与自己讲话。科尔不知自己为什么会在这辆车上，而且他发现自己居然是以另一个人的身份存在，正当他迷惑不解的时候，列车上忽然发生爆炸……\r\n　　科特又一次惊醒，发现自己身处一个密闭的太空仓里，有一位女军官古德温（维拉•法米加 Vera Farmiga 饰）正在通过视频和自己对话，并要求自己报告列车上发生的事情。一头雾水的科特还没搞明白是怎么回事时，他又一次被送上那辆列车。这次之后，科特终于明白自己在执行一件任务，负责调察芝加哥火车爆炸案找到恐怖份子并查出他的下一个目标。科特被一次又一次的送上那辆高速列车，每次只有八分钟... ','法国 / 美国 / 加拿大','英语','2011-04-01(美国) ','upload/moviePoster/2695783614038.jpg');
INSERT INTO `movies` VALUES (15,'香水','剧情 / 惊悚','汤姆·提克威','本·卫肖 ','十八世纪，让-马普蒂斯特•格雷诺耶（本•韦肖 Ben Whishaw 饰）出生在巴黎最肮脏、最恶臭不堪的地方——鱼市场上。格雷诺耶天生对气味有着惊人的天赋：无论恶臭还芳香，他都一一记住，并能轻易分辨各','德国','英语','2007-01-05','upload/moviePoster/2735011333502.jpg');
INSERT INTO `movies` VALUES (16,'钢的琴 (2010）','剧情 / 喜剧','张猛',' 王千源 / 秦海璐 / 张申英 / 田雨 / 国永振','上世纪90年代初，东北某重工业城市。原钢厂工人陈桂林（王千源 饰）在下岗后，独自拉起了一支乐队，终日奔波在婚丧嫁娶、店铺开业的营生之中，生活勉强维持。他的妻子小菊（张申英 饰）离家出走，转投有钱的假药商人怀抱。如今小菊光鲜回归，不仅要与桂林离婚，还要争夺独生女小元的抚养权。桂林慨叹自己失败的命运，于是一心要将女儿培养成钢琴家。为了得到女儿，他四处筹措买钢琴的钱，甚至和女友淑娴（秦海璐 饰）以及当年钢厂的好哥们夜入学校偷钢琴。\r\n　　当所有的办法都失败后，桂林偶然翻到一本关于钢琴的俄国文献，于是叫上伙伴们在早已破败的厂房中开始了手工制造钢琴的征途……   \r\n\r\n','中国大陆',' 汉语普通话','2011-07-15(中国大陆)','upload/moviePoster/2778199032716.jpg');
INSERT INTO `movies` VALUES (17,'月球','剧情','邓肯-琼斯','威尔 / 凯文·史派西 ','　未来世界，随着科技的飞速进步，地球的污染也越来越严重。为了遏制这种现状，一家名为月能工业有限公司的企业应运而生。该公司致力于月球能源的开发，通过采集氦-3来满足地球对能源的需求。月能公司在月球设有基地，山姆•贝尔（山姆•洛克威尔 Sam Rockwell 饰）正是该基地上唯一的工作人员。山姆是公司聘用的合同工，他已在月球孤零零地生活3年，陪伴他的只有智能机器人戈蒂（凯文•斯派西 Kevin Spacey 饰）。枯燥乏味的生活令山姆归心似箭，在还有两周就离开月球的时候，山姆偶然遭遇一起事故。醒来后的他发现戈蒂似乎对其有所隐瞒，公司高层也拒绝他的回程请求。山姆借机逃出基地，却在事故发生地点发现另一个自己……\r\n','英国','英语','2009-07-17','upload/moviePoster/2822496492255.jpg');
INSERT INTO `movies` VALUES (18,'孤岛惊魂','悬疑 / 惊悚 / 恐怖','钟继昌','陈小春 / 杨幂','雄踞东南亚的亿万富豪——OEC集团总裁陈家栋（姜皓文 饰）继承父亲的产业，并决心开发位于太平洋爪哇海上一个已经废弃40年之久的小岛。虽然父亲极力阻拦，但是利益熏心的家栋依然发起了一个孤岛探险的比赛。他开出100万元的巨额奖金，引得彭非（陈小春 饰）、沈依琳（杨幂 饰）、小野宏（叶山豪 饰）等8名青年男女搭上了前往小岛的游艇。一切看似顺风顺水，游艇却在航行中遭遇了离奇海难，致令8个青年被迫滞留小岛。\r\n　　在这座岛上，他们不仅遭到疯狂野猪的袭击，还发现了一座几成废墟的麻风病人的医院。此后不久，恐怖的事情接连发生，众人陷入一个无法退出的死亡游戏之中……   ','中国大陆','汉语普通话 / 粤语 / 英语','2011-07-08(中国大陆)','upload/moviePoster/2838779210168.jpg');
INSERT INTO `movies` VALUES (19,'The Pretender','剧情 / 科幻 ','Steven Long Mitchell','Michael T. Weiss ','我们中有些人会伪装，他们是天才，可以伪装成任何一种人。1963年，一个名为”中心“的社团隔离了这样一个伪装者，是个名叫杰瑞的小男孩，利用它的大脑进行研究——后来，这个伪装者逃走了…\r\n','美国','英语','1996-09-19','upload/moviePoster/2911525283990.jpg');
INSERT INTO `movies` VALUES (20,'钢琴家','传记 / 剧情','罗曼·波兰斯基','艾德里安·布洛迪 ','史标曼（艾德里安•布洛迪 Adrien Brody 饰）是波兰一家电台的钢琴师。二战即将爆发之时，他们全家被迫被赶进华沙的犹太区。在战争的颠沛流离中，家人和亲戚最终被纳粹杀害，而标曼本人也受尽种种羞辱和折磨，他侥幸得到一位朋友的帮助，暂时有了藏身之处。战争','法国','俄语','2002-05-24','upload/moviePoster/2923752237280.jpg');
INSERT INTO `movies` VALUES (21,'千钧一发','剧情','安德鲁·尼科尔','伊桑·霍克 / 乌玛·瑟曼','　未来的世界，科技的力量胜过一切，基因决定命运，几乎成为金科玉律。不幸文森特（伊桑•霍克 Ethan Hawke 饰）是一个基因不良的人，出生以来就决定了他近视和心脏病的缺陷，他只能活到30岁。于是父母为文森特增添了一个有着优良基因的弟弟。\r\n　　文森特的梦想是漫游太空，然而恶劣的基因令他无法圆梦，直到他遇到了太空中心的杰罗姆（裘德•洛 Jude Law 饰）。杰罗姆有优秀的基因，却在一次意外中半身瘫痪。二人决定调换身份，文森特千方百计隐藏自己的基因信息，每天都认真清洗掉自己的皮屑毛发，不暴露任何蛛丝马迹。另一方面，杰罗姆帮他筹备进入太空前需要检查的基因物品。事情进展得非常顺利，然而一桩谋杀案和一根睫毛，让事情节外生枝','美国','英语','1997-10-24','upload/moviePoster/2992189320685.jpg');
INSERT INTO `movies` VALUES (22,'不明身份','剧情 / 悬疑','佐米·希尔拉','连姆·尼森 ','马丁·哈里斯博士（连姆·尼森 饰）在柏林遭遇一场车祸后从昏迷中苏醒，发现妻子（詹纽瑞·琼斯 饰）突然不认识自己了，而另一个陌生人（艾丹·奎因 饰）盗用了他的身份。柏林当地警署对他的申诉将信将疑，无意展开调查，同时又有杀手不断追杀。虽然希望渺茫，','美国','英语','2011-02-18(美国)','upload/moviePoster/3044960336706.jpg');
INSERT INTO `movies` VALUES (23,'小情人','喜剧','Vitcha Gojiew',': 查理·哲华','10 岁的阿捷（查理•哲华 Chalee Trirat）和奈娜（霍嘉丝•芝华顾 Focus Jeerakul 饰）是一对青梅竹马的好朋友，两人同样身为理发师的父亲虽然老死不相往来，但这两个小家伙却每天都在一起玩耍。清秀的阿捷热衷和奈娜玩一些女孩子的游戏，这也招致小霸王吉扎（查郎谱•狄锦邦提哇旺 Chaleumpol Tikumpornteerawong 饰）等人的嘲笑。男孩的心中都有一份倔强和不甘，阿捷为此狠下心和奈娜决裂，两个好友从此不再见面。阿捷随着吉扎他们来到男孩的世界尽情玩耍，却不知自己的青梅竹马正要举家搬到另一个地方……\r\n　　本片荣获2004年上海国际电影节最佳导演奖、2004年泰国影评人协会最佳导演和最佳男配角奖（Chaleumpol Tikumpornteerawong）。    ','泰国','泰语',' 2004-02-06','upload/moviePoster/3048216400426.jpg');
INSERT INTO `movies` VALUES (24,'三个傻瓜','剧情 / 喜剧 ','拉库马·希拉尼','阿米尔·汗','本片根据印度畅销书作家奇坦·巴哈特（Chetan Bhagat）的处女作小说《五点人》（Five Point Someone）改编而成。法兰（马德哈万 R Madhavan 饰）、拉杜（沙曼·乔希 Sharman Joshi 饰）与兰乔（阿米尔·汗 Aamir Khan 饰）是皇家工程学院的学生，三人共居一室，结为好友。在以严格著称的学院里，兰乔是个非常与众不同的学生，他不死记硬背，甚至还公然顶撞校长“病毒”（波曼·伊拉尼 Boman Irani 饰），质疑他的教学方法。他不仅鼓动法兰与拉杜去勇敢追寻理想，还劝说校长的二女儿碧雅（卡琳娜·卡普 Kareena Kapoor 饰）离开满眼铜臭的未婚夫。兰乔的特立独行引起了模范学生“消音器”（奥米·维嘉 Omi Vaidya 饰）的不满，他约定十年后再与兰乔一决高下，看哪种生活方式更能取得成功。\r\n','印度','北印度语 / 乌尔都语','2009-12-25','upload/moviePoster/3050737756023.jpg');
INSERT INTO `movies` VALUES (25,'2010','悬疑 / 科幻 / 惊悚 / 冒险','Peter Hyams','Scheider / John Lithgow ','《2001太空漫游》后9年，前国家航天委员会主任弗洛伊德博士（罗伊•谢德 Roy Scheider 饰）接受苏美合作计划，带领发现号航天站设计者科脑博士（约翰•利思戈 John Lithgow 饰）和HAL9000电脑的创始人钱德拉博士（鲍勃•巴拉班 Bob Balaban 饰）登录木星附近的苏联航空站，与苏联宇航员卡布珂（海伦•米伦 Helen Mirren 饰）等合作，空中接轨美国发现号航天站，调查九年前的事故原因，探索木卫二的神秘黑石，并查明宇航员大卫•伯曼（凯尔•杜拉 Keir Dullea 饰）缘何神秘失踪。然而任务执行尚未过半，美苏关系愈发紧张，战争一触即发；与此同时，大卫•伯曼竟突然现身对弗洛伊德博士发出神秘警告','美国',': 英语 / 俄语','1984-12-07','upload/moviePoster/3192854571033.jpg');
INSERT INTO `movies` VALUES (26,'肩上蝶','剧情 / 爱情 / 动画 / 奇幻','张之亮',' 陈坤 / 梁咏琪 / 江一燕 / 桂纶镁 / 张之亮','这是一个奇特的岛屿，所有事物的诡异消失，都可能受历着某种未知的魔力，但让年轻科学家严国（陈坤）几乎崩溃的是，神秘植物开花的一刹那，他却忽然昏厥，而女友宝宝也突然消失。这一切打破了小岛的安宁，也让一直暗恋严国的白兰（桂纶美）日渐忧虑，却无法走进他为宝宝而封锁的内心。就在此时，女记者杨霖的突然到访，却告知了他们更可怕的“危机”，一场科学之战悄然侵袭着严国的世界，但他浑然不知，真正挽救一切的，却是那只一直陪伴在他左右的神秘蝴蝶，和她所经历的奇异之军！\r\n　　有一种魔力，它穿越彼此、穿越时空、甚至穿越生灵，只为那深刻于灵魂的爱恋。\r\n\r\n','中国大陆','汉语普通话 / 粤语','2011-07-08(中国大陆)','upload/moviePoster/3204975572006.jpg');
INSERT INTO `movies` VALUES (27,'初三大四','喜剧',': เหมันต์ เชตมี',': สโรชา ตันจรารักษ์','一次偶然的机会，读初一的小男生纳认识了在普吉开店的女网友珍，两个人通过网络熟识，以msn联系了三年，直到纳初三毕业。纳决定来普吉跟珍见面。纳的哥哥梯是医学院大四的学生，由于外形俊朗成绩优秀，所以备受女孩青睐，不少女孩主动投怀送抱，以至于让他对爱情产生了游戏的态度。但是，当他在街上偶遇女孩淳的时候，他才知道原来爱情犹如电击。巧合的是，珍和淳是普吉的一对姐妹花，而且年龄分别与纳和梯相仿。纳来到普吉与珍见面，尾随而至的梯赫然发现淳竟然是珍的姐姐。初三的这对恋人，情窦初开，对爱情充满了甜蜜的憧憬与想象。大四的这对，面临毕业走向社会，他们的抉择似乎拥有一种更理性的光辉。在风光旖旎的普吉岛上，兄弟俩和姐妹俩上演了浪漫的爱情故事……','泰国','泰语',' 2009-04-30','upload/moviePoster/3210437368969.jpg');
INSERT INTO `movies` VALUES (28,'Baywatch','剧情 / 动作 / 冒险','Gregory J. Bonann','Pamela Anderson','　美国的救生员剧《生死海滩》由大卫·哈塞尔霍夫和帕米拉·安德逊主演，是世界上收视率最高的电视连续剧。故事情节围绕海滩护卫队员营救故事展开，最大看点是海边的旖旎风景，以及无数的泳装帅哥美女。据估计在142个国家和地区内每周收看该剧的人数超过11亿。\r\n　　《Baywatch》从1989年播映到2001年共播出了11个季。国内曾引进过前2季，相信不少人看过，讲述一大堆俊男美女的海岸救生员的故事。其中男主角David Hasselhoff 曾出演过《霹雳游侠》。Baywatch比较类似于《ER》，主题曲歌词翻译过来几乎就是美国版的《接过雷锋的抢》，每集不管大事小事都要救几个落水的，就单单一个沙滩的救生员就能拍出那么多集来，每集一个故事，几乎涉及到了生活的方方面面。\r\n　　Lifeguards (and beautiful ones at that) patrol the... ','美国','英语','1989-09-22','upload/moviePoster/3256530941176.jpg');
INSERT INTO `movies` VALUES (29,'女医生','伦理','dsfgr','简·西摩尔 / 乔·兰多','\r\n女医生的获奖情况 · · · · · · ( 全部 )\r\n1996年\r\n金球奖(Golden Globe) Best Performance by an Actress in a TV-Series - Drama\r\n简·西摩尔\r\n1994年\r\n青年艺术家奖(Young Artist Award) Best New Television Series\r\n\r\n1996年\r\n青年艺术家奖(Michael Landon Award) \r\nBeth Sullivan\r\n','美国','英语','1993-01-01','upload/moviePoster/3420354861031.jpg');
INSERT INTO `movies` VALUES (30,'星际之门','奇幻 / 动作 / 冒险 / 科幻','罗兰·艾默里奇','罗兰·艾默里奇','　考古学者丹尼尔·杰克逊博士对古埃及象形文字颇有研究，对金字塔是谁建造的也有他的一套理论，但是很多人对他的观点不屑一顾，为了寻找有力的证据，丹尼尔毅然决定和政府派遣的以杰克·欧尼尔上校为首的军方小分队一起到达可以连通两个世界的所谓星际之门。他们要穿过这个“门”而进入法老传说中的神话世界。临行前，凯瑟琳将小时候捡到的一条“雷之眼”挂链交给他，说这会给他带来好运。 \r\n　　 \r\n　　 丹尼尔和小分队穿过星际之门，却发现这里只是一个荒漠的采矿区，他们想法与采矿人交流，当那些人看到丹尼尔脖子上挂的“雷之眼”挂链时，立刻全体跪拜，原来这挂链在这里是神的象征。丹尼尔结识了一个名叫舒瑞的美丽姑娘，找到了记载当地历史的文字。上面记载着：远方星球一个叫雷的人逃离濒临灭亡的家园，到银河系寻求长生之道，终于在地球发现了人类，雷利用一个男孩的好奇心，附在他的身上，有了人形后，... ','美国','英语','1994-10-28','upload/moviePoster/3504623325859.jpg');
INSERT INTO `movies` VALUES (31,'Baywatch','剧情 / 动作 / 冒险','Gregory J. Bonann','Lee Goldberg /','海魂的剧情简介 · · · · · · \r\n\r\n　　美国的救生员剧《生死海滩》由大卫·哈塞尔霍夫和帕米拉·安德逊主演，是世界上收视率最高的电视连续剧。故事情节围绕海滩护卫队员营救故事展开，最大看点是海边的旖旎风景，以及无数的泳装帅哥美女。据估计在142个国家和地区内每周收看该剧的人数超过11亿。\r\n　　《Baywatch》从1989年播映到2001年共播出了11个季。国内曾引进过前2季，相信不少人看过，讲述一大堆俊男美女的海岸救生员的故事。其中男主角David Hasselhoff 曾出演过《霹雳游侠》。Baywatch比较类似于《ER》，主题曲歌词翻译过来几乎就是美国版的《接过雷锋的抢》，每集不管大事小事都要救几个落水的，就单单一个沙滩的救生员就能拍出那么多集来，每集一个故事，几乎涉及到了生活的方方面面。\r\n　　Lifeguards (and beautiful ones at that) patrol the... ','美国','英语','1989-09-22','upload/moviePoster/3635930567888.jpg');
INSERT INTO `movies` VALUES (32,'美国队长',' 动作 / 科幻 / 冒险',' 乔·庄斯顿',' 克里斯·埃文斯 / 雨果·维文 / 汤米·李·琼斯 / 史坦利·图齐 / 理查德·阿米塔格 ','　在二次世界大战中德军美国盟军对敌。德军制造一个邪恶的替身“红头怪”来危害世界人民。具有超人能力的正义英雄“美国先生”为了解放世界人民与“红头怪”展开正义与邪恶的战争。在一次与“红头怪”交手时“美国先生”不敌掉进维加斯的冰山里。二十年后“美国先生”被意外获救，当他得知“红头怪”还在人世，正义与邪恶又将再次进行较量……\r\n\r\n','美国',' 英语','2011-07-22(美国) / 2011-09-09(中','upload/moviePoster/3716458603947.jpg');
INSERT INTO `movies` VALUES (33,'孤岛惊魂','惊悚','钟继昌','陈小春/杨幂','雄踞东南亚的亿万富豪——OEC集团总裁陈家栋（姜皓文 饰）继承父亲的产业，并决心开发位于太平洋爪哇海上一个已经废弃40年之久的小岛。虽然父亲极力阻拦，但是利益熏心的家栋依然发起了一个孤岛探险的比赛。他开出100万元的巨额奖金，引得彭非（陈小春 饰）、沈依琳（杨幂 饰）、小野宏（叶山豪 饰）等8名青年男女搭上了前往小岛的游艇。一切看似顺风顺水，游艇却在航行中遭遇了离奇海难，致令8个青年被迫滞留小岛。\r\n　　在这座岛上，他们不仅遭到疯狂野猪的袭击，还发现了一座几成废墟的麻风病人的医院。此后不久，恐怖的事情接连发生，众人陷入一个无法退出的死亡游戏之中……','中国','汉语','2011-07-08','upload/moviePoster/3757983276212.jpg');
INSERT INTO `movies` VALUES (34,'灯塔','惊悚','Michael Stokes','David Rees Snell/泰瑞·波罗','\r\n\r\n布莱恩是个摄影师，保罗肖是天文学家，将迁往得克萨斯州，在一间舒适的小公寓重建他们的生活。在三年前的万圣节上，布莱恩没有照看好儿子丹尼，结果孩子失踪了，被推定为溺死。布莱恩试图自杀，保罗牺牲了自己的事业去照料她。保罗在大学里找到了新工作。随后的日子里，保罗不断做噩梦，并出现男童幽灵。保罗决定对儿子的死一探究竟。\r\n\r\n','美国','英语','2010-07-01','upload/moviePoster/4147763390205.jpg');
INSERT INTO `movies` VALUES (35,'海上钢琴师',': 剧情 / 爱情 / 音乐','朱塞佩·托纳多雷',' 蒂姆·罗斯 / 普路特·泰勒·文斯 / 比尔·努恩 / 梅兰尼·蒂埃里 / Alberto Vazquez','　本片讲述了一个钢琴天才传奇的一生。\r\n　　1900年，Virginian号豪华邮轮上，一个孤儿被遗弃在头等舱，由船上的水手抚养长大，取名1900（蒂姆•罗斯 饰）。1900慢慢长大，显示了出了无师自通的非凡钢琴天赋，在船上的乐队表演钢琴，每个听过他演奏的人，都被深深打动。爵士乐鼻祖杰尼听说了1900的高超技艺，专门上船和他比赛，最后自叹弗如，黯然离去。\r\n',' 意大利','英语 / 法语','1998-10-28','upload/moviePoster/4184796173793.jpg');
INSERT INTO `movies` VALUES (36,'灿烂人生','爱情/剧情',' 马可·图利欧·吉欧达纳',' 马可·图利欧·吉欧达纳','片长六个小时，讲述一个意大利普通的六口之家，卡拉提（Carati），从1966年到2000年间的风风雨雨生老病死。主要角色是家中的兄弟俩：哥哥马提奥，弟弟尼可拉。 \r\n　　 \r\n　　影片开始时，兄弟俩都在上大学，暑假刚开始时，两个人试图帮助一个年轻女精神病人乔吉娜逃离精神病院，其间两天两夜的事件彻底改变了两个人的生活。尼可拉成为了精神病医师，全心全意的为了改变意大利的精神病医院里对病人的不人道待遇而努力着。马提奥却弃笔从戎，成为了警察。。。 \r\n　　 \r\n　　涵蓋意大利的近代史 \r\n　　 \r\n　　這部長達六小時的意大利家族巨片是一部充滿激情的史詩式電影，片名源自意大利導演柏索里尼(Pier Paolo Pasolini)的同名詩集，也是Alpine Troops主唱的同名舊曲。影片情節涵蓋了意大利近代發生的多次重大事件，包括六十年代佛羅倫斯的大水災；西西里人對抗黑手黨；七十年代都靈市... ','意大利','意大利语',': 2003-06-22','upload/moviePoster/4416918771041.jpg');
INSERT INTO `movies` VALUES (37,'偷自行车的人','犯罪/剧情',': 维托里奥·德·西卡',': 朗培尔托·马齐奥拉尼 / 恩佐·斯泰奥拉 ','二战过后，罗马同许多城市一样，充斥失业和贫困，人们常常为一个工作机会争得头破血流。已失业两年的里奇（Lamberto Maggiorani）费劲千辛万苦获得一份海报张贴的工作后，却为这份工作需要一辆自行车犯愁，为了以后的日子好过，他倾其所有购买了一辆新自行车，不想，他的自行车在上班第一天就被盗。\r\n　　里奇同儿子布鲁诺（Enzo Staiola）寻遍罗马大街小巷，也没能找到他赖以活命的自行车，眼见无指望的日子又要降临，里奇决定以牙还牙，可是他的运气却没有别的小偷好。','意大利','意大利语','1948-11-24','upload/moviePoster/4685876364544.jpg');
INSERT INTO `movies` VALUES (38,'听见天堂','剧情',' 克里斯提诺·波顿','马可·科奇','当上帝为你关上一扇门时，往往他会为你打开另一扇。意大利男孩米克（Luca Capriotti 饰）虽然出身在穷乡僻壤，但他从小就梦想成一流的电影大师，只因他是那么的热爱电影。然而上帝跟他开了一个最残酷的玩笑，米克玩弄一直来复枪时不幸走火，从此他只能淹没在巨大的黑暗当中。这一度令米克感到沮丧万分，仿佛活着已没有了动力。然而盲校的唐老师的一番话让米克豁然开朗，为什么音乐家在演奏时会闭上眼睛，因为那样音符会蜕变，变的更有力量。于是，米克选择了用耳朵代替眼睛，去记录他生活的点点滴滴。这时，我们才发现：原来，不仅可以看见天堂，还可以听见天堂。','意大利','意大利语',' 2006-10-17','upload/moviePoster/5065519508285.jpg');
INSERT INTO `movies` VALUES (39,'熟男我爱你','爱情',' 费德瑞克·莫恰',': 雷欧·波瓦 / 米琪拉·奎阿托希欧奇','影片讲述的是37岁的老男人和他的20岁晚辈之间的忘年恋。37岁的亚力是个上班族，长得成熟俊帅的他，最近却刚被女友给甩了。失意的亚力有天开车上班，竟不小心撞到了正要赶去上学的妮奇，还将她的机车给撞烂了。17岁的妮奇刁蛮难缠，她不但坚持要亚力送她上学，还不忘跟亚力要了电话。之后妮奇没事就call亚力带她去兜风，让她班上同学都看傻了眼。大家纷纷揣测：妮奇对成熟男人的兴趣，似乎还高过学校的男生…。正当大家怀疑她是否只是一阵新鲜感时，妮奇却突然决定要带亚力回家，介绍给她年龄相仿的爸妈……\r\n　　影片由意大利畅销作家费德瑞克·莫洽执导，执导过不少电影的莫洽，这次首度将自己的小说搬上银幕。电影原著小说在义大利卖出110万册，勾起意大利人心底对美好爱情的幻想与渴望，电影上映10天就以1500万美金成绩刷新意大利近年票房纪录。本片由是跨足好莱坞与意大利影坛的性感男星雷欧·波... ','意大利','意大利语',' 2008-01-25','upload/moviePoster/5238381686003.jpg');
INSERT INTO `movies` VALUES (40,'巴尼的人生','剧情',' Richard J. Lewis',' 保罗·吉亚玛提 ','巴尼·潘诺夫斯基 Barney Panofsky（保罗·吉亚玛提 Paul Giamatti 饰），一位酗酒、嗜烟、粗俗的曲棍球爱好者和电视导演，65岁的他陷入对自己人生的追忆中。在他的一生中，有成功的喜悦，但是更多是一次次挫折，一次次可笑的失态和尴尬。他与信奉自由的灵魂的第一任妻子克拉拉 Clara（蕾切尔·李费佛 Rachelle Lefevre 饰）在罗马相识，之后又与富有的犹太公主P夫人 Mrs. P（明妮·德里弗 Minnie Driver 饰）相爱，最终，他找到了真爱――第三任妻子米利亚姆 Miriam（罗莎曼德·派克 Rosamund Pike 饰），并与她养育了两个孩子。而与他的父亲伊西（达斯汀·霍夫曼 Dustin Hoffman 饰）之间，更像是一种与助手的关系。就这样，巴尼带给了我们一种五彩斑斓的人生，而已到暮年的他，对于自己的... ','加拿大/意大利','英语',' 2010-12-24','upload/moviePoster/5540885288470.jpg');
INSERT INTO `movies` VALUES (41,'我是爱','爱情/剧情','Luca Guadagnino',': 蒂尔达·斯温顿 / 马里莎·贝伦森','意大利米兰的瑞奇家族靠纺织业致富，在家长的寿宴上，爷爷将企业传给了儿子坦科雷德（加布里埃尔.费泽蒂 Gabriele Ferzetti 饰）和孙子爱德瓦多（弗拉维奥·帕伦蒂 Flavio Parenti 饰）。坦科雷德的俄国妻子艾玛（蒂尔达·斯维顿 Tilda Swinton 饰doardo Gabbriellini 饰）之后，艾玛被安东尼奥高超的烹饪手艺所吸引，情不自禁坠入爱河。不仅如此，艾玛还必须隐瞒她偶然发现女儿伊丽莎白（阿尔芭·洛尔瓦彻Alba Rohrwacher 饰）是同性恋的事实。与此同时，家族的事业也在变迁，尽管爱德瓦多独力反对出售家族企业，却还是无法抵挡资本全球化大潮的冲击，并购势在必行。在探讨商务的晚宴上，一个意外打破了艾玛苦心枯守的表面平静，她的灵魂就...','意大利','意大利语 / 俄语 / 英语',': 2009-09-05','upload/moviePoster/6098942281212.jpg');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table moviesimilarity
#

DROP TABLE IF EXISTS `moviesimilarity`;
CREATE TABLE `moviesimilarity` (
  `movieID1` bigint(20) NOT NULL,
  `movieID2` bigint(20) NOT NULL,
  `similarity` double default NULL,
  PRIMARY KEY  (`movieID1`,`movieID2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table moviesimilarity
#

LOCK TABLES `moviesimilarity` WRITE;
/*!40000 ALTER TABLE `moviesimilarity` DISABLE KEYS */;
/*!40000 ALTER TABLE `moviesimilarity` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table usergibberish
#

DROP TABLE IF EXISTS `usergibberish`;
CREATE TABLE `usergibberish` (
  `id` bigint(20) NOT NULL auto_increment,
  `userID` bigint(20) default NULL,
  `content` varchar(100) default NULL,
  `timestamp` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table usergibberish
#

LOCK TABLES `usergibberish` WRITE;
/*!40000 ALTER TABLE `usergibberish` DISABLE KEYS */;
INSERT INTO `usergibberish` VALUES (1,31,'测试发心情。、、、',1285214299437);
INSERT INTO `usergibberish` VALUES (2,31,'哈哈',1285215709859);
INSERT INTO `usergibberish` VALUES (3,31,'、、、',1285216276437);
INSERT INTO `usergibberish` VALUES (4,33,'新鲜事',1285246026796);
INSERT INTO `usergibberish` VALUES (5,32,'我是test2, 发个心情~~',1285248237406);
INSERT INTO `usergibberish` VALUES (6,31,'大家好啊，大家有没有看见我喜欢的电影啊',1285292578828);
/*!40000 ALTER TABLE `usergibberish` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table users
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL auto_increment,
  `email` varchar(50) default NULL,
  `nickName` varchar(20) default NULL,
  `password` varchar(30) default NULL,
  `timstamp` bigint(20) default NULL,
  `newsTypes` varchar(300) default NULL,
  `avatarLink` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table users
#

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (23,'suspense@doudouer.com','suspense','1',1316680930693,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar1.png');
INSERT INTO `users` VALUES (24,'Thriller@doudouer.com','Thriller','1',1316680931797,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar2.png');
INSERT INTO `users` VALUES (25,'Adventure@doudouer.com','Adventure','1',1316680932716,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar3.png');
INSERT INTO `users` VALUES (26,'affectional@doudouer.com','affectional','1',1316680933495,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar4.png');
INSERT INTO `users` VALUES (27,'sci_fi@doudouer.com','sci_fi','1',1316680934359,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar5.png');
INSERT INTO `users` VALUES (28,'comedy@doudouer.com','comedy','1',1316680935242,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar6.png');
INSERT INTO `users` VALUES (29,'horror@doudouer.com','horror','1',1316680935971,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar7.png');
INSERT INTO `users` VALUES (30,'action@doudouer.com','action','1',1316680936701,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar8.png');
INSERT INTO `users` VALUES (31,'test1@doudouer.com','test1','1',1316683194179,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar9.png');
INSERT INTO `users` VALUES (32,'test2@doudouer.com','test2','1',1316684246559,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar10.png');
INSERT INTO `users` VALUES (33,'test3@doudouer.com','test3','1',1316687596563,'addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview','upload/userAvatar/avatar_default.png');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

#
#  Foreign keys for table collection
#

ALTER TABLE `collection`
ADD CONSTRAINT `FK9835AE9E271AF184` FOREIGN KEY (`movieCollection`) REFERENCES `movies` (`id`),
ADD CONSTRAINT `FK9835AE9E6C5E4631` FOREIGN KEY (`whoCollect`) REFERENCES `users` (`id`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
