INSERT INTO users(id, archive, username, email,number, password, role, cart_id )
                               VALUES (1, false, 'antonka', 'anton@gmail.com', '+79961232970',
                                       'admin', 'ADMIN', null);

ALTER sequence user_seq RESTART WITH 2;

INSERT INTO products(id, amount, price, title)
                                VALUES (1, 3, 80.0, 'Молоко');

ALTER sequence product_seq RESTART WITH 2;