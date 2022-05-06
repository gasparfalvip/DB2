--film package
create or replace package Filmekpck as 
procedure InsertFilm(nev in varchar2,kiad in date, ar in number,tk_id in number);
procedure VUpdate(tablanev in varchar2,bfield in varchar2,adat in varchar2, idb in number);
procedure NUpdate(tablanev in varchar2,bfield in varchar2,adat in number, idb in number);
procedure DUpdate(tablanev in varchar2,bfield in varchar2,adat in DATE, idb in number);
procedure deleteonefilm(bid in number);
function NewId(tablazat in VARCHAR2) return number;
function filmlekerdezes(neve in varchar2) return varchar2;
end filmekpck;

Create or replace package body Filmekpck as
procedure InsertFilm(nev in varchar2,kiad in date, ar in number,tk_id in number) as
rosszar exception;
rossztkid exception;
unev varchar(50):=nev;
ukiad date:=kiad;
uar number(4):=ar;
utk_id number(4):=tk_id;
rossztkidv number(4):=0;
Begin
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
when rosszar then dbms_output.put_line('Az ár nem megfelelõ');
when rossztkid then dbms_output.put_line('Nincs ilyen idjû tk');
END;
procedure VUpdate(tablanev in varchar2,bfield in varchar2,adat in varchar2, idb in number) as
ptablanev varchar2(60):=tablanev;
pfield varchar(60):=bfield;
padat varchar(60):=adat;
pid number(4):=idb;
Begin
if(ptablanev='Filmek') then
case pfield
when 'Filmnev' then
update Filmek set filmnev=padat where id=pid;
when 'kiadasdatuma' then
update Filmek set kiadasdatuma=padat where id=pid;
when 'ar' then
update Filmek set ar=padat where id=idb;
when 'tk_id' then
update Filmek set tk_id=padat where id=idb;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
elsif(ptablanev='Kolcsonzok') then
case pfield
when 'Nev' then
update Kolcsonzok set nev=padat where id=pid;
when 'Nyitas' then
update Kolcsonzok set nyitas=padat where id=pid;
when 'Alkalmazottak' then
update Kolcsonzok set alkalmazottak=padat where id=pid;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
end if;
end;
procedure NUpdate(tablanev in varchar2,bfield in varchar2,adat in number, idb in number) as
ptablanev varchar2(60):=tablanev;
pfield varchar(60):=bfield;
padat number(4):=adat;
pid number(4):=idb;
Begin
if(ptablanev='Filmek') then
case pfield
when 'Filmnev' then
update Filmek set filmnev=padat where id=pid;
when 'ar' then
update Filmek set ar=padat where id=idb;
when 'tk_id' then
update Filmek set tk_id=padat where id=idb;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
if(ptablanev='Kolcsonzok') then
case pfield
when 'Nev' then
update Kolcsonzok set nev=padat where id=pid;
when 'Alkalmazottak' then
update Kolcsonzok set alkalmazottak=padat where id=pid;
end case;
else
dbms_output.put_line('Rossz field nevet addot meg');
end if;
end if;
END;
procedure DUpdate(tablanev in varchar2,bfield in varchar2,adat in DATE, idb in number) as
ptablanev varchar2(60):=tablanev;
pfield varchar(60):=bfield;
padat date:=adat;
pid number(4):=idb;
Begin
if(ptablanev='Filmek') then
case pfield
when 'Kiadasdatuma' then
update Filmek set Kiadasdatuma=padat where id=pid;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
if(ptablanev='Kolcsonzok') then
case pfield
when 'nyitas' then
update Kolcsonzok set nyitas=padat where id=pid;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
end if;
end if;
END;
procedure deleteonefilm(bid in number) as
fid number(4):=bid;
begin
delete from filmek where id=fid;
end;
function NewId(tablazat in VARCHAR2) return number as
maxid number:=0;
tablazatnev varchar2(50) := tablazat;
begin
if(tablazatnev='Filmek') then
select max(ID) into maxid from Filmek;
elsif(tablazatnev='Kolcsonzok') then
select max(ID) into maxid from Kolcsonzok;
else
dbms_output.put_line('Rossz tábla nevet addot meg');
end if;
if maxid > 0 then
return maxid + 1;
else
return 1;
end if;
end;
function filmlekerdezes(neve in varchar2) return varchar2 as
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
END;

