# 12 — Déploiement

## Backend
```bash
./mvnw clean package
java -jar target/...
```

## Configuration externalisée
- URL BDD ;
- identifiants ;
- stockage ;
- taille upload ;
- URL publique ;
- secrets.

## BDD
Documenter :
- création ;
- migrations/DDL ;
- sauvegarde ;
- restauration.

## Fichiers
Documenter :
- emplacement ;
- sauvegarde ;
- restauration ;
- droits filesystem ;
- comportement si un fichier manque.

## JavaFX
Produire au minimum un artefact lançable et documenter l'exécution.

Bonus :
- jlink ;
- jpackage ;
- installateur natif.

## Conteneurs
Docker/Compose recommandé pour backend + BDD, sans masquer la compréhension.
