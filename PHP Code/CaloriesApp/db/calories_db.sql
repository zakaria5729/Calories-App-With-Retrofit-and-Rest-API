-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2018 at 04:46 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `calories_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `fruits`
--

CREATE TABLE `fruits` (
  `id` int(255) UNSIGNED NOT NULL,
  `name` text CHARACTER SET utf8 NOT NULL,
  `imagepath` varchar(500) CHARACTER SET utf8 NOT NULL,
  `calorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fruits`
--

INSERT INTO `fruits` (`id`, `name`, `imagepath`, `calorie`) VALUES
(1, 'pine apple', 'http://192.168.100.10/CaloriesApp/images/pineapple.JPG', 54),
(2, 'fdfdfd', 'http://192.168.100.10/CaloriesApp/images/pineapple.JPG', 42),
(3, 'hghh', 'http://192.168.100.10/CaloriesApp/images/pineapple.JPG', 87),
(4, 'hhg', 'http://192.168.100.10/CaloriesApp/images/pineapple.JPG', 9),
(5, 'kjkjk', 'http://192.168.100.10/CaloriesApp/images/pineapple.JPG', 16),
(6, 'Banana', 'http://192.168.100.10/CaloriesApp/images/pineapple.JPG', 46),
(7, 'Apple', 'http://192.168.100.10/CaloriesApp/images/pineapple.JPG', 46);

-- --------------------------------------------------------

--
-- Table structure for table `vegetables`
--

CREATE TABLE `vegetables` (
  `id` int(255) UNSIGNED NOT NULL,
  `name` text CHARACTER SET utf8 NOT NULL,
  `imagepath` varchar(500) CHARACTER SET utf8 NOT NULL,
  `calorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vegetables`
--

INSERT INTO `vegetables` (`id`, `name`, `imagepath`, `calorie`) VALUES
(1, 'alu', 'http://192.168.100.10/CaloriesApp/images/alu.JPG', 12),
(2, 'fjdfd', 'http://192.168.100.10/CaloriesApp/images/alu.JPG', 43),
(3, 'trtr', 'http://192.168.100.10/CaloriesApp/images/alu.JPG', 76),
(4, 'fdfdf', 'http://192.168.100.10/CaloriesApp/images/alu.JPG', 13),
(5, 'fdf', 'http://192.168.100.10/CaloriesApp/images/alu.JPG', 65),
(6, 'lau', 'http://192.168.100.10/CaloriesApp/images/alu.JPG', 43);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fruits`
--
ALTER TABLE `fruits`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vegetables`
--
ALTER TABLE `vegetables`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fruits`
--
ALTER TABLE `fruits`
  MODIFY `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `vegetables`
--
ALTER TABLE `vegetables`
  MODIFY `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
