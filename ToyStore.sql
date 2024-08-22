--create database ToyStore
use ToyStore

CREATE TABLE Categories (
    categoriesId int PRIMARY KEY IDENTITY(1, 1) NOT NULL,
    categoriesName NVARCHAR(255) NOT NULL,
	describe NVARCHAR(255),
);
GO
INSERT [dbo].[Categories] ([categoriesName], [describe]) VALUES (N'LEGO', N'Mô hình lắp ráp sống động ')
INSERT [dbo].[Categories] ([categoriesName], [describe]) VALUES (N'Đai Lưng', N'Đai lương sống động , âm thanh rõ ràng')
INSERT [dbo].[Categories] ([categoriesName], [describe]) VALUES (N'Mô Hình', N'Mô hình sắc nét, màu sắt rõ ràng, khớp nối linh hoạt')

CREATE TABLE Account (
    accountId int PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	firstName nvarchar(50) NOT NULL,
    lastName nvarchar(50) NOT NULL,
    password varchar(100) NOT NULL, 
    email varchar(100) NOT NULL,
    address nvarchar(255),
    phone varchar(20),
	isAdmin bit
);
Insert into Account(firstName, lastName, password, email, address, phone, isAdmin) values
(N'Mạnh', N'Dũng', '123', 'admin@gmail.com', '123 Main St', '0123456789',1),
(N'Minh', N'Trần', '123', 'cus1@example.com', '456 Oak St', '0987654321', 0),
(N'Cửu', N'Bảo', '123', 'cus2@gmail.com', '1234 Main St', '0123456789', 0),
(N'Đưc', N'Lê', '123', 'cus3@gmail.com', '123 St', '0123456782', 0);


CREATE TABLE Products (
    productId int PRIMARY KEY IDENTITY(1, 1) NOT NULL,
	categoriesId int REFERENCES Categories(categoriesId),
	productName NVARCHAR(255),
	available int NOT NULL,
    listPrice DECIMAL(10, 2) NOT NULL,
	discount int,
	createDate DATETIME,
	modifiedDate DATETIME,
	describe NVARCHAR(255),
	productImg VARCHAR(255),
);

Insert into Products(categoriesId, productName, available, listPrice, discount, describe, productImg) VALUES
(1, N'LEGO Lâu Đài', 22, 699000, 10, N'Mô hình lâu đài sống động và đẹp', N'images/lego1.jpg'),
 (1, N'LEGO Ô Tô', 25, 257400, 12, N'Mô hình ô tô đen', N'images/lego2.jpg'),
(1, N'LEGO Tàu Chiến', 33, 345600, 50, N'Mô hình chiến hạm trắng ', N'images/lego3.jpg'),
 (1, N'LEGO Phong Cảnh', 64, 232000, 38, N'Mô hình phong cảnh hùng vĩ ', N'images/lego4.jpg'),
(1, N'Đai Lưng Decade', 15, 674000, 42, N'Máy biến thân của kamen rider Decade', N'images/dailung1.jpg'),
(2, N'Đai Lưng Katbuto', 37, 826000, 12, N'Máy biến thân của kamen rider Kabuto', N'images/dailung2.jpg'),
(2, N'Đai Lưng Gaim', 66, 354000, 13, N'Máy biến thân của kamen rider Gaim', N'images/dailung3.jpg'),
(2, N'Đai Lưng OOO', 74, 129000, 22, N'Máy biến thân của kamen rider OOO và mô hình kamen rider OOO', N'images/dailung4.jpg'),
(2, N'Mô hình Zhao Yun', 53, 332000, 45, N'Mô hình mô phỏng sự kết hợp của danh tướng Triệu Vân và cơ giáp', N'images/mohinh1.jpg'),
(3, N'Mô hình Bai Qi', 12, 120000, 28, N'Mô hình mô phỏng sự kết hợp của danh tướng Bạch Khởi và cơ giáp', N'images/mohinh2.jpg'),
(3, N'Mô hình Cao Yen', 44, 621000, 23, N'Mô hình mô phỏng sự kết hợp của danh tướng Tào Nhân và cơ giáp', N'images/mohinh3.jpg'),
(3, N'Mô hình Ma Chao', 56, 3890000, 21, N'Mô hình mô phỏng sự kết hợp của danh tướng Mã Siêu và cơ giáp', N'images/mohinh4.jpg'),
(3, N'Mô hình Lu Bu', 72, 520000, 19, N'Mô hình mô phỏng sự kết hợp của danh tướng Lữ Bố và cơ giáp', N'images/mohinh5.jpg')


CREATE TABLE Orders (
    orderId int PRIMARY KEY IDENTITY(1, 1),
    accountId int REFERENCES Account(accountId) NOT NULL,
    orderDate DATETIME NOT NULL,
	recieveDate DATETIME,
    status nvarchar(100)
);

CREATE TABLE OrderItems (
    orderItemId int PRIMARY KEY IDENTITY(1, 1),
    orderId int REFERENCES Orders(orderId),
    productId int REFERENCES Products(productId),
    listPrice DECIMAL(10, 2) NOT NULL,
    discount int,
    quantity int NOT NULL
);



--insert into Orders(customerId , orderDate , status)
--values
--(1,'2023-10-10 20:25:00', N'Chờ xử lý')


--insert into OrderItems(orderId, productId, listPrice, discount, quantity)
--values
--(1, 1, 45490000, 10, 1),
--(1, 2, 45490000, 11, 2),
--(1, 3, 16790000, 12, 3)
