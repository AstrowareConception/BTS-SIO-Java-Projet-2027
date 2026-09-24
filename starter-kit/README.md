# Starter Kit Spring Boot + JavaFX

Ce starter montre une architecture minimale mais professionnelle.

## Versions figées lors de la préparation du dépôt

- JDK 25
- Spring Boot 4.1.1
- JavaFX 26
- springdoc-openapi 3.1.1
- PostgreSQL 17
- Gson 2.14.0 côté client

**En février 2027, vérifiez les versions stables avant de mettre à jour.** Ne changez pas de version au milieu de la semaine sans raison.

## Structure

```text
starter-kit/
├── backend/
├── javafx-client/
├── docker-compose.yml
└── pom.xml
```

## 1. Base de données

```bash
docker compose up -d
```

## 2. Backend

Depuis `starter-kit/` :

```bash
mvn -pl backend spring-boot:run
```

Serveur : http://localhost:8080

### Comptes de démonstration

- `user / user123!`
- `contrib / contrib123!`
- `admin / admin123!`

Ces comptes sont **uniquement pédagogiques**. Votre projet final doit gérer ses utilisateurs selon votre conception.

## 3. Admin Web

Ouvrir :

http://localhost:8080/admin

Connectez-vous avec `contrib` ou `admin`.

## 4. API

```bash
curl http://localhost:8080/api/health
```

Puis :

```bash
curl -u user:user123! http://localhost:8080/api/assets
```

## 5. JavaFX

```bash
mvn -pl javafx-client javafx:run
```

Le client demande URL, login et mot de passe. Il charge le catalogue par REST.

Pour lire un média, il le télécharge d'abord via `HttpClient` authentifié dans un fichier temporaire puis le confie à JavaFX `MediaPlayer`. Cela évite de contourner la sécurité HTTP.

## Ce que montre le starter

- séparation backend / client lourd ;
- REST ;
- DTO ;
- JPA ;
- upload multipart ;
- métadonnées en BDD ;
- contenu sur filesystem ;
- HTTP Basic stateless pour l'API ;
- formulaire de login pour l'admin Web ;
- rôles ;
- client JavaFX asynchrone ;
- player ;
- OpenAPI ;
- test unitaire ;
- CI.

## Ce qui manque volontairement

- vos sujets ;
- vos modules ;
- vos règles ;
- utilisateurs persistés en BDD ;
- vraies politiques de sécurité de production ;
- stockage objet ;
- JWT ;
- migrations Flyway ;
- tests complets.

Le starter doit être **compris puis adapté**, pas livré tel quel.
