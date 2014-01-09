
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (300, '7', '76133', 'Karlsruhe','Moltkestrasse', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (301, '9', '76133', 'Karlsruhe','Liefernatstrasse', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (302, '8', '76133', 'Karlsruhe','Herstellerstrasse', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (303, '6', '76133', 'Karlsruhe','Kaiserstrasse', NULL);

INSERT INTO lieferant (id, aktualisiert, erzeugt, adresse_id, name) VALUES (100, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 301, 'Lieferant');
INSERT INTO lieferant (id, aktualisiert, erzeugt, adresse_id, name) VALUES (101, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 302, 'Lieferant2');

INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (100, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 302, 'hersteller');
INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (102, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 303, 'hersteller2');

INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('R', 11, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 'Fahrrad', 120, '2011', 15, 100, 100);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('E', 10, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 'Fahrrad', 120, '2011', NULL, 102, 101);