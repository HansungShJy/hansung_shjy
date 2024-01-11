package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDetailDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.PlanDetail;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface PlanService {

    // 우리의 여행 계획 첫 화면 =========================================================
    List<Plan> listPlan(Integer couple_id) throws ExecutionException, InterruptedException;

    // 우리의 여행 계획 디테일 화면 =========================================================
    Map<String, Object> detailPlan(Integer plan_id) throws ExecutionException, InterruptedException;

    // 우리의 여행 계획 등록 ============================================================
    String createPlan(PlanRequest planRequest) throws ExecutionException, InterruptedException;

    // 우리의 여행 계획 수정 ============================================================
    Plan modifyPlan(Integer plan_id) throws ExecutionException, InterruptedException;
    PlanDetail modifyPlanDetail(Integer plan_id) throws ExecutionException, InterruptedException;

    // 우리의 여행 계획 삭제 ============================================================
    String deletePlan(Integer plan_id) throws ExecutionException, InterruptedException;

}
