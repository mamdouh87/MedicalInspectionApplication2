package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.InspectionType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InspectionType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionTypeRepository extends JpaRepository<InspectionType, Long> {
}
