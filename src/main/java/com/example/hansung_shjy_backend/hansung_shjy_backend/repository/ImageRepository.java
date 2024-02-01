package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>, JpaSpecificationExecutor<Image> {

    @Query(value = "SELECT * FROM Image i WHERE i.diary.diary_diaryid = :diaryid", nativeQuery = true)
    Image findImageByDiary(@Param("diaryid") Integer diaryid);
}
