# 27 — Matrice d'alignement enseignant / préparation projet 2027

**Statut :** référence de préparation, distincte du sujet de projet et de la grille d'examen officielle. La semaine de projet se déroule du **22 au 26 février 2027**.

## 1. Décision pédagogique

Les cours Java 01 à 05 portent sur les objets et un desktop autonome. Le projet exige un backend Spring Boot, REST/JSON, Spring Data JPA, PostgreSQL, Spring Security, fichiers distants et une administration Web. **La passerelle Spring Boot est obligatoire dans la préparation**, pas un bonus à découvrir le lundi matin.

On conserve intégralement :
- les trois sujets et le choix **3 modules principaux + 2 secondaires** ;
- la séparation JavaFX / Spring Boot / Admin Web / stockage ;
- la qualité des tests, la sécurité et les preuves demandées ;
- la chronologie et les incidents/évolutions imposés en cours de semaine.

## 2. Tableau de préparation préprojet

| Domaine | Ce que les cours 01–05 apportent | Ce qui est requis avant le 22/02 | Vérification |
|---|---|---|---|
| Java POO | classes, objets, méthodes, interfaces, collections | savoir lire une entité et un service | exercice oral sur responsabilités |
| JavaFX | scène, contrôleur, événements, formulaires | faire une lecture réseau asynchrone | démo sans blocage GUI |
| JDBC/SQLite | SQL, schéma, transactions, repository | distinguer JPA et JDBC | expliquer `JpaRepository` |
| REST | culture déjà vue en PHP/Slim | appliquer méthodes, JSON, statuts à Java | `curl` + DTO |
| Spring Boot | non traité | `@RestController`, `@Service`, injection par constructeur | tracer GET /api/assets |
| JPA | non traité | `@Entity`, `@Id`, mapping, repository | lecture du modèle |
| Sécurité Spring | non traité | 401/403, méthodes protégées, sessions Web/Basic API | appels tests |
| Fichiers | Java I/O de base | upload multipart, stockage serveur, download auth | fichier round-trip |
| Qualité | JUnit et Maven | tests services, tests HTTP, CI, défauts reproductibles | `mvn verify` |
| Livrable | packaging desktop | deux applications (backend + JavaFX), config et preuve | lancer depuis clone propre |

## 3. Atelier de préparation — barème diagnostique proposé

Un contrôle de **30 à 45 minutes** peut précéder la semaine, sans devenir une nouvelle épreuve officielle :

| Test | Validation observable |
|---|---|
| Identifier les 5 couches | étudiant nomme JavaFX / Controller / Service / Repository / BDD |
| Lire une API | GET /api/assets : statut, JSON, pagination |
| Montrer JPA | expliquer @Entity, identifiant et différence DTO |
| Comprendre HTTP asynchrone | expliquer pourquoi la GUI ne bloque pas |
| Vérifier les droits | 401 vs 403 depuis curl |
| Tester un cas métier | test JUnit sur un service |
| Suivre une erreur | consulter log et reproduire 404 / panne réseau |

**Feu vert :** tous les tests essentiels réussissent. **Feu orange :** lacune ponctuelle corrigible via l'atelier 26. **Feu rouge :** serveur et client ne communiquent pas ; suivre l'atelier avant de lancer les modules individuels.

## 4. Environnement : politique de versions

Les cours 2026/27 présentent **JDK 27 et JavaFX 27** ; le starter a été vérifié dans sa configuration épinglée **JDK 25 LTS / JavaFX 26 / Spring Boot 4.1.1**. On ne remplace pas silencieusement une version par l'autre.

- Pour le projet, la vérité est `starter-kit/pom.xml` et `.github/workflows/starter-check.yml`.
- Avant l'intensive, comparer versions stables et exécuter `mvn clean verify` sur un clone frais et le poste de classe.
- Toute mise à jour nécessite tests backend + lancement JavaFX + test de média natif + validation de la CI.
- Les versions du cours peuvent différer du starter : **la syntaxe enseignée doit rester compatible avec la cible Maven 25**.

## 5. Risques déjà identifiés dans le starter — à expliquer, non à masquer

1. **Identifiants de démonstration en mémoire**, destinés uniquement au local ; ne pas publier en production.
2. **HTTP Basic** facile à comprendre, mais seulement avec HTTPS hors du poste local ; aucun JWT imposé.
3. **Le code JavaFX d'exemple contient les identifiants de démo préremplis** : à retirer avant toute diffusion à de vrais utilisateurs ; ne jamais journaliser le mot de passe.
4. **Fichiers vs base** : pas de transaction distribuée entre l'écriture disque et JPA ; prévoir reprise/compensation et nettoyage.
5. **Type MIME annoncé par le client** : ne suffit pas à valider le contenu ; adopter une validation de type et de taille adaptée.
6. **Chemins et stockage** : config externe, espaces de stockage non exposés directement par le serveur Web et chemin normalisé.
7. **DDL Hibernate `update`** : acceptable pour le starter local de formation, pas une stratégie de migration en production.
8. **Le player JavaFX dépend des codecs et du système** : ne pas annoncer qu'un audio/vidéo quelconque est toujours lisible.
9. **Lecture réseau/ressources natives** : fermer les flux, éviter d'accumuler des médias temporaires et vérifier les limites de taille.
10. **CI backend** : le workflow vérifie la compilation et les tests ; ce n'est pas, à lui seul, une preuve de packaging native multi-OS.

Ces points doivent nourrir l'amélioration du projet personnel. Le starter n'est ni une application prête pour Internet, ni un corrigé d'examen à recopier.

## 6. Liens utiles et enchaînement

- [Correspondance cours → projet](25-pont-entre-les-cours-et-le-projet.md)
- [Atelier passerelle](26-atelier-passerelle-spring-boot.md)
- [Architecture du projet](01-architecture.md)
- [Planning réel de la semaine](02-planning.md)
- [Tests](22-strategie-tests.md)
- [Ressources techniques](17-ressources.md)
- [Preuves BTS](13-preuves-bts.md)

**Référentiel :** RNCP40792, option SLAM — conception et développement de solutions applicatives, technologies Web et mobilité, exploitation de composants, maintenance, tests, documentation et cybersécurité. La réalisation seule ne garantit pas que tous les critères de l'environnement de certification E5 sont satisfaits : cet environnement doit être vérifié séparément conformément à l'annexe II.E.
