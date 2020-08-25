package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.AbdominalInseption} entity.
 */
public class AbdominalInseptionDTO implements Serializable {
    
    private Long id;

    private String bloodGroup;

    private String doctorComments;


    private Long medicalConditionId;

    private Long inspectionResultId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDoctorComments() {
        return doctorComments;
    }

    public void setDoctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
    }

    public Long getMedicalConditionId() {
        return medicalConditionId;
    }

    public void setMedicalConditionId(Long medicalConditionId) {
        this.medicalConditionId = medicalConditionId;
    }

    public Long getInspectionResultId() {
        return inspectionResultId;
    }

    public void setInspectionResultId(Long inspectionResultId) {
        this.inspectionResultId = inspectionResultId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbdominalInseptionDTO)) {
            return false;
        }

        return id != null && id.equals(((AbdominalInseptionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AbdominalInseptionDTO{" +
            "id=" + getId() +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", doctorComments='" + getDoctorComments() + "'" +
            ", medicalConditionId=" + getMedicalConditionId() +
            ", inspectionResultId=" + getInspectionResultId() +
            "}";
    }
}
