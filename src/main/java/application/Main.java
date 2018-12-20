package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utility.JWTInfo;

public class Main extends Application {

  private static Stage primaryStage;

  @Override
  public void start(Stage primaryStage) {
    Main.primaryStage = primaryStage;
    Main.primaryStage.setWidth(1200);
    Main.primaryStage.setMinWidth(1200);
    Main.primaryStage.setHeight(800);
    Main.primaryStage.setMinHeight(800);
    Main.primaryStage.setResizable(true);
    showClientView();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static void showClientView() {
    try {
      Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("/fxml/PatientLogin.fxml")));
      scene.getStylesheets().add(Main.class.getResource("/css/application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void showMainPatientView(JWTInfo parsedJWT) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(Main.class.getResource("/fxml/PatientPrincipalScene.fxml"));
    
    ClientMainController controller = new ClientMainController();
    controller.setToken(parsedJWT);
    
    fxmlLoader.setController(controller);

    GridPane pane = fxmlLoader.load();

    Scene scene = new Scene(pane, 1200, 800);
    scene.getStylesheets().add(Main.class.getResource("/css/application.css").toExternalForm());

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
