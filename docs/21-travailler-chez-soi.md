# 21 — Travailler chez soi : que faire ce soir ?

Cette page répond à une question simple :

> **« Si je continue ce soir, qu'est-ce qui est réellement utile ? »**

La réponse dépend de votre état : 🔴 rouge, 🟠 orange ou 🟢 vert dans la [chronologie](02-planning.md).

---

# Règle n°1 — Ne travaillez pas au hasard

Avant de commencer chez vous :

1. ouvrez votre dernier `POINT_ETAPE.md` ;
2. relisez le jalon du lendemain ;
3. choisissez **une seule priorité** ;
4. fixez un résultat démontrable ;
5. poussez votre travail avant d'arrêter.

Mauvais objectif :

> « Ce soir je vais avancer Spring. »

Bon objectif :

> « Ce soir, `GET /api/assets/{id}` retourne 200 ou 404 correctement et possède trois tests. »

---

# Si vous avez 30 minutes

Choisissez **une micro-victoire** :

- corriger un test rouge ;
- documenter trois endpoints OpenAPI ;
- écrire un test unitaire manquant ;
- corriger un 500 en 404/409/422 ;
- nettoyer une duplication ;
- ajouter un cas d'erreur JavaFX ;
- documenter une décision ;
- créer une issue claire pour demain.

Ne démarrez pas une grosse fonctionnalité.

---

# Si vous avez 1 heure

## 🔴 Rouge — rattrapage

Travaillez uniquement sur le **premier maillon cassé du chemin critique** :

```text
Spring
→ BDD/JPA
→ REST
→ Security
→ Storage
→ JavaFX
→ Modules
→ Tests
```

Exemple : si votre API fonctionne mais pas JavaFX, **ne touchez plus au modèle**. Faites fonctionner JavaFX.

## 🟠 Orange — consolidation

Choisissez le manque qui vous empêche d'être vert :

- terminer le troisième module ;
- ajouter les tests essentiels ;
- terminer OpenAPI ;
- sécuriser un endpoint ;
- terminer le player.

## 🟢 Vert — approfondissement

Prenez **un atelier** :

- tests unitaires Spring ;
- couverture JaCoCo ;
- GitHub Actions ;
- Semgrep ;
- SonarQube.

---

# Si vous avez 2 heures ou plus

Découpez :

```text
15 min  → état des lieux
60 min  → objectif principal
30 min  → tests / nettoyage
15 min  → commit + documentation + point d'étape
```

Un travail du soir doit **réduire le risque du lendemain**, pas ajouter du risque.

---

# Lundi soir

## 🔴
Objectif unique :

> obtenir le premier flux BDD → API → JavaFX.

Ordre :
1. faire démarrer Spring ;
2. corriger BDD ;
3. obtenir un JSON par HTTP ;
4. appeler ce JSON depuis JavaFX ;
5. l'afficher.

**Ne faites ni upload, ni sécurité avancée, ni modules.**

## 🟠
JavaFX manque seulement :

- créer `ApiClient` ;
- tester l'URL avec curl avant JavaFX ;
- gérer le statut HTTP ;
- afficher une liste.

## 🟢
Vous pouvez :
- écrire votre premier test de service ;
- ajouter un 404 propre ;
- ajouter un second endpoint ;
- lire [l'atelier tests](../ateliers/01-tests-junit-spring.md).

---

# Mardi soir

## 🔴
Rattraper dans cet ordre :

1. stockage fichier ;
2. endpoint de contenu ;
3. JavaFX télécharge ;
4. preview/player ;
5. sécurité.

## 🟠
Finir la brique manquante : admin, sécurité ou recherche.

## 🟢
Commencer la qualité :
- 5 à 10 tests utiles ;
- JaCoCo ;
- CI plus riche ;
- premier scan Semgrep.

---

# Mercredi soir

## 🔴
Priorité :
1. troisième module principal ;
2. tests du module ;
3. stabilité du socle.

**Modules secondaires supprimés tant que vous êtes rouge.**

## 🟠
Terminer :
- module principal restant ;
- OpenAPI ;
- tests.

## 🟢
Vous pouvez :
- améliorer la CI ;
- ajouter JaCoCo ;
- faire Semgrep ;
- installer SonarQube localement ;
- tenter HATEOAS ;
- travailler cache/offline.

---

# Jeudi soir

## 🔴
Le but n'est plus d'ajouter des fonctionnalités.

1. corriger l'incident ;
2. ajouter le test de non-régression ;
3. rendre la CI verte ;
4. stabiliser.

## 🟠
Finir documentation et recette.

## 🟢
Approfondissements pertinents :
- quality gate ;
- SonarQube ;
- scan sécurité ;
- packaging JavaFX ;
- Testcontainers ;
- MinIO ;
- HATEOAS.

---

# La règle des trois niveaux

## Niveau 1 — Sauver la RP
Le chemin critique fonctionne.

## Niveau 2 — Rendre la RP solide
Tests, sécurité, erreurs, documentation, CI.

## Niveau 3 — Se différencier
Qualité avancée, analyse statique, SonarQube, Semgrep, packaging, observabilité.

**On ne passe jamais au niveau 3 lorsque le niveau 1 est cassé.**
