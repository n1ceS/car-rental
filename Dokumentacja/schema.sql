-- phpMyAdmin SQL Dump
-- version 4.6.6deb5ubuntu0.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Czas generowania: 27 Cze 2021, 18:46
-- Wersja serwera: 5.7.34-0ubuntu0.18.04.1
-- Wersja PHP: 7.2.24-0ubuntu0.18.04.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `freedbtech_tisztaldatabase`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `cardetails`
--

CREATE TABLE `cardetails` (
  `car_id` int(10) NOT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `year` int(4) NOT NULL,
  `fuel_type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `power` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `gearbox` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `color` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `doors_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `cars`
--

CREATE TABLE `cars` (
  `id` int(10) NOT NULL,
  `brand` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `model` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `price` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `carstatus`
--

CREATE TABLE `carstatus` (
  `car_status` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `carstatus`
--

INSERT INTO `carstatus` (`car_status`, `description`) VALUES
('BROKEN', 'Car is broken'),
('NEW', 'Car is new'),
('NOTRDY', 'Car is not ready to use'),
('READY', 'Car is new'),
('RENTED', 'Car is rented by client.');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rentals`
--

CREATE TABLE `rentals` (
  `id` int(10) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `total_cost` decimal(15,2) NOT NULL,
  `rentalstatus` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `car_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rentalstatuscodes`
--

CREATE TABLE `rentalstatuscodes` (
  `rent_status` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `rentalstatuscodes`
--

INSERT INTO `rentalstatuscodes` (`rent_status`, `description`) VALUES
('CANCELED', 'Rent is canceled'),
('NEW', 'Rent is added and has a new status'),
('RENTED', 'Rent has been rented'),
('RESERVED', 'Car is reserved. Rent is ready'),
('RETURNED', 'Car has been returned. Rent ended');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `userroles`
--

CREATE TABLE `userroles` (
  `id` int(10) NOT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `userroles`
--

INSERT INTO `userroles` (`id`, `type`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `firstname` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `birth_date` date NOT NULL,
  `pesel` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `email`, `phone`, `birth_date`, `pesel`, `password`) VALUES
(1, 'admin', 'admin', 'admin@mail.com', '123456789', '1999-01-01', '99010112345', '$2y$10$eoSsq2oUdn0Nvb38E20euuV0pgzFeZP3yjL.nKI/mqWfsH/F13.OS');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users_to_roles`
--

CREATE TABLE `users_to_roles` (
  `userroles_id` int(10) NOT NULL,
  `users_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `users_to_roles`
--

INSERT INTO `users_to_roles` (`userroles_id`, `users_id`) VALUES
(1, 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `cardetails`
--
ALTER TABLE `cardetails`
  ADD KEY `fk_cardetails_cars1_idx` (`car_id`);

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cars_carstatus1_idx` (`status`);

--
-- Indexes for table `carstatus`
--
ALTER TABLE `carstatus`
  ADD PRIMARY KEY (`car_status`);

--
-- Indexes for table `rentals`
--
ALTER TABLE `rentals`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_rentals_rentalstatuscodes1_idx` (`rentalstatus`),
  ADD KEY `fk_rentals_cars1_idx` (`car_id`),
  ADD KEY `fk_rentals_users1_idx` (`user_id`);

--
-- Indexes for table `rentalstatuscodes`
--
ALTER TABLE `rentalstatuscodes`
  ADD PRIMARY KEY (`rent_status`);

--
-- Indexes for table `userroles`
--
ALTER TABLE `userroles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indexes for table `users_to_roles`
--
ALTER TABLE `users_to_roles`
  ADD PRIMARY KEY (`users_id`,`userroles_id`),
  ADD KEY `fk_users_to_roles_users1_idx` (`users_id`),
  ADD KEY `fk_users_to_roles_userroles` (`userroles_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `cars`
--
ALTER TABLE `cars`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT dla tabeli `rentals`
--
ALTER TABLE `rentals`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `cardetails`
--
ALTER TABLE `cardetails`
  ADD CONSTRAINT `fk_cardetails_cars1` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`);

--
-- Ograniczenia dla tabeli `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `fk_cars_carstatus1` FOREIGN KEY (`status`) REFERENCES `carstatus` (`car_status`);

--
-- Ograniczenia dla tabeli `rentals`
--
ALTER TABLE `rentals`
  ADD CONSTRAINT `fk_rentals_cars1` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`),
  ADD CONSTRAINT `fk_rentals_rentalstatuscodes1` FOREIGN KEY (`rentalstatus`) REFERENCES `rentalstatuscodes` (`rent_status`),
  ADD CONSTRAINT `fk_rentals_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ograniczenia dla tabeli `users_to_roles`
--
ALTER TABLE `users_to_roles`
  ADD CONSTRAINT `fk_users_to_roles_userroles` FOREIGN KEY (`userroles_id`) REFERENCES `userroles` (`id`),
  ADD CONSTRAINT `fk_users_to_roles_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
