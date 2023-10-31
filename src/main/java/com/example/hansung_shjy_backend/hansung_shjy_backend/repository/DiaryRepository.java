package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer>, JpaSpecificationExecutor<Diary> {

    Diary findByDiaryID(Integer diary_id);

    @Query(value = "SELECT Diary.diaryID FROM Diary d WHERE d.userID = :user_id", nativeQuery = true)
        List<Diary> findAllByDiaryID(@Param("user_id") Integer user_id);

}
