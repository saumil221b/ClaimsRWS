package com.test;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.xml.sax.InputSource;

public class xmlUnmarshall {
    createQueries q = new createQueries();
    
    public void unmarshall(String s){
        
        try {
            JAXBContext jc = JAXBContext.newInstance(newClaim.class);
            Unmarshaller ums = jc.createUnmarshaller();
            //test file : newClaim claim = (newClaim) ums.unmarshal(new File("C:\\Users\\Saumil\\Documents\\NetBeansProjects\\Practice Qs\\RestWS\\src\\java\\com\\test\\claims.xml"));
            InputSource inputSource = new InputSource( new StringReader(s) );
            newClaim claim = (newClaim) ums.unmarshal(inputSource);
            q.CreateClaim(claim);
            
            System.out.println("Vehicle vin   : " + claim.getVehicles().getVehicleDetails().getVin());
            
        } catch (Exception e) {
            
            System.out.print(e.getMessage());
            
        }
    }
}
