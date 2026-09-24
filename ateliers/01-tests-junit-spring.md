# Atelier 1 — JUnit, Mockito et tests Spring

**Durée indicative : 45 à 90 minutes**

Objectif : passer de « je teste en cliquant » à une suite automatisée utile.

---

## Étape 1 — Un test de service

Choisissez une règle importante.

Exemple :

> Un titre vide doit être refusé.

Écrivez :
- Arrange : données ;
- Act : appel ;
- Assert : exception/résultat.

Puis ajoutez un cas nominal.

---

## Étape 2 — Mock d'une dépendance

Un service ne doit pas nécessiter PostgreSQL pour chaque test unitaire.

Mockez :
- repository ;
- StorageService ;
- autre service.

Vérifiez :
- résultat ;
- et éventuellement interaction importante.

---

## Étape 3 — Tester une erreur métier

Exemples :
- licence expirée ;
- quota dépassé ;
- média archivé ;
- doublon SHA ;
- accès à une ressource d'un autre utilisateur.

Le test doit raconter la règle.

---

## Étape 4 — Test Web Spring Boot 4

Spring Boot 4 fournit `@WebMvcTest` pour isoler la couche MVC. Les collaborateurs peuvent être injectés avec `@MockitoBean`.

À vérifier sur un endpoint :
- 200 ;
- 404 ;
- JSON ;
- validation.

Puis testez :
- 401 ;
- 403.

---

## Étape 5 — Test de non-régression

Choisissez un bug déjà rencontré.

1. recréez-le par un test ;
2. vérifiez que le test serait rouge sans correctif ;
3. appliquez/corrigez ;
4. conservez le test.

---

## Définition de terminé

- [ ] au moins 3 comportements métier testés ;
- [ ] un cas d'erreur ;
- [ ] un endpoint REST testé ;
- [ ] un droit testé ;
- [ ] `mvn verify` vert.

## Pour aller plus loin
Passez à l'[atelier JaCoCo + Actions](02-jacoco-github-actions.md).