--kolcsonzo package

create or replace package Kolcsonzokpck as 
procedure InsertKolcsonzo(nev in varchar2,kiad in date, ar in number,tk_id in number);
procedure VUpdate(tablanev in varchar2,bfield in varchar2,adat in varchar2, idb in number);
procedure NUpdate(tablanev in varchar2,bfield in varchar2,adat in number, idb in number);
procedure DUpdate(tablanev in varchar2,bfield in varchar2,adat in DATE, idb in number);
procedure deleteonekolcsonzo(bid in number);
function kolcsonzolekerdezes(neve in varchar2) return varchar2;
end Kolcsonzokpck;

create or replace package body Kolcsonzokpck as
procedure InsertKolcsonzo(id in number,nev in varchar2,nyitas in date,alkalmaz in number) as
uid number:=id;
unev varchar(80):=nev;
unyitas date:=nyitas;
ualkalmaz number:=alkalmaz;
Begin
    INSERT into kolcsonzok VALUES(uid,unev,unyitas,ualkalmaz);
end;
procedure VUpdate(tablanev in varchar2,bfield in varchar2,adat in varchar2, idb in number) as
ptablanev varchar2(60):=tablanev;
pfield varchar(60):=bfield;
padat varchar(60):=adat;
pid number(4):=idb;
Begin
if(ptablanev='Filmek') then
case pfield
when 'Filmnev' then
update Filmek set filmnev=padat where id=pid;
when 'kiadasdatuma' then
update Filmek set kiadasdatuma=padat where id=pid;
when 'ar' then
update Filmek set ar=padat where id=idb;
when 'tk_id' then
update Filmek set tk_id=padat where id=idb;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
elsif(ptablanev='Kolcsonzok') then
case pfield
when 'Nev' then
update Kolcsonzok set nev=padat where id=pid;
when 'Nyitas' then
update Kolcsonzok set nyitas=padat where id=pid;
when 'Alkalmazottak' then
update Kolcsonzok set alkalmazottak=padat where id=pid;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
end if;
end;
procedure NUpdate(tablanev in varchar2,bfield in varchar2,adat in number, idb in number) as
ptablanev varchar2(60):=tablanev;
pfield varchar(60):=bfield;
padat number(4):=adat;
pid number(4):=idb;
Begin
if(ptablanev='Filmek') then
case pfield
when 'Filmnev' then
update Filmek set filmnev=padat where id=pid;
when 'ar' then
update Filmek set ar=padat where id=idb;
when 'tk_id' then
update Filmek set tk_id=padat where id=idb;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
if(ptablanev='Kolcsonzok') then
case pfield
when 'Nev' then
update Kolcsonzok set nev=padat where id=pid;
when 'Alkalmazottak' then
update Kolcsonzok set alkalmazottak=padat where id=pid;
end case;
else
dbms_output.put_line('Rossz field nevet addot meg');
end if;
end if;
END;
procedure DUpdate(tablanev in varchar2,bfield in varchar2,adat in DATE, idb in number) as
ptablanev varchar2(60):=tablanev;
pfield varchar(60):=bfield;
padat date:=adat;
pid number(4):=idb;
Begin
if(ptablanev='Filmek') then
case pfield
when 'Kiadasdatuma' then
update Filmek set Kiadasdatuma=padat where id=pid;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
if(ptablanev='Kolcsonzok') then
case pfield
when 'nyitas' then
update Kolcsonzok set nyitas=padat where id=pid;
else
dbms_output.put_line('Rossz field nevet addot meg');
end case;
end if;
end if;
END;
procedure deleteonekolcsonzo(bid in number) as
fid number(4):=bid;
begin
delete from kolcsonzok where id=fid;
end;
function kolcsonzolekerdezes(neve in varchar2) return varchar2 as
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
END;
