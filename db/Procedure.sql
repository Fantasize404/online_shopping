CREATE PROCEDURE sp_add_member(
    IN p_member_id VARCHAR(50),
    IN p_account VARCHAR(50),
    IN p_password VARCHAR(255),
    IN p_role VARCHAR(20),
    IN p_member_name VARCHAR(50)
)
BEGIN
    INSERT INTO Member (MemberID, Account, Password, Role, MemberName)
    VALUES (p_member_id, p_account, p_password, p_role, p_member_name);
END;

CREATE PROCEDURE sp_add_product(
    IN p_product_id VARCHAR(20),
    IN p_product_name VARCHAR(100),
    IN p_price INT,
    IN p_quantity INT
)
BEGIN
    INSERT INTO Product (ProductID, ProductName, Price, Quantity)
    VALUES (p_product_id, p_product_name, p_price, p_quantity);
END;

CREATE PROCEDURE sp_create_order_full(
    IN p_order_id VARCHAR(50),
    IN p_member_id VARCHAR(50),
    IN p_total_price INT,
    IN p_pay_status INT,
    IN p_items_json JSON
)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE json_len INT;
    DECLARE v_product_id VARCHAR(20);
    DECLARE v_quantity INT;
    DECLARE v_stand_price INT;
    DECLARE v_item_price INT;
    DECLARE v_current_stock INT;


    INSERT INTO `Order` (OrderID, MemberID, Price, PayStatus)
    VALUES (p_order_id, p_member_id, p_total_price, p_pay_status);
    SET json_len = JSON_LENGTH(p_items_json);

    WHILE i < json_len DO
        SET v_product_id = JSON_UNQUOTE(JSON_EXTRACT(p_items_json, CONCAT('$[', i, '].productId')));
        SET v_quantity = CAST(JSON_EXTRACT(p_items_json, CONCAT('$[', i, '].quantity')) AS UNSIGNED);
        SET v_stand_price = CAST(JSON_EXTRACT(p_items_json, CONCAT('$[', i, '].standPrice')) AS UNSIGNED);
        SET v_item_price = CAST(JSON_EXTRACT(p_items_json, CONCAT('$[', i, '].itemPrice')) AS UNSIGNED);

        SELECT Quantity INTO v_current_stock 
        FROM Product 
        WHERE ProductID = v_product_id 
        FOR UPDATE;

        IF v_current_stock < v_quantity THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = '庫存不足，無法建立訂單';
        ELSE
            INSERT INTO OrderDetail (OrderID, ProductID, Quantity, StandPrice, ItemPrice)
            VALUES (p_order_id, v_product_id, v_quantity, v_stand_price, v_item_price);

            UPDATE Product
            SET Quantity = Quantity - v_quantity
            WHERE ProductID = v_product_id;
        END IF;

        SET i = i + 1;
    END WHILE;

END;

CREATE PROCEDURE sp_get_member_orderdetail (IN p_member_id VARCHAR (50))
BEGIN
  SELECT
    ord_detail.OrderID,
    ord_detail.ProductID,
    pro.ProductName,
    ord_detail.Quantity,
    ord_detail.StandPrice,
    ord_detail.ItemPrice
  FROM
    OrderDetail ord_detail
    LEFT JOIN `Order` ord ON ord_detail.OrderID = ord.OrderID
    LEFT JOIN Product pro ON ord_detail.ProductID = pro.ProductID
  WHERE
    ord.MemberID = p_member_id
  ORDER BY
    ord.OrderID DESC;
END