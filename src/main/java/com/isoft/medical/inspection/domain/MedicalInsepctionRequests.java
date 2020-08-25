package com.isoft.medical.inspection.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MedicalInsepctionRequests.
 */
@Entity
@Table(name = "medical_insepction_requests")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalInsepctionRequests implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "request_number")
    private String requestNumber;

    @Column(name = "exported")
    private Boolean exported;

    @OneToOne
    @JoinColumn(unique = true)
    private AbdominalInseption abdominalInseption;

    @OneToOne
    @JoinColumn(unique = true)
    private OphthalmicInspection ophthalmicInspection;

    @OneToOne
    @JoinColumn(unique = true)
    private Persons person;

    @OneToOne
    @JoinColumn(unique = true)
    private RequestBiometricData biometricdata;

    @OneToOne
    @JoinColumn(unique = true)
    private LicenseCategory licenseCategory;

    @OneToOne
    @JoinColumn(unique = true)
    private TransactionType transacionType;

    @OneToOne
    @JoinColumn(unique = true)
    private TrafficUnits trafficUnit;

    @OneToOne
    @JoinColumn(unique = true)
    private RequestStatus status;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "medical_insepction_requests_requirements",
               joinColumns = @JoinColumn(name = "medical_insepction_requests_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "requirements_id", referencedColumnName = "id"))
    private Set<InspectionRequirement> requirements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public MedicalInsepctionRequests requestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
        return this;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Boolean isExported() {
        return exported;
    }

    public MedicalInsepctionRequests exported(Boolean exported) {
        this.exported = exported;
        return this;
    }

    public void setExported(Boolean exported) {
        this.exported = exported;
    }

    public AbdominalInseption getAbdominalInseption() {
        return abdominalInseption;
    }

    public MedicalInsepctionRequests abdominalInseption(AbdominalInseption abdominalInseption) {
        this.abdominalInseption = abdominalInseption;
        return this;
    }

    public void setAbdominalInseption(AbdominalInseption abdominalInseption) {
        this.abdominalInseption = abdominalInseption;
    }

    public OphthalmicInspection getOphthalmicInspection() {
        return ophthalmicInspection;
    }

    public MedicalInsepctionRequests ophthalmicInspection(OphthalmicInspection ophthalmicInspection) {
        this.ophthalmicInspection = ophthalmicInspection;
        return this;
    }

    public void setOphthalmicInspection(OphthalmicInspection ophthalmicInspection) {
        this.ophthalmicInspection = ophthalmicInspection;
    }

    public Persons getPerson() {
        return person;
    }

    public MedicalInsepctionRequests person(Persons persons) {
        this.person = persons;
        return this;
    }

    public void setPerson(Persons persons) {
        this.person = persons;
    }

    public RequestBiometricData getBiometricdata() {
        return biometricdata;
    }

    public MedicalInsepctionRequests biometricdata(RequestBiometricData requestBiometricData) {
        this.biometricdata = requestBiometricData;
        return this;
    }

    public void setBiometricdata(RequestBiometricData requestBiometricData) {
        this.biometricdata = requestBiometricData;
    }

    public LicenseCategory getLicenseCategory() {
        return licenseCategory;
    }

    public MedicalInsepctionRequests licenseCategory(LicenseCategory licenseCategory) {
        this.licenseCategory = licenseCategory;
        return this;
    }

    public void setLicenseCategory(LicenseCategory licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public TransactionType getTransacionType() {
        return transacionType;
    }

    public MedicalInsepctionRequests transacionType(TransactionType transactionType) {
        this.transacionType = transactionType;
        return this;
    }

    public void setTransacionType(TransactionType transactionType) {
        this.transacionType = transactionType;
    }

    public TrafficUnits getTrafficUnit() {
        return trafficUnit;
    }

    public MedicalInsepctionRequests trafficUnit(TrafficUnits trafficUnits) {
        this.trafficUnit = trafficUnits;
        return this;
    }

    public void setTrafficUnit(TrafficUnits trafficUnits) {
        this.trafficUnit = trafficUnits;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public MedicalInsepctionRequests status(RequestStatus requestStatus) {
        this.status = requestStatus;
        return this;
    }

    public void setStatus(RequestStatus requestStatus) {
        this.status = requestStatus;
    }

    public Set<InspectionRequirement> getRequirements() {
        return requirements;
    }

    public MedicalInsepctionRequests requirements(Set<InspectionRequirement> inspectionRequirements) {
        this.requirements = inspectionRequirements;
        return this;
    }

    public MedicalInsepctionRequests addRequirements(InspectionRequirement inspectionRequirement) {
        this.requirements.add(inspectionRequirement);
        inspectionRequirement.getRequests().add(this);
        return this;
    }

    public MedicalInsepctionRequests removeRequirements(InspectionRequirement inspectionRequirement) {
        this.requirements.remove(inspectionRequirement);
        inspectionRequirement.getRequests().remove(this);
        return this;
    }

    public void setRequirements(Set<InspectionRequirement> inspectionRequirements) {
        this.requirements = inspectionRequirements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalInsepctionRequests)) {
            return false;
        }
        return id != null && id.equals(((MedicalInsepctionRequests) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalInsepctionRequests{" +
            "id=" + getId() +
            ", requestNumber='" + getRequestNumber() + "'" +
            ", exported='" + isExported() + "'" +
            "}";
    }
}
