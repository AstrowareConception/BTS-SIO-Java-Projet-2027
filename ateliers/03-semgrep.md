# Atelier 3 — Semgrep CE

**Durée indicative : 30 à 60 minutes**

Semgrep effectue une analyse statique à partir de règles.

Il ne remplace :
- ni les tests ;
- ni une revue ;
- ni SonarQube.

Il apporte un angle supplémentaire : **chercher des motifs suspects directement dans le code**.

---

# 1. Tester sans installation

Avec Docker :

```bash
docker run --rm -v "${PWD}:/src" semgrep/semgrep \
  semgrep scan --config auto /src
```

Lisez les résultats avant de « corriger pour faire disparaître le rouge ».

Question :
- est-ce un vrai problème dans votre contexte ?

---

# 2. GitHub Actions

Copiez :

`exemples/github-actions/semgrep.yml`

vers :

`.github/workflows/semgrep.yml`

La version Community Edition peut utiliser :

```bash
semgrep scan --config auto
```

sans compte Semgrep.

---

# 3. Comprendre un finding

Pour chaque résultat intéressant, documentez :

1. règle ;
2. code concerné ;
3. risque ;
4. vrai positif / faux positif ;
5. correction ou justification.

---

# 4. Challenge

Introduisez **sur une branche temporaire** un exemple volontairement mauvais issu d'un exercice, puis vérifiez si votre règle/scan le détecte.

Ne conservez pas le code vulnérable sur `main`.

---

# 5. Aller plus loin

Semgrep permet :
- règles personnalisées ;
- scans CI ;
- intégration plateforme ;
- analyse diff-aware selon configuration.

## Terminé
- [ ] scan local ;
- [ ] scan CI ;
- [ ] au moins un finding analysé ;
- [ ] conclusion documentée.
