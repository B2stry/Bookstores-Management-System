create database bookstore;

use bookstore;

--���ű�
create table dept(
did int primary key identity ,
dname varchar(20)unique not null
);

insert into dept values('����');
insert into dept values('�ɹ���');
insert into dept values('Ӫ����');
insert into dept values('�ִ���');
insert into dept values('ս�Է�չ��');


--����
create table type(
tid int primary key identity,
tname varchar(20) unique not null
);

insert into type values('�ƽ������');
insert into type values('�����/�������');
insert into type values('�����/����ϵͳ');
insert into type values('Ӣ��/�ʻ�');
insert into type values('רҵӢ��');
insert into type values('������');
insert into type values('ͨ����');
insert into type values('��ѧ��');
insert into type values('��ѧ��');
insert into type values('������');

--Ա����Ϣ��
create table admininfo(
aid int identity primary key,
did int foreign key references dept(did),
aname varchar(10) not null unique,
aphone varchar(11) not null,
adress varchar(50) not null,
asfz varchar(18) not null unique,
atutas varchar(2) not null,
atime datetime not null
);

insert into admininfo values(1,'����','15033015171','��������','430525199605086845','��','2016-1-1'); 
insert into admininfo values(1,'���','18875725849','��������','430651199510185617','��','2016-1-1');
insert into admininfo values(2,'���Ǵ�','18713595236','��������','452155199505092519','��','2016-1-1');
insert into admininfo values(3,'���Ӻ�','13373077975','��������','430525199410155415','��','2016-1-1');
insert into admininfo values(4,'���','18295603806','��������','439352199508155913','��','2016-1-1');
insert into admininfo values(5,'����','15833723728','��������','430525199604022716','��','2016-1-1');


--Ա����¼��
create table admin(
adid int primary key identity,
aid int foreign key references admininfo(aid),
adname varchar(20) not null unique,
adpwd varchar(20) not null,
adrole varchar(20) not null
);

insert into admin values(1,'hd001','123456','����Ա');
insert into admin values(2,'hd002','123456','����Ա');
insert into admin values(3,'hd003','123456','�ɹ�Ա');
insert into admin values(4,'hd004','123456','����Ա');
insert into admin values(5,'hd005','123456','���Ա');
insert into admin values(6,'admin','admin','����Ա');

--��Ӧ����Ϣ��
create table supplier(
sid int primary key identity,
sname varchar(10) not null,
slink varchar(10) not null,
saddress varchar(50) not null,
sphone varchar(11) not null
);

insert into supplier values('�»����','��ά','����ʡ������','15793254952');
insert into supplier values('����ͼ��','��С��','����ʡ������','18652415621');
insert into supplier values('�������','���','����ʡ������','13455565842');
insert into supplier values('С�������','����','����ʡ������','15156875221');


--��Ա��Ϣ��
create table client(
cid int primary key identity,
cname varchar(10) not null,
csex varchar(2) ,
cphone varchar(11) unique not null,
cnum int not null
);

insert into client values('����','��','15073315893',0);
insert into client values('�ܺ���','��','15573313526',0);

--����ϸ��Ϣ��
create table booksinfo(
bid int primary key identity,
bname varchar(50) not null,
bauthor varchar(50) not null,
bisbn varchar(50) not null unique,
bconcern varchar(50) not null,
tid int foreign key references type(tid),
bprice money not null,
bnum int not null,
sid int foreign key references supplier(sid),
);

