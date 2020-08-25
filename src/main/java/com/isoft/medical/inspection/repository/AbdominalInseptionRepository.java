package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.AbdominalInseption;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AbdominalInseption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbdominalInseptionRepository extends JpaRepository<AbdominalInseption, Long> {
}
