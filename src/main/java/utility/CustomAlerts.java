package utility;

import java.io.IOException;
import java.util.List;

import application.CustomAlertController;
import application.Main;
import entity.Drug;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomAlerts {

  private CustomAlerts() {
  }

  public static void showInternetErrorConnectionAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Connection eror");
    alert.setHeaderText("Error:");
    alert.setContentText("Could not connect to the internet");
    alert.showAndWait();
  }

  public static void showServerErrorConnectionAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Connection eror");
    alert.setHeaderText("Error:");
    alert.setContentText("Could not connect to server");
    alert.showAndWait();
  }

  public static void showServiceUnavailableAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Connection eror");
    alert.setHeaderText("Error:");
    alert.setContentText("Service unavailable");
    alert.showAndWait();
  }

  public static void showemptyPrescriptionListAlert() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Empty list");
    alert.setHeaderText("Prescription:");
    alert.setContentText("This patient does not have any prescription in this period");
    alert.showAndWait();
  }
  
  public static void showemptyHospitalListAlert() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Empty list");
    alert.setHeaderText("Hospitals:");
    alert.setContentText("There is no hospitals");
    alert.showAndWait();
  }

  public static void showEmptyFieldsAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Empty fields error:");
    alert.setContentText("Please complete all fields.");
    alert.showAndWait();
  }

  public static void showPrescriptionDoesNotExistAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Database error:");
    alert.setContentText("Prescription could not be returned. Plase try again.");
    alert.showAndWait();
  }

  public static void showDrugDoesNotExistAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Database eror");
    alert.setHeaderText("Error:");
    alert.setContentText("Medication does not exist");
    alert.showAndWait();
  }
  
  public static void showemptyDrugListAlert() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Empty list");
    alert.setHeaderText("Medicine:");
    alert.setContentText("There is no medicine with this name.");
    alert.showAndWait();
  }
  
  public static Drug showemptyDrugMultipleAlert(List<Drug> drugs) throws IOException {

    CustomAlertController dialogController = new CustomAlertController();
    dialogController.setDrugs(drugs);

    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/customAlert.fxml"));

    loader.setController(dialogController);

    Stage stage = new Stage();
    try {
      stage.setScene(new Scene((AnchorPane)loader.load()));
    }
    catch (IOException e) {
    }

    stage.setResizable(false);
    stage.showAndWait();

    Drug result = dialogController.getDrugResult();
    
    if(result == null)
    {
      throw new IOException();
    }
    
    return result;
  }

  public static void showPatientDoesNotExistAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Database eror");
    alert.setHeaderText("Error:");
    alert.setContentText("Patient does not exist");
    alert.showAndWait();
  }
}
