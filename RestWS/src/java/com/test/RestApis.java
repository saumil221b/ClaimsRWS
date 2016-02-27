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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("claim")
public class RestApis {

    xmlUnmarshall um = new xmlUnmarshall();
    createQueries c = new createQueries();
    Connections d = new Connections();

    // Search claim by loss date range
    @GET
    @Path("/read/bylossdate")
    @Produces({MediaType.APPLICATION_XML})
    public String getClaimByLossDate(@QueryParam("LossDateStart") String startDate, @QueryParam("LossDateEnd") String endDate) {
        return c.readClaimByLossDate(startDate, endDate);
    }

    // Read a claim
    @GET
    @Path("/read")
    @Produces({MediaType.APPLICATION_XML})
    public String getClaim(@QueryParam("ClaimNumber") String claimNumber) {
        return c.readClaim(claimNumber);
    }
    
    // Read Vehicle Claim
    @GET
    @Path("/read/vehicle")
    @Produces({MediaType.APPLICATION_XML})
    public String getVehicleDetails(@QueryParam("ClaimNumber") String claimNumber,@DefaultValue("1M8GDM9AXKP042788")@QueryParam("Vin") String vin ) {
        return c.readVehicleDetails(claimNumber, vin);
    }
    
    // Delete Claim
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
    
    // Update Claim
    @POST
    @Path("/update")
    @Consumes("application/xml")
    public Response updateClaim(InputStream incomingData) {
        StringBuilder stringBuilder = new StringBuilder();
        String s1 = null;
        try {
            // Just to check
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
             s1 = um.updateClaim(stringBuilder.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage()+"Error Parsing: - ");
        }
        System.out.println("Data Received: " + stringBuilder.toString());

        return Response.status(200).entity(s1).build();
    }


    // Create new claim
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
            um.createClaim(stringBuilder.toString());
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
