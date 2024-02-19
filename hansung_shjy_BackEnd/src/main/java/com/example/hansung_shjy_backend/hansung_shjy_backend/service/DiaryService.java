package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DiaryService {

    // 홈 화면 & 전체 보기 리스트 ===============================================================
    List<Diary> listDiary(Integer user_id) throws ExecutionException, InterruptedException;

    // 일기 저장 =============================================================
    Diary createDiary(Couple couple, String diaryDate) throws ExecutionException, InterruptedException;


}
