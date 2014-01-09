
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (300, '7', '76133', 'Karlsruhe','Moltkestrasse', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (301, '9', '76133', 'Karlsruhe','Liefernatstrasse', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (302, '8', '76133', 'Karlsruhe','Herstellerstrasse', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (303, '6', '76133', 'Karlsruhe','Kaiserstrasse', NULL);

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