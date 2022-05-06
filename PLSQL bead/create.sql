drop table KOLCSONZOK;
drop table filmek;

Create table Kolcsonzok(
ID NUMBER(4) primary key,
Nev VARCHAR2(80),
Nyitas DATE,
Alkalmazottak NUMBER(3)
);

Create table Filmek (
ID NUMBER(4) primary key,
Filmnev VARCHAR2(50),
Kiadasdatuma DATE,
Ar NUMBER(4),
TK_ID Number(4),
FOREIGN KEY (TK_ID) REFERENCES KOLCSONZOK (ID)
);

INSERT into kolcsonzok VALUES();

BEGIN
INSERT into kolcsonzok(ID,NEV,NYITAS,ALKALMAZOTTAK) VALUES(1,'Gyors videoteka',TO_DATE('2001-02-05','YYYY-MM-DD'),3);
INSERT into kolcsonzok VALUES(2,'Gyorsabb videoteka',TO_DATE('2003-4-9','YYYY-MM-DD'),2);
END;

BEGIN
INSERT into Filmek VALUES(1,'Halalos iramban',TO_DATE('2001-6-22','YYYY-MM-DD'),500,1);
INSERT into Filmek VALUES(2,'Halalosabb iramban',TO_DATE('2003-6-6','YYYY-MM-DD'),500,1);
INSERT into Filmek VALUES(3,'Halalos iramban: Tokioi hajsza',TO_DATE('2006-6-4','YYYY-MM-DD'),500,2);
INSERT into Filmek VALUES(4,'Halalos iram',TO_DATE('2009-4-3','YYYY-MM-DD'),500,2);
INSERT into Filmek VALUES(5,'Halalos iramban: Otodik sebesseg',TO_DATE('2011-4-29','YYYY-MM-DD'),700,1);
INSERT into Filmek VALUES(6,'Halalos iramban 6',TO_DATE('2013-5-24','YYYY-MM-DD'),700,2);
INSERT into Filmek VALUES(7,'Halalos iramban 7',TO_DATE('2015-4-3','YYYY-MM-DD'),990,1);
INSERT into Filmek VALUES(8,'Halalos iramban 8',TO_DATE('2017-4-14','YYYY-MM-DD'),990,2);
INSERT into Filmek VALUES(9,'Halalos iramban 9',TO_DATE('2021-6-25','YYYY-MM-DD'),990,1);
END;



