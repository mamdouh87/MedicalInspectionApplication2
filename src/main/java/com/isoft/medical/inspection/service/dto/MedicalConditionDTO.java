package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.MedicalCondition} entity.
 */
public class MedicalConditionDTO implements Serializable {
    
    private Long id;

    private String conditionNameEn;

    private String conditionNameAr;


    private Long inspectionTypeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConditionNameEn() {
        return conditionNameEn;
    }

    public void setConditionNameEn(String conditionNameEn) {
        this.conditionNameEn = conditionNameEn;
    }

    public String getConditionNameAr() {
        return conditionNameAr;
    }

    public void setConditionNameAr(String conditionNameAr) {
        this.conditionNameAr = conditionNameAr;
    }

    public Long getInspectionTypeId() {
        return inspectionTypeId;
    }

    public void setInspectionTypeId(Long inspectionTypeId) {
        this.inspectionTypeId = inspectionTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalConditionDTO)) {
            return false;
        }

        return id != null && id.equals(((MedicalConditionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalConditionDTO{" +
            "id=" + getId() +
            ", conditionNameEn='" + getConditionNameEn() + "'" +
            ", conditionNameAr='" + getConditionNameAr() + "'" +
            ", inspectionTypeId=" + getInspectionTypeId() +
            "}";
    }
}
