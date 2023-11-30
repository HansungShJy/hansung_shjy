package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.PlanRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private PlanRepository planRepository;

    private UserRepository userRepository;


    // 우리의 여행 계획 첫 화면
    @Override
    public List<Plan> listPlan(Integer userid) throws ExecutionException, InterruptedException {
        System.out.println("listPlan userid:: " + userid);
        if (userid == null) return null;

        List<Plan> plan = planRepository.findByUserID(userid);
        if (plan == null) return null;
        else return plan;
    }

    // 우리의 여행 계획 등록
    @Override
    public PlanDTO createPlan(PlanDTO planDTO) throws ExecutionException, InterruptedException {
        Plan plan = new Plan();
        plan.setPlanTitle(planDTO.getPlanTitle());
        plan.setPlanTraffic(planDTO.getPlanTraffic());
        plan.setPlanHome(planDTO.getPlanHome());
        plan.setPlanStartDate(planDTO.getPlanStartDate());
        plan.setPlanEndDate(planDTO.getPlanEndDate());
        plan.setUserID(userRepository.findUserByUserID(planDTO.getUserID()));
        planRepository.save(Plan.toEntity(planDTO));
        return planDTO;
    }

    // 우리의 여행 계획 수정
    @Override
    public PlanDTO modifyPlan(PlanDTO planDTO) throws ExecutionException, InterruptedException {
        Plan plan = planRepository.findAllByUserIDAndPlanID(planDTO.getUserID(), planDTO.getPlanID());
        System.out.println("modifyPlan:: " + plan);
        if (plan == null) return null;

        plan.setPlanTitle(planDTO.getPlanTitle());
        plan.setPlanTraffic(planDTO.getPlanTraffic());
        plan.setPlanHome(planDTO.getPlanHome());
        plan.setPlanStartDate(planDTO.getPlanStartDate());
        plan.setPlanEndDate(planDTO.getPlanEndDate());
        planRepository.save(Plan.toEntity(planDTO));
        return planDTO;
    }

    // 우리의 여행 계획 삭제
    @Override
    public String deletePlan(Integer plan_id) throws ExecutionException, InterruptedException {
        Plan plan = planRepository.findByPlanID(plan_id);
        if (plan == null) return null;
        else return "delete";
    }
}
