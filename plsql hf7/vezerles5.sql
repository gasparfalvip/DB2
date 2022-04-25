declare
a NUMBER;
b NUMBER;
c NUMBER;
Begin
a:=&a;
b:=&b;
c:=&c;

if (a+b>c and a+c>b and b+c>a) then
dbms_output.put_line(a||', '||b||' és '|| c|| ' értékek háromszöget alkotnak');
else
dbms_output.put_line(a||', '||b||' és '|| c|| ' értékek nem alkotnak háromszöget');
end if;
end;