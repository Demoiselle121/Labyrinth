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


# Credits

* Application icon made by [Freepik](http://www.freepik.com) from [www.flaticon.com](http://www.flaticon.com) is licensed by [CC 3.0 BY](href="http://creativecommons.org/licenses/by/3.0/)
* A lot of the treasure-icons taken from [numix-Project](https://numixproject.org/)
* Gameidea orginally from [Ravensburger](https://www.ravensburger.de/produkte/spiele/familienspiele/das-verrueckte-labyrinth-26446/index.html)
* Music: "Cold Sober" Kevin MacLeod (incompetech.com) Licensed under [Creative Commons: By Attribution 3.0 License ](http://creativecommons.org/licenses/by/3.0/)