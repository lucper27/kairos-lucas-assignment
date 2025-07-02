
INSERT INTO brands (id) VALUES (1);

INSERT INTO products (id) VALUES (35455);

INSERT INTO prices (brand_id, product_id, start_date, end_date, price_list, priority, price, currency, last_update, last_updated_by)
VALUES

(1, 35455, '2020-06-14T00:00:00Z', '2020-12-31T23:59:59Z', 1, 0, 35.50, 'EUR', '2020-03-26T14:49:07Z', 'user1'),
(1, 35455, '2020-06-14T15:00:00Z', '2020-06-14T18:30:00Z', 2, 1, 25.45, 'EUR', '2020-05-26T15:38:22Z', 'user1'),
(1, 35455, '2020-06-15T00:00:00Z', '2020-06-15T11:00:00Z', 3, 1, 30.50, 'EUR', '2020-05-26T15:39:22Z', 'user2'),
(1, 35455, '2020-06-15T16:00:00Z', '2020-12-31T23:59:59Z', 4, 1, 38.95, 'EUR', '2020-06-02T10:14:00Z', 'user1');