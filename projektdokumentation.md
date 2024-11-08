
# Projekt-Dokumentation

Damian Eisenring

| Datum | Version | Zusammenfassung                                              |
| ----- | ------- | ------------------------------------------------------------ |
|       | 0.0.1   | ✍️ Jedes Mal, wenn Sie an dem Projekt arbeiten, fügen Sie hier eine neue Zeile ein und beschreiben in *einem* Satz, was Sie erreicht haben. |
|       | ...     |                                                              |
|       | 1.0.0   |                                                              |

## 1 Informieren

### 1.1 Ihr Projekt

Ich erstelle mit Java ein Bank Management System

### 1.2 User Stories

| US-№ | Verbindlichkeit | Typ  | Beschreibung                       |
| ---- | --------------- | ---- | ---------------------------------- |
|1	|Muss|	Funktional|	Als ein Benutzer möchte ich ein neues Konto erstellen können, damit ich den Service nutzen kann.|
|2	|Muss	|Funktional	|Als ein Benutzer möchte ich Geld auf mein Konto einzahlen und abheben können, damit ich mein Konto aktiv nutzen kann.|
|3	|Muss	|Funktional	|Als ein Benutzer möchte ich mich in mein Konto einloggen können, damit ich sicher auf mein Konto zugreifen kann.|
|4|	Muss	|Funktional	|Als ein Benutzer möchte ich meinen Transaktionsverlauf einsehen können, damit ich meine Kontobewegungen nachvollziehen kann.|
|5|	Muss	|Qualität	|Als ein Benutzer möchte ich eine Fehlermeldung erhalten, wenn mein Kontostand nicht ausreicht oder ungültige Beträge eingegeben werden.|
|6	|Kann	|Funktional|	Als ein Benutzer möchte ich mein Konto löschen oder Kontoinformationen aktualisieren können, damit ich meine Konten flexibel verwalten kann.|
|7|	Muss	|Qualität	|Als ein Benutzer möchte ich sicherstellen, dass nur autorisierte Benutzer ihre eigenen Konten verwalten können, damit meine Daten sicher sind.|
| ...  |                 |      |                                    |

✍️ Jede User Story hat eine ganzzahlige Nummer (1, 2, 3 etc.), eine Verbindlichkeit (Muss oder Kann?), und einen Typ (Funktional, Qualität, Rand). Die User Story selber hat folgende Form: *Als ein 🤷‍♂️ möchte ich 🤷‍♂️, damit 🤷‍♂️*.

### 1.3 Testfälle

| TC-№ | Ausgangslage | Eingabe | Erwartete Ausgabe |
| ---- | ------------ | ------- | ----------------- |
|1.1	|Benutzer ist auf der Kontoerstellungsseite|	Formular mit gültigen Daten ausfüllen|	Neues Benutzerkonto wird erfolgreich erstellt und bestätigt.|
|1.2	|Benutzer ist auf der Kontoerstellungsseite|	Formular mit ungültigen Daten (z. B. fehlende Pflichtfelder)	|Fehlermeldung wird angezeigt, dass Pflichtfelder ausgefüllt werden müssen.|
|2.1	|Benutzer ist im Konto eingeloggt und auf der Transaktionsseite|	Gültigen Betrag zur Einzahlung eingeben	|Betrag wird dem Kontostand hinzugefügt und bestätigt.|
|2.2	|Benutzer ist im Konto eingeloggt und auf der Transaktionsseite|	Betrag zur Abhebung eingeben, der den Kontostand nicht übersteigt	|Betrag wird vom Kontostand abgezogen und bestätigt.|
|2.3	|Benutzer ist im Konto eingeloggt und auf der Transaktionsseite|	Betrag zur Abhebung eingeben, der den Kontostand übersteigt|	Fehlermeldung wird angezeigt, dass das Guthaben nicht ausreicht.|
|3.1	|Benutzer ist auf der Login-Seite	|Gültige Anmeldedaten eingeben|	Benutzer wird erfolgreich eingeloggt und auf das Konto weitergeleitet.|
|3.2	|Benutzer ist auf der Login-Seite|	Ungültige Anmeldedaten eingeben	|Fehlermeldung wird angezeigt, dass die Zugangsdaten ungültig sind.|
|4.1	|Benutzer ist im Konto eingeloggt|	Transaktionsverlauf aufrufen	|Vollständiger Transaktionsverlauf des Kontos wird angezeigt.|
|5.1	|Benutzer ist auf der Transaktionsseite und gibt einen ungültigen Betrag ein|	Ungültiger Betrag (z. B. Text statt Zahl)	Fehlermeldung wird angezeigt, dass der Betrag ungültig ist.|
|6.1	|Benutzer ist im Konto eingeloggt|	Kontoinformationen aufrufen und bearbeiten|	Aktualisierte Kontoinformationen werden gespeichert und bestätigt.|
|6.2	|Benutzer ist im Konto eingeloggt	|Option zur Konto-Löschung auswählen|	Konto wird gelöscht und Bestätigung wird angezeigt.|
|7.1|	Benutzer A ist eingeloggt und versucht, auf Konto von Benutzer B zuzugreifen|	Versuch, Konto-ID von Benutzer B manuell einzugeben|	Zugriff verweigert; Fehlermeldung wird angezeigt, dass der Zugriff nicht autorisiert ist.|
| ...  |              |         |                   |



