# 09 — Tests & intégration continue

## Minimum backend
- tests unitaires des services ;
- tests des contrôleurs REST ;
- tests repository/intégration pertinents ;
- au moins un test de sécurité.

## JavaFX
Testez surtout :
- services clients ;
- parsing JSON ;
- comportement face aux statuts HTTP ;
- logique indépendante de l'UI.

## API
Prévoir notamment :
- 200 ;
- 201 ;
- 400/422 ;
- 401 ;
- 403 ;
- 404 ;
- 409 ;
- upload invalide.

## CI
Chaque push doit :
1. compiler ;
2. exécuter les tests ;
3. échouer si un test échoue.

Bonus :
- analyse statique ;
- analyse dépendances ;
- packaging ;
- artefacts ;
- Docker.

## Non-régression
Après évolution et incident, ajoutez/adaptez un test qui prouve que le défaut ne revient pas.
