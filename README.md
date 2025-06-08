# Projet POO – Version 4 (POO-v4)

## 🎯 Objectif
Cette version améliore le projet en ajoutant l'import/export de données, la gestion des appariements et de l’historique des affectations.

## 📦 Contenu de cette version

- `Adolescent.java` : classe représentant un adolescent avec critères.
- `Critere.java` : énumération des critères et de leurs types.
- `File.java` : classe utilitaire pour l’import/export CSV.
- `Appariement.java` : classe gérant la constitution des paires hôte–invité.
- `Historique.java` : classe gérant la sérialisation et désérialisation des anciens appariements.
- **(New)** `Pretraitement.java` : classe permettant de supprimer les adolescent incohérent.

- `TestAppariement.java` : classe de test exécutant le système complet.
- `data_invites.csv` et `data_hotes.csv` : fichiers d’entrée.
- `export.csv` : exemple de résultat généré.

## ✅ Fonctionnalités ajoutés

Méthode nettoyerAdolescent() ajouté dans une nouvelle classe Pretraitement qui en fixant une limite d'incohérence
maximale permet de supprimer un adolescent dont le nombre d'incohérence dépasse cette limite.

Cette méthode ce base sur le fait que l'adolescent est allergique aux animaux et posséde un animal et sur la méthode valierCritere() de la classe adolescent






## 🔖 Version

Tag Git : `POO-v4`