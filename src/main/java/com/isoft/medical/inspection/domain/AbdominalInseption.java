package com.isoft.medical.inspection.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AbdominalInseption.
 */
@Entity
@Table(name = "abdominal_inseption")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AbdominalInseption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "doctor_comments")
    private String doctorComments;

    @OneToOne
    @JoinColumn(unique = true)
    private MedicalCondition medicalCondition;

    @OneToOne
    @JoinColumn(unique = true)
    private InspectionResult inspectionResult;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public AbdominalInseption bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDoctorComments() {
        return doctorComments;
    }

    public AbdominalInseption doctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
        return this;
    }

    public void setDoctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
    }

    public MedicalCondition getMedicalCondition() {
        return medicalCondition;
    }

    public AbdominalInseption medicalCondition(MedicalCondition medicalCondition) {
        this.medicalCondition = medicalCondition;
        return this;
    }

    public void setMedicalCondition(MedicalCondition medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public InspectionResult getInspectionResult() {
        return inspectionResult;
    }

    public AbdominalInseption inspectionResult(InspectionResult inspectionResult) {
        this.inspectionResult = inspectionResult;
        return this;
    }

    public void setInspectionResult(InspectionResult inspectionResult) {
        this.inspectionResult = inspectionResult;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbdominalInseption)) {
            return false;
        }
        return id != null && id.equals(((AbdominalInseption) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AbdominalInseption{" +
            "id=" + getId() +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", doctorComments='" + getDoctorComments() + "'" +
            "}";
    }
}
