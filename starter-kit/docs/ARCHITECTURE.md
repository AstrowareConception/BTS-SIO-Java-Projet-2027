# Architecture du starter

## Vue d'ensemble
```text
JavaFX
  │ HttpClient + JSON
  ▼
API Spring Boot
  │
  ├── Controller
  ├── Service
  ├── Repository JPA ── PostgreSQL
  └── StorageService ── filesystem

Navigateur
  │
  ▼
Admin Spring MVC + Thymeleaf
  │
  └── mêmes services métier
```

## Deux mécanismes d'authentification
- `/api/**` : HTTP Basic, stateless, pratique pour montrer un client JavaFX.
- admin Web : form login + session + CSRF.

Ce choix est pédagogique. Une vraie architecture exposée sur Internet doit être servie en HTTPS et peut employer OAuth2/JWT selon le contexte.

## Pourquoi JavaFX télécharge avant lecture ?
Le composant JavaFX `MediaPlayer` ne fournit pas simplement un header Authorization arbitraire à chaque requête média. Le starter :
1. appelle l'API avec `HttpClient` authentifié ;
2. télécharge dans un fichier temporaire ;
3. lit localement avec `MediaPlayer`.

Cela montre clairement la frontière sécurité/client.

## Pourquoi un StorageService ?
Pour que le domaine ne dépende pas du filesystem. Un étudiant avancé peut remplacer `LocalStorageService` par MinIO/S3.

## À améliorer dans votre projet
- utilisateurs en BDD ;
- validation MIME plus forte ;
- transactions fichier + métadonnées ;
- suppression compensatoire en cas d'échec ;
- migrations ;
- tests HTTP ;
- observabilité ;
- vraie politique de production.
