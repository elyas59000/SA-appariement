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
import dev.FileAdo;

public class PageVuPaireController {

    @FXML
    private javafx.scene.control.Button boutonRetour;

    @FXML
    private ComboBox<Adolescent> comboBoxPaire; // ComboBox pour sélectionner une paire

    @FXML
    private TextArea textAreaHote; // Affiche infos hôte

    @FXML
    private TextArea textAreaVisiteur; // Affiche infos visiteur

    @FXML
    private Label labelScore; // Affiche le score

    @FXML
    private Label labelRhibitoire; // Affiche si la paire est rhédibitoire

    @FXML
    public void initialize() {
        // Remplit la ComboBox avec les visiteurs si appariements existe
        if (Main.appariements != null) {
            comboBoxPaire.getItems().clear();
            comboBoxPaire.getItems().addAll(Main.appariements.keySet());
            setupComboBox();
        }
    }

    private void setupComboBox() {
        // Affiche seulement le nom dans la ComboBox
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

        // Affiche le nom dans la liste déroulante
        comboBoxPaire.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Adolescent ado, boolean empty) {
                super.updateItem(ado, empty);
                setText((empty || ado == null) ? "" : ado.getNom());
            }
        });

        // Quand une paire est sélectionnée, affiche les infos
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
        // Affiche les infos des deux adolescents et le score
        if (hote != null && visiteur != null) {
            textAreaVisiteur.setText(visiteur.toString());
            textAreaHote.setText(hote.toString());

            boolean compatible = hote.estCompatible(visiteur);
            int score = hote.ScoreAffectation(visiteur,Main.score_initial, Main.point_ecart_age, Main.point_preference_genre, Main.point_hobby_commun);

            labelScore.setText(String.valueOf(score));
            labelRhibitoire.setText(compatible ? " Non" : " Oui");
        } else {
            textAreaVisiteur.clear();
            textAreaHote.clear();
            labelScore.setText("—");
            labelRhibitoire.setText("—");
        }
    }

    @FXML
    public void allerSelectionMenu(ActionEvent event) throws IOException {
        // Retour au menu de sélection
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
        // Supprime la paire sélectionnée
        Adolescent visiteur = comboBoxPaire.getSelectionModel().getSelectedItem();
        if (visiteur != null) {
            Main.appariements.remove(visiteur);
            comboBoxPaire.getItems().remove(visiteur);
            comboBoxPaire.getSelectionModel().clearSelection();
            textAreaHote.clear();
            textAreaVisiteur.clear();
            labelScore.setText("");
            labelRhibitoire.setText("");
        }
    }

    @FXML
    private void exporterPaires(ActionEvent event) {
        // Exporte les paires en CSV
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Exporter les paires");
        fileChooser.getExtensionFilters().add(
            new javafx.stage.FileChooser.ExtensionFilter("Fichiers CSV", "*.csv")
        );
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        java.io.File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            FileAdo.export(file.getAbsolutePath(), Main.appariements);
        }
    }

}
