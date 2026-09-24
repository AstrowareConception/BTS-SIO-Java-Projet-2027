# 07 — Gestion professionnelle des fichiers

## Séparer contenu et métadonnées

### BDD
- identifiant ;
- titre ;
- nom original ;
- clé de stockage ;
- MIME type ;
- taille ;
- checksum ;
- propriétaire ;
- statut ;
- dates ;
- tags/catégories.

### Stockage
Le contenu binaire.

```text
storage/
  8c/
    f3/
      8cf31...a2.mp3
```

## Upload
Vérifier :
- taille ;
- type autorisé ;
- nom ;
- contenu lorsque possible ;
- quota éventuel ;
- droits.

## Lecture/téléchargement
L'accès au fichier passe aussi par l'autorisation serveur.

Connaître la clé de stockage ne doit pas permettre de contourner les droits.

## SHA-256
Utile pour :
- intégrité ;
- déduplication ;
- détection de modification.

## Stockage
Socle :
- filesystem local ou volume serveur.

Bonus :
- MinIO / S3 compatible.

L'abstraction `StorageService` doit permettre de changer le backend.

## Range
Le support de `Range` pour lecture audio/vidéo partielle est un bonus avancé.
