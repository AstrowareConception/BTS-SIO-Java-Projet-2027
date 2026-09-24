# 18 — Dépannage

## Backend ne démarre pas
Lire la première vraie cause de la stack trace, puis vérifier JDK, Maven, port, BDD et configuration.

## 401
Authentification absente/invalide.

## 403
Authentifié mais interdit.

Ne « corrigez » pas en mettant permitAll partout.

## JavaFX figé
Probable appel réseau sur le thread UI.

## JSON illisible
Comparer contrat OpenAPI, DTO serveur et DTO client.

## Média non lu
Vérifier MIME, URL, droits, codec JavaFX, réponse serveur et fichier réel.

## Upload 413
Vérifier limites multipart ET règle métier.

## LazyInitialization
Ne passez pas tout en EAGER. Vérifiez DTO, transaction et besoin réel.

## Boucle JSON
N'exposez pas naïvement un graphe JPA bidirectionnel : DTO.

## CORS
Si admin séparé : configurez explicitement. Pas de wildcard aveugle avec credentials.

## Demande d'aide
```text
Objectif :
Résultat :
Attendu :
Endpoint :
Statut HTTP :
Log serveur :
Log client :
Hypothèses testées :
```
