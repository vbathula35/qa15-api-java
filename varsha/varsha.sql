-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 22, 2024 at 10:51 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
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
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1271),
(1271);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `take_home` int(10) NOT NULL,
  `tax` int(10) NOT NULL,
  `total_amount` int(10) NOT NULL,
  `comments` longtext DEFAULT NULL,
  `federal_tax` int(10) NOT NULL,
  `other_payment` int(10) NOT NULL,
  `other_tax` int(10) NOT NULL,
  `state_tax` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `take_home`, `tax`, `total_amount`, `comments`, `federal_tax`, `other_payment`, `other_tax`, `state_tax`) VALUES
(2, 7000, 3000, 9000, '* This payment is for testing purpose.\n* Will remove this payment and project once testing is done.\n* If other tax and payments are not application please enter 0.\n* Still this project under construction.', 1800, 250, 250, 700),
(3, 7500, 2500, 10000, '* No Other taxes and Other payments.', 1800, 0, 0, 700),
(4, 1473, 831, 2304, '* This payroll run to Pandu on Jun 14 2023', 787, 0, 0, 43),
(5, 4600, 4232, 8832, '* This Payroll run to Pandu on July 14.', 4232, 0, 0, 530),
(6, 6802, 877, 7680, 'This Payroll Run to Kittu and Ramu\n* Kittu -- 3840(Payroll) -- aftertax(3401.03)\n* Ramu -- 3840(Payroll) -- aftertax(3401.03)', 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `paymentsprojectsrelationship`
--

CREATE TABLE `paymentsprojectsrelationship` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `month` int(2) NOT NULL,
  `project_id` int(11) NOT NULL,
  `year` int(4) NOT NULL,
  `payment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `paymentsprojectsrelationship`
--

INSERT INTO `paymentsprojectsrelationship` (`id`, `email`, `month`, `project_id`, `year`, `payment_id`) VALUES
(2, 'venkattest@gmail.com', 11, 4039, 2023, 2),
(3, 'venkateng@yopmail.com', 12, 4039, 2023, 3),
(4, 'venkatbathula35@gmail.com', 2, 4038, 2023, 4),
(5, 'venkatbathula35@gmail.com', 3, 4038, 2023, 5),
(6, 'venkatbathula35@gmail.com', 4, 4038, 2023, 6);

-- --------------------------------------------------------

--
-- Table structure for table `user_projects`
--

CREATE TABLE `user_projects` (
  `id` bigint(20) NOT NULL,
  `project_description` varchar(1000) DEFAULT NULL,
  `project_location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_name` varchar(50) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_projects`
--

INSERT INTO `user_projects` (`id`, `project_description`, `project_location`, `project_name`, `created_by`, `created_date`, `modified_date`) VALUES
(4038, 'This is State of Maryland client with vendor Triwave Solution and Infinics. ', 'Maryland (Remote)', 'State of Maryland Project', 'venkatreddy251198@gmail.com', '2024-01-18 18:20:56', NULL),
(4039, 'This is Test project', 'Test1', 'Test Project', 'venkatreddy251198@gmail.com', '2024-01-20 19:41:48', NULL),
(4040, '*This is Pnc Bank project with Tcs implementation partner. Sysmind and Cymansis are the vendors\n*Infinics is employer', 'Pittsburgh, PA (Remote)', 'PNC BANK', 'venkatreddy251198@gmail.com', '2024-01-21 07:25:10', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_projects_relationship`
--

CREATE TABLE `user_projects_relationship` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `is_admin` bit(1) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `employer_percentage` bigint(3) NOT NULL,
  `pay_rate` bigint(4) NOT NULL,
  `project_end_date` date DEFAULT NULL,
  `project_start_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_projects_relationship`
--

INSERT INTO `user_projects_relationship` (`id`, `email`, `is_admin`, `project_id`, `employer_percentage`, `pay_rate`, `project_end_date`, `project_start_date`) VALUES
(582, 'venkatreddy251198@gmail.com', b'1', 4038, 20, 60, '2023-02-21', '2023-04-28'),
(762, 'venkattest@gmail.com', b'1', 4039, 20, 80, '2024-01-20', '2023-07-25'),
(763, 'venkateng@yopmail.com', b'0', 4039, 15, 90, '2024-01-20', '2023-01-01'),
(775, 'venkatbathula35@gmail.com', b'0', 4038, 20, 60, '2023-04-28', '2023-02-21'),
(776, 'venkatreddy251198@gmail.com', b'1', 4040, 0, 0, NULL, NULL),
(777, 'venkatbathula35@gmail.com', b'0', 4040, 20, 60, '2023-05-31', '2022-10-03'),
(1138, 'venkateng14@yopmail.com', b'0', 4039, 20, 70, '2024-01-22', '2024-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `user_timesheets`
--

CREATE TABLE `user_timesheets` (
  `id` bigint(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `hours` int(11) NOT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `notes_updated_by` varchar(30) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `updated_by` varchar(30) NOT NULL,
  `date` date NOT NULL,
  `day` varchar(20) NOT NULL,
  `is_holyday` bit(1) NOT NULL,
  `week` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `project_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_timesheets`
--

INSERT INTO `user_timesheets` (`id`, `email`, `hours`, `notes`, `notes_updated_by`, `status`, `updated_by`, `date`, `day`, `is_holyday`, `week`, `month`, `year`, `project_id`) VALUES
(583, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-01', 'Wednesday', b'0', 1, 3, 2023, 4038),
(584, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-02', 'Thursday', b'0', 1, 3, 2023, 4038),
(585, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-03', 'Friday', b'0', 1, 3, 2023, 4038),
(586, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-04', 'Saturday', b'1', 1, 3, 2023, 4038),
(587, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-05', 'Sunday', b'1', 2, 3, 2023, 4038),
(588, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-06', 'Monday', b'0', 2, 3, 2023, 4038),
(589, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-07', 'Tuesday', b'0', 2, 3, 2023, 4038),
(590, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-08', 'Wednesday', b'0', 2, 3, 2023, 4038),
(591, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-09', 'Thursday', b'0', 2, 3, 2023, 4038),
(592, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-10', 'Friday', b'0', 2, 3, 2023, 4038),
(593, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-11', 'Saturday', b'1', 2, 3, 2023, 4038),
(594, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-12', 'Sunday', b'1', 3, 3, 2023, 4038),
(595, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-13', 'Monday', b'0', 3, 3, 2023, 4038),
(596, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-14', 'Tuesday', b'0', 3, 3, 2023, 4038),
(597, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-15', 'Wednesday', b'0', 3, 3, 2023, 4038),
(598, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-16', 'Thursday', b'0', 3, 3, 2023, 4038),
(599, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-17', 'Friday', b'0', 3, 3, 2023, 4038),
(600, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-18', 'Saturday', b'1', 3, 3, 2023, 4038),
(601, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-19', 'Sunday', b'1', 4, 3, 2023, 4038),
(602, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-20', 'Monday', b'0', 4, 3, 2023, 4038),
(603, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-21', 'Tuesday', b'0', 4, 3, 2023, 4038),
(604, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-22', 'Wednesday', b'0', 4, 3, 2023, 4038),
(605, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-23', 'Thursday', b'0', 4, 3, 2023, 4038),
(606, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-24', 'Friday', b'0', 4, 3, 2023, 4038),
(607, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-25', 'Saturday', b'1', 4, 3, 2023, 4038),
(608, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-26', 'Sunday', b'1', 5, 3, 2023, 4038),
(609, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-27', 'Monday', b'0', 5, 3, 2023, 4038),
(610, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-28', 'Tuesday', b'0', 5, 3, 2023, 4038),
(611, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-29', 'Wednesday', b'0', 5, 3, 2023, 4038),
(612, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-30', 'Thursday', b'0', 5, 3, 2023, 4038),
(613, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-03-31', 'Friday', b'0', 5, 3, 2023, 4038),
(614, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-01', 'Wednesday', b'0', 1, 2, 2023, 4038),
(615, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-02', 'Thursday', b'0', 1, 2, 2023, 4038),
(616, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-03', 'Friday', b'0', 1, 2, 2023, 4038),
(617, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-04', 'Saturday', b'1', 1, 2, 2023, 4038),
(618, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-05', 'Sunday', b'1', 2, 2, 2023, 4038),
(619, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-06', 'Monday', b'0', 2, 2, 2023, 4038),
(620, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-07', 'Tuesday', b'0', 2, 2, 2023, 4038),
(621, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-08', 'Wednesday', b'0', 2, 2, 2023, 4038),
(622, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-09', 'Thursday', b'0', 2, 2, 2023, 4038),
(623, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-10', 'Friday', b'0', 2, 2, 2023, 4038),
(624, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-11', 'Saturday', b'1', 2, 2, 2023, 4038),
(625, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-12', 'Sunday', b'1', 3, 2, 2023, 4038),
(626, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-13', 'Monday', b'0', 3, 2, 2023, 4038),
(627, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-14', 'Tuesday', b'0', 3, 2, 2023, 4038),
(628, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-15', 'Wednesday', b'0', 3, 2, 2023, 4038),
(629, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-16', 'Thursday', b'0', 3, 2, 2023, 4038),
(630, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-17', 'Friday', b'0', 3, 2, 2023, 4038),
(631, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-18', 'Saturday', b'1', 3, 2, 2023, 4038),
(632, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-19', 'Sunday', b'1', 4, 2, 2023, 4038),
(633, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-20', 'Monday', b'0', 4, 2, 2023, 4038),
(634, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-21', 'Tuesday', b'0', 4, 2, 2023, 4038),
(635, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-22', 'Wednesday', b'0', 4, 2, 2023, 4038),
(636, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-23', 'Thursday', b'0', 4, 2, 2023, 4038),
(637, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-24', 'Friday', b'0', 4, 2, 2023, 4038),
(638, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-25', 'Saturday', b'1', 4, 2, 2023, 4038),
(639, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-26', 'Sunday', b'1', 5, 2, 2023, 4038),
(640, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-27', 'Monday', b'0', 5, 2, 2023, 4038),
(641, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-02-28', 'Tuesday', b'0', 5, 2, 2023, 4038),
(642, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-01', 'Saturday', b'1', 1, 4, 2023, 4038),
(643, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-02', 'Sunday', b'1', 2, 4, 2023, 4038),
(644, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-03', 'Monday', b'0', 2, 4, 2023, 4038),
(645, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-04', 'Tuesday', b'0', 2, 4, 2023, 4038),
(646, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-05', 'Wednesday', b'0', 2, 4, 2023, 4038),
(647, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-06', 'Thursday', b'0', 2, 4, 2023, 4038),
(648, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-07', 'Friday', b'0', 2, 4, 2023, 4038),
(649, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-08', 'Saturday', b'1', 2, 4, 2023, 4038),
(650, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-09', 'Sunday', b'1', 3, 4, 2023, 4038),
(651, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-10', 'Monday', b'0', 3, 4, 2023, 4038),
(652, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-11', 'Tuesday', b'0', 3, 4, 2023, 4038),
(653, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-12', 'Wednesday', b'0', 3, 4, 2023, 4038),
(654, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-13', 'Thursday', b'0', 3, 4, 2023, 4038),
(655, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-14', 'Friday', b'0', 3, 4, 2023, 4038),
(656, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-15', 'Saturday', b'1', 3, 4, 2023, 4038),
(657, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-16', 'Sunday', b'1', 4, 4, 2023, 4038),
(658, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-17', 'Monday', b'0', 4, 4, 2023, 4038),
(659, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-18', 'Tuesday', b'0', 4, 4, 2023, 4038),
(660, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-19', 'Wednesday', b'0', 4, 4, 2023, 4038),
(661, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-20', 'Thursday', b'0', 4, 4, 2023, 4038),
(662, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-21', 'Friday', b'0', 4, 4, 2023, 4038),
(663, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-22', 'Saturday', b'1', 4, 4, 2023, 4038),
(664, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-23', 'Sunday', b'1', 5, 4, 2023, 4038),
(665, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-24', 'Monday', b'0', 5, 4, 2023, 4038),
(666, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-25', 'Tuesday', b'0', 5, 4, 2023, 4038),
(667, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-26', 'Wednesday', b'0', 5, 4, 2023, 4038),
(668, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-27', 'Thursday', b'0', 5, 4, 2023, 4038),
(669, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-28', 'Friday', b'0', 5, 4, 2023, 4038),
(670, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-29', 'Saturday', b'1', 5, 4, 2023, 4038),
(671, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatreddy251198@gmail.com', '2023-04-30', 'Sunday', b'1', 6, 4, 2023, 4038),
(778, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-01', 'Thursday', b'0', 1, 12, 2022, 4040),
(779, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-02', 'Friday', b'0', 1, 12, 2022, 4040),
(780, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-03', 'Saturday', b'1', 1, 12, 2022, 4040),
(781, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-04', 'Sunday', b'1', 2, 12, 2022, 4040),
(782, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-05', 'Monday', b'0', 2, 12, 2022, 4040),
(783, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-06', 'Tuesday', b'0', 2, 12, 2022, 4040),
(784, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-07', 'Wednesday', b'0', 2, 12, 2022, 4040),
(785, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-08', 'Thursday', b'0', 2, 12, 2022, 4040),
(786, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-09', 'Friday', b'0', 2, 12, 2022, 4040),
(787, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-10', 'Saturday', b'1', 2, 12, 2022, 4040),
(788, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-11', 'Sunday', b'1', 3, 12, 2022, 4040),
(789, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-12', 'Monday', b'0', 3, 12, 2022, 4040),
(790, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-13', 'Tuesday', b'0', 3, 12, 2022, 4040),
(791, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-14', 'Wednesday', b'0', 3, 12, 2022, 4040),
(792, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-15', 'Thursday', b'0', 3, 12, 2022, 4040),
(793, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-16', 'Friday', b'0', 3, 12, 2022, 4040),
(794, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-17', 'Saturday', b'1', 3, 12, 2022, 4040),
(795, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-18', 'Sunday', b'1', 4, 12, 2022, 4040),
(796, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-19', 'Monday', b'0', 4, 12, 2022, 4040),
(797, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-20', 'Tuesday', b'0', 4, 12, 2022, 4040),
(798, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-21', 'Wednesday', b'0', 4, 12, 2022, 4040),
(799, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-22', 'Thursday', b'0', 4, 12, 2022, 4040),
(800, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-23', 'Friday', b'0', 4, 12, 2022, 4040),
(801, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-24', 'Saturday', b'1', 4, 12, 2022, 4040),
(802, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-25', 'Sunday', b'1', 5, 12, 2022, 4040),
(803, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-26', 'Monday', b'0', 5, 12, 2022, 4040),
(804, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-27', 'Tuesday', b'0', 5, 12, 2022, 4040),
(805, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-28', 'Wednesday', b'0', 5, 12, 2022, 4040),
(806, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-29', 'Thursday', b'0', 5, 12, 2022, 4040),
(807, 'venkatbathula35@gmail.com', 8, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-30', 'Friday', b'0', 5, 12, 2022, 4040),
(808, 'venkatbathula35@gmail.com', 0, NULL, NULL, 'Submitted', 'venkatbathula35@gmail.com', '2022-12-31', 'Saturday', b'1', 5, 12, 2022, 4040);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_features`
--

CREATE TABLE `varsha_features` (
  `feature_code` varchar(10) NOT NULL,
  `default_ind` tinyint(1) DEFAULT NULL,
  `feature_description` varchar(500) DEFAULT NULL,
  `feature_name` varchar(20) DEFAULT NULL,
  `visible_ind` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_features`
--

INSERT INTO `varsha_features` (`feature_code`, `default_ind`, `feature_description`, `feature_name`, `visible_ind`) VALUES
('ADMUSRS', 1, 'Admin User feature is to manage all users based on role permissions.', 'Users', 1),
('EVNTS', 1, 'Event feature is to create events and edit events for all users.', 'Events', 1),
('PAYMGMT', 1, 'This is Payments features.', 'Payments', 1),
('PROJMGMT', 1, 'Project Management Feature is to add new projects and manage existing projects for admins and super admins', 'Project Management', 1),
('ROLEMGMT', 1, 'This is feature to add remove or edit Roles, permissions and features', 'Role Management', 1),
('STYLGUID', 1, 'Style Guide feature is to reference for website stylings for designers and admins.', 'Style Guide', 1),
('SUBSCRPTN', 1, 'Subscription feature is to manage all subscriptions based on role permissions.', 'Subscription', 1),
('TMESHETS', 1, 'Time Sheets is to create times sheets and edit time sheets based on role permissions.', 'Time Sheets', 1),
('TSKS', 1, 'Task feature is to create task and edit task for all users.', 'Tasks', 1);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_feature_permission_relatioship`
--

CREATE TABLE `varsha_feature_permission_relatioship` (
  `relatiohship_code` varchar(30) NOT NULL,
  `feature_code` varchar(15) DEFAULT NULL,
  `permission_code` varchar(15) DEFAULT NULL,
  `visible_ind` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_feature_permission_relatioship`
--

INSERT INTO `varsha_feature_permission_relatioship` (`relatiohship_code`, `feature_code`, `permission_code`, `visible_ind`) VALUES
('ADMUSRSACTVUSR', 'ADMUSRS', 'ACTVUSR', NULL),
('ADMUSRSADUSR', 'ADMUSRS', 'ADUSR', NULL),
('ADMUSRSDEACTVUSR', 'ADMUSRS', 'DEACTVUSR', NULL),
('ADMUSRSDLTUSR', 'ADMUSRS', 'DLTUSR', NULL),
('ADMUSRSEDTUSR', 'ADMUSRS', 'EDTUSR', NULL),
('EVNTSADEVNT', 'EVNTS', 'ADEVNT', NULL),
('EVNTSCMPLTEVNT', 'EVNTS', 'CMPLTEVNT', NULL),
('EVNTSDLTEVNT', 'EVNTS', 'DLTEVNT', NULL),
('EVNTSEDTEVNT', 'EVNTS', 'EDTEVNT', NULL),
('EVNTSEXPEVNT', 'EVNTS', 'EXPEVNT', NULL),
('PAYMGMTADPAY', 'PAYMGMT', 'ADPAY', NULL),
('PAYMGMTDLTPAY', 'PAYMGMT', 'DLTPAY', NULL),
('PAYMGMTEDITPAY', 'PAYMGMT', 'EDITPAY', NULL),
('PROJMGMTADDPROJ', 'PROJMGMT', 'ADDPROJ', NULL),
('PROJMGMTADDUSR', 'PROJMGMT', 'PROJADDUSR', NULL),
('PROJMGMTDLTPROJ', 'PROJMGMT', 'DLTPROJ', NULL),
('PROJMGMTDLTUSR', 'PROJMGMT', 'PROJDLTUSR', NULL),
('PROJMGMTEDITPROJ', 'PROJMGMT', 'EDITPROJ', NULL),
('PROJMGMTEDITUSR', 'PROJMGMT', 'PROJEDITUSR', NULL),
('ROLEMGMTADDFEAT', 'ROLEMGMT', 'ADDFEAT', NULL),
('ROLEMGMTADDPERM', 'ROLEMGMT', 'ADDPERM', NULL),
('ROLEMGMTADDROLE', 'ROLEMGMT', 'ADDROLE', NULL),
('ROLEMGMTDLTFEAT', 'ROLEMGMT', 'DLTFEAT', NULL),
('ROLEMGMTDLTPERM', 'ROLEMGMT', 'DLTPERM', NULL),
('ROLEMGMTDLTROLE', 'ROLEMGMT', 'DLTROLE', NULL),
('ROLEMGMTEDITFEAT', 'ROLEMGMT', 'EDITFEAT', NULL),
('ROLEMGMTEDITPERM', 'ROLEMGMT', 'EDITPERM', NULL),
('ROLEMGMTEDITROLE', 'ROLEMGMT', 'EDITROLE', NULL),
('STYLGUIDVSTYLGUID', 'STYLGUID', 'VSTYLGUID', NULL),
('SUBSCRPTNACTVSUBSCPT', 'SUBSCRPTN', 'ACTVSUBSCP', NULL),
('SUBSCRPTNADSUBSCPTN', 'SUBSCRPTN', 'ADSUBSCPTN', NULL),
('SUBSCRPTNDEACTVSUBSC', 'SUBSCRPTN', 'DEACTVSUBS', NULL),
('SUBSCRPTNDLTSUBSCPTN', 'SUBSCRPTN', 'DLTSUBSCPT', NULL),
('SUBSCRPTNEDTSUBSCPTN', 'SUBSCRPTN', 'EDTSUBSCPT', NULL),
('SUBSCRPTNSNDSUBSCPTN', 'SUBSCRPTN', 'SNDSUBSCPT', NULL),
('TMESHETSADTMESHT', 'TMESHETS', 'ADTMESHT', NULL),
('TMESHETSAPPVTMESHT', 'TMESHETS', 'APPVTMESHT', NULL),
('TMESHETSDENIDTMESHT', 'TMESHETS', 'DENIDTMESH', NULL),
('TMESHETSDLTTMESHT', 'TMESHETS', 'DLTTMESHT', NULL),
('TMESHETSEDTTMESHT', 'TMESHETS', 'EDTTMESHT', NULL),
('TMESHETSPNDGTMESHT', 'TMESHETS', 'PNDGTMESHT', NULL),
('TMESHETSSUBTMESHT', 'TMESHETS', 'SUBTMESHT', NULL),
('TSKSADTSK', 'TSKS', 'ADTSK', NULL),
('TSKSCMPLTTSK', 'TSKS', 'CMPLTTSK', NULL),
('TSKSDLTTSK', 'TSKS', 'DLTTSK', NULL),
('TSKSEDTTSK', 'TSKS', 'EDTTSK', NULL),
('TSKSEXPTSK', 'TSKS', 'EXPTSK', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_permissions`
--

CREATE TABLE `varsha_permissions` (
  `permission_code` varchar(15) NOT NULL,
  `default_ind` tinyint(1) DEFAULT NULL,
  `permission_description` varchar(500) DEFAULT NULL,
  `permission_name` varchar(20) DEFAULT NULL,
  `visible_ind` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_permissions`
--

INSERT INTO `varsha_permissions` (`permission_code`, `default_ind`, `permission_description`, `permission_name`, `visible_ind`) VALUES
('ACTVSUBSCP', 1, 'Activate subscription permission to activate existing deactivated subscription from subscription por', 'Activate Subscriptio', 1),
('ACTVUSR', 1, 'Activate user permission to activate existing deactivated user from admin portal.', 'Activate User', 1),
('ADDFEAT', 1, 'Add Feature', 'Add Feature', 1),
('ADDPERM', 1, 'Add Permissions', 'Add Permissions', 1),
('ADDPROJ', 1, 'Add Project Permission is to add new project.', 'Add Project', 1),
('ADDROLE', 1, 'Add Role', 'Add Role', 1),
('ADEVNT', 1, 'Add event permission is to create new events in event portal.', 'Add Event ', 1),
('ADPAY', 1, 'This is permission to add payments', 'Add Payments', 1),
('ADSUBSCPTN', 1, 'Add subscription permission to add new subscription from subscription portal based on role permissio', 'Add Subscription', 1),
('ADTMESHT', 1, 'Add timesheet permission to add new timesheet from timesheet portal for all users.', 'Add Timesheet', 1),
('ADTSK', 1, 'Add task permission to create new task from task portal.', 'Add Task', 1),
('ADUSR', 1, 'Add user permission to create new user from admin portal.', 'Add User', 1),
('APPVTMESHT', 1, 'Approve timesheet permission to approve submitted timesheet from timesheet portal based on role perm', 'Approve Timesheet', 1),
('CMPLTEVNT', 1, 'Complete event permission to make it done existing event from event portal.', 'Complete Event', 1),
('CMPLTTSK', 1, 'Complete task permission to make it done existing task from task portal.', 'Complete Task', 1),
('DEACTVSUBS', 1, 'Deactivate subscription permission to deactivate existing subscription from subscription portal base', 'Deactivate Subscript', 1),
('DEACTVUSR', 1, 'Deactivate user permission to deactivate existing user from admin portal.', 'Deactivate User', 1),
('DENIDTMESH', 1, 'Denial timesheet permission to denied submitted timesheet from timesheet portal based on role permis', 'Denial Timesheet', 1),
('DLTEVNT', 1, 'Delete event permission to delete existing event from event portal.', 'Delete Event', 1),
('DLTFEAT', 1, 'Delete Feature', 'Delete Feature', 1),
('DLTPAY', 1, 'This is permission to delete payments', 'Delete Payments', 1),
('DLTPERM', 1, 'Edit Permissions', 'Delete Permissions', 1),
('DLTPROJ', 1, 'Delete Project Permission to Delete Existing Project.', 'Delete Project', 1),
('DLTROLE', 1, 'Add Role', 'Delete Role', 1),
('DLTSUBSCPT', 1, 'Delete subscription permission to delete existing subscription from subscription portal based on rol', 'Delete Subscription', 1),
('DLTTMESHT', 1, 'Delete timesheet permission to delete existing timesheet from timesheet portal.', 'Delete Timesheet', 1),
('DLTTSK', 1, 'Delete task permission to delete existing task from task portal.', 'Delete Task', 1),
('DLTUSR', 1, 'Delete user permission to delete existing user from admin portal.', 'Delete User', 1),
('EDITFEAT', 1, 'Edit Feature', 'Edit Feature', 1),
('EDITPAY', 1, 'This is permission to edit payments', 'Edit Payments', 1),
('EDITPERM', 1, 'Edit Permissions', 'Edit Permissions', 1),
('EDITPROJ', 1, 'Edit Project Permission is to add Edit existing project.', 'Edit Project', 1),
('EDITROLE', 1, 'Edit Role', 'Edit Role', 1),
('EDTEVNT', 1, 'Edit events permission to edit existing event from events portal.', 'Edit Event', 1),
('EDTSUBSCPT', 1, 'Edit subscription permission to edit existing subscription from subscription portal based on role pe', 'Edit Subscription', 1),
('EDTTMESHT', 1, 'Edit timesheet permission to edit existing timesheet from timesheet portal.', 'Edit Timesheet', 1),
('EDTTSK', 1, 'Edit task permission to edit existing from task portal.', 'Edit Task', 1),
('EDTUSR', 1, 'Edit user permission to edit existing user from admin portal.', 'Edit User', 1),
('EXPEVNT', 1, 'Expire events permission to make it expire existing event from events portal.', 'Expire Event', 1),
('EXPTSK', 1, 'Expire task permission to expire existing task from task portal.', 'Expire Task', 1),
('PNDGTMESHT', 1, 'Pending timesheet permission to pending submitted timesheet from timesheet portal based on role perm', 'Pending Timesheet', 1),
('PROJADDUSR', 1, 'Add User To Project', 'Add User', 1),
('PROJDLTUSR', 1, 'DELETE User From Project', 'Delete User', 1),
('PROJEDITUSR', 1, 'Edit User From Project', 'Edit User', 1),
('SNDSUBSCPT', 1, 'Send subscription permission to send subscription to users from subscription portal based on role pe', 'Send Subscription', 1),
('SUBTMESHT', 1, 'Submit timesheet permission to submit timesheet from timesheet portal.', 'Submit Timesheet', 1),
('VSTYLGUID', 1, 'View style guide permission to view the stylings and reference of website from styleguide portal.', 'View Style Guide', 1);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_roles`
--

CREATE TABLE `varsha_roles` (
  `role_code` varchar(10) NOT NULL,
  `role_description` varchar(500) DEFAULT NULL,
  `role_name` varchar(20) NOT NULL,
  `visible_ind` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_roles`
--

INSERT INTO `varsha_roles` (`role_code`, `role_description`, `role_name`, `visible_ind`) VALUES
('REGUSR', 'Guest role can store based on his persimissions.', 'Regular', 1),
('RGADMN', 'Admin role can access all the administrative tasks in the application.', 'Admin', 1),
('SPADMN', 'Super Admin can Edit everything in the application.', 'Super Admin', 0);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_role_feature_permission_relatioship`
--

CREATE TABLE `varsha_role_feature_permission_relatioship` (
  `relatiohship_code` varchar(30) NOT NULL,
  `feature_code` varchar(15) DEFAULT NULL,
  `permission_code` varchar(15) DEFAULT NULL,
  `role_code` varchar(15) DEFAULT NULL,
  `visible_ind` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_role_feature_permission_relatioship`
--

INSERT INTO `varsha_role_feature_permission_relatioship` (`relatiohship_code`, `feature_code`, `permission_code`, `role_code`, `visible_ind`) VALUES
('REGUSREVNTSADEVNT', 'EVNTS', 'ADEVNT', 'REGUSR', 1),
('REGUSREVNTSCMPLTEVNT', 'EVNTS', 'CMPLTEVNT', 'REGUSR', 1),
('REGUSREVNTSEXPEVNT', 'EVNTS', 'EXPEVNT', 'REGUSR', 1),
('REGUSRTMESHETSADTMES', 'TMESHETS', 'ADTMESHT', 'REGUSR', 1),
('REGUSRTMESHETSSUBTME', 'TMESHETS', 'SUBTMESHT', 'REGUSR', 1),
('REGUSRTSKSADTSK', 'TSKS', 'ADTSK', 'REGUSR', 1),
('REGUSRTSKSCMPLTTSK', 'TSKS', 'CMPLTTSK', 'REGUSR', 1),
('REGUSRTSKSEXPTSK', 'TSKS', 'EXPTSK', 'REGUSR', 1),
('RGADMNADMUSRSACTVUSR', 'ADMUSRS', 'ACTVUSR', 'RGADMN', 1),
('RGADMNADMUSRSADUSR', 'ADMUSRS', 'ADUSR', 'RGADMN', 1),
('RGADMNADMUSRSDEACTVU', 'ADMUSRS', 'DEACTVUSR', 'RGADMN', 1),
('RGADMNADMUSRSDLTUSR', 'ADMUSRS', 'DLTUSR', 'RGADMN', 1),
('RGADMNADMUSRSEDTUSR', 'ADMUSRS', 'EDTUSR', 'RGADMN', 1),
('RGADMNEVNTSADEVNT', 'EVNTS', 'ADEVNT', 'RGADMN', 1),
('RGADMNEVNTSCMPLTEVNT', 'EVNTS', 'CMPLTEVNT', 'RGADMN', 1),
('RGADMNEVNTSDLTEVNT', 'EVNTS', 'DLTEVNT', 'RGADMN', 1),
('RGADMNEVNTSEDTEVNT', 'EVNTS', 'EDTEVNT', 'RGADMN', 1),
('RGADMNEVNTSEXPEVNT', 'EVNTS', 'EXPEVNT', 'RGADMN', 1),
('RGADMNPAYMGMTADPAY', 'PAYMGMT', 'ADPAY', 'RGADMN', 1),
('RGADMNPAYMGMTDLTPAY', 'PAYMGMT', 'DLTPAY', 'RGADMN', 1),
('RGADMNPAYMGMTEDITPAY', 'PAYMGMT', 'EDITPAY', 'RGADMN', 1),
('RGADMNPROJMGMTADDPROJ', 'PROJMGMT', 'ADDPROJ', 'RGADMN', 1),
('RGADMNPROJMGMTADDUSR', 'PROJMGMT', 'PROJADDUSR', 'RGADMN', 1),
('RGADMNPROJMGMTDLTPROJ', 'PROJMGMT', 'DLTPROJ', 'RGADMN', 1),
('RGADMNPROJMGMTDLTUSR', 'PROJMGMT', 'PROJDLTUSR', 'RGADMN', 1),
('RGADMNPROJMGMTEDITPROJ', 'PROJMGMT', 'EDITPROJ', 'RGADMN', 1),
('RGADMNPROJMGMTEDITUSR', 'PROJMGMT', 'PROJEDITUS', 'RGADMN', 1),
('RGADMNSUBSCRPTNACTVS', 'SUBSCRPTN', 'ACTVSUBSCP', 'RGADMN', 1),
('RGADMNSUBSCRPTNADSUB', 'SUBSCRPTN', 'ADSUBSCPTN', 'RGADMN', 1),
('RGADMNSUBSCRPTNDEACT', 'SUBSCRPTN', 'DEACTVSUBS', 'RGADMN', 1),
('RGADMNSUBSCRPTNDLTSU', 'SUBSCRPTN', 'DLTSUBSCPT', 'RGADMN', 1),
('RGADMNSUBSCRPTNEDTSU', 'SUBSCRPTN', 'EDTSUBSCPT', 'RGADMN', 1),
('RGADMNSUBSCRPTNSNDSU', 'SUBSCRPTN', 'SNDSUBSCPT', 'RGADMN', 1),
('RGADMNTMESHETSADTMES', 'TMESHETS', 'ADTMESHT', 'RGADMN', 1),
('RGADMNTMESHETSAPPVTM', 'TMESHETS', 'APPVTMESHT', 'RGADMN', 1),
('RGADMNTMESHETSDENIDT', 'TMESHETS', 'DENIDTMESH', 'RGADMN', 1),
('RGADMNTMESHETSDLTTME', 'TMESHETS', 'DLTTMESHT', 'RGADMN', 1),
('RGADMNTMESHETSEDTTME', 'TMESHETS', 'EDTTMESHT', 'RGADMN', 1),
('RGADMNTMESHETSPNDGTM', 'TMESHETS', 'PNDGTMESHT', 'RGADMN', 1),
('RGADMNTMESHETSSUBTME', 'TMESHETS', 'SUBTMESHT', 'RGADMN', 1),
('RGADMNTSKSADTSK', 'TSKS', 'ADTSK', 'RGADMN', 1),
('RGADMNTSKSCMPLTTSK', 'TSKS', 'CMPLTTSK', 'RGADMN', 1),
('RGADMNTSKSDLTTSK', 'TSKS', 'DLTTSK', 'RGADMN', 1),
('RGADMNTSKSEDTTSK', 'TSKS', 'EDTTSK', 'RGADMN', 1),
('RGADMNTSKSEXPTSK', 'TSKS', 'EXPTSK', 'RGADMN', 1),
('SPADMNADMUSRSACTVUSR', 'ADMUSRS', 'ACTVUSR', 'SPADMN', 1),
('SPADMNADMUSRSADUSR', 'ADMUSRS', 'ADUSR', 'SPADMN', 1),
('SPADMNADMUSRSDEACTVU', 'ADMUSRS', 'DEACTVUSR', 'SPADMN', 1),
('SPADMNADMUSRSDLTUSR', 'ADMUSRS', 'DLTUSR', 'SPADMN', 1),
('SPADMNADMUSRSEDTUSR', 'ADMUSRS', 'EDTUSR', 'SPADMN', 1),
('SPADMNEVNTSADEVNT', 'EVNTS', 'ADEVNT', 'SPADMN', 1),
('SPADMNEVNTSCMPLTEVNT', 'EVNTS', 'CMPLTEVNT', 'SPADMN', 1),
('SPADMNEVNTSDLTEVNT', 'EVNTS', 'DLTEVNT', 'SPADMN', 1),
('SPADMNEVNTSEDTEVNT', 'EVNTS', 'EDTEVNT', 'SPADMN', 1),
('SPADMNEVNTSEXPEVNT', 'EVNTS', 'EXPEVNT', 'SPADMN', 1),
('SPADMNPAYMGMTADPAY', 'PAYMGMT', 'ADPAY', 'SPADMN', 1),
('SPADMNPAYMGMTDLTPAY', 'PAYMGMT', 'DLTPAY', 'SPADMN', 1),
('SPADMNPAYMGMTEDITPAY', 'PAYMGMT', 'EDITPAY', 'SPADMN', 1),
('SPADMNPROJMGMTADDPROJ', 'PROJMGMT', 'ADDPROJ', 'SPADMN', 1),
('SPADMNPROJMGMTADDUSR', 'PROJMGMT', 'PROJADDUSR', 'SPADMN', 1),
('SPADMNPROJMGMTDLTPROJ', 'PROJMGMT', 'DLTPROJ', 'SPADMN', 1),
('SPADMNPROJMGMTDLTUSR', 'PROJMGMT', 'PROJDLTUSR', 'SPADMN', 1),
('SPADMNPROJMGMTEDITPROJ', 'PROJMGMT', 'EDITPROJ', 'SPADMN', 1),
('SPADMNPROJMGMTEDITUSR', 'PROJMGMT', 'PROJEDITUSR', 'SPADMN', 1),
('SPADMNROLEMGMTADDFEAT', 'ROLEMGMT', 'ADDFEAT', 'SPADMN', 1),
('SPADMNROLEMGMTADDPERM', 'ROLEMGMT', 'ADDPERM', 'SPADMN', 1),
('SPADMNROLEMGMTADDROLE', 'ROLEMGMT', 'ADDROLE', 'SPADMN', 1),
('SPADMNROLEMGMTDLTFEAT', 'ROLEMGMT', 'DLTFEAT', 'SPADMN', 1),
('SPADMNROLEMGMTDLTPERM', 'ROLEMGMT', 'DLTPERM', 'SPADMN', 1),
('SPADMNROLEMGMTDLTROLE', 'ROLEMGMT', 'DLTROLE', 'SPADMN', 1),
('SPADMNROLEMGMTEDITFEAT', 'ROLEMGMT', 'EDITFEAT', 'SPADMN', 1),
('SPADMNROLEMGMTEDITPERM', 'ROLEMGMT', 'EDITPERM', 'SPADMN', 1),
('SPADMNROLEMGMTEDITROLE', 'ROLEMGMT', 'EDITROLE', 'SPADMN', 1),
('SPADMNSTYLGUIDVSTYLG', 'STYLGUID', 'VSTYLGUID', 'SPADMN', 1),
('SPADMNSUBSCRPTNACTVS', 'SUBSCRPTN', 'ACTVSUBSCP', 'SPADMN', 1),
('SPADMNSUBSCRPTNADSUB', 'SUBSCRPTN', 'ADSUBSCPTN', 'SPADMN', 1),
('SPADMNSUBSCRPTNDEACT', 'SUBSCRPTN', 'DEACTVSUBS', 'SPADMN', 1),
('SPADMNSUBSCRPTNDLTSU', 'SUBSCRPTN', 'DLTSUBSCPT', 'SPADMN', 1),
('SPADMNSUBSCRPTNEDTSU', 'SUBSCRPTN', 'EDTSUBSCPT', 'SPADMN', 1),
('SPADMNSUBSCRPTNSNDSU', 'SUBSCRPTN', 'SNDSUBSCPT', 'SPADMN', 1),
('SPADMNTMESHETSADTMES', 'TMESHETS', 'ADTMESHT', 'SPADMN', 1),
('SPADMNTMESHETSAPPVTM', 'TMESHETS', 'APPVTMESHT', 'SPADMN', 1),
('SPADMNTMESHETSDENIDT', 'TMESHETS', 'DENIDTMESH', 'SPADMN', 1),
('SPADMNTMESHETSDLTTME', 'TMESHETS', 'DLTTMESHT', 'SPADMN', 1),
('SPADMNTMESHETSEDTTME', 'TMESHETS', 'EDTTMESHT', 'SPADMN', 1),
('SPADMNTMESHETSPNDGTM', 'TMESHETS', 'PNDGTMESHT', 'SPADMN', 1),
('SPADMNTMESHETSSUBTME', 'TMESHETS', 'SUBTMESHT', 'SPADMN', 1),
('SPADMNTSKSADTSK', 'TSKS', 'ADTSK', 'SPADMN', 1),
('SPADMNTSKSCMPLTTSK', 'TSKS', 'CMPLTTSK', 'SPADMN', 1),
('SPADMNTSKSDLTTSK', 'TSKS', 'DLTTSK', 'SPADMN', 1),
('SPADMNTSKSEDTTSK', 'TSKS', 'EDTTSK', 'SPADMN', 1),
('SPADMNTSKSEXPTSK', 'TSKS', 'EXPTSK', 'SPADMN', 1);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_role_feature_relatioship`
--

CREATE TABLE `varsha_role_feature_relatioship` (
  `relatiohship_code` varchar(20) NOT NULL,
  `feature_code` varchar(10) NOT NULL,
  `role_code` varchar(10) NOT NULL,
  `visible_ind` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_role_feature_relatioship`
--

INSERT INTO `varsha_role_feature_relatioship` (`relatiohship_code`, `feature_code`, `role_code`, `visible_ind`) VALUES
('REGUSREVNTS', 'EVNTS', 'REGUSR', 1),
('REGUSRTMESHETS', 'TMESHETS', 'REGUSR', 1),
('REGUSRTSKS', 'TSKS', 'REGUSR', 1),
('RGADMNADMUSRS', 'ADMUSRS', 'RGADMN', 1),
('RGADMNEVNTS', 'EVNTS', 'RGADMN', 1),
('RGADMNPAYMGMT', 'PAYMGMT', 'RGADMN', 1),
('RGADMNPROJMGMT', 'PROJMGMT', 'RGADMN', 1),
('RGADMNSUBSCRPTN', 'SUBSCRPTN', 'RGADMN', 1),
('RGADMNTMESHETS', 'TMESHETS', 'RGADMN', 1),
('RGADMNTSKS', 'TSKS', 'RGADMN', 1),
('SPADMNADMUSRS', 'ADMUSRS', 'SPADMN', 1),
('SPADMNEVNTS', 'EVNTS', 'SPADMN', 1),
('SPADMNPAYMGMT', 'PAYMGMT', 'SPADMN', 1),
('SPADMNPROJMGMT', 'PROJMGMT', 'SPADMN', 1),
('SPADMNROLEMGMT', 'ROLEMGMT', 'SPADMN', 1),
('SPADMNSTYLGUID', 'STYLGUID', 'SPADMN', 1),
('SPADMNSUBSCRPTN', 'SUBSCRPTN', 'SPADMN', 1),
('SPADMNTMESHETS', 'TMESHETS', 'SPADMN', 1),
('SPADMNTSKS', 'TSKS', 'SPADMN', 1);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user`
--

CREATE TABLE `varsha_user` (
  `email` varchar(30) NOT NULL DEFAULT '',
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `user_role` varchar(10) NOT NULL,
  `address_line1` varchar(100) NOT NULL,
  `address_line2` varchar(50) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zip_code` int(11) NOT NULL,
  `phone_number` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_user`
--

INSERT INTO `varsha_user` (`email`, `first_name`, `last_name`, `user_role`, `address_line1`, `address_line2`, `city`, `country`, `state`, `zip_code`, `phone_number`) VALUES
('arun@gmail.com', 'Arun', 'Kondla', 'REGUSR', '3551 FORESTDALE DR', 'APT DI', 'BURLINGTON', 'USA', 'NC', 27215, '2147483647'),
('venkatbathula35@gmail.com', 'Venkat Reddy', 'Bathula', 'REGUSR', '10415 Glenmere Creek Cir', 'N/A', 'Charlotte', 'USA', 'NC', 28262, '(408) 475-4949'),
('venkateng10@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'NC', 28262, '(987) 654-3210'),
('venkateng11@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'NC', 28262, '(987) 654-3210'),
('venkateng12@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'NC', 28262, '(987) 654-3210'),
('venkateng13@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'NC', 28262, '(987) 654-3210'),
('venkateng14@yopmail.com', 'Venkat', 'Reddy', 'RGADMN', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng1@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng2@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng3@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng4@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng5@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng6@gmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng6@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng7@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng8@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng9@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkateng@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
('venkatreddy251198@gmail.com', 'Venkat Reddy', 'Bathula', 'SPADMN', '3551 FORESTALE DR', 'APT DI', 'BURLINGTON', 'USA', 'NC', 27215, '4084317411'),
('venkatreddy@gmail.com', 'Venkat test', 'Reddy test', 'REGUSR', '3060 S CHURCH ST', 'TEST', 'BURLINGTON', 'USA', 'NC', 27215, '4084317411'),
('venkattest@gmail.com', 'Venkat2', 'Reddy2', 'REGUSR', '3061 S CHURCH ST', '', 'BURLINGTON', 'USA', 'NC', 27215, '9857654865');

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_auth`
--

CREATE TABLE `varsha_user_auth` (
  `email` varchar(30) NOT NULL,
  `fppin` bigint(20) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `pin` bigint(20) NOT NULL,
  `register_date` datetime NOT NULL,
  `user_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_user_auth`
--

INSERT INTO `varsha_user_auth` (`email`, `fppin`, `password`, `pin`, `register_date`, `user_status`) VALUES
('arun@gmail.com', NULL, '$2a$10$Ow8l5qg8Dd.St2e6MHZfFOypWvaaFQ/HSkMqUB1Bx1wUTRk/QbPNG', 3456, '2020-03-15 14:41:09', 'locked'),
('venkatbathula35@gmail.com', NULL, '$2a$10$wIbZh9X.2llW4XJzs/zS/.A4TmRHmkErupHYsQVhEbXf9OxR2AM1m', 9948819102, '2024-01-21 04:29:07', 'active'),
('venkateng10@yopmail.com', NULL, '$2a$10$IwV9cqIdELyh2kwovDfdkeXdhwfjEKPBwt.fR0HZHcDWosrn8Kita', 7387191875, '2023-08-09 19:50:52', 'pending'),
('venkateng11@yopmail.com', NULL, '$2a$10$5k5ToCHlJH8JQ1J1CC2O9.O7i55YArgpBDom7JpIJ8dAN8ci9oz.i', 1808460577, '2023-08-09 19:59:30', 'pending'),
('venkateng12@yopmail.com', NULL, '$2a$10$ed3NpVfMdNmr01avrR3UROW6d/O8ymnXDDfKWc5OfLytFitERGZgm', 4377171650, '2023-08-09 20:10:17', 'pending'),
('venkateng13@yopmail.com', NULL, '$2a$10$w8zA.ICBpOo0aa8GQCXBm.vP3ScfMVdbESoZorVbjLg5DLBcRsFbO', 2097224466, '2023-08-09 20:14:40', 'pending'),
('venkateng14@yopmail.com', NULL, '$2a$10$om7JiximVHvu7uPcBkN.k.Rl8S8.tgwLVqbAVUM64ixDDOveBlqby', 8475529241, '2023-08-15 04:28:43', 'active'),
('venkateng1@yopmail.com', NULL, '$2a$10$82MyZgOvxA1fqfk1JvfeBOxBG/a/p9IyKI5F2LWCxg1WFXLCwjggS', 7718280652, '2023-08-07 22:42:27', 'pending'),
('venkateng2@yopmail.com', NULL, '$2a$10$Ow8l5qg8Dd.St2e6MHZfFOypWvaaFQ/HSkMqUB1Bx1wUTRk/QbPNG', 5507923566, '2023-08-07 22:44:22', 'pending'),
('venkateng3@yopmail.com', NULL, '$2a$10$vIEmPCV1hRdGS.SFlx7v1eYd.kNPeRMscCyypCp4OSBB4CWKzfr0q', 3551181094, '2023-08-07 23:09:04', 'pending'),
('venkateng4@yopmail.com', NULL, '$2a$10$SoLIFjEB1kGlQQ/q5.AnXuOfyqQsukeN3rCciLGbyWyO754EI.a1m', 7063684857, '2023-08-07 23:20:20', 'pending'),
('venkateng5@yopmail.com', NULL, '$2a$10$A9w2hRwwKUK9Y7nTWBBw0.2MJIhkJc8Hh4w.ALsFQUSePFOcUz6ji', 9604947464, '2023-08-07 23:23:39', 'pending'),
('venkateng6@gmail.com', NULL, '$2a$10$byvi.xB0foH36/owoomoVuAwcG0H9Nh2/GR0qlH0FkYMeOgh1c942', 1307534304, '2023-08-07 23:28:41', 'pending'),
('venkateng6@yopmail.com', NULL, '$2a$10$5KmsfNPYU32EuXmK7Kvltu7u0.0IqLbjwzpg1mDIPNpMOif2FhKCW', 5932573122, '2023-08-07 23:27:31', 'pending'),
('venkateng7@yopmail.com', NULL, '$2a$10$OTr5AtHig8V/bqyA8UzFUulokfpq2MJtAD/MsLyZfQ/LfkEX/FXIC', 3985638777, '2023-08-07 23:34:21', 'pending'),
('venkateng8@yopmail.com', NULL, '$2a$10$vaqWa4iGMVsooK4LXIx8re3I2eyQjMd1PS9WFHlvOBsb4F5kr13Y.', 6569846970, '2023-08-07 23:35:44', 'pending'),
('venkateng9@yopmail.com', NULL, '$2a$10$N.5sSIIBVnPTdmimzks5KuaRP8WT0n8z53uRfyinIqJIT9Ik97mve', 5470347200, '2023-08-07 23:49:23', 'pending'),
('venkateng@yopmail.com', NULL, '$2a$10$YnjJdz9lTqTcyM3OVzVw9OajV8tL.xXCgYFVnZ5S/XZkAjBdWazj2', 2978544565, '2023-08-07 22:36:30', 'active'),
('venkatreddy251198@gmail.com', NULL, '$2a$10$82MyZgOvxA1fqfk1JvfeBOxBG/a/p9IyKI5F2LWCxg1WFXLCwjggS', 1234, '2020-03-15 16:30:00', 'active'),
('venkatreddy@gmail.com', NULL, '$2a$10$Ow8l5qg8Dd.St2e6MHZfFOypWvaaFQ/HSkMqUB1Bx1wUTRk/QbPNG', 2345, '2020-03-15 16:33:12', 'pending'),
('venkattest@gmail.com', NULL, '$2a$10$Ow8l5qg8Dd.St2e6MHZfFOypWvaaFQ/HSkMqUB1Bx1wUTRk/QbPNG', 4567, '2020-03-15 16:44:18', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_event`
--

CREATE TABLE `varsha_user_event` (
  `id` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `event_budget` varchar(255) DEFAULT NULL,
  `event_date` datetime DEFAULT NULL,
  `event_description` varchar(255) DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_features`
--

CREATE TABLE `varsha_user_features` (
  `email` varchar(30) NOT NULL,
  `feature_code` varchar(10) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_user_features`
--

INSERT INTO `varsha_user_features` (`email`, `feature_code`, `id`) VALUES
('venkateng4@yopmail.com', 'EVNTS', 7),
('venkateng4@yopmail.com', 'TMESHETS', 8),
('venkateng4@yopmail.com', 'TSKS', 9),
('venkateng5@yopmail.com', 'EVNTS', 18),
('venkateng5@yopmail.com', 'TMESHETS', 19),
('venkateng5@yopmail.com', 'TSKS', 20),
('venkateng6@yopmail.com', 'EVNTS', 29),
('venkateng6@yopmail.com', 'TMESHETS', 30),
('venkateng6@yopmail.com', 'TSKS', 31),
('venkateng6@gmail.com', 'EVNTS', 40),
('venkateng6@gmail.com', 'TMESHETS', 41),
('venkateng6@gmail.com', 'TSKS', 42),
('venkateng7@yopmail.com', 'EVNTS', 51),
('venkateng7@yopmail.com', 'TMESHETS', 52),
('venkateng7@yopmail.com', 'TSKS', 53),
('venkateng8@yopmail.com', 'EVNTS', 62),
('venkateng8@yopmail.com', 'TMESHETS', 63),
('venkateng8@yopmail.com', 'TSKS', 64),
('venkateng9@yopmail.com', 'EVNTS', 73),
('venkateng9@yopmail.com', 'TMESHETS', 74),
('venkateng9@yopmail.com', 'TSKS', 75),
('venkateng10@yopmail.com', 'EVNTS', 84),
('venkateng10@yopmail.com', 'TMESHETS', 85),
('venkateng10@yopmail.com', 'TSKS', 86),
('venkateng11@yopmail.com', 'EVNTS', 95),
('venkateng11@yopmail.com', 'TMESHETS', 96),
('venkateng11@yopmail.com', 'TSKS', 97),
('venkateng12@yopmail.com', 'EVNTS', 106),
('venkateng12@yopmail.com', 'TMESHETS', 107),
('venkateng12@yopmail.com', 'TSKS', 108),
('venkateng13@yopmail.com', 'EVNTS', 117),
('venkateng13@yopmail.com', 'TMESHETS', 118),
('venkateng13@yopmail.com', 'TSKS', 119),
('venkateng14@yopmail.com', 'ADMUSRS', 385),
('venkateng14@yopmail.com', 'EVNTS', 386),
('venkateng14@yopmail.com', 'PROJMGMT', 387),
('venkateng14@yopmail.com', 'SUBSCRPTN', 388),
('venkateng14@yopmail.com', 'TMESHETS', 389),
('venkateng14@yopmail.com', 'TSKS', 390),
('venkattest@gmail.com', 'TMESHETS', 672),
('venkattest@gmail.com', 'TMESHETS', 673),
('venkattest@gmail.com', 'PROJMGMT', 674),
('venkateng@yopmail.com', 'EVNTS', 688),
('venkateng@yopmail.com', 'TSKS', 689),
('venkateng@yopmail.com', 'TMESHETS', 690),
('venkatbathula35@gmail.com', 'EVNTS', 1155),
('venkatbathula35@gmail.com', 'TMESHETS', 1156),
('venkatbathula35@gmail.com', 'TSKS', 1157),
('venkatreddy251198@gmail.com', 'ADMUSRS', 1225),
('venkatreddy251198@gmail.com', 'EVNTS', 1226),
('venkatreddy251198@gmail.com', 'PAYMGMT', 1227),
('venkatreddy251198@gmail.com', 'PROJMGMT', 1228),
('venkatreddy251198@gmail.com', 'STYLGUID', 1229),
('venkatreddy251198@gmail.com', 'SUBSCRPTN', 1230),
('venkatreddy251198@gmail.com', 'TMESHETS', 1231),
('venkatreddy251198@gmail.com', 'TSKS', 1232);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_permissions`
--

CREATE TABLE `varsha_user_permissions` (
  `email` varchar(30) NOT NULL,
  `id` bigint(20) NOT NULL DEFAULT 0,
  `permission_code` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `varsha_user_permissions`
--

INSERT INTO `varsha_user_permissions` (`email`, `id`, `permission_code`) VALUES
('venkateng6@yopmail.com', 32, 'ADEVNT'),
('venkateng6@yopmail.com', 33, 'CMPLTEVNT'),
('venkateng6@yopmail.com', 34, 'EXPEVNT'),
('venkateng6@yopmail.com', 35, 'ADTMESHT'),
('venkateng6@yopmail.com', 36, 'SUBTMESHT'),
('venkateng6@yopmail.com', 37, 'ADTSK'),
('venkateng6@yopmail.com', 38, 'CMPLTTSK'),
('venkateng6@yopmail.com', 39, 'EXPTSK'),
('venkateng6@gmail.com', 43, 'ADEVNT'),
('venkateng6@gmail.com', 44, 'CMPLTEVNT'),
('venkateng6@gmail.com', 45, 'EXPEVNT'),
('venkateng6@gmail.com', 46, 'ADTMESHT'),
('venkateng6@gmail.com', 47, 'SUBTMESHT'),
('venkateng6@gmail.com', 48, 'ADTSK'),
('venkateng6@gmail.com', 49, 'CMPLTTSK'),
('venkateng6@gmail.com', 50, 'EXPTSK'),
('venkateng7@yopmail.com', 54, 'ADEVNT'),
('venkateng7@yopmail.com', 55, 'CMPLTEVNT'),
('venkateng7@yopmail.com', 56, 'EXPEVNT'),
('venkateng7@yopmail.com', 57, 'ADTMESHT'),
('venkateng7@yopmail.com', 58, 'SUBTMESHT'),
('venkateng7@yopmail.com', 59, 'ADTSK'),
('venkateng7@yopmail.com', 60, 'CMPLTTSK'),
('venkateng7@yopmail.com', 61, 'EXPTSK'),
('venkateng8@yopmail.com', 65, 'ADEVNT'),
('venkateng8@yopmail.com', 66, 'CMPLTEVNT'),
('venkateng8@yopmail.com', 67, 'EXPEVNT'),
('venkateng8@yopmail.com', 68, 'ADTMESHT'),
('venkateng8@yopmail.com', 69, 'SUBTMESHT'),
('venkateng8@yopmail.com', 70, 'ADTSK'),
('venkateng8@yopmail.com', 71, 'CMPLTTSK'),
('venkateng8@yopmail.com', 72, 'EXPTSK'),
('venkateng9@yopmail.com', 76, 'ADEVNT'),
('venkateng9@yopmail.com', 77, 'CMPLTEVNT'),
('venkateng9@yopmail.com', 78, 'EXPEVNT'),
('venkateng9@yopmail.com', 79, 'ADTMESHT'),
('venkateng9@yopmail.com', 80, 'SUBTMESHT'),
('venkateng9@yopmail.com', 81, 'ADTSK'),
('venkateng9@yopmail.com', 82, 'CMPLTTSK'),
('venkateng9@yopmail.com', 83, 'EXPTSK'),
('venkateng10@yopmail.com', 87, 'ADEVNT'),
('venkateng10@yopmail.com', 88, 'CMPLTEVNT'),
('venkateng10@yopmail.com', 89, 'EXPEVNT'),
('venkateng10@yopmail.com', 90, 'ADTMESHT'),
('venkateng10@yopmail.com', 91, 'SUBTMESHT'),
('venkateng10@yopmail.com', 92, 'ADTSK'),
('venkateng10@yopmail.com', 93, 'CMPLTTSK'),
('venkateng10@yopmail.com', 94, 'EXPTSK'),
('venkateng11@yopmail.com', 98, 'ADEVNT'),
('venkateng11@yopmail.com', 99, 'CMPLTEVNT'),
('venkateng11@yopmail.com', 100, 'EXPEVNT'),
('venkateng11@yopmail.com', 101, 'ADTMESHT'),
('venkateng11@yopmail.com', 102, 'SUBTMESHT'),
('venkateng11@yopmail.com', 103, 'ADTSK'),
('venkateng11@yopmail.com', 104, 'CMPLTTSK'),
('venkateng11@yopmail.com', 105, 'EXPTSK'),
('venkateng12@yopmail.com', 109, 'ADEVNT'),
('venkateng12@yopmail.com', 110, 'CMPLTEVNT'),
('venkateng12@yopmail.com', 111, 'EXPEVNT'),
('venkateng12@yopmail.com', 112, 'ADTMESHT'),
('venkateng12@yopmail.com', 113, 'SUBTMESHT'),
('venkateng12@yopmail.com', 114, 'ADTSK'),
('venkateng12@yopmail.com', 115, 'CMPLTTSK'),
('venkateng12@yopmail.com', 116, 'EXPTSK'),
('venkateng13@yopmail.com', 120, 'ADEVNT'),
('venkateng13@yopmail.com', 121, 'CMPLTEVNT'),
('venkateng13@yopmail.com', 122, 'EXPEVNT'),
('venkateng13@yopmail.com', 123, 'ADTMESHT'),
('venkateng13@yopmail.com', 124, 'SUBTMESHT'),
('venkateng13@yopmail.com', 125, 'ADTSK'),
('venkateng13@yopmail.com', 126, 'CMPLTTSK'),
('venkateng13@yopmail.com', 127, 'EXPTSK'),
('venkateng14@yopmail.com', 391, 'ACTVUSR'),
('venkateng14@yopmail.com', 392, 'ADUSR'),
('venkateng14@yopmail.com', 393, 'EDTUSR'),
('venkateng14@yopmail.com', 394, 'ADEVNT'),
('venkateng14@yopmail.com', 395, 'CMPLTEVNT'),
('venkateng14@yopmail.com', 396, 'EDTEVNT'),
('venkateng14@yopmail.com', 397, 'EXPEVNT'),
('venkateng14@yopmail.com', 398, 'EDITPROJ'),
('venkateng14@yopmail.com', 399, 'ACTVSUBSCP'),
('venkateng14@yopmail.com', 400, 'ADSUBSCPTN'),
('venkateng14@yopmail.com', 401, 'EDTSUBSCPT'),
('venkateng14@yopmail.com', 402, 'SNDSUBSCPT'),
('venkateng14@yopmail.com', 403, 'ADTMESHT'),
('venkateng14@yopmail.com', 404, 'APPVTMESHT'),
('venkateng14@yopmail.com', 405, 'DENIDTMESH'),
('venkateng14@yopmail.com', 406, 'EDTTMESHT'),
('venkateng14@yopmail.com', 407, 'PNDGTMESHT'),
('venkateng14@yopmail.com', 408, 'SUBTMESHT'),
('venkateng14@yopmail.com', 409, 'ADTSK'),
('venkateng14@yopmail.com', 410, 'CMPLTTSK'),
('venkateng14@yopmail.com', 411, 'EXPTSK'),
('venkateng14@yopmail.com', 412, 'ADDPROJ'),
('venkattest@gmail.com', 675, 'ADTMESHT'),
('venkattest@gmail.com', 676, 'SUBTMESHT'),
('venkattest@gmail.com', 677, 'APPVTMESHT'),
('venkattest@gmail.com', 678, 'DENIDTMESH'),
('venkattest@gmail.com', 679, 'DLTTMESHT'),
('venkattest@gmail.com', 680, 'EDTTMESHT'),
('venkattest@gmail.com', 681, 'PNDGTMESHT'),
('venkattest@gmail.com', 682, 'ADDPROJ'),
('venkattest@gmail.com', 683, 'DLTPROJ'),
('venkattest@gmail.com', 684, 'EDITPROJ'),
('venkateng@yopmail.com', 691, 'ADEVNT'),
('venkateng@yopmail.com', 692, 'CMPLTEVNT'),
('venkateng@yopmail.com', 693, 'DLTEVNT'),
('venkateng@yopmail.com', 694, 'EDTEVNT'),
('venkateng@yopmail.com', 695, 'EXPEVNT'),
('venkateng@yopmail.com', 696, 'ADTSK'),
('venkateng@yopmail.com', 697, 'CMPLTTSK'),
('venkateng@yopmail.com', 698, 'DLTTSK'),
('venkateng@yopmail.com', 699, 'EDTTSK'),
('venkateng@yopmail.com', 700, 'EXPTSK'),
('venkateng@yopmail.com', 701, 'SUBTMESHT'),
('venkatbathula35@gmail.com', 1158, 'ADEVNT'),
('venkatbathula35@gmail.com', 1159, 'CMPLTEVNT'),
('venkatbathula35@gmail.com', 1160, 'DLTEVNT'),
('venkatbathula35@gmail.com', 1161, 'EDTEVNT'),
('venkatbathula35@gmail.com', 1162, 'EXPEVNT'),
('venkatbathula35@gmail.com', 1163, 'SUBTMESHT'),
('venkatbathula35@gmail.com', 1164, 'ADTSK'),
('venkatbathula35@gmail.com', 1165, 'CMPLTTSK'),
('venkatbathula35@gmail.com', 1166, 'DLTTSK'),
('venkatbathula35@gmail.com', 1167, 'EDTTSK'),
('venkatbathula35@gmail.com', 1168, 'EXPTSK'),
('venkatreddy251198@gmail.com', 1233, 'ACTVUSR'),
('venkatreddy251198@gmail.com', 1234, 'ADUSR'),
('venkatreddy251198@gmail.com', 1235, 'DEACTVUSR'),
('venkatreddy251198@gmail.com', 1236, 'DLTUSR'),
('venkatreddy251198@gmail.com', 1237, 'EDTUSR'),
('venkatreddy251198@gmail.com', 1238, 'ADEVNT'),
('venkatreddy251198@gmail.com', 1239, 'CMPLTEVNT'),
('venkatreddy251198@gmail.com', 1240, 'DLTEVNT'),
('venkatreddy251198@gmail.com', 1241, 'EDTEVNT'),
('venkatreddy251198@gmail.com', 1242, 'EXPEVNT'),
('venkatreddy251198@gmail.com', 1243, 'ADPAY'),
('venkatreddy251198@gmail.com', 1244, 'DLTPAY'),
('venkatreddy251198@gmail.com', 1245, 'EDITPAY'),
('venkatreddy251198@gmail.com', 1246, 'ADDPROJ'),
('venkatreddy251198@gmail.com', 1247, 'PROJADDUSR'),
('venkatreddy251198@gmail.com', 1248, 'DLTPROJ'),
('venkatreddy251198@gmail.com', 1249, 'PROJDLTUSR'),
('venkatreddy251198@gmail.com', 1250, 'EDITPROJ'),
('venkatreddy251198@gmail.com', 1251, 'PROJEDITUSR'),
('venkatreddy251198@gmail.com', 1252, 'VSTYLGUID'),
('venkatreddy251198@gmail.com', 1253, 'ACTVSUBSCP'),
('venkatreddy251198@gmail.com', 1254, 'ADSUBSCPTN'),
('venkatreddy251198@gmail.com', 1255, 'DEACTVSUBS'),
('venkatreddy251198@gmail.com', 1256, 'DLTSUBSCPT'),
('venkatreddy251198@gmail.com', 1257, 'EDTSUBSCPT'),
('venkatreddy251198@gmail.com', 1258, 'SNDSUBSCPT'),
('venkatreddy251198@gmail.com', 1259, 'ADTMESHT'),
('venkatreddy251198@gmail.com', 1260, 'APPVTMESHT'),
('venkatreddy251198@gmail.com', 1261, 'DENIDTMESH'),
('venkatreddy251198@gmail.com', 1262, 'DLTTMESHT'),
('venkatreddy251198@gmail.com', 1263, 'EDTTMESHT'),
('venkatreddy251198@gmail.com', 1264, 'PNDGTMESHT'),
('venkatreddy251198@gmail.com', 1265, 'SUBTMESHT'),
('venkatreddy251198@gmail.com', 1266, 'ADTSK'),
('venkatreddy251198@gmail.com', 1267, 'CMPLTTSK'),
('venkatreddy251198@gmail.com', 1268, 'DLTTSK'),
('venkatreddy251198@gmail.com', 1269, 'EDTTSK'),
('venkatreddy251198@gmail.com', 1270, 'EXPTSK');

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_subscriptions`
--

CREATE TABLE `varsha_user_subscriptions` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subscription_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_task`
--

CREATE TABLE `varsha_user_task` (
  `id` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `task_date` datetime DEFAULT NULL,
  `task_description` varchar(255) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `paymentsprojectsrelationship`
--
ALTER TABLE `paymentsprojectsrelationship`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_projects`
--
ALTER TABLE `user_projects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_projects_relationship`
--
ALTER TABLE `user_projects_relationship`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_timesheets`
--
ALTER TABLE `user_timesheets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `varsha_features`
--
ALTER TABLE `varsha_features`
  ADD PRIMARY KEY (`feature_code`);

--
-- Indexes for table `varsha_feature_permission_relatioship`
--
ALTER TABLE `varsha_feature_permission_relatioship`
  ADD PRIMARY KEY (`relatiohship_code`);

--
-- Indexes for table `varsha_permissions`
--
ALTER TABLE `varsha_permissions`
  ADD PRIMARY KEY (`permission_code`);

--
-- Indexes for table `varsha_roles`
--
ALTER TABLE `varsha_roles`
  ADD PRIMARY KEY (`role_code`);

--
-- Indexes for table `varsha_role_feature_permission_relatioship`
--
ALTER TABLE `varsha_role_feature_permission_relatioship`
  ADD PRIMARY KEY (`relatiohship_code`);

--
-- Indexes for table `varsha_role_feature_relatioship`
--
ALTER TABLE `varsha_role_feature_relatioship`
  ADD PRIMARY KEY (`relatiohship_code`);

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

--
-- Indexes for table `varsha_user_event`
--
ALTER TABLE `varsha_user_event`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `varsha_user_features`
--
ALTER TABLE `varsha_user_features`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `varsha_user_permissions`
--
ALTER TABLE `varsha_user_permissions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `varsha_user_subscriptions`
--
ALTER TABLE `varsha_user_subscriptions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `varsha_user_task`
--
ALTER TABLE `varsha_user_task`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `paymentsprojectsrelationship`
--
ALTER TABLE `paymentsprojectsrelationship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_projects`
--
ALTER TABLE `user_projects`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4041;

--
-- AUTO_INCREMENT for table `user_projects_relationship`
--
ALTER TABLE `user_projects_relationship`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1139;

--
-- AUTO_INCREMENT for table `user_timesheets`
--
ALTER TABLE `user_timesheets`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=809;

--
-- AUTO_INCREMENT for table `varsha_user_features`
--
ALTER TABLE `varsha_user_features`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1233;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
