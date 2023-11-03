package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.BankDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.PlanService;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
public class PlanController {

    private PlanService planService;

    private UserService userService;

    // 우리의 여행 계획 첫 화면 ===========================================================================
    @GetMapping("/plan")
    public ResponseEntity<Object> firstPlan(@RequestBody Integer user_id) throws ExecutionException, InterruptedException {
        System.out.println("planFirst userID:: " + user_id);
        List<Plan> planList = planService.listPlan(user_id);
        System.out.println("planList:: " + planList);
        return ResponseEntity.ok().body(planList);
    }

    // 우리의 여행 계획 등록
    @PostMapping("/plan/save")
    public ResponseEntity<Object> createPlan(@RequestBody PlanDTO planDTO) throws ExecutionException, InterruptedException {
        System.out.println("createPlan planDTO:: " + planDTO);
        PlanDTO plan = planService.createPlan(planDTO);
        System.out.println("createPlan plan:: " + plan);
        if (plan == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }

    // 우리의 여행 계획 수정
    @PatchMapping("/plan/edit/{plan_id}")
    public ResponseEntity<Object> modifyPlan(@PathVariable Integer plan_id, @RequestBody PlanDTO planDTO) throws ExecutionException, InterruptedException {
        System.out.println("modifyPlan:: " + plan_id + ", " + planDTO);
        planDTO.setPlanTitle(planDTO.getPlanTitle());
        planDTO.setPlanTraffic(planDTO.getPlanTraffic());
        planDTO.setPlanHome(planDTO.getPlanHome());
        planDTO.setPlanStartDate(planDTO.getPlanStartDate());
        planDTO.setPlanEndDate(planDTO.getPlanEndDate());
        planDTO.setUserID(userService.findUserByUserid(planDTO.getUserID()));
        PlanDTO plan = planService.modifyPlan(planDTO);
        if (plan == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(plan);
    }

    // 우리의 여행 계획 삭제
    @DeleteMapping("/plan/delete/{plan_id}")
    public ResponseEntity<Object> deletePlan(@PathVariable Integer plan_id) throws ExecutionException, InterruptedException {
        System.out.println("deletePlan:: " + plan_id);
        String delete = planService.deletePlan(plan_id);
        if (Objects.equals(delete, "delete")) return ResponseEntity.ok().body("delete");
        else return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
    }
}
