--Adressse--
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, aktualisiert, erzeugt, kunde_fk) VALUES (300, '7', '76133', 'Karlsruhe','Moltkestrasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, aktualisiert, erzeugt, kunde_fk) VALUES (301, '9', '76133', 'Karlsruhe','Pfisterstrasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, aktualisiert, erzeugt, kunde_fk) VALUES (302, '8', '76133', 'Karlsruhe','Strassestrasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, aktualisiert, erzeugt, kunde_fk) VALUES (303, '6', '76133', 'Karlsruhe','Kaiserstrasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, aktualisiert, erzeugt, kunde_fk) VALUES (304, '30', '76133', 'Karlsruhe','Wegstrasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00', NULL);

--Hersteller--
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (100, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'Schwalbe');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (101, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'Cube');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (102, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'Bianchi');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (103, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'Vermont');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (104, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'Votec');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (105, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'Ortler');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (106, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'Giro');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (107, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'Sigma');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (108, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'Trelock');

--Artikel--
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_fk) VALUES ('R', 11, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Fahrrad', 350, '2013', 28, 100);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_fk) VALUES ('R', 12, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Mountainbike', 1200, '2011', 28, 101);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_fk) VALUES ('R', 13, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'KidsBike', 140, '2010', 15, 108);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_fk) VALUES ('R', 14, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Damenrad', 220, '2011', 24, 102);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_fk) VALUES ('R', 15, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Electrorad', 2520, '2014', 28, 103);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_fk, rad_fk) VALUES ('E', 16, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Spiegel', 29, '2011', NULL, 104, 11);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_fk, rad_fk) VALUES ('E', 17, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Pedale', 50, '2013', NULL, 105, 12);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_fk, rad_fk) VALUES ('E', 18, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Sitz', 120, '2010', NULL, 106, 13);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_fk, rad_fk) VALUES ('E', 19, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Lenker', 60, '2011', NULL, 107, 14);

--Bankdaten--
INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (1, '123456789', '123454345', 'Sparkasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (2, '123456789', '123454345', 'Geldbank', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (3, '123456789', '123454345', 'Volksbank', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (4, '123456789', '123454345', 'Moneybank', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (5, '123456789', '123454345', 'Kreditbank', '09.01.2013 00:00:00', '09.01.2013 00:00:00');

--Kunden--
INSERT INTO kunde (art, id, vorname, nachname, adresse_id, bankdaten_id, geburtstag, telefon, email, geschlecht, password, aktualisiert, erzeugt) VALUES ('P', 900,'Andreas', 'Jankowoi', 300, 2,'21.04.1991', '21943973274', 'jaan@hs-karlsruhe.de', 'M', 'password', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO kunde (art, id, vorname, nachname, adresse_id, bankdaten_id, geburtstag, telefon, email, geschlecht, password, aktualisiert, erzeugt) VALUES ('P', 901,'Patrick', 'Gabor', 301, 3,'21.04.1991', '21943973274', 'gapa@hs-karlsruhe.de', 'M', 'passwort', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO kunde (art, id, vorname, nachname, adresse_id, bankdaten_id, geburtstag, telefon, email, geschlecht, password, aktualisiert, erzeugt) VALUES ('P', 902,'Tilman', 'Holzschuh', 302, 4,'21.04.1991', '21943973274', 'hoti@hs-karlsruhe.de', 'M', 'wortpass', '09.01.2013 00:00:00', '09.01.2013 00:00:00');
INSERT INTO kunde (art, id, vorname, nachname, adresse_id, bankdaten_id, geburtstag, telefon, email, geschlecht, password, aktualisiert, erzeugt) VALUES ('P', 903,'Daniel', 'Hauth', 303, 4,'21.04.1991', '21943973274', 'hada@hs-karlsruhe.de', 'M', 'wordpass', '09.01.2013 00:00:00', '09.01.2013 00:00:00');
INSERT INTO kunde (art, id, vorname, nachname, adresse_id, bankdaten_id, geburtstag, telefon, email, geschlecht, password, aktualisiert, erzeugt) VALUES ('P', 904,'Max', 'Mustermann', 304, 4,'21.04.1991', '21943973274', 'muma@hs-karlsruhe.de', 'M', 'muster', '09.01.2013 00:00:00', '09.01.2013 00:00:00');

--Bestellung--
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (400,900,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (401,901,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (402,902,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (403,903,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (404,904,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');

--Bestellposition--
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (500, 404, 11, 1, '01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (501, 403, 12, 3, '01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (502, 402, 13, 2, '01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (503, 401, 14, 1, '01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (504, 400, 15, 5, '01.08.2006 00:00:00','01.08.2006 00:00:00');
