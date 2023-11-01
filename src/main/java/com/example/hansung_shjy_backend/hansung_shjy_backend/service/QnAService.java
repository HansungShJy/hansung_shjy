package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface QnAService {

    // 오늘의 질문 화면 ===============================================================
    List<QnA> listQnA(Integer user_id) throws ExecutionException, InterruptedException;

    // 오늘의 질문 세부화면 ============================================================
    QnADTO detailQnA(QnADTO qnADTO) throws ExecutionException, InterruptedException;

    // 오늘의 질문 저장 ===============================================================
    QnADTO createQnA(QnADTO qnADTO) throws ExecutionException, InterruptedException;

    // 오늘의 질문 수정 ===============================================================
    QnADTO modifyQnA(QnADTO qnADTO) throws ExecutionException, InterruptedException;
}
