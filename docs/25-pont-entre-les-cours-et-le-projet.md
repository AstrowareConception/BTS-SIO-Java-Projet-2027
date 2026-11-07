# 25 — Passer des cinq cours Java au projet de février 2027

> **Document de liaison pédagogique — BTS SIO SLAM 2026/2027.** À lire AVANT le démarrage du projet. Le projet conserve son architecture distribuée et ses trois sujets.

## Le changement de paradigme

Les supports Java 01 à 05 produisent une bibliothèque personnelle **autonome** : JavaFX → Repository JDBC → fichier SQLite local.

La réalisation professionnelle de février 2027 est différente :

```text
JavaFX ----HTTP/JSON----> Spring Boot (Controller -> Service -> Repository JPA)
                                            |                   |
Admin Web ----HTTP------> Spring Security   |                   v
                                            +------------> PostgreSQL
                                            |
                                            +------------> stockage médias
```

**Règle non négociable :** le client JavaFX n'accède jamais directement à PostgreSQL, à un EntityManager JPA, ni aux dossiers de fichiers du serveur. Le client Web et le client JavaFX utilisent le même métier côté serveur.

## Matrice de correspondance exacte

| Support préparé | Réutilisable tel quel | À adapter pour 2027 |
|---|---|---|
| **01 — Java / premiers objets** | types, conditions, méthodes, classes, objets, constructeurs | remplacer l'exemple Livre par MediaAsset une fois les bases assimilées |
| **02 — POO / collections / tests** | encapsulation, composition, List, interfaces, exceptions, JUnit | distinguer entité JPA, DTO, service Spring, mocks Mockito |
| **03 — JavaFX / UI** | scène, contrôleurs, composants, listes, états de l'interface | ajouter une couche ApiClient, JSON, HttpClient asynchrone, gestion 401/403/404 |
| **04 — JDBC / SQLite** | SQL, clés, transactions, Repository, mapping | **JDBC local est une étape d'apprentissage**, le serveur utilise PostgreSQL + Spring Data JPA |
| **05 — livraison / Maven** | Maven, configurations, tests, Git, documents, diagnostic | distinguer le serveur Spring Boot (JAR/service) du client JavaFX (application native) |
| **06 — Passerelle Spring Boot** | nouveau cours à étudier | Spring Web MVC, JPA, REST/JSON, HTTP, Security, fichiers, Thymeleaf et tests de contrat |

Les autres cours BTS déjà reçus permettent des rapprochements : **PHP MVC/Slim 4** (routeur, contrôleur, modèle), **API REST** (HTTP, Richardson, JSON, statuts), **JWT** (authentification/autorisation). Ne pas faire croire que Slim et Spring ont les mêmes API : transférer les concepts, pas la syntaxe.

## Versions : ne pas confondre apprentissage et cible projet

- La collection des cinq cours Java a été préparée pour **JDK 27 / JavaFX 27**.
- Le **starter réellement fourni par ce dépôt est épinglé** à **JDK 25 (LTS) / JavaFX 26 / Spring Boot 4.1.1**.
- **Pour exécuter le starter, utilisez le JDK 25 sélectionné comme Project SDK et Maven Runner dans IntelliJ**, même si un JDK 27 est installé ailleurs. Les sources ne doivent pas utiliser des API propres à Java 27 si le projet compile avec `java.version=25`.
- Le JDK 27 n'est pas un prérequis du starter ; le JDK 25 est la cible d'enseignement du projet jusqu'à décision explicite et validation de CI. Les dépendances Maven sont gérées depuis le `pom.xml` parent.
- La date et les conditions de réévaluation sont précisées dans `starter-kit/README.md`. **Ne mettez pas les versions à jour au milieu de la semaine intensive**.

## Les concepts nouveaux (à apprendre AVANT le lundi 22 février)

1. **Serveur HTTP :** il démarre indépendamment du client, écoute sur un port et retourne une réponse par requête.
2. **Spring Boot :** il assemble les composants et démarre le serveur. `@RestController` reçoit HTTP ; `@Service` porte le métier ; `@Repository` / `JpaRepository` accède aux données.
3. **DTO JSON :** les objets exposés par l'API sont un contrat, pas une publication automatique de toutes les colonnes JPA.
4. **Spring Data JPA :** il remplace le JDBC manuel dans ce starter. Une entité `@Entity` ne doit pas être envoyée telle quelle à tous les clients.
5. **Client JavaFX distant :** `HttpClient.sendAsync`, `CompletableFuture`, et `Platform.runLater` pour revenir sur le thread JavaFX.
6. **Sécurité :** authentifier n'est pas autoriser. Tester 401 (non authentifié) et 403 (droits insuffisants), y compris par un appel HTTP direct.
7. **Fichiers :** `multipart/form-data`, métadonnées en base, octets sur disque côté serveur, téléchargement authentifié côté client.
8. **Admin Web :** Thymeleaf et formulaire HTTP côté serveur, sans introduire un second backend ou un deuxième modèle métier.
9. **Tests :** les règles Java unitaires restent testables ; compléter par un test contrôleur, un test d'accès et une recette client-serveur.

## Parcours préparatoire recommandé

Suivre dans cet ordre :

1. Cours 01 et 02 : modèles Java et tests.
2. Cours 03 : fenêtre JavaFX et callback.
3. Cours 04 : SQL/JDBC pour comprendre une persistance. **Ne pas importer SQLite ni JDBC dans le client du projet 2027.**
4. Cours 05 : Maven, configuration, diagnostic et livraison.
5. Cours **06** : transition Spring Boot + REST + JPA + client distant.
6. [Atelier passerelle autonome](26-atelier-passerelle-spring-boot.md) et [matrice de préparation](27-matrice-preparation-projet.md).
7. Seulement ensuite : [choix du sujet](../README.md) et [planning de la semaine](02-planning.md).

## Ce que le starter a déjà fait et ce que l'étudiant doit encore faire

**Déjà fourni :** endpoints de base, santé du serveur, entité, repository JPA, service, stockage local, contrôle d'accès démonstratif, client JavaFX, page admin, test de service, CI.

**Non terminé intentionnellement :** vos trois modules principaux et deux secondaires, modèle adapté, autorisations par propriétaire, comptes persistés, tests exhaustifs, gestion robuste du cycle de vie des fichiers, packaging métier final.

**Prudence importante :** le stockage disque et la transaction de la BDD sont deux ressources différentes : `@Transactional` ne rend pas l'écriture du fichier atomique. Prévoir nettoyage/compensation si une écriture SQL échoue après l'enregistrement physique.

## La passerelle est terminée si l'étudiant peut expliquer sans notes

- Pourquoi JavaFX n'interroge pas directement le SGBD distant ;
- Le trajet `GET /api/assets` → contrôleur → service → repository → DTO → JSON → JavaFX ;
- La différence entre `@Entity`, `AssetDto` et `AssetDto` côté client ;
- Pourquoi `HttpClient.sendAsync` évite de figer l'interface ;
- Pourquoi un utilisateur authentifié peut recevoir 403 ;
- Comment un upload stocke deux formes différentes de données ;
- Pourquoi les modifications du métier restent côté serveur.

Les compétences visées sont cohérentes avec la conception et le développement de solutions applicatives, l'exploitation des technologies Web, les tests, la maintenance et la cybersécurité décrites dans le RNCP40792. Ce document **n'affirme pas que Spring Boot est imposé par le diplôme** : c'est le choix technologique de cette réalisation.
