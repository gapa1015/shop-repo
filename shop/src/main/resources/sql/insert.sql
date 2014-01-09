INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (300, '7', '76133', 'Karlsruhe','Moltkestrasse', NULL);

INSERT INTO lieferant (id, aktualisiert, erzeugt, adresse_id, name) VALUES (100, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 300, 'Lieferant');

INSERT INTO hersteller (id, aktualisiert, erzeugt, adresse_id, name) VALUES (100, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 300, 'hersteller');

INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id) VALUES ('R', 11, '06.08.2006 00:00:00', '06.08.2006 00:00:00', 'Fahrrad', 120, '2011', 15, 100, 100);