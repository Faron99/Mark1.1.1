-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 13, 2015 at 08:37 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mark4.1`
--

-- --------------------------------------------------------

--
-- Table structure for table `core_team`
--

CREATE TABLE IF NOT EXISTS `core_team` (
`idcore_team` int(11) NOT NULL,
  `name` varchar(105) NOT NULL,
  `charge` varchar(45) NOT NULL,
  `TAC_idtac` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_team`
--

INSERT INTO `core_team` (`idcore_team`, `name`, `charge`, `TAC_idtac`) VALUES
(1, 'cristian', 'gerente', 1),
(2, 'miguel', 'tronco', 1);

-- --------------------------------------------------------

--
-- Table structure for table `issue`
--

CREATE TABLE IF NOT EXISTS `issue` (
`idissue` int(11) NOT NULL,
  `issue` int(11) NOT NULL,
  `root_cause` varchar(200) NOT NULL,
  `recommend` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `TAC_idtac` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `issue`
--

INSERT INTO `issue` (`idissue`, `issue`, `root_cause`, `recommend`, `description`, `TAC_idtac`) VALUES
(1, 1, 'se rompio el orto', 'lavarte bien', 'jkssssssssssssssssssss', 1),
(2, 2, 'se rompio la colita', 'tsss que mal, hazlo de nuevo', 'jksjsjks', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pm`
--

CREATE TABLE IF NOT EXISTS `pm` (
`idpm` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `office` varchar(45) DEFAULT NULL,
  `workcall` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `image` varchar(70) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pm`
--

INSERT INTO `pm` (`idpm`, `name`, `office`, `workcall`, `email`, `image`) VALUES
(1, 'mario rajoy', 'negra', '88989', 'jkbk@dkjd.com', ''),
(2, 'pepe el toro', 'kjdss', 'ewu', '232@kdk.com', ''),
(3, 'Fernandez Abigail', 'MX-Tijuana', '6649794593', 'abigail.FERNANDEZ', 'imagenes/Abigail.png'),
(4, 'Hernandez Luis', 'MX-Tijuana', '6649794607', 'luis.hernandez', 'imagenes/hernandez.png'),
(5, 'Huerta Alfonso', 'MX-Tijuana', '6646474815', 'Alfonso.HUERTA', 'imagenes/Alfonso.png'),
(6, 'King-Baird Claudia', 'MX-Tijuana', '6649794522', 'Claudia.KING', 'imagenes/claudia.png');

-- --------------------------------------------------------

--
-- Table structure for table `tac`
--

CREATE TABLE IF NOT EXISTS `tac` (
`idtac` int(11) NOT NULL,
  `name` varchar(7) NOT NULL,
  `PM_idpm` int(11) NOT NULL,
  `description` varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tac`
--

INSERT INTO `tac` (`idtac`, `name`, `PM_idpm`, `description`) VALUES
(1, 'tac1000', 1, 'muy bonita, pero muy fea'),
(2, 'tac1001', 1, 'buena onda'),
(3, 'tac1002', 1, '2x2=4'),
(4, 'tac1003', 2, 'simon simon'),
(5, 'TAC1235', 6, 'NCO2587 with increased mic sensitivity');

-- --------------------------------------------------------

--
-- Table structure for table `wiki`
--

CREATE TABLE IF NOT EXISTS `wiki` (
`idWiki` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Model` varchar(45) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Image` varchar(45) DEFAULT NULL,
  `Disp` varchar(45) DEFAULT NULL,
  `Tec` varchar(45) DEFAULT NULL,
  `Ent` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wiki`
--

INSERT INTO `wiki` (`idWiki`, `Name`, `Model`, `Description`, `Image`, `Disp`, `Tec`, `Ent`) VALUES
(1, 'Blackwire', '420', 'Dispositivo de conexion usb', 'PLC/420', 'Ordenador', 'DECT', 'Microsoft Lync'),
(2, 'Blackwire', '470', 'Dispositivo de conexion bluetooth', 'PLC/470', 'Ordenador/Cel', 'DECT', 'No Microsoft Lync');

-- --------------------------------------------------------

--
-- Table structure for table `wiki_has_issue`
--

CREATE TABLE IF NOT EXISTS `wiki_has_issue` (
  `Wiki_idWiki` int(11) NOT NULL,
  `issue_idissue` int(11) NOT NULL,
  `demas` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `core_team`
--
ALTER TABLE `core_team`
 ADD PRIMARY KEY (`idcore_team`), ADD KEY `fk_CORE_TEAM_TAC1_idx` (`TAC_idtac`);

--
-- Indexes for table `issue`
--
ALTER TABLE `issue`
 ADD PRIMARY KEY (`idissue`), ADD KEY `fk_ISSUE_TAC1_idx` (`TAC_idtac`);

--
-- Indexes for table `pm`
--
ALTER TABLE `pm`
 ADD PRIMARY KEY (`idpm`);

--
-- Indexes for table `tac`
--
ALTER TABLE `tac`
 ADD PRIMARY KEY (`idtac`), ADD UNIQUE KEY `name_UNIQUE` (`name`), ADD KEY `fk_TAC_PM_idx` (`PM_idpm`);

--
-- Indexes for table `wiki`
--
ALTER TABLE `wiki`
 ADD PRIMARY KEY (`idWiki`);

--
-- Indexes for table `wiki_has_issue`
--
ALTER TABLE `wiki_has_issue`
 ADD PRIMARY KEY (`Wiki_idWiki`,`issue_idissue`), ADD KEY `fk_Wiki_has_issue_Wiki1_idx` (`Wiki_idWiki`), ADD KEY `issue_idissue` (`issue_idissue`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `core_team`
--
ALTER TABLE `core_team`
MODIFY `idcore_team` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `issue`
--
ALTER TABLE `issue`
MODIFY `idissue` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `pm`
--
ALTER TABLE `pm`
MODIFY `idpm` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `tac`
--
ALTER TABLE `tac`
MODIFY `idtac` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `wiki`
--
ALTER TABLE `wiki`
MODIFY `idWiki` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `core_team`
--
ALTER TABLE `core_team`
ADD CONSTRAINT `fk_CORE_TEAM_TAC1` FOREIGN KEY (`TAC_idtac`) REFERENCES `tac` (`idtac`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `issue`
--
ALTER TABLE `issue`
ADD CONSTRAINT `fk_ISSUE_TAC1` FOREIGN KEY (`TAC_idtac`) REFERENCES `tac` (`idtac`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tac`
--
ALTER TABLE `tac`
ADD CONSTRAINT `fk_TAC_PM` FOREIGN KEY (`PM_idpm`) REFERENCES `pm` (`idpm`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `wiki_has_issue`
--
ALTER TABLE `wiki_has_issue`
ADD CONSTRAINT `fk_Wiki_has_issue_Wiki1` FOREIGN KEY (`Wiki_idWiki`) REFERENCES `wiki` (`idWiki`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `wiki_has_issue_ibfk_1` FOREIGN KEY (`issue_idissue`) REFERENCES `tac` (`idtac`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
