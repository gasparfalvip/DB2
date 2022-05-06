--adott rekor mezõinek lekérdezése
create or replace function filmlekerdezes(neve in varchar2) return varchar2 as
datas varchar2(80);
v_id varchar2(20);
v_nev varchar2(20);
v_kiadas varchar2(20);
v_ar varchar2(20);
v_tk_id varchar2(20);
x varchar2(3):='#';
begin
select id, filmnev, kiadasdatuma, ar, tk_id into v_id,v_nev,v_kiadas,v_ar,v_tk_id from filmek where filmnev like '%'||neve||'%';
datas:=v_id||x||v_nev||x||v_kiadas||x||v_ar||x||v_tk_id;
return datas;
end;

create or replace function kolcsonzolekerdezes(neve in varchar2) return varchar2 as
datas varchar2(80);
v_id varchar2(20);
v_nev varchar2(20);
v_nyitas varchar2(20);
v_alk varchar2(20);
x varchar2(3):='#';
begin
select id, nev, nyitas, alkalmazottak into v_id,v_nev,v_nyitas,v_alk from Kolcsonzok where nev like '%'||neve||'%';
datas:=v_id||x||v_nev||x||v_nyitas||x||v_alk;
return datas;
end;

declare
a varchar2(80):=filmlekerdezes('Halalos iramban 1');
begin
dbms_output.put_line(a);
end;

declare
a varchar2(80):=kolcsonzolekerdezes('Gyorsabb');
begin
dbms_output.put_line(a);
end;

-- agregalt lekerdezes
create or replace function filmekkolcsonzoben(neve in varchar2) return number as
x number;
begin
select count(*) into x from kolcsonzok inner join filmek on kolcsonzok.id=filmek.tk_id where kolcsonzok.nev like '%'||neve||'%';
return x;
end;

create or replace function legdragabbfilmkolcsonzoben(neve in varchar2) return number as
x number;
begin
select max(ar) into x from kolcsonzok inner join filmek on kolcsonzok.id=filmek.tk_id where kolcsonzok.nev like '%'||neve||'%';
return x;
end;

declare
a number(10):=filmekkolcsonzoben('Gyorsabb');
begin
dbms_output.put_line('Szam : '||a);
end;

declare
a number(10):=legdragabbfilmkolcsonzoben('Gyorsabb');
begin
dbms_output.put_line('Legdragabb : '||a);
end;
