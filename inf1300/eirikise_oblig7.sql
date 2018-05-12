-- Obligatorsk oppgave 7

-- Oppgave 1.

SELECT 
	title, 
	prodyear
FROM 
	film AS f,
	filmgenre AS fg
WHERE 
	title LIKE 'Rush Hour%'
	AND f.filmid = fg.filmid
	AND fg.genre = 'Action';

-- Oppgave 2.

SELECT
	f.title,
	f.prodyear,
	i.filmtype
FROM
	film AS f,
	filmitem AS i
WHERE
	f.filmid = i.filmid
	AND f.prodyear = 1893;

-- Oppgave 3.

SELECT 
	p.firstname || ' ' || p.lastname AS name
FROM
	person AS p,
	film AS f,
	filmparticipation AS fp
WHERE
	f.title = 'Baile Perfumado'
	AND fp.personid = p.personid
	AND fp.filmid = f.filmid
	AND fp.parttype = 'cast';

-- Oppgave 4.

SELECT
	f.title,
	f.prodyear
FROM
	film AS f,
	person AS p,
	filmparticipation AS fp
WHERE
	f.filmid = fp.filmid
	AND fp.parttype = 'director'
	AND fp.personid = p.personid
	AND p.firstname = 'Ingmar'
	AND p.lastname = 'Bergman'
ORDER BY 
	f.prodyear DESC;

-- Oppgave 5.

SELECT
	MIN(f.prodyear) AS første,
	MAX(f.prodyear) AS siste
FROM
	film AS f,
	person AS p,
	filmparticipation AS fp
WHERE
	f.filmid = fp.filmid
	AND fp.parttype = 'director'
	AND fp.personid = p.personid
	AND p.firstname = 'Ingmar'
	AND p.lastname = 'Bergman';

-- Oppgave 6.

SELECT
	firstprodyear,
	COUNT(*)
FROM
	series
WHERE
	firstprodyear = 2008
	OR firstprodyear = 2009
GROUP BY
	firstprodyear;

-- Oppgave 7.

SELECT
	f.title,
	f.prodyear
FROM
	film AS f,
	filmparticipation AS fp
WHERE
	f.filmid = fp.filmid
GROUP BY
	f.title,
	f.prodyear
HAVING
  	COUNT(DISTINCT fp.personid) > 300;

-- Oppgave 8.

SELECT
	p.firstname || ' ' || p.lastname AS name,
	COUNT(f.filmid) AS antallfilmer,
	MIN(f.prodyear) AS førstefilm,
	MAX(f.prodyear) AS sistefilm
FROM
	person AS p,
	film AS f,
	filmparticipation AS fp
WHERE
	f.filmid = fp.filmid
	AND fp.parttype = 'director'
	AND fp.personid = p.personid
GROUP BY
	name,
	p.personid
HAVING
	MAX(f.prodyear) - MIN(f.prodyear) > 49
ORDER BY
	MAX(f.prodyear) - MIN(f.prodyear) DESC;