# 08 — Sécurité

## Authentification centralisée
Le serveur décide des identités et droits.

Rôles conseillés :
- USER ;
- CONTRIBUTOR ;
- ADMIN ;
- éventuellement VALIDATOR / TRAINER.

## Starter
Le starter montre une solution simple :
- API en HTTP Basic pour faciliter JavaFX ;
- formulaire Web pour l'administration ;
- mots de passe BCrypt ;
- utilisateurs côté serveur.

Pour une exposition réelle, HTTP Basic doit être protégé par HTTPS. JWT/bearer token est un bonus possible.

## Autorisation
Contrôlez :
- les URL ;
- les méthodes ;
- et les règles liées à la ressource.

Exemple : un CONTRIBUTOR modifie ses ressources mais pas celles d'un autre.

## Upload
Ne faites jamais confiance à la seule extension.

Contrôlez :
- taille ;
- MIME type ;
- nom ;
- droits ;
- emplacement.

## Secrets
Jamais dans Git.

## Logs
Ne jamais journaliser :
- mot de passe ;
- Authorization ;
- token ;
- secret.

## CSRF
Pour un admin Web utilisant session/formulaire, conservez la protection CSRF sauf justification.
