package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import entity.Drug;
import entity.PatientProfile;
import entity.Prescription;
import entity.PrescriptionDetails;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import request.DrugProspectumRequest;
import request.PatientProfileRequest;
import request.PrescriptionDetailsRequest;
import request.PrescriptionsTabRequest;
import table.CreatePrescriptionTableFormat;
import table.PatientPrescriptionsTableControl;
import table.PatientPrescriptionsTableFormat;
import table.PrescriptionDetailsTableControl;
import table.ProfileScene;
import utility.DateFormatConverter;
import utility.JWTInfo;

public class ClientMainController {

  @FXML
  private GridPane pane;

  @FXML
  private GridPane gridPanelTable;

  @FXML
  private TableView<PatientPrescriptionsTableFormat> table;

  @FXML
  private TableView<CreatePrescriptionTableFormat> medicamentTable;

  @FXML
  private TableColumn<PatientPrescriptionsTableFormat, String> days;

  @FXML
  private TableColumn<PatientPrescriptionsTableFormat, Date> prescriptionDate;

  @FXML
  private TableColumn<PatientPrescriptionsTableFormat, Long> prescriptionTableId;

  @FXML
  private TableColumn<PatientPrescriptionsTableFormat, String> diagnostic;

  @FXML
  private TableColumn<CreatePrescriptionTableFormat, String> medicamentName;

  @FXML
  private TableColumn<CreatePrescriptionTableFormat, String> pills;

  @FXML
  private TableColumn<CreatePrescriptionTableFormat, String> observations;

  @FXML
  private TableColumn<CreatePrescriptionTableFormat, String> daysLabel;

  @FXML
  private Button populateTableButton;

  @FXML
  private Button button;

  @FXML
  private DatePicker datepickerFrom;

  @FXML
  private DatePicker datepickerTo;

  @FXML
  private GridPane gridPanePrescription;

  @FXML
  private Label doctorNameLabel;

  @FXML
  private Label doctorSpecialityLabel;

  @FXML
  private Label doctorEmailLabel;

  @FXML
  private Label presctiptionDateLabel;

  @FXML
  private Label hospitalNameLabel;

  @FXML
  private Label hospitalEmailLabel;

  @FXML
  private Label hospitalCityLabel;

  @FXML
  private Label diagnosticLabel;

  @FXML
  private Button drugSearchButton;
  
  @FXML
  private Label drugName;

  @FXML
  private Label drugCompozition;

  @FXML
  private Label drugPharmaceuticalForm;

  @FXML
  private Label drugTherapeuticIndications;

  @FXML
  private Label drugAdministrattion;

  @FXML
  private Label drugWarnings;

  @FXML
  private Label drugOverdose;

  @FXML
  private Label drugPharmacokineticProperties;

  @FXML
  private Label drugExcipients;

  @FXML
  private Label drugIncompatibilities;

  @FXML
  private Label drugShelfLife;

  @FXML
  private Label drugSpecialPrecautions;

  @FXML
  private Label drugMarketing;
  
  @FXML
  private GridPane drugDetailsGridPane;

  @FXML
  private GridPane medicationTabGridMessage;
  
  @FXML
  private ComboBox<String> logOutComboBox;
  
  @FXML
  private Label taskBarUserName;
  
  @FXML
  private TextField searchBoxDrugName;
  
  @FXML
  private ImageView imageViewBackground;
  
  private JWTInfo token;

  public JWTInfo getToken() {
    return token;
  }

  public void setToken(JWTInfo token) {
    this.token = token;
  }

