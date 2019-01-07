package table;

import java.io.IOException;

import application.Main;
import application.PatientProfileController;
import entity.PatientProfile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProfileScene {

  public void initProfile(PatientProfile patientProfile) {
    PatientProfileController profileController = new PatientProfileController();
    profileController.setPatientProfile(patientProfile);

    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/patientProfile.fxml"));

    loader.setController(profileController);

    Stage stage = new Stage();
    try {
      stage.setScene(new Scene((AnchorPane)loader.load()));
    }
    catch (IOException e) {
    }

    stage.setResizable(false);
    stage.showAndWait();
  }
}
