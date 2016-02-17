package com.test;

/* @author
    Saumil jagirdar
    *Imp - Dont use java.sql.Date - otherwise error

*/
import java.util.Date;
import java.sql.Timestamp;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {
      @Override
      public Date marshal(Timestamp v) {
          return new Date(v.getTime());
      }
      @Override
      public Timestamp unmarshal(Date v) {
          return new Timestamp(v.getTime());
      }
  }