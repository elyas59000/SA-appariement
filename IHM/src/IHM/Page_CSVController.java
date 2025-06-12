package IHM;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.io.File;
import dev.FileAdo;

public class Page_CSVController {

    @FXML
    private javafx.scene.control.Button boutonRetour;

    // Bouton pour aller au menu de sélection
    @FXML
    public void allerSelectionMenu(ActionEvent event) throws IOException {
        // Vérifie si les fichiers sont chargés
        if (Main.hotes == null || Main.hotes.isEmpty() || Main.visiteurs == null || Main.visiteurs.isEmpty()) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitle("Fichier manquant");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner les fichiers CSV pour les hôtes et les visiteurs avant de continuer.");
            alert.showAndWait();
            return;
        }

        // Charge la page suivante
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Selectionmenu.fxml"));
        Parent root = fxmlLoader.load();

        // Appelle une méthode du contrôleur suivant
        SelectionMenuController controller = fxmlLoader.getController();
        controller.selectionAdo();

        // Affiche la nouvelle scène
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu de Sélection");
        stage.show();
    }

    // Bouton pour choisir le fichier CSV des hôtes
    @FXML
    public void choisirFichierHotes(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner le fichier CSV des hôtes");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv")
        );

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        File fichier = fileChooser.showOpenDialog(stage);
        if (fichier != null) {
            Main.hotes = FileAdo.importListAdo(fichier.getAbsolutePath());
        }
        System.out.println("✅ Hôtes chargés : " + Main.hotes.size());
    }

    // Bouton pour choisir le fichier CSV des visiteurs
    @FXML
    public void choisirFichierVisiteurs(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner le fichier CSV des visiteurs");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv")
        );

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        File fichier = fileChooser.showOpenDialog(stage);
        if (fichier != null) {
            Main.visiteurs = FileAdo.importListAdo(fichier.getAbsolutePath());
        }
        System.out.println("✅ Visiteurs chargés : " + Main.visiteurs.size());
    }
}
