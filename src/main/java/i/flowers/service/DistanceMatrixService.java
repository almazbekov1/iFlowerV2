package i.flowers.service;

import com.google.gson.*;
import i.flowers.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import i.flowers.service.json.DistanceResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class DistanceMatrixService {

    @Value("${distance.matrix}")
    private String distanceMatrixApiKey;

    public DistanceResponse callAndParse(String from, String to) {
        URL url;
        try {
            url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?"
                    +getDistance(from,to)+"&key=" + distanceMatrixApiKey);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine + "\n");
            }
            in.close();
            con.disconnect();
            Gson gson = new Gson();
            DistanceResponse d = gson.fromJson(content.toString(), DistanceResponse.class);
            return d;
        } catch (IOException e) {
            JsonObject error = new JsonObject();
            error.add("error", new JsonPrimitive(e.getMessage()));
            throw new UserServiceException("bad request");
        }
    }

    private String getDistance(String from, String to) {
        to = to.replace(',', ' ');
        from = from.replace(',', ' ');
        String[] froms = from.split(" ");
        String[] toes = to.split(" ");

        String result = "origins=";
        for (int i = 0; i < froms.length; i++) {
            if (froms[i] != "" && froms[i] != null && !froms[i].isBlank()) {

                if (froms[i].matches("[0-9]+")&&i!=0) {
                    result += "-"+froms[i];
                } else if (i==0){
                    result += froms[i];
                }else {
                    result += ","+froms[i];
                }
            }
        }
        result += "&destinations=";
        for (int i = 0; i < toes.length; i++) {
            if (toes[i] != "" && toes[i] != null && !toes[i].isBlank()) {

                if (toes[i].matches("[0-9]+")&&i!=0) {
                    result += "-"+toes[i];
                } else if (i==0){
                    result += toes[i];
                }else {
                    result += ","+toes[i];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "Bishkek,ShabdanBaatyra-155";
        String s2 = "Bishkek ,ShabdanBaatyra-155";
        String ha = " ";
        String a = s.replace(',', ' ');
        if (ha.isBlank()) {
            System.out.println("s ");
        }


//        for (int i = 0; i < strings.length; i++) {
//            System.out.println(strings[i]);
//        }
    }


}
