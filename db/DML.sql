USE onlineshopping;

INSERT INTO Member (MemberID, Account, Password, Role, MemberName) VALUES
('ADMIN_01', 'admin', 'admin', 'ADMIN', 'ADMIN'),
('M001', 'user1', 'user1', 'USER', 'USER_A'),
('M002', 'user2', 'user2', 'USER', 'USER_B');

INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES
('P001', 'osii 舒壓按摩椅', 98000, 5),
('P002', '網友最愛起司蛋糕', 1200, 50),
('P003', '真愛密碼項鍊', 8500, 20);

INSERT INTO `Order` (OrderID, MemberID, Price, PayStatus) VALUES
('Ms20250801186230', '458', 98000, 1),
('Ms20250805157824', '55688', 9700, 0),
('Ms20250805258200', '1713', 2400, 1);

INSERT INTO OrderDetail (OrderID, ProductID, Quantity, StandPrice, ItemPrice) VALUES
('Ms20250801186230', 'P001', 1, 98000, 98000),
('Ms20250805157824', 'P002', 1, 1200, 1200),
('Ms20250805157824', 'P003', 1, 8500, 8500),
('Ms20250805258200', 'P002', 2, 1200, 2400);
