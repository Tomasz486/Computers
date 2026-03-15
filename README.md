# Computers – Spring Boot + Angular

Aplikacja demonstracyjna napisana w **Spring Boot + Angular**, która pobiera kurs USD z API NBP, przelicza ceny komputerów na PLN, zapisuje dane w bazie SQLite oraz generuje plik XML.

- Spring Boot
- REST API
- Angular
- JPA / Hibernate
- integracji z zewnętrznym API
- paginacji, filtrowania i sortowania
- walidacji i obsługi wyjątków
- testów jednostkowych

---

# Architektura aplikacji

  Angular (frontend)
  
  ↓ REST
  
  Spring Boot (backend)
  
  ↓
  
  SQLite (baza danych)
  
  ↓
  
  NBP API (kurs walut)
  
  ↓
  
  XML (faktura)


---

# Technologie

Backend

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- SQLite
- Jackson XML
- Bean Validation
- JUnit + Mockito

Frontend

- Angular
- Angular Material
- RxJS
- TypeScript

---

# Funkcjonalności

## Aplikacja umożliwia:

### 1. Pobranie kursu USD z API NBP

API:

https://api.nbp.pl/api/exchangerates/rates/A/USD/{date}/?format=json


Jeśli dla danego dnia nie ma kursu (np. weekend), aplikacja automatycznie cofa się do poprzedniego dnia roboczego.

---

### 2. Przeliczenie kosztów komputerów

Dane wejściowe:

| Komputer | Koszt USD | Data |

|--------|--------|--------|

| ACER Aspire | 345 | 2026-01-05 |

| DELL Latitude | 543 | 2026-01-11 |

| HP Victus | 346 | 2026-01-19 |

Koszt PLN liczony jest według wzoru:


costPLN = costUSD * exchangeRate


Wynik zaokrąglany jest do **2 miejsc po przecinku**.

---

### 3. Zapis do bazy danych

Tabela:


| Pole | Typ | Opis |

| :--- | :--- | :--- |

| **id** | `Long` | Unikalny identyfikator (Klucz główny) |

| **name** | `String` | Nazwa urządzenia / modelu |

| **accountingDate** | `LocalDate` | Data zaksięgowania (format: YYYY-MM-DD) |

| **costUsd** | `BigDecimal` | Cena w dolarach (wysoka precyzja) |

| **costPln** | `BigDecimal` | Cena w złotówkach (po przeliczeniu) |

Baza danych:


SQLite


---

### 4️. Generowanie pliku XML

Format:
```xml
&lt;faktura&gt;
    &lt;komputer&gt;
        &lt;nazwa&gt;ACER Aspire&lt;/nazwa&gt;
        &lt;data_ksiegowania&gt;2026-01-05&lt;/data_ksiegowania&gt;
        &lt;koszt_USD&gt;345.00&lt;/koszt_USD&gt;
        &lt;koszt_PLN&gt;1401.22&lt;/koszt_PLN&gt;
    &lt;/komputer&gt;
&lt;/faktura&gt;
```

Plik generowany jest automatycznie podczas inicjalizacji danych.

### 5. REST API

Endpointy:

metoda	endpoint	opis
GET	/api/computers?page=0&size=5	
paginacja i wyszukiwanie

Filtrowanie możliwe po:

nazwie komputera

dacie księgowania

Przykład:


GET /api/computers?name=acer

### 6. Sortowanie

Spring automatycznie obsługuje sortowanie przez Pageable.

Przykład:


GET /api/computers?sort=name,asc

### 7. Paginacja

Przykład:


GET /api/computers?page=0&size=5


## Uruchomienie projektu
### 1️. Backend

W katalogu projektu:


mvn spring-boot:run


Backend uruchomi się na:


http://localhost:8080

### 2. Frontend

W katalogu Angular:


npm install
npm start


Frontend dostępny:


http://localhost:4200

### Autor: 

*Tomasz Kalinowski*