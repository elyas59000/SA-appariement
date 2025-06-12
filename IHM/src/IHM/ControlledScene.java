package IHM;

/**
 * Interface permettant de lier une scène contrôlée à son contrôleur principal.
 * Fournit une méthode pour définir le contrôleur principal de l'application.
 */
public interface ControlledScene {
    void setMainApp(AcceuilControleur mainApp);
}