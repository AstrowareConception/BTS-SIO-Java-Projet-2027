# Tableau de bord enseignant — Chronologie de la semaine

Utiliser cette page pour repérer rapidement les étudiants qui dérivent du chemin critique.

## Codes

- 🟢 **Vert** : rythme nominal.
- 🟠 **Orange** : retard rattrapable sans réduire le socle.
- 🔴 **Rouge** : risque important sur la RP ; arbitrage immédiat.

---

## Lundi soir

### 🟢
- Spring démarre ;
- BDD connectée ;
- 1 entité JPA ;
- 1 endpoint ;
- JavaFX affiche une donnée distante.

### 🟠
- API fonctionne ;
- JavaFX pas encore branché.

### 🔴
- backend/BDD/API non fonctionnels.

### Action enseignant
En rouge : interdire le travail sur les modules et reprendre le flux vertical mardi matin.

---

## Mardi midi

### 🟢
- auth en place ;
- upload opérationnel ou presque ;
- rôles identifiés.

### 🟠
- auth ou upload incomplet.

### 🔴
- lundi non rattrapé.

---

## Mardi soir

### 🟢
- upload + métadonnées + stockage ;
- JavaFX recherche/consulte ;
- player/preview ;
- droits ;
- admin minimal.

### 🟠
- une des briques secondaires manque.

### 🔴
- fichier distant non consultable depuis JavaFX.

### Action enseignant
En rouge : supprimer tout bonus et figer le périmètre métier avant mercredi.

---

## Mercredi midi — contrôle déterminant

### 🟢
- socle complet ;
- 1 à 2 modules principaux terminés ;
- API propre.

### 🟠
- socle complet ;
- modules en retard.

### 🔴
- player, stockage ou sécurité encore cassé.

### Action enseignant
En rouge : réduire immédiatement le scope secondaire et faire terminer un seul module vertical à la fois.

---

## Mercredi soir

### 🟢
- 3 modules principaux ;
- OpenAPI ;
- CI ;
- évolution analysée.

### 🟠
- 2 modules + socle complet.

### 🔴
- moins de 2 modules ou socle incomplet.

### Action enseignant
En orange : jeudi matin réservé au troisième module avant évolution.
En rouge : modules secondaires supprimés d'office.

---

## Jeudi midi

### 🟢
Évolution terminée et tests verts.

### 🟠
Évolution presque terminée.

### 🔴
Évolution pas commencée ou gros bug de régression.

---

## Jeudi soir

### 🟢
- évolution ;
- incident ;
- test de non-régression ;
- application stable.

### 🟠
Socle stable, aucun module secondaire : acceptable.

### 🔴
Un module principal ou incident reste bloquant.

### Action enseignant
Vendredi = priorité absolue stabilité, aucune nouveauté.

---

## Vendredi midi

### 🟢
Feature freeze déjà effectué, recette en cours.

### 🟠
Une correction fonctionnelle légère reste à faire.

### 🔴
Fonctionnalités majeures encore développées.

---

# Vue synthétique des 6 étudiants

| Étudiant | Lundi soir | Mardi soir | Mercredi midi | Mercredi soir | Jeudi soir | Vendredi |
|---|---|---|---|---|---|---|
| 1 | | | | | | |
| 2 | | | | | | |
| 3 | | | | | | |
| 4 | | | | | | |
| 5 | | | | | | |
| 6 | | | | | | |

## Principe d'arbitrage

Le périmètre à protéger est toujours :

**architecture → client/serveur → sécurité → fichiers → modules principaux → tests → maintenance → livraison**.

Les modules secondaires et bonus sont les premières variables d'ajustement.
