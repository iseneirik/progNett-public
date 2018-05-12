-- Obligatorisk oppgave 9 --

-- Oppgave 1 --

SELECT 
    f.filmid
FROM 
    film AS f, 
    filmitem AS fi
WHERE 
    f.filmid = fi.filmid 
    AND fi.filmtype='C' 
    AND f.title='Love';

-- Oppgave 2 --

SELECT
    filmid,
    title,
    count(personid) - 1
FROM
    film NATURAL JOIN 
    filmparticipation
WHERE
    parttype = 'director' AND
    filmid IN (
        SELECT
            filmid
        FROM
            filmparticipation NATURAL JOIN 
            person
        WHERE
            firstname = 'Ingmar' AND
            lastname = 'Bergman' AND
            parttype = 'director'
            )
GROUP BY
    filmid,
    title;

-- Oppgave 3 --

SELECT
    filmid,
    count(personid),
    prodyear,
    rank
FROM
    film NATURAL JOIN
    filmparticipation NATURAL JOIN
    filmrating
WHERE
    filmid IN (
        SELECT
            filmid
        FROM
            filmparticipation NATURAL JOIN
            person
        WHERE
            firstname = 'Ingmar' AND
            lastname = 'Bergman' AND
            parttype = 'director'
            )
GROUP BY
    filmid,
    prodyear,
    rank
ORDER BY prodyear asc;

-- Oppgave 4 --

(SELECT
    prodyear,
    title
FROM
    film NATURAL JOIN
    filmparticipation NATURAL JOIN
    person
WHERE
    firstname = 'Angelina' AND
    lastname = 'Jolie'
) INTERSECT (
SELECT
    prodyear,
    title
FROM
    film NATURAL JOIN
    filmparticipation NATURAL JOIN
    person
WHERE
    firstname = 'Antonio' AND
    lastname = 'Banderas');

-- Oppgave 5 -- 

SELECT
    title,
    firstname || ' ' || lastname AS name,
    fp.parttype
FROM
    filmitem NATURAL JOIN
    film NATURAL JOIN
    filmparticipation fp NATURAL JOIN
    person 
WHERE
    prodyear = 2003 AND
    filmtype = 'C'
GROUP BY
    title,
    name,
    parttype,
    fp.filmid,
    fp.personid
HAVING
    (SELECT 
        COUNT(distinct fp2.parttype)
    FROM
        filmparticipation fp2
    WHERE
        fp.filmid = fp2.filmid AND
        fp.personid = fp2.personid) > 1);




-- Oppgave 6 --

SELECT
    firstname || ' ' || lastname AS name
FROM
    person
WHERE
    personid NOT IN (
        SELECT
            personid
        FROM
            film NATURAL JOIN
            filmparticipation
        WHERE
            prodyear = 2005
        ) AND
    personid IN (
        SELECT
            personid
        FROM
            film NATURAL JOIN
            filmparticipation
        WHERE
            prodyear BETWEEN 2008 AND 2010
        GROUP BY 
            personid
        HAVING
            count(distinct filmid) > 15
    );

-- Oppgave 7 --

SELECT
    firstname || ' ' || lastname AS name,
    title
FROM
    person NATURAL JOIN
    film NATURAL JOIN
    filmparticipation
WHERE
    parttype = 'director' AND
    filmid IN (
        SELECT
            filmid
        FROM
            film NATURAL JOIN
            filmparticipation
        GROUP BY
            filmid
        HAVING
            count(distinct personid) > 200
        ) AND
    filmid IN (
        SELECT
            filmid
        FROM
            film NATURAL JOIN
            filmparticipation
        WHERE
            parttype = 'director'
        GROUP BY
            filmid
        HAVING
            count(personid) = 1
        );

-- Oppgave 8 --

SELECT
    firstname || ' ' || lastname AS name
FROM
    person NATURAL JOIN
    filmparticipation
WHERE
    parttype = 'director' AND
    filmid IN (
        SELECT
            distinct filmid
        FROM
            film NATURAL JOIN
            filmparticipation
        GROUP BY
            filmid
        HAVING
            count(distinct personid) > 150
        ) AND
    filmid IN (
        SELECT
            distinct filmid
        FROM
            film NATURAL JOIN
            filmparticipation
        WHERE
            parttype = 'director'
        GROUP BY
            filmid
        HAVING
            count(personid) = 1
        ) AND 
    personid IN (
        SELECT
            distinct personid
        FROM
            film NATURAL JOIN
            filmparticipation
        WHERE
            parttype = 'director'
        GROUP BY 
            personid
        HAVING
            MAX(prodyear) - MIN(prodyear) > 49
        )
GROUP BY
    name
ORDER BY name ASC;