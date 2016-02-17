package com.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Saumil Jagirdar
 *
 */
public class claimClient {

    public static void main(String[] args) throws FileNotFoundException {
        String string = "";

        InputStream crunchifyInputStream = new FileInputStream("C:\\Users\\Saumil\\Documents\\NetBeansProjects\\Practice Qs\\RestWS\\src\\java\\com\\test\\claims.xml");
        InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
        try (BufferedReader br = new BufferedReader(crunchifyReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                string += line + "\n";
            }

            try {
                URL url = new URL("http://localhost:8080/RestWS/webresources/claim/create");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/xml");
                connection.setConnectTimeout(50000);
                connection.setReadTimeout(50000);
                try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
                    out.write(string);
                }

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    while (in.readLine() != null) {
                    }
                    System.out.println("\n Service class successful..");
                }
            } catch (Exception e) {
                System.out.println("\nerror calling REST Service");
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
