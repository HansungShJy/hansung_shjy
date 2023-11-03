package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.QnARepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class QnAServiceImpl implements QnAService {

    private QnARepository qnARepository;

    private UserRepository userRepository;


    // 오늘의 질문 첫 화면
    @Override
    public List<QnA> listQnA(Integer user_id) throws ExecutionException, InterruptedException {
        System.out.println("listQnA:: " + user_id);
        if(user_id == null) return null;

        List<QnA> qnA = qnARepository.findAllByUserID(user_id);
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
    public QnADTO saveQnA(QnADTO qnADTO) throws ExecutionException, InterruptedException {
        QnA qnA = new QnA();
        qnA.setQnaTitle(qnADTO.getQnaTitle());
        qnA.setQnaDate(qnADTO.getQnaDate());
        qnA.setMyAnswer(qnADTO.getMyAnswer());
        qnA.setOtherAnswer(qnADTO.getOtherAnswer());
        qnA.setUserID(userRepository.findUserByUserID(qnADTO.getUserID()));
        qnARepository.save(QnA.toEntity(qnADTO));
        return qnADTO;
    }

    // 오늘의 질문 수정
    @Override
    public QnADTO modifyQnA(QnADTO qnADTO) throws ExecutionException, InterruptedException {
        QnA qnA = qnARepository.findAllByUserIDAndQnaID(qnADTO.getUserID(), qnADTO.getQnaID());
        System.out.println("modifyQnA:: " + qnA);
        if (qnA == null) return null;

        qnA.setQnaTitle(qnADTO.getQnaTitle());
        qnA.setQnaDate(qnADTO.getQnaDate());
        qnA.setMyAnswer(qnA.getMyAnswer());
        qnA.setOtherAnswer(qnA.getOtherAnswer());
        qnA.setUserID(qnA.getUserID());
        qnARepository.save(QnA.toEntity(qnADTO));
        return qnADTO;
    }
}
