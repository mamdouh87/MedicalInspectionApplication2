package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.OphthalmicInspection} entity.
 */
public class OphthalmicInspectionDTO implements Serializable {
    
    private Long id;

    private String rigthEye;

    private String leftEye;

    private String doctorComments;


    private Long medicalConditionId;

    private Long inspectionResultId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRigthEye() {
        return rigthEye;
    }

    public void setRigthEye(String rigthEye) {
        this.rigthEye = rigthEye;
    }

    public String getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(String leftEye) {
        this.leftEye = leftEye;
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
        if (!(o instanceof OphthalmicInspectionDTO)) {
            return false;
        }

        return id != null && id.equals(((OphthalmicInspectionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OphthalmicInspectionDTO{" +
            "id=" + getId() +
            ", rigthEye='" + getRigthEye() + "'" +
            ", leftEye='" + getLeftEye() + "'" +
            ", doctorComments='" + getDoctorComments() + "'" +
            ", medicalConditionId=" + getMedicalConditionId() +
            ", inspectionResultId=" + getInspectionResultId() +
            "}";
    }
}
