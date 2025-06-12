package IHM;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Contrôleur principal de l'application, gère le changement de scènes
public class AcceuilControleur extends Application {

    private Stage primaryStage; // Fenêtre principale

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        showSelectionMenu();  // Affiche la page de démarrage
        stage.show();
    }

    // Affiche le menu principal
    public void showSelectionMenu() throws IOException {
        loadScene("Selectionmenu.fxml", "Menu Principal");
    }

    // Affiche la page de visualisation des paires
    public void showPageVuPaire() throws IOException {
        loadScene("Page_vu_paire.fxml", "Visualisation des Paires");
    }

    // Affiche la page d'explication des critères
    public void showVisuelCompatibilite() throws IOException {
        loadScene("ExplicationCritères.fxml", "Explication des Critères");
    }

    // Charge et affiche une scène à partir d'un fichier FXML
    private void loadScene(String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource(fxmlFile);
        if (fxmlFileUrl == null) {
            System.out.println("Impossible de charger le fichier FXML : " + fxmlFile);
            System.exit(-1);
        }
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);

        // Injection de l'application principale dans le contrôleur, si besoin
        Object controller = loader.getController();
        if (controller instanceof ControlledScene) {
            ((ControlledScene) controller).setMainApp(this);
        }
    }

}
