# 22 — Stratégie de tests : quoi tester et comment ?

Le but n'est pas d'avoir « beaucoup de tests ». Le but est de rendre **les comportements importants vérifiables**.

---

# 1. La pyramide

```text
          recette / E2E
        ────────────────
         intégration HTTP
      ────────────────────
       services / unités
    ────────────────────────
```

Plus on descend, plus les tests sont rapides et ciblés.

---

# 2. Tests unitaires de service

À tester en priorité :

- workflow ;
- autorisation métier ;
- calcul ;
- expiration ;
- versionnement ;
- déduplication ;
- changement d'état ;
- quota ;
- règles de modules.

Le repository ou le stockage peuvent être simulés avec Mockito.

Structure recommandée :

```text
Given  → état initial
When   → action
Then   → résultat + interactions importantes
```

Exemples de noms :

```text
uploadRejectsBlankTitle
expiredLicensePreventsPlayback
duplicateShaDoesNotCreateSecondFile
contributorCannotModifyForeignAsset
```

---

# 3. Tests Web / REST

Avec Spring Boot 4, vous pouvez cibler un contrôleur avec `@WebMvcTest` et remplacer ses collaborateurs par `@MockitoBean`.

À vérifier :

- route ;
- statut ;
- JSON ;
- Content-Type ;
- validation ;
- erreurs ;
- sécurité.

Exemple de matrice :

| Situation | Attendu |
|---|---|
| ressource existante | 200 |
| création valide | 201 |
| absent | 404 |
| conflit métier | 409 |
| données refusées | 422 |
| anonyme | 401 |
| mauvais rôle | 403 |

---

# 4. Tests de repository

Ils servent à vérifier :

- requête JPA personnalisée ;
- tri ;
- filtre ;
- pagination ;
- relations ;
- contrainte d'unicité.

Ne testez pas `findById` juste pour tester Spring Data.

Testez ce que **vous avez conçu**.

---

# 5. Tests d'intégration

Utilisez-les lorsque plusieurs briques doivent coopérer :

```text
HTTP → Security → Controller → Service → Repository → BDD
```

Cas pertinents :

- upload complet ;
- transaction ;
- sécurité réelle ;
- persistence ;
- recherche avancée.

Pour aller plus loin, Testcontainers permet d'utiliser un vrai PostgreSQL éphémère plutôt qu'une BDD différente de la production.

---

# 6. Tests du client JavaFX

N'essayez pas immédiatement de tester chaque bouton graphiquement.

Isolez ce qui est testable :

- `ApiClient` ;
- parsing JSON ;
- calcul de cache ;
- stratégie de retry ;
- mapping erreur HTTP → message utilisateur ;
- logique de ViewModel/service.

Exemples :

```text
401 → demande de reconnexion
404 → "média introuvable"
503 → message "serveur indisponible"
JSON incorrect → erreur maîtrisée
```

---

# 7. Tests de sécurité

Au minimum :

- anonyme → 401 ;
- USER → lecture autorisée ;
- USER → upload interdit ;
- CONTRIBUTOR → upload autorisé ;
- utilisateur A ne modifie pas une ressource de B ;
- fichier archivé/expiré non accessible si votre métier l'exige.

---

# 8. Tester un bug

Lorsqu'un incident apparaît :

1. reproduire ;
2. écrire un test qui **échoue** ;
3. corriger ;
4. vérifier que le test passe ;
5. rejouer la suite.

C'est votre meilleure preuve de non-régression.

---

# 9. Commandes Maven

Tout :

```bash
mvn test
```

Vérification complète :

```bash
mvn verify
```

Un test précis :

```bash
mvn -Dtest=MediaAssetServiceTest test
```

Backend seulement dans le starter :

```bash
mvn -pl backend test
```

---

# 10. Combien de tests ?

Il n'existe pas de chiffre magique.

Pour cette semaine, une bonne réalisation aura typiquement :

- plusieurs tests de services sur les règles importantes ;
- quelques tests REST ;
- au moins un test de sécurité ;
- les tests de non-régression de l'évolution et de l'incident.

Cherchez la **pertinence**, pas un compteur.

---

# 11. Ordre d'apprentissage

1. JUnit simple ;
2. Mockito ;
3. test service ;
4. test Web Spring ;
5. test sécurité ;
6. test intégration ;
7. JaCoCo ;
8. CI.

Voir [Atelier 1](../ateliers/01-tests-junit-spring.md).
