package com.test;

/**
 *
 * @author Saumil
 */
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "VehicleDetails")
class VehicleDetails {

    private String Vin;
    private int ModelYear;
    private String MakeDescription;
    private String ModelDescription;
    private String EngineDescription;
    private String ExteriorColor;
    private String LicPlate;
    private String LicPlateState;
    private String LicPlateExpDate;
    private String DamageDescription;
    private int Mileage;

    @XmlElement(name = "Vin")
    public String getVin() {
        return Vin;
    }

    public void setVin(String Vin) {
        this.Vin = Vin;
    }

    @XmlElement(name = "ModelYear")
    public int getModelYear() {
        return ModelYear;
    }

    public void setModelYear(int ModelYear) {
        this.ModelYear = ModelYear;
    }

    @XmlElement(name = "MakeDescription")
    public String getMakeDescription() {
        return MakeDescription;
    }

    public void setMakeDescription(String MakeDescription) {
        this.MakeDescription = MakeDescription;
    }

    @XmlElement(name = "ModelDescription")
    public String getModelDescription() {
        return ModelDescription;
    }

    public void setModelDescription(String ModelDescription) {
        this.ModelDescription = ModelDescription;
    }

    @XmlElement(name = "EngineDescription")
    public String getEngineDescription() {
        return EngineDescription;
    }

    public void setEngineDescription(String EngineDescription) {
        this.EngineDescription = EngineDescription;
    }

    @XmlElement(name = "ExteriorColor")
    public String getExteriorColor() {
        return ExteriorColor;
    }

    public void setExteriorColor(String ExteriorColor) {
        this.ExteriorColor = ExteriorColor;
    }

    @XmlElement(name = "LicPlate")
    public String getLicPlate() {
        return LicPlate;
    }

    public void setLicPlate(String LicPlate) {
        this.LicPlate = LicPlate;
    }

    @XmlElement(name = "LicPlateState")
    public String getLicPlateState() {
        return LicPlateState;
    }

    public void setLicPlateState(String LicPlateState) {
        this.LicPlateState = LicPlateState;
    }

    @XmlElement(name = "LicPlateExpDate")
    public String getLicPlateExpDate() {
        return LicPlateExpDate;
    }

    public void setLicPlateExpDate(String LicPlateExpDate) {
        this.LicPlateExpDate = LicPlateExpDate;
    }

    @XmlElement(name = "DamageDescription")
    public String getDamageDescription() {
        return DamageDescription;
    }

    public void setDamageDescription(String DamageDescription) {
        this.DamageDescription = DamageDescription;
    }

    @XmlElement(name = "Mileage")
    public int getMileage() {
        return Mileage;
    }

    public void setMileage(int Mileage) {
        this.Mileage = Mileage;
    }
}
