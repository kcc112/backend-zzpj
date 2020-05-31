
INSERT INTO warehouse (amount) VALUES (10);
INSERT INTO alcohol (name,cost,warehouse_id) VALUES ('Tatra', 2,1);
INSERT INTO user (login,password,type) VALUES ('Szymon', 'kfshdkfsdjgbsjbgjb5r43y52tr673476', 'ADMIN');
INSERT INTO purchase (user_id) VALUES (1);
INSERT INTO purchase_list (alcohol_id,purchase_id,buy_amount) VALUES (1,1,33);