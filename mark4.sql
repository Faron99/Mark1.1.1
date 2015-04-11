-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 12-04-2015 a las 00:51:53
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `mark4`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `core_team`
--

CREATE TABLE IF NOT EXISTS `core_team` (
  `idcore_team` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(105) NOT NULL,
  `charge` varchar(45) NOT NULL,
  `TAC_idtac` int(11) NOT NULL,
  PRIMARY KEY (`idcore_team`),
  KEY `fk_CORE_TEAM_TAC1_idx` (`TAC_idtac`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `core_team`
--

INSERT INTO `core_team` (`idcore_team`, `name`, `charge`, `TAC_idtac`) VALUES
(1, 'cristian', 'gerente', 1),
(2, 'miguel', 'tronco', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `issue`
--

CREATE TABLE IF NOT EXISTS `issue` (
  `idissue` int(11) NOT NULL AUTO_INCREMENT,
  `issue` int(11) NOT NULL,
  `root_cause` varchar(200) NOT NULL,
  `recommend` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `TAC_idtac` int(11) NOT NULL,
  PRIMARY KEY (`idissue`),
  KEY `fk_ISSUE_TAC1_idx` (`TAC_idtac`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `issue`
--

INSERT INTO `issue` (`idissue`, `issue`, `root_cause`, `recommend`, `description`, `TAC_idtac`) VALUES
(1, 1, 'se rompio el orto', 'lavarte bien', 'jkssssssssssssssssssss', 1),
(2, 2, 'se rompio la colita', 'tsss que mal, hazlo de nuevo', 'jksjsjks', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pm`
--

CREATE TABLE IF NOT EXISTS `pm` (
  `idpm` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `office` varchar(45) DEFAULT NULL,
  `workcall` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpm`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `pm`
--

INSERT INTO `pm` (`idpm`, `name`, `office`, `workcall`, `email`) VALUES
(1, 'mario rajoy', 'negra', '88989', 'jkbk@dkjd.com'),
(2, 'pepe el toro', 'kjdss', 'ewu', '232@kdk.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tac`
--

CREATE TABLE IF NOT EXISTS `tac` (
  `idtac` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(7) NOT NULL,
  `PM_idpm` int(11) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`idtac`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_TAC_PM_idx` (`PM_idpm`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `tac`
--

INSERT INTO `tac` (`idtac`, `name`, `PM_idpm`, `description`) VALUES
(1, 'tac1000', 1, 'muy bonita, pero muy fea'),
(2, 'tac1001', 1, 'buena onda'),
(3, 'tac1002', 1, '2x2=4'),
(4, 'tac1003', 2, 'simon simon');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `core_team`
--
ALTER TABLE `core_team`
  ADD CONSTRAINT `fk_CORE_TEAM_TAC1` FOREIGN KEY (`TAC_idtac`) REFERENCES `tac` (`idtac`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `issue`
--
ALTER TABLE `issue`
  ADD CONSTRAINT `fk_ISSUE_TAC1` FOREIGN KEY (`TAC_idtac`) REFERENCES `tac` (`idtac`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tac`
--
ALTER TABLE `tac`
  ADD CONSTRAINT `fk_TAC_PM` FOREIGN KEY (`PM_idpm`) REFERENCES `pm` (`idpm`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
