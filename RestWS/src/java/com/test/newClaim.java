package com.test;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Saumil
 */
@XmlRootElement(name = "MitchellClaim")
public class newClaim {

    private String ClaimNumber;
    private String ClaimantFirstName;
    private String ClaimantLastName;
    private String Status;

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    private Timestamp LossDate;
    private long AssignedAdjusterID;

    private Vehicles vehicles;

    @XmlElement(name = "AssignedAdjusterID")
    public long getAssignedAdjusterID() {
        return AssignedAdjusterID;
    }

    public void setAssignedAdjusterID(long AssignedAdjusterID) {
        this.AssignedAdjusterID = AssignedAdjusterID;
    }

    @XmlElement(name = "Vehicles")
    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    private LossInfo lossinfo;

    public LossInfo getLossinfo() {
        return lossinfo;
    }

    @XmlElement(name = "LossInfo")
    public void setLossinfo(LossInfo lossinfo) {
        this.lossinfo = lossinfo;
    }

    @XmlElement(name = "ClaimNumber")
    public String getClaimNumber() {
        return ClaimNumber;
    }

    public void setClaimNumber(String ClaimNumber) {
        this.ClaimNumber = ClaimNumber;
    }

    @XmlElement(name = "ClaimantFirstName")
    public String getClaimantFirstName() {
        return ClaimantFirstName;
    }

    public void setClaimantFirstName(String ClaimantFirstName) {
        this.ClaimantFirstName = ClaimantFirstName;
    }

    @XmlElement(name = "ClaimantLastName")
    public String getClaimantLastName() {
        return ClaimantLastName;
    }

    public void setClaimantLastName(String ClaimantLastName) {
        this.ClaimantLastName = ClaimantLastName;
    }

    @XmlElement(name = "Status")
    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @XmlElement(name = "LossDate")
    public Timestamp getLossDate() {
        return LossDate;
    }

    public void setLossDate(Timestamp LossDate) {
        this.LossDate = LossDate;
    }

    public newClaim(String ClaimNumber, String ClaimantFirstName, String ClaimantLastName, String Status, Timestamp LossDate, long AssignedAdjusterID) {
        this.ClaimNumber = ClaimNumber;
        this.ClaimantFirstName = ClaimantFirstName;
        this.ClaimantLastName = ClaimantLastName;
        this.Status = Status;
        this.LossDate = LossDate;
        this.AssignedAdjusterID = AssignedAdjusterID;
    }

    public newClaim() {

        super();
    }
}
