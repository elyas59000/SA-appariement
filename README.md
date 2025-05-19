# Projet POO – Version 2 (POO-v2)

## 🎯 Objectif
Amélioration du système de gestion des adolescents dans le cadre d’un échange linguistique. Cette version intègre la validation des critères via des exceptions ainsi que des règles de compatibilité spécifiques à certains pays.

## 📦 Contenu de cette version

- `Adolescent.java` : Refactorisée pour stocker tous les critères sous forme de `Map<Critere, String>`, avec validation intégrée.
- `Critere.java` : Enumération enrichie avec type de critère (booléen ou texte).
- `CritereInvalideException.java` : Exception personnalisée levée lors de l’ajout de critères invalides.
- `TestAdolescent.java` : Classe de test mise à jour pour inclure des cas de validation et des règles pays.
- `rapport_POO-v2.pdf` : Rapport complet des évolutions (refactoring, exceptions, règles pays).

## ✅ Fonctionnalités implémentées

- **Validation des critères** selon leur type (`B`, `T`) via la méthode `validerCritere(...)` :
  - Booléens : doivent valoir `yes` ou `no`
  - Textes : ne doivent pas être vides
- **Refactoring du modèle** :
  - Passage de `Map<Critere, Object>` → `Map<Critere, String>`
  - Uniformisation des traitements des critères
- **Règle de compatibilité spécifique à la France** :
  - Obligation d’avoir au moins **un hobby en commun** si l’un des deux adolescents est français
- Mise à jour de `estCompatible(...)` pour intégrer toutes les règles précédentes

## 🧪 Tests

- Cas d’insertion de critères valides et invalides
- Compatibilité entre adolescents français et non-français
- Affichage console des exceptions levées et des décisions d’appariement

## 🔖 Version

Tag Git : `POO-v2`

---
