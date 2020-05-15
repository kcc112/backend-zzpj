INSERT INTO alcohol (name,cost,amount) VALUES ('Tatra', 2, 10);
INSERT INTO user (login,password,type) VALUES ('Szymon', 'kfshdkfsdjgbsjbgjb5r43y52tr673476', 'ADMIN');
INSERT INTO purchase_list (user_id) VALUES (1);
INSERT INTO purchase_alcohol (alcohol_id,purchase_list_id,buy_amount) VALUES (1,1,33);