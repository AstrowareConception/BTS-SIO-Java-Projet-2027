# Adapter le starter à votre sujet

## Étape 1 — Renommer le domaine
`MediaAsset` peut devenir `Resource`, `Media` ou rester `Asset`.

## Étape 2 — Ajouter le modèle métier
Ne mettez pas toutes les données dans une seule table.

Ajoutez catégories, tags, collections, compétences, playlists, licences selon votre sujet.

## Étape 3 — Écrire les modules dans les services
Exemple :
- versionnement ;
- workflow ;
- expiration ;
- progression ;
- cue points.

## Étape 4 — Faire évoluer l'API
Le contrat vient du métier. N'ajoutez pas une URI par bouton d'interface.

## Étape 5 — Faire évoluer JavaFX
Le client doit refléter votre contexte :
- DAM ;
- formation ;
- studio/régie.

## Étape 6 — Faire évoluer l'admin
Ajoutez uniquement les écrans nécessaires au pilotage.

## Étape 7 — Tests
Chaque module principal doit produire des scénarios et des tests.

## Étape 8 — Sécurité
Remplacez les comptes en mémoire par votre stratégie réelle.
