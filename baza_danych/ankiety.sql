-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 15 Kwi 2020, 11:04
-- Wersja serwera: 10.4.11-MariaDB
-- Wersja PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `ankiety`
--
CREATE DATABASE IF NOT EXISTS `ankiety` DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;
USE `ankiety`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ankiety`
--

CREATE TABLE `ankiety` (
  `ID` int(11) NOT NULL,
  `tytul` varchar(150) COLLATE utf8_polish_ci NOT NULL,
  `liczba_punktow` int(10) NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `nagrody`
--

CREATE TABLE `nagrody` (
  `ID` int(11) NOT NULL,
  `liczba_punktow` int(10) NOT NULL,
  `nazwa` varchar(150) COLLATE utf8_polish_ci NOT NULL,
  `zdjecie` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `odpowiedzi`
--

CREATE TABLE `odpowiedzi` (
  `ID` int(11) NOT NULL,
  `ID_pytania` int(11) NOT NULL,
  `odpowiedz` text COLLATE utf8_polish_ci NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `odpowiedzi_uzytkownicy`
--

CREATE TABLE `odpowiedzi_uzytkownicy` (
  `ID_odpowiedzi` int(11) NOT NULL,
  `ID_uzytkownika` int(11) NOT NULL,
  `punktowe` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pytania`
--

CREATE TABLE `pytania` (
  `ID` int(11) NOT NULL,
  `ID_ankiety` int(11) NOT NULL,
  `tresc` text COLLATE utf8_polish_ci NOT NULL,
  `zdjecie` longblob DEFAULT NULL,
  `punktowe` int(100) DEFAULT NULL,
  `rodzaj_pytania` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pytania_uzytkownicy`
--

CREATE TABLE `pytania_uzytkownicy` (
  `ID_uzytkownika` int(11) NOT NULL,
  `ID_pytania` int(11) NOT NULL,
  `odpowiedz` text COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE `uzytkownicy` (
  `ID` int(11) NOT NULL,
  `imie` varchar(80) COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(80) COLLATE utf8_polish_ci NOT NULL,
  `mail` varchar(150) COLLATE utf8_polish_ci NOT NULL,
  `haslo` varchar(150) COLLATE utf8_polish_ci NOT NULL,
  `uprawnienia` int(1) NOT NULL DEFAULT 0,
  `miejscowosc` varchar(80) COLLATE utf8_polish_ci NOT NULL,
  `ulica` varchar(80) COLLATE utf8_polish_ci NOT NULL,
  `numer_budynku` varchar(10) COLLATE utf8_polish_ci NOT NULL,
  `numer_lokalu` varchar(10) COLLATE utf8_polish_ci DEFAULT NULL,
  `kod_pocztowy` varchar(6) COLLATE utf8_polish_ci NOT NULL,
  `liczba_punktow` int(10) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy_ankiety`
--

CREATE TABLE `uzytkownicy_ankiety` (
  `ID_uzytkownika` int(11) NOT NULL,
  `ID_ankiety` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy_nagrody`
--

CREATE TABLE `uzytkownicy_nagrody` (
  `ID_uzytkownika` int(11) NOT NULL,
  `ID_nagrody` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `ankiety`
--
ALTER TABLE `ankiety`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `nagrody`
--
ALTER TABLE `nagrody`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `odpowiedzi`
--
ALTER TABLE `odpowiedzi`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_pytania` (`ID_pytania`);

--
-- Indeksy dla tabeli `odpowiedzi_uzytkownicy`
--
ALTER TABLE `odpowiedzi_uzytkownicy`
  ADD UNIQUE KEY `ID_odpowiedzi` (`ID_odpowiedzi`),
  ADD UNIQUE KEY `ID_uzytkownika` (`ID_uzytkownika`);

--
-- Indeksy dla tabeli `pytania`
--
ALTER TABLE `pytania`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID_ankiety` (`ID_ankiety`);

--
-- Indeksy dla tabeli `pytania_uzytkownicy`
--
ALTER TABLE `pytania_uzytkownicy`
  ADD UNIQUE KEY `ID_uzytkownika` (`ID_uzytkownika`),
  ADD UNIQUE KEY `ID_pytania` (`ID_pytania`);

--
-- Indeksy dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `uzytkownicy_ankiety`
--
ALTER TABLE `uzytkownicy_ankiety`
  ADD UNIQUE KEY `ID_uzytkownika` (`ID_uzytkownika`),
  ADD UNIQUE KEY `ID_ankiety` (`ID_ankiety`);

--
-- Indeksy dla tabeli `uzytkownicy_nagrody`
--
ALTER TABLE `uzytkownicy_nagrody`
  ADD UNIQUE KEY `ID_uzytkownika` (`ID_uzytkownika`),
  ADD UNIQUE KEY `ID_nagrody` (`ID_nagrody`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `ankiety`
--
ALTER TABLE `ankiety`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `nagrody`
--
ALTER TABLE `nagrody`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `odpowiedzi`
--
ALTER TABLE `odpowiedzi`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `pytania`
--
ALTER TABLE `pytania`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `nagrody`
--
ALTER TABLE `nagrody`
  ADD CONSTRAINT `nagrody_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `uzytkownicy_nagrody` (`ID_nagrody`);

--
-- Ograniczenia dla tabeli `odpowiedzi`
--
ALTER TABLE `odpowiedzi`
  ADD CONSTRAINT `odpowiedzi_ibfk_1` FOREIGN KEY (`ID_pytania`) REFERENCES `pytania` (`ID`);

--
-- Ograniczenia dla tabeli `odpowiedzi_uzytkownicy`
--
ALTER TABLE `odpowiedzi_uzytkownicy`
  ADD CONSTRAINT `odpowiedzi_uzytkownicy_ibfk_1` FOREIGN KEY (`ID_uzytkownika`) REFERENCES `uzytkownicy` (`ID`),
  ADD CONSTRAINT `odpowiedzi_uzytkownicy_ibfk_2` FOREIGN KEY (`ID_odpowiedzi`) REFERENCES `odpowiedzi` (`ID`);

--
-- Ograniczenia dla tabeli `pytania`
--
ALTER TABLE `pytania`
  ADD CONSTRAINT `pytania_ibfk_1` FOREIGN KEY (`ID_ankiety`) REFERENCES `ankiety` (`ID`);

--
-- Ograniczenia dla tabeli `pytania_uzytkownicy`
--
ALTER TABLE `pytania_uzytkownicy`
  ADD CONSTRAINT `pytania_uzytkownicy_ibfk_1` FOREIGN KEY (`ID_uzytkownika`) REFERENCES `uzytkownicy` (`ID`),
  ADD CONSTRAINT `pytania_uzytkownicy_ibfk_2` FOREIGN KEY (`ID_pytania`) REFERENCES `pytania` (`ID`);

--
-- Ograniczenia dla tabeli `uzytkownicy_ankiety`
--
ALTER TABLE `uzytkownicy_ankiety`
  ADD CONSTRAINT `uzytkownicy_ankiety_ibfk_1` FOREIGN KEY (`ID_uzytkownika`) REFERENCES `uzytkownicy` (`ID`),
  ADD CONSTRAINT `uzytkownicy_ankiety_ibfk_2` FOREIGN KEY (`ID_ankiety`) REFERENCES `ankiety` (`ID`);

--
-- Ograniczenia dla tabeli `uzytkownicy_nagrody`
--
ALTER TABLE `uzytkownicy_nagrody`
  ADD CONSTRAINT `uzytkownicy_nagrody_ibfk_1` FOREIGN KEY (`ID_uzytkownika`) REFERENCES `uzytkownicy` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
