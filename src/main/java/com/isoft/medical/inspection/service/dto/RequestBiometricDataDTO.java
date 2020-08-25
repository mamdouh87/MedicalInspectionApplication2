package com.isoft.medical.inspection.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.RequestBiometricData} entity.
 */
public class RequestBiometricDataDTO implements Serializable {
    
    private Long id;

    @Lob
    private byte[] image;

    private String imageContentType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestBiometricDataDTO)) {
            return false;
        }

        return id != null && id.equals(((RequestBiometricDataDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestBiometricDataDTO{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            "}";
    }
}
