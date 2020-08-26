package com.isoft.medical.inspection.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OphthalmicInspection.
 */
@Entity
@Table(name = "ophthalmic_inspection")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OphthalmicInspection extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "rigth_eye")
    private String rigthEye;

    @Column(name = "left_eye")
    private String leftEye;

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

    public String getRigthEye() {
        return rigthEye;
    }

    public OphthalmicInspection rigthEye(String rigthEye) {
        this.rigthEye = rigthEye;
        return this;
    }

    public void setRigthEye(String rigthEye) {
        this.rigthEye = rigthEye;
    }

    public String getLeftEye() {
        return leftEye;
    }

    public OphthalmicInspection leftEye(String leftEye) {
        this.leftEye = leftEye;
        return this;
    }

    public void setLeftEye(String leftEye) {
        this.leftEye = leftEye;
    }

    public String getDoctorComments() {
        return doctorComments;
    }

    public OphthalmicInspection doctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
        return this;
    }

    public void setDoctorComments(String doctorComments) {
        this.doctorComments = doctorComments;
    }

    public MedicalCondition getMedicalCondition() {
        return medicalCondition;
    }

    public OphthalmicInspection medicalCondition(MedicalCondition medicalCondition) {
        this.medicalCondition = medicalCondition;
        return this;
    }

    public void setMedicalCondition(MedicalCondition medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public InspectionResult getInspectionResult() {
        return inspectionResult;
    }

    public OphthalmicInspection inspectionResult(InspectionResult inspectionResult) {
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
        if (!(o instanceof OphthalmicInspection)) {
            return false;
        }
        return id != null && id.equals(((OphthalmicInspection) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OphthalmicInspection{" +
            "id=" + getId() +
            ", rigthEye='" + getRigthEye() + "'" +
            ", leftEye='" + getLeftEye() + "'" +
            ", doctorComments='" + getDoctorComments() + "'" +
            "}";
    }
}
