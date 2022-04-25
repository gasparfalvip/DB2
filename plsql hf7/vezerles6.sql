declare
a NUMBER;
b NUMBER;
c NUMBER;
s NUMBER;
t NUMBER;
Begin
a:=&a;
b:=&b;
c:=&c;

if (a+b>c and a+c>b and b+c>a) then
s:=(a+b+c)/2;
t:=SQRT(s*(s-a)*(s-b)*(s-c));
dbms_output.put_line(a||', '||b||' és '|| c|| ' értékek háromszöget alkotnak. A háromszög területe: '||t);
else
dbms_output.put_line(a||', '||b||' és '|| c|| ' értékek nem alkotnak háromszöget');
end if;
end;