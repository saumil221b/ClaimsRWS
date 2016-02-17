package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("claim")
public class RestApis {

    xmlUnmarshall um = new xmlUnmarshall();
    createQueries c = new createQueries();
    Connections d = new Connections();

    //3. Task to search claim by loss date range
    @GET
    @Path("/read/bylossdate")
    @Produces({MediaType.APPLICATION_XML})
    public String getClaimByLossDate(@QueryParam("LossDateStart") String startDate, @QueryParam("LossDateEnd") String endDate) {
        return c.readClaimByLossDate(startDate, endDate);
    }

    // 2. Task to read a claim
    @GET
    @Path("/read")
    @Produces({MediaType.APPLICATION_XML})
    public String getClaim(@QueryParam("ClaimNumber") String claimNumber) {
        return c.readClaim(claimNumber);
    }
    
    @GET
    @Path("/read/vehicle")
    @Produces({MediaType.APPLICATION_XML})
    public String getVehicleDetails(@QueryParam("ClaimNumber") String claimNumber, @QueryParam("Vin") String vin ) {
        return c.readVehicleDetails(claimNumber, vin);
    }
    
    @DELETE @Path("/delete")
    @Produces({MediaType.TEXT_HTML})
    public Response deletePodcastById(@QueryParam("ClaimNumber") String claimNumber) {
	
        try{
            c.deleteClaim(claimNumber);
        }
        catch(Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.status(200).entity("Deleted successfully").build();
}

    // 1. Task to create a new claim
    @POST
    @Path("/create")
    @Consumes("application/xml")
    public Response createClaim(InputStream incomingData) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            // Just to check
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
            um.unmarshall(stringBuilder.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage()+"Error Parsing: - ");
        }
        System.out.println("Data Received: " + stringBuilder.toString());

        return Response.status(200).entity(stringBuilder.toString()).build();
    }

}
class Connections {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/project2";

    static final String USER = "root";
    static final String PASS = "sumikkkk";

    List<String> query = new ArrayList<>();
    static List<String> output = new ArrayList<>();
    Connection conn = null;
    Statement stmt = null;

    public void createConection() {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

        } catch (SQLException | ClassNotFoundException se) {
            
            System.out.print(se.getMessage());
        }
    }
}

//    @XmlRootElement
//class StringList {
//
//    private List<String> data;
//
//    public StringList() {
//    }
//
//    public StringList(List<String> data) {
//        this.data = data;
//    }
//
//    public List<String> getData() {
//        return data;
//    }
//
//    public void setData(List<String> data) {
//        this.data = data;
//    }
//}
//    public List<String> CDB() {
//
//        createConection();
//        
//        try {
//
//            stmt = conn.createStatement();
//
//            String s1 = "Select content from advertisement limit 5";
//            ResultSet result = stmt.executeQuery(s1);
//
//            while (result.next()) {
//                output.add(result.getString("content"));
//
//            }
//            return output;
//        } catch (SQLException ex) {
//            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//
//            try {
//                if (stmt != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//                //no task
//            }
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException se) {
//            }
//        }
//        return null;
//    }

