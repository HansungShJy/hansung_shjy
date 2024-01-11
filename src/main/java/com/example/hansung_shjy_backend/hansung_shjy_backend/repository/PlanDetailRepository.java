package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.PlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlanDetailRepository extends JpaRepository<PlanDetail, Integer>, JpaSpecificationExecutor<Plan> {

    // Plan All
    @Query(value = "SELECT * FROM plan_detail pd WHERE pd.planID = :planID", nativeQuery = true)
    List<PlanDetail> findByPlanID(@Param("planID") Integer planID);

    // Plan Detail
    @Query(value = "SELECT plan_detailid, plan_check, plan_day_number, plan_end_time, plan_location, plan_number, plan_price, plan_start_time" +
            " FROM plan_detail pd WHERE pd.planID = :planID", nativeQuery = true)
    List<PlanDetail> findPlanDetailsByPlanID(@Param("planID") Integer planID);

    // Plan Modify
    @Query(value = "SELECT * FROM plan_detail pd WHERE pd.planID = :planID", nativeQuery = true)
    PlanDetail findAllByPlanID(@Param("planID") Integer planID);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM plan_detail pd WHERE pd.planID = :planID", nativeQuery = true)
    Integer findPlanDetailByPlanID(@Param("planID") Integer planID);

}
