-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.5-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- hyun 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `hyun` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hyun`;

-- 테이블 hyun.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `b_idx` int(10) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(50) NOT NULL,
  `b_content` varchar(1000) NOT NULL,
  `b_assistant` varchar(50) NOT NULL,
  `b_writer` varchar(50) NOT NULL,
  `b_date` datetime NOT NULL,
  `b_img` varchar(50) DEFAULT NULL,
  `u_idx` int(10) DEFAULT NULL,
  `b_group` int(11) DEFAULT NULL,
  `b_order` int(11) DEFAULT NULL,
  `b_depth` int(11) DEFAULT NULL,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8;

-- 테이블 데이터 hyun.board:~37 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`b_idx`, `b_title`, `b_content`, `b_assistant`, `b_writer`, `b_date`, `b_img`, `u_idx`, `b_group`, `b_order`, `b_depth`) VALUES
	(76, 'AAA', '원글', '0', '원글', '2022-03-15 10:01:47', NULL, 12, 76, 1, 0),
	(77, 'aaa', '답글', '0', '답글', '2022-03-15 10:02:07', NULL, NULL, 76, 4, 1),
	(80, 'aaa2', '답글', '0', '답글', '2022-03-15 10:03:14', NULL, NULL, 76, 2, 1),
	(81, 'aaa2-1', '답글의답글', '0', '답글의답글', '2022-03-15 10:03:55', NULL, NULL, 76, 3, 2),
	(83, 'BBB', '원글', '0', '원글', '2022-03-15 10:07:32', NULL, 12, 83, 1, 0),
	(85, 'bbb2', '답글', '0', '답글', '2022-03-15 10:08:03', NULL, NULL, 83, 2, 1),
	(86, 'bbb2-1', '답글의답글', '0', '답글의답글', '2022-03-15 10:08:13', NULL, NULL, 83, 4, 2),
	(104, 'ddd', 'ddd', '0', 'ddd', '2022-05-19 11:32:46', NULL, 12, 104, 1, 0),
	(105, 'df31', 'f31232', '0', 'f132f13', '2022-05-19 11:38:58', 'img10.gif', 12, 105, 1, 0),
	(106, '케케', '케케', '0', '케케', '2022-05-23 09:16:20', 'img11.gif', 12, 106, 1, 0),
	(107, '2개', '2개', '0', '2개', '2022-05-23 09:56:01', 'dog.jfif', 12, 107, 1, 0),
	(108, '2개', '2개', '0', '2개', '2022-05-23 10:01:18', 'dog1.jfif', 12, 108, 1, 0),
	(109, '222', '222', '0', '222', '2022-05-23 10:01:59', 'img15.gif', 12, 109, 1, 0),
	(110, '222', '222', '0', '222', '2022-05-23 10:06:36', 'img17.gif', 12, 110, 1, 0),
	(135, '타이틀', 'ddd', '0', 'ddd', '2022-05-26 10:43:50', NULL, 12, 135, 1, 0),
	(136, '이미', '이미', '0', '이미', '2022-05-26 10:52:09', NULL, 12, 136, 1, 0),
	(137, 'zsdf', 'zdsf', '0', 'zsdf', '2022-05-26 10:55:13', NULL, 12, 137, 1, 0),
	(138, 'zsdf', 'zsdf', '0', 'zsdf', '2022-05-26 10:57:10', NULL, 12, 138, 1, 0),
	(139, 'zfe', '3fzf', '0', '3', '2022-05-26 10:59:41', NULL, 12, 139, 1, 0),
	(140, 'zdsf', 'zsdf', '0', 'zsdf', '2022-05-26 11:12:18', NULL, 12, 140, 1, 0),
	(141, 'zdsf', 'zsdf', '0', 'zsdf', '2022-05-26 11:16:32', NULL, 12, 141, 1, 0),
	(142, '타이틀', 'df', '0', 'df', '2022-05-26 11:22:24', NULL, 12, 142, 1, 0),
	(143, '타이틀', 'zfds', '0', 'zdsf', '2022-05-26 11:29:58', NULL, 12, 143, 1, 0),
	(144, 'zsdf', 'zdsf', '0', 'zsdf', '2022-05-26 11:33:50', NULL, 12, 144, 1, 0),
	(145, 'zsdf', 'zsdf', '0', 'zsdf', '2022-05-26 11:39:33', NULL, 12, 145, 1, 0);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 hyun.boardfile 구조 내보내기
CREATE TABLE IF NOT EXISTS `boardfile` (
  `bf_idx` int(11) NOT NULL AUTO_INCREMENT,
  `b_idx` int(11) NOT NULL,
  `bf_filename` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`bf_idx`),
  KEY `FK_boardfile_board` (`b_idx`),
  CONSTRAINT `FK_boardfile_board` FOREIGN KEY (`b_idx`) REFERENCES `board` (`b_idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- 테이블 데이터 hyun.boardfile:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `boardfile` DISABLE KEYS */;
