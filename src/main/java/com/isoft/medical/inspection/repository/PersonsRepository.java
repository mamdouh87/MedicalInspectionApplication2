package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.Persons;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Persons entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonsRepository extends JpaRepository<Persons, Long> {
}
