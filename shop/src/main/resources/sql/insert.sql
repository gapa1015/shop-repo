
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

INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('R', 11, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 'Fahrrad', 120, '2011', 15, 100, 100);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('E', 10, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 'Fahrrad', 120, '2011', NULL, 102, 101);