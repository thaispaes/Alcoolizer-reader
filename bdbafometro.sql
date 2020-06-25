drop database if exists BafometroLeitura;
Create Database if not exists BafometroLeitura
Default Character Set utf8
Default Collate utf8_general_ci;

use BafometroLeitura;

CREATE TABLE usuario (
usuarioID int AUTO_INCREMENT NOT NULL,
nickname varchar(20) NOT NULL,
senha varchar(255) NOT NULL,
nomeU varchar(255) NOT NULL,
tipo varchar(1) NOT NULL DEFAULT 'C',
email varchar(255) UNIQUE,
pedido int default '1',
PRIMARY KEY (usuarioID)
)  DEFAULT CHARSET=utf8;

/*admin pra teste*/
insert into usuario (nickname,senha,nomeU,tipo,email,pedido)values
('adminadmin','f6fdffe48c908deb0f4c3bd36c032e72','admin','A','jsdjwk@gmail.com','0');
    
Create table Active(
ID int not null auto_increment PRIMARY KEY,
RawData text(100),
RecordNum int(10),
MainSerial varchar(100),
MainFWV varchar(100),
SampleSerial int(100),
SampleFWV varchar(10),
DateD date,
TimeT text,
EventType varchar(50),
DownloadDate date,
TrueResult float(4,3),
DownloadTime varchar(1000),
NameFile varchar(1000));

Create table Passive(
ID int not null auto_increment,
RawData text(100),
RecordNum int(10),
MainSerial varchar(100),
MainFWV varchar(100),
SampleSerial int(100),
SampleFWV    varchar(10),
DateD  date,
TimeT  text,
EventType    varchar(50),
DownloadDate date,
DownloadTime  text,
ResultQualitative   text,
NameFile varchar(1000),
primary key(id));

/*
select * from Active;
select * from Passive;
SELECT * FROM Passive order by RecordNum  ASC;
SELECT DISTINCT SUBSTR(DateD, 6,2) FROM Passive ORDER BY SUBSTR(DateD, 6,2) ASC;
SELECT DISTINCT SUBSTR(DateD,1,4) FROM Passive ORDER BY SUBSTR(DateD,1,4) ASC;
SELECT DISTINCT SUBSTR(DateD,1,4) FROM Passive ORDER BY SUBSTR(DateD,1,4) ASC;
 SELECT * FROM Active WHERE MONTH(DateD)=01;
SELECT * FROM Passive ORDER BY MainSerial ASC;
SELECT NOW()

*/