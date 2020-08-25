package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.RequestBiometricData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RequestBiometricData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestBiometricDataRepository extends JpaRepository<RequestBiometricData, Long> {
}
