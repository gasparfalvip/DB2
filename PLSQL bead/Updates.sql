-- updatek beviteli típus szerint varchar2, number és date

create or replace procedure VUpdate(tablanev in varchar2,bfield in varchar2,adat in varchar2, idb in number) as
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
end if;
END;

create or replace procedure NUpdate(tablanev in varchar2,bfield in varchar2,adat in number, idb in number) as
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

create or replace procedure DUpdate(tablanev in varchar2,bfield in varchar2,adat in DATE, idb in number) as
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

-- trigger a film árának kezelésére
create or replace trigger Arcontrol
before update on filmek
for each row
Begin
if(:new.ar<400) then
:new.ar :=400;
elsif(:new.ar>3000) then
:new.ar :=3000;
end if;
end if;
End;
