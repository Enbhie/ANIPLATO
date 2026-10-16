# ANIPLATO

Surplus-food board for Metro Manila: donors post surplus, food groups offer to collect it, admins verify organizations. JavaFX + MySQL, OOP finals prototype.

## Setup
1. Install JDK 25+, Maven, and MySQL 8+ (the Maven dependency is only the JDBC driver, not the server). Set `JAVA_HOME` to JDK 25 and make sure `java` on `PATH` uses the same JDK; start the MySQL service.
2. For a normal database-backed launch, copy `src/main/resources/db.properties.example` to `src/main/resources/db.properties` and set its `url`, `user`, and `password` values for your MySQL server.
3. Run `setup-db.bat` (or `./setup-db.ps1`). It creates the `aniplato` database and loads demo data. It asks before replacing an existing database; `-Force` skips the question.
4. In PowerShell, point Maven at the installed JDK 25 before launching:

   ```powershell
   $env:JAVA_HOME = 'C:\Path\To\jdk-25'
   $env:Path = "$env:JAVA_HOME\bin;$env:Path"
   mvn -version
   mvn javafx:run
   ```

   Confirm `mvn -version` reports Java 25 or newer. Replace the example JDK path with your installation path.

Demo logins, password `Aniplato123!` for all: `admin@aniplato.test`, `donor@aniplato.test`, `group@aniplato.test`.

### UI preview without MySQL

To browse the screens without configuring or starting MySQL, enable preview mode in the same PowerShell session before running Maven. Without this setting, normal startup requires valid database settings in `src/main/resources/db.properties`.

```powershell
$env:ANIPLATO_PREVIEW = 'true'
mvn javafx:run
```

Preview mode opens the admin dashboard by default. Set `ANIPLATO_PREVIEW_ROLE` to `donor` or `foodgroup` to preview those dashboards instead. The screens use their built-in placeholder content; preview mode skips database maintenance and is for visual browsing, not saving or loading real data. Close the app and run `Remove-Item Env:ANIPLATO_PREVIEW` (and `Remove-Item Env:ANIPLATO_PREVIEW_ROLE` if set) to return to normal startup.

## Layout
```
src/main/java/com/aniplato/
  Main.java
  model/       entities and enums
  view/        FXML + CSS (admin/, donor/, foodgroup/, shared/)
  controller/  one per screen (admin/, donor/, foodgroup/, shared/)
  service/     business rules, one transaction per action
  dao/         SQL only, one per table
  exception/   RuleViolationException
  util/        Database, PasswordHasher, Settings, Session, Navigator
sql/           schema.sql, seed.sql
docs/          ARCHITECTURE.md (schema + diagrams)
uploads/       documents and photos (git-ignored)
```
See `CLAUDE.md` for the coding rules and `docs/Project Spec.md` for the full spec.
