package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.CoupleRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class QnAServiceImpl implements QnAService {

    @Autowired
    private QnARepository qnARepository;

    @Autowired
    private CoupleRepository coupleRepository;


    // 오늘의 질문 첫 화면
    @Override
    public List<QnA> listQnA(Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("listQnA:: " + couple_id);
        if(couple_id == null) return null;

        List<QnA> qnA = qnARepository.findAllByCoupleID(couple_id);
        if (qnA == null) return null;
        else return qnA;
    }

    // 오늘의 질문 디테일 화면
    @Override
    public QnA detailQnA(Integer qnaID) throws ExecutionException, InterruptedException {
        System.out.println("detailQnADTO:: " + qnaID);
        if (qnaID == null) return null;

        QnA qnA = qnARepository.findQnAByQnaID(qnaID);
        System.out.println("detailQnA:: " + qnA);
        if (qnA == null) return null;
        else return qnA;
    }

    // 오늘의 질문 저장
    @Override
    public QnA saveQnA(QnADTO qnADTO, Couple couple) throws ExecutionException, InterruptedException {
        // db -> qna_id, qna_date, my_answer, other_answer, coupleID(Integer)
        QnA qnA = new QnA();
        qnA.setQnaDate(qnADTO.getQnaDate());
        qnA.setCoupleID(couple);

        return qnA;
    }

    // 오늘의 질문 수정
    @Override
    public QnADTO modifyQnA(QnADTO qnADTO) throws ExecutionException, InterruptedException {
        QnA qnA = qnARepository.findAllByCoupleIDAndQnaID(qnADTO.getCoupleID(), qnADTO.getQnaID());
        System.out.println("modifyQnA:: " + qnA);
        if (qnA == null) return null;

        qnA.setQnaDate(qnADTO.getQnaDate());
        qnA.setMyAnswer(qnADTO.getMyAnswer());

        qnARepository.save(QnA.toEntity(qnADTO));
        return qnADTO;
    }
}
