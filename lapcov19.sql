-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2020 at 03:28 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lapcov19`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `nama` varchar(50) NOT NULL,
  `jk` varchar(12) NOT NULL,
  `tanggallahir` date NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `nik` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `pekerjaan` varchar(50) NOT NULL,
  `foto` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`nama`, `jk`, `tanggallahir`, `alamat`, `nik`, `username`, `password`, `pekerjaan`, `foto`, `role`) VALUES
('admin', 'perampuan', '2020-06-17', 'admin', 12312, 'admin', 'admin', 'admin', '1593133173914_image00000001.PNG', 'pengurus'),
('Ako', 'Perampuan', '2020-05-01', 'del', 121, 'ako', 'ako', 'idk', '1591450065740_image00000001.PNG', 'penduduk'),
('ako1', 'Perampian', '2020-06-18', 'Del', 11418028, 'ako1', 'ako1', 'Mahasiswa', 'default.png', 'penduduk');

-- --------------------------------------------------------

--
-- Table structure for table `aspirasi`
--

CREATE TABLE `aspirasi` (
  `id_aspirasi` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `isi` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cekkesehatan`
--

CREATE TABLE `cekkesehatan` (
  `id_cek` int(11) NOT NULL,
  `daftarpertanyaan_gejala` varchar(500) NOT NULL,
  `daftarpertanyaan_aktivitas` varchar(500) NOT NULL,
  `username` varchar(255) NOT NULL,
  `hasil` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cekkesehatan`
--

INSERT INTO `cekkesehatan` (`id_cek`, `daftarpertanyaan_gejala`, `daftarpertanyaan_aktivitas`, `username`, `hasil`) VALUES
(50, '1', '0', 'Ako Riko Shiro', 1),
(51, '1', '0', 'Ako Riko Shiro', 0),
(52, '1', '0', 'Ako Riko Shiro', 1),
(53, '1', '0', 'Ako Riko Shiro', 3),
(54, '1', '0', 'Ako Riko Shiro', 1),
(55, '0', '1', 'Ako Riko Shiro', 3),
(56, '1', '0', 'Ako Riko Shiro', 3),
(57, '0', '1', 'Ako', 3),
(58, '1', '0', 'Ako', 2),
(59, '0', '1', 'Ako', 1),
(60, '1', '0', 'Ako', 3),
(61, '0', '1', 'ako1', 1),
(62, '1', '0', 'ako1', 1),
(63, '1', '0', 'ako1', 2),
(64, '1', '0', 'ako', 1);

-- --------------------------------------------------------

--
-- Table structure for table `data_penduduk`
--

CREATE TABLE `data_penduduk` (
  `nik` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jk` varchar(5) NOT NULL,
  `tanggallahir` date NOT NULL,
  `alamat` int(11) NOT NULL,
  `foto` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `informasi`
--

CREATE TABLE `informasi` (
  `id_informasi` int(11) NOT NULL,
  `judul` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `isi` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `informasi`
--

INSERT INTO `informasi` (`id_informasi`, `judul`, `tanggal`, `kategori`, `isi`) VALUES
(15, 'Fafaw', '2020-06-16', 'Umum', 'asdsa'),
(16, 'Fafaw', '2020-06-16', 'Umum', 'Permana'),
(17, 'Ka', '2020-06-26', 'Umum', 'asdd');

-- --------------------------------------------------------

--
-- Table structure for table `keluhan`
--

CREATE TABLE `keluhan` (
  `id_keluhan` int(11) NOT NULL,
  `pengirim` varchar(255) NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `tanggal` date NOT NULL,
  `isi` varchar(500) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keluhan`
--

INSERT INTO `keluhan` (`id_keluhan`, `pengirim`, `kategori`, `tanggal`, `isi`, `status`) VALUES
(2, 'p', 'p', '2020-06-10', 'p', 'p'),
(7, 'ako', 's', '2020-06-09', 'wowo', 'q'),
(8, 'ako', 'Umum', '2020-06-16', '1234', 'Proses'),
(10, 'ako', 'Kesehatan', '2020-06-16', 'Tes', 'Proses'),
(11, 'ako', 'Umum', '2020-06-17', '1234', 'Proses'),
(13, 'ako1', 'Umum', '2020-06-25', 'adTeasdaskdoasd\nasdasda', 'Proses');

-- --------------------------------------------------------

--
-- Table structure for table `masyarakat`
--

CREATE TABLE `masyarakat` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pengajuan`
--

CREATE TABLE `pengajuan` (
  `id` int(11) NOT NULL,
  `topik` varchar(255) NOT NULL,
  `pengaju` varchar(255) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pengurusdaerah`
--

CREATE TABLE `pengurusdaerah` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `resiko`
--

CREATE TABLE `resiko` (
  `id_resiko` int(11) NOT NULL,
  `judul` varchar(50) NOT NULL,
  `solusi` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `status_penduduk`
--

CREATE TABLE `status_penduduk` (
  `id_status` int(11) NOT NULL,
  `jumlahpenduduk` int(11) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `aspirasi`
--
ALTER TABLE `aspirasi`
  ADD PRIMARY KEY (`id_aspirasi`);

--
-- Indexes for table `cekkesehatan`
--
ALTER TABLE `cekkesehatan`
  ADD PRIMARY KEY (`id_cek`);

--
-- Indexes for table `data_penduduk`
--
ALTER TABLE `data_penduduk`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `informasi`
--
ALTER TABLE `informasi`
  ADD PRIMARY KEY (`id_informasi`);

--
-- Indexes for table `keluhan`
--
ALTER TABLE `keluhan`
  ADD PRIMARY KEY (`id_keluhan`);

--
-- Indexes for table `masyarakat`
--
ALTER TABLE `masyarakat`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pengajuan`
--
ALTER TABLE `pengajuan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pengurusdaerah`
--
ALTER TABLE `pengurusdaerah`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `resiko`
--
ALTER TABLE `resiko`
  ADD PRIMARY KEY (`id_resiko`);

--
-- Indexes for table `status_penduduk`
--
ALTER TABLE `status_penduduk`
  ADD PRIMARY KEY (`id_status`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cekkesehatan`
--
ALTER TABLE `cekkesehatan`
  MODIFY `id_cek` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `informasi`
--
ALTER TABLE `informasi`
  MODIFY `id_informasi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `keluhan`
--
ALTER TABLE `keluhan`
  MODIFY `id_keluhan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `pengajuan`
--
ALTER TABLE `pengajuan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
