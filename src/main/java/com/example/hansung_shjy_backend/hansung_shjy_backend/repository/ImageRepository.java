package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>, JpaSpecificationExecutor<Image> {

    // 일기 홈 -> image 까지 같이
    @Query(value = "SELECT * FROM Image i WHERE i.diary.diaryID = :diaryID", nativeQuery = true)
    List<Image> findAllByDiary(@Param("diaryID") Integer diaryID);
}
