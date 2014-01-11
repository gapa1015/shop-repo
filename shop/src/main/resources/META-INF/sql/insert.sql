-- ===============================================================================
-- Jede SQL-Anweisung muss in genau 1 Zeile
-- Kommentare durch -- am Zeilenanfang
-- ===============================================================================

--
-- kunde
--
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (1,'Admin','Admin','01.01.2001','F',NULL,NULL,1,NULL,NULL,'admin@hs-karlsruhe.de','1','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (101,'Alpha','Adriana','31.01.2001','P','VH','W',1,'0,1','1500,5','101@hs-karlsruhe.de','101','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (102,'Alpha','Alfred','28.02.2002','P','L','M',1,NULL,'500,5','102@hs-karlsruhe.de','102','02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (103,'Alpha','Anton','15.09.2003','F',NULL,NULL,0,'0,1','0,5','103@hs-karlsruhe.de','103','03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (104,'Delta','Dirk','30.04.2004','F',NULL,NULL,1,'0,15','1500,5','104@hs-karlsruhe.de','104','04.08.2006 00:00:00','04.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, art, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (105,'Epsilon','Emil','31.03.2005','P','G','M',0,NULL,'1500,5','105@hs-karlsruhe.de','105','05.08.2006 00:00:00','05.08.2006 00:00:00');

--
-- adresse
--
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (200,'76133','Karlsruhe','Moltkestraﬂe','30',1,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (201,'76133','Karlsruhe','Moltkestraﬂe','31',101,'02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (202,'76133','Karlsruhe','Moltkestraﬂe','32',102,'03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (203,'76133','Karlsruhe','Moltkestraﬂe','33',103,'04.08.2006 00:00:00','04.08.2006 00:00:00');
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (204,'76133','Karlsruhe','Moltkestraﬂe','34',104,'05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO adresse (id, plz, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (205,'76133','Karlsruhe','Moltkestraﬂe','35',105,'06.08.2006 00:00:00','06.08.2006 00:00:00');

--
-- kunde_hobby : S = SPORT, L = LESEN, R = REISEN
--
INSERT INTO kunde_hobby (kunde_fk, hobby) VALUES (101,'S');
INSERT INTO kunde_hobby (kunde_fk, hobby) VALUES (101,'L');
INSERT INTO kunde_hobby (kunde_fk, hobby) VALUES (102,'S');
INSERT INTO kunde_hobby (kunde_fk, hobby) VALUES (102,'R');
INSERT INTO kunde_hobby (kunde_fk, hobby) VALUES (105,'L');
INSERT INTO kunde_hobby (kunde_fk, hobby) VALUES (105,'R');

--
-- wartungsvertrag
--
INSERT INTO wartungsvertrag (nr, datum, inhalt, kunde_fk, idx, erzeugt, aktualisiert) VALUES (1,'31.01.2005','Wartungsvertrag_1_K101',101,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO wartungsvertrag (nr, datum, inhalt, kunde_fk, idx, erzeugt, aktualisiert) VALUES (2,'31.01.2006','Wartungsvertrag_2_K101',101,1,'02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO wartungsvertrag (nr, datum, inhalt, kunde_fk, idx, erzeugt, aktualisiert) VALUES (1,'30.06.2006','Wartungsvertrag_1_K102',102,0,'03.08.2006 00:00:00','03.08.2006 00:00:00');

--
-- artikel
--
INSERT INTO artikel (id, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (300,'Tisch ''Oval''',80,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO artikel (id, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (301,'Stuhl ''Sitz bequem''',10,0,'02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO artikel (id, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (302,'T¸r ''Hoch und breit''',300,0,'03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO artikel (id, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (303,'Fenster ''Glasklar''',150,0,'04.08.2006 00:00:00','04.08.2006 00:00:00');
INSERT INTO artikel (id, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (304,'Spiegel ''Mach mich schˆner''',60,0,'05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO artikel (id, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (305,'Kleiderschrank ''Viel Platz''',500,0,'06.08.2006 00:00:00','06.08.2006 00:00:00');
INSERT INTO artikel (id, bezeichnung, preis, ausgesondert, erzeugt, aktualisiert) VALUES (306,'Bett ''Mit Holzwurm''',600,0,'07.08.2006 00:00:00','07.08.2006 00:00:00');

--
-- bestellung
--
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (400,101,0,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (401,101,1,'02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (402,102,0,'03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (403,102,1,'04.08.2006 00:00:00','04.08.2006 00:00:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (404,104,0,'05.08.2006 00:00:00','05.08.2006 00:00:00');

--
-- bestellposition
--
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (500,400,300,1,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (501,400,301,4,'01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (502,401,302,5,'02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (503,402,303,3,'03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (504,402,304,2,'03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (505,403,305,1,'04.08.2006 00:00:00','04.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (506,404,300,5,'05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (507,404,301,2,'05.08.2006 00:00:00','05.08.2006 00:00:00');
INSERT INTO bestellposition (id, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (508,404,302,8,'05.08.2006 00:00:00','05.08.2006 00:00:00');

--
-- lieferung
--
INSERT INTO lieferung (id, liefernr, transport_art, erzeugt, aktualisiert) VALUES (600,'20051005-001','ST','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO lieferung (id, liefernr, transport_art, erzeugt, aktualisiert) VALUES (601,'20051005-002','SCH','02.08.2006 00:00:00','02.08.2006 00:00:00');
INSERT INTO lieferung (id, liefernr, transport_art, erzeugt, aktualisiert) VALUES (602,'20051005-003','L','03.08.2006 00:00:00','03.08.2006 00:00:00');
INSERT INTO lieferung (id, liefernr, transport_art, erzeugt, aktualisiert) VALUES (603,'20051008-001','W','04.08.2006 00:00:00','04.08.2006 00:00:00');

--
-- bestellung_lieferung
--
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (400,600);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (401,600);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (402,601);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (402,602);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (403,602);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (404,603);
