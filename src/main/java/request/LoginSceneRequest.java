package request;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import constant.AccountType;
import constant.Consts;
import utility.CustomAlerts;
import utility.JWTInfo;

public class LoginSceneRequest {
  
  public JWTInfo sendLogInDoctorPOST(String email, String password) throws IOException {
//     if (!new Utility().isOnline()) {
//     CustomAlerts.showInternetErrorConnectionAlert();
//     throw new IOException();
//     }

    URL object = new URL(Consts.LOCAL_SERVER ? Consts.LOGIN_URL : Consts.OPENSHIFT_LOGIN_URL);
    HttpURLConnection con = (HttpURLConnection)object.openConnection();
    con.setDoOutput(true);
    con.setDoInput(true);
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Accept", "application/json");
    con.setRequestMethod("POST");
    try {
      con.connect();
    }
    catch (Exception e) {
      CustomAlerts.showServerErrorConnectionAlert();
    }

    JSONObject jsonResponse = new JSONObject();
    jsonResponse.put("userName", email);
    jsonResponse.put("password", password);

    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
    wr.write(jsonResponse.toString());
    wr.flush();

    JWTInfo parsedJWT = null;
    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
      parsedJWT = new JWTInfo(con.getHeaderField("Authorization"));
      return parsedJWT.getRole().equals(AccountType.PAT.toString()) ? parsedJWT : null;
    }
    else if (con.getResponseCode() == 503) {
      CustomAlerts.showServiceUnavailableAlert();
      throw new IOException();
    }
    else {
      return null;
    }

  }
}
