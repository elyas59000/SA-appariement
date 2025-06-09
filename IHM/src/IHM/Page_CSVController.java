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

@FXML
public void allerSelectionMenu(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Selectionmenu.fxml"));
    Parent root = fxmlLoader.load();

    // Accès au contrôleur pour appeler selectionAdo()
    SelectionMenuController controller = fxmlLoader.getController();
    controller.selectionAdo();

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("Menu de Sélection");
    stage.show();
}


    
    @FXML
    public void choisirFichierHotes(ActionEvent event)throws IOException {
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
    @FXML
    public void choisirFichierVisiteurs(ActionEvent event)throws IOException {
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
