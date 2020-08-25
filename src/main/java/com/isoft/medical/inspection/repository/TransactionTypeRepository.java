package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.TransactionType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TransactionType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
