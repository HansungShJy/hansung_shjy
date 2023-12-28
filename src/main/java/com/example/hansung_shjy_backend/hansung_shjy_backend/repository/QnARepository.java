package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Integer>, JpaSpecificationExecutor<QnA> {

    // QnA all
    @Query(value = "SELECT * FROM QnA q WHERE q.coupleID = :coupleID", nativeQuery = true)
        List<QnA> findAllByCoupleID(@Param("coupleID") Integer coupleID);

    // QnA modify
    @Query(value = "SELECT * FROM QnA q WHERE q.coupleID = :coupleID AND q.qnaID = :qnaID", nativeQuery = true)
        QnA findAllByCoupleIDAndQnaID(@Param("coupleID") Integer coupleID, @Param("qnaID") Integer qna_id);

    // QnA Detail
    @Query(value = "SELECT * FROM QnA q WHERE q.qnaID = :qnaID", nativeQuery = true)
        QnA findQnAByQnaID(@Param("qnaID") Integer qnaID);


    QnA findQnAByQnaNumber(Integer qnaNumber);

    QnA findByCoupleIDAndQnaNumber(Integer coupleID, Integer qnaNumber);

}
