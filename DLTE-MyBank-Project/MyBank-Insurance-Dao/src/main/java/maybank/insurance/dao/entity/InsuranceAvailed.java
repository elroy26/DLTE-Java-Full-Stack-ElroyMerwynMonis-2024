package maybank.insurance.dao.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class InsuranceAvailed {

//    @NotNull(message = "{availed.insuranceAvailedId.null}")
//    @Digits(integer = 8, fraction = 0, message = "{availed.insuranceAvailedId.invalid}")
//    private Integer insuranceAvailedId;
    @NotNull(message = "{availed.insuranceType.null}")
    private String insuranceType;
    @NotNull(message = "{availed.insuranceName.null}")
    private String insuranceName;
    @NotNull(message = "{availed.insuranceKeyBenefits.null}")
    private String insuranceKeyBenefits;
    @NotNull(message = "{availed.insuranceLifetime.null}")
    @Digits(integer = 2, fraction = 0, message = "{availed.insuranceLifetime.invalid}")
    private Integer insuranceLifetime;
    @NotNull(message = "{availed.insurancePremium.null}")
    @Digits(integer = Integer.MAX_VALUE, fraction =2, message = "{availed.insurancePremium.invalid}")
    private Double insurancePremium;
    @NotNull(message = "{availed.customerId.null}")
    @Digits(integer = 8, fraction = 0, message = "{user.customerId.null}")
    private Integer customerId;
    @NotNull(message = "{availed.insuranceId.null}")
    @Digits(integer = 8, fraction = 0, message = "{availed.insuranceId.invalid}")
    private Integer insuranceId;
    @NotNull(message = "{availed.insuranceCoverage.null}")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "{availed.insuranceCoverage.invalid}")
    private Double insuranceCoverage;

    public InsuranceAvailed() {
    }


    public InsuranceAvailed(Integer customerId,Integer insuranceId,String insuranceType, String insuranceName, String insuranceKeyBenefits, Integer insuranceLifetime, Double insurancePremium,Double insuranceCoverage){
        this.insuranceType = insuranceType;
        this.insuranceName = insuranceName;
        this.insuranceKeyBenefits = insuranceKeyBenefits;
        this.insuranceLifetime = insuranceLifetime;
        this.insurancePremium = insurancePremium;
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        this.insuranceCoverage = insuranceCoverage;
    }


    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceKeyBenefits() {
        return insuranceKeyBenefits;
    }

    public void setInsuranceKeyBenefits(String insuranceKeyBenefits) {
        this.insuranceKeyBenefits = insuranceKeyBenefits;
    }

    public Integer getInsuranceLifetime() {
        return insuranceLifetime;
    }

    public void setInsuranceLifetime(Integer insuranceLifetime) {
        this.insuranceLifetime = insuranceLifetime;
    }

    public Double getInsurancePremium() {
        return insurancePremium;
    }

    public void setInsurancePremium(Double insurancePremium) {
        this.insurancePremium = insurancePremium;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Double getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public void setInsuranceCoverage(Double insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
    }

    @Override
    public String toString() {
        return "InsuranceAvailed{" +
                ", insuranceType='" + insuranceType + '\'' +
                ", insuranceName='" + insuranceName + '\'' +
                ", insuranceKeyBenefits='" + insuranceKeyBenefits + '\'' +
                ", insuranceLifetime=" + insuranceLifetime +
                ", insurancePremium=" + insurancePremium +
                ", customerId=" + customerId +
                ", insuranceId=" + insuranceId +
                ", insuranceCoverage=" + insuranceCoverage +
                '}';
    }
}
