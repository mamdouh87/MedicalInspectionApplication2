package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.MedicalCondition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedicalCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicalConditionRepository extends JpaRepository<MedicalCondition, Long> {
}
