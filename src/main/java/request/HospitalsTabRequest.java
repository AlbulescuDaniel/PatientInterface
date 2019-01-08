package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import constant.Consts;
import entity.HospitalWithSpecialization;
import utility.CustomAlerts;
import utility.JWTInfo;

public class HospitalsTabRequest {

  private HospitalsTabRequest() {
  }

  public static List<HospitalWithSpecialization> requestFillHospitalsTable(String city, String specialization, JWTInfo token) throws IOException {
    // if (!new Utility().isOnline()) {
    // CustomAlerts.showInternetErrorConnectionAlert();
    // throw new IOException();
    // }

    if(city.equals("")) {
      CustomAlerts.showEmptyFieldsAlert();
      throw new IOException();
    }
    
    URL obj = new URL(
        (Consts.LOCAL_SERVER ? Consts.FILL_HOSPITALS_TABLE_URL : Consts.OPENSHIFT_FILL_HOSPITALS_TABLE_URL) + "?city=" + city + "&specialization=" + specialization);

    HttpURLConnection con = (HttpURLConnection)obj.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Accept", "application/json");
    con.setRequestProperty("Authorization", token.getToken());

    try {
      con.connect();
    }
    catch (Exception e) {
      CustomAlerts.showServerErrorConnectionAlert();
      throw new IOException();
    }

    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      JSONArray jsonArray = new JSONArray(response.toString());
      List<HospitalWithSpecialization> hospitals = new ArrayList<>();
      for (Object object : jsonArray) {
        Gson gson = new GsonBuilder().create();
        hospitals.add(gson.fromJson(object.toString().trim(), HospitalWithSpecialization.class));
      }

      if (hospitals.size() == 0) {
        CustomAlerts.showemptyPrescriptionListAlert();
        throw new IOException();
      }

      return hospitals;
    }
    else if (con.getResponseCode() == 503) {
      CustomAlerts.showServiceUnavailableAlert();
      throw new IOException();
    }
    else if (con.getResponseCode() == 204) {
      CustomAlerts.showemptyHospitalListAlert();
      throw new IOException();
    }
    else {
      return Collections.emptyList();
    }
  }
}