INSERT INTO `boardfile` (`bf_idx`, `b_idx`, `bf_filename`) VALUES
	(1, 76, 'a.jpg'),
	(2, 76, 'b.jpg'),
	(3, 76, 'c.jpg');
/*!40000 ALTER TABLE `boardfile` ENABLE KEYS */;

-- 테이블 hyun.comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `comment` (
  `c_idx` int(11) NOT NULL AUTO_INCREMENT,
  `c_comment` varchar(50) NOT NULL,
  `c_date` datetime NOT NULL DEFAULT current_timestamp(),
  `b_idx` int(11) NOT NULL,
  `c_group` int(11) NOT NULL DEFAULT 0,
  `c_order` int(11) NOT NULL DEFAULT 0,
  `c_depth` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`c_idx`),
  KEY `b_idx` (`b_idx`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`b_idx`) REFERENCES `board` (`b_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8;

-- 테이블 데이터 hyun.comment:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`c_idx`, `c_comment`, `c_date`, `b_idx`, `c_group`, `c_order`, `c_depth`) VALUES
	(240, '11', '2022-04-26 11:03:18', 86, 239, 2, 1),
	(241, '111', '2022-04-26 11:03:23', 86, 239, 3, 2),
	(242, '1111', '2022-04-26 11:03:27', 86, 239, 4, 3),
	(243, '2\r\n', '2022-04-26 11:03:29', 86, 243, 1, 0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- 테이블 hyun.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `u_idx` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` varchar(255) NOT NULL,
  `u_pw` varchar(255) NOT NULL,
  `u_name` varchar(255) NOT NULL,
  `u_tel` varchar(255) NOT NULL,
  `u_age` int(11) NOT NULL,
  `u_level` varchar(50) NOT NULL,
  PRIMARY KEY (`u_idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- 테이블 데이터 hyun.user:~17 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_idx`, `u_id`, `u_pw`, `u_name`, `u_tel`, `u_age`, `u_level`) VALUES
	(3, 'b', '222', '이모씨', '010-2222-2222', 37, '3'),
	(6, 'zyzy', '252d', '대한민국', '010-4747-3634', 42, '3'),
	(7, 'lsdlsd57', 'sss111', '황철현', '010-669-5555', 28, '3'),
	(8, 'a1234', 'bfmklwef', '구구구', '010-9999-9999', 99, '3'),
	(9, '가나다라', '1234', '가나다', '111-1111-1112', 31, '3'),
	(10, '가나다라마', '1234', '나가', '111-1111-1112', 45, '3'),
	(11, 'lcomputer1`23', 'dfdf', 'zzzz', '044-235-1234', 54, '3'),
	(12, '가가가', '123', '가가각', '123-123-123', 19, '1'),
	(13, '나나나', '123', '나나난', '234-4442-1234', 23, '3'),
	(14, '다다다', '3214', '다다닫', '234-243-2222', 31, '3'),
	(15, '라라라', '9678', '라라랄', '543-235-5462', 41, '3'),
	(16, '마마맘', '45123', '마마맘', '8234-8234-8324', 52, '3'),
	(17, '바바밥', '4567', '바바밥', '999-4144-9999', 97, '3'),
	(18, '사사삿', '8888', '사사삿', '1320-1329-1924', 83, '3'),
	(19, '아아앙', '95023I', '아아앙', '5913-4901-4891', 61, '3'),
	(20, '자자잦', '9501239', '자자잦', '5913-4901-4891', 59, '3'),
	(21, '차차찿', '444244', '차차찿', '9998-8888-7777', 34, '3');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
