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

## Kit technique

- [Starter Spring Boot + JavaFX](starter-kit/README.md)
- [Architecture imposée](docs/01-architecture.md)
- [REST & Richardson](docs/04-rest.md)
- [Spring Boot](docs/05-spring-boot.md)
- [JavaFX](docs/06-javafx.md)
- [Fichiers multimédias](docs/07-fichiers.md)
- [Sécurité](docs/08-securite.md)
- [Tests & CI](docs/09-tests-ci.md)
- [OpenAPI & HATEOAS](docs/10-openapi-hateoas.md)
- [Ressources externes](docs/17-ressources.md)

## Parcours

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

## Philosophie

Le métier reste volontairement abordable. La difficulté attendue est ailleurs : **POO Java, Spring, séparation des responsabilités, contrat REST, client lourd, sécurité, persistance, fichiers, tests et déploiement**.

Une application simple mais très propre, testée et expliquée vaut mieux qu'un produit immense et fragile.
