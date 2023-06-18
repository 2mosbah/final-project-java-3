-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2023 at 11:00 AM
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
(21, '2023-07-06', 'THURSDAY', '5 pm', 'free'),
(22, '2023-07-27', 'THURSDAY', '2 pm', 'bokked'),
(23, '2023-07-25', 'TUESDAY', '6 pm', 'bokked'),
(24, '2023-06-21', 'WEDNESDAY', '4 pm', 'bokked'),
(25, '2023-07-19', 'WEDNESDAY', '7 pm', 'bokked'),
(26, '2023-07-02', 'SUNDAY', '7 pm', 'bokked'),
(27, '2023-07-02', 'SUNDAY', '7 pm', 'bokked'),
(28, '2023-07-01', 'SATURDAY', '3 pm', 'bokked'),
(29, '2023-06-23', 'FRIDAY', '3 pm', 'free'),
(30, '2023-07-06', 'THURSDAY', '4 pm', 'free'),
(31, '2023-07-06', 'THURSDAY', '2 pm', 'free'),
(32, '2023-06-27', 'TUESDAY', '3 pm', 'free'),
(33, '2023-07-06', 'THURSDAY', '2 pm', 'free');

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

--
-- Dumping data for table `bokkedappointments`
--

INSERT INTO `bokkedappointments` (`id`, `appointmentId`, `userId`, `status`, `doctorComment`) VALUES
(20, 27, 22, 'finished', 'go to the gym'),
(21, 24, 24, 'finished', 'eat healthy food'),
(22, 23, 24, 'waiting', NULL),
(23, 22, 24, 'waiting', NULL),
(24, 28, 22, 'waiting', NULL);

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
(22, 'ahmed11', '123', 'ahmed', 'ali', 19, 'ahmed@gmail.com', 26518911, 'male', 'patient'),
(23, 'hadi12', '123', 'hadi', 'mohammed', 20, 'hadi@gmail.com', 561321, 'male', 'patient'),
(24, 'soha11', '123', 'soha', 'ibrahim', 23, 'soha@gmail.com', 513512, 'female', 'patient'),
(25, 'asc', '12312312', '123csa', 'asc', 12, 'asc@gmail.com', 65132, 'male', 'patient');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `bokkedappointments`
--
ALTER TABLE `bokkedappointments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

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
