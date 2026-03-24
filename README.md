# MGIN Aufgabe 10 - Radiologie-Institut Reservierungssystem

Das ist meine Abgabe für die MGIN Aufgabe 10.  
Ich habe ein System gebaut, mit dem man in einem Radiologie-Institut Patienten verwalten und Termine für Geräte wie MRT oder CT buchen kann.

## Was kann die App?

Ich habe versucht, alle Anforderungen aus der Angabe umzusetzen:

* **Patienten anmelden:** Man kann neue Patienten mit SVN, Name, Geburtsdatum usw. registrieren.
* **Geräte hinzufügen:** Man kann neue Geräte wie MRT, CT usw. anlegen (mit ID, Typ und Raum).
* **Termine buchen:** Wenn ein Patient im System ist, kann man für ihn eine Reservierung bei einem Gerät machen. Dabei kann man auch die Körperregion auswählen und einen Kommentar schreiben.
* **Listen-Ansicht:** Man kann sich für jedes Gerät anschauen, welche Termine es gibt.
* **Design:** Ich habe Bootstrap verwendet, damit die Seite nicht ganz so leer aussieht und auch am Handy funktioniert.

## Wie startet man die Webseite?

Damit das Programm läuft, muss man ein paar Dinge einstellen:

### 1. Datenbank
* Ich verwende MySQL.
* Die Datenbank muss `MGIN_Aufgabe_10` heißen (steht in der application.properties).
* Die Tabellen werden automatisch erstellt, man muss nichts extra machen.
* In der DataInitializer.java sind schon ein paar Geräte (CT, MRT, Röntgen, Ultraschall) drin, damit man gleich testen kann.

### 2. Starten
* Im Terminal im Projektordner eingeben:  
  `./mvnw spring-boot:run`
* Danach ist die Seite unter http://localhost:8080 erreichbar.

## Aufbau

* **Spring Boot:** Backend
* **Thymeleaf:** HTML-Seiten
* **Spring Data JPA:** Datenbank-Anbindung
* **Bootstrap:** Design

## Kurze Anleitung

1. Zuerst einen Patienten anlegen, sonst kann man nichts reservieren.
2. Dann eine Reservierung erstellen und ein Gerät auswählen.
3. Bei den Listen kann man nach Geräten filtern.

---

MGIN Aufgabe 10  
Finn Freitag - 3BHBGM HTL Spengergasse  
fre220600@spengergasse.at