# 02 — Chronologie complète de la semaine

Cette page est votre **GPS de projet**. Elle indique non seulement ce qu'il faut faire chaque jour, mais aussi **dans quel ordre**, ce qui doit absolument fonctionner avant de passer à la suite et comment savoir si vous êtes dans les temps.

Le projet suit une logique de **tranches verticales** :

```text
Concevoir
   ↓
Faire fonctionner un premier trajet complet
   ↓
Sécuriser le socle
   ↓
Ajouter les modules personnalisés
   ↓
Faire évoluer
   ↓
Diagnostiquer / corriger
   ↓
Stabiliser
   ↓
Livrer
```

Ne cherchez pas à terminer « toute la base », puis « toute l'API », puis « tout JavaFX ». Dès lundi, une première fonctionnalité doit traverser toute l'architecture.

---

# Lundi 22 — Construire le squelette et valider l'architecture

## Objectif du jour

À la fin du lundi, vous devez avoir **une première tranche verticale fonctionnelle** :

```text
PostgreSQL
   ↓
JPA
   ↓
Service
   ↓
Controller REST
   ↓
JSON
   ↓
HttpClient JavaFX
   ↓
Écran JavaFX
```

Le produit est encore très incomplet, mais son architecture est réelle.

## Bloc 1 — Choisir et cadrer

À faire dans cet ordre :

1. lire les trois sujets ;
2. choisir le contexte ;
3. sélectionner les 3 modules principaux ;
4. sélectionner les 2 modules secondaires ;
5. faire valider la combinaison ;
6. remplir le cahier des charges personnalisé ;
7. créer le dépôt personnel.

**Ne commencez pas à coder avant d'avoir validé le sujet et les modules.**

### Résultat attendu

Vous savez répondre clairement à :

- pour quelle organisation développez-vous ?
- quel problème résolvez-vous ?
- qui utilise JavaFX ?
- qui utilise l'administration Web ?
- quelles sont vos trois fonctionnalités différenciantes ?

---

## Bloc 2 — Concevoir avant de développer

Produire :

1. cas d'usage principaux ;
2. modèle de données initial ;
3. diagramme de classes métier simple ;
4. architecture générale ;
5. premières URI REST ;
6. maquette JavaFX ;
7. maquette admin Web.

Vous n'avez pas besoin de modéliser chaque détail de la semaine. Il faut disposer d'un modèle **suffisamment cohérent pour commencer**.

### Contrat REST initial

Écrivez déjà quelques ressources :

```http
GET  /api/assets
GET  /api/assets/{id}
GET  /api/assets/{id}/content
POST /api/assets
```

ou leurs équivalents pour votre sujet.

---

## Bloc 3 — Initialiser l'environnement

Dans cet ordre :

1. démarrer PostgreSQL ;
2. démarrer Spring Boot ;
3. vérifier `/api/health` ;
4. créer une première entité ;
5. créer son repository JPA ;
6. créer son service ;
7. créer un endpoint REST ;
8. vérifier la réponse JSON.

À ce stade, **n'ajoutez pas dix entités**.

Une seule chaîne complète vaut mieux qu'un modèle énorme non testé.

---

## Bloc 4 — Connecter JavaFX

1. démarrer JavaFX ;
2. créer/configurer `ApiClient` ;
3. appeler l'endpoint ;
4. afficher les données ;
5. gérer au moins le cas serveur indisponible.

### Jalon impératif du lundi soir

Vous devez pouvoir faire une démonstration de 60 secondes :

> « Voici une donnée stockée en base. Spring Boot l'expose par l'API. JavaFX appelle l'API et l'affiche. »

### État d'avancement attendu : **20 à 25 %**

### 🟢 Vert

- architecture conçue ;
- projet versionné ;
- Spring démarre ;
- BDD connectée ;
- au moins une entité persistée ;
- endpoint REST opérationnel ;
- JavaFX consomme réellement cet endpoint.

### 🟠 Orange

Spring et la BDD fonctionnent, mais JavaFX n'est pas encore connecté.

**Priorité mardi matin : terminer immédiatement le flux vertical.**

### 🔴 Rouge

À la fin du lundi :
- Spring ne démarre pas ;
- la BDD n'est pas connectée ;
- aucune API ne répond.

**Vous ne devez pas commencer les modules personnalisés mardi.**

---

# Mardi 23 — Construire le socle professionnel

## Objectif du jour

Rendre opérationnelles les briques communes :

- authentification ;
- habilitations ;
- fichiers ;
- métadonnées ;
- recherche ;
- lecture/preview ;
- administration Web.

À la fin du mardi, le produit doit déjà ressembler à une **petite application professionnelle utilisable**.

---

## Bloc 1 — Authentification et rôles

Dans cet ordre :

1. identifier les profils ;
2. sécuriser l'API ;
3. sécuriser l'administration ;
4. tester 401 ;
5. tester 403 ;
6. vérifier qu'un utilisateur ne peut pas contourner les droits par URL directe.

Ne faites pas seulement disparaître des boutons : **le serveur doit refuser l'action interdite**.

---

## Bloc 2 — Upload et stockage

Construire le workflow :

```text
Admin Web
   ↓
multipart upload
   ↓
Spring Boot
   ├── métadonnées → BDD
   └── contenu → StorageService
```

Vérifier :

- nom original ;
- clé de stockage ;
- MIME type ;
- taille ;
- checksum ;
- ressource en base ;
- fichier réellement présent.

---

## Bloc 3 — Catalogue et recherche

Mettre en place :

- liste paginée ;
- recherche texte ;
- au moins un filtre métier ;
- tri utile ;
- endpoint de détail.

JavaFX doit utiliser ces services.

---

## Bloc 4 — Player / preview

JavaFX doit pouvoir consulter au moins deux types pertinents pour votre contexte, par exemple :

- image ;
- audio ;
- vidéo.

Pour un type non supporté, afficher un comportement propre plutôt qu'une erreur incompréhensible.

---

## Bloc 5 — Administration Web

Minimum attendu :

- liste ;
- upload ;
- édition de métadonnées ;
- gestion d'une donnée de référence ;
- contrôle des rôles.

La technologie du front est libre. Le starter Thymeleaf suffit si vous souhaitez rester concentré sur Java.

### Jalon impératif du mardi soir

Scénario de démonstration :

> « Un contributeur ajoute un média depuis l'admin. Le fichier et ses métadonnées sont stockés. Un utilisateur le retrouve dans JavaFX, l'ouvre et le lit/prévisualise. Un utilisateur sans droit ne peut pas effectuer l'upload. »

### État d'avancement attendu : **45 à 50 %**

### 🟢 Vert

- upload complet ;
- stockage fiable ;
- recherche ;
- JavaFX catalogue + détail + player/preview ;
- authentification ;
- habilitations ;
- admin minimal.

### 🟠 Orange

Le média circule correctement, mais sécurité OU admin Web reste incomplet.

### 🔴 Rouge

Le mardi soir, JavaFX ne peut toujours pas récupérer et consulter un fichier distant.

Dans ce cas :
- abandonner tout bonus ;
- réduire temporairement les fonctionnalités admin ;
- priorité absolue au trajet fichier complet.

---

# Mercredi 24 — Personnaliser et stabiliser le contrat REST

## Objectif du jour

C'est **le jour charnière**.

Vous passez d'un produit générique à **votre réalisation personnelle**.

Les 3 modules principaux doivent être opérationnels avant la fin de journée.

---

# Mercredi matin — Checkpoint majeur

Avant de commencer les modules, vous devez normalement avoir :

- backend stable ;
- BDD ;
- fichiers ;
- sécurité ;
- JavaFX connecté ;
- player ;
- admin minimal ;
- recherche/pagination.

### Si oui

Vous êtes dans les temps.

### Si une seule brique secondaire manque

Vous êtes légèrement en retard mais encore dans une situation normale.

### Si JavaFX, fichiers ou sécurité ne fonctionne toujours pas

Vous êtes en **retard critique**.

Ne commencez pas trois modules en parallèle.

---

## Bloc 1 — Module principal n°1

Faire le module **de bout en bout** :

```text
modèle
→ repository
→ service
→ API
→ JavaFX/admin
→ test
```

Puis seulement passer au suivant.

---

## Bloc 2 — Modules principaux n°2 et n°3

Même logique.

Ne laissez pas trois modules terminés « côté serveur » avec JavaFX à faire plus tard.

---

## Bloc 3 — Nettoyage REST

Avant mercredi après-midi, vérifier :

- noms de ressources ;
- verbes HTTP ;
- statuts ;
- erreurs ;
- pagination ;
- filtrage ;
- DTO ;
- absence de RPC grossier.

Vous devez être clairement au **niveau 2 de Richardson**.

---

## Bloc 4 — OpenAPI

Vérifier que l'API est lisible depuis la documentation :

- opérations ;
- DTO ;
- paramètres ;
- réponses ;
- erreurs importantes ;
- sécurité.

---

## Bloc 5 — Tests + CI

À ce stade, la CI doit être réellement utile :

```text
push
 ↓
compilation
 ↓
tests
 ↓
succès / échec
```

---

## Mercredi après-midi — Évolution client

L'enseignant vous communique une évolution.

**Ne codez pas immédiatement.**

Créer d'abord une issue comprenant :

1. besoin reformulé ;
2. modèle impacté ;
3. endpoints impactés ;
4. JavaFX impacté ;
5. Web impacté ;
6. compatibilité avec l'existant ;
7. tests à créer/modifier ;
8. estimation.

### Jalon impératif du mercredi soir

- les **3 modules principaux fonctionnent** ;
- API propre ;
- OpenAPI accessible ;
- CI active ;
- évolution analysée ;
- branche/tâche d'évolution prête.

### État d'avancement attendu : **65 à 70 %**

## Le test « suis-je dans les temps mercredi ? »

### 🟢 VERT — rythme idéal

Mercredi soir vous pouvez démontrer :

1. connexion ;
2. recherche ;
3. média ;
4. vos trois modules principaux ;
5. droits ;
6. OpenAPI ;
7. tests verts.

**Vous êtes parfaitement dans le rythme.**

### 🟠 ORANGE — rattrapable

Il manque :
- un module principal ;
- OU OpenAPI ;
- OU quelques tests.

Mais le socle complet fonctionne.

**Jeudi matin : terminer le manque avant l'évolution.**

### 🔴 ROUGE — périmètre à réduire

Il manque encore :
- le player ;
- le stockage ;
- l'authentification ;
- ou deux modules principaux.

Vous devez :
1. figer toute nouvelle fonctionnalité secondaire ;
2. supprimer les bonus ;
3. reporter les modules secondaires ;
4. demander un arbitrage de périmètre ;
5. restaurer d'abord le chemin critique.

---

# Jeudi 25 — Faire évoluer, diagnostiquer, corriger

## Objectif du jour

Montrer que vous savez **maintenir une application existante**.

---

## Bloc 1 — Réaliser l'évolution

Suivre l'analyse du mercredi.

Après développement :

- mettre à jour le modèle ;
- mettre à jour OpenAPI ;
- mettre à jour JavaFX ;
- rejouer les tests ;
- ajouter les tests nécessaires.

### Jalon de mi-journée

L'évolution doit être **intégrée et stable**.

### État d'avancement attendu : **80 %**

---

## Bloc 2 — Incident imposé

L'enseignant vous communique un dysfonctionnement.

Procédure obligatoire :

```text
reproduire
 ↓
collecter les faits
 ↓
logs / statut HTTP
 ↓
formuler une hypothèse
 ↓
identifier la cause
 ↓
corriger
 ↓
ajouter test
 ↓
non-régression
```

Ne corrigez pas à l'aveugle.

---

## Bloc 3 — Rapport d'incident

Documenter :

- symptôme ;
- impact ;
- reproduction ;
- cause racine ;
- correction ;
- test ajouté ;
- commits.

---

## Bloc 4 — Modules secondaires

Seulement lorsque :

- évolution terminée ;
- incident corrigé ;
- CI verte.

### Jalon impératif du jeudi soir

Le produit est désormais **fonctionnellement terminé**.

Vendredi ne doit pas servir à développer une grosse fonctionnalité.

### État attendu : **90 %**

### 🟢 Vert

- évolution terminée ;
- incident corrigé ;
- non-régression ;
- trois modules principaux stables ;
- au moins un secondaire réalisé.

### 🟠 Orange

Le socle est stable mais aucun secondaire n'est fait.

Ce n'est pas grave : priorité à une bonne livraison.

### 🔴 Rouge

Vous avez encore un module principal incomplet.

Vendredi matin : terminer uniquement ce module, puis **feature freeze**.

---

# Vendredi 26 — Transformer le projet en livraison professionnelle

## Objectif

Ne plus « développer un projet », mais **livrer un produit démontrable**.

---

## Bloc 1 — Feature freeze

Après les dernières corrections critiques :

> plus aucune nouvelle fonctionnalité importante.

À partir de là :
- recette ;
- corrections ;
- documentation ;
- packaging ;
- déploiement.

---

## Bloc 2 — Recette complète

Tester les parcours :

- connexion ;
- droits ;
- catalogue ;
- recherche ;
- détail ;
- fichiers ;
- player ;
- trois modules principaux ;
- évolution ;
- scénario de l'incident corrigé ;
- admin.

---

## Bloc 3 — Déploiement et sauvegarde

Vérifier :

- backend packagé ;
- configuration externalisée ;
- BDD sauvegardable/restaurable ;
- stockage sauvegardable ;
- admin accessible ;
- JavaFX configuré pour le serveur cible.

---

## Bloc 4 — Packaging JavaFX

Minimum :
- commande reproductible ;
- artefact lançable ;
- procédure documentée.

Bonus :
- jlink ;
- jpackage ;
- installateur.

---

## Bloc 5 — Documentation et preuves BTS

Organiser les preuves.

Le README doit permettre à un tiers de comprendre et lancer la réalisation.

---

## Bloc 6 — Release

Créer une version finale, par exemple :

```text
v1.0.0
```

La release doit correspondre exactement à la version démontrée.

---

# Tableau de progression globale

| Moment | Avancement attendu | Ce qui doit fonctionner |
|---|---:|---|
| Lundi midi | ~10 % | cadrage + conception |
| Lundi soir | 20–25 % | premier flux BDD → REST → JavaFX |
| Mardi midi | ~35 % | sécurité + upload en cours |
| Mardi soir | 45–50 % | socle professionnel complet |
| Mercredi midi | ~60 % | au moins 1–2 modules principaux |
| Mercredi soir | 65–70 % | 3 modules + REST/OpenAPI + évolution analysée |
| Jeudi midi | ~80 % | évolution terminée |
| Jeudi soir | ~90 % | incident corrigé + fonctionnalité gelée |
| Vendredi midi | ~95 % | recette/déploiement/packaging |
| Vendredi fin | 100 % | release + preuves + démonstration |

Ces pourcentages ne représentent pas le nombre de lignes de code. Ils représentent **le niveau de complétude d'une réalisation professionnelle livrable**.

---

# Le chemin critique

Si vous manquez de temps, protégez toujours cette chaîne :

```text
Spring Boot
   ↓
BDD/JPA
   ↓
REST
   ↓
Security
   ↓
File Storage
   ↓
JavaFX
   ↓
3 modules principaux
   ↓
Tests
   ↓
Évolution
   ↓
Incident
   ↓
Livraison
```

Puis seulement :

```text
admin sophistiqué
modules secondaires
HATEOAS
cache avancé
MinIO
jpackage
bonus
```

---

# Règle de pilotage

À chaque fin de demi-journée, posez-vous trois questions :

1. **Qu'est-ce que je peux réellement démontrer maintenant ?**
2. **Quelle est la prochaine dépendance bloquante ?**
3. **Si je devais livrer demain, qu'est-ce qui manquerait absolument ?**

Le but est de ne jamais découvrir jeudi soir que trois jours de développement ne s'intègrent pas.
