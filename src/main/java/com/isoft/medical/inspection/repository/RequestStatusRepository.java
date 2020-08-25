package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.RequestStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RequestStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestStatusRepository extends JpaRepository<RequestStatus, Long> {
}
