package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoupleRepository extends JpaRepository<Couple, Integer>, JpaSpecificationExecutor<Couple> {

    // 오늘의 질문 세부화면
    Couple findByCoupleID(Integer coupleid);

}
