package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    Optional<Diary> findByUserid(Integer user_id);

    Diary findByDiaryID(Integer diary_id);


}
