Do uruchomienia aplikacji backendowej wymagane jest:

Java 17

Maven 3.8+

Spring Boot 3.x

dostęp do internetu (do pobrania kursu USD z API NBP)

Aplikacja korzysta z:

SQLite jako bazy danych

NBP REST API do pobierania kursów walut

Baza danych tworzona jest automatycznie przy pierwszym uruchomieniu aplikacji.



Uruchomienie aplikacji
Backend

W katalogu projektu:

mvn spring-boot:run

Serwer uruchomi się pod adresem:

http://localhost:8080

API dostępne jest pod:

http://localhost:8080/api/computers