# Sujet 2 — EduMedia

## Contexte
Un **organisme de formation** dispose de vidéos pédagogiques, podcasts, PDF, images et démonstrations. Les ressources doivent être classées par formation, thème, niveau et compétences, puis retrouvées facilement par formateurs et apprenants.

## Socle obligatoire

### JavaFX
- catalogue ;
- recherche ;
- filtres formation/niveau/type ;
- player/preview ;
- détail ;
- historique local.

### Admin Web
- ressources ;
- formations ;
- compétences/tags ;
- upload ;
- publication ;
- utilisateurs.

### Backend
- REST ;
- sécurité ;
- JPA ;
- stockage ;
- OpenAPI ;
- pagination.

## Modèle suggéré
```text
User
Resource
Course
Skill
Tag
ResourceSkill
Playlist
PlaylistItem
Progress
Bookmark
```

# 10 modules

## E1 — Playlists pédagogiques
Créer et ordonner des parcours de ressources.

## E2 — Association aux compétences
Une ressource peut travailler plusieurs compétences.

## E3 — Progression
Mémoriser non commencé/en cours/terminé et progression pertinente.

## E4 — Marque-pages temporels
Ajouter une note à une position audio/vidéo.

## E5 — Ressources obsolètes
Remplacer une ressource sans casser les anciens liens.

## E6 — Validation pédagogique
`DRAFT → PEDAGOGICAL_REVIEW → AVAILABLE → OBSOLETE`.

## E7 — Téléchargement hors ligne
Cache JavaFX des ressources autorisées.

## E8 — Transcription/sous-titres
Associer un fichier texte/VTT et le consulter.

## E9 — Statistiques pédagogiques
Consultations, complétions, ressources utilisées.

## E10 — Export de parcours
Exporter les métadonnées d'un parcours en JSON ou PDF.

## API typique
```http
GET  /api/resources
GET  /api/resources/{id}
GET  /api/resources/{id}/content
GET  /api/courses
POST /api/playlists
PATCH /api/progress/{resourceId}
```

## Bonus
- recherche plein texte ;
- chapitres ;
- HATEOAS ;
- MinIO ;
- accessibilité.
