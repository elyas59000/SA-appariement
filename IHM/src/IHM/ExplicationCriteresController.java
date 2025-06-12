package IHM;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.event.ActionEvent;

public class ExplicationCriteresController {

    @FXML
    private javafx.scene.control.Button boutonRetour; // Bouton retour

    // Changement de scène vers le menu de sélection
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
    private javafx.scene.control.Label labelScoreInitial;
    @FXML
    private javafx.scene.control.Label labelEcartAge;
    @FXML
    private javafx.scene.control.Label labelPreferenceGenre;
    @FXML
    private javafx.scene.control.Label labelHobbyCommun;

    // Initialisation des labels avec les valeurs de Main
    @FXML
    public void initialize() {
        labelScoreInitial.setText(String.valueOf(Main.score_initial));
        labelEcartAge.setText(String.valueOf(Main.point_ecart_age));
        labelPreferenceGenre.setText(String.valueOf(Main.point_preference_genre));
        labelHobbyCommun.setText(String.valueOf(Main.point_hobby_commun));
    }

    // Incrémentation/décrémentation du score initial
    @FXML
    private void augmenterScoreInitial() {
        Main.score_initial++;
        labelScoreInitial.setText(String.valueOf(Main.score_initial));
    }

    @FXML
    private void diminuerScoreInitial() {
        Main.score_initial--;
        labelScoreInitial.setText(String.valueOf(Main.score_initial));
    }

    // Incrémentation/décrémentation de l'écart d'âge
    @FXML
    private void augmenterEcartAge() {
        Main.point_ecart_age++;
        labelEcartAge.setText(String.valueOf(Main.point_ecart_age));
    }

    @FXML
    private void diminuerEcartAge() {
        Main.point_ecart_age--;
        labelEcartAge.setText(String.valueOf(Main.point_ecart_age));
    }

    // Incrémentation/décrémentation de la préférence de genre
    @FXML
    private void augmenterPreferenceGenre() {
        Main.point_preference_genre++;
        labelPreferenceGenre.setText(String.valueOf(Main.point_preference_genre));
    }

    @FXML
    private void diminuerPreferenceGenre() {
        Main.point_preference_genre--;
        labelPreferenceGenre.setText(String.valueOf(Main.point_preference_genre));
    }

    // Incrémentation/décrémentation du hobby commun
    @FXML
    private void augmenterHobbyCommun() {
        Main.point_hobby_commun++;
        labelHobbyCommun.setText(String.valueOf(Main.point_hobby_commun));
    }

    @FXML
    private void diminuerHobbyCommun() {
        Main.point_hobby_commun--;
        labelHobbyCommun.setText(String.valueOf(Main.point_hobby_commun));
    }

}
