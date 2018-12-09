package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import entity.Prescription;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import request.PrescriptionsTabRequest;
import table.CreatePrescriptionTableFormat;
import table.PatientPrescriptionsTableControl;
import table.PatientPrescriptionsTableFormat;
import table.PrescriptionDetailsTableControl;
import utility.DateFormatConverter;
import utility.JWTInfo;

public class ClientMainController {

	@FXML
	private GridPane pane;

	@FXML
	private TableView<PatientPrescriptionsTableFormat> table;

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
	private AnchorPane registerPatientAnchorPane;

	@FXML
	private Button populateTableButton;

	@FXML
	private DatePicker datepickerFrom;

	@FXML
	private DatePicker datepickerTo;

	@FXML
	private GridPane gridPanePrescription;

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
		datepickerFrom.setValue(LocalDate.now().minusYears(1));
		datepickerTo.setValue(LocalDate.now());
		DateFormatConverter.setConverter(datepickerFrom, datepickerTo);

		PatientPrescriptionsTableControl.setWidth(pane, table, diagnostic, days, prescriptionDate, prescriptionTableId);
		PrescriptionDetailsTableControl.setWidth(pane, table, medicamentName, daysLabel, pills, observations,
				registerPatientAnchorPane);

		populateTableButton.setOnAction(event -> {
			try {
				List<Prescription> prescriptions = PrescriptionsTabRequest
						.requestFillPrescriptionTable(datepickerFrom.getValue(), datepickerTo.getValue(), token);
				PatientPrescriptionsTableControl.initializePrescriptionTable(prescriptions, table, diagnostic, days,
						prescriptionDate, prescriptionTableId);
			} catch (IOException e) {
			}
		});
	}
}