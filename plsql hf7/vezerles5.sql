declare
a NUMBER;
b NUMBER;
c NUMBER;
Begin
a:=&a;
b:=&b;
c:=&c;

if (a+b>c and a+c>b and b+c>a) then
dbms_output.put_line(a||', '||b||' �s '|| c|| ' �rt�kek h�romsz�get alkotnak');
else
dbms_output.put_line(a||', '||b||' �s '|| c|| ' �rt�kek nem alkotnak h�romsz�get');
end if;
end;