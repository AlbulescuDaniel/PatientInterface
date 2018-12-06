package application;

import java.util.List;

import entity.Prescription;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import utility.JWTInfo;

public class ClientMainController {

	
  private JWTInfo token;
  
	public JWTInfo getToken() {
    return token;
  }
	
  public void setToken(JWTInfo token) {
    this.token = token;
  }

  @FXML
	private void initialize() {

//		gridPanePrescription.setVisible(false);
//
//		datepickerFrom.setConverter(new StringConverter<LocalDate>() {
//			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//			@Override
//			public String toString(LocalDate date) {
//				if (date != null) {
//					return dateFormatter.format(date);
//				} else {
//					return "";
//				}
//			}
//
//			@Override
//			public LocalDate fromString(String string) {
//				if (string != null && !string.isEmpty()) {
//					return LocalDate.parse(string, dateFormatter);
//				} else {
//					return null;
//				}
//			}
//		});
//
//		datepickerTo.setConverter(new StringConverter<LocalDate>() {
//			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//			@Override
//			public String toString(LocalDate date) {
//				if (date != null) {
//					return dateFormatter.format(date);
//				} else {
//					return "";
//				}
//			}
//
//			@Override
//			public LocalDate fromString(String string) {
//				if (string != null && !string.isEmpty()) {
//					return LocalDate.parse(string, dateFormatter);
//				} else {
//					return null;
//				}
//			}
//		});
//
//		datepickerFrom.setValue(newLocalDate().minusYears(1));
//		datepickerTo.setValue(newLocalDate());
//
//		button.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				gridPanePrescription.setVisible(false);
//				gridPanelTable.setVisible(true);
//			}
//		});
//
//		table.setRowFactory(tv -> {
//			TableRow<TableCompletation> row = new TableRow<>();
//			row.setOnMouseClicked(event -> {
//				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
//
//					TableCompletation clickedRow = row.getItem();
//					String date = clickedRow.getPrescriptionDate();
//					String diagnostic = clickedRow.getDiagnostic();
//					Prescription prescription = null;
//
//					for (Prescription p : prescriptions) {
//						if (new SimpleDateFormat("dd-MM-yyyy").format(p.getDatePrescripted()).compareTo(date) == 0
//								& p.getDiagnostic().compareTo(diagnostic) == 0) {
//							prescription = p;
//							break;
//						}
//					}
//
//					moveToPrescriptionGrid(prescription);
//				}
//			});
//			return row;
//		});
//
//		populateTableButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				try {
//					PrescriptionsTabRequest fill = new PrescriptionsTabRequest();
//					String response = null;
//					prescriptions = new ArrayList<>();
//
//					response = fill.requestFillPrescriptionTable(email, datepickerFrom.getValue(),
//							datepickerTo.getValue());
//					ObjectMapper mapper = new ObjectMapper();
//
//					JsonNode rootNode  = null;
//					Alert alert = null;
//					
//					switch(response) {
//						case "400":
//							alert = new Alert(AlertType.INFORMATION);
//							alert.setTitle("Patient information");
//							alert.setContentText("This patient does not have prescriptions");
//							alert.showAndWait();
//							break;
//						case Consts.SERVER_CLOSED:
//							alert = new Alert(AlertType.ERROR);
//							alert.setTitle("Error");
//							alert.setHeaderText("Server Error");
//							alert.setContentText(response);
//							alert.showAndWait();
//						default:
//							rootNode = mapper.readTree(response);
//							if (rootNode.isArray()) {
//								for (final JsonNode objNode : rootNode) {
//									prescriptions.add(mapper.readValue(objNode, Prescription.class));
//								}
//							}
//
//							ObservableList<TableCompletation> fillTablePrescriptions = FXCollections.observableArrayList();
//
//							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//							prescriptions.forEach(p -> fillTablePrescriptions.add(new TableCompletation(
//									p.getDoctorByIdDoctor().getFirstName() + " " + p.getDoctorByIdDoctor().getLastName(),
//									concatenateSpecialities(p.getDoctorByIdDoctor().getDoctorSpecialitiesById()),
//									formatedDate(p.getDatePrescripted()).format(formatter), p.getDiagnostic())));
//
//							initializePrescriptionTable();
//							table.setItems(fillTablePrescriptions);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		populatePharmaciesTableButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				try {
//					PharmaciesTabRequest fill = new PharmaciesTabRequest();
//					String response = null;
//					List<Pharmacy> pharmacies = new ArrayList<>();
//
//					response = fill.getPharmaciesByMedicamentRequest(medicamentInPharmacyTab.getText().toLowerCase().toString()
//							, cityInPharmacyTab.getText().substring(0,1).toUpperCase() + cityInPharmacyTab.getText().substring(1).toLowerCase());
//					ObjectMapper mapper = new ObjectMapper();
//
//					JsonNode rootNode  = null;
//					Alert alert = null;
//					switch(response) {
//						case "400":
//							alert = new Alert(AlertType.INFORMATION);
//							alert.setTitle("Patient information");
//							alert.setContentText("This medicament does not exist or pharmacies do not have it in stock");
//							alert.showAndWait();
//							break;
//						case Consts.SERVER_CLOSED:
//							alert = new Alert(AlertType.ERROR);
//							alert.setTitle("Error");
//							alert.setHeaderText("Server Error");
//							alert.setContentText(response);
//							alert.showAndWait();
//						default:
//							rootNode = mapper.readTree(response);
//							if (rootNode.isArray()) {
//								for (final JsonNode objNode : rootNode) {
//									pharmacies.add(mapper.readValue(objNode, Pharmacy.class));
//								}
//							}
//							ObservableList<PharmacyTableCompletation> fillTablePharmacies = FXCollections.observableArrayList();
//							
//							pharmacies.forEach(p -> fillTablePharmacies.add(new PharmacyTableCompletation(
//									p.getPharmacyName(),
//									p.getStreet(),
//									p.getCity(),
//									p.getStreetNumber(),
//									p.getPhone(),
//									p.getPharmacyStocksByIdPharmacy().get(0).getPrice())));
//							
//							initializePharmaciesTable();
//							pharmaciesTable.setItems(fillTablePharmacies);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		populateHospitalsTableButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				try {
//					HospitalsTabRequest fill = new HospitalsTabRequest();
//					String response = null;
//					List<Hospital> hospitals = new ArrayList<>();
//
//					response = fill.requestFillHospitalTable(cityInHospitalTab.getText().substring(0,1).toUpperCase() + cityInHospitalTab.getText().substring(1).toLowerCase());
//					ObjectMapper mapper = new ObjectMapper();
//
//					JsonNode rootNode  = null;
//					Alert alert = null;
//					switch(response) {
//						case "400":
//							alert = new Alert(AlertType.INFORMATION);
//							alert.setTitle("Hospital information");
//							alert.setContentText("In this city does not exist an hospital");
//							alert.showAndWait();
//							break;
//						case Consts.SERVER_CLOSED:
//							alert = new Alert(AlertType.ERROR);
//							alert.setTitle("Error");
//							alert.setHeaderText("Server Error");
//							alert.setContentText(response);
//							alert.showAndWait();
//						default:
//							rootNode = mapper.readTree(response);
//							if (rootNode.isArray()) {
//								for (final JsonNode objNode : rootNode) {
//									hospitals.add(mapper.readValue(objNode, Hospital.class));
//								}
//							}
//							hospitals.forEach(h -> System.out.println(h.toString()));
//							ObservableList<Hospital> fillTableHospital = FXCollections.observableArrayList();
//							fillTableHospital.addAll(hospitals);
//							
//							initializeHospitalTable();
//							hospitalsTable.setItems(fillTableHospital);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	private String concatenateSpecialities(List<DoctorSpeciality> doctorSpecialities) {
//		String specialities = "";
//
//		for (DoctorSpeciality ds : doctorSpecialities) {
//			if (specialities.compareTo("") == 0)
//				specialities += ds.getSpecialityBySpecialityId().getName();
//			else
//				specialities += ", " + ds.getSpecialityBySpecialityId().getName();
//
//		}
//		return specialities;
//	}
//
//	private LocalDate newLocalDate() {
//		Date date = new Date();
//		Instant instant = date.toInstant();
//		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//
//		return localDate;
//	}
//
//	private LocalDate formatedDate(Date date) {
//		Instant instant = date.toInstant();
//		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//		return localDate;
//	}
//
//	@SuppressWarnings("unchecked")
//	private void initializePrescriptionTable() {
//		doctorName.setStyle("-fx-padding: 0 0 0 10;");
//		doctorSpeciality.setStyle("-fx-padding: 0 0 0 10;");
//		prescriptionDate.setStyle("-fx-padding: 0 0 0 10;");
//		diagnostic.setStyle("-fx-padding: 0 0 0 10;");
//
//		doctorName.setCellValueFactory(new PropertyValueFactory<TableCompletation, String>("doctorName"));
//		doctorSpeciality.setCellValueFactory(new PropertyValueFactory<TableCompletation, String>("doctorSpeciality"));
//		prescriptionDate.setCellValueFactory(new PropertyValueFactory<TableCompletation, Date>("prescriptionDate"));
//		diagnostic.setCellValueFactory(new PropertyValueFactory<TableCompletation, String>("diagnostic"));
//
//		GUIUtils.autoFitTable(table);
//	}
//	@SuppressWarnings("unchecked")
//	private void moveToPrescriptionGrid(Prescription prescription) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//		gridPanelTable.setVisible(false);
//		gridPanePrescription.setVisible(true);
//
//		doctorNameLabel.setText(prescription.getDoctorByIdDoctor().getFirstName() + " "
//				+ prescription.getDoctorByIdDoctor().getLastName());
//		doctorSpecialityLabel
//				.setText(concatenateSpecialities(prescription.getDoctorByIdDoctor().getDoctorSpecialitiesById()));
//		doctorEmailLabel.setText(prescription.getDoctorByIdDoctor().getEmail());
//		presctiptionDateLabel.setText(formatedDate(prescription.getDatePrescripted()).format(formatter));
//		daysLabel.setText(((Integer) prescription.getDays()).toString());
//		diagnosticLabel.setText(prescription.getDiagnostic());
//
//		checked.setStyle("-fx-alignment: CENTER;");
//		medicamentName.setStyle("-fx-padding: 0 0 0 10;");
//		pills.setStyle("-fx-padding: 0 0 0 10;");
//		observations.setStyle("-fx-padding: 0 0 0 10;");
//
//		checked.setCellValueFactory(new PropertyValueFactory<MedicamentTableCompletation, Image>("checkedImage"));
//		medicamentName
//				.setCellValueFactory(new PropertyValueFactory<MedicamentTableCompletation, String>("medicamentName"));
//		pills.setCellValueFactory(new PropertyValueFactory<MedicamentTableCompletation, Integer>("pills"));
//		observations.setCellValueFactory(new PropertyValueFactory<MedicamentTableCompletation, String>("observations"));
//
//		GUIUtils.autoFitTable(medicamentTable);
//
//		ObservableList<MedicamentTableCompletation> fillTableMedicaments = FXCollections.observableArrayList();
//
//		prescription.getPrescriptionMedicamentsByIdPrescription()
//				.forEach(p -> fillTableMedicaments.add(new MedicamentTableCompletation(selectImage(p.getChecked()),
//						p.getMedicamentByMedicamentId().getName(), p.getPillsNumber(), p.getDescription())));
//
//		medicamentTable.setItems(fillTableMedicaments);
//
//	}
//
//	@SuppressWarnings("unchecked")
//	private void initializePharmaciesTable() {
//		pharmacyName.setStyle("-fx-padding: 0 0 0 10;");
//		pharmacyCity.setStyle("-fx-padding: 0 0 0 10;");
//		pharmacyStreet.setStyle("-fx-padding: 0 0 0 10;");
//		pharmacyStreetNumber.setStyle("-fx-padding: 0 0 0 10;");
//		pharmacyPhone.setStyle("-fx-padding: 0 0 0 10;");
//		medicamentPrce.setStyle("-fx-padding: 0 0 0 10;");
//
//		pharmacyName.setCellValueFactory(new PropertyValueFactory<PharmacyTableCompletation, String>("pharmacyName"));
//		pharmacyCity.setCellValueFactory(new PropertyValueFactory<PharmacyTableCompletation, String>("city"));
//		pharmacyStreet.setCellValueFactory(new PropertyValueFactory<PharmacyTableCompletation, String>("street"));
//		pharmacyStreetNumber.setCellValueFactory(new PropertyValueFactory<PharmacyTableCompletation, String>("streetNumber"));
//		pharmacyPhone.setCellValueFactory(new PropertyValueFactory<PharmacyTableCompletation, String>("phone"));
//		medicamentPrce.setCellValueFactory(new PropertyValueFactory<PharmacyTableCompletation, Double>("price"));
//	}
//	
//	@SuppressWarnings("unchecked")
//	private void initializeHospitalTable() {
//		hospitalName.setStyle("-fx-padding: 0 0 0 10;");
//		hospitalCity.setStyle("-fx-padding: 0 0 0 10;");
//		hospitalStreet.setStyle("-fx-padding: 0 0 0 10;");
//		hospitalStreetNumber.setStyle("-fx-padding: 0 0 0 10;");
//		hospitalPhone.setStyle("-fx-padding: 0 0 0 10;");
//		hospitalEmail.setStyle("-fx-padding: 0 0 0 10;");
//
//		hospitalName.setCellValueFactory(new PropertyValueFactory<Hospital, String>("hospitalName"));
//		hospitalCity.setCellValueFactory(new PropertyValueFactory<Hospital, String>("city"));
//		hospitalStreet.setCellValueFactory(new PropertyValueFactory<Hospital, String>("street"));
//		hospitalStreetNumber.setCellValueFactory(new PropertyValueFactory<Hospital, String>("streetNumber"));
//		hospitalPhone.setCellValueFactory(new PropertyValueFactory<Hospital, String>("phone"));
//		hospitalEmail.setCellValueFactory(new PropertyValueFactory<Hospital, Double>("email"));
//	}
//	
//	public CheckBox selectImage(int i) {
//		CheckBox checkBox = new CheckBox();
//		if (i == 1)
//			checkBox.setSelected(true);
//		checkBox.setDisable(true);
//		checkBox.setAlignment(Pos.CENTER_RIGHT);
//		return checkBox;
//	}
	}
}