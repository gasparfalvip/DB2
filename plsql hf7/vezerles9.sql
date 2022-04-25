declare
x NUMBER;
i NUMBER;
p NUMBER;

Begin
x:=2;
i:=&i;
p:=1;

FOR x IN 2..i/2
LOOP
if mod(i,x) = 0 then
    p:=0;
    exit;
end if;
END LOOP;

if (p=1) then
dbms_output.put_line(i||' prímszám;');
else
dbms_output.put_line(i||' nem prímszám;');
end if;

end;