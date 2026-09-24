# SonarQube local — environnement pédagogique

Ce compose sert à **expérimenter** SonarQube Community Build.

## Démarrer

```bash
docker compose up -d
```

Puis ouvrir :

```text
http://localhost:9000
```

## Arrêter

```bash
docker compose down
```

N'utilisez pas `down -v` si vous souhaitez conserver les analyses.

## Ressources

SonarQube utilise Elasticsearch. Prévoyez plusieurs Go de RAM disponibles.

Sur Linux, si Elasticsearch refuse de démarrer, consultez les prérequis SonarQube officiels, notamment les limites système telles que `vm.max_map_count`.

## Image

Le fichier utilise `sonarqube:latest` pour faciliter un atelier ponctuel.

Pour un environnement durable, **épinglez une version Community Build testée** plutôt que de dépendre de `latest`.

## Sécurité

Ce compose est destiné à un poste de développement local :
- mots de passe simples ;
- port directement exposé ;
- pas de TLS.

Ne le publiez pas tel quel sur Internet.
