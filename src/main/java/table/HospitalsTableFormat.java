package table;

public class HospitalsTableFormat {

  private String hospitalName;
  private String hospitalCity;
  private String hospitalregion;
  private String hospitalStreet;
  private String hospitalStreetNumber;
  private String hospitalPhone;
  private String hospitalEmail;
  private String hospitalWebSite;

  public HospitalsTableFormat() {
    super();
  }

  public HospitalsTableFormat(String hospitalName, String hospitalCity, String hospitalregion, String hospitalStreet, String hospitalStreetNumber, String hospitalPhone, String hospitalEmail,
      String hospitalWebSite) {
    super();
    this.hospitalName = hospitalName;
    this.hospitalCity = hospitalCity;
    this.hospitalregion = hospitalregion;
    this.hospitalStreet = hospitalStreet;
    this.hospitalStreetNumber = hospitalStreetNumber;
    this.hospitalPhone = hospitalPhone;
    this.hospitalEmail = hospitalEmail;
    this.hospitalWebSite = hospitalWebSite;
  }

  public String getHospitalName() {
    return hospitalName;
  }

  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  public String getHospitalCity() {
    return hospitalCity;
  }

  public void setHospitalCity(String hospitalCity) {
    this.hospitalCity = hospitalCity;
  }

  public String getHospitalregion() {
    return hospitalregion;
  }

  public void setHospitalregion(String hospitalregion) {
    this.hospitalregion = hospitalregion;
  }

  public String getHospitalStreet() {
    return hospitalStreet;
  }

  public void setHospitalStreet(String hospitalStreet) {
    this.hospitalStreet = hospitalStreet;
  }

  public String getHospitalStreetNumber() {
    return hospitalStreetNumber;
  }

  public void setHospitalStreetNumber(String hospitalStreetNumber) {
    this.hospitalStreetNumber = hospitalStreetNumber;
  }

  public String getHospitalPhone() {
    return hospitalPhone;
  }

  public void setHospitalPhone(String hospitalPhone) {
    this.hospitalPhone = hospitalPhone;
  }

  public String getHospitalEmail() {
    return hospitalEmail;
  }

  public void setHospitalEmail(String hospitalEmail) {
    this.hospitalEmail = hospitalEmail;
  }

  public String getHospitalWebSite() {
    return hospitalWebSite;
  }

  public void setHospitalWebSite(String hospitalWebSite) {
    this.hospitalWebSite = hospitalWebSite;
  }
}
