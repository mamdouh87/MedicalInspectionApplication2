package com.isoft.medical.inspection.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TrafficUnits.
 */
@Entity
@Table(name = "traffic_units")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrafficUnits implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "traffic_unit_name_en")
    private String trafficUnitNameEn;

    @Column(name = "traffic_unit_name_ar")
    private String trafficUnitNameAr;

    @Column(name = "traffic_unit_code")
    private String trafficUnitCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrafficUnitNameEn() {
        return trafficUnitNameEn;
    }

    public TrafficUnits trafficUnitNameEn(String trafficUnitNameEn) {
        this.trafficUnitNameEn = trafficUnitNameEn;
        return this;
    }

    public void setTrafficUnitNameEn(String trafficUnitNameEn) {
        this.trafficUnitNameEn = trafficUnitNameEn;
    }

    public String getTrafficUnitNameAr() {
        return trafficUnitNameAr;
    }

    public TrafficUnits trafficUnitNameAr(String trafficUnitNameAr) {
        this.trafficUnitNameAr = trafficUnitNameAr;
        return this;
    }

    public void setTrafficUnitNameAr(String trafficUnitNameAr) {
        this.trafficUnitNameAr = trafficUnitNameAr;
    }

    public String getTrafficUnitCode() {
        return trafficUnitCode;
    }

    public TrafficUnits trafficUnitCode(String trafficUnitCode) {
        this.trafficUnitCode = trafficUnitCode;
        return this;
    }

    public void setTrafficUnitCode(String trafficUnitCode) {
        this.trafficUnitCode = trafficUnitCode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrafficUnits)) {
            return false;
        }
        return id != null && id.equals(((TrafficUnits) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrafficUnits{" +
            "id=" + getId() +
            ", trafficUnitNameEn='" + getTrafficUnitNameEn() + "'" +
            ", trafficUnitNameAr='" + getTrafficUnitNameAr() + "'" +
            ", trafficUnitCode='" + getTrafficUnitCode() + "'" +
            "}";
    }
}
