# Atelier 4 — SonarQube Community Build

**Durée indicative : 60 à 120 minutes la première fois**

Cet atelier s'adresse aux étudiants **verts**. N'installez pas SonarQube pour éviter de terminer votre application.

---

# 1. Pourquoi un serveur ?

Contrairement à un simple rapport local, SonarQube centralise l'analyse et son historique dans une interface Web.

Vous pouvez y explorer :
- problèmes ;
- maintenabilité ;
- duplications ;
- couverture ;
- règles ;
- quality gate.

---

# 2. Démarrer localement

Le dépôt contient :

`outils/sonarqube/docker-compose.yml`

Lancer :

```bash
docker compose -f outils/sonarqube/docker-compose.yml up -d
```

Puis :

```text
http://localhost:9000
```

Pour un essai local, les identifiants initiaux habituels sont indiqués par SonarQube au démarrage/documentation. Changez le mot de passe dès la première connexion.

**Attention :** SonarQube utilise Elasticsearch et demande des ressources significatives. La documentation officielle recommande environ 4 Go de RAM pour une petite installation.

---

# 3. Créer un projet et un token

Dans l'interface :
1. créer le projet ;
2. créer un token ;
3. ne jamais committer le token.

Placez-le dans une variable d'environnement :

```bash
export SONAR_TOKEN="..."
export SONAR_HOST_URL="http://localhost:9000"
```

PowerShell :

```powershell
$env:SONAR_TOKEN="..."
$env:SONAR_HOST_URL="http://localhost:9000"
```

---

# 4. Analyser le projet Maven

Pour un projet Maven, Sonar recommande le SonarScanner for Maven.

Exemple :

```bash
mvn clean verify \
  org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
  -Dsonar.projectKey=mon-projet
```

Puis ouvrez le dashboard.

---

# 5. Que regarder ?

Ne cherchez pas à « avoir A partout ».

Choisissez trois éléments :
- une duplication ;
- un problème de maintenabilité ;
- une zone peu testée.

Pour chacun :
1. comprendre ;
2. décider ;
3. corriger ou justifier ;
4. relancer.

---

# 6. GitHub Actions

Le fichier :

`exemples/github-actions/sonarqube.yml`

montre le principe.

Secrets :
- `SONAR_TOKEN`
- `SONAR_HOST_URL`

**Important :** un runner GitHub hébergé ne peut pas joindre le `localhost` de votre ordinateur. Pour analyser depuis GitHub Actions, votre serveur SonarQube doit être joignable depuis le runner, ou vous devez utiliser un runner auto-hébergé adapté.

---

# 7. Quality Gate

Bonus intéressant :

> refuser une livraison si certains critères définis ne sont pas respectés.

Mais un quality gate doit être pertinent. Ne transformez pas l'outil en compteur arbitraire.

## Terminé
- [ ] SonarQube démarre ;
- [ ] projet créé ;
- [ ] analyse Maven ;
- [ ] couverture visible ;
- [ ] trois findings compris ;
- [ ] amélioration documentée.
