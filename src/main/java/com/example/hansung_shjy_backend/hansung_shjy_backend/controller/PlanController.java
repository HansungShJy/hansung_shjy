package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.*;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.PlanDetail;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.PlanDetailRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.PlanRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.PlanService;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanDetailRepository planDetailRepository;

    // 우리의 여행 계획 첫 화면 ===========================================================================
    @GetMapping("/plan")
    public ResponseEntity<Object> firstPlan(@RequestParam("coupleID") Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("planFirst couple_id:: " + couple_id);
        List<Plan> planList = planService.listPlan(couple_id);
        System.out.println("planList:: " + planList);

        List<PlanDTO> planDTOList = planList.stream()
                .map(plan -> new PlanDTO(plan.getPlanID(), plan.getPlanStartDate(), plan.getPlanEndDate(),
                        plan.getPlanTitle(), plan.getPlanTraffic(), plan.getPlanHome(), plan.getCouple().getCoupleID()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(planDTOList);
    }

    // 우리의 여행 계획 세부화면 ===========================================================================
    @GetMapping("/plan/detail/{plan_id}")
    public ResponseEntity<Object> detailPlan(@PathVariable Integer plan_id) throws ExecutionException, InterruptedException {
        System.out.println("planDetail plan_id:: " + plan_id);
        Map<String, Object> planList = planService.detailPlan(plan_id);
        System.out.println("planList:: " + planList);

        return ResponseEntity.ok().body(planList);
    }

    // 우리의 여행 계획 등록
    @PostMapping("/plan/save")
    public ResponseEntity<Object> createPlan(@RequestBody PlanRequest planRequest) throws ExecutionException, InterruptedException {
        System.out.println("createPlan planRequest:: " + planRequest.getPlanDTO() + " " + planRequest.getPlanDetailDTO());
        String plan = planService.createPlan(planRequest);
        System.out.println("createPlan plan:: " + plan);
        if (plan == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }

    // 우리의 여행 계획 수정
    @PatchMapping("/plan/edit/{plan_id}")
    public ResponseEntity<Object> modifyPlan(@PathVariable Integer plan_id, @RequestBody PlanModifyRequest planRequest)
            throws ExecutionException, InterruptedException {

        System.out.println("modifyPlan:: " + plan_id + ", plan:: " + planRequest.getPlanDTO() +
                ", planDetail:: " + planRequest.getPlanDetailDTOs());

        Plan plan = planService.modifyPlan(plan_id);
        List<PlanDetail> planDetails = planService.modifyPlanDetail(plan_id);

        Map<String, Object> resultMap = new HashMap<>();

        PlanDTO planDTO = planRequest.getPlanDTO();
        List<PlanDetailDTO> planDetailDTOs = planRequest.getPlanDetailDTOs();

        if (plan == null) {
            return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        }

        for (PlanDetailDTO planDetailDTO : planDetailDTOs) {
            // Find the corresponding PlanDetail entity by planDetailID
            PlanDetail planDetail = findPlanDetailByPlanDetailID(planDetails, planDetailDTO.getPlanDetailID());

            if (planDetail == null) {
                return new ResponseEntity<>("PlanDetail not found for planDetailID: " + planDetailDTO.getPlanDetailID(),
                        HttpStatus.BAD_REQUEST);
            }

            planDetail.setPlanDayNumber(planDetailDTO.getPlanDayNumber());
            planDetail.setPlanNumber(planDetailDTO.getPlanNumber());
            planDetail.setPlanLocation(planDetailDTO.getPlanLocation());
            planDetail.setPlanPrice(planDetailDTO.getPlanPrice());
            // planDetail.setPlanStartTime(planDetailDTO.getPlanStartTime());
            // planDetail.setPlanEndTime(planDetailDTO.getPlanEndTime());
            planDetail.setPlanCheck(planDetailDTO.getPlanCheck());

            // Save the modified PlanDetail
            planDetailRepository.save(planDetail);
        }

        // Update Plan properties (assuming they are common for all PlanDetailDTOs)
        plan.setPlanTitle(planDTO.getPlanTitle());
        plan.setPlanTraffic(planDTO.getPlanTraffic());
        plan.setPlanHome(planDTO.getPlanHome());

        // Save the modified Plan
        planRepository.save(plan);

        resultMap.put("plan", plan);
        resultMap.put("planDetails", planDetails);

        return ResponseEntity.ok().body(resultMap);
    }

    private PlanDetail findPlanDetailByPlanDetailID(List<PlanDetail> planDetails, Integer planDetailID) {
        // Find and return the matching PlanDetail by planDetailID
        return planDetails.stream()
                .filter(planDetail -> planDetail.getPlanDetailID().equals(planDetailID))
                .findFirst()
                .orElse(null);
    }


    // 우리의 여행 계획 삭제
    @Transactional
    @DeleteMapping("/plan/delete/{plan_id}")
    public ResponseEntity<Object> deletePlan(@PathVariable Integer plan_id) throws ExecutionException, InterruptedException {
        System.out.println("deletePlan:: " + plan_id);

        planService.deletePlan(plan_id);

        if (plan_id == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body("plan delete");
    }
}
