# 26 — Atelier passerelle : comprendre Spring Boot avant la semaine de projet

**Public :** BTS SIO SLAM ayant suivi les cinq cours Java.  
**Durée conseillée :** deux demi-journées de 3 heures, **avant** le projet intensif de février 2027.  
**Objectif :** ne pas consacrer le lundi du projet à découvrir Spring, les échanges HTTP et JPA en même temps.

L'atelier utilise le [starter du dépôt](../starter-kit/README.md), sans changer son architecture, ses sujets ou ses exigences finales. L'étudiant n'a pas à reprogrammer tout le starter : il doit savoir le lire, le lancer, le tester et y apporter une modification simple.

## Prérequis vérifiables

- Un JDK **25** installé ; IntelliJ peut posséder en parallèle JDK 27, mais le **Project SDK**, `JAVA_HOME` et le Maven Runner du starter doivent pointer vers 25.
- Maven (ou Maven proposé par IntelliJ), Docker et Docker Compose.
- Connaître `class`, constructeur, `List<T>`, `try/catch`, JUnit et les bases SQL.
- Savoir expliquer une requête HTTP (méthode, URL, en-têtes, corps, réponse).
- Trois terminaux disponibles : Docker, backend, client.

## 0 — Démarrer le système (30 min)

Depuis la racine du dépôt :

```bash
cd starter-kit
java -version
mvn -version
docker compose up -d
mvn -pl backend spring-boot:run
```

Dans un **second terminal**, depuis `starter-kit` :

```bash
curl -i http://localhost:8080/api/health
curl -i http://localhost:8080/api/assets
curl -i -u user:user123! http://localhost:8080/api/assets
```

**Attention :** les identifiants sont des données fictives **uniquement pour le serveur local**. HTTP Basic transporte un secret réutilisable ; n'exposez jamais cette démonstration sur Internet en HTTP.

**Attendus :** `/api/health` réussit ; la liste sans identité est refusée ; avec l'utilisateur de démonstration elle réussit. Capturer les statuts exacts dans les DevTools ou `curl -i`.

## 1 — Retrouver les responsabilités (30 min)

Ouvrir ces fichiers, dans cet ordre :

1. `backend/.../controller/ApiAssetController.java` : il reçoit `GET /api/assets`.
2. `backend/.../service/MediaAssetService.java` : il choisit liste complète ou recherche.
3. `backend/.../repository/MediaAssetRepository.java` : il interroge les entités JPA.
4. `backend/.../domain/MediaAsset.java` : la donnée persistée.
5. `backend/.../dto/AssetDto.java` : la forme renvoyée au client.

Répondre par écrit : dans quel fichier placer une **règle de validation**, un **filtre SQL**, un **code 404**, un **champ JSON** et un **message JavaFX** ?

**Règle :** aucune requête SQL dans le contrôleur ; aucune vue JavaFX dans le backend.

## 2 — Modifier la recherche de façon verticale (45 min)

Objectif : rendre la recherche par titre observable, sans modifier le schéma.

1. Enregistrer un média dans l'admin `http://localhost:8080/admin` (compte `contrib`).
2. Demander `GET /api/assets?search=... ` avec et sans identité.
3. Retrouver `search` dans le Controller, le Service et le Repository.
4. Vérifier dans le JSON la liste `content` et les informations de pagination.
5. Depuis IntelliJ, poser un point d'arrêt dans `MediaAssetService.list`.

**Preuve :** capture du débogueur + deux réponses JSON + explication du `Page<AssetDto>`. N'écrire aucun code supplémentaire tant que ce trajet n'est pas compris.

## 3 — Brancher JavaFX sur l'API (45 min)

Dans un **troisième terminal** :

```bash
cd starter-kit
mvn -pl javafx-client javafx:run
```

Lire `javafx-client/.../ApiClient.java` et `MediaClientApp.java`.

Repérer : `HttpClient`, `HttpRequest`, `sendAsync`, `CompletableFuture`, `Platform.runLater`, `Gson`.

**Petite modification à réaliser :** lorsqu'un appel échoue, afficher un message distinct pour 401, 403, 404 ou erreur réseau ; ne jamais afficher le mot de passe, l'en-tête `Authorization` ou une trace serveur complète. Lors d'un chargement, bloquer temporairement l'action concernée puis la réactiver.

**Preuve :** écran qui affiche une liste ; capture d'un serveur volontairement arrêté et d'un message d'erreur compréhensible ; aucun gel prolongé de l'interface.

## 4 — Protéger un endpoint (45 min)

Lire `SecurityConfig.java` puis `ApiAssetController.upload`.

Vérifier en environnement local :

- sans identité : 401 sur une ressource protégée ;
- USER : liste possible, upload interdit (403) ;
- CONTRIBUTOR : upload autorisé ;
- vérifier une URL directement, **sans** utiliser de bouton de l'interface.

Pour un upload, utiliser un fichier test de petite taille et un outil HTTP ou l'admin. Documenter le statut obtenu. Le rôle est vérifié **sur le serveur**, pas dans JavaFX.

**Ne pas confondre :** utilisateur authentifié, utilisateur autorisé, propriétaire d'une ressource. Le starter ne réalise pas encore toutes les autorisations de propriété demandées par les sujets.

## 5 — Comprendre le stockage (30 min)

Faire un upload de test via `/admin`, puis identifier :

1. l'entrée `MediaAsset` en base : titre, type MIME, taille, SHA-256 et clé de stockage ;
2. le fichier réellement enregistré dans le dossier configuré `STORAGE_ROOT` ;
3. l'endpoint `GET /api/assets/{id}/content` ;
4. son téléchargement par le client JavaFX.

**Analyse attendue :** la base n'est pas le fichier. Un rollback JPA **n'annule pas** une écriture disque déjà réalisée ; le cycle de vie complet nécessite une stratégie d'échec et de nettoyage.

## 6 — Écrire et exécuter un test (30 min)

Exécuter :

```bash
cd starter-kit
mvn verify
```

Ouvrir `backend/src/test/.../MediaAssetServiceTest.java`.

Ajouter un cas simple : un titre vide doit être refusé, et le repository comme le stockage ne doivent pas être sollicités dans ce cas. Utiliser JUnit, AssertJ et Mockito déjà présents dans le module backend.

**Preuve :** test ajouté, rouge s'il est volontairement incorrect puis vert après correction, exécution du Maven `verify`.

## Mini-recette à remettre (15 min)

| Scénario | Action | Résultat exigé |
|---|---|---|
| Santé | GET /api/health | succès |
| Liste anonyme | GET /api/assets sans identité | 401 |
| Liste authentifiée | GET /api/assets avec USER | 200 + JSON |
| Téléversement interdit | USER tente upload | 403 |
| Téléversement autorisé | CONTRIBUTOR upload | 201 + ressource |
| Consultation média | JavaFX télécharge | preview ou message contrôlé |
| Panne réseau | couper le serveur puis rechercher | aucun gel JavaFX ; message clair |
| Test Maven | mvn verify | succès |

Les captures et commandes sont accompagnées d'une **explication personnelle**.

## Dépannage rapide

| Symptôme | Vérifier en priorité |
|---|---|
| Maven emploie Java 27 au lieu de 25 | Project SDK, Maven Runner, JAVA_HOME |
| Port 5432 déjà occupé | autre PostgreSQL local / docker compose ps |
| Backend ne démarre pas | Docker, DB_URL, logs serveur, version Java |
| 401 sur la liste | identifiants, HTTP Basic, en-tête Authorization |
| 403 sur upload | rôles USER / CONTRIBUTOR |
| JavaFX affiche un écran vide | backend actif, URL, logs du client, résultat de l'API |
| 415 lors de l'upload | requête multipart/form-data mal construite |
| 500 au téléchargement | présence du fichier dans STORAGE_ROOT, cohérence métadonnées/disque |

## Point pédagogique

Cet atelier enseigne la **liaison entre les briques** ; il ne remplace ni les cinq cours Java, ni le [cours passerelle 06](25-pont-entre-les-cours-et-le-projet.md), ni le projet individuel. Les sujets 2027 restent à personnaliser entièrement par les étudiants.
