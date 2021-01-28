# Towers of Hanoi

## Was ist Tower of Hanoi
In Tower of Hanoi besteht aus drei gleich großen Stäben A, B und C, auf die mehrere gelochte Scheiben gelegt werden, 
alle verschieden groß. Zu Beginn liegen alle Scheiben auf Stab A, der Größe nach geordnet, mit der größten Scheibe unten
und der kleinsten oben. Ziel des Spiels ist es, den kompletten Scheiben-Stapel von A nach C zu versetzen.

Bei jedem Zug darf die oberste Scheibe eines beliebigen Stabes unter der Voraussetzung, dass sich dort nicht schon eine
kleinere Scheibe befindet, auf einen der beiden anderen Stäbe gelegt werden. Folglich sind zu jedem Zeitpunkt des Spieles
die Scheiben auf jedem Feld der Größe nach geordnet. [Wikipedia](https://de.wikipedia.org/wiki/T%C3%BCrme_von_Hanoi)

## Spieleinstellungen

#### Spielscheiben
Per Slider und den dazu gehörigen Knöpfen kann zwischen 3 und 8 Scheiben eingestellt werden, um somit den
Schwierigkeitsgrad anzupassen.

#### Timed
Wird der Haken bei Timed gesetzt, wird während des Spiels die aktuell gebrauchte Zeit angezeigt, kann zur eigenen
Rekordaufnahme genutzt werden.

#### Hardmode
Während der Hardmode aktiviert ist, kann der Spieler nur `(2^n)-1` Züge, wobei `n` die Anzahl der Scheiben ist machen,
bevor das Spiel automatisch verloren ist.

## Architektur des Codes
Wir haben eine Abwandlung des Model‐View‐ViewModel (MVVC) implementiert, allerdings binden wir auch Daten im Model mit 
an Daten in den Controllern. Wir haben sehr auf Modularität, für Mockup Testing geachtet. Die Scenes wurden mit dem 
ViewBuilder erstellt, der Automatisch eine Controller-Klasse zur verfügen stellt.

Im MenuController werden die Optionen, die der User einstellt ins Modell gespeichert, sodass beim Laden von der 
hanoiGame.fxml Scene die Optionen geladen werden können.
Die Disks werden durch Rectangles dargestellt und in VBoxes hinzugefügt. Diese VBoxes haben OnClick EventListener, sodass
diese wie große Buttons Funktionieren. Nach jedem Disk "sprung" wird überprüft, ob der Spieler gewonnen hat. 

Wenn `Hardmode` aktiv ist, wird davor geprüft, ob der Spieler schon zu viele "moves", also Disksprünge gemacht hat.
Falls der Spieler die Option `Timed` gewählt hat, wird oben in der Menu Bar ein Label sichtbar, welches die vergangene
Zeit in Sekunden anzeigt. Dieses Label wird von einem Externen Thread aktualisiert, da wir so den Thread oft warten lassen 
können und die CPU nicht unnötig belasten. Dies wurde mit einem Task realisiert, dieser übergibt die Update-Operation an 
Platform.runLater. Da Operationen auf die Scene nur vom JavaFX Application Thread ausgeführt werden können und 
Platform.runLater dafür sorbet, dass diese nach einer unbestimmten Zeit von diesem Thread ausgeführt werden.

## Aspekte der Software-Ergonomie
Wir haben uns darauf konzentriert, dass eine geringe Belastung des Kurzzeitgedächtnisses auftritt, indem wir
im Startmenü nur die Scheibenauswahl, zwei Knöpfe und zwei Checkboxen eingebunden haben um damit bei der
maximalkapazität von 7 +- 2 Elementen zu landen.
Im Spiel selbst gibt es nur zwei klassische Knöpfe und drei Klickbare Bereiche um die Scheiben vom einem Turm auf den
anderen zu bewegen.

Wir haben das UI im Allgemeinen sehr schlicht und kompakt gehalten, sodass wir Fitt's Law einhalten und
einen Gorilla Arm vermeiden können.

## Fremdcode
game.GameController.jar:66 -> [StackOverflow](https://stackoverflow.com/questions/17850191/)
``` java
Platform.runLater(new Runnable() {
@Override
public void run() {
timerProperty.setValue(System.currentTimeMillis() - startTime);
}
});
```
Farbschema wurde von [Discord](https://discord.com/branding) inspiriert
