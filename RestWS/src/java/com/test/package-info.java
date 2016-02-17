
/**
 *
 * @author Saumil
 */
@XmlJavaTypeAdapters(  
 @XmlJavaTypeAdapter(value=TimestampAdapter.class,type=Timestamp.class))

    @XmlSchema(
    namespace = "http://www.mitchell.com/examples/claim",
    elementFormDefault = XmlNsForm.QUALIFIED,
    xmlns = {
        @XmlNs(prefix="cla", namespaceURI="http://www.example.com/a")
    }
)  
package com.test;
import java.sql.Timestamp;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

    