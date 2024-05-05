package maybank.insurance.dao.entity;

import org.springframework.data.relational.core.sql.In;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class InsuranceAvailable {
    @NotNull(message = "{available.insuranceId.null}")
    @Digits(integer = 9, fraction = 0, message = "{available.insuranceId.invalid}")
    private Integer insuranceId;
    @NotNull(message = "{available.insuranceType.null}")
    private String insuranceType;
    @NotNull(message = "{available.insuranceName.null}")
    private String insuranceName;
    @NotNull(message = "{available.insuranceKeyBenefits.null}")
    private String insuranceKeyBenefits;
    @NotNull(message = "{available.insuranceLifetime.null}")
    @Digits(integer = 4, fraction = 0, message = "{user.customerId.null}")
    private Integer insuranceLifetime;

    @Override
    public String toString() {
        return "InsuranceAvailable{" +
                "insuranceId=" + insuranceId +
                ", insuranceType='" + insuranceType + '\'' +
                ", insuranceName='" + insuranceName + '\'' +
                ", insuranceKeyBenefits='" + insuranceKeyBenefits + '\'' +
                ", insuranceLifetime=" + insuranceLifetime +
                '}';
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
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

    public void setInsuranceKeyBenefits(String insuranceKeyBenefits) {
        this.insuranceKeyBenefits = insuranceKeyBenefits;
    }

    public void setInsuranceLifetime(Integer insuranceLifetime) {
        this.insuranceLifetime = insuranceLifetime;
    }

    public InsuranceAvailable() {
    }

    public InsuranceAvailable(Integer insuranceId, String insuranceType, String insuranceName, String insuranceKeyBenefits, Integer insuranceLifetime) {
        this.insuranceId = insuranceId;
        this.insuranceType = insuranceType;
        this.insuranceName = insuranceName;
        this.insuranceKeyBenefits = insuranceKeyBenefits;
        this.insuranceLifetime = insuranceLifetime;
    }
}
