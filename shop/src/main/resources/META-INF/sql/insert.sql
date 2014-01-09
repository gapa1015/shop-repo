
--
-- adresse
--
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (300, '7', '76133', 'Karlsruhe','Moltkestrasse', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (301, '8', '76133', 'Karlsruhe','Moltkestrasse', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (302, '9', '76133', 'Karlsruhe','Moltkestrasse', NULL);
INSERT INTO adresse (id, hausnummer, plz, stadt, strasse, kunde_fk) VALUES (303, '10', '76133', 'Karlsruhe','Moltkestrasse', NULL);


--
-- Lieferant
--
INSERT INTO lieferant (id, aktualisiert, erzeugt, name, adresse_id) VALUES (400,'02.08.2006 00:00:00','02.08.2006 00:00:00',"DHL",301);

--
-- Lieferant
--
INSERT INTO hersteller (id, aktualisiert, erzeugt, name, adresse_id) VALUES (500,'02.08.2006 00:00:00','02.08.2006 00:00:00',"Reno",302);

--
-- Artikel
--