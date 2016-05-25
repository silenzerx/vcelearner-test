-- DMLs DummyDatensätze wiederherstellen

-- Datenbank vcetrainer
use vcetrainer;

-- alle DS in allen Tabellen bis auf themenbereich löschen,
-- AUTO_INCREMENT auf 1 setzen
TRUNCATE lernkarte2themenbereich;
TRUNCATE potentielleantwort;
DELETE FROM lernkarte;
ALTER TABLE lernkarte AUTO_INCREMENT=1;

-- 2 DS für komplette Lernkarten erstellen
INSERT INTO lernkarte VALUES (NULL, "Ja, wo bin ich?", NULL);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 1, 1);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 1, 2);
INSERT INTO potentielleantwort VALUES(NULL, "true", "qwertzu", 1);
INSERT INTO potentielleantwort VALUES(NULL, "false", "Raum 5.2", 1);

INSERT INTO lernkarte VALUES (NULL, "Frage an Alle:wie spät ist es?", NULL);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 2, 1);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 2, 5);
INSERT INTO potentielleantwort VALUES(NULL, "true", "12 Uhr 30", 2);
INSERT INTO potentielleantwort VALUES(NULL, "false", "6:00 Uhr", 2);

INSERT INTO lernkarte VALUES (NULL, "Fraglos gut?", NULL);
INSERT INTO lernkarte2themenbereich VALUES(NULL, 3, 3);
INSERT INTO potentielleantwort VALUES(NULL, "true", "A", 3);
INSERT INTO potentielleantwort VALUES(NULL, "false", "B", 3);
INSERT INTO potentielleantwort VALUES(NULL, "true", "C", 3);
INSERT INTO potentielleantwort VALUES(NULL, "false", "D", 3);
INSERT INTO potentielleantwort VALUES(NULL, "true", "E", 3);
INSERT INTO potentielleantwort VALUES(NULL, "false", "F", 3);
INSERT INTO potentielleantwort VALUES(NULL, "true", "G", 3);
