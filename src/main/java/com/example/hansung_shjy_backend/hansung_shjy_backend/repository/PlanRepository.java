package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Integer>, JpaSpecificationExecutor<Plan> {

    // Plan All
    @Query(value = "SELECT * FROM Plan p WHERE p.coupleid = :coupleid", nativeQuery = true)
    List<Plan> findAllByCouple(@Param("coupleid") Integer coupleid);

    // Plan Modify
    @Query(value = "SELECT * FROM Plan p WHERE p.planID = :planID", nativeQuery = true)
    Plan findAllByPlanID(@Param("planID") Integer planID);

    // Plan Delete
    @Query(value = "DELETE * FROM Plan p WHERE p.planID = :planID", nativeQuery = true)
    Plan findByPlanID(@Param("planID") Integer planID);
}
