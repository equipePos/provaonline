CREATE DATABASE  IF NOT EXISTS `prova` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `prova`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: prova
-- ------------------------------------------------------
-- Server version	5.5.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_disciplina`
--

DROP TABLE IF EXISTS `tbl_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_disciplina` (
  `cod_disciplina` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `disciplina` varchar(100) NOT NULL,
  PRIMARY KEY (`cod_disciplina`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_disciplina`
--

LOCK TABLES `tbl_disciplina` WRITE;
/*!40000 ALTER TABLE `tbl_disciplina` DISABLE KEYS */;
INSERT INTO `tbl_disciplina` VALUES (1,'História'),(2,'Geografia'),(3,'Matemática'),(4,'Filosofia'),(5,'Algoritmos'),(6,'Programação Estruturada');
/*!40000 ALTER TABLE `tbl_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_prova`
--

DROP TABLE IF EXISTS `tbl_prova`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_prova` (
  `cod_prova` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cod_pro` int(10) unsigned NOT NULL,
  `cod_disciplina` int(10) unsigned NOT NULL,
  `tbl_usuario_idusuario` int(11) NOT NULL,
  PRIMARY KEY (`cod_prova`),
  KEY `fk_tbl_prova_tbl_disciplina1_idx` (`cod_disciplina`),
  KEY `fk_tbl_prova_tbl_usuario1_idx` (`tbl_usuario_idusuario`),
  CONSTRAINT `fk_tbl_prova_tbl_disciplina1` FOREIGN KEY (`cod_disciplina`) REFERENCES `tbl_disciplina` (`cod_disciplina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_prova_tbl_usuario1` FOREIGN KEY (`tbl_usuario_idusuario`) REFERENCES `tbl_usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_prova`
--

LOCK TABLES `tbl_prova` WRITE;
/*!40000 ALTER TABLE `tbl_prova` DISABLE KEYS */;
INSERT INTO `tbl_prova` VALUES (1,1,6,30),(2,2,6,30),(3,3,3,30),(4,4,5,30),(5,5,4,30),(6,6,5,30);
/*!40000 ALTER TABLE `tbl_prova` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_prova_feita`
--

DROP TABLE IF EXISTS `tbl_prova_feita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_prova_feita` (
  `cod_prova` int(10) unsigned NOT NULL,
  `cod_aluno` int(10) unsigned NOT NULL,
  `cod_questao` int(10) unsigned NOT NULL,
  `resposta` varchar(1) DEFAULT NULL,
  `tbl_usuario_idusuario` int(11) NOT NULL,
  KEY `fk_tbl_prova_has_tbl_aluno_tbl_prova1_idx` (`cod_prova`),
  KEY `fk_tbl_prova_feita_tbl_questao1` (`cod_questao`),
  KEY `fk_tbl_prova_feita_tbl_usuario1_idx` (`tbl_usuario_idusuario`),
  CONSTRAINT `fk_tbl_prova_feita_tbl_questao1` FOREIGN KEY (`cod_questao`) REFERENCES `tbl_questao` (`cod_questao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_prova_feita_tbl_usuario1` FOREIGN KEY (`tbl_usuario_idusuario`) REFERENCES `tbl_usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_prova_has_tbl_aluno_tbl_prova1` FOREIGN KEY (`cod_prova`) REFERENCES `tbl_prova` (`cod_prova`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_prova_feita`
--

LOCK TABLES `tbl_prova_feita` WRITE;
/*!40000 ALTER TABLE `tbl_prova_feita` DISABLE KEYS */;
INSERT INTO `tbl_prova_feita` VALUES (1,1,2,'a',1),(1,1,3,'a',1),(1,1,4,'a',1),(1,1,5,'a',1),(1,1,6,'a',1),(1,1,7,'a',1),(1,1,8,'a',1),(1,1,9,'a',1),(1,1,10,'a',1),(1,1,11,'a',1),(2,1,13,'a',1),(2,1,14,'a',1),(2,1,15,'a',1),(2,1,16,'b',1),(2,1,17,'b',1),(4,31,23,'c',31),(4,31,24,'c',31),(4,31,25,'c',31),(4,31,26,'c',31),(4,31,27,'c',31),(4,31,23,'d',31),(4,31,24,'a',31),(4,31,25,'a',31),(4,31,26,'a',31),(4,31,27,'a',31),(3,31,18,'b',31),(3,31,19,'b',31),(3,31,20,'b',31),(3,31,21,'a',31),(3,31,22,'a',31),(2,31,13,'a',31),(2,31,14,'b',31),(2,31,15,'a',31),(2,31,16,'a',31),(2,31,17,'a',31),(3,1,18,'a',1),(3,1,19,'a',1),(3,1,20,'a',1),(3,1,21,'a',1),(3,1,22,'a',1),(6,31,33,'b',31),(6,31,34,'d',31),(6,31,35,'c',31),(6,31,36,'a',31),(6,31,37,'a',31);
/*!40000 ALTER TABLE `tbl_prova_feita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_questao`
--

DROP TABLE IF EXISTS `tbl_questao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_questao` (
  `cod_questao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cod_prova` int(10) unsigned NOT NULL,
  `enunciado` varchar(500) NOT NULL,
  `correta` char(1) DEFAULT NULL,
  `alternativa_a` varchar(100) DEFAULT NULL,
  `alternativa_b` varchar(100) DEFAULT NULL,
  `alternativa_c` varchar(100) DEFAULT NULL,
  `alternativa_d` varchar(100) DEFAULT NULL,
  `alternativa_e` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cod_questao`),
  KEY `fk_tbl_questao_tbl_prova` (`cod_prova`),
  CONSTRAINT `fk_tbl_questao_tbl_prova` FOREIGN KEY (`cod_prova`) REFERENCES `tbl_prova` (`cod_prova`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_questao`
--

LOCK TABLES `tbl_questao` WRITE;
/*!40000 ALTER TABLE `tbl_questao` DISABLE KEYS */;
INSERT INTO `tbl_questao` VALUES (2,1,'isto é um teste de enunciado  para a pergunta 1','b','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(3,1,'isto é um teste de enunciado  para a pergunta 2','c','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(4,1,'isto é um teste de enunciado  para a pergunta 3','d','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(5,1,'isto é um teste de enunciado  para a pergunta  4','e','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(6,1,'isto é um teste de enunciado  para a pergunta 5','b','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(7,1,'isto é um teste de enunciado  para a pergunta  6','b','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(8,1,'isto é um teste de enunciado  para a pergunta 7','c','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(9,1,'isto é um teste de enunciado  para a pergunta  8','c','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(10,1,'isto é um teste de enunciado  para a pergunta  9','d','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(11,1,'isto é um teste de enunciado  para a pergunta  10','d','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(13,2,'isto é um teste de enunciado  para a pergunta  1','a','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(14,2,'isto é um teste de enunciado  para a pergunta  2','a','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(15,2,'isto é um teste de enunciado  para a pergunta  3','b','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(16,2,'isto é um teste de enunciado  para a pergunta  4','b','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(17,2,'isto é um teste de enunciado  para a pergunta  5','c','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(18,3,'isto é um teste de enunciado  para a pergunta 1','c','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(19,3,'isto é um teste de enunciado  para a pergunta 2','d','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(20,3,'isto é um teste de enunciado  para a pergunta 3','d','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(21,3,'isto é um teste de enunciado  para a pergunta 4','e','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(22,3,'isto é um teste de enunciado  para a pergunta 5','e','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(23,4,'isto é um teste de enunciado  para a pergunta 1','e','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(24,4,'isto é um teste de enunciado  para a pergunta 2','e','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(25,4,'isto é um teste de enunciado  para a pergunta 3','d','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(26,4,'isto é um teste de enunciado  para a pergunta 4','d','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(27,4,'isto é um teste de enunciado  para a pergunta 5','c','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(28,5,'isto é um teste de enunciado  para a pergunta 1','c','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(29,5,'isto é um teste de enunciado  para a pergunta 2','b','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(30,5,'isto é um teste de enunciado  para a pergunta 3','b','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(31,5,'isto é um teste de enunciado  para a pergunta 4','a','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(32,5,'isto é um teste de enunciado  para a pergunta 5','a','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(33,6,'isto é um teste de enunciado  para a pergunta 1','c','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(34,6,'isto é um teste de enunciado  para a pergunta 2','d','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(35,6,'isto é um teste de enunciado  para a pergunta 3','c','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(36,6,'isto é um teste de enunciado  para a pergunta 4','b','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima'),(37,6,'isto é um teste de enunciado  para a pergunta 5','b','esta é a primeira resposta para a pergunta acima','esta é a segunda resposta para a pergunta acima','esta é a terceira resposta para a pergunta acima','esta é a quarta resposta para a pergunta acima','esta é a quinta resposta para a pergunta acima');
/*!40000 ALTER TABLE `tbl_questao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_tipo_usuario`
--

DROP TABLE IF EXISTS `tbl_tipo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_tipo_usuario` (
  `id_tipo_usuario` int(10) NOT NULL AUTO_INCREMENT,
  `tipo_usuario` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_tipo_usuario`
--

LOCK TABLES `tbl_tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_tipo_usuario` DISABLE KEYS */;
INSERT INTO `tbl_tipo_usuario` VALUES (1,'Aluno'),(2,'Professor'),(3,'Coordenador');
/*!40000 ALTER TABLE `tbl_tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usuario` (
  `idusuario` int(10) NOT NULL AUTO_INCREMENT,
  `tbl_tipo_usuario_id_tipo_usuario` int(11) NOT NULL,
  `ra_rm_usuario` varchar(10) DEFAULT NULL,
  `nome_usuario` varchar(100) DEFAULT NULL,
  `rg_usuario` varchar(15) DEFAULT NULL,
  `email_usuario` varchar(80) DEFAULT NULL,
  `telefone_usuario` varchar(13) DEFAULT NULL,
  `login_usuario` varchar(30) DEFAULT NULL,
  `cpf_usuario` char(14) DEFAULT NULL,
  `senha_usuario` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_tbl_usuario_tbl_tipo_usuario1_idx` (`tbl_tipo_usuario_id_tipo_usuario`),
  CONSTRAINT `fk_tbl_usuario_tbl_tipo_usuario1` FOREIGN KEY (`tbl_tipo_usuario_id_tipo_usuario`) REFERENCES `tbl_tipo_usuario` (`id_tipo_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT INTO `tbl_usuario` VALUES (1,1,'1234','Joca Boboca','12,345,678-9','joca@boboca.com','11-97548-5689','joca','123,456,789-85','1234'),(30,2,'123','Helvetica','12.345.987-8','helvinha@gmail.com','11-9875-9898','helvetica','123.654.967-89','1234'),(31,1,'4556','Cecilia','1132456','ceci@lia.gmail.com',NULL,'ceci','45646','123');
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `vw_prova_feita`
--

DROP TABLE IF EXISTS `vw_prova_feita`;
/*!50001 DROP VIEW IF EXISTS `vw_prova_feita`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_prova_feita` (
  `cod_prova` tinyint NOT NULL,
  `cod_aluno` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `vw_provas`
--

DROP TABLE IF EXISTS `vw_provas`;
/*!50001 DROP VIEW IF EXISTS `vw_provas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_provas` (
  `cod_prova` tinyint NOT NULL,
  `cod_pro` tinyint NOT NULL,
  `cod_disciplina` tinyint NOT NULL,
  `tbl_usuario_idusuario` tinyint NOT NULL,
  `codprova` tinyint NOT NULL,
  `codaluno` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `vw_provas_disciplina`
--

DROP TABLE IF EXISTS `vw_provas_disciplina`;
/*!50001 DROP VIEW IF EXISTS `vw_provas_disciplina`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_provas_disciplina` (
  `cod_prova` tinyint NOT NULL,
  `cod_disciplina` tinyint NOT NULL,
  `disciplina` tinyint NOT NULL,
  `codaluno` tinyint NOT NULL,
  `codprova` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'prova'
--

--
-- Final view structure for view `vw_prova_feita`
--

/*!50001 DROP TABLE IF EXISTS `vw_prova_feita`*/;
/*!50001 DROP VIEW IF EXISTS `vw_prova_feita`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_prova_feita` AS select distinct `tbl_prova_feita`.`cod_prova` AS `cod_prova`,`tbl_prova_feita`.`cod_aluno` AS `cod_aluno` from `tbl_prova_feita` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_provas`
--

/*!50001 DROP TABLE IF EXISTS `vw_provas`*/;
/*!50001 DROP VIEW IF EXISTS `vw_provas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_provas` AS select `p`.`cod_prova` AS `cod_prova`,`p`.`cod_pro` AS `cod_pro`,`p`.`cod_disciplina` AS `cod_disciplina`,`p`.`tbl_usuario_idusuario` AS `tbl_usuario_idusuario`,`v`.`cod_prova` AS `codprova`,`v`.`cod_aluno` AS `codaluno` from (`tbl_prova` `p` left join `vw_prova_feita` `v` on((`p`.`cod_prova` = `v`.`cod_prova`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_provas_disciplina`
--

/*!50001 DROP TABLE IF EXISTS `vw_provas_disciplina`*/;
/*!50001 DROP VIEW IF EXISTS `vw_provas_disciplina`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_provas_disciplina` AS select `p`.`cod_prova` AS `cod_prova`,`d`.`cod_disciplina` AS `cod_disciplina`,`d`.`disciplina` AS `disciplina`,`p`.`codaluno` AS `codaluno`,`p`.`codprova` AS `codprova` from (`tbl_disciplina` `d` join `vw_provas` `p`) where (`d`.`cod_disciplina` = `p`.`cod_disciplina`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-10-18 14:03:44
