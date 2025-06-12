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

    public static int score_initial = 10;
    public static int point_ecart_age = 2;
    public static int point_preference_genre = 2;
    public static int point_hobby_commun = 1;

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