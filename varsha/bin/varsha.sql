-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 05, 2023 at 07:28 PM
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
(320),
(320);

-- --------------------------------------------------------

--
-- Table structure for table `user_projects`
--

CREATE TABLE `user_projects` (
  `project_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_description` varchar(100) DEFAULT NULL,
  `project_location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_name` varchar(50) NOT NULL,
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_projects`
--

INSERT INTO `user_projects` (`project_id`, `project_description`, `project_location`, `project_name`, `id`, `created_by`, `created_date`, `modified_date`) VALUES
('4028c4f98a198eef018a198f5ccd0000', 'This is Test Project Creation ', 'Charlotte, NC, 28215', 'Test Project', '', 'venkateng14@yopmail.com', '2023-08-01 01:19:52', '2023-08-01 01:20:02');

-- --------------------------------------------------------

--
-- Table structure for table `user_project_relatioship`
--

CREATE TABLE `user_project_relatioship` (
  `relatiohship_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) NOT NULL,
  `project_id` varchar(255) NOT NULL,
  `date_and_time` datetime NOT NULL,
  `updated_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_project_relatioship`
--

INSERT INTO `user_project_relatioship` (`relatiohship_code`, `email`, `project_id`, `date_and_time`, `updated_by`) VALUES
('ghhfg54654vbh6dfgdfgdf', 'venkateng14@yopmail.com', '4028c4f98a198eef018a198f5ccd0000', '2023-08-21 15:06:32', 'venkateng14@yopmail.com');

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
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_timesheets`
--

INSERT INTO `user_timesheets` (`id`, `email`, `hours`, `notes`, `notes_updated_by`, `status`, `updated_by`, `date`, `day`, `is_holyday`, `week`, `month`, `year`) VALUES
(306, 'venkateng14@yopmail.com', 0, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-08-27', 'Sunday', b'1', 5, 8, 2023),
(307, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-08-28', 'Monday', b'0', 5, 8, 2023),
(308, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-08-29', 'Tuesday', b'0', 5, 8, 2023),
(309, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-08-30', 'Wednesday', b'0', 5, 8, 2023),
(310, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-08-31', 'Thursday', b'0', 5, 8, 2023),
(311, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-09-01', 'Friday', b'0', 1, 9, 2023),
(312, 'venkateng14@yopmail.com', 0, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-09-02', 'Saturday', b'1', 1, 9, 2023),
(313, 'venkateng14@yopmail.com', 0, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-09-03', 'Sunday', b'1', 2, 9, 2023),
(314, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-09-04', 'Monday', b'0', 2, 9, 2023),
(315, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-09-05', 'Tuesday', b'0', 2, 9, 2023),
(316, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-09-06', 'Wednesday', b'0', 2, 9, 2023),
(317, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-09-07', 'Thursday', b'0', 2, 9, 2023),
(318, 'venkateng14@yopmail.com', 8, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-09-08', 'Friday', b'0', 2, 9, 2023),
(319, 'venkateng14@yopmail.com', 0, NULL, NULL, 'Submitted', 'venkateng14@yopmail.com', '2023-09-09', 'Saturday', b'1', 2, 9, 2023);

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
('PROJMGMT', 1, 'Project Management Feature is to add new projects and manage existing projects for admins and super admins', 'Project Management', 1),
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
  `feature_code` varchar(10) DEFAULT NULL,
  `permission_code` varchar(10) DEFAULT NULL,
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
  `permission_code` varchar(10) NOT NULL,
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
('ADDPROJ', 1, 'Add Project Permission is to add new project.', 'Add Project', 1),
('ADEVNT', 1, 'Add event permission is to create new events in event portal.', 'Add Event ', 1),
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
('DLTPROJ', 1, 'Delete Project Permission to Delete Existing Project.', 'Delete Project', 1),
('DLTSUBSCPT', 1, 'Delete subscription permission to delete existing subscription from subscription portal based on rol', 'Delete Subscription', 1),
('DLTTMESHT', 1, 'Delete timesheet permission to delete existing timesheet from timesheet portal.', 'Delete Timesheet', 1),
('DLTTSK', 1, 'Delete task permission to delete existing task from task portal.', 'Delete Task', 1),
('DLTUSR', 1, 'Delete user permission to delete existing user from admin portal.', 'Delete User', 1),
('EDITPROJ', 1, 'Edit Project Permission is to add Edit existing project.', 'Edit Project', 1),
('EDTEVNT', 1, 'Edit events permission to edit existing event from events portal.', 'Edit Event', 1),
('EDTSUBSCPT', 1, 'Edit subscription permission to edit existing subscription from subscription portal based on role pe', 'Edit Subscription', 1),
('EDTTMESHT', 1, 'Edit timesheet permission to edit existing timesheet from timesheet portal.', 'Edit Timesheet', 1),
('EDTTSK', 1, 'Edit task permission to edit existing from task portal.', 'Edit Task', 1),
('EDTUSR', 1, 'Edit user permission to edit existing user from admin portal.', 'Edit User', 1),
('EXPEVNT', 1, 'Expire events permission to make it expire existing event from events portal.', 'Expire Event', 1),
('EXPTSK', 1, 'Expire task permission to expire existing task from task portal.', 'Expire Task', 1),
('PNDGTMESHT', 1, 'Pending timesheet permission to pending submitted timesheet from timesheet portal based on role perm', 'Pending Timesheet', 1),
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
  `feature_code` varchar(10) DEFAULT NULL,
  `permission_code` varchar(10) DEFAULT NULL,
  `role_code` varchar(10) DEFAULT NULL,
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
('RGADMNPROJMGMTADDPROJ', 'PROJMGMT', 'ADDPROJ', 'RGADMN', 1),
('RGADMNPROJMGMTDLTPROJ', 'PROJMGMT', 'DLTPROJ', 'RGADMN', 1),
('RGADMNPROJMGMTEDITPROJ', 'PROJMGMT', 'EDITPROJ', 'RGADMN', 1),
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
('SPADMNPROJMGMTADDPROJ', 'PROJMGMT', 'ADDPROJ', 'SPADMN', 1),
('SPADMNPROJMGMTDLTPROJ', 'PROJMGMT', 'DLTPROJ', 'SPADMN', 1),
('SPADMNPROJMGMTEDITPROJ', 'PROJMGMT', 'EDITPROJ', 'SPADMN', 1),
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
('RGADMNPROJMGMT', 'PROJMGMT', 'RGADMN', 1),
('RGADMNSUBSCRPTN', 'SUBSCRPTN', 'RGADMN', 1),
('RGADMNTMESHETS', 'TMESHETS', 'RGADMN', 1),
('RGADMNTSKS', 'TSKS', 'RGADMN', 1),
('SPADMNADMUSRS', 'ADMUSRS', 'SPADMN', 1),
('SPADMNEVNTS', 'EVNTS', 'SPADMN', 1),
('SPADMNPROJMGMT', 'PROJMGMT', 'SPADMN', 1),
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
('venkateng10@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'NC', 28262, '(987) 654-3210'),
('venkateng11@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'NC', 28262, '(987) 654-3210'),
('venkateng12@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'NC', 28262, '(987) 654-3210'),
('venkateng13@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'NC', 28262, '(987) 654-3210'),
('venkateng14@yopmail.com', 'Venkat', 'Reddy', 'REGUSR', 'test', 'test', 'Clt', 'USA', 'Nc', 28262, '(987) 654-3210'),
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
('venkattest@gmail.com', 'Venkat2', 'Reddy2', 'REGUSR', '3061 S CHURCH ST', NULL, 'BURLINGTON', 'USA', 'NC', 27215, '9857654865');

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
('venkateng@yopmail.com', NULL, '$2a$10$YnjJdz9lTqTcyM3OVzVw9OajV8tL.xXCgYFVnZ5S/XZkAjBdWazj2', 2978544565, '2023-08-07 22:36:30', 'pending'),
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
('venkateng14@yopmail.com', 'EVNTS', 128),
('venkateng14@yopmail.com', 'TMESHETS', 129),
('venkateng14@yopmail.com', 'TSKS', 130),
('venkatreddy251198@gmail.com', 'ADMUSRS', 225),
('venkatreddy251198@gmail.com', 'EVNTS', 226),
('venkatreddy251198@gmail.com', 'STYLGUID', 227),
('venkatreddy251198@gmail.com', 'SUBSCRPTN', 228),
('venkatreddy251198@gmail.com', 'TMESHETS', 229),
('venkatreddy251198@gmail.com', 'TSKS', 230),
('venkatreddy251198@gmail.com', 'PROJMGMT', 231);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_permissions`
--

