-- Oppgave 1

CREATE TABLE Student(
	bnavn varchar(12) PRIMARY KEY,	
	navn varchar(30) NOT NULL, 	
	fdato date NOT NULL, 
	spkode varchar(10), 
	tlfnr char(8) UNIQUE 
);

-- Oppgave 2

CREATE TABLE Emner(
	emnekode varchar(12) PRIMARY KEY,
	emnenavn varchar(30) NOT NULL,
	spkode varchar(10)
);

CREATE TABLE Studieprogram(
	spkode varchar(10) PRIMARY KEY,
	spnavn varchar(50) UNIQUE NOT NULL,
	institutt varchar(30) NOT NULL
);

-- Test
INSERT INTO Student VALUES ('eirikise', 'Eirik Isene', '07.21.1993', 'I:Pro', '93233652');
INSERT INTO Student VALUES ('hahesla', 'Håkon Hesla', '10.07.1993', 'I:D', '94522311');
INSERT INTO Student VALUES ('simsam', 'Simen Samuelsen', '03.17.1993', 'I:Nor', '');
INSERT INTO Student VALUES ('pergasto', 'Per Gaute Stople', '10.23.1993', 'I:SK', '98234542');
INSERT INTO Student VALUES ('magwa', 'Magnus Wanderås', '08.11.1993', 'M:Leg', '92233254');

INSERT INTO Emner VALUES ('INF1000', 'Introduksjon til Programmering', 'I:Pro');
INSERT INTO Emner VALUES ('INF1010', 'Objektorientert Programmering', 'I:Pro');
INSERT INTO Emner VALUES ('INF1080', 'Log metoder for informatikk', 'I:Pro');
INSERT INTO Emner VALUES ('LEG1001', 'Lege stuffz', 'M:Leg');
INSERT INTO Emner VALUES ('LEG1023', 'Tannlege stuffz', 'M:Tann');
INSERT INTO Emner VALUES ('INF1500', 'Design stuffz', 'I:D');

INSERT INTO Studieprogram VALUES ('I:Pro', 'Informatikk: Programmering og Nettverk', 'Institutt for informatikk');
INSERT INTO Studieprogram VALUES ('I:D', 'Informatikk: Design og Interaksjon', 'Institutt for informatikk');
INSERT INTO Studieprogram VALUES ('I:Nor', 'Informatikk: ITteknologi og stuff', 'Institutt for informatikk');
INSERT INTO Studieprogram VALUES ('I:SK', 'Informatikk: Simly Kult', 'Institutt for informatikk');
INSERT INTO Studieprogram VALUES ('M:Leg', 'Medisin: ITLege', 'Medisinske fakultet');
INSERT INTO Studieprogram VALUES ('M:Tann', 'Medisin: Tannlege', 'Medisinske fakultet');

-- Oppgave 3

-- 1.
SELECT * 
FROM Student;

-- 2.
SELECT navn
FROM Student
WHERE navn LIKE 'H%' OR navn LIKE 'S%';

-- 3.
SELECT navn, tlfnr
FROM Student
WHERE extract(month from fdato) = 10;

-- 4.
SELECT navn
FROM Student
WHERE length(bnavn) < 8;

-- 5.
SELECT bnavn
FROM Student
WHERE navn LIKE '% % %';

-- 6.
SELECT bnavn, navn
FROM Student
WHERE spkode NOT LIKE 'I:%';

-- Oppgave 4

-- 1.

SELECT S.navn, P.spnavn
FROM Student as S, Studieprogram as P
WHERE S.tlfnr = '' and S.spkode = P.spkode;

-- 2.
SELECT S.navn
FROM Student as S, Studieprogram as P
WHERE S.spkode = P.spkode and P.spnavn LIKE '%teknologi%';

-- 3.
SELECT P.spnavn
FROM Studieprogram as P
WHERE NOT EXISTS
		(
		SELECT NULL
		FROM Emner as E
		WHERE P.spkode = E.spkode 
		and E.emnekode = 'INF1000'
		);

-- 4.
SELECT E.emnekode, E.emnenavn
FROM Emner as E, Studieprogram as P
WHERE P.spnavn LIKE '%IT%' and P.institutt NOT LIKE 'Institutt for informatikk' and P.spkode = E.spkode;

DROP TABLE Student;
DROP TABLE Emner;
DROP TABLE Studieprogram;