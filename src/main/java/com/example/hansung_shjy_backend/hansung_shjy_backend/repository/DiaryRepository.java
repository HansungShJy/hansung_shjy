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

    // 일기 홈 & 전체 보기
    @Query(value = "SELECT * FROM Diary d WHERE d.coupleID = :coupleID", nativeQuery = true)
    List<Diary> findAllByDiaryID(@Param("coupleID") Integer coupleID);

    // 일기 수정할 때 & 일기 디테일 화면
    @Query(value = "SELECT * FROM Diary d WHERE d.coupleID = :coupleID AND d.diaryID = :diary_id", nativeQuery = true)
        Diary findByDiaryID(@Param("coupleID") Integer coupleID, @Param("diary_id") Integer diary_id);



}
