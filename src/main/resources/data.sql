INSERT INTO brands (id) VALUES (1);

INSERT INTO products (id) VALUES (35455);

INSERT INTO prices (brand_id, product_id, start_date, end_date, price_list, priority, price, currency, last_update, last_updated_by)
VALUES
(1, 35455, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 0, 35.50, 'EUR', '2020-03-26 14:49:07', 'user1'),
(1, 35455, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 1, 25.45, 'EUR', '2020-05-26 15:38:22', 'user1'),
(1, 35455, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 1, 30.50, 'EUR', '2020-05-26 15:39:22', 'user2'),
(1, 35455, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 1, 38.95, 'EUR', '2020-06-02 10:14:00', 'user1');