package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.InspectionResult} entity.
 */
public class InspectionResultDTO implements Serializable {
    
    private Long id;

    private String nameEn;

    private String nameAr;

    private String code;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionResultDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionResultDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionResultDTO{" +
            "id=" + getId() +
            ", nameEn='" + getNameEn() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
