package IHM;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.StringConverter;
import javafx.scene.control.ListCell;

import java.io.IOException;

import dev.Adolescent;


public class PageVuPaireController {

    @FXML
    private javafx.scene.control.Button boutonRetour;

    @FXML
    private ComboBox<Adolescent> comboBoxPaire;

    @FXML
    private TextArea textAreaHote;

    @FXML
    private TextArea textAreaVisiteur;

    @FXML
    private Label labelScore;

    @FXML
    private Label labelRhibitoire;

    @FXML
    public void initialize() {
        if (Main.appariements != null) {
            comboBoxPaire.getItems().clear();
            comboBoxPaire.getItems().addAll(Main.appariements.keySet()); // visiteurs
            setupComboBox();
        }
    }

    private void setupComboBox() {
        // Affichage du nom uniquement
        comboBoxPaire.setConverter(new StringConverter<>() {
            @Override
            public String toString(Adolescent ado) {
                return (ado == null) ? "" : ado.getNom();
            }

            @Override
            public Adolescent fromString(String string) {
                return null;
            }
        });

        // Affichage dans la liste déroulante
        comboBoxPaire.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Adolescent ado, boolean empty) {
                super.updateItem(ado, empty);
                setText((empty || ado == null) ? "" : ado.getNom());
            }
        });

        // Gestion de la sélection
        comboBoxPaire.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Adolescent visiteur = newVal;
                Adolescent hote = Main.appariements.get(visiteur);
                afficherInfos(visiteur, hote);
            } else {
                textAreaHote.clear();
                textAreaVisiteur.clear();
                labelScore.setText("");
                labelRhibitoire.setText("");
            }
        });
    }

    private void afficherInfos(Adolescent visiteur, Adolescent hote) {
        if (hote != null && visiteur != null) {
            textAreaVisiteur.setText(visiteur.toString());
            textAreaHote.setText(hote.toString());

            boolean compatible = hote.estCompatible(visiteur);
            int score = hote.ScoreAffectation(visiteur);

            labelScore.setText(String.valueOf(score));
            labelRhibitoire.setText(compatible ? "✅ Non" : "❌ Oui");
        } else {
            textAreaVisiteur.clear();
            textAreaHote.clear();
            labelScore.setText("—");
            labelRhibitoire.setText("—");
        }
    }

    @FXML
    public void allerSelectionMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Selectionmenu.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu de Sélection");
        stage.show();
    }

    @FXML
private void defairePaire(ActionEvent event) {
    Adolescent visiteur = comboBoxPaire.getSelectionModel().getSelectedItem();
    if (visiteur != null) {
        // Supprimer la paire de la Map
        Main.appariements.remove(visiteur);
        
        // Retirer le visiteur de la ComboBox
        comboBoxPaire.getItems().remove(visiteur);
        
        // Nettoyer affichages
        comboBoxPaire.getSelectionModel().clearSelection();
        textAreaHote.clear();
        textAreaVisiteur.clear();
        labelScore.setText("");
        labelRhibitoire.setText("");
    }
}

}
