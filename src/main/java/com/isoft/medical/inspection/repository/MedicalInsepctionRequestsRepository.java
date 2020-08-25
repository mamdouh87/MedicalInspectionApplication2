package com.isoft.medical.inspection.repository;

import com.isoft.medical.inspection.domain.MedicalInsepctionRequests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MedicalInsepctionRequests entity.
 */
@Repository
public interface MedicalInsepctionRequestsRepository extends JpaRepository<MedicalInsepctionRequests, Long> {

    @Query(value = "select distinct medicalInsepctionRequests from MedicalInsepctionRequests medicalInsepctionRequests left join fetch medicalInsepctionRequests.requirements",
        countQuery = "select count(distinct medicalInsepctionRequests) from MedicalInsepctionRequests medicalInsepctionRequests")
    Page<MedicalInsepctionRequests> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct medicalInsepctionRequests from MedicalInsepctionRequests medicalInsepctionRequests left join fetch medicalInsepctionRequests.requirements")
    List<MedicalInsepctionRequests> findAllWithEagerRelationships();

    @Query("select medicalInsepctionRequests from MedicalInsepctionRequests medicalInsepctionRequests left join fetch medicalInsepctionRequests.requirements where medicalInsepctionRequests.id =:id")
    Optional<MedicalInsepctionRequests> findOneWithEagerRelationships(@Param("id") Long id);
}