  @FXML
  private void initialize() {
    gridPanePrescription.setVisible(false);
    taskBarUserName.setText(WordUtils.capitalizeFully(token.getUser().replaceAll("\\d", "").replace(".", " ")));
    datepickerFrom.setValue(LocalDate.now().minusYears(1));
    datepickerTo.setValue(LocalDate.now());
    DateFormatConverter.setConverter(datepickerFrom, datepickerTo);

    PatientPrescriptionsTableControl.setWidth(pane, table, diagnostic, days, prescriptionDate, prescriptionTableId);
    PrescriptionDetailsTableControl.setWidth(pane, table, medicamentName, daysLabel, pills, observations);

    populateTableButton.setOnAction(event -> {
      try {
        List<Prescription> prescriptions = PrescriptionsTabRequest.requestFillPrescriptionTable(datepickerFrom.getValue(), datepickerTo.getValue(), token);
        PatientPrescriptionsTableControl.initializePrescriptionTable(prescriptions, table, diagnostic, days, prescriptionDate, prescriptionTableId);
      }
      catch (IOException e) {
      }
    });

    table.setRowFactory(e -> {
      TableRow<PatientPrescriptionsTableFormat> row = new TableRow<>();
      
      row.hoverProperty().addListener(observable -> {
        final PatientPrescriptionsTableFormat person = row.getItem();

        if (row.isHover() && person != null) {
          row.setStyle("-fx-border-color: lightgray; -fx-background-color: gray;");
        }
        if (!row.isHover()) {
          row.setStyle("-fx-background-color: linear-gradient(white 0%, white 90%, #e0e0e0 90%);");
        }
      });
      
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
          PatientPrescriptionsTableFormat clickedRow = row.getItem();
          Long id = clickedRow.getId();
          try {
            PrescriptionDetails prescriptionDetails = PrescriptionDetailsRequest.prescriptionDetailsRequest(id, token);
            if (prescriptionDetails != null) {
              PrescriptionDetailsTableControl.fillFields(prescriptionDetails, doctorNameLabel, doctorSpecialityLabel, doctorEmailLabel, hospitalNameLabel, hospitalCityLabel, hospitalEmailLabel,
                  diagnosticLabel, presctiptionDateLabel);
              PrescriptionDetailsTableControl.initTable(prescriptionDetails.getPrescriptionDrugDTO(), medicamentTable, medicamentName, daysLabel, pills, observations);
              gridPanePrescription.setVisible(true);
              gridPanelTable.setVisible(false);
            }
          }
          catch (IOException e1) {
          }
        }
      });
      return row;
    });

    button.setOnAction(event -> {
      gridPanePrescription.setVisible(false);
      gridPanelTable.setVisible(true);
    });
    
    drugSearchButton.setOnAction(event -> {
      try {
        Drug drug = DrugProspectumRequest.drugProspectumRequest(searchBoxDrugName.getText(), token);
        drugName.setText(drug.getName());
        drugCompozition.setText(drug.getComposition());
        drugPharmaceuticalForm.setText(drug.getPharmaceuticalForm());
        drugTherapeuticIndications.setText(drug.getTherapeuticIndications());
        drugAdministrattion.setText(drug.getAdministrationMethod());
        drugWarnings.setText(drug.getSpecialWarnings());
        drugOverdose.setText(drug.getOverdose());
        drugPharmacokineticProperties.setText(drug.getPharmacokineticProperties());
        drugExcipients.setText(drug.getExcipients());
        drugIncompatibilities.setText(drug.getIncompatibilities());
        drugShelfLife.setText(drug.getShelfLife());
        drugSpecialPrecautions.setText(drug.getSpecialPrecautionsForStorage());
        drugMarketing.setText(drug.getMarketingAuthorisationHolder());
        drugDetailsGridPane.setVisible(true);
        medicationTabGridMessage.setVisible(false);
      }
      catch (IOException e1) {
      }
    });

    logOutComboBox.setItems(FXCollections.observableArrayList("Logout", "Profile"));

    logOutComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
      if (newVal.equals("Logout")) {
        this.setToken(null);
        Main.showClientView();
      }
      if (newVal.equals("Profile")) {
        try {
          PatientProfile patientProfile = PatientProfileRequest.doctorProfileRequest(token);
          new ProfileScene().initProfile(patientProfile);
        }
        catch (Exception e1) {
          e1.printStackTrace();
        }
//        logOutComboBox.getItems().removeAll(logOutComboBox.getItems());
//        logOutComboBox.setItems(FXCollections.observableArrayList("Logout", "Profile"));
      }
    });
    
    pane.widthProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) -> {
      imageViewBackground.setFitWidth(newSceneWidth.doubleValue());
    });
    
    pane.heightProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) -> {
      imageViewBackground.setFitHeight(newSceneHeight.doubleValue() - 70);
    });
  }
}