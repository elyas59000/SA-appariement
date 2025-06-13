package IHM;

import java.io.IOException;

import dev.Adolescent;
import dev.Appariement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import java.util.HashMap;
import java.util.HashSet;
import dev.Appariement;

public class SelectionMenuController implements ControlledScene {

    private AcceuilControleur mainApp;

    @FXML
    private ComboBox<Adolescent> hoteComboBox;

    @FXML
    private ComboBox<Adolescent> visiteurComboBox;

    @FXML
    private TextArea textAreaHote;

    @FXML
    private TextArea textAreaVisiteur;

        @FXML
    private TextArea textAreaResultat;

    @Override
    public void setMainApp(AcceuilControleur mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void allerVersPageVuePair(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Page_vu_paire.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Visualisation des Paires");
        stage.show();
    }

    @FXML
    public void allerVersExplicationCriteres(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ExplicationCriteres.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Explication des Critères");
        stage.show();
    }

    @FXML
    public void selectionAdo() {
        if (Main.hotes != null) {
            hoteComboBox.getItems().clear();
            hoteComboBox.getItems().addAll(Main.hotes);
            setupComboBox(hoteComboBox, textAreaHote);
        } else {
            System.out.println("⚠️ Liste des hôtes vide ou non initialisée");
        }

        if (Main.visiteurs != null) {
            visiteurComboBox.getItems().clear();
            visiteurComboBox.getItems().addAll(Main.visiteurs);
            setupComboBox(visiteurComboBox, textAreaVisiteur);
        } else {
            System.out.println("⚠️ Liste des visiteurs vide ou non initialisée");
        }
    }

    private void setupComboBox(ComboBox<Adolescent> comboBox, TextArea textArea) {
        // Affiche seulement le nom dans le champ sélectionné
        comboBox.setConverter(new StringConverter<Adolescent>() {
            @Override
            public String toString(Adolescent ado) {
                return ado == null ? "" : ado.getNom();
            }

            @Override
            public Adolescent fromString(String string) {
                return null; // non utilisé
            }
        });

        // Affiche aussi seulement le nom dans la liste déroulante
        comboBox.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Adolescent item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNom());
            }
        });

        // Lors de la sélection, afficher les détails dans la TextArea
        comboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                textArea.setText(newVal.toString());
            } else {
                textArea.clear();
            }
            // Appeler afficherResultatCompatibilite si les deux adolescents sont sélectionnés
            Adolescent hote = hoteComboBox.getSelectionModel().getSelectedItem();
            Adolescent visiteur = visiteurComboBox.getSelectionModel().getSelectedItem();
            if (hote != null && visiteur != null) {
                afficherResultatCompatibilite(hote, visiteur, textAreaResultat);
            } else {
                textAreaResultat.clear();
            }
        });
    }

   private void afficherResultatCompatibilite(Adolescent ado1, Adolescent ado2, TextArea textAreaResultat) {
    StringBuilder resultat = new StringBuilder();

    resultat.append("Résultat de la compatibilité entre ")
            .append(ado1.getPrenom()).append(" ").append(ado1.getNom())
            .append(" et ")
            .append(ado2.getPrenom()).append(" ").append(ado2.getNom())
            .append(" :\n\n");

    boolean compatible = ado1.estCompatible(ado2);
    int score = ado1.ScoreAffectation(ado2,Main.score_initial, Main.point_ecart_age, Main.point_preference_genre, Main.point_hobby_commun);
    if (compatible == true) {
        resultat.append("Compatibles ? ❌ NON\n");
    } else {
        resultat.append("Compatibles ? ❌ NON\n");
    }
    resultat.append("Score d'affectation : ").append(score).append("\n");

    if (!compatible) {
        resultat.append("⚠️ Une contrainte rédhibitoire empêche l'appariement.\n");
    }

    textAreaResultat.setText(resultat.toString());
}
@FXML
private void apparierAutomatiquement() {
    if (Main.visiteurs == null || Main.hotes == null || Main.visiteurs.isEmpty() || Main.hotes.isEmpty()) {
        textAreaResultat.setText("⚠️ Veuillez d'abord charger les fichiers CSV des hôtes et des invités.");
        return;
    }

    Main.appariements = Appariement.apparier(Main.visiteurs, Main.hotes); // Sans historique ici, mais tu peux l'ajouter

    StringBuilder sb = new StringBuilder();
    sb.append("🎯 Résultat des appariements :\n\n");

    for (Adolescent invite : Main.appariements.keySet()) {
        Adolescent hote = Main.appariements.get(invite);
        sb.append("- ").append(invite.getPrenom()).append(" ").append(invite.getNom()).append(" ⟷ ");
        if (hote != null) {
            sb.append(hote.getPrenom()).append(" ").append(hote.getNom()).append("\n");
        } else {
            sb.append("❌ Aucun hôte compatible\n");
        }
    }

    textAreaResultat.setText(sb.toString());
}
@FXML
private void apparierSelection() {
    Adolescent visiteur = visiteurComboBox.getSelectionModel().getSelectedItem();
    Adolescent hote = hoteComboBox.getSelectionModel().getSelectedItem();

    if (visiteur == null || hote == null) {
        textAreaResultat.setText("⚠️ Veuillez sélectionner un visiteur et un hôte.");
        return;
    }

    // Initialiser la map si nécessaire
    if (Main.appariements == null) {
        Main.appariements = new HashMap<>();
        if (!visiteur.estCompatible(hote)) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitle("Contrainte rédhibitoire");
            alert.setHeaderText(null);
            alert.setContentText("Attention il existe une contrainte rédhibitoire entre les 2");
            alert.showAndWait();
        }
    }

    // Forcer l'association
    Main.appariements.put(visiteur, hote);

    StringBuilder sb = new StringBuilder();
    sb.append("✅ Appariement forcé réalisé :\n")
      .append(visiteur.getPrenom()).append(" ").append(visiteur.getNom())
      .append(" ⟷ ")
      .append(hote.getPrenom()).append(" ").append(hote.getNom());

    textAreaResultat.setText(sb.toString());
}
@FXML
public void initialize() {
    selectionAdo();
}


}
