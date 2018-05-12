For å gange et tall X med 10, så kan vi skrive det slik:

	10X = 8X + 2X

Når vi benytter binære tall kan vi gange et tall X med 2
ved å shifte alle bits en plass til venstre:

	2X = X << 1
	8X = (2^3)X = X << 3

Om vi representerer tallet X som et 4-bits-tall skrevet
som ABCD, Hvor A er mest signifikante bitet og D er det
Minst signifikante bitet så kan vi sette det opp slik:
			
		ABCD << 1 = 	  ABCD0
	+	ABCD << 3 = 	ABCD000
	=	ABCD * 10 =     ????CD0

Dermed kan det minst signifikantet bitet til outputen settes til 0,
og 2er-biten til outputen settes til 1er-biten til inputen,
og 4er-biten til outputen settes til 2er-biten til inputen.

Så kan vi legge sammen bit B og D med en half-adder, bit A og C
og resten fra B og D med en full-adder, så B og resten fra A og C
med en half-adder og til slutt A med resten fra B og rest i en siste
half-adder.

Etter at multiplikasjonen har skjedd så kan vi velge om det skal være
positivt eller negativt ved å sende alle bits fra resultatet inn i hver
sin XOR gate med et sign bit, og legge til et bit med en serie half-addere