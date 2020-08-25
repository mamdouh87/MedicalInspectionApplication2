package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.LicenseCategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LicenseCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LicenseCategoryRepository extends JpaRepository<LicenseCategory, Long> {
}
