package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saumil
 */
public class createQueries {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/xsdsql";

    static final String USER = "root";
    static final String PASS = "sumikkkk";

    List<String> query = new ArrayList<>();
    Connection conn = null;
    Statement stmt = null;

    public void createConnection() {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

        } catch (SQLException | ClassNotFoundException se) {
        }
    }

    public String readClaimByLossDate(String startDate, String endDate) {

        List<String> output = new ArrayList<>();

        createConnection();
        StringBuilder readClaimByLossDate = new StringBuilder();
        String getClaimByLossDate = "Select * from mitchellclaim where LossDate BETWEEN '" + startDate + "' AND '" + endDate + "'";
        // create the preparedstatement before the loop
        try {
            Statement preparedStmt = conn.createStatement();

            ResultSet result = preparedStmt.executeQuery(getClaimByLossDate);
            int count = 0;
            while (result.next()) {

                count++;
                readClaimByLossDate.append(count);
                readClaimByLossDate.append(".\n");
                readClaimByLossDate.append("ClaimantFirstName:");
                readClaimByLossDate.append(result.getString("ClaimantFirstName"));
                readClaimByLossDate.append("\n");

                readClaimByLossDate.append("ClaimantLastName:");
                readClaimByLossDate.append(result.getString("ClaimantLastName"));
                readClaimByLossDate.append("\n");

                readClaimByLossDate.append("Status:");
                readClaimByLossDate.append(result.getString("Status"));
                readClaimByLossDate.append("\n");

                readClaimByLossDate.append("LossDate:");
                readClaimByLossDate.append(result.getString("LossDate"));
                readClaimByLossDate.append("\n");

                readClaimByLossDate.append("AssignedAdjusterID:");
                readClaimByLossDate.append(result.getString("AssignedAdjusterID"));
                readClaimByLossDate.append("\n\n");
            }
            return readClaimByLossDate.toString();

        } catch (SQLException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                //no task
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
        }
        return null;
    }
