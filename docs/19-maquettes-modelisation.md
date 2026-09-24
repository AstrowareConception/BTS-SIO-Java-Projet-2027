# 19 — Maquettes & modélisation

## Maquettes obligatoires

### JavaFX
Au minimum :
1. connexion ;
2. catalogue ;
3. recherche/filtres ;
4. détail média ;
5. player/preview ;
6. écran d'un module principal ;
7. erreur/absence de données.

### Admin Web
Au minimum :
1. connexion ;
2. tableau de bord/liste ;
3. upload ;
4. édition métadonnées ;
5. gestion d'une donnée de référence ;
6. écran lié aux droits ou au workflow.

Outils libres : Figma, Penpot, Excalidraw, diagrams.net, papier proprement numérisé.

## Modélisation

### Obligatoire
- diagramme de cas d'usage ou équivalent structuré ;
- modèle de données ;
- diagramme de classes métier pertinent ;
- schéma d'architecture ;
- séquence d'au moins un échange JavaFX → API → service → repository.

### Recommandé
Un diagramme de séquence sur un scénario fort, par exemple :

```text
Utilisateur → JavaFX : choisir média
JavaFX → API : GET /api/assets/42/content
API → Security : vérifier identité/droits
API → Service : content(42)
Service → Repository : findById(42)
Service → Storage : load(storageKey)
API → JavaFX : 200 + bytes
JavaFX → Player : lecture
```

## Évolution des modèles
Si l'évolution du mercredi modifie le domaine, mettez à jour les diagrammes. Une modélisation qui ne correspond plus au code n'est pas une bonne preuve.