insert into booksinfo values('���ݽṹ���㷨����','��ˡ����ס�ά˹','7-111-14404-X','��е��ҵ������',2,40.00,20,2);
insert into booksinfo values('C�������','̷��ǿ','978-7-302-22446-4','�廪��ѧ������',2,33.00,30,2);
insert into booksinfo values('���ݿ⼼����Ӧ��','��С�� ������','978-7-5170-1892-6','�й�ˮ��ˮ�������',2,35.00,30,2)
insert into booksinfo values('���java','���','978-7-115-22168-1','�����ʵ������',2,59.00,50,2);
insert into booksinfo values('C++������ƽ̳�','���ǲ���','978-7-115-39484-2','�����ʵ������',2,45.00,50,2);
insert into booksinfo values('΢�ͼ����������Ӧ��','��÷��','978-7-302-16577-4','�廪��ѧ������',2,39.00,30,1);
insert into booksinfo values('��Ϣ�������','������','978-7-121-17992-1','���ӹ�ҵ������',8,30.00,30,1);
insert into booksinfo values('�ź���ϵͳ','���ٷ�','978-7-115-28484-6','�����ʵ������',8,42.00,30,1);
insert into booksinfo values('��������ѧ����ԭ��','Ҷ����','978-7-04-042292-4','�ߵȽ���������',7,30.00,30,1);
insert into booksinfo values('ë��˼��','�����д��','978-7-04-043202-2','�ߵȽ���������',10,25.00,30,1);
insert into booksinfo values('�й����ִ�ʷ��Ҫ','�����д��','978-7-04-037747-7','�ߵȽ���������',10,23.00,30,1);
insert into booksinfo values('���˼�������ԭ��','�����д��','978-7-04-043197-1','�ߵȽ���������',10,23.00,30,1);
insert into booksinfo values('˼����������뷨�ɻ���','�����д��','978-7-04-037746-0','�ߵȽ���������',10,16.00,30,1);
insert into booksinfo values('���Ե�����·','л��','978-7-04-028316-7','�ߵȽ���������',11,38.00,30,3);
insert into booksinfo values('�ߵ���ѧ ��','ͬ�ô�ѧ��ѧϵ','978-7-04-020549-7','�ߵȽ���������',9,35.00,50,3);
insert into booksinfo values('�ߵ���ѧ ��','ͬ�ô�ѧ��ѧϵ','978-7-04-039662-1','�ߵȽ���������',9,35.00,50,3);
insert into booksinfo values('ͨ��ԭ��','������','978-7-118-04607-6','������ҵ������',8,46.00,50,3);
insert into booksinfo values('��ų����Ų�','л����','978-7-04-018258-3','�ߵȽ���������',8,35.00,50,3);
insert into booksinfo values('ͨ��Ӣ��','��ݯ��','978-7-5635-4074-7','�����ʵ��ѧ������',5,34.00,50,3);
insert into booksinfo values('���ֵ��Ӽ�������','���ε�','978-7-03-023262-5','��ѧ������',11,38.00,50,4);
insert into booksinfo values('���亯������ֱ任','�����','978-7-309-05412-5','������ѧ������',9,34.00,50,4);
insert into booksinfo values('������������ͳ��','л����','978-7-309-11877-3','������ѧ������',9,38.00,50,4);
insert into booksinfo values('ͨ�ŵ��ӵ�·','�ں���','978-7-302-29420-7','�廪��ѧ������',8,38.00,50,4);
insert into booksinfo values('�ļ�����һЦ����','��˼��','978-7-111-39507-2','��е��ҵ������',4,38.00,50,4);
insert into booksinfo values('����ѧJava Web����','����','978-7-121-19558-7','���ӹ�ҵ������',2,49.00,50,4);
insert into booksinfo values('Linux��������shell�ű����','Richard Blum','978-7-115-19777-1','�����ʵ������',3,69.00,50,4);
insert into booksinfo values('Java�����㷨�ֲ�','��־��','978-7-113-14366-4','�й�����������',2,59.00,50,4);


--����
create table bookin(
id int primary key identity,
bid int foreign key references booksinfo(bid),
aid int foreign key references admininfo(aid),
iprice money not null,
inum int not null,
itime datetime not null
);

insert into bookin values(1,5,20.00,20,'2016/11/30 22:14:38');
insert into bookin values(2,5,16.00,30,'2016/11/30 22:15:00');
insert into bookin values(3,5,17.00,30,'2016/11/30 22:16:00');
insert into bookin values(4,5,30.0,50,'2017/01/09 22:16:00');
insert into bookin values(5,5,20.00,50,'2017/01/09 22:17:00');
insert into bookin values(6,5,20.00,30,'2017/01/09 22:18:00');
insert into bookin values(7,5,17.00,30,'2017/01/09 22:19:00');
insert into bookin values(8,5,20.00,30,'2017/01/09 22:20:00');
insert into bookin values(9,5,15.00,30,'2017/01/09 22:21:00');
insert into bookin values(10,5,13.00,30,'2017/01/09 22:22:00');
insert into bookin values(11,5,13.00,30,'2017/01/09 22:23:00');
insert into bookin values(12,5,13.00,30,'2017/01/09 22:24:00');
insert into bookin values(13,5,8.00,30,'2017/01/09 22:25:00');
insert into bookin values(14,5,20.00,30,'2017/01/09 22:26:00');
insert into bookin values(15,5,18.00,50,'2017/01/09 22:27:00');
insert into bookin values(16,5,18.00,50,'2017/01/09 22:28:00');
insert into bookin values(17,5,25.00,50,'2017/01/09 22:29:00');
insert into bookin values(18,5,17.00,50,'2017/01/09 22:30:00');
insert into bookin values(19,5,17.00,50,'2017/01/09 22:31:00');
insert into bookin values(20,5,20.00,50,'2017/01/09 22:32:00');
insert into bookin values(21,5,17.00,50,'2017/01/09 22:33:00');
insert into bookin values(22,5,20.00,50,'2017/01/09 22:34:00');
insert into bookin values(23,5,20.00,50,'2017/01/09 22:35:00');
insert into bookin values(24,5,20.00,50,'2017/01/09 22:36:00');
insert into bookin values(25,5,25.00,50,'2017/01/09 22:37:00');
insert into bookin values(26,5,35.00,50,'2017/01/09 22:38:00');
insert into bookin values(27,5,30.00,50,'2017/01/09 22:39:00');

--���۱�
create table bookout(
oid int primary key identity,
bid  int foreign key references booksinfo(bid),
aid int foreign key references admininfo(aid),
onum int not null,
otime datetime not null,
oprice money not null

);

--��Ա��
create table vip(
vid int primary key identity,
cid int foreign key references client(cid),
vname varchar(11),
vpwd varchar(20)
);
insert into vip values(1,'15073315893','123456');
insert into vip values(2,'15573313526','123456');