## 2 Planen

| AP-№ | Frist | Zuständig | Beschreibung | geplante Zeit |
| ---- | ----- | --------- | ------------ | ------------- |
|1.A			|22.11.2024|Damian Eisenring|Implementierung der Kontoerstellungsfunktion|	75
|1.B			|22.11.2024|Damian Eisenring|Validierung der Eingabedaten bei der Kontoerstellung|	75
|2.A			|22.11.2024|Damian Eisenring|Implementierung der Einzahlungsfunktion	| 75
|2.B			|22.11.2024|Damian Eisenring|Implementierung der Abhebungsfunktion|	75
|2.C			|22.11.2024|Damian Eisenring|Test und Validierung von Einzahlungs- und Abhebungsfunktionen	|75
|3.A			|29.11.2024|Damian Eisenring|Implementierung der Login-Funktion|	75
|3.B			|29.11.2024|Damian Eisenring|Implementierung der Session-Verwaltung nach dem Login|	75
|4.A			|6.11.2024|Damian Eisenring|Implementierung der Anzeige des Transaktionsverlaufs|	75
|4.B		|6.12.2024|Damian Eisenring|Gestaltung der Benutzeroberfläche für den Transaktionsverlauf	|75
|5.A		|13.12.2024|Damian Eisenring|Implementierung der Fehlerbehandlung bei unzureichendem Guthaben|	75
|5.B		|6.12.2024|Damian Eisenring|Fehlerbehandlung bei ungültigen Beträgen und Eingaben	|75
|6.A			|13.12.2024|Damian Eisenring|Funktion zur Aktualisierung von Kontoinformationen hinzufügen	|75
|6.B		|13.12.2024|Damian Eisenring|	Funktion zur Konto-Löschung implementieren|75
|6.C|13.12.2024|Damian Eisenring|			Test und Validierung der Datenbearbeitungsfunktionen|75
|7.A|13.12.2024	|	Damian Eisenring	|Implementierung der Zugriffsbeschränkungen	|75

Total: 18.75h



## 3 Entscheiden

✍️ Dokumentieren Sie hier Ihre Entscheidungen und Annahmen, die Sie im Bezug auf Ihre User Stories und die Implementierung getroffen haben.

## 4 Realisieren

| AP-№ | Datum | Zuständig | geplante Zeit | tatsächliche Zeit |
| ---- | ----- | --------- | ------------- | ----------------- |
| 1.A  |       |           |               |                   |
| ...  |       |           |               |                   |

✍️ Tragen Sie jedes Mal, wenn Sie ein Arbeitspaket abschließen, hier ein, wie lang Sie effektiv dafür hatten.

## 5 Kontrollieren

### 5.1 Testprotokoll

| TC-№ | Datum | Resultat | Tester |
| ---- | ----- | -------- | ------ |
| 1.1  |       |          |        |
| ...  |       |          |        |

✍️ Vergessen Sie nicht, ein Fazit hinzuzufügen, welches das Test-Ergebnis einordnet.

### 5.2 Exploratives Testen

| BR-№ | Ausgangslage | Eingabe | Erwartete Ausgabe | Tatsächliche Ausgabe |
| ---- | ------------ | ------- | ----------------- | -------------------- |
| I    |              |         |                   |                      |
| ...  |              |         |                   |                      |

✍️ Verwenden Sie römische Ziffern für Ihre Bug Reports, also I, II, III, IV etc.

## 6 Auswerten

✍️ Fügen Sie hier eine Verknüpfung zu Ihrem Lern-Bericht ein.
