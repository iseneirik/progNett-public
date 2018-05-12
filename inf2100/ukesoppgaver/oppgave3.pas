program Fib;

const N = 40;

function Fib1(x : Integer): Integer;
var 
	f1 : Integer; 
	f2 : Integer;
	f3 : Integer;
	i : Integer
begin
	f1 := 0;
	f2 := 1;
	i := 0;
	while  i < x do 
	begin
		f3 := f1 + f2;
		f1 := f2;
		f2 := f3;
	end;
	Fib1 := f1;
end; {Fib1}

function Fib2(x : Integer): Integer;
begin
	if x <= 2 then
		Fib2 := 1
	else
		Fib2 := Fib2(x-2) + Fib2(x-1)
end;

begin
	write(Fib1(N), eol)
	write(Fib2(N), eol)
end;
