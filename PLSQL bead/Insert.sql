--insert filmek
CREATE SEQUENCE Filmid_seq
  MINVALUE 1
  START WITH 9 /*már 9 adat van a táblámban*/
  INCREMENT BY 1
  CACHE 30;

create or REPLACE trigger FilmID
    BEFORE INSERT ON Filmek
    FOR EACH ROW
BEGIN
    SELECT Filmid_seq.nextval
    INTO:new.ID
    FROM dual;
END;


create or replace procedure InsertFilm(nev in varchar2,kiad in date, ar in number,tk_id in number) as
unev varchar(50):=nev;
ukiad date:=kiad;
uar number(4):=ar;
utk_id number(4):=tk_id;
rosszar exception;
rossztkid exception;
rossztkidv number(4):=0;
Begin
dbms_output.put_line('Az ár nem megfelelõ');
Select count(*) into rossztkidv from Kolcsonzok where ID=utk_id;
if uar not between 400 and 3000 then
raise rosszar;
else
if rossztkidv=1 then
insert into Filmek(filmnev, kiadasdatuma,ar,tk_id) VALUES(unev,ukiad,uar,utk_id);
else
raise rossztkid;
end if;
end if;
EXCEPTION
when rosszar then
dbms_output.put_line('Az ár nem megfelelõ');
when rossztkid then
dbms_output.put_line('Nincs ilyen idjû tk');
END;

dbms_output.enable;
Begin
InsertFilm('Big iram',TO_DATE('2022-2-21','YYYY-MM-DD'),500,3);
commit;
End;


--insert kolcsonzok

create or replace function NewId(tablazat in VARCHAR2) return number as
maxid number:=0;
tablazatnev varchar2(50) := tablazat;
begin
if(tablazatnev='Filmek') then
select max(id) into maxid from Filmek;
elsif(tablazatnev='Kolcsonzok') then
select max(id) into maxid from Kolcsonzok;
else
dbms_output.put_line('Rossz tábla nevet addot meg');
end if;
if maxid > 0 then
return maxid+ 1;
else
return 1;
end if;
end;

--create or replace trigger alaklamzottszam

create or replace procedure InsertKolcsonzo(id in number,nev in varchar2,nyitas in date,alkalmaz in number) as
uid number:=id;
unev varchar(80):=nev;
unyitas date:=nyitas;
ualkalmaz number:=alkalmaz;
Begin
    INSERT into kolcsonzok VALUES(uid,unev,unyitas,ualkalmaz);
end;

Begin
InsertKolcsonzo(3,'Kemény tk',TO_DATE('2022-2-21','YYYY-MM-DD'),3);
commit;
End;

