package com.isoft.medical.inspection.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MedicalCondition.
 */
@Entity
@Table(name = "medical_condition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalCondition extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "condition_name_en")
    private String conditionNameEn;

    @Column(name = "condition_name_ar")
    private String conditionNameAr;

    @OneToOne
    @JoinColumn(unique = true)
    private InspectionType inspectionType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConditionNameEn() {
        return conditionNameEn;
    }

    public MedicalCondition conditionNameEn(String conditionNameEn) {
        this.conditionNameEn = conditionNameEn;
        return this;
    }

    public void setConditionNameEn(String conditionNameEn) {
        this.conditionNameEn = conditionNameEn;
    }

    public String getConditionNameAr() {
        return conditionNameAr;
    }

    public MedicalCondition conditionNameAr(String conditionNameAr) {
        this.conditionNameAr = conditionNameAr;
        return this;
    }

    public void setConditionNameAr(String conditionNameAr) {
        this.conditionNameAr = conditionNameAr;
    }

    public InspectionType getInspectionType() {
        return inspectionType;
    }

    public MedicalCondition inspectionType(InspectionType inspectionType) {
        this.inspectionType = inspectionType;
        return this;
    }

    public void setInspectionType(InspectionType inspectionType) {
        this.inspectionType = inspectionType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalCondition)) {
            return false;
        }
        return id != null && id.equals(((MedicalCondition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalCondition{" +
            "id=" + getId() +
            ", conditionNameEn='" + getConditionNameEn() + "'" +
            ", conditionNameAr='" + getConditionNameAr() + "'" +
            "}";
    }
}
