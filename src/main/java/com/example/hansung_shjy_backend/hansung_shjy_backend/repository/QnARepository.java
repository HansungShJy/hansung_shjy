package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Integer>, JpaSpecificationExecutor<QnA> {

    // QnA all
    @Query(value = "SELECT * FROM QnA q WHERE q.userID = :user_id", nativeQuery = true)
        List<QnA> findAllByUserID(@Param("user_id") Integer user_id);

    // QnA modify
    @Query(value = "SELECT * FROM QnA q WHERE q.userID = :user_id AND q.qnaID = :qna_id", nativeQuery = true)
        QnA findAllByUserIDAndQnaID(@Param("user_id") Integer user_id, @Param("qna_id") Integer qna_id);

    // QnA Detail
    @Query(value = "SELECT * FROM QnA q WHERE q.qnaID = :qna_id", nativeQuery = true)
        QnA findQnAByQnaID(@Param("qna_id") Integer qna_id);

}
