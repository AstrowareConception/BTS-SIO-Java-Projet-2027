# 05 — Spring Boot : organisation attendue

## Controller
- reçoit HTTP ;
- valide la forme ;
- appelle le service ;
- choisit le statut ;
- retourne DTO/réponse.

## Service
- règles applicatives ;
- transactions ;
- coordination.

## Repository
- données ;
- requêtes ;
- pagination/recherche.

## StorageService
- clé de stockage ;
- écriture/lecture ;
- suppression contrôlée ;
- checksum ;
- validations techniques.

## DTO
Ne renvoyez pas aveuglément les entités JPA. Les DTO contrôlent le contrat, masquent les données sensibles et évitent les graphes JSON incontrôlés.

## Validation
```java
public record CreateAssetRequest(
    @NotBlank String title,
    @Size(max = 500) String description
) {}
```

## Transactions
Utilisez `@Transactional` quand plusieurs opérations forment une unité cohérente.

## Erreurs
Centralisez leur traduction HTTP avec `@RestControllerAdvice`.
