# Projet POO – Version 3 (POO-v3)

## 🎯 Objectif
Cette version améliore le projet en ajoutant l'import/export de données, la gestion des appariements et de l’historique des affectations.

## 📦 Contenu de cette version

- `Adolescent.java` : classe représentant un adolescent avec critères.
- `Critere.java` : énumération des critères et de leurs types.
- `File.java` : classe utilitaire pour l’import/export CSV.
- `Appariement.java` : classe gérant la constitution des paires hôte–invité.
- `Historique.java` : classe gérant la sérialisation et désérialisation des anciens appariements.
- `TestAppariement.java` : classe de test exécutant le système complet.
- `data_invites.csv` et `data_hotes.csv` : fichiers d’entrée.
- `export.csv` : exemple de résultat généré.

## ✅ Fonctionnalités

- Import CSV des adolescents
- Export CSV des appariements
- Calcul de score d’affinité basé sur :
  - Allergies
  - Régime alimentaire
  - Genre et préférences
  - Différence d’âge
  - Hobbies communs
- Historique pris en compte si existant (fichier binaire)

## 🔄 Changements

- Suppression de `est_hote` dans `Adolescent.java`
- Gestion séparée des hôtes et des invités via deux fichiers CSV
- Meilleure gestion des erreurs sur les critères invalides

## 🔖 Version

Tag Git : `POO-v3`