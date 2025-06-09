package IHM;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AcceuilControleur extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        showSelectionMenu();  // page de démarrage
        stage.show();
    }

    public void showSelectionMenu() throws IOException {
        loadScene("Selectionmenu.fxml", "Menu Principal");
    }

    public void showPageVuPaire() throws IOException {
        loadScene("Page_vu_paire.fxml", "Visualisation des Paires");
    }

    public void showVisuelCompatibilite() throws IOException {
        loadScene("ExplicationCritères.fxml", "Explication des Critères");
    }

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

        // Injection de l'application principale dans le controller, si besoin
        Object controller = loader.getController();
        if (controller instanceof ControlledScene) {
            ((ControlledScene) controller).setMainApp(this);
        }
    }

}
