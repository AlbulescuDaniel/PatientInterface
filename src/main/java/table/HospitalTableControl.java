package table;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import entity.HospitalWithSpecialization;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class HospitalTableControl {

  private HospitalTableControl() {

  }

  public static void setWidth(GridPane pane, TableView<HospitalsTableFormat> hospitalsTable, TableColumn<HospitalsTableFormat, String> hospitalName,
      TableColumn<HospitalsTableFormat, String> hospitalCity, TableColumn<HospitalsTableFormat, String> hospitalRegion, TableColumn<HospitalsTableFormat, String> hospitalStreet,
      TableColumn<HospitalsTableFormat, String> hospitalStreetNumber, TableColumn<HospitalsTableFormat, String> hospitalPhone, TableColumn<HospitalsTableFormat, String> hospitalEmail,
      TableColumn<HospitalsTableFormat, String> webSite) {

    webSite.setPrefWidth(0D);
    pane.widthProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) -> {
      hospitalsTable.setPrefWidth(newSceneWidth.doubleValue() - 40);
      hospitalName.setPrefWidth(hospitalsTable.getPrefWidth() * 16 / 100);
      hospitalCity.setPrefWidth(hospitalsTable.getPrefWidth() * 14 / 100);
      hospitalRegion.setPrefWidth(hospitalsTable.getPrefWidth() * 14 / 100);
      hospitalStreet.setPrefWidth(hospitalsTable.getPrefWidth() * 14 / 100);
      hospitalStreetNumber.setPrefWidth(hospitalsTable.getPrefWidth() * 14 / 100);
      hospitalPhone.setPrefWidth(hospitalsTable.getPrefWidth() * 14 / 100);
      hospitalEmail.setPrefWidth(hospitalsTable.getPrefWidth() * 14 / 100);
    });
  }

  public static void initializeHospitalTable(List<HospitalWithSpecialization> hospitals, TableView<HospitalsTableFormat> hospitalsTable, TableColumn<HospitalsTableFormat, String> hospitalName,
      TableColumn<HospitalsTableFormat, String> hospitalCity, TableColumn<HospitalsTableFormat, String> hospitalRegion, TableColumn<HospitalsTableFormat, String> hospitalStreet,
      TableColumn<HospitalsTableFormat, String> hospitalStreetNumber, TableColumn<HospitalsTableFormat, String> hospitalPhone, TableColumn<HospitalsTableFormat, String> hospitalEmail,
      TableColumn<HospitalsTableFormat, String> webSite) {

    hospitalName.setStyle("-fx-padding: 0 0 0 10;");
    hospitalCity.setStyle("-fx-padding: 0 0 0 10;");
    hospitalRegion.setStyle("-fx-padding: 0 0 0 10;");
    hospitalStreet.setStyle("-fx-padding: 0 0 0 10;");
    hospitalStreetNumber.setStyle("-fx-padding: 0 0 0 10;");
    hospitalPhone.setStyle("-fx-padding: 0 0 0 10;");
    hospitalEmail.setStyle("-fx-padding: 0 0 0 10;");

    ObservableList<HospitalsTableFormat> fillTableHospitals = FXCollections.observableArrayList();
    hospitals.forEach(p -> {
      fillTableHospitals.add(new HospitalsTableFormat(p.getName(), p.getAddressDTO().getCity(), p.getAddressDTO().getRegion(), p.getAddressDTO().getStreet(), p.getAddressDTO().getStreetNumber(),
          p.getPhone(), p.getEmail(), p.getWebSite()));
    });
    hospitalsTable.setItems(fillTableHospitals);

    hospitalName.setCellValueFactory(new PropertyValueFactory<HospitalsTableFormat, String>("hospitalName"));
    hospitalCity.setCellValueFactory(new PropertyValueFactory<HospitalsTableFormat, String>("hospitalCity"));
    hospitalRegion.setCellValueFactory(new PropertyValueFactory<HospitalsTableFormat, String>("hospitalregion"));
    hospitalStreet.setCellValueFactory(new PropertyValueFactory<HospitalsTableFormat, String>("hospitalStreet"));
    hospitalStreetNumber.setCellValueFactory(new PropertyValueFactory<HospitalsTableFormat, String>("hospitalStreetNumber"));
    hospitalPhone.setCellValueFactory(new PropertyValueFactory<HospitalsTableFormat, String>("hospitalPhone"));
    hospitalEmail.setCellValueFactory(new PropertyValueFactory<HospitalsTableFormat, String>("hospitalEmail"));
    webSite.setCellValueFactory(new PropertyValueFactory<HospitalsTableFormat, String>("hospitalWebSite"));
  }

  public static void tableRowControl(TableView<HospitalsTableFormat> hospitalsTable) {
    hospitalsTable.setRowFactory(e -> {
      TableRow<HospitalsTableFormat> row = new TableRow<>();

      row.hoverProperty().addListener(observable -> {
        final HospitalsTableFormat hospital = row.getItem();

        if (row.isHover() && hospital != null) {
          row.setStyle("-fx-border-color: lightgray; -fx-background-color: gray;");
        }
        if (!row.isHover()) {
          row.setStyle("-fx-background-color: linear-gradient(white 0%, white 90%, #e0e0e0 90%);");
        }
      });

      row.setOnMouseClicked(event -> {
        if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
          HospitalsTableFormat clickedRow = row.getItem();
          String webSite = clickedRow.getHospitalWebSite();
          try {
            Desktop.getDesktop().browse(new URL(webSite).toURI());
          }
          catch (IOException | URISyntaxException e1) {
          }
        }
      });
      return row;
    });
  }
}
