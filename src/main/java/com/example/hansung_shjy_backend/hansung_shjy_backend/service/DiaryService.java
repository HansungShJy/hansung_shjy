package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface DiaryService {

    // 홈 화면 & 전체 보기 리스트 ===============================================================
    Map<Diary, Image> listDiary(Integer user_id) throws ExecutionException, InterruptedException;

    // 일기 저장 =============================================================
    Diary createDiary(Couple couple, Date diaryDate) throws ExecutionException, InterruptedException;

    // 일기 수정 =============================================================
    DiaryDTO modifyDiary(DiaryDTO diaryDTO) throws ExecutionException, InterruptedException;

}
