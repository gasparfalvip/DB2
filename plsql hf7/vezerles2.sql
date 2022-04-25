declare
a NUMBER;
b NUMBER;
Begin
a:=10;
b:=81;

if (a<b) then
dbms_output.put_line(a ||' kisebb mint '||b);
else
dbms_output.put_line(b ||' kisebb mint '||a);
end if;
end;