-- phpMyAdmin SQL Dump
-- version 4.4.15.9
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 06, 2020 at 03:36 AM
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
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_features`
--

CREATE TABLE IF NOT EXISTS `varsha_features` (
  `feature_code` varchar(10) NOT NULL,
  `default_ind` tinyint(1) DEFAULT NULL,
  `feature_description` varchar(500) DEFAULT NULL,
  `feature_name` varchar(20) DEFAULT NULL,
  `visible_ind` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `varsha_features`
--

INSERT INTO `varsha_features` (`feature_code`, `default_ind`, `feature_description`, `feature_name`, `visible_ind`) VALUES
('ADMUSRS', 1, 'Admin User feature is to manage all users based on role permissions.', 'Users', 1),
('EVNTS', 1, 'Event feature is to create events and edit events for all users.', 'Events', 1),
('STYLGUID', 1, 'Style Guide feature is to reference for website stylings for designers and admins.', 'Style Guide', 1),
('SUBSCRPTN', 1, 'Subscription feature is to manage all subscriptions based on role permissions.', 'Subscription', 1),
('TMESHETS', 1, 'Time Sheets is to create times sheets and edit time sheets based on role permissions.', 'Time Sheets', 1),
('TSKS', 1, 'Task feature is to create task and edit task for all users.', 'Tasks', 1);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_feature_permission_relatioship`
--

