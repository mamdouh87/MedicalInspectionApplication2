package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.TrafficUnits} entity.
 */
public class TrafficUnitsDTO implements Serializable {
    
    private Long id;

    private String trafficUnitNameEn;

    private String trafficUnitNameAr;

    private String trafficUnitCode;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrafficUnitNameEn() {
        return trafficUnitNameEn;
    }

    public void setTrafficUnitNameEn(String trafficUnitNameEn) {
        this.trafficUnitNameEn = trafficUnitNameEn;
    }

    public String getTrafficUnitNameAr() {
        return trafficUnitNameAr;
    }

    public void setTrafficUnitNameAr(String trafficUnitNameAr) {
        this.trafficUnitNameAr = trafficUnitNameAr;
    }

    public String getTrafficUnitCode() {
        return trafficUnitCode;
    }

    public void setTrafficUnitCode(String trafficUnitCode) {
        this.trafficUnitCode = trafficUnitCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrafficUnitsDTO)) {
            return false;
        }

        return id != null && id.equals(((TrafficUnitsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrafficUnitsDTO{" +
            "id=" + getId() +
            ", trafficUnitNameEn='" + getTrafficUnitNameEn() + "'" +
            ", trafficUnitNameAr='" + getTrafficUnitNameAr() + "'" +
            ", trafficUnitCode='" + getTrafficUnitCode() + "'" +
            "}";
    }
}
