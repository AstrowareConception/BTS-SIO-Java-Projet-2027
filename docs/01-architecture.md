# 01 — Architecture commune

## Architecture obligatoire
```text
                        ┌─────────────────────────┐
                        │       Spring Boot       │
                        │                         │
JavaFX ── REST/JSON ───►│ REST Controllers       │
                        │ Services métier         │
Admin Web ─────────────►│ Security               │
                        │ Repositories JPA        │
                        │ PostgreSQL/MariaDB      │
                        │ File Storage            │
                        └─────────────────────────┘
```

## Backend
- Java ;
- Spring Boot ;
- Spring Web MVC ;
- Spring Data JPA ;
- Spring Security ;
- validation Jakarta ;
- OpenAPI ;
- BDD relationnelle ;
- stockage de fichiers.

## Client métier
- JavaFX ;
- appels HTTP distants ;
- JSON ;
- gestion des erreurs réseau ;
- affichage/lecture de médias.

## Administration
- Web obligatoire ;
- technologie libre ;
- le starter fournit Thymeleaf comme voie simple.

## Interdictions
- connexion JDBC/JPA directe depuis JavaFX ;
- règles métier importantes uniquement côté client ;
- mots de passe en clair ;
- URL serveur dupliquée partout ;
- contrôleur Spring contenant toute la logique métier.

## Organisation serveur recommandée
```text
controller/
dto/
service/
repository/
domain/
security/
storage/
config/
exception/
```

## Principe
```text
HTTP → Controller → Service → Repository / Storage → BDD / fichiers
```
