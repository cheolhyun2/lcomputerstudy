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
  `u_idx` int(10) DEFAULT NULL,
  `b_group` int(11) DEFAULT NULL,
  `b_order` int(11) DEFAULT NULL,
  `b_depth` int(11) DEFAULT NULL,
  PRIMARY KEY (`b_idx`),
  KEY `u_idx` (`u_idx`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- 테이블 데이터 hyun.board:~8 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`b_idx`, `b_title`, `b_content`, `b_assistant`, `b_writer`, `b_date`, `u_idx`, `b_group`, `b_order`, `b_depth`) VALUES
	(76, 'AAA', '원글', '0', '원글', '2022-03-15 10:01:47', 12, 76, 1, 0),
	(77, 'aaa', '답글', '0', '답글', '2022-03-15 10:02:07', NULL, 76, 4, 1),
	(80, 'aaa2', '답글', '0', '답글', '2022-03-15 10:03:14', NULL, 76, 2, 1),
	(81, 'aaa2-1', '답글의답글', '0', '답글의답글', '2022-03-15 10:03:55', NULL, 76, 3, 2),
	(83, 'BBB', '원글', '0', '원글', '2022-03-15 10:07:32', 12, 83, 1, 0),
	(85, 'bbb2', '답글', '0', '답글', '2022-03-15 10:08:03', NULL, 83, 2, 1),
	(86, 'bbb2-1', '답글의답글', '0', '답글의답글', '2022-03-15 10:08:13', NULL, 83, 4, 2),
	(87, 'dfaf', 'fasdfasdfsdafas', '0', 'efadsfsd', '2022-03-24 10:23:44', NULL, 83, 3, 2);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- 테이블 데이터 hyun.comment:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`c_idx`, `c_comment`, `c_date`, `b_idx`, `c_group`, `c_order`, `c_depth`) VALUES
	(122, '1', '2022-03-29 11:11:28', 86, 122, 1, 0),
	(126, '2', '2022-03-29 11:11:39', 86, 122, 2, 1),
	(127, '3', '2022-03-29 11:11:58', 86, 122, 4, 2),
	(132, '4', '2022-03-29 11:17:51', 86, 122, 5, 3),
	(133, '5', '2022-03-29 11:18:24', 86, 122, 6, 4),
	(134, '22', '2022-03-29 11:18:45', 86, 122, 2, 1);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- 테이블 hyun.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `u_idx` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` varchar(255) NOT NULL,
  `u_pw` varchar(255) NOT NULL,
  `u_name` varchar(255) NOT NULL,
  `u_tel` varchar(255) NOT NULL,
  `u_age` int(11) NOT NULL,
  PRIMARY KEY (`u_idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- 테이블 데이터 hyun.user:~17 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_idx`, `u_id`, `u_pw`, `u_name`, `u_tel`, `u_age`) VALUES
	(3, 'b', '222', '이모씨', '010-2222-2222', 37),
	(6, 'zyzy', '252d', '대한민국', '010-4747-3634', 42),
	(7, 'lsdlsd57', 'sss111', '황철현', '010-669-5555', 28),
	(8, 'a1234', 'bfmklwef', '구구구', '010-9999-9999', 99),
	(9, '가나다라', '1234', '가나다', '111-1111-1112', 31),
	(10, '가나다라마', '1234', '나가', '111-1111-1112', 45),
	(11, 'lcomputer1`23', 'dfdf', 'zzzz', '044-235-1234', 54),
	(12, '가가가', '123', '가가각', '123-123-123', 19),
	(13, '나나나', '123', '나나난', '234-4442-1234', 23),
	(14, '다다다', '3214', '다다닫', '234-243-2222', 31),
	(15, '라라라', '9678', '라라랄', '543-235-5462', 41),
	(16, '마마맘', '45123', '마마맘', '8234-8234-8324', 52),
	(17, '바바밥', '4567', '바바밥', '999-4144-9999', 97),
	(18, '사사삿', '8888', '사사삿', '1320-1329-1924', 83),
	(19, '아아앙', '95023I', '아아앙', '5913-4901-4891', 61),
	(20, '자자잦', '9501239', '자자잦', '5913-4901-4891', 59),
	(21, '차차찿', '444244', '차차찿', '9998-8888-7777', 34);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
