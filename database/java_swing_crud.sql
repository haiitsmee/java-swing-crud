-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2025 at 08:37 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_jual`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kdBarang` varchar(10) NOT NULL,
  `nmBarang` varchar(50) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `harga` int(10) NOT NULL,
  `stok` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kdBarang`, `nmBarang`, `satuan`, `harga`, `stok`) VALUES
('B0001', 'Barang 1', '1', 10, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `detailpesanan`
--

CREATE TABLE `detailpesanan` (
  `noPesan` varchar(10) DEFAULT NULL,
  `kdBarang` varchar(10) DEFAULT NULL,
  `jumlahPesan` varchar(4) DEFAULT NULL,
  `hargaPesanan` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detailpesanan`
--

INSERT INTO `detailpesanan` (`noPesan`, `kdBarang`, `jumlahPesan`, `hargaPesanan`) VALUES
('PS001', 'B0001', '2', '20000'),
('PS002', 'B0001', '2', '20000'),
('PS002', 'B0001', '2', '20000'),
('PS013', 'B0001', '1', '10000'),
('PS213', 'B0001', '3', '30000'),
('PS014', 'B0001', '2', '20000'),
('PS342', 'B0001', '2', '20000');

-- --------------------------------------------------------

--
-- Table structure for table `nota`
--

CREATE TABLE `nota` (
  `noNota` varchar(10) NOT NULL,
  `tanggal` varchar(10) DEFAULT NULL,
  `noPesan` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nota`
--

INSERT INTO `nota` (`noNota`, `tanggal`, `noPesan`) VALUES
('1', '2025-06-14', 'PS001'),
('12', '2025-06-14', 'PS002'),
('123', '2025-06-14', 'PS013'),
('1234', '2025-06-14', 'PS342'),
('32', '2025-06-14', 'PS213'),
('324', '2025-06-14', 'PS014');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `kdPelanggan` varchar(10) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` text NOT NULL,
  `telepon` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`kdPelanggan`, `nama`, `alamat`, `telepon`) VALUES
('P0001', 'pelanggan 1', 'some addres st. street 1', '+621234567890');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `noPesan` varchar(10) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `kdPelanggan` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`noPesan`, `tanggal`, `kdPelanggan`) VALUES
('PS001', '2025-06-14', 'P0001'),
('PS002', '2025-06-14', 'P0001'),
('PS013', '2025-06-14', 'P0001'),
('PS014', '2025-06-14', 'P0001'),
('PS213', '2025-06-14', 'P0001'),
('PS342', '2025-06-14', 'P0001');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kdBarang`);

--
-- Indexes for table `nota`
--
ALTER TABLE `nota`
  ADD PRIMARY KEY (`noNota`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`noPesan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
