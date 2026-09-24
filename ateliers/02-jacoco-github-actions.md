# Atelier 2 — JaCoCo + GitHub Actions

**Durée indicative : 45 à 75 minutes**

## Objectif

Obtenir à chaque push :
- compilation ;
- tests ;
- rapport de couverture ;
- statut vert/rouge.

---

# 1. JaCoCo en local

Le starter est configuré avec le plugin JaCoCo.

Exécutez :

```bash
mvn verify
```

Ouvrez :

```text
backend/target/site/jacoco/index.html
```

Questions à vous poser :
- quel service important est rouge ?
- quelles branches métier ne sont jamais exécutées ?
- ai-je testé uniquement le cas heureux ?

**Ne cherchez pas 100 % mécaniquement.**

---

# 2. GitHub Actions

Copiez/adaptez :

`exemples/github-actions/ci-avance.yml`

dans :

`.github/workflows/ci.yml`

Puis poussez.

Dans GitHub → Actions, vérifiez :
- build ;
- tests ;
- rapport JaCoCo en artifact.

---

# 3. Faire volontairement échouer

Ajoutez temporairement un test faux.

Poussez.

Constater :
- workflow rouge ;
- étape qui échoue ;
- logs.

Corrigez puis repoussez.

Vous venez de vérifier que votre CI **protège réellement** le dépôt.

---

# 4. Bonus : seuil de couverture

JaCoCo possède un objectif `check` permettant de bloquer le build sous un seuil.

Ne choisissez pas un seuil arbitraire élevé.

Commencez éventuellement par protéger uniquement des packages métier importants.

---

# 5. Pourquoi c'est professionnel ?

La CI rend visible que :
- votre build est reproductible ;
- vos tests s'exécutent ailleurs que sur votre machine ;
- une régression peut bloquer une fusion.

GitHub Actions permet aussi de mettre en cache Maven et de stocker des artifacts issus du workflow.

## Terminé
- [ ] `mvn verify` local ;
- [ ] rapport JaCoCo ;
- [ ] GitHub Action verte ;
- [ ] un échec volontaire observé ;
- [ ] rapport récupérable.
