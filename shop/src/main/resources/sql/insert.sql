
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, aktualisiert, erzeugt, kunde_fk) VALUES (300, '7', '76133', 'Karlsruhe','Moltkestrasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, aktualisiert, erzeugt, kunde_fk) VALUES (301, '9', '76133', 'Karlsruhe','Liefernatstrasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, aktualisiert, erzeugt, kunde_fk) VALUES (302, '8', '76133', 'Karlsruhe','Herstellerstrasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, aktualisiert, erzeugt, kunde_fk) VALUES (303, '6', '76133', 'Karlsruhe','Kaiserstrasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00', NULL);

INSERT INTO lieferant (id, aktualisiert, erzeugt, adresse_id, name) VALUES (100, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 301, 'dhl');
INSERT INTO lieferant (id, aktualisiert, erzeugt, adresse_id, name) VALUES (101, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 301, 'luk');
INSERT INTO lieferant (id, aktualisiert, erzeugt, adresse_id, name) VALUES (102, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 301, 'dpd');
INSERT INTO lieferant (id, aktualisiert, erzeugt, adresse_id, name) VALUES (103, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 301, 'hermes');
INSERT INTO lieferant (id, aktualisiert, erzeugt, adresse_id, name) VALUES (104, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 301, 'pin');

INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (100, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'schwalbe');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (101, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'cube');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (102, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'bianchi');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (103, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'vermont');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (104, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'votec');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (105, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'ortler');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (106, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'giro');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (107, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'sigma');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (108, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 302, 'trelock');

INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('R', 11, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Fahrrad', 350, '2013', 28, 100, 100);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('R', 12, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Mountainbike', 1200, '2011', 28, 101, 101);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('R', 13, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'KidsBike', 140, '2010', 15, 108, 104);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('R', 14, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Damenrad', 220, '2011', 24, 102, 102);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('R', 15, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Electrorad', 2520, '2014', 28, 103, 103);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id, rad_fk) VALUES ('E', 16, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Spiegel', 29, '2011', NULL, 104, 102, 11);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id, rad_fk) VALUES ('E', 17, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Pedale', 50, '2013', NULL, 105, 103, 12);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id, rad_fk) VALUES ('E', 18, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Sitz', 120, '2010', NULL, 106, 102, 13);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id, rad_fk) VALUES ('E', 19, '09.01.2013 00:00:00', '09.01.2013 00:00:00', 'Lenker', 60, '2011', NULL, 107, 104, 14);

INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (1, '123456789', '123454345', 'Sparkasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (2, '123456789', '123454345', 'Sparkasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (3, '123456789', '123454345', 'Volksbank', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (4, '123456789', '123454345', 'Volksbank', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO bankdaten (id, kontonummer, blz, bankname, aktualisiert, erzeugt) VALUES (5, '123456789', '123454345', 'Sparkasse', '09.01.2013 00:00:00', '09.01.2013 00:00:00');

INSERT INTO kunde (art, id, vorname, nachname, adresse_id, bankdaten_id, geburtstag, telefon, email, geschlecht, password, aktualisiert, erzeugt) VALUES ('P', 900,'Andreas', 'Jankowoi', 303, 2,'21.04.1991', '21943973274', 'andaas@web.de', 'M', 'akasdjasd', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO kunde (art, id, vorname, nachname, adresse_id, bankdaten_id, geburtstag, telefon, email, geschlecht, password, aktualisiert, erzeugt) VALUES ('P', 901,'Andreas', 'Jankowoi', 300, 3,'21.04.1991', '21943973274', 'andaag@web.de', 'M', 'akasdjasd', '09.01.2013 00:00:00', '09.01.2013 00:00:00'); 
INSERT INTO kunde (art, id, vorname, nachname, adresse_id, bankdaten_id, geburtstag, telefon, email, geschlecht, password, aktualisiert, erzeugt) VALUES ('P', 902,'Andreas', 'Jankowoi', 301, 4,'21.04.1991', '21943973274', 'andaaf@web.de', 'M', 'akasdjasd', '09.01.2013 00:00:00', '09.01.2013 00:00:00');

INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (400,901,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');

INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (500, 400, 11, 1, '01.08.2006 00:00:00','01.08.2006 00:00:00');
