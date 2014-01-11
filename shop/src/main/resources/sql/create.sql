-- ===============================================================================
-- Jede SQL-Anweisung muss in genau 1 Zeile
-- Kommentare durch -- am Zeilenanfang
-- ===============================================================================


--
-- hibernate_sequence
--
DROP SEQUENCE hibernate_sequence;
CREATE SEQUENCE hibernate_sequence START WITH 5000;

--
-- Check-Constraints fuer Enums
--
ALTER TABLE kunde ADD CONSTRAINT check_geschlecht CHECK (geschlecht IN ('M', 'W'));
ALTER TABLE kunde ADD CONSTRAINT check_familienstand CHECK (familienstand IN ('L', 'VH', 'G', 'VW'));
ALTER TABLE kunde_hobby ADD CONSTRAINT check_hobby CHECK (hobby IN ('S', 'L', 'R'));