CREATE TABLE IF NOT EXISTS `varsha_feature_permission_relatioship` (
  `relatiohship_code` varchar(20) NOT NULL,
  `feature_code` varchar(10) DEFAULT NULL,
  `permission_code` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `varsha_feature_permission_relatioship`
--

INSERT INTO `varsha_feature_permission_relatioship` (`relatiohship_code`, `feature_code`, `permission_code`) VALUES
('ADMUSRSACTVUSR', 'ADMUSRS', 'ACTVUSR'),
('ADMUSRSADUSR', 'ADMUSRS', 'ADUSR'),
('ADMUSRSDEACTVUSR', 'ADMUSRS', 'DEACTVUSR'),
('ADMUSRSDLTUSR', 'ADMUSRS', 'DLTUSR'),
('ADMUSRSEDTUSR', 'ADMUSRS', 'EDTUSR'),
('EVNTSADEVNT', 'EVNTS', 'ADEVNT'),
('EVNTSCMPLTEVNT', 'EVNTS', 'CMPLTEVNT'),
('EVNTSDLTEVNT', 'EVNTS', 'DLTEVNT'),
('EVNTSEDTEVNT', 'EVNTS', 'EDTEVNT'),
('EVNTSEXPEVNT', 'EVNTS', 'EXPEVNT'),
('STYLGUIDVSTYLGUID', 'STYLGUID', 'VSTYLGUID'),
('SUBSCRPTNACTVSUBSCPT', 'SUBSCRPTN', 'ACTVSUBSCP'),
('SUBSCRPTNADSUBSCPTN', 'SUBSCRPTN', 'ADSUBSCPTN'),
('SUBSCRPTNDEACTVSUBSC', 'SUBSCRPTN', 'DEACTVSUBS'),
('SUBSCRPTNDLTSUBSCPTN', 'SUBSCRPTN', 'DLTSUBSCPT'),
('SUBSCRPTNEDTSUBSCPTN', 'SUBSCRPTN', 'EDTSUBSCPT'),
('SUBSCRPTNSNDSUBSCPTN', 'SUBSCRPTN', 'SNDSUBSCPT'),
('TMESHETSADTMESHT', 'TMESHETS', 'ADTMESHT'),
('TMESHETSAPPVTMESHT', 'TMESHETS', 'APPVTMESHT'),
('TMESHETSDENIDTMESHT', 'TMESHETS', 'DENIDTMESH'),
('TMESHETSDLTTMESHT', 'TMESHETS', 'DLTTMESHT'),
('TMESHETSEDTTMESHT', 'TMESHETS', 'EDTTMESHT'),
('TMESHETSPNDGTMESHT', 'TMESHETS', 'PNDGTMESHT'),
('TMESHETSSUBTMESHT', 'TMESHETS', 'SUBTMESHT'),
('TSKSADTSK', 'TSKS', 'ADTSK'),
('TSKSCMPLTTSK', 'TSKS', 'CMPLTTSK'),
('TSKSDLTTSK', 'TSKS', 'DLTTSK'),
('TSKSEDTTSK', 'TSKS', 'EDTTSK'),
('TSKSEXPTSK', 'TSKS', 'EXPTSK');

-- --------------------------------------------------------

--
-- Table structure for table `varsha_permissions`
--

CREATE TABLE IF NOT EXISTS `varsha_permissions` (
  `permission_code` varchar(10) NOT NULL,
  `default_ind` tinyint(1) DEFAULT NULL,
  `permission_description` varchar(500) DEFAULT NULL,
  `permission_name` varchar(20) DEFAULT NULL,
  `visible_ind` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `varsha_permissions`
--

INSERT INTO `varsha_permissions` (`permission_code`, `default_ind`, `permission_description`, `permission_name`, `visible_ind`) VALUES
('ACTVSUBSCP', 1, 'Activate subscription permission to activate existing deactivated subscription from subscription por', 'Activate Subscriptio', 1),
('ACTVUSR', 1, 'Activate user permission to activate existing deactivated user from admin portal.', 'Activate User', 1),
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
('DLTSUBSCPT', 1, 'Delete subscription permission to delete existing subscription from subscription portal based on rol', 'Delete Subscription', 1),
('DLTTMESHT', 1, 'Delete timesheet permission to delete existing timesheet from timesheet portal.', 'Delete Timesheet', 1),
('DLTTSK', 1, 'Delete task permission to delete existing task from task portal.', 'Delete Task', 1),
('DLTUSR', 1, 'Delete user permission to delete existing user from admin portal.', 'Delete User', 1),
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

CREATE TABLE IF NOT EXISTS `varsha_roles` (
  `role_code` varchar(10) NOT NULL,
  `role_description` varchar(500) DEFAULT NULL,
  `role_name` varchar(20) NOT NULL,
  `visible_ind` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE IF NOT EXISTS `varsha_role_feature_permission_relatioship` (
  `relatiohship_code` varchar(20) NOT NULL,
  `feature_code` varchar(10) DEFAULT NULL,
  `permission_code` varchar(10) DEFAULT NULL,
  `role_code` varchar(10) DEFAULT NULL,
  `visible_ind` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE IF NOT EXISTS `varsha_role_feature_relatioship` (
  `relatiohship_code` varchar(20) NOT NULL,
  `feature_code` varchar(10) NOT NULL,
  `role_code` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `varsha_role_feature_relatioship`
--

INSERT INTO `varsha_role_feature_relatioship` (`relatiohship_code`, `feature_code`, `role_code`) VALUES
('REGUSREVNTS', 'EVNTS', 'REGUSR'),
('REGUSRTMESHETS', 'TMESHETS', 'REGUSR'),
('REGUSRTSKS', 'TSKS', 'REGUSR'),
('RGADMNADMUSRS', 'ADMUSRS', 'RGADMN'),
('RGADMNEVNTS', 'EVNTS', 'RGADMN'),
('RGADMNSUBSCRPTN', 'SUBSCRPTN', 'RGADMN'),
('RGADMNTMESHETS', 'TMESHETS', 'RGADMN'),
('RGADMNTSKS', 'TSKS', 'RGADMN'),
('SPADMNADMUSRS', 'ADMUSRS', 'SPADMN'),
('SPADMNEVNTS', 'EVNTS', 'SPADMN'),
('SPADMNSTYLGUID', 'STYLGUID', 'SPADMN'),
('SPADMNSUBSCRPTN', 'SUBSCRPTN', 'SPADMN'),
('SPADMNTMESHETS', 'TMESHETS', 'SPADMN'),
('SPADMNTSKS', 'TSKS', 'SPADMN');

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

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_features`
--

CREATE TABLE IF NOT EXISTS `varsha_user_features` (
  `email` varchar(30) NOT NULL,
  `feature_code` varchar(10) NOT NULL,
  `id` bigint(20) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `varsha_user_features`
--

INSERT INTO `varsha_user_features` (`email`, `feature_code`, `id`) VALUES
('venkatreddy251198@gmail.com', 'ADMUSRS', 0),
('venkatreddy251198@gmail.com', 'EVNTS', 1),
('venkatreddy251198@gmail.com', 'STYLGUID', 2),
('venkatreddy251198@gmail.com', 'SUBSCRPTN', 3),
('venkatreddy251198@gmail.com', 'TMESHETS', 4),
('venkatreddy251198@gmail.com', 'TSKS', 5);

-- --------------------------------------------------------

--
-- Table structure for table `varsha_user_permissions`
--

CREATE TABLE IF NOT EXISTS `varsha_user_permissions` (
  `email` varchar(30) NOT NULL,
  `id` bigint(20) NOT NULL DEFAULT '0',
  `permission_code` varchar(10) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `varsha_user_permissions`
--

INSERT INTO `varsha_user_permissions` (`email`, `id`, `permission_code`) VALUES
('venkatreddy251198@gmail.com', 1, 'ACTVSUBSCP'),
('venkatreddy251198@gmail.com', 2, 'ACTVUSR'),
('venkatreddy251198@gmail.com', 3, 'ADEVNT'),
('venkatreddy251198@gmail.com', 4, 'ADSUBSCPTN'),
('venkatreddy251198@gmail.com', 5, 'ADTMESHT'),
('venkatreddy251198@gmail.com', 6, 'ADTSK'),
('venkatreddy251198@gmail.com', 7, 'ADUSR'),
('venkatreddy251198@gmail.com', 8, 'APPVTMESHT'),
('venkatreddy251198@gmail.com', 9, 'CMPLTEVNT'),
('venkatreddy251198@gmail.com', 10, 'CMPLTTSK'),
('venkatreddy251198@gmail.com', 11, 'DEACTVSUBS'),
('venkatreddy251198@gmail.com', 12, 'DEACTVUSR'),
('venkatreddy251198@gmail.com', 13, 'DENIDTMESH'),
('venkatreddy251198@gmail.com', 14, 'DLTEVNT'),
('venkatreddy251198@gmail.com', 15, 'DLTSUBSCPT'),
('venkatreddy251198@gmail.com', 16, 'DLTTMESHT'),
('venkatreddy251198@gmail.com', 17, 'DLTTSK'),
('venkatreddy251198@gmail.com', 18, 'DLTUSR'),
('venkatreddy251198@gmail.com', 19, 'EDTEVNT'),
('venkatreddy251198@gmail.com', 20, 'EDTSUBSCPT'),
('venkatreddy251198@gmail.com', 21, 'EDTTMESHT'),
('venkatreddy251198@gmail.com', 22, 'EDTTSK'),
('venkatreddy251198@gmail.com', 23, 'EDTUSR'),
('venkatreddy251198@gmail.com', 24, 'EXPEVNT'),
('venkatreddy251198@gmail.com', 25, 'EXPTSK');

--
-- Indexes for dumped tables
--

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
-- Indexes for table `varsha_user_features`
--
ALTER TABLE `varsha_user_features`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `varsha_user_permissions`
--
ALTER TABLE `varsha_user_permissions`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
