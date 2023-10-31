package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface DiaryService {

    // 홈 화면 & 전체 보기 리스트 ===============================================================
    Optional<Diary> diary(Integer user_id) throws ExecutionException, InterruptedException;

    // 일기 저장 =============================================================
    DiaryDTO createDiary(DiaryDTO diaryDTO) throws ExecutionException, InterruptedException;

    // 일기 수정 =============================================================
    DiaryDTO modifyDiary(Integer diary_id, DiaryDTO diaryDTO) throws ExecutionException, InterruptedException;

}
