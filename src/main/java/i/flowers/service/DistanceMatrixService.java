package i.flowers.service;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class DistanceMatrixService {

    @Value("${distance.matrix}")
    private String distanceMatrixApiKey;

    public JsonElement callAndParse(String from, String to) {

        String endpoint = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + from
                + "&destinations=" + to + "&key=" + distanceMatrixApiKey;

        URL url;
        try {

            url = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
//            System.out.println(con);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {

                content.append(inputLine+"\n");

            }
            in.close();
            con.disconnect();
//            System.out.println(content);
            return new JsonParser().parse(content.toString());
        } catch (IOException e) {
            JsonObject error = new JsonObject();
            error.add("error", new JsonPrimitive(e.getMessage()));
            return error;
        }
    }

    static String s ="";
    public static void main(String[] args) {
        Gson gson = new Gson();

        MyObject = gson.fromJson(s , MyObjectClass.class);
    }
}
