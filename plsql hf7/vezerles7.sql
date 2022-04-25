declare
a NUMBER;
b NUMBER;

Begin
a:=1;
b:=10;

FOR i IN a..b
LOOP
    dbms_output.put_line(i);
END LOOP;

end;