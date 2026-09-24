# Sujet 3 — StudioFlow

## Contexte
Un **studio audiovisuel, une radio locale ou une société de production** possède une bibliothèque de jingles, ambiances, interviews, musiques libres, bandes annonces, rushs vidéo et habillages. Le classement par dossiers ne suffit plus.

## Socle obligatoire

### JavaFX
- bibliothèque ;
- recherche ;
- filtres ;
- lecteur audio/vidéo ;
- aperçu ;
- sélection rapide ;
- erreurs réseau.

### Admin Web
- ingestion/upload ;
- catégories/tags ;
- statuts ;
- droits ;
- utilisateurs ;
- métadonnées techniques.

### Backend
- REST ;
- JPA ;
- stockage ;
- sécurité ;
- OpenAPI ;
- pagination.

## Modèle suggéré
```text
User
Media
MediaType
Tag
Playlist
PlaylistItem
CuePoint
UsageEvent
License
MediaVersion
```

# 10 modules

## S1 — Playlist / conducteur
Construire et réordonner une liste de diffusion.

## S2 — Soundboard
JavaFX propose une grille de médias favoris déclenchables rapidement.

## S3 — Cue points
Définir des marqueurs temporels nommés.

## S4 — Cache local
Précharger certains médias pour lecture hors connexion.

## S5 — Historique de diffusion
Tracer l'utilisation d'un média.

## S6 — Licences et expiration
Interdire ou signaler l'utilisation après une date.

## S7 — Versionnement
Conserver plusieurs masters d'un contenu.

## S8 — Recherche technique
Durée, format, type, tags, date, statut.

## S9 — Planning de disponibilité
Rendre un média disponible sur une période définie.

## S10 — Statistiques
Top médias, catégories, périodes, utilisateurs.

## API typique
```http
GET  /api/media
GET  /api/media/{id}
GET  /api/media/{id}/content
POST /api/playlists
PATCH /api/playlists/{id}/items
POST /api/media/{id}/usage-events
```

## Bonus
- waveform ;
- Range HTTP ;
- MinIO ;
- raccourcis clavier ;
- écran régie plein écran ;
- HATEOAS.
