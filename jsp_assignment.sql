-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2023-05-03 10:33:36
-- 伺服器版本： 10.4.24-MariaDB
-- PHP 版本： 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `jsp_assignment`
--

-- --------------------------------------------------------

--
-- 資料表結構 `booking`
--

CREATE TABLE `booking` (
  `id` int(10) NOT NULL,
  `memberid` int(11) NOT NULL,
  `venueid` int(11) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 0,
  `createtime` timestamp NULL DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `guestlistid` int(11) DEFAULT NULL,
  `notificationid` int(11) DEFAULT NULL,
  `checkintime` datetime DEFAULT NULL,
  `checkouttime` datetime DEFAULT NULL,
  `startdate` date NOT NULL,
  `starthour` int(2) NOT NULL,
  `totalhour` int(2) NOT NULL,
  `fee` double(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `booking`
--

INSERT INTO `booking` (`id`, `memberid`, `venueid`, `status`, `createtime`, `image`, `guestlistid`, `notificationid`, `checkintime`, `checkouttime`, `startdate`, `starthour`, `totalhour`, `fee`) VALUES
(1, 1242, 3, 0, '2023-05-01 17:53:30', '', 6, 7, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00', 8, 9, 1.00);

-- --------------------------------------------------------

--
-- 資料表結構 `closing_days`
--

CREATE TABLE `closing_days` (
  `id` int(10) NOT NULL,
  `venueid` int(11) NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `closing_days`
--

INSERT INTO `closing_days` (`id`, `venueid`, `start`, `end`) VALUES
(1, 1, '2023-05-11', '2023-05-12');

-- --------------------------------------------------------

--
-- 資料表結構 `guest`
--

CREATE TABLE `guest` (
  `id` int(10) NOT NULL,
  `memberid` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `guest`
--

INSERT INTO `guest` (`id`, `memberid`, `name`, `email`) VALUES
(1, 2, 'name', 'haha@123.com');

-- --------------------------------------------------------

--
-- 資料表結構 `guestlist`
--

CREATE TABLE `guestlist` (
  `id` int(10) NOT NULL,
  `guestid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `member_comment`
--

CREATE TABLE `member_comment` (
  `id` int(10) NOT NULL,
  `bookingid` int(11) NOT NULL,
  `memberid` int(11) NOT NULL,
  `remark` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `notification`
--

CREATE TABLE `notification` (
  `id` int(10) NOT NULL,
  `templateid` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `opening_days`
--

CREATE TABLE `opening_days` (
  `id` int(10) NOT NULL,
  `venueid` int(11) NOT NULL,
  `weekdays` int(1) NOT NULL,
  `openinghour` int(2) NOT NULL,
  `closinghour` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `opening_days`
--

INSERT INTO `opening_days` (`id`, `venueid`, `weekdays`, `openinghour`, `closinghour`) VALUES
(1, 1, 1, 9, 18),
(2, 1, 2, 9, 18),
(3, 1, 3, 9, 18),
(4, 1, 4, 9, 13),
(5, 1, 5, 13, 18);

-- --------------------------------------------------------

--
-- 資料表結構 `remark_type`
--

CREATE TABLE `remark_type` (
  `id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `staff_remark`
--

CREATE TABLE `staff_remark` (
  `id` int(10) NOT NULL,
  `bookingid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `staff_remark_remark_type`
--

CREATE TABLE `staff_remark_remark_type` (
  `staff_remarkid` int(11) NOT NULL,
  `remark_typeid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `template`
--

CREATE TABLE `template` (
  `id` int(10) NOT NULL,
  `file` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 資料表結構 `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` int(8) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `role` int(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `user`
--

INSERT INTO `user` (`id`, `fname`, `lname`, `email`, `phone`, `pwd`, `role`) VALUES
(1, 'Tai Man', 'Chan', 'staff@outlook.com', 69285085, 'staff@outlook.com', 1),
(2, 'Ming', 'Siu', 'a@a.a', 69285085, 'a', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `venue`
--

CREATE TABLE `venue` (
  `id` int(10) NOT NULL,
  `staffid` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `desc` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `capacity` int(10) NOT NULL,
  `fee` double(7,2) NOT NULL,
  `hidden` bit(1) NOT NULL DEFAULT b'0',
  `lastmodifiedfee` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `venue`
--

INSERT INTO `venue` (`id`, `staffid`, `name`, `address`, `desc`, `img`, `type`, `capacity`, `fee`, `hidden`, `lastmodifiedfee`) VALUES
(1, 1, 'Tuen Mun', '18 Tsing Wun Road, Tuen Mun, New Territories', 'With audio equipments provided', '01', 'exhibition', 500, 1200.00, b'0', '2023-05-02 01:52:13'),
(2, 2, 'Sha Tin', '21 Yuen Wo Road, Sha Tin, New Territories', 'A farm that has a lot of apples, which you are not allowed to pick up.', '02', 'playground', 50, 700.00, b'0', '2023-05-03 13:23:21'),
(3, 3, 'Tsing Yi', '20 Tsing Yi Road, Tsing Yi Island, New Territories', 'Western style wedding venue', '', 'wedding', 70, 2000.00, b'0', '2023-05-03 13:29:44'),
(4, 4, 'Lee Wai Lee', '3 King Ling Road, Tseung Kwan O, New Territories', 'with booths, seats and tables provided', '', 'exhibition', 1000, 1000.00, b'0', '2023-05-03 13:32:07'),
(5, 5, 'Chai Wan', '30 Shing Tai Road, Chai Wan, Hong Kong ', 'grand hall with 250 Square metres', '', 'exhibition', 5000, 10000.00, b'0', '2023-05-03 13:38:08');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `closing_days`
--
ALTER TABLE `closing_days`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `guest`
--
ALTER TABLE `guest`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `guestlist`
--
ALTER TABLE `guestlist`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `member_comment`
--
ALTER TABLE `member_comment`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `opening_days`
--
ALTER TABLE `opening_days`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `remark_type`
--
ALTER TABLE `remark_type`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `staff_remark`
--
ALTER TABLE `staff_remark`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `template`
--
ALTER TABLE `template`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `venue`
--
ALTER TABLE `venue`
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `closing_days`
--
ALTER TABLE `closing_days`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `guest`
--
ALTER TABLE `guest`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `guestlist`
--
ALTER TABLE `guestlist`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `member_comment`
--
ALTER TABLE `member_comment`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `opening_days`
--
ALTER TABLE `opening_days`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `remark_type`
--
ALTER TABLE `remark_type`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `staff_remark`
--
ALTER TABLE `staff_remark`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `template`
--
ALTER TABLE `template`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `venue`
--
ALTER TABLE `venue`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
