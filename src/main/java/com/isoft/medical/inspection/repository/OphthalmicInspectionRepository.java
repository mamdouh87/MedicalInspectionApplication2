package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.OphthalmicInspection;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OphthalmicInspection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OphthalmicInspectionRepository extends JpaRepository<OphthalmicInspection, Long> {
}
