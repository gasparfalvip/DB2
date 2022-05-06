--delete one by id
create or replace procedure deleteonefilm(bid in number) as
fid number(4):=bid;
begin
delete from filmek where id=fid;
end;

create or replace procedure deleteonekolcsonzo(bid in number) as
fid number(4):=bid;
begin
delete from kolcsonzok where id=fid;
end;
--proba
begin
deleteonefilm();
end;

--delete all between two id
create or replace procedure deletefilmek(kid in number, nid in number) as
cursor sorok is select * from filmek where id between kid and nid;
x varchar2(3):= '#';
datas varchar2(80);
begin
for a in sorok loop
datas:=a.id||x||a.filmnev||x||a.kiadasdatuma||x||a.ar||x||a.tk_id;
delete from filmek where id=a.id;
dbms_output.put_line('Torolt sor:' ||datas);
end loop;
end;

create or replace procedure deletekolcsonzok(kid in number, nid in number) as
cursor sorok is select * from kolcsonzok where id between kid and nid;
x varchar2(3):= '#';
datas varchar2(80);
begin
for a in sorok loop
datas:=a.id||x||a.nev||x||a.nyitas||x||a.alkalmazottak;
delete from kolcsonzok where id=a.id;
dbms_output.put_line('Torolt sor:' ||datas);
end loop;
end;

--proba
Begin
INSERT into Filmek VALUES(50,'Halalos iramban: A sokadik hajsza',TO_DATE('2041-6-25','YYYY-MM-DD'),990,1);
INSERT into Filmek VALUES(51,'Halalos iramban: nagyon halálos iram',TO_DATE('2042-6-25','YYYY-MM-DD'),990,1);
INSERT into Filmek VALUES(52,'Halálos iramban: Iramban haldokolva',TO_DATE('2043-4-20','YYYY-MM-DD'),1100,1);
INSERT into Filmek VALUES(53,'Halalos iramban: Szapora iram - a mosdóra ',TO_DATE('2043-4-8','YYYY-MM-DD'),1300,1);
end;

Begin
deletefilmek(30,60);
end;