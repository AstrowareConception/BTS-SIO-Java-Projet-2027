# 23 — CI & qualité : progression par niveaux

La CI doit évoluer avec votre maîtrise.

---

# Niveau 0 — Local

Vous savez exécuter :

```bash
mvn verify
```

et obtenir un build vert.

Si ce niveau n'est pas acquis, ne passez pas au suivant.

---

# Niveau 1 — GitHub Actions

À chaque push / pull request :

```text
checkout
→ JDK
→ cache Maven
→ mvn verify
```

GitHub fournit un guide officiel pour construire et tester un projet Maven. Le cache Maven peut être géré directement par `setup-java`.

Voir l'[atelier GitHub Actions + JaCoCo](../ateliers/02-jacoco-github-actions.md).

---

# Niveau 2 — Couverture JaCoCo

La couverture n'est **pas une note de qualité**.

Elle répond à :

> « Quelle partie de mon code a été exécutée pendant mes tests ? »

Commande :

```bash
mvn verify
```

Puis :

```text
backend/target/site/jacoco/index.html
```

Analysez surtout les services métier non couverts.

---

# Niveau 3 — Analyse statique sécurité : Semgrep

Semgrep cherche des motifs de code problématiques sans exécuter l'application.

Pour un premier essai :

```bash
docker run --rm -v "${PWD}:/src" semgrep/semgrep \
  semgrep scan --config auto /src
```

Puis intégration GitHub Actions.

Voir [Atelier Semgrep](../ateliers/03-semgrep.md).

---

# Niveau 4 — Qualité centralisée : SonarQube

SonarQube centralise notamment :

- bugs potentiels ;
- vulnérabilités / hotspots selon analyse ;
- duplications ;
- maintenabilité ;
- couverture importée ;
- dette technique / règles de qualité.

Vous pouvez installer **SonarQube Community Build** localement avec Docker, analyser le projet Maven puis consulter le dashboard.

Voir [Atelier SonarQube](../ateliers/04-sonarqube.md).

---

# Niveau 5 — Pipeline de très bon niveau

Exemple :

```text
Commit / PR
   ↓
Compilation
   ↓
Tests unitaires
   ↓
Tests Spring
   ↓
JaCoCo
   ↓
Semgrep
   ↓
SonarQube
   ↓
Package
   ↓
Artifact
```

Bonus possibles :

- Testcontainers ;
- analyse des dépendances ;
- image Docker ;
- artefacts ;
- quality gate ;
- déploiement test.

---

# Badges personnels

Vous pouvez vous fixer ces objectifs :

### 🥉 Bronze
`mvn verify` vert + tests unitaires.

### 🥈 Argent
GitHub Actions + tests REST + JaCoCo.

### 🥇 Or
Semgrep + sécurité + couverture analysée.

### 💎 Platine
SonarQube + quality gate + pipeline complet.

Ces badges ne remplacent pas les exigences du projet : ils servent à vous donner un **prochain défi concret** lorsque vous êtes à jour.
