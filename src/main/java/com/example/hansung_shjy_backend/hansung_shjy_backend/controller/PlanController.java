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
    public ResponseEntity<Object> modifyPlan(@PathVariable Integer plan_id, @RequestBody PlanModifyRequest planRequest) throws ExecutionException, InterruptedException {
        System.out.println("modifyPlan:: " + plan_id + ", plan:: " + planRequest.getPlanDTO() +
                ", planDetail:: " + planRequest.getPlanDetailDTOs());
        Plan plan = planService.modifyPlan(plan_id);
        List<PlanDetail> planDetails = planService.modifyPlanDetail(plan_id); // unique X -> result : 2

        Map<String, Object> resultMap = new HashMap<>();

        PlanDTO planDTO = planRequest.getPlanDTO();
        List<PlanDetailDTO> planDetailDTOs = planRequest.getPlanDetailDTOs();

        PlanDetail planDetail = findPlanDetail(planDetails, planDetailDTOs);  // 얘를 하나로 잡아야됨

        if (planDetail == null) return new ResponseEntity<>("PlanDetail not found", HttpStatus.BAD_REQUEST);

        plan.setPlanTitle(planDTO.getPlanTitle());
        plan.setPlanTraffic(planDTO.getPlanTraffic());
        plan.setPlanHome(planDTO.getPlanHome());
//        plan.setPlanStartDate(planDTO.getPlanStartDate());
//        plan.setPlanEndDate(planDTO.getPlanEndDate());

        planDetail.setPlanDayNumber(planDetailDTOs.get(planDetail.getPlanDetailID()).getPlanDayNumber());
        planDetail.setPlanNumber(planDetailDTOs.get(planDetail.getPlanDetailID()).getPlanNumber());
        planDetail.setPlanLocation(planDetailDTOs.get(planDetail.getPlanDetailID()).getPlanLocation());
        planDetail.setPlanPrice(planDetailDTOs.get(planDetail.getPlanDetailID()).getPlanPrice());
//        planDetail.setPlanStartTime(planDetailDTOs.getPlanStartTime());
//        planDetail.setPlanEndTime(planDetailDTOs.getPlanEndTime());
        planDetail.setPlanCheck(planDetailDTOs.get(plan_id).getPlanCheck());

        planRepository.save(plan);
        planDetailRepository.save(planDetail);

        resultMap.put("plan", plan);
        resultMap.put("planDetail", planDetail);

        return ResponseEntity.ok().body(resultMap);
    }

    private PlanDetail findPlanDetail(List<PlanDetail> planDetails, List<PlanDetailDTO> planDetailDTOs) {
        // Iterate through planDetails and planDetailDTOs to find the matching PlanDetail
        // Adjust the logic based on your specific criteria for matching
        // For example, matching based on planDetailID
        for (PlanDetail planDetail : planDetails) {
            for (PlanDetailDTO planDetailDTO : planDetailDTOs) {
                if (planDetail.getPlanDetailID().equals(planDetailDTO.getPlanDetailID())) {
                    return planDetail;
                }
            }
        }
        return null; // If no matching PlanDetail is found
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
