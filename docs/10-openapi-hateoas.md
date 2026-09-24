# 10 — OpenAPI & HATEOAS

## OpenAPI : obligatoire
L'API publie un contrat OpenAPI.

Avec springdoc-openapi, on peut notamment exposer :
- `/v3/api-docs` ;
- une interface de consultation.

Documentez :
- opérations ;
- paramètres ;
- schémas ;
- réponses ;
- erreurs ;
- sécurité.

## Richardson
### Niveau 0
RPC : insuffisant.

### Niveau 1
Ressources identifiées.

### Niveau 2 — obligatoire
Ressources + verbes HTTP + statuts.

### Niveau 3 — bonus
Hypermedia / HATEOAS.

## Exemple HATEOAS
```json
{
  "id": 42,
  "title": "Jingle ouverture",
  "_links": {
    "self": { "href": "/api/assets/42" },
    "content": { "href": "/api/assets/42/content" },
    "collection": { "href": "/api/assets" }
  }
}
```

Spring HATEOAS peut notamment produire HAL.

Ne sacrifiez jamais tests, sécurité ou architecture pour HATEOAS.
