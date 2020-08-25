package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.InspectionRequirement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InspectionRequirement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionRequirementRepository extends JpaRepository<InspectionRequirement, Long> {
}
