create table tb_cal (
	id int(10) unsigned not null auto_increment,
	op1 int not null,
	op varchar(5) not null,
	op2 int not null,
	result int not null,
	primary key (id)
) ENGINE=InnoDB default charset=utf8;
select * from tb_cal;