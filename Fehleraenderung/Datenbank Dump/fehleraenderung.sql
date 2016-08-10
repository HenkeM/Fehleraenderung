-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 25, 2016 at 10:18 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `fehleraenderung`
--

-- --------------------------------------------------------

--
-- Table structure for table `fehler`
--

CREATE TABLE IF NOT EXISTS `fehler` (
`feNr` int(11) NOT NULL,
  `prioID` int(11) NOT NULL,
  `proNr` int(11) NOT NULL,
  `staNr` int(11) NOT NULL,
  `erstelltVon` int(11) NOT NULL,
  `zugewiesenAn` int(11) NOT NULL,
  `bezeichnung` varchar(64) NOT NULL,
  `beschreibung` varchar(2000) NOT NULL,
  `erstelltAm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `zugewiesenAm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fehler`
--

INSERT INTO `fehler` (`feNr`, `prioID`, `proNr`, `staNr`, `erstelltVon`, `zugewiesenAn`, `bezeichnung`, `beschreibung`, `erstelltAm`, `zugewiesenAm`) VALUES
(1, 1, 1, 5, 1, 2, 'Erster Fehler', 'Programm spricht nicht mehr mit mir', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(2, 2, 1, 4, 1, 2, 'Zweiter Fehler', 'Schwarz-weiße Streifen auf dem Bildschirm', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(3, 3, 1, 3, 1, 2, 'Dritter Fehler', 'Alles laeuft schief', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(4, 4, 1, 2, 1, 2, 'Untoter Fehler', 'Der gleiche Fehler wie jedes Mal', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(5, 5, 1, 1, 1, 2, 'Boesartiger Fehler', 'Stiehlt private Fotos und postet sie im Internet', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(6, 1, 2, 2, 4, 2, 'Sexy Fehler', 'Alle NPCs laufen im Bikini rum, auch Maenner', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(7, 2, 2, 3, 1, 1, 'Fauler Fehler', 'Macht eigentlich nichts', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(8, 3, 2, 4, 1, 1, 'Kreativer Fehler', 'Programm zeichnet zufaelliger Paintbilder', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(9, 4, 2, 5, 1, 1, 'Hilfreicher Fehler', 'Programm macht meine arbeit von alleine', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(10, 5, 2, 4, 1, 3, 'Spam Fehler new', 'Programm meint ich braeuchte Potenzmittel', '2016-05-11 22:00:00', '2016-05-23 09:47:44'),
(11, 1, 2, 3, 1, 1, 'Naiver Fehler', 'Programm schreibt Briefe an den Weihnachtsmann', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(12, 2, 2, 2, 1, 1, 'Arroganter Fehler', 'Meint, wir koennten ihn nicht beheben', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(13, 3, 2, 1, 1, 4, 'Buerokratischer Fehler', 'Spammt meine Oberflaecher mit Formularen voll', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(14, 4, 2, 2, 1, 4, 'Newb Fehler', 'Programm wirft NullPointerException', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(15, 5, 2, 3, 1, 4, 'Runtime Fehler', 'Irgendwas scheint nicht zu laufen', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(16, 1, 3, 4, 1, 4, 'Manufaktur Fehler', 'Das Programm war schon vorher kaputt', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(17, 2, 3, 5, 1, 4, 'Jugend Fehler', 'Alle coolen Kinder machen das so', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(18, 3, 3, 4, 1, 4, 'Strategischer Fehler', 'Hat sich Jahrelang auf diesen Moment vorbereitet', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(19, 4, 3, 3, 1, 4, 'Taktischer Fehler', 'Ist mir immer einen Schritt voraus', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(20, 5, 3, 2, 1, 3, 'Operativer Fehler', 'Seine Waffen machen mehr Schaden als meine', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(21, 1, 3, 1, 2, 3, 'Hektischer Fehler', 'Verursacht Kopfschmerzen', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(22, 2, 3, 2, 2, 3, 'Toedlicher Fehler', 'Es ist in den Lueftungsschaechten', '2016-05-11 22:00:00', '2016-05-11 22:00:00'),
(23, 3, 3, 3, 5, 3, 'Letzter Fehler', 'Programm-Support wird danach eingestellt', '2016-05-11 22:00:00', '2016-05-11 22:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `fehlerverweise`
--

CREATE TABLE IF NOT EXISTS `fehlerverweise` (
  `vorgaenger` int(11) NOT NULL,
  `verweisAuf` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `kommentar`
--

CREATE TABLE IF NOT EXISTS `kommentar` (
`koNr` int(11) NOT NULL,
  `feNr` int(11) NOT NULL,
  `nuNr` int(11) NOT NULL,
  `text` varchar(2000) NOT NULL,
  `erstelltAm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kommentar`
--

INSERT INTO `kommentar` (`koNr`, `feNr`, `nuNr`, `text`, `erstelltAm`) VALUES
(1, 1, 1, 'Kommentar Einfuegen!', '2016-05-16 22:00:00'),
(2, 1, 2, 'stuff232', '2016-05-16 22:00:00'),
(3, 1, 3, 'stuff233', '2016-05-16 22:00:00'),
(4, 2, 4, 'stuff021', '2016-05-16 22:00:00'),
(5, 2, 5, 'stuff022', '2016-05-16 22:00:00'),
(6, 2, 1, 'stuff023', '2016-05-16 22:00:00'),
(7, 3, 2, 'stuff031', '2016-05-16 22:00:00'),
(8, 3, 3, 'stuff032', '2016-05-16 22:00:00'),
(9, 3, 4, 'stuff033', '2016-05-16 22:00:00'),
(10, 4, 5, 'stuff041', '2016-05-16 22:00:00'),
(11, 4, 4, 'stuff042', '2016-05-16 22:00:00'),
(12, 4, 3, 'stuff043', '2016-05-16 22:00:00'),
(13, 5, 2, 'stuff051', '2016-05-16 22:00:00'),
(14, 5, 1, 'stuff052', '2016-05-16 22:00:00'),
(15, 5, 2, 'stuff053', '2016-05-16 22:00:00'),
(16, 6, 3, 'stuff061', '2016-05-16 22:00:00'),
(17, 6, 4, 'stuff062', '2016-05-16 22:00:00'),
(18, 6, 5, 'stuff063', '2016-05-16 22:00:00'),
(19, 7, 1, 'stuff071', '2016-05-16 22:00:00'),
(20, 7, 2, 'stuff072', '2016-05-16 22:00:00'),
(21, 7, 3, 'stuff073', '2016-05-16 22:00:00'),
(22, 8, 4, 'stuff081', '2016-05-16 22:00:00'),
(23, 8, 5, 'stuff082', '2016-05-16 22:00:00'),
(24, 8, 1, 'stuff083', '2016-05-16 22:00:00'),
(25, 9, 2, 'stuff091', '2016-05-16 22:00:00'),
(26, 9, 3, 'stuff092', '2016-05-16 22:00:00'),
(27, 9, 4, 'stuff093', '2016-05-16 22:00:00'),
(28, 10, 5, 'stuff101', '2016-05-16 22:00:00'),
(29, 10, 1, 'stuff102', '2016-05-16 22:00:00'),
(30, 10, 2, 'stuff103', '2016-05-16 22:00:00'),
(31, 11, 3, 'stuff111', '2016-05-16 22:00:00'),
(32, 11, 4, 'stuff112', '2016-05-16 22:00:00'),
(33, 11, 5, 'stuff113', '2016-05-16 22:00:00'),
(34, 12, 1, 'stuff121', '2016-05-16 22:00:00'),
(35, 12, 2, 'stuff122', '2016-05-16 22:00:00'),
(36, 12, 3, 'stuff123', '2016-05-16 22:00:00'),
(37, 13, 4, 'stuff131', '2016-05-16 22:00:00'),
(38, 13, 5, 'stuff132', '2016-05-16 22:00:00'),
(39, 13, 1, 'stuff133', '2016-05-16 22:00:00'),
(40, 14, 2, 'stuff141', '2016-05-16 22:00:00'),
(41, 14, 3, 'stuff142', '2016-05-16 22:00:00'),
(42, 14, 4, 'stuff143', '2016-05-16 22:00:00'),
(43, 15, 5, 'stuff151', '2016-05-16 22:00:00'),
(44, 15, 4, 'stuff152', '2016-05-16 22:00:00'),
(45, 15, 3, 'stuff153', '2016-05-16 22:00:00'),
(46, 16, 2, 'stuff161', '2016-05-16 22:00:00'),
(47, 16, 1, 'stuff162', '2016-05-16 22:00:00'),
(48, 16, 2, 'stuff163', '2016-05-16 22:00:00'),
(49, 17, 3, 'stuff171', '2016-05-16 22:00:00'),
(50, 17, 4, 'stuff172', '2016-05-16 22:00:00'),
(51, 17, 5, 'stuff173', '2016-05-16 22:00:00'),
(52, 18, 1, 'stuff181', '2016-05-16 22:00:00'),
(53, 18, 2, 'stuff182', '2016-05-16 22:00:00'),
(54, 18, 3, 'stuff183', '2016-05-16 22:00:00'),
(55, 19, 4, 'stuff191', '2016-05-16 22:00:00'),
(56, 19, 5, 'stuff192', '2016-05-16 22:00:00'),
(57, 19, 1, 'stuff193', '2016-05-16 22:00:00'),
(58, 20, 2, 'stuff201', '2016-05-16 22:00:00'),
(59, 20, 3, 'stuff202', '2016-05-16 22:00:00'),
(60, 20, 4, 'stuff203', '2016-05-16 22:00:00'),
(61, 21, 5, 'stuff211', '2016-05-16 22:00:00'),
(62, 21, 1, 'stuff212', '2016-05-16 22:00:00'),
(63, 21, 2, 'stuff213', '2016-05-16 22:00:00'),
(64, 22, 3, 'stuff221', '2016-05-16 22:00:00'),
(65, 22, 4, 'stuff222', '2016-05-16 22:00:00'),
(66, 22, 5, 'stuff223', '2016-05-16 22:00:00'),
(67, 23, 1, 'stuff231', '2016-05-16 22:00:00'),
(68, 23, 2, 'stuff232', '2016-05-16 22:00:00'),
(69, 23, 3, 'stuff233', '2016-05-16 22:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `nutzer`
--

CREATE TABLE IF NOT EXISTS `nutzer` (
`nuNr` int(11) NOT NULL,
  `vorname` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nutzer`
--

INSERT INTO `nutzer` (`nuNr`, `vorname`, `name`) VALUES
(1, 'John', 'Smith'),
(2, 'Marie', 'Sue'),
(3, 'Paul', 'Su'),
(4, 'Duke', 'Nukem'),
(5, 'Axel', 'Schweiß');

-- --------------------------------------------------------

--
-- Table structure for table `prioritaet`
--

CREATE TABLE IF NOT EXISTS `prioritaet` (
`prioID` int(11) NOT NULL,
  `bezeichnung` varchar(64) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prioritaet`
--

INSERT INTO `prioritaet` (`prioID`, `bezeichnung`) VALUES
(1, 'sehr niedrig'),
(2, 'niedrig'),
(3, 'mittel'),
(4, 'hoch'),
(5, 'sehr hoch');

-- --------------------------------------------------------

--
-- Table structure for table `projekt`
--

CREATE TABLE IF NOT EXISTS `projekt` (
`proNr` int(11) NOT NULL,
  `bezeichnung` varchar(2000) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projekt`
--

INSERT INTO `projekt` (`proNr`, `bezeichnung`) VALUES
(1, 'Tolles Projekt 1'),
(2, 'Irgenson Projekt 2'),
(3, 'Timewaster Projekt 3');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
`staNr` int(11) NOT NULL,
  `bezeichnung` varchar(2000) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`staNr`, `bezeichnung`) VALUES
(1, 'waiting for triage'),
(2, 'resolved'),
(3, 'closed'),
(4, 'reopened'),
(5, 'in progress');

-- --------------------------------------------------------

--
-- Table structure for table `statusaenderungen`
--

CREATE TABLE IF NOT EXISTS `statusaenderungen` (
  `vorgaenger` int(11) NOT NULL,
  `nachfolger` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statusaenderungen`
--

INSERT INTO `statusaenderungen` (`vorgaenger`, `nachfolger`) VALUES
(1, 2),
(1, 3),
(1, 5),
(2, 3),
(2, 4),
(2, 5),
(3, 2),
(3, 4),
(3, 5),
(4, 2),
(4, 3),
(4, 5),
(5, 1),
(5, 2),
(5, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fehler`
--
ALTER TABLE `fehler`
 ADD PRIMARY KEY (`feNr`), ADD KEY `prioID` (`prioID`), ADD KEY `proNr` (`proNr`), ADD KEY `staNr` (`staNr`), ADD KEY `erstelltVon` (`erstelltVon`), ADD KEY `zugewiesenAn` (`zugewiesenAn`);

--
-- Indexes for table `fehlerverweise`
--
ALTER TABLE `fehlerverweise`
 ADD PRIMARY KEY (`vorgaenger`,`verweisAuf`), ADD KEY `verweisAuf` (`verweisAuf`);

--
-- Indexes for table `kommentar`
--
ALTER TABLE `kommentar`
 ADD PRIMARY KEY (`koNr`), ADD KEY `feNr` (`feNr`), ADD KEY `nuNr` (`nuNr`);

--
-- Indexes for table `nutzer`
--
ALTER TABLE `nutzer`
 ADD PRIMARY KEY (`nuNr`);

--
-- Indexes for table `prioritaet`
--
ALTER TABLE `prioritaet`
 ADD PRIMARY KEY (`prioID`);

--
-- Indexes for table `projekt`
--
ALTER TABLE `projekt`
 ADD PRIMARY KEY (`proNr`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
 ADD PRIMARY KEY (`staNr`);

--
-- Indexes for table `statusaenderungen`
--
ALTER TABLE `statusaenderungen`
 ADD PRIMARY KEY (`vorgaenger`,`nachfolger`), ADD KEY `vorgaenger` (`vorgaenger`), ADD KEY `nachfolger` (`nachfolger`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fehler`
--
ALTER TABLE `fehler`
MODIFY `feNr` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `kommentar`
--
ALTER TABLE `kommentar`
MODIFY `koNr` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=72;
--
-- AUTO_INCREMENT for table `nutzer`
--
ALTER TABLE `nutzer`
MODIFY `nuNr` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `prioritaet`
--
ALTER TABLE `prioritaet`
MODIFY `prioID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `projekt`
--
ALTER TABLE `projekt`
MODIFY `proNr` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
MODIFY `staNr` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `fehler`
--
ALTER TABLE `fehler`
ADD CONSTRAINT `FK_fehler_nutzer_erstelltVon` FOREIGN KEY (`erstelltVon`) REFERENCES `nutzer` (`nuNr`),
ADD CONSTRAINT `FK_fehler_nutzer_zugewiesenAn` FOREIGN KEY (`zugewiesenAn`) REFERENCES `nutzer` (`nuNr`),
ADD CONSTRAINT `FK_fehler_prioritaet` FOREIGN KEY (`prioID`) REFERENCES `prioritaet` (`prioID`),
ADD CONSTRAINT `FK_fehler_projekt` FOREIGN KEY (`proNr`) REFERENCES `projekt` (`proNr`) ON DELETE CASCADE,
ADD CONSTRAINT `FK_fehler_status` FOREIGN KEY (`staNr`) REFERENCES `status` (`staNr`);

--
-- Constraints for table `fehlerverweise`
--
ALTER TABLE `fehlerverweise`
ADD CONSTRAINT `verweisAuf` FOREIGN KEY (`verweisAuf`) REFERENCES `fehler` (`feNr`) ON DELETE CASCADE,
ADD CONSTRAINT `vorgaengerFehler` FOREIGN KEY (`vorgaenger`) REFERENCES `fehler` (`feNr`) ON DELETE CASCADE;

--
-- Constraints for table `kommentar`
--
ALTER TABLE `kommentar`
ADD CONSTRAINT `FK_kommentar_fehler` FOREIGN KEY (`feNr`) REFERENCES `fehler` (`feNr`) ON DELETE CASCADE,
ADD CONSTRAINT `FK_kommentar_nutzer` FOREIGN KEY (`nuNr`) REFERENCES `nutzer` (`nuNr`);

--
-- Constraints for table `statusaenderungen`
--
ALTER TABLE `statusaenderungen`
ADD CONSTRAINT `nachfolger` FOREIGN KEY (`nachfolger`) REFERENCES `status` (`staNr`) ON DELETE CASCADE,
ADD CONSTRAINT `vorgaenger` FOREIGN KEY (`vorgaenger`) REFERENCES `status` (`staNr`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
