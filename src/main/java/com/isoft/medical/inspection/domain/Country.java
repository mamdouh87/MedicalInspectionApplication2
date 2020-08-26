package com.isoft.medical.inspection.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Country extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "country_name_en")
    private String countryNameEn;

    @Column(name = "country_name_ar")
    private String countryNameAr;

    @Column(name = "country_code")
    private String countryCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public Country countryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
        return this;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCountryNameAr() {
        return countryNameAr;
    }

    public Country countryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
        return this;
    }

    public void setCountryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Country countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return id != null && id.equals(((Country) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Country{" +
            "id=" + getId() +
            ", countryNameEn='" + getCountryNameEn() + "'" +
            ", countryNameAr='" + getCountryNameAr() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            "}";
    }
}
