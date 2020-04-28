-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 17 Kwi 2020, 11:53
-- Wersja serwera: 10.4.11-MariaDB
-- Wersja PHP: 7.2.29

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

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`ID`, `imie`, `nazwisko`, `mail`, `haslo`, `uprawnienia`, `miejscowosc`, `ulica`, `numer_budynku`, `numer_lokalu`, `kod_pocztowy`, `liczba_punktow`) VALUES
(1, 'Hubert', 'Jakobsze', 'hubertjakobsze@gmail.com', '1234567890', 3, 'Rzeszów', 'Hanasiewicza', '13', '12', '36-128', 0),
(2, 'Czesław', 'Nowak', 'aleluja@interia.pl', '02846523', 0, 'Warszawa', '3 maja', '1', '32', '52-123', 20),
(3, 'Natalia', 'Nowak', 'NNowak@interia.com', 'hgfy376g3', 1, 'Sandomierz', '3 maja', '35', '3', '23-412', 7),
(4, 'Samanta', 'Wójcik', 'AkAgnieszka@gmail.com', '01271997', 0, 'Markuszowa', '', '102', '', '38-126', 0),
(5, 'Daniel', 'Dom', 'DD321@gmail.com', '0987654321asdw', 0, 'Kraków', 'Kościuszki', '3', '12', '12-671', 70),
(6, 'Jarek', 'Grzyb', 'Grzybtoja@gmail.com', '542365561254', 1, 'Warszawa', 'Kościuszki', '1', '14', '12-125', 10),
(7, 'Janusz', 'Janusz', 'januszbiznesu@gmail.com', '1234', 0, 'Rzeszów', 'Hanasiewicza', '2', '1', '123-12', 99),
(8, 'Kamil', 'Kalisztan', 'kamil@interia.pl', '123', 1, 'Rzeszów', 'Rejtana', '2', '34', '38-126', 0),
(9, 'Jakub', 'Bober', 'Bobik@jah.com', '3221asdfg', 1, 'Rzeszów', 'Rejtana', '12', '1', '23-412', 1),
(10, 'Rafał', 'Czeski', 'CRZak@interia.pl', '456asd', 1, 'Rzeszów', 'Dąbrowskiego', '23', '1', '34-15', 63),
(11, 'Damian', 'Nowak', 'NOWa@interia.pl', 'Nowa', 0, 'Warszawa', 'Rejtana', '12', '3', '23-412', 0),
(12, 'Marcelina', 'Kawał', 'Kawalkawal@kawal.com', 'kawall', 0, 'Rzeszów', 'Hanasiewicza', '3', '32', '38-126', 31);






--
-- Zrzut danych tabeli `ankiety`
--

INSERT INTO `ankiety` (`ID`, `tytul`, `liczba_punktow`, `data_rozpoczecia`, `data_zakonczenia`) VALUES
(12, 'Profilaktyka przeciwstarzeniowa', 50, '2020-04-16', '2020-04-30'),
(13, 'Test Drogowy', 100, '2020-04-17', '2020-07-17'),
(14, 'Internet', 50, '2020-04-16', '2020-04-30');


--
-- Zrzut danych tabeli `pytania`
--

INSERT INTO `pytania` (`ID`, `ID_ankiety`, `tresc`, `zdjecie`, `punktowe`, `rodzaj_pytania`) VALUES
(14, 12, 'Płeć', NULL, NULL, 0),
(15, 12, 'Przedział wiekowy ', NULL, NULL, 0),
(18, 12, 'Wykształcenie', NULL, NULL, 0),
(21, 12, 'Miejsce zamieszkania?', NULL, NULL, 0),
(22, 12, 'Czy zauważasz u siebie pierwsze oznaki starzenia?', NULL, NULL, 2),
(23, 12, 'Czy wiesz, w jaki sposób przeciwdziałać pierwszym oznakom starzenia?', NULL, NULL, 0),
(24, 12, 'Jak określasz swoją sytuację materialną?', NULL, NULL, 0),
(25, 12, 'W jaki sposób dbasz o wygląd skóry?', NULL, NULL, 1),
(26, 12, 'Jakie zabiegi stosujesz w zaciszu domowym?', NULL, NULL, 1);
INSERT INTO `pytania` (`ID`, `ID_ankiety`, `tresc`, `zdjecie`, `punktowe`, `rodzaj_pytania`) VALUES
(27, 13, 'Kierujesz pojazdem na drodze oznaczonej tym znakiem pionowym. Czy na odcinku za znakiem wolno Ci zatrzymać pojazd, aby wysadzić pasażera?', NULL, NULL, 0);
INSERT INTO `pytania` (`ID`, `ID_ankiety`, `tresc`, `zdjecie`, `punktowe`, `rodzaj_pytania`) VALUES
(28, 13, 'Czy masz prawo wjechać na lewą część jezdni, aby upewnić się o możliwości wyprzedzania?', NULL, NULL, 0);
INSERT INTO `pytania` (`ID`, `ID_ankiety`, `tresc`, `zdjecie`, `punktowe`, `rodzaj_pytania`) VALUES
(29, 13, 'Czy w przedstawionej sytuacji wolno Ci skręcić w prawo?', NULL, NULL, 0);
INSERT INTO `pytania` (`ID`, `ID_ankiety`, `tresc`, `zdjecie`, `punktowe`, `rodzaj_pytania`) VALUES
(30, 13, 'Czy w tej sytuacji nadawany sygnał świetlny zezwala Ci na wjazd za sygnalizator?', NULL, NULL, 0);
INSERT INTO `pytania` (`ID`, `ID_ankiety`, `tresc`, `zdjecie`, `punktowe`, `rodzaj_pytania`) VALUES
(31, 13, 'Czy postąpisz właściwie umieszczając ostrzegawczy trójkąt odblaskowy w odległości 30-50 m za pojazdem, który uległ awarii na drodze ekspresowej?', NULL, NULL, 0);
INSERT INTO `pytania` (`ID`, `ID_ankiety`, `tresc`, `zdjecie`, `punktowe`, `rodzaj_pytania`) VALUES
(32, 14, 'W jakim stopniu czujesz się uzależniony od internetu (0-100)', NULL, 100, 3),
(37, 14, 'Ile czasu spędzasz dziennie używając internetu ?', NULL, 24, 3),
(38, 14, 'Ile czasu dziennie poświęcasz dla rodziny ?', NULL, 24, 3),
(39, 14, 'Jakie wykonujesz czynności rekreacyjne? ', NULL, NULL, 3),
(41, 14, 'Czy uważasz, że związki przez internet są dobre ?', NULL, NULL, 0),
(43, 14, 'Jakie środki przemieszczania się preferujesz?', NULL, NULL, 1);

