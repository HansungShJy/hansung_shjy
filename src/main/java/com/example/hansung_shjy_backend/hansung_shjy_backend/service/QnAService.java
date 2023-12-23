package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface QnAService {

    // 오늘의 질문 화면 ===============================================================
    List<QnA> listQnA(Integer user_id) throws ExecutionException, InterruptedException;

    // 오늘의 질문 세부화면 ============================================================
    QnA detailQnA(Integer qna_id) throws ExecutionException, InterruptedException;

    // 오늘의 질문 저장 ===============================================================
    QnA saveQnA(QnADTO qnADTO, Couple couple) throws ExecutionException, InterruptedException;

    // 오늘의 질문 수정 ===============================================================
    QnADTO modifyQnA(QnADTO qnADTO) throws ExecutionException, InterruptedException;
}
