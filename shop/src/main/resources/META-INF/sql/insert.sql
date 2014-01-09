
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
INSERT INTO lieferant (id, aktualisiert, erzeugt, name, adresse_id) VALUES (401,'02.08.2006 00:00:00','02.08.2006 00:00:00',"DHL",301);
INSERT INTO lieferant (id, aktualisiert, erzeugt, name, adresse_id) VALUES (402,'02.08.2006 00:00:00','02.08.2006 00:00:00',"Post",301);

--
-- Hersteller
--
INSERT INTO hersteller (id, aktualisiert, erzeugt, name, adresse_id) VALUES (501,'02.08.2006 00:00:00','02.08.2006 00:00:00',"Reno",302);
INSERT INTO hersteller (id, aktualisiert, erzeugt, name, adresse_id) VALUES (502,'02.08.2006 00:00:00','02.08.2006 00:00:00',"Mountainbick",302);

--
-- Artikel
--
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id, rad_fk) VALUES ("R",101,'02.08.2006 00:00:00','02.08.2006 00:00:00',"MountainbickeXXL",1599,"2013",28,501,401,NULL);
INSERT INTO artikel (dtype, id, aktualisiert, erzeugt, name, preis, baujahr, zoll, hersteller_id, lieferant_id, rad_fk) VALUES ("E",102,'02.08.2006 00:00:00','02.08.2006 00:00:00',"Pedale",99,"2013",NULL,502,402,NULL);