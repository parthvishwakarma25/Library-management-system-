# Library Management with Fine Calculation & Reservation Queue

A terminal-first Java 17 library system demonstrating catalogue CRUD, issue/return with automatic fines, a `PriorityQueue` reservation scheduler, and role-based access using inheritance and interfaces.

## Requirements
- JDK 17+
- Maven 3.9+
- Terminal/command prompt

Check with `java -version` and `mvn -version`.

## Build and test
From the repository root:

```bash
mvn clean test
mvn clean package
```

## Run
```bash
mvn exec:java -Dexec.mainClass="com.vityarthi.library.Main"
```

The SQLite database is created automatically at `data/library.db`. No external database server is needed.

## Demo accounts
| Username | Password | Role |
|---|---|---|
| admin | admin123 | Admin |
| librarian | lib123 | Librarian |
| alice | alice123 | Member |
| bob | bob123 | Member |

## Features
1. Catalogue CRUD.
2. Role-based login and permissions.
3. Book issue and return.
4. Fine calculation using `java.time`.
5. Reservation queue using Java `PriorityQueue`.
6. Catalogue, loan, overdue, reservation and fine reports.
7. SQLite persistence.
8. JUnit tests and validation.

## Fine policy
Default loan period: 14 days. Default fine: ₹5 per overdue day, with zero grace days.

Example: due 2026-09-15 and returned 2026-09-18 = 3 overdue days = ₹15.

## Role model
`User` is abstract and extended by `Admin`, `Librarian`, and `Member`. `User` implements `PermissionAware`; permissions are defined by `Role` and `Permission`.

## Reservation ordering
Reservations are ordered by:
1. Higher member priority.
2. Earlier reservation time.
3. Lower reservation ID as deterministic tie-breaker.

## Project structure
```text
src/main/java/com/vityarthi/library/
  db/Database.java
  model/{User,Admin,Librarian,Member,Book,Loan,Reservation}.java
  repository/{UserRepository,BookRepository,LoanRepository,ReservationRepository}.java
  security/{Permission,Role,PermissionAware}.java
  service/{AuthenticationService,FineEngine,LibraryService,ReportService}.java
  ui/ConsoleUI.java
  Main.java
src/test/java/com/vityarthi/library/
  FineEngineTest.java
  ReservationQueueTest.java
docs/design.md
statement.md
```

## Evaluator walkthrough
1. `mvn clean test`
2. Run the app.
3. Login as `alice / alice123`.
4. View catalogue.
5. Logout and login as `librarian / lib123`.
6. Issue a book to Alice.
7. Return it using a date after the due date to see the fine engine.
8. Login as `admin / admin123` and open Reports.

## VITyarthi submission checklist
- Repository must be **Public**.
- `README.md` must be at the root.
- `statement.md` must be at the root.
- Run `mvn clean test` before submission.
- Submit only the repository root URL in this exact form:
`https://github.com/{github-username}/{repo-name}`
- Do not submit `/tree/main/` or `/blob/main/`.
