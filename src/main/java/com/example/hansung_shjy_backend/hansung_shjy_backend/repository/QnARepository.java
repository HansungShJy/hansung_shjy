package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Integer>, JpaSpecificationExecutor<QnA> {

    // QnA all
    @Query(value = "SELECT * FROM QnA q WHERE q.userID = :userID", nativeQuery = true)
        List<QnA> findAllByUserID(@Param("userID") Integer userID);

    // QnA modify
    @Query(value = "SELECT * FROM QnA q WHERE q.userID = :userID AND q.qnaID = :qnaID", nativeQuery = true)
        QnA findAllByUserIDAndQnaID(@Param("userID") Integer userID, @Param("qnaID") Integer qna_id);

    // QnA Detail
    @Query(value = "SELECT * FROM QnA q WHERE q.qnaID = :qnaID", nativeQuery = true)
        QnA findQnAByQnaID(@Param("qnaID") Integer qnaID);


    // QnA otherAnswer
    @Query(value = "SELECT * FROM QnA q WHERE q.userID = :userID", nativeQuery = true)
        QnA findByUserID(@Param("userID") Integer userID);
}
