# 06 — JavaFX : véritable client lourd

Le client JavaFX est le **client métier principal**, pas une démonstration de formulaires.

## Obligatoire
- écran de connexion ;
- navigation ;
- catalogue/liste ;
- recherche et filtres ;
- détail d'une ressource ;
- player ou preview adapté ;
- états loading/empty/error ;
- appels asynchrones ;
- gestion des erreurs HTTP ;
- URL serveur configurable.

## Architecture recommandée
```text
View/FXML
 ↓
Controller JavaFX
 ↓
Client Service
 ↓
HttpClient
 ↓
REST API
```

## Asynchronisme
Une requête réseau ne doit pas bloquer le JavaFX Application Thread.

Utilisez selon votre architecture :
- `Task` ;
- `Service` JavaFX ;
- `HttpClient.sendAsync`.

## Player
- `ImageView` pour images ;
- `MediaPlayer` / `MediaView` pour audio/vidéo ;
- ouverture externe ou preview adaptée pour PDF.

## Erreurs à gérer
- serveur inaccessible ;
- 401 ;
- 403 ;
- 404 ;
- média non lisible ;
- téléchargement interrompu ;
- réponse JSON invalide.
