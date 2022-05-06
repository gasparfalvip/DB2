--film naplozas
Create table FilmNaplo(
ID NUMBER(4) primary key,
ido DATE,
tipus varchar2(80)
);

CREATE SEQUENCE fnaploid_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 30;

create or REPLACE trigger NaploID
    BEFORE INSERT ON FilmNaplo
    FOR EACH ROW
BEGIN
    SELECT fnaploid_seq.nextval
    INTO:new.ID
    FROM dual;
END;

create or REPLACE trigger Filmnaplotrig
    BEFORE Insert or delete or UPDATE on Filmek
    FOR EACH ROW
BEGIN
   If inserting then
   Insert into Filmnaplo (ido, tipus) values(sysdate,'insert');
   elsif Deleting then
   Insert into Filmnaplo (ido, tipus) values(sysdate,'delete');
   elsif updating then
   Insert into Filmnaplo (ido, tipus) values(sysdate,'update');
   end if;
END;

--kolcsonzo naplozas

Create table KolcsNaplo(
ID NUMBER(4) primary key,
ido DATE,
tipus varchar2(80)
);

CREATE SEQUENCE knaploid_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 30;

create or REPLACE trigger kNaploID
    BEFORE INSERT ON KolcsNaplo
    FOR EACH ROW
BEGIN
    SELECT knaploid_seq.nextval
    INTO:new.ID
    FROM dual;
END;

create or REPLACE trigger Kolcsnaplotrig
    BEFORE Insert or delete or UPDATE on Kolcsonzok
    FOR EACH ROW
BEGIN
   If inserting then
   Insert into KolcsNaplo (ido, tipus) values(sysdate,'insert');
   elsif Deleting then
   Insert into KolcsNaplo (ido, tipus) values(sysdate,'delete');
   elsif updating then
   Insert into KolcsNaplo (ido, tipus) values(sysdate,'update');
   end if;
END;
