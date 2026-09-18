# Design Documentation

## Problem Statement
A library needs a reliable way to manage catalogue records, loans, overdue fines and demand for unavailable books.

## Objectives
Apply OOP, interfaces, inheritance, PriorityQueue, java.time, persistence, modularity, validation and testing.

## Functional Requirements
- Catalogue CRUD
- Authentication and role-based access
- Issue and return
- Fine calculation
- Reservation priority queue
- Reports

## Non-Functional Requirements
Usability, reliability, maintainability, performance, security, error handling and resource efficiency.

## Architecture
```text
ConsoleUI
   |
   +--> AuthenticationService --> UserRepository --+
   +--> LibraryService -----------------------------+--> SQLite
   |      |       |        |
   |      |       |        +--> FineEngine
   |      |       +--> LoanRepository
   |      +--> BookRepository
   |      +--> ReservationRepository
   |
   +--> ReportService --> repositories
```

## Workflow
```text
Start -> Login -> Validate -> Role Menu
                     |
       +-------------+----------------+
       |             |        |       |
   Catalogue       Issue    Return  Reserve
       |             |        |       |
       +-------------+--------+-------+
                            |
                      Reports / Logout
```

## Use Cases
```text
Admin:      Catalogue CRUD, Reports, Reservation visibility
Librarian:  Catalogue, Issue, Return, Reports, Reservations
Member:     Catalogue, Issue, Return, Reserve
```

## Class/Component View
```text
User (abstract) implements PermissionAware
 |-- Admin
 |-- Librarian
 |-- Member

LibraryService -> BookRepository
               -> LoanRepository
               -> ReservationRepository
               -> FineEngine

AuthenticationService -> UserRepository
ReportService         -> repositories
```

## Sequence — Return
```text
Member -> ConsoleUI -> LibraryService
LibraryService -> LoanRepository: find active loan
LibraryService -> FineEngine: calculateFine(due, return)
FineEngine --> LibraryService: amount
LibraryService -> LoanRepository: close loan
LibraryService -> BookRepository: add copy
LibraryService -> ReservationRepository: promote next
LibraryService --> ConsoleUI: fine
```

## ER / Storage Design
```text
USERS(id, username, password, name, role, priority)
BOOKS(id, isbn, title, author, publication_year, total_copies, available_copies)
LOANS(id, book_id FK, member_id FK, issue_date, due_date, return_date, fine)
RESERVATIONS(id, book_id FK, member_id FK, priority, reserved_at, status)

BOOKS 1---* LOANS
USERS 1---* LOANS
BOOKS 1---* RESERVATIONS
USERS 1---* RESERVATIONS
```

## Design Rationale
- `PriorityQueue` directly models reservation scheduling and provides O(log n) insertion/removal.
- `LocalDate` and `ChronoUnit.DAYS` make fine calculations explicit.
- Inheritance represents the three roles; `PermissionAware` defines the authorization contract.
- Repository classes separate SQL from business rules.
- SQLite keeps the project fully terminal-executable without a separate database server.
