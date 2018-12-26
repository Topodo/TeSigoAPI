-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tesis-test
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `a_completado`
--

DROP TABLE IF EXISTS `a_completado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `a_completado` (
  `ID_A_COMPLETADO` int(11) NOT NULL AUTO_INCREMENT,
  `INDICADOR_COMPLETADO` tinyint(1) DEFAULT NULL,
  `ID_INDICADOR` int(11) NOT NULL,
  `ID_ALUMNO` int(11) NOT NULL,
  PRIMARY KEY (`ID_A_COMPLETADO`),
  KEY `fk_a_completado_alumno1_idx` (`ID_ALUMNO`),
  KEY `fk_a_completado_indicador_evaluacion_idx` (`ID_INDICADOR`),
  CONSTRAINT `FKk8g74a783gouqr4nqmbfpin3u` FOREIGN KEY (`ID_ALUMNO`) REFERENCES `alumno` (`id_alumno`),
  CONSTRAINT `FKqdw0q13ttjgpuflyd9e2j8aql` FOREIGN KEY (`ID_INDICADOR`) REFERENCES `indicador_evaluacion` (`id_indicador`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a_completado`
--

LOCK TABLES `a_completado` WRITE;
/*!40000 ALTER TABLE `a_completado` DISABLE KEYS */;
INSERT INTO `a_completado` VALUES (1,1,1,1),(2,1,1,2),(3,1,1,3),(4,1,2,1),(5,0,3,1),(6,1,4,1),(7,1,5,1),(8,0,6,1),(9,1,7,1),(10,1,8,1),(11,0,9,1),(12,1,10,1),(13,1,2,2),(14,1,3,2),(15,1,4,2),(16,1,5,2),(17,1,6,2),(18,1,7,2),(19,1,8,2),(20,1,9,2),(21,0,10,2),(22,1,2,3),(23,1,3,3),(24,1,4,3),(25,1,5,3),(26,0,6,3),(27,0,7,3),(28,0,8,3),(29,0,9,3),(30,1,10,3);
/*!40000 ALTER TABLE `a_completado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `alumno` (
  `ID_ALUMNO` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE_ALUMNO` varchar(150) DEFAULT NULL,
  `APELLIDO_PATERNO_ALUMNO` varchar(150) DEFAULT NULL,
  `APELLIDO_MATERNO_ALUMNO` varchar(150) DEFAULT NULL,
  `EDAD_ALUMNO` int(11) DEFAULT NULL,
  `ID_CURSO` int(11) NOT NULL,
  PRIMARY KEY (`ID_ALUMNO`),
  KEY `fk_alumno_curso1_idx` (`ID_CURSO`),
  CONSTRAINT `FKai7w6ro86sq58nbm0qf3gyckf` FOREIGN KEY (`ID_CURSO`) REFERENCES `curso` (`id_curso`),
  CONSTRAINT `fk_alumno_curso1` FOREIGN KEY (`ID_CURSO`) REFERENCES `curso` (`id_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (1,'pedrito','perez','contreras',10,3),(2,'juanito','sanchez','vasquez',9,3),(3,'Daniela','Torres','Rojas',10,3);
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ar_internal_metadata`
--

DROP TABLE IF EXISTS `ar_internal_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ar_internal_metadata` (
  `key` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ar_internal_metadata`
--

LOCK TABLES `ar_internal_metadata` WRITE;
/*!40000 ALTER TABLE `ar_internal_metadata` DISABLE KEYS */;
INSERT INTO `ar_internal_metadata` VALUES ('environment','development','2018-11-18 03:17:13','2018-11-18 03:17:13');
/*!40000 ALTER TABLE `ar_internal_metadata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `curso` (
  `ID_CURSO` int(11) NOT NULL AUTO_INCREMENT,
  `GRADO_CURSO` varchar(150) DEFAULT NULL,
  `CANTIDAD_ALUMNOS` int(11) NOT NULL,
  PRIMARY KEY (`ID_CURSO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,'Cuarto básico',3),(3,'2° Básico',10);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formulario_evidencia`
--

DROP TABLE IF EXISTS `formulario_evidencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `formulario_evidencia` (
  `id_evidencia` bigint(20) NOT NULL AUTO_INCREMENT,
  `contexto_evidencia` varchar(255) DEFAULT NULL,
  `fecha_evidencia` datetime DEFAULT NULL,
  `firebase_id` varchar(255) DEFAULT NULL,
  `nombre_evidencia` varchar(255) DEFAULT NULL,
  `id_alumno` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_evidencia`),
  KEY `FKk4decx2fg2ajqlm46acsxntr1` (`id_alumno`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formulario_evidencia`
--

LOCK TABLES `formulario_evidencia` WRITE;
/*!40000 ALTER TABLE `formulario_evidencia` DISABLE KEYS */;
INSERT INTO `formulario_evidencia` VALUES (1,'Vi al cabro haciendo algo bacán','2018-12-26 12:45:30',NULL,'Cabro haciendo algo bacán',1),(6,'Vi al cabro haciendo algo fome','2018-12-26 12:45:30',NULL,'Cabro haciendo algo fome',1);
/*!40000 ALTER TABLE `formulario_evidencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (2),(2),(2),(2),(2),(2),(2);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicador_evaluacion`
--

DROP TABLE IF EXISTS `indicador_evaluacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `indicador_evaluacion` (
  `ID_INDICADOR` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION_INDICADOR` text,
  `ID_OBJETIVO` int(11) NOT NULL,
  PRIMARY KEY (`ID_INDICADOR`),
  KEY `fk_indicador_evaluacion_objetivo_aprendizaje1_idx` (`ID_OBJETIVO`),
  CONSTRAINT `FKf34rurqi96wdtkkmyomx2sch2` FOREIGN KEY (`ID_OBJETIVO`) REFERENCES `objetivo_aprendizaje` (`id_objetivo`),
  CONSTRAINT `fk_indicador_evaluacion_objetivo_aprendizaje1` FOREIGN KEY (`ID_OBJETIVO`) REFERENCES `objetivo_aprendizaje` (`id_objetivo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicador_evaluacion`
--

LOCK TABLES `indicador_evaluacion` WRITE;
/*!40000 ALTER TABLE `indicador_evaluacion` DISABLE KEYS */;
INSERT INTO `indicador_evaluacion` VALUES (1,'Primer y único indicador',1),(2,'Desarrollan ideas para sus trabajos personales basados en la observación de imágenes, obras de arte, experiencias personales e imaginación',6),(3,'Identifican características personales y aspectos de su propia identidad y la expresan en pinturas y esculturas.',6),(4,'Describen observaciones de imágenes y de obras de arte con temas patrimoniales, y de figura humana.',6),(5,'Seleccionan entre materiales dados por el profesor para desarrollar sus trabajos de arte.',6),(6,'Expresan emociones e ideas en pinturas y collages con el tema del retrato (persona, familiar y colectivo).',6),(7,'Crean pinturas, esculturas y collages con el tema de: \\n- retrato y autorretrato\\n- patrimonio cultural de Chile',6),(8,'Comunican oralmente, por escrito o usando la expresión corporal, lo que sienten (sentimientos y emociones) y piensan de las obras de arte.',7),(9,'Explican sus impresiones de las obras de arte oralmente o usando la expresión corporal.',7),(10,'Observan y describen en las obras de arte: \\n- los elementos (forma y colores) y objetos que contienen.\\n- las escenas, personajes y temas\\n- sus aspectos originales y posibles significados.',7);
/*!40000 ALTER TABLE `indicador_evaluacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetivo_aprendizaje`
--

DROP TABLE IF EXISTS `objetivo_aprendizaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `objetivo_aprendizaje` (
  `ID_OBJETIVO` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION_OBJETIVO` text,
  `ID_UNIDAD` int(11) NOT NULL,
  PRIMARY KEY (`ID_OBJETIVO`),
  KEY `fk_objetivo_aprendizaje_unidad1_idx` (`ID_UNIDAD`),
  CONSTRAINT `FKdao8liignebi5ol8r4iog2ofc` FOREIGN KEY (`ID_UNIDAD`) REFERENCES `unidad` (`id_unidad`),
  CONSTRAINT `fk_objetivo_aprendizaje_unidad1` FOREIGN KEY (`ID_UNIDAD`) REFERENCES `unidad` (`id_unidad`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetivo_aprendizaje`
--

LOCK TABLES `objetivo_aprendizaje` WRITE;
/*!40000 ALTER TABLE `objetivo_aprendizaje` DISABLE KEYS */;
INSERT INTO `objetivo_aprendizaje` VALUES (1,'Primer objetivo',1),(6,'Expresar y crear trabajos de arte a partir de la observación del: \\n> entorno natural: _ gura humana y paisajes chilenos \\n> entorno cultural: personas y patrimonio cultural de Chile \\n> entorno artístico: obras de arte local, chileno, latinoamericano y del resto del mundo',2),(7,'Comunicar y explicar sus impresiones de lo que sienten y piensan de obras de arte por variados medios.',2);
/*!40000 ALTER TABLE `objetivo_aprendizaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `profesor` (
  `ID_PROFESOR` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE_PROFESOR` varchar(150) DEFAULT NULL,
  `APELLIDO_PATERNO` varchar(150) DEFAULT NULL,
  `APELLIDO_MATERNO` varchar(150) DEFAULT NULL,
  `CORREO_ELECTRONICO` varchar(150) DEFAULT NULL,
  `FIREBASE_UID` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID_PROFESOR`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (1,'Cecilia','Maturana','Ibaceta','cecilia.maturana.ibaceta@gmail.com','Qt5i8i3Mk2U3P1fbo1djhV8bHB73'),(2,'Ariel','Tirado','Maturana','ariel.tirado@usach.cl','Qt5i8i3Mk2U3P1fbo1djhV8bHB74');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor_cursos`
--

DROP TABLE IF EXISTS `profesor_cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `profesor_cursos` (
  `curso_id` bigint(20) NOT NULL,
  `profesor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`curso_id`,`profesor_id`),
  KEY `FKc6t5xpoe39muq9tm3e6snd7gr` (`profesor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor_cursos`
--

LOCK TABLES `profesor_cursos` WRITE;
/*!40000 ALTER TABLE `profesor_cursos` DISABLE KEYS */;
INSERT INTO `profesor_cursos` VALUES (1,1),(3,1);
/*!40000 ALTER TABLE `profesor_cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporte`
--

DROP TABLE IF EXISTS `reporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reporte` (
  `ID_REPORTE` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE_PROFESOR` varchar(150) DEFAULT NULL,
  `DESCRIPCION_REPORTE` text,
  `ID_ALUMNO` int(11) NOT NULL,
  `ASUNTO` text,
  PRIMARY KEY (`ID_REPORTE`),
  KEY `fk_reporte_alumno1_idx` (`ID_ALUMNO`),
  CONSTRAINT `FK699iicpjxgx0i9x2lf4x804ee` FOREIGN KEY (`ID_ALUMNO`) REFERENCES `alumno` (`id_alumno`),
  CONSTRAINT `fk_reporte_alumno1` FOREIGN KEY (`ID_ALUMNO`) REFERENCES `alumno` (`id_alumno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporte`
--

LOCK TABLES `reporte` WRITE;
/*!40000 ALTER TABLE `reporte` DISABLE KEYS */;
INSERT INTO `reporte` VALUES (1,'Maria Cecilia Maturana','Primer reporte sobre pedrito',1,'asunto del reporte');
/*!40000 ALTER TABLE `reporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schema_migrations`
--

DROP TABLE IF EXISTS `schema_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `schema_migrations` (
  `version` varchar(255) NOT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schema_migrations`
--

LOCK TABLES `schema_migrations` WRITE;
/*!40000 ALTER TABLE `schema_migrations` DISABLE KEYS */;
INSERT INTO `schema_migrations` VALUES ('20181118031101');
/*!40000 ALTER TABLE `schema_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad`
--

DROP TABLE IF EXISTS `unidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `unidad` (
  `ID_UNIDAD` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE_UNIDAD` varchar(150) DEFAULT NULL,
  `OBJETIVO_TRANSVERSAL` text,
  `EJES` varchar(150) DEFAULT NULL,
  `BIMESTRE` varchar(150) DEFAULT NULL,
  `ID_CURSO` int(11) NOT NULL,
  PRIMARY KEY (`ID_UNIDAD`),
  KEY `fk_unidad_curso1_idx` (`ID_CURSO`),
  CONSTRAINT `FKg3p49r3c9yyepxktyu95sse5w` FOREIGN KEY (`ID_CURSO`) REFERENCES `curso` (`id_curso`),
  CONSTRAINT `fk_unidad_curso1` FOREIGN KEY (`ID_CURSO`) REFERENCES `curso` (`id_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad`
--

LOCK TABLES `unidad` WRITE;
/*!40000 ALTER TABLE `unidad` DISABLE KEYS */;
INSERT INTO `unidad` VALUES (1,'Primera unidad','we','as','Marzo - Abril',1),(2,'Unidad N°2','Ejercitar la habilidad de expresar y comunicar las','Expresar y crear visualmente','Mayo-Junio-Julio',3),(3,'Unidad N°3','e_e','e.e','ewe',3);
/*!40000 ALTER TABLE `unidad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-26  3:35:48
