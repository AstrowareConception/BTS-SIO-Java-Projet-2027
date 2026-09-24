# Sujet 1 — MediaVault

## Contexte
Une **agence de communication** produit et reçoit quotidiennement photos, vidéos, pistes audio, logos et documents de campagne. Les fichiers sont dispersés dans des dossiers réseau et deviennent difficiles à retrouver, versionner et sécuriser.

L'organisation souhaite un **Digital Asset Management (DAM)**.

## Socle obligatoire

### JavaFX
- authentification ;
- catalogue ;
- recherche/filtres ;
- détail média ;
- preview/player ;
- gestion des erreurs réseau.

### Admin Web
- utilisateurs ;
- catégories/tags ;
- upload ;
- métadonnées ;
- publication/archivage ;
- droits.

### Backend
- API REST ;
- JPA ;
- stockage ;
- rôles ;
- pagination ;
- OpenAPI.

## Modèle suggéré
```text
User
Asset
Category
Tag
AssetTag
Collection
CollectionItem
AssetVersion
AuditEntry
```

# 10 modules

## M1 — Collections
Créer, nommer et réordonner des collections de médias.

## M2 — Versionnement
Nouvelle version d'un asset sans perdre les versions précédentes.

## M3 — Déduplication SHA-256
Détecter un contenu identique déjà stocké.

## M4 — Workflow de validation
`DRAFT → REVIEW → PUBLISHED → ARCHIVED`.

## M5 — Gestion de licences
Type de licence, dates de validité et alerte d'expiration.

## M6 — Partage temporaire
Créer un accès temporaire à une ressource ou collection.

## M7 — Journal d'audit
Tracer les actions sensibles.

## M8 — Recherche avancée
Combiner type, tags, dates, propriétaire, statut.

## M9 — Statistiques d'utilisation
Consultations/téléchargements par média et catégorie.

## M10 — Cache local JavaFX
Conserver certaines ressources localement et les invalider proprement.

## API typique
```http
GET  /api/assets
GET  /api/assets/{id}
GET  /api/assets/{id}/content
POST /api/assets
POST /api/assets/{id}/versions
GET  /api/categories
```

## Bonus
- thumbnails ;
- EXIF ;
- S3/MinIO ;
- lien signé ;
- HATEOAS.
