declare
a NUMBER;
b NUMBER;
c NUMBER;
Begin
a:=10;
b:=100;
c:=&c;
if (c<b and c>a) then
dbms_output.put_line(c || ' ' || a || ' és ' || b || ' közé esik');
else
dbms_output.put_line(c ||' nem esik ' || a || ' és ' || b || ' közé');
end if;
end;