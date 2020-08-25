package com.isoft.medical.inspection.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A InspectionRequirement.
 */
@Entity
@Table(name = "inspection_requirement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionRequirement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "code")
    private String code;

    @Column(name = "jhi_order")
    private Integer order;

    @ManyToMany(mappedBy = "requirements")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<MedicalInsepctionRequests> requests = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public InspectionRequirement nameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameAr() {
        return nameAr;
    }

    public InspectionRequirement nameAr(String nameAr) {
        this.nameAr = nameAr;
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getCode() {
        return code;
    }

    public InspectionRequirement code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrder() {
        return order;
    }

    public InspectionRequirement order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Set<MedicalInsepctionRequests> getRequests() {
        return requests;
    }

    public InspectionRequirement requests(Set<MedicalInsepctionRequests> medicalInsepctionRequests) {
        this.requests = medicalInsepctionRequests;
        return this;
    }

    public InspectionRequirement addRequests(MedicalInsepctionRequests medicalInsepctionRequests) {
        this.requests.add(medicalInsepctionRequests);
        medicalInsepctionRequests.getRequirements().add(this);
        return this;
    }

    public InspectionRequirement removeRequests(MedicalInsepctionRequests medicalInsepctionRequests) {
        this.requests.remove(medicalInsepctionRequests);
        medicalInsepctionRequests.getRequirements().remove(this);
        return this;
    }

    public void setRequests(Set<MedicalInsepctionRequests> medicalInsepctionRequests) {
        this.requests = medicalInsepctionRequests;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionRequirement)) {
            return false;
        }
        return id != null && id.equals(((InspectionRequirement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionRequirement{" +
            "id=" + getId() +
            ", nameEn='" + getNameEn() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", code='" + getCode() + "'" +
            ", order=" + getOrder() +
            "}";
    }
}
