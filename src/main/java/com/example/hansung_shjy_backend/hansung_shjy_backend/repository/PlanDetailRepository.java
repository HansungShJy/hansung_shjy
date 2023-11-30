package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.PlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanDetailRepository extends JpaRepository<PlanDetail, Integer>, JpaSpecificationExecutor<Plan> {

    // Plan All
    @Query(value = "SELECT * FROM PlanDetail pd WHERE pd.planID = :planID", nativeQuery = true)
    List<PlanDetail> findByPlanID(@Param("planID") Integer planID);

//    // Plan Modify
//    @Query(value = "SELECT * FROM Plan p WHERE p.userID = :user_id AND p.planID = :plan_id", nativeQuery = true)
//    Plan findAllByUserIDAndPlanID(@Param("user_id") Integer user_id, @Param("plan_id") Integer plan_id);
//
//    // Plan Delete
//    @Query(value = "DELETE * FROM Plan p WHERE p.planID = :plan_id", nativeQuery = true)
//    Plan findByPlanID(@Param("plan_id") Integer plan_id);
}
