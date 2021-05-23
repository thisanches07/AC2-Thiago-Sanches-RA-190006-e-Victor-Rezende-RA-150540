INSERT INTO tb_event (name,description,start_date,end_date,start_time,end_time,email_contact,amount_free_tickets,amount_payed_tickets,price_ticket) VALUES ('Abril Fest','descricao do Abril Fest','2021-04-10','2021-11-11','00:00','22:22','email',20,200,2);
INSERT INTO tb_event (name,description,start_date,end_date,start_time,end_time,email_contact,amount_free_tickets,amount_payed_tickets,price_ticket) VALUES ('October Fest','descricao do October Fest','2021-10-11','2021-10-15','00:00','22:22','email',40,400,1.5);
INSERT INTO tb_event (name,description,start_date,end_date,start_time,end_time,email_contact,amount_free_tickets,amount_payed_tickets,price_ticket) VALUES ('Festa da Facens','descricao do Abril Fest','2021-01-11','2021-01-12','00:00','22:22','email',100,200,1);

INSERT INTO tb_place (name,address) VALUES ('Estadio Municipal','Rua Estadio 123');
INSERT INTO tb_place (name,address) VALUES ('Salao de Eventos','Rua salao de Eventos 123');
INSERT INTO tb_place (name,address) VALUES ('Chacara de Eventos','Rua Chacara de Eventos 123');



INSERT INTO tb_baseuser(name,email) VALUES ('Thiago','thi.sanches@hotmail.com');
INSERT INTO tb_baseuser(name,email) VALUES ('Vitor','vitor@gmail.com');
INSERT INTO tb_baseuser(name,email) VALUES ('Glauco','glauco@hotmail.com');
INSERT INTO tb_baseuser(name,email) VALUES ('Joao','joao@hotmail.com');
INSERT INTO tb_baseuser(name,email) VALUES ('Ana','ana@gmail.com');
INSERT INTO tb_baseuser(name,email) VALUES ('Maria','maria@hotmail.com');

INSERT INTO tb_admin (phone_Number,baseuser_id) VALUES ('15997447070',1);
INSERT INTO tb_admin (phone_Number,baseuser_id) VALUES ('15997447171',2);
INSERT INTO tb_admin (phone_Number,baseuser_id) VALUES ('15997447272',3);

INSERT INTO tb_attend (balance,baseuser_id) VALUES ('150',4);
INSERT INTO tb_attend (balance,baseuser_id) VALUES ('50.50',5);
INSERT INTO tb_attend (balance,baseuser_id) VALUES ('100',6);

