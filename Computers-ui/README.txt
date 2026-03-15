Angular Material frontend gotowy do podłączenia pod Spring Boot.

Wymagania:
- Node.js LTS
- Angular CLI

Instalacja:
1. npm install
2. npm start
3. Otwórz: http://localhost:4200

Backend:
Domyślnie frontend odwołuje się do:
http://localhost:8080/api/computers

Oczekiwany endpoint backendu:
GET /api/computers?page=0&size=5&sort=name,asc&name=acer&accountingDate=2026-01-05

Jeśli masz CORS, dodaj w Spring Boot:
@CrossOrigin(origins = "http://localhost:4200")
