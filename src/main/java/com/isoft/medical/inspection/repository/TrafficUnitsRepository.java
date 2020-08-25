package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.TrafficUnits;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TrafficUnits entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrafficUnitsRepository extends JpaRepository<TrafficUnits, Long> {
}
