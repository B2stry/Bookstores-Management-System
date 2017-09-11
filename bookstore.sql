create database bookstore;

use bookstore;

--部门表
create table dept(
did int primary key identity ,
dname varchar(20)unique not null
);

insert into dept values('财务部');
insert into dept values('采购部');
insert into dept values('营销部');
insert into dept values('仓储部');
insert into dept values('战略发展部');


--类别表
create table type(
tid int primary key identity,
tname varchar(20) unique not null
);

insert into type values('科教类读物');
insert into type values('计算机/程序设计');
insert into type values('计算机/操作系统');
insert into type values('英语/词汇');
insert into type values('专业英语');
insert into type values('经济类');
insert into type values('通信类');
insert into type values('数学类');
insert into type values('哲学类');
insert into type values('电子类');

--员工信息表
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

insert into admininfo values(1,'张萌','15033015171','湖南株洲','430525199605086845','是','2016-1-1'); 
insert into admininfo values(1,'杨迪','18875725849','湖南株洲','430651199510185617','是','2016-1-1');
insert into admininfo values(2,'王亚川','18713595236','湖南株洲','452155199505092519','是','2016-1-1');
insert into admininfo values(3,'王子豪','13373077975','湖南株洲','430525199410155415','是','2016-1-1');
insert into admininfo values(4,'马冲','18295603806','湖南株洲','439352199508155913','是','2016-1-1');
insert into admininfo values(5,'闫岩','15833723728','湖南株洲','430525199604022716','是','2016-1-1');


--员工登录表
create table admin(
adid int primary key identity,
aid int foreign key references admininfo(aid),
adname varchar(20) not null unique,
adpwd varchar(20) not null,
adrole varchar(20) not null
);

insert into admin values(1,'hd001','123456','收银员');
insert into admin values(2,'hd002','123456','收银员');
insert into admin values(3,'hd003','123456','采购员');
insert into admin values(4,'hd004','123456','销售员');
insert into admin values(5,'hd005','123456','理货员');
insert into admin values(6,'admin','admin','管理员');

--供应商信息表
create table supplier(
sid int primary key identity,
sname varchar(10) not null,
slink varchar(10) not null,
saddress varchar(50) not null,
sphone varchar(11) not null
);

insert into supplier values('新华书店','刘维','湖南省株洲市','15793254952');
insert into supplier values('龙门图书','朱小荣','湖南省株洲市','18652415621');
insert into supplier values('友谊书店','李春光','湖南省株洲市','13455565842');
insert into supplier values('小不点书店','马华敏','湖南省株洲市','15156875221');


--会员信息表
create table client(
cid int primary key identity,
cname varchar(10) not null,
csex varchar(2) ,
cphone varchar(11) unique not null,
cnum int not null
);

insert into client values('张鹏','男','15073315893',0);
insert into client values('曹恒磊','男','15573313526',0);

--书详细信息表
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

insert into booksinfo values('数据结构与算法分析','马克·艾伦·维斯','7-111-14404-X','机械工业出版社',2,40.00,20,2);
insert into booksinfo values('C程序设计','谭浩强','978-7-302-22446-4','清华大学出版社',2,33.00,30,2);
insert into booksinfo values('数据库技术与应用','王小玲 安剑奇','978-7-5170-1892-6','中国水利水电出版社',2,35.00,30,2)
insert into booksinfo values('疯狂java','李刚','978-7-115-22168-1','人民邮电出版社',2,59.00,50,2);
insert into booksinfo values('C++程序设计教程','传智播客','978-7-115-39484-2','人民邮电出版社',2,45.00,50,2);
insert into booksinfo values('微型计算机技术及应用','戴梅萼','978-7-302-16577-4','清华大学出版社',2,39.00,30,1);
insert into booksinfo values('信息论与编码','孙丽华','978-7-121-17992-1','电子工业出版社',8,30.00,30,1);
insert into booksinfo values('信号与系统','刘百芬','978-7-115-28484-6','人民邮电出版社',8,42.00,30,1);
insert into booksinfo values('西方经济学简明原理','叶德磊','978-7-04-042292-4','高等教育出版社',7,30.00,30,1);
insert into booksinfo values('毛泽东思想','本书编写组','978-7-04-043202-2','高等教育出版社',10,25.00,30,1);
insert into booksinfo values('中国近现代史纲要','本书编写组','978-7-04-037747-7','高等教育出版社',10,23.00,30,1);
insert into booksinfo values('马克思主义基本原理','本书编写组','978-7-04-043197-1','高等教育出版社',10,23.00,30,1);
insert into booksinfo values('思想道德修养与法律基础','本书编写组','978-7-04-037746-0','高等教育出版社',10,16.00,30,1);
insert into booksinfo values('线性电子线路','谢军','978-7-04-028316-7','高等教育出版社',11,38.00,30,3);
insert into booksinfo values('高等数学 上','同济大学数学系','978-7-04-020549-7','高等教育出版社',9,35.00,50,3);
insert into booksinfo values('高等数学 下','同济大学数学系','978-7-04-039662-1','高等教育出版社',9,35.00,50,3);
insert into booksinfo values('通信原理','樊昌信','978-7-118-04607-6','国防工业出版社',8,46.00,50,3);
insert into booksinfo values('电磁场与电磁波','谢处方','978-7-04-018258-3','高等教育出版社',8,35.00,50,3);
insert into booksinfo values('通信英语','张莜华','978-7-5635-4074-7','北京邮电大学出版社',5,34.00,50,3);
insert into booksinfo values('数字电子技术基础','唐治德','978-7-03-023262-5','科学出版社',11,38.00,50,4);
insert into booksinfo values('复变函数与积分变换','马柏林','978-7-309-05412-5','复旦大学出版社',9,34.00,50,4);
insert into booksinfo values('概率论与数理统计','谢永钦','978-7-309-11877-3','复旦大学出版社',9,38.00,50,4);
insert into booksinfo values('通信电子电路','于洪珍','978-7-302-29420-7','清华大学出版社',8,38.00,50,4);
insert into booksinfo values('四级单词一笑而过','周思成','978-7-111-39507-2','机械工业出版社',4,38.00,50,4);
insert into booksinfo values('轻松学Java Web开发','张昆','978-7-121-19558-7','电子工业出版社',2,49.00,50,4);
insert into booksinfo values('Linux命令行与shell脚本编程','Richard Blum','978-7-115-19777-1','人民邮电出版社',3,69.00,50,4);
insert into booksinfo values('Java常用算法手册','赵志云','978-7-113-14366-4','中国铁道出版社',2,59.00,50,4);


--入库表
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

--销售表
create table bookout(
oid int primary key identity,
bid  int foreign key references booksinfo(bid),
aid int foreign key references admininfo(aid),
onum int not null,
otime datetime not null,
oprice money not null

);

--会员表
create table vip(
vid int primary key identity,
cid int foreign key references client(cid),
vname varchar(11),
vpwd varchar(20)
);
insert into vip values(1,'15073315893','123456');
insert into vip values(2,'15573313526','123456');

