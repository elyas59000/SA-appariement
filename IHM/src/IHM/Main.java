package IHM;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.HashSet;
import dev.Adolescent;



public class Main extends Application{

    public static HashSet<Adolescent> hotes;
    public static HashSet<Adolescent> visiteurs;
    public static HashMap<Adolescent, Adolescent> appariements;
    
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Page_CSV.fxml"));
        
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}