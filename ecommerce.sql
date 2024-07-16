-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 05, 2023 at 03:00 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `WholesaleManagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `Buyers`
--

CREATE TABLE `Buyers` (
  `id` int(30) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `stockid` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Buyers`
--

INSERT INTO `Buyers` (`id`, `Name`, `Address`, `stockid`) VALUES
(1, 'James', '123, Texas', 2),
(1, 'James', '123, Texas', 2),
(2, 'John', '123, Texas', 3),
(3, 'Thomas', '123, Texas', 4),
(4, 'Silas', '123, Texas', 5),
(5, 'Samson', '123, Texas', 6),
(6, 'Jessy', '123, Texas', 7),
(7, 'Jairus', '123, Texas', 8),
(8, 'George', '123, Texas', 9),
(9, 'Cynthia', '123, Texas', 10),
(10, 'Patrobas', '123, Texas', 11),
(11, 'Gonza', '123, Texas', 12),
(12, 'Dorothy', '123, Texas', 13),
(13, 'Melvin', '123, Texas', 14),
(14, 'Harry', '123, Texas', 15),
(15, 'Mercy', '123, Texas', 16),
(16, 'Kelvin', '123, Texas', 17),
(17, 'Amos', '123, Texas', 18),
(18, 'Hillary', '123, Texas', 19),
(19, 'Hillary', '123, Texas', 12),
(20, 'Hillary', '123, Texas', 16);

-- --------------------------------------------------------

--
-- Table structure for table `currentstock`
--

CREATE TABLE `currentstock` (
  `ProductCode` int(30) NOT NULL,
  `ProductName` varchar(100) NOT NULL,
  `Quantity` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `Name` varchar(255) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `PhoneNumber` varchar(100) NOT NULL,
  `id` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`Name`, `Address`, `PhoneNumber`, `id`) VALUES
('Alice', '105, California', '97000202283', 1),
('Alice', '105, California', '97000202283', 1),
('Pato', '105, California', '97000202283', 1),
('Joash', '105, California', '97000202283', 1),
('Charity', '105, California', '97000202283', 1),
('Mary', '105, California', '97000202283', 1),
('Sheilah', '105, California', '97000202283', 1),
('Mercy', '105, California', '97000202283', 1),
('Stella', '105, California', '97000202283', 1),
('Ann', '105, California', '97000202283', 1),
('Moraa', '105, California', '97000202283', 1),
('Joyce', '105, California', '97000202283', 1),
('Caroline', '105, California', '97000202283', 1),
('Brian', '105, California', '97000202283', 1),
('Alex', '105, California', '97000202283', 1),
('Amstrong', '105, California', '97000202283', 1),
('Stallon', '105, California', '97000202283', 1),
('Julius', '105, California', '97000202283', 1),
('Derrick', '105, California', '97000202283', 1),
('Miano', '105, California', '97000202283', 1),
('Thomas', '105, California', '97000202283', 1),
('Shekila', '105, California', '97000202283', 1),
('Cynthia', '105, California', '97000202283', 1),
('Jose', '105, California', '97000202283', 1),
('Babra', '105, California', '97000202283', 1),
('Carlos', '105, California', '97000202283', 1),
('June', '105, California', '97000202283', 1),
('Maurice', '105, California', '97000202283', 1),
('Alicia', '105, California', '97000202283', 1),
('Petronilla', '105, California', '97000202283', 1),
('Eunice', '105, California', '97000202283', 1);

-- --------------------------------------------------------

--
-- Table structure for table `Defaulter's`
--

CREATE TABLE `Defaulter's` (
  `CustomerID` int(30) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Product Price` varchar(100) NOT NULL,
  `Debt` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Defaulter's`
--

INSERT INTO `Defaulter's` (`CustomerID`, `Name`, `Product Price`, `Debt`) VALUES
(1, 'George Samwel', '1000', '40000'),
(2, 'Seline khasia', '2000', '6000'),
(3, 'Kelvin amson', '3500', '120000');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentID` int(100) NOT NULL,
  `ProductId` varchar(100) NOT NULL,
  `ProductName` varchar(100) NOT NULL,
  `Price` int(100) NOT NULL,
  `Quantity` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentID`, `ProductId`, `ProductName`, `Price`, `Quantity`) VALUES
(1, '123', 'Urine Reagent Strips 10 Parameter', 2000, 3),
(2, '003', 'LASIK surgical machine', 4500, 5),
(4, '007', 'lood gas analyzers', 540, 10),
(19, '040', 'Magnetic resonance imaging (MRI)', 34000, 3),
(65, '034', 'Electrolyte analyzers', 12400, 5);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ID` int(30) NOT NULL,
  `ProductName` varchar(255) NOT NULL,
  `Quantity` varchar(100) NOT NULL,
  `CostPrice` varchar(100) NOT NULL,
  `SellPrice` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ID`, `ProductName`, `Quantity`, `CostPrice`, `SellPrice`) VALUES
(16, 'Consult Liquid Urine Control', '5', '', '23000'),
(87, 'Conical centrifuge tube, 15 ml', '4', '2310', '3800'),
(89, 'Coagulation analyzers', '3', '7800', '12300');

-- --------------------------------------------------------

--
-- Table structure for table `Profit`
--

CREATE TABLE `Profit` (
  `BuyingPrice` varchar(50) NOT NULL,
  `SellingPrice` varchar(50) NOT NULL,
  `ProfitGained` varchar(50) NOT NULL,
  `TotalProfit` varchar(50) NOT NULL,
  `Quantity` varchar(100) NOT NULL,
  `Product` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Profit`
--

INSERT INTO `Profit` (`BuyingPrice`, `SellingPrice`, `ProfitGained`, `TotalProfit`, `Quantity`, `Product`) VALUES
('1200', '1800', '600', '60000', '10', 'Dialysis machine'),
('8000', '12000', '40000', '320000', '8', 'Urine Reagent Strips 10 Parameter'),
('1500', '2000', '500', '3000', '6', 'Infusion pumps');

-- --------------------------------------------------------

--
-- Table structure for table `Purchaseinfo`
--

CREATE TABLE `Purchaseinfo` (
  `PurchaseID` int(30) NOT NULL,
  `ProductCode` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id` int(30) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Quantity` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id`, `Name`, `Quantity`) VALUES
(1, 'Ventilators', '10'),
(1, 'Ventilators', '10'),
(2, 'Cardiopulmonary bypass device', '10'),
(3, 'Dialysis machine', '10'),
(4, 'Infusion pumps', '10'),
(5, 'LASIK surgical machine', '10'),
(6, 'Medical lasers', '10'),
(7, 'Consult 120 Urine Analyzer', '10'),
(8, 'Urine Reagent Strips 10 Parameter', '10'),
(9, 'Consult Liquid Urine Control', '10'),
(10, 'Plastic urine containers, sterile or unsterile', '10'),
(11, 'Conical centrifuge tube, 15 ml', '10'),
(12, 'Microscope slides and 1 coverslip', '10'),
(13, 'Clinical Centrifuge', '10'),
(14, 'Flow cytometers', '10'),
(15, 'Blood gas analyzers', '10'),
(16, 'Electrolyte analyzers', '10'),
(17, 'Differential counters', '10'),
(18, 'Coagulation analyzers', '10'),
(19, 'Slide strainers', '10'),
(20, 'Magnetic resonance imaging (MRI)', '10');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `usertype` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
