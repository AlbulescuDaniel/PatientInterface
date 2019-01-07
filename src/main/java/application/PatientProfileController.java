package application;

import java.time.Instant;
import java.time.ZoneId;

import entity.PatientProfile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PatientProfileController {

  @FXML
  private TextArea firstNameProfile;

  @FXML
  private TextArea lastNameProfile;

  @FXML
  private TextArea userNameProfile;

  @FXML
  private TextArea specialityProfile;

  @FXML
  private TextArea emailProfile;

  @FXML
  private TextArea phoneProfile;

  @FXML
  private TextArea countryProfile;

  @FXML
  private TextArea cityProfile;

  @FXML
  private TextArea regionProfile;

  @FXML
  private TextArea postalCodeProfile;

  @FXML
  private TextArea streetProfile;

  @FXML
  private TextArea streetNumberProfile;

  @FXML
  private TextArea hospitalProfile;

  @FXML
  private TextArea hospitalEmailProfile;

  @FXML
  private Button closeButton;

  private PatientProfile patientProfile;

  public void setPatientProfile(PatientProfile patientProfile) {
    this.patientProfile = patientProfile;
  }

  @FXML
  private void initialize() {
    firstNameProfile.setText(patientProfile.getFirstName());
    lastNameProfile.setText(patientProfile.getLastName());
    userNameProfile.setText(patientProfile.getUserName());
    specialityProfile.setText(Instant.ofEpochMilli(patientProfile.getBirthDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate().toString());
    emailProfile.setText(patientProfile.getEmail());
    phoneProfile.setText(patientProfile.getPhoneNumber());
    countryProfile.setText(patientProfile.getAddressDTO().getCountryName());
    cityProfile.setText(patientProfile.getAddressDTO().getCity());
    regionProfile.setText(patientProfile.getAddressDTO().getRegion());
    postalCodeProfile.setText(patientProfile.getAddressDTO().getPostalCode());
    streetProfile.setText(patientProfile.getAddressDTO().getStreet());
    streetNumberProfile.setText(patientProfile.getAddressDTO().getStreetNumber());

    closeButton.setOnAction(event -> {
      Stage stage = (Stage)closeButton.getScene().getWindow();
      stage.close();
    });
  }
}
