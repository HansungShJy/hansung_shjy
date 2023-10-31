package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.DiaryRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class DiaryServiceImpl implements DiaryService {

    DiaryRepository diaryRepository;

    UserRepository userRepository;



    // 홈 화면 & 전체 보기 리스트 =============================================================
    @Override
    public Optional<Diary> diary(Integer user_id) throws ExecutionException, InterruptedException {
        return diaryRepository.findByUserid(user_id);
    }


    // 일기 저장 ============================================================
    @Override
    public DiaryDTO createDiary(DiaryDTO diaryDTO) throws ExecutionException, InterruptedException {
        Diary diary = new Diary();
        diary.setDiaryDate(diaryDTO.getDiaryDate());
        diary.setMyDiary(diary.getMyDiary());
        diary.setOtherDiary(diary.getOtherDiary());
        diary.setUserid(userRepository.findUserByUserID(diaryDTO.getUserID()));
        diaryRepository.save(Diary.toEntity(diaryDTO));
        return diaryDTO;
    }

    // 일기 수정 ============================================================
    @Override
    public DiaryDTO modifyDiary(Integer diary_id, DiaryDTO diaryDTO) throws ExecutionException, InterruptedException {
        Diary diary = diaryRepository.findByDiaryID(diary_id);
        if (diary == null) return null;

        diary.setDiaryDate(diaryDTO.getDiaryDate());
        diary.setMyDiary(diary.getMyDiary());
        diary.setOtherDiary(diary.getOtherDiary());
        diary.setUserid(userRepository.findUserByUserID(diaryDTO.getUserID()));
        diaryRepository.save(Diary.toEntity(diaryDTO));
        return diaryDTO;
    }




}
