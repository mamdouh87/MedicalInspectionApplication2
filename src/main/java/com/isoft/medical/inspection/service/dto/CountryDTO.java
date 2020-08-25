package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.Country} entity.
 */
public class CountryDTO implements Serializable {
    
    private Long id;

    private String countryNameEn;

    private String countryNameAr;

    private String countryCode;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCountryNameAr() {
        return countryNameAr;
    }

    public void setCountryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CountryDTO)) {
            return false;
        }

        return id != null && id.equals(((CountryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CountryDTO{" +
            "id=" + getId() +
            ", countryNameEn='" + getCountryNameEn() + "'" +
            ", countryNameAr='" + getCountryNameAr() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            "}";
    }
}
