package com.isoft.medical.inspection.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.isoft.medical.inspection.domain.Persons} entity.
 */
public class PersonsDTO implements Serializable {
    
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String familyName;

    private String fullName;

    private LocalDate birthDate;

    private String nationalId;

    private String passportNumber;


    private Long passportIssueCountryId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Long getPassportIssueCountryId() {
        return passportIssueCountryId;
    }

    public void setPassportIssueCountryId(Long countryId) {
        this.passportIssueCountryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonsDTO)) {
            return false;
        }

        return id != null && id.equals(((PersonsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonsDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", familyName='" + getFamilyName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", nationalId='" + getNationalId() + "'" +
            ", passportNumber='" + getPassportNumber() + "'" +
            ", passportIssueCountryId=" + getPassportIssueCountryId() +
            "}";
    }
}
