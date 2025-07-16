# ProjectCharlie

<img width="801" height="638" alt="grafik" src="https://github.com/user-attachments/assets/ae58576c-9315-40ac-b322-5df16ac22313" />


Dieses Projekt wurde von Constantin, Finn, David und Jakob erstellt, mit dem Ziel sich der Softwareentwicklung und deren Prozesse zu nähern.
Besonders wurde dabei auf die Zusammenarbeit im Team und die Prozesse im Hintergrund geachtet, welche einen reibungslosen Ablauf ermöglichen.
Deshalb handelt es sich bei diesem Spiel um ein sehr einfaches und klassisches Spiel, nämlich Pong.

Pong wurde in den 70er Jahren entwickelt und ist das erste erfolgreiche Videospiel. Es ist ähnlich wie Tischtennis: jeder Spieler hat einen Balken(Schläger), den er hoch und runter bewegen kann. Zudem gibt es auch einen Ball, der sich bewegt und von den Rändern des Spielfelds reflektiert wird. Wenn der Ball an dem Balken eines Spielers vorbeigeht, bekommt der andere einen Punkt. 
Im Frühjahr 1972 präsentierte die Firma Magnafox ihre von Ralph Baer entwickelte Magnafox Odyssey, die erste kommerzielle Spielkonsole. Bei dieser Präsentation spielte Nolan Bushnell zum ersten Mal dieses Ping-Pong Spiel. Als Bushnell wenig später Atari gründete, beauftragte er seinen noch neuen Angestellten Allan Allcon, zu Übungszwecken ein Ping-Pong-Spiel zu erstellen. Wie sich herausstellte, machte das Ping-Pong-Spiel so viel Spaß, dass Bushnell sich entschied, es zu veröffentlichen. Da der Begriff Ping Pong bereits geschützt war, einigte man sich darauf, das Spiel ganz einfach Pong zu nennen.
Als Magnavox von Pong erfuhr, informierten sie Atari darüber, dass für das Spielprinzip bereits Patente bestanden. Vor Gericht konnte Magnavox beweisen, dass Bushnell im Frühjahr 1972 deren Ping-Pong-Spiel gesehen und gespielt hatte. Ein Eintrag Bushnells im Gästebuch von Magnavox untermauerte deren Beweisführung. Atari wurde zur Zahlung von $700.000 zur Nutzung der Patente von Magnavox verurteilt. Für Atari war das jedoch eine gute Investition, da man bis 1983 weit über 8000 Pong-Münzautomaten verkaufte.
Heutzutage ist Pong nicht mehr so beliebt, da es sehr viel kompliziertere und spannendere Videospiele gibt, wobei es dennoch den Grundstein vieler Spiele gelegt hat.
Fun Fact: die ursprüngliche Version ist erst ab 18, da es nie von der USK geprüft wurde. 1999 wurde dann jedoch eine neue Version veröffentlicht, die nicht ab 18 ist.  

> Das 1972 von Atari veröffentlichte Pong wurde zum ersten weltweit beliebten Videospiel und in den 1970er Jahren zunächst auf Geräten in Spielhallen bekannt. Es gilt als Urvater der Videospiele, obgleich schon zuvor Videospiele entwickelt worden waren.
>
> -- <cite>[Wikipedia](https://de.wikipedia.org/wiki/Pong)</cite>

Das Spiel lässt sich zu zweit spielen und hat kein Ende. Die Tasten für Spieler A sind `W` und `S` und für Spieler B die Pfeiltasten.

## Javadoc

Ein Teil der Vorgaben, war die ausseinandersetzung mit Javadoc. Anfänglich begangen wir den fehler, nicht sofort oder sehr sporadisch Javadoc zu schreiben. Das führte dazu, dass
wir am Ende in mehreren Iteration den Code nachkommentieren mussten, was sich als Umständlich erwies.

Der Javadoc lässt sich sehr einfach generieren mit `./gradlew javadoc`. Er ist dann unter `/core/build/docs` einzusehen.

## Gradle

Das Projekt bedient sich libgdx und damit auch [Gradle](https://gradle.org/) um Abhängigkeiten zu managen.
Der Gradle-Wrapper ist mit inkludiert und kann über `./gradlew` verwendet werden.
Die wichtigsten Befehle sind:

- `build`: builds sources and archives of every project.
- `html:dist`: compiles GWT sources. The compiled application can be found at `html/build/dist`: you can use any HTTP server to deploy it.
- `html:superDev`: compiles GWT sources and runs the application in SuperDev mode. It will be available at [localhost:8080/html](http://localhost:8080/html). Use only during development.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Das Projekt, gliedert sich in 3 Unterteile. Den `core` Teil, welcher die gesamte selbstgeschriebene Logik des Programms enthält. Den `lwgjl` Teil, welche das Programm
für Desktop kompiliert und startet. Den `html` Teil, welcher das Programm als Web-Applikation kompiliert.

## Class diagram

Das Spiel wurde mit einer Variation des Model - View - Controller Schema entworfen.
Hierbei agiert das Model als dumme Klasse ohne irgendeine Kenntniss der anderen Klassen. Das Model
fungiert als reines Speichermedium. Währendessen kennen das View und der Controller beide das Model aber nicht den jeweils anderen.
Das View rendert eigenständig den Spielstand, basierend auf den Werten im Model, welche vom Controller manipuliert werden. Es findet sozusagen
eine indirekte Kommunikation statt.

```mermaid
classDiagram
Model <|-- Main
View <|-- Main
Controller <|-- Main
Model <|-- View
Model <|-- Controller
class Model {
    screenWidth: int
    screenHeight: int
    paddleA: Rectangle
    paddleB: Rectangle
    paddleHeight: int
    paddleWidth: int
    ball: Rectangle
    ballVelocity: Vector2
    scoreA: int
    scoreB: int
    paddleSpeed: int
    backgroundColor: float[4]
}
class Controller {
    model: Model
    modelwerteInitialisieren()
    render(float delta)
    resetBall()
    inputHandling()
}
class View {
    model: Model
    render()
    dispose()
}
class Main {
    model: Model
    view: View
    controller: Controller
    create()
    render()
    dispose()
}
```
