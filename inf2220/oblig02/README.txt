Programmet kjøres slik:

	> java Oblig2 <filnavn> <manpower>

<filnavn>: en txt fil som følger formatet oppgitt i obligen
<manpower>: et heltall opp til Integer.MAX_VALUE, for uendelig manpower benytt '0'

Programmet er skrevet med hensyn til bruk av begrenset manpower, derfor er det skrevet
mer som en simulering enn som et oppsett av oppgaver, dette påvirker også Stor-O for
kjøringen av programmet, da spesiellt i metoden run() i TaskManager.java

Stor-O ved sentrale metoder i programmet:

calcEST(int startTime) i Task.java:
	Dette er implementert som et dybde først søk
	dermed vil denne ha O(V+E) hvor V er antall Tasks
	og E er kanter mellom nodene.

calcLST() i Task.java:
	også dybde først søk, samme som over

findLoop() i Task.java:
	Også dybde først søk, samme som over

run() i TaskManager.java:
	Denne består av flere for-løkker, hvor
	den største av de er:
		for(Task t : tasks)
	som har O(N), hvor N er antall tasks.
	Denne befinner seg inni	en do-while 
	som har O(T) hvor T er den optimale
	tiden det tar å fullføre taskene,
	totalen blir da O(N*T);

