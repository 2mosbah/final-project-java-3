-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2023 at 06:28 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinic_appointments`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `id` int(11) NOT NULL,
  `appointmentDate` varchar(20) NOT NULL,
  `appointmentDay` varchar(20) NOT NULL,
  `appointmentTime` varchar(20) NOT NULL,
  `status` enum('free','bokked') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`id`, `appointmentDate`, `appointmentDay`, `appointmentTime`, `status`) VALUES
(1, '2023-06-12', '12', '12', 'free'),
(3, '2023-06-20', '20', '5', 'free');

-- --------------------------------------------------------

--
-- Table structure for table `bokkedappointments`
--

CREATE TABLE `bokkedappointments` (
  `id` int(11) NOT NULL,
  `appointmentId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `status` enum('waiting','finished') DEFAULT NULL,
  `doctorComment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(70) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(35) NOT NULL,
  `phone` int(11) NOT NULL,
  `gender` enum('male','female') DEFAULT NULL,
  `role` enum('admin','patient') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `firstName`, `lastName`, `age`, `email`, `phone`, `gender`, `role`) VALUES
(1, 'mosbah99', '123456', 'mosbah', 'bardaweel', 20, 'mosbah@gmail.com', 594737867, 'male', 'admin'),
(3, 'raan99', '123456', 'raan', 'tanna', 15, 'raan@gmail.com', 591257451, 'female', 'admin'),
(4, 'mohanned99', '123456', 'mohanned', 'banna', 20, 'mohanned@gmail.com', 597815482, 'male', 'admin'),
(5, 'jalal101', '123456', 'jalal', 'hamed', 47, 'jalal@gmail.com', 599884009, 'male', 'admin'),
(10, 'd', 'fqwf', 'af', 'asffs', 123, 'af', 12312, 'male', 'patient'),
(18, 'sd', 'asda', 'as', 'adssa', 213, 'as', 123, 'male', 'patient');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bokkedappointments`
--
ALTER TABLE `bokkedappointments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `appointmentId` (`appointmentId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `bokkedappointments`
--
ALTER TABLE `bokkedappointments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bokkedappointments`
--
ALTER TABLE `bokkedappointments`
  ADD CONSTRAINT `bokkedappointments_ibfk_1` FOREIGN KEY (`appointmentId`) REFERENCES `appointments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bokkedappointments_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