--
-- Zrzut danych tabeli `pytania_uzytkownicy`
--

INSERT INTO `pytania_uzytkownicy` (`ID_uzytkownika`, `ID_pytania`, `odpowiedz`) VALUES
(4, 39, 'spacery po lesie');

--
-- Zrzut danych tabeli `odpowiedzi`
--

INSERT INTO `odpowiedzi` (`ID`, `ID_pytania`, `odpowiedz`) VALUES
(1, 14, 'kobieta '),
(2, 14, 'mężczyzna '),
(3, 15, '18-25 '),
(4, 15, '26-30 '),
(5, 15, '31-35'),
(6, 15, '36-40 '),
(7, 15, 'powyżej 40 lat '),
(8, 18, 'podstawowe '),
(9, 18, 'zawodowe '),
(10, 18, 'średnie '),
(11, 18, 'wyższe '),
(12, 24, 'bardzo dobra '),
(13, 24, 'dobra '),
(14, 24, 'przeciętna '),
(15, 24, 'trudna '),
(16, 24, 'bardzo zła '),
(17, 21, 'wieś '),
(18, 21, 'miasto '),
(19, 23, 'tak'),
(20, 23, 'nie'),
(21, 25, 'w domu '),
(22, 25, 'w salonie kosmetycznym '),
(33, 26, 'peelingi '),
(34, 26, 'maseczki nawilżające '),
(35, 26, 'używam szczoteczki sonicznej do mycia twarzy, '),
(36, 26, 'ampułki z kwasem hialuronowym, z witaminami A,C i E '),
(37, 26, 'kremy, serum do cery z pierwszymi oznakami starzenia'),
(40, 26, ''),
(41, 27, 'tak'),
(42, 28, 'tak'),
(43, 31, 'tak'),
(44, 30, 'tak'),
(45, 29, 'tak'),
(46, 27, 'nie'),
(47, 28, 'nie'),
(48, 31, 'nie'),
(49, 30, 'nie'),
(50, 29, 'nie'),
(51, 32, '0'),
(52, 37, '0'),
(53, 38, '0'),
(54, 39, ''),
(55, 41, 'tak'),
(56, 41, 'nie'),
(61, 43, 'Auta'),
(62, 43, 'Rower'),
(63, 43, 'Autobusy'),
(64, 43, 'Pieszo');
--
-- Zrzut danych tabeli `odpowiedzi_uzytkownicy`
--

INSERT INTO `odpowiedzi_uzytkownicy` (`ID_odpowiedzi`, `ID_uzytkownika`, `punktowe`) VALUES
(41, 2, NULL),
(42, 2, NULL),
(43, 2, NULL),
(44, 2, NULL),
(45, 2, NULL),
(51, 4, 23),
(52, 4, 2),
(54, 4, 21),
(55, 4, NULL),
(62, 4, NULL),
(64, 4, NULL);

--
-- Zrzut danych tabeli `uzytkownicy_ankiety`
--

INSERT INTO `uzytkownicy_ankiety` (`ID_uzytkownika`, `ID_ankiety`) VALUES
(6, 12),
(3, 12),
(6, 14);

--
-- Zrzut danych tabeli `uzytkownicy_nagrody`
--

INSERT INTO `uzytkownicy_nagrody` (`ID_uzytkownika`, `ID_nagrody`) VALUES
(1, 1),
(1, 0),
(1, 2),
(1, 3),
(1, 4),
(1, 5);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Zrzut danych tabeli `nagrody`
--

INSERT INTO `nagrody` (`ID`, `liczba_punktow`, `nazwa`, `zdjecie`) VALUES
(1, 44, 'Kupon Allegro 50 zł', NULL),
(2, 150, 'Kupon Allegro 200 zł', NULL),
(3, 200, 'kupon Allegro 250zł', NULL);
INSERT INTO `nagrody` (`ID`, `liczba_punktow`, `nazwa`, `zdjecie`) VALUES
(4, 7500, 'Telewizor Philips 32 LED 32PFS5803/12 Full HD', NULL);
INSERT INTO `nagrody` (`ID`, `liczba_punktow`, `nazwa`, `zdjecie`) VALUES
(5, 70, 'Kuchenka mikrofalowa SAMSUNG ME83M ', NULL);



