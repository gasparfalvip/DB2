declare
a NUMBER;
b NUMBER;
i NUMBER;
y NUMBER;
Begin
a:=0;
b:=1;
i:=&i;

if(i=1) then
dbms_output.put_line(a);
else if(i=2) then
dbms_output.put_line(a);
dbms_output.put_line(b);
else
dbms_output.put_line(a);
dbms_output.put_line(b);
FOR x IN 2..i
LOOP
    y:=a+b;
    a:=b;
    b:=y;
    dbms_output.put_line(y);
END LOOP;
end if;
end if;
end;