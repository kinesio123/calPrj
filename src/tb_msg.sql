CREATE TABLE tb_msg ( 
   id  int NOT NULL,
   code  int NOT NULL,
   msg  varchar(30) NOT NULL
);

ALTER TABLE tb_msg ADD PRIMARY KEY (id);

INSERT INTO tb_msg (id, code, msg) VALUES (1,  97, 'AddZeroException is occured.');
INSERT INTO tb_msg (id, code, msg) VALUES (2,  98, 'SubZeroException is occured.');
INSERT INTO tb_msg (id, code, msg) VALUES (3,  99, 'MulOneException is occured.');
INSERT INTO tb_msg (id, code, msg) VALUES (4, 100, 'DivOneException is occured.');
INSERT INTO tb_msg (id, code, msg) VALUES (4, 100, '나는 pro');
INSERT INTO tb_msg (id, code, msg) VALUES (4, 100, '나는 pro');
