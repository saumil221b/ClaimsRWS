package com.test;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.xml.sax.InputSource;

public class xmlUnmarshall {
    createQueries q = new createQueries();
    
    public void createClaim(String s){
       
        
        try {
            JAXBContext jc = JAXBContext.newInstance(newClaim.class);
            Unmarshaller ums = jc.createUnmarshaller();
            //test file : newClaim claim = (newClaim) ums.unmarshal(new File("C:\\Users\\Saumil\\Documents\\NetBeansProjects\\Practice Qs\\RestWS\\src\\java\\com\\test\\claims.xml"));
            InputSource inputSource = new InputSource( new StringReader(s) );
            newClaim claim = (newClaim) ums.unmarshal(inputSource);
            q.createClaim(claim);
            
        } catch (Exception e) {
            
            System.out.print(e.getMessage());
            
        }
    }
    
    public String updateClaim(String s){
       
        newClaim claim=null;
        try {
            JAXBContext jc = JAXBContext.newInstance(newClaim.class);
            Unmarshaller ums = jc.createUnmarshaller();
            InputSource inputSource = new InputSource( new StringReader(s) );
            
            claim = (newClaim) ums.unmarshal(inputSource);
            return q.updateClaim(claim);
            
        } catch (Exception e) {
            
            System.out.print(e.getMessage());
            return "In catch xml";
        }  
    }
}
