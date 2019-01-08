package constant;

public class Consts {

  private Consts() {
    throw new AssertionError();
  }

  public static final Boolean LOCAL_SERVER = true;

  public static final String LOGIN_URL = "http://localhost:8080/login";
  public static final String OPENSHIFT_LOGIN_URL = "http://licenta-licenta.193b.starter-ca-central-1.openshiftapps.com/login";

  public static final String FILL_PRESCRIPTION_TABLE_URL = "http://localhost:8080/prescriptions/patient";
  public static final String OPENSHIFT_FILL_PRESCRIPTION_TABLE_URL = "http://licenta-licenta.193b.starter-ca-central-1.openshiftapps.com/prescriptions/patient";

  public static final String PRESCRIPTION_DETAILS_URL = "http://localhost:8080/prescriptions/details";
  public static final String OPENSHIFT_PRESCRIPTION_DETAILS_URL_URL = "http://licenta-licenta.193b.starter-ca-central-1.openshiftapps.com/prescriptions/details";
  
  public static final String DRUG_PROSPECTUS_URL = "http://localhost:8080/drugs/name";
  public static final String OPENSHIFT_DRUG_PROSPECTUS_URL = "http://licenta-licenta.193b.starter-ca-central-1.openshiftapps.com/drugs/name";

  public static final String PATIENT_PROFILE_URL = "http://localhost:8080/patients/profile";
  public static final String OPENSHIFT_PATIENT_PROFILE_URL = "http://licenta-licenta.193b.starter-ca-central-1.openshiftapps.com/patients/profile";
  
  public static final String FILL_HOSPITALS_TABLE_URL = "http://localhost:8080/hospitals/filter";
  public static final String OPENSHIFT_FILL_HOSPITALS_TABLE_URL = "http://licenta-licenta.193b.starter-ca-central-1.openshiftapps.com/hospitals/filter";

}