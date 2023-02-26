package app.lyricsapp.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class API {

    public static String call(String url) {

        try {
            String val;

            URL URL = new URL(url);

            BufferedReader br = new BufferedReader(new InputStreamReader(URL.openStream()));

            String xmlContent = "";
            while ((val = br.readLine()) != null) {
                xmlContent += val + "\n";
            }

            br.close();
            return xmlContent;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return url;
    }

}

