# BTS SIO SLAM — Semaine intensive Java 2027

**Dates : du lundi 22 au vendredi 26 février 2027**  
**Format : projet individuel**  
**Objectif : produire une seconde réalisation professionnelle complémentaire du projet Web PHP/Slim.**

Cette semaine est centrée sur une **architecture distribuée professionnelle** :

```text
                 ┌──────────────────────────────┐
                 │       SPRING BOOT            │
                 │                              │
JavaFX ── HTTP ─►│  API REST + sécurité        │◄── HTTP ── Admin Web
                 │  services métier             │
                 │  Spring Data JPA             │
                 │  BDD + stockage fichiers     │
                 └──────────────────────────────┘
```

Le client JavaFX ne se connecte jamais directement à la base de données : il consomme l'API distante.

## Les trois sujets

- [MediaVault — Digital Asset Management](sujets/mediavault.md)
- [EduMedia — Médiathèque pédagogique](sujets/edumedia.md)
- [StudioFlow — Médiathèque audiovisuelle de production](sujets/studioflow.md)

## Personnalisation

Chaque étudiant choisit :
- **3 modules principaux** parmi 10 ;
- **2 modules secondaires** ;
- une combinaison principale différente des autres étudiants ayant le même sujet.

Le socle commun reste obligatoire pour tous.

## Où en suis-je ?

- [Chronologie complète et feux vert/orange/rouge](docs/02-planning.md)
- [Que faire chez moi ce soir ?](docs/21-travailler-chez-soi.md)
- [Guide d'autonomie](docs/20-autonomie.md)
- [Checklist finale](docs/16-checklist.md)

## Kit technique

- [Starter Spring Boot + JavaFX](starter-kit/README.md)
- [Architecture imposée](docs/01-architecture.md)
- [REST & Richardson](docs/04-rest.md)
- [Spring Boot](docs/05-spring-boot.md)
- [JavaFX](docs/06-javafx.md)
- [Fichiers multimédias](docs/07-fichiers.md)
- [Sécurité](docs/08-securite.md)
- [Stratégie de tests](docs/22-strategie-tests.md)
- [CI & qualité par niveaux](docs/23-ci-qualite.md)
- [OpenAPI & HATEOAS](docs/10-openapi-hateoas.md)
- [Ressources externes](docs/17-ressources.md)

## Ateliers autonomes

Si vous êtes 🟢 et souhaitez aller plus loin :

1. [JUnit, Mockito et tests Spring](ateliers/01-tests-junit-spring.md)
2. [JaCoCo + GitHub Actions](ateliers/02-jacoco-github-actions.md)
3. [Semgrep Community Edition](ateliers/03-semgrep.md)
4. [SonarQube Community Build](ateliers/04-sonarqube.md)
5. [Choisir un axe d'approfondissement](docs/24-parcours-approfondissement.md)

Des exemples prêts à adapter sont fournis dans `exemples/github-actions/`.

## Parcours complet

- [00 — Démarrage](docs/00-demarrage.md)
- [01 — Architecture](docs/01-architecture.md)
- [02 — Planning](docs/02-planning.md)
- [03 — Personnalisation](docs/03-personnalisation.md)
- [04 — REST](docs/04-rest.md)
- [05 — Spring Boot](docs/05-spring-boot.md)
- [06 — JavaFX](docs/06-javafx.md)
- [07 — Fichiers](docs/07-fichiers.md)
- [08 — Sécurité](docs/08-securite.md)
- [09 — Tests & CI](docs/09-tests-ci.md)
- [10 — OpenAPI & HATEOAS](docs/10-openapi-hateoas.md)
- [11 — Admin Web](docs/11-admin-web.md)
- [12 — Déploiement](docs/12-deploiement.md)
- [13 — Preuves BTS](docs/13-preuves-bts.md)
- [14 — Livrables](docs/14-livrables.md)
- [15 — Bonus](docs/15-bonus.md)
- [16 — Checklist](docs/16-checklist.md)
- [17 — Ressources](docs/17-ressources.md)
- [18 — Dépannage](docs/18-depannage.md)
- [19 — Maquettes & modélisation](docs/19-maquettes-modelisation.md)
- [20 — Guide d’autonomie](docs/20-autonomie.md)
- [21 — Travailler chez soi](docs/21-travailler-chez-soi.md)
- [22 — Stratégie de tests](docs/22-strategie-tests.md)
- [23 — CI & qualité](docs/23-ci-qualite.md)
- [24 — Approfondissement](docs/24-parcours-approfondissement.md)

## Philosophie

Le métier reste volontairement abordable. La difficulté attendue est ailleurs : **POO Java, Spring, séparation des responsabilités, contrat REST, client lourd, sécurité, persistance, fichiers, tests et déploiement**.

Une application simple mais très propre, testée et expliquée vaut mieux qu'un produit immense et fragile.
