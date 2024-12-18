# Über

In einem verwunschenen Labyrinth gehen die Spieler auf die Suche nach geheimnisvollen 
Gegenständen und Lebewesen. Jeder Spieler versucht, durch geschicktes Verschieben der 
Gänge den Weg zu ihnen freizumachen und dorthin zu ziehen.
Wer als Erster alle Geheimnisse lüftet und wieder auf seinem Startfeld ankommt, gewinnt 
dieses spannende Spiel.


# Build & Run

### Software-Voraussetzungen

Um das Projekt zu kompilieren wird folgende Software benötigt:

* Java11 - JDK
    * OpenJDK 11
* Maven 3.8.5

### Building:
```
mvn clean package
```

* `clean`: löscht alte Kompilate
* `package`:  Fügen Sie Packaged Jar zu Ihrem Zielordner hinzu.
### Anwendung starten:
```
mvn [compile] exec:java [-Dexec.args="localhost"]
```

* `compile` Kompiliert die aktuellen Änderungen
* `exec:java` führt das Maven-Plugin zum Starten aus. ACHTUNG: Es wird nicht kompiliert
* `-Dexec.args` gibt die Parameter bei der Ausführung an (String[] args) ==> @ ip

# Releases  & Downloads

Die jar-Dateien der aktuellen und vorherigen Releases findet man unter [Tags](../../target)
==> **fur die ausführbare Datei (Jar):** java -jar Mazenet-Client-0.0.1-SNAPSHOT.jar localhost 
