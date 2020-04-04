-- phpMyAdmin SQL Dump
-- version 4.4.15.9
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 04, 2020 at 09:37 PM
-- Server version: 5.6.37
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `varsha`
--

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user`
--

CREATE TABLE IF NOT EXISTS `varsha_user` (
  `email` varchar(30) NOT NULL DEFAULT '',
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `user_role` varchar(10) NOT NULL,
  `address_line1` varchar(100) NOT NULL,
  `address_line2` varchar(50) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zip_code` int(5) NOT NULL,
  `phone_number` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `varsha_user`
--

INSERT INTO `varsha_user` (`email`, `first_name`, `last_name`, `user_role`, `address_line1`, `address_line2`, `city`, `country`, `state`, `zip_code`, `phone_number`) VALUES
('arun@gmail.com', 'Arun', 'Kondla', 'REGUSR', '3551 FORESTDALE DR', 'APT DI', 'BURLINGTON', 'USA', 'NC', 27215, '2147483647'),
('venkatreddy251198@gmail.com', 'Venkat Reddy', 'Bathula', 'SPADMN', '3551 FORESTALE DR', 'APT DI', 'BURLINGTON', 'USA', 'NC', 27215, '4084317411'),
('venkatreddy@gmail.com', 'Venkat test', 'Reddy test', 'REGUSR', '3060 S CHURCH ST', 'TEST', 'BURLINGTON', 'USA', 'NC', 27215, '4084317411'),
('venkattest@gmail.com', 'Venkat2', 'Reddy2', 'REGUSR', '3061 S CHURCH ST', NULL, 'BURLINGTON', 'USA', 'NC', 27215, '9857654865');

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_auth`
--

CREATE TABLE IF NOT EXISTS `varsha_user_auth` (
  `email` varchar(30) NOT NULL,
  `fppin` int(4) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `pin` int(4) NOT NULL,
  `register_date` datetime NOT NULL,
  `user_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `varsha_user_auth`
--

INSERT INTO `varsha_user_auth` (`email`, `fppin`, `password`, `pin`, `register_date`, `user_status`) VALUES
('arun@gmail.com', NULL, 'Admin@123', 3456, '2020-03-15 14:41:09', 'locked'),
('venkatreddy251198@gmail.com', NULL, 'Admin@123', 1234, '2020-03-15 16:30:00', 'active'),
('venkatreddy@gmail.com', NULL, 'Admin@123', 2345, '2020-03-15 16:33:12', 'pending'),
('venkattest@gmail.com', NULL, 'Admin@123', 4567, '2020-03-15 16:44:18', 'active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `varsha_user`
--
ALTER TABLE `varsha_user`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `varsha_user_auth`
--
ALTER TABLE `varsha_user_auth`
  ADD PRIMARY KEY (`email`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