//   
    public String readClaim(String claimNumber) {

        createConnection();
        StringBuilder readClaim = new StringBuilder();
        String getMitchellClaim = "Select * from mitchellclaim where ClaimNumber = '" + claimNumber + "'";
        // create the preparedstatement before the loop
        try {
            Statement preparedStmt = conn.createStatement();
            ResultSet result = preparedStmt.executeQuery(getMitchellClaim);
            int count = 0;
            while (result.next()) {

                count++;
                readClaim.append(count + ".\n");
                readClaim.append("ClaimantFirstName:");
                readClaim.append(result.getString("ClaimantFirstName"));
                readClaim.append("\n");

                readClaim.append("ClaimantLastName:");
                readClaim.append(result.getString("ClaimantLastName"));
                readClaim.append("\n");

                readClaim.append("Status:");
                readClaim.append(result.getString("Status"));
                readClaim.append("\n");

                readClaim.append("LossDate:");
                readClaim.append(result.getString("LossDate"));
                readClaim.append("\n");

                readClaim.append("AssignedAdjusterID:");
                readClaim.append(result.getString("AssignedAdjusterID"));
                readClaim.append("\n\n");

            }
            return readClaim.toString();

        } catch (SQLException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                //no task
                System.out.println(se.getErrorCode()+":"+se.getMessage());
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getErrorCode() + ":" + se.getMessage());
            }
        }
        return null;

    }

    public void createClaim(newClaim claim) {

        createConnection();

        String insertMitchellClaim = " insert into mitchellclaim (ClaimNumber, ClaimantFirstName, ClaimantLastName, Status, LossDate, AssignedAdjusterID)"
                + " values (?, ?, ?, ?, ?, ?)";

        String insertLossInfo = " insert into lossinfo (ClaimNumber, CauseOfLoss, ReportedDate, LossDescription) values (?, ?, ?, ?)";

        String insertVehicleDetails = " insert into vehicledetails (ClaimNumber, ModelYear, MakeDescription, ModelDescription, "
                + "EngineDescription, ExteriorColor, LicPlate, LicPlateState, LicPlateExpDate, DamageDescription, Mileage, Vin)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // declare the preparedstatement reference
        PreparedStatement preparedStmt = null;
        try {
            // create the preparedstatement before the loop
            preparedStmt = conn.prepareStatement(insertMitchellClaim);

            preparedStmt.setString(1, claim.getClaimNumber());
            preparedStmt.setString(2, claim.getClaimantFirstName());
            preparedStmt.setString(3, claim.getClaimantLastName());
            preparedStmt.setString(4, claim.getStatus());
            preparedStmt.setTimestamp(5, claim.getLossDate());
            preparedStmt.setLong(6, claim.getAssignedAdjusterID());
            preparedStmt.execute();           // INSERT in MitchellClaim

            preparedStmt = conn.prepareStatement(insertLossInfo);

            preparedStmt.setString(1, claim.getClaimNumber());
            preparedStmt.setString(2, claim.getLossinfo().getCauseOfLoss());
            preparedStmt.setTimestamp(3, claim.getLossinfo().getReportedDate());
            preparedStmt.setString(4, claim.getLossinfo().getLossDescription());
            preparedStmt.execute();           // INSERT in LossInfo

            preparedStmt = conn.prepareStatement(insertVehicleDetails);

            preparedStmt.setString(1, claim.getClaimNumber());
            preparedStmt.setInt(2, claim.getVehicles().getVehicleDetails().getModelYear());
            preparedStmt.setString(3, claim.getVehicles().getVehicleDetails().getMakeDescription());
            preparedStmt.setString(4, claim.getVehicles().getVehicleDetails().getModelDescription());
            preparedStmt.setString(5, claim.getVehicles().getVehicleDetails().getEngineDescription());
            preparedStmt.setString(6, claim.getVehicles().getVehicleDetails().getExteriorColor());
            preparedStmt.setString(7, claim.getVehicles().getVehicleDetails().getLicPlate());
            preparedStmt.setString(8, claim.getVehicles().getVehicleDetails().getLicPlateExpDate());
            preparedStmt.setString(9, claim.getVehicles().getVehicleDetails().getLicPlateState());
            preparedStmt.setString(10, claim.getVehicles().getVehicleDetails().getDamageDescription());
            preparedStmt.setInt(11, claim.getVehicles().getVehicleDetails().getMileage());
            preparedStmt.setString(12, claim.getVehicles().getVehicleDetails().getVin());

            preparedStmt.execute();           // INSERT in VehicleDetails

        } catch (SQLException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                //no task
                System.out.println(se.getErrorCode()+":"+se.getMessage());
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                
                System.out.println(se.getErrorCode()+":"+se.getMessage());
            }
        }
    }

    public String readVehicleDetails(String claimNumber, String vin) {

        createConnection();
        StringBuilder readClaim = new StringBuilder();
        String getVehicle = "Select * from vehicledetails where vin = '" + vin + "' AND ClaimNumber = '" + claimNumber + "'";
        try {
            Statement preparedStmt = conn.createStatement();
            ResultSet result = preparedStmt.executeQuery(getVehicle);
            int count = 0;
            while (result.next()) {

                count++;
                readClaim.append(count + ".\n");
                readClaim.append("ModelYear:");
                readClaim.append(result.getString("ModelYear"));
                readClaim.append("\n");

                readClaim.append("MakeDescription:");
                readClaim.append(result.getString("MakeDescription"));
                readClaim.append("\n");

                readClaim.append("ModelDescription:");
                readClaim.append(result.getString("ModelDescription"));
                readClaim.append("\n");

                readClaim.append("EngineDescription:");
                readClaim.append(result.getString("EngineDescription"));
                readClaim.append("\n");

                readClaim.append("ExteriorColor:");
                readClaim.append(result.getString("ExteriorColor"));
                readClaim.append("\n");

                readClaim.append("Vin:");
                readClaim.append(result.getString("Vin"));
                readClaim.append("\n");

                readClaim.append("LicPlate:");
                readClaim.append(result.getString("LicPlate"));
                readClaim.append("\n");

                readClaim.append("LicPlateState:");
                readClaim.append(result.getString("LicPlateState"));
                readClaim.append("\n");

                readClaim.append("LicPlateExpDate:");
                readClaim.append(result.getString("LicPlateExpDate"));
                readClaim.append("\n");

                readClaim.append("DamageDescription:");
                readClaim.append(result.getString("DamageDescription"));
                readClaim.append("\n");

                readClaim.append("Mileage:");
                readClaim.append(result.getString("Mileage"));
                readClaim.append("\n\n");

            }
            return readClaim.toString();

        } catch (SQLException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getErrorCode() + ":" + se.getMessage());
                //no task
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println(se.getErrorCode() + ":" + se.getMessage());
            }
        }
        return null;
    }

    public void deleteClaim(String claimNumber) {

        try {
            createConnection();

            try {

                String deleteMitchellClaim = "DELETE from mitchellclaim WHERE ClaimNumber = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(deleteMitchellClaim);
                preparedStatement.setString(1, claimNumber);
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(createQueries.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {

                String deleteLossInfo = "DELETE from lossinfo WHERE ClaimNumber = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(deleteLossInfo);
                preparedStatement.setString(1, claimNumber);
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(createQueries.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                String deleteVehicleDetails = "DELETE from vehicledetails WHERE ClaimNumber = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(deleteVehicleDetails);
                preparedStatement.setString(1, claimNumber);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(createQueries.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {

            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.print(se.getErrorCode() + ":" + se.getMessage());
                //no task
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {

                System.out.print(se.getErrorCode() + ":" + se.getMessage());
            }
        }

    }

    public String updateClaim(newClaim claim) {

        try{
            createConnection();
            
            HashMap<String, String> updateMap = new HashMap<>();
            updateMap.put("ClaimantFirstName", claim.getClaimantFirstName());
            updateMap.put("ClaimantLastName", claim.getClaimantLastName());
            updateMap.put("Status", claim.getStatus());
            updateMap.put("AssignedAdjusterID", Long.toString(claim.getAssignedAdjusterID()));
//            updateMap LossDateError
//            updateMap.put("CauseOfLoss", claim.getLossinfo().getCauseOfLoss());
//            updateMap.put("ReportedDate",claim.getLossinfo().getReportedDate().toString());
//            updateMap.put("LossDescription",claim.getLossinfo().getLossDescription());
            updateMap.put("MakeDescription",claim.getVehicles().getVehicleDetails().getMakeDescription());
            updateMap.put("ModelDescription",claim.getVehicles().getVehicleDetails().getModelDescription());
            updateMap.put("EngineDescription",claim.getVehicles().getVehicleDetails().getEngineDescription());
            updateMap.put("ExteriorColor",claim.getVehicles().getVehicleDetails().getExteriorColor());
            updateMap.put("LicPlate",claim.getVehicles().getVehicleDetails().getLicPlate());
            updateMap.put("LicPlateState",claim.getVehicles().getVehicleDetails().getLicPlateState());
            updateMap.put("LicPlateExpDate",claim.getVehicles().getVehicleDetails().getLicPlateExpDate());
            updateMap.put("DamageDescription",claim.getVehicles().getVehicleDetails().getDamageDescription());
            updateMap.put("Vin",claim.getVehicles().getVehicleDetails().getVin());            

          
            // remove if 0 since they are Integers;
            if(claim.getVehicles().getVehicleDetails().getMileage()!=0){
                updateMap.put("Mileage",Integer.toString(claim.getVehicles().getVehicleDetails().getMileage()));
            }
            if(claim.getVehicles().getVehicleDetails().getModelYear()!=0){
                updateMap.put("ModelYear",Integer.toString(claim.getVehicles().getVehicleDetails().getModelYear()));
            }
            
            int i = 0;
            int nullValues = 0;
            
            StringBuilder updateQuery = new StringBuilder();
            updateQuery.append("UPDATE mitchellclaim natural join vehicledetails natural join lossinfo ");
            
            for (Map.Entry<String, String> m : updateMap.entrySet()) {
                if (m.getValue() != null) {
                    if (i == 0) {
                        updateQuery.append("set ").append(m.getKey()).append(" = ").append("'").append(m.getValue()).append("' ");
                        i++;
                    } else {
                        updateQuery.append(", ").append(m.getKey()).append(" = ").append("'").append(m.getValue()).append("' ");
                    }
                } 
                else{
                    nullValues++;
                }
            }

            updateQuery.append("where ClaimNumber = ").append("'").append(claim.getClaimNumber()).append("'");
                
            if(nullValues!=updateMap.size()){
                stmt = conn.createStatement();
                stmt.executeUpdate(updateQuery.toString());
            }
            return updateQuery.toString();
             

        } catch (SQLException ex) {
            Logger.getLogger(createQueries.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    
    }
}

