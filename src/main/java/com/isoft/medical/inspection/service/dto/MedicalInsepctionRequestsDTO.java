package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.MedicalInsepctionRequests} entity.
 */
public class MedicalInsepctionRequestsDTO implements Serializable {
    
    private Long id;

    private String requestNumber;

    private Boolean exported;


    private Long abdominalInseptionId;

    private Long ophthalmicInspectionId;

    private Long personId;

    private Long biometricdataId;

    private Long licenseCategoryId;

    private Long transacionTypeId;

    private Long trafficUnitId;

    private Long statusId;
    private Set<InspectionRequirementDTO> requirements = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Boolean isExported() {
        return exported;
    }

    public void setExported(Boolean exported) {
        this.exported = exported;
    }

    public Long getAbdominalInseptionId() {
        return abdominalInseptionId;
    }

    public void setAbdominalInseptionId(Long abdominalInseptionId) {
        this.abdominalInseptionId = abdominalInseptionId;
    }

    public Long getOphthalmicInspectionId() {
        return ophthalmicInspectionId;
    }

    public void setOphthalmicInspectionId(Long ophthalmicInspectionId) {
        this.ophthalmicInspectionId = ophthalmicInspectionId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personsId) {
        this.personId = personsId;
    }

    public Long getBiometricdataId() {
        return biometricdataId;
    }

    public void setBiometricdataId(Long requestBiometricDataId) {
        this.biometricdataId = requestBiometricDataId;
    }

    public Long getLicenseCategoryId() {
        return licenseCategoryId;
    }

    public void setLicenseCategoryId(Long licenseCategoryId) {
        this.licenseCategoryId = licenseCategoryId;
    }

    public Long getTransacionTypeId() {
        return transacionTypeId;
    }

    public void setTransacionTypeId(Long transactionTypeId) {
        this.transacionTypeId = transactionTypeId;
    }

    public Long getTrafficUnitId() {
        return trafficUnitId;
    }

    public void setTrafficUnitId(Long trafficUnitsId) {
        this.trafficUnitId = trafficUnitsId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long requestStatusId) {
        this.statusId = requestStatusId;
    }

    public Set<InspectionRequirementDTO> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<InspectionRequirementDTO> inspectionRequirements) {
        this.requirements = inspectionRequirements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalInsepctionRequestsDTO)) {
            return false;
        }

        return id != null && id.equals(((MedicalInsepctionRequestsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalInsepctionRequestsDTO{" +
            "id=" + getId() +
            ", requestNumber='" + getRequestNumber() + "'" +
            ", exported='" + isExported() + "'" +
            ", abdominalInseptionId=" + getAbdominalInseptionId() +
            ", ophthalmicInspectionId=" + getOphthalmicInspectionId() +
            ", personId=" + getPersonId() +
            ", biometricdataId=" + getBiometricdataId() +
            ", licenseCategoryId=" + getLicenseCategoryId() +
            ", transacionTypeId=" + getTransacionTypeId() +
            ", trafficUnitId=" + getTrafficUnitId() +
            ", statusId=" + getStatusId() +
            ", requirements='" + getRequirements() + "'" +
            "}";
    }
}
