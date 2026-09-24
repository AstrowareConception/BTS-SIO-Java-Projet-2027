# 04 — API REST : exigence centrale

## Niveau minimal : Richardson 2
Obligatoire :
- URI de ressources ;
- méthodes HTTP cohérentes ;
- codes HTTP pertinents.

### Bon
```http
GET    /api/assets
GET    /api/assets/42
POST   /api/assets
PATCH  /api/assets/42
DELETE /api/assets/42
GET    /api/assets/42/content
```

### À éviter
```http
POST /api/getAssets
POST /api/deleteAsset
POST /api/updateAsset
```

## Codes utiles
- 200 lecture/modification ;
- 201 création ;
- 204 succès sans contenu ;
- 400 requête mal formée ;
- 401 non authentifié ;
- 403 interdit ;
- 404 absent ;
- 409 conflit ;
- 413 fichier trop volumineux ;
- 415 type non supporté ;
- 422 refus métier.

## Pagination
```http
GET /api/assets?page=0&size=20&sort=createdAt,desc
```

## Filtrage
```http
GET /api/assets?type=AUDIO&tag=jingle&search=intro
```

## Erreurs structurées
```json
{
  "code": "ASSET_NOT_PLAYABLE",
  "message": "Cette ressource n'est pas disponible à la lecture.",
  "path": "/api/assets/42/content"
}
```

## HATEOAS
Niveau 3 en bonus seulement. Voir `10-openapi-hateoas.md`.
