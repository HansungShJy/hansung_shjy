package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDetailDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.PlanDetail;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.CoupleRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.PlanDetailRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.PlanRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanDetailRepository planDetailRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoupleRepository coupleRepository;


    // 우리의 여행 계획 첫 화면
    @Override
    public List<Plan> listPlan(Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("listPlan couple_id:: " + couple_id);
        if (couple_id == null) return null;

        List<Plan> plan = planRepository.findAllByCouple(couple_id);
        System.out.println("plan first list :: "+ plan);
        if (plan == null) return null;
        else return plan;
    }

    // 우리의 여행 계획 등록 --> plan, planDetail
    @Override
    public String createPlan(PlanRequest planRequest) throws ExecutionException, InterruptedException {
        System.out.println("planServiceImpl:: " + planRequest.getPlanDTO() + ", " + planRequest.getPlanDetailDTO());

        if (planRequest.getPlanDTO() == null || planRequest.getPlanDetailDTO() == null) return null;
        else  {
            PlanDTO planDTO = planRequest.getPlanDTO();
            List<PlanDetailDTO> planDetailDTOList = planRequest.getPlanDetailDTO();

            Plan plan = new Plan();

            plan.setPlanTitle(planDTO.getPlanTitle());
            plan.setPlanTraffic(planDTO.getPlanTraffic());
            plan.setPlanHome(planDTO.getPlanHome());
            plan.setPlanStartDate(planDTO.getPlanStartDate());
            plan.setPlanEndDate(planDTO.getPlanEndDate());

            Couple couple = coupleRepository.findByCoupleID(planDTO.getCoupleID());
            plan.setCouple(couple);

            planRepository.save(plan);

            for (PlanDetailDTO planDetailDTO : planDetailDTOList) {
                PlanDetail planDetail = new PlanDetail();

                planDetail.setPlanID(plan);
                planDetail.setPlanCheck(planDetailDTO.getPlanCheck());
                planDetail.setPlanNumber(planDetailDTO.getPlanNumber());
                planDetail.setPlanPrice(planDetailDTO.getPlanPrice());
                planDetail.setPlanLocation(planDetailDTO.getPlanLocation());
                planDetail.setPlanDayNumber(planDetailDTO.getPlanDayNumber());
                planDetail.setPlanStartTime(planDetailDTO.getPlanStartTime());
                planDetail.setPlanEndTime(planDetailDTO.getPlanEndTime());
                planDetailRepository.save(planDetail);
            }


            return "Plan save success";
        }
    }

    // 우리의 여행 계획 수정
    @Override
    public Plan modifyPlan(Integer plan_id) throws ExecutionException, InterruptedException {
        Plan plan = planRepository.findAllByPlanID(plan_id);
        System.out.println("modifyPlan:: " + plan);
        if (plan == null) return null;

        return plan;
    }

    @Override
    public PlanDetail modifyPlanDetail(Integer plan_id) throws ExecutionException, InterruptedException {
        PlanDetail planDetail = planDetailRepository.findAllByPlanID(plan_id);
        System.out.println("modifyPlanDetail:: " + planDetail);
        if (planDetail == null) return null;

        return planDetail;
    }

    // 우리의 여행 계획 삭제
    @Override
    public String deletePlan(Integer plan_id) throws ExecutionException, InterruptedException {
        PlanDetail planDetail = planDetailRepository.findPlanDetailByPlanID(plan_id);
        Plan plan = planRepository.findByPlanID(plan_id);

        if (plan == null || planDetail == null) return null;
        else return "plan & planDetail delete";
    }
}
