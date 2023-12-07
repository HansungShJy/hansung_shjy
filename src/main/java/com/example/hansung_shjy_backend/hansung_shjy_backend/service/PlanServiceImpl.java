package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanDetailDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.PlanRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Plan;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.PlanDetail;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
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


    // 우리의 여행 계획 첫 화면
    @Override
    public List<Plan> listPlan(Integer userid) throws ExecutionException, InterruptedException {
        System.out.println("listPlan userid:: " + userid);
        if (userid == null) return null;

        List<Plan> plan = planRepository.findByUserID(userid);
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
            PlanDetail planDetail = new PlanDetail();

            plan.setPlanTitle(planDTO.getPlanTitle());
            plan.setPlanTraffic(planDTO.getPlanTraffic());
            plan.setPlanHome(planDTO.getPlanHome());
            plan.setPlanStartDate(planDTO.getPlanStartDate());
            plan.setPlanEndDate(planDTO.getPlanEndDate());

            User user = userRepository.findUserByUserID(planDTO.getUserID());
            plan.setUserID(user);

            planRepository.save(plan);

            for (PlanDetailDTO planDetailDTO : planDetailDTOList) {
                planDetail.setPlanID(plan);
                planDetail.setPlanCheck(planDetailDTO.getPlanCheck());
                planDetail.setPlanNumber(planDetailDTO.getPlanNumber());
                planDetail.setPlanLocation(planDetailDTO.getPlanLocation());
                planDetail.setPlanDayNumber(planDetailDTO.getPlanDayNumber());
                planDetail.setPlanStartTime(planDetailDTO.getPlanStartTime());
                planDetail.setPlanEndTime(planDetailDTO.getPlanEndTime());
                planDetailRepository.save(planDetail);
            }


            return "Plan save success";
        }
    }
    //@Transactional
    //    public String createPlan(PlanDTO planDTO, PlanDetailDTO planDetailDTO) throws ExecutionException, InterruptedException {
    //        // Create and save Plan entity
    //        Plan plan = Plan.toEntity(planDTO);
    //        Plan savedPlan = planRepository.save(plan);
    //
    //        // Create and save PlanDetail entity with the reference to the saved Plan
    //        PlanDetail planDetail = PlanDetail.toEntity(planDetailDTO);
    //        planDetail.setPlanID(savedPlan);
    //        planDetailRepository.save(planDetail);
    //
    //        return "Plan and PlanDetail saved successfully";
    //    }

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
