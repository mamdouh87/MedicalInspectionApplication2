package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.InspectionResult;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InspectionResult entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionResultRepository extends JpaRepository<InspectionResult, Long> {
}