CREATE TABLE `varsha_user_permissions` (
  `email` varchar(30) NOT NULL,
  `id` bigint(20) NOT NULL DEFAULT 0,
  `permission_code` varchar(10) NOT NULL DEFAULT ''
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
('venkateng14@yopmail.com', 131, 'ADEVNT'),
('venkateng14@yopmail.com', 132, 'CMPLTEVNT'),
('venkateng14@yopmail.com', 133, 'EXPEVNT'),
('venkateng14@yopmail.com', 134, 'ADTMESHT'),
('venkateng14@yopmail.com', 135, 'SUBTMESHT'),
('venkateng14@yopmail.com', 136, 'ADTSK'),
('venkateng14@yopmail.com', 137, 'CMPLTTSK'),
('venkateng14@yopmail.com', 138, 'EXPTSK'),
('venkatreddy251198@gmail.com', 232, 'ACTVUSR'),
('venkatreddy251198@gmail.com', 233, 'ADUSR'),
('venkatreddy251198@gmail.com', 234, 'DEACTVUSR'),
('venkatreddy251198@gmail.com', 235, 'DLTUSR'),
('venkatreddy251198@gmail.com', 236, 'EDTUSR'),
('venkatreddy251198@gmail.com', 237, 'ADEVNT'),
('venkatreddy251198@gmail.com', 238, 'CMPLTEVNT'),
('venkatreddy251198@gmail.com', 239, 'DLTEVNT'),
('venkatreddy251198@gmail.com', 240, 'EDTEVNT'),
('venkatreddy251198@gmail.com', 241, 'EXPEVNT'),
('venkatreddy251198@gmail.com', 242, 'VSTYLGUID'),
('venkatreddy251198@gmail.com', 243, 'ACTVSUBSCP'),
('venkatreddy251198@gmail.com', 244, 'ADSUBSCPTN'),
('venkatreddy251198@gmail.com', 245, 'DEACTVSUBS'),
('venkatreddy251198@gmail.com', 246, 'DLTSUBSCPT'),
('venkatreddy251198@gmail.com', 247, 'EDTSUBSCPT'),
('venkatreddy251198@gmail.com', 248, 'SNDSUBSCPT'),
('venkatreddy251198@gmail.com', 249, 'ADTMESHT'),
('venkatreddy251198@gmail.com', 250, 'APPVTMESHT'),
('venkatreddy251198@gmail.com', 251, 'DENIDTMESH'),
('venkatreddy251198@gmail.com', 252, 'DLTTMESHT'),
('venkatreddy251198@gmail.com', 253, 'EDTTMESHT'),
('venkatreddy251198@gmail.com', 254, 'PNDGTMESHT'),
('venkatreddy251198@gmail.com', 255, 'SUBTMESHT'),
('venkatreddy251198@gmail.com', 256, 'ADTSK'),
('venkatreddy251198@gmail.com', 257, 'CMPLTTSK'),
('venkatreddy251198@gmail.com', 258, 'DLTTSK'),
('venkatreddy251198@gmail.com', 259, 'EDTTSK'),
('venkatreddy251198@gmail.com', 260, 'EXPTSK'),
('venkatreddy251198@gmail.com', 261, 'ADDPROJ'),
('venkatreddy251198@gmail.com', 262, 'DLTPROJ'),
('venkatreddy251198@gmail.com', 263, 'EDITPROJ');

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
-- Indexes for table `user_projects`
--
ALTER TABLE `user_projects`
  ADD PRIMARY KEY (`project_id`);

--
-- Indexes for table `user_project_relatioship`
--
ALTER TABLE `user_project_relatioship`
  ADD PRIMARY KEY (`relatiohship_code`);

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
-- AUTO_INCREMENT for table `user_timesheets`
--
ALTER TABLE `user_timesheets`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=320;

--
-- AUTO_INCREMENT for table `varsha_user_features`
--
ALTER TABLE `varsha_user_features`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=232;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
