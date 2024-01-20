package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.CoupleRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.DiaryRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CoupleRepository coupleRepository;


    // 홈 화면 & 전체 보기 리스트 =============================================================
    @Override
    public List<Diary> listDiary(Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("listDiary:: " + couple_id);
        return diaryRepository.findAllByDiaryID(couple_id);
    }

    // 일기 저장 ============================================================
    @Override
    public DiaryDTO createDiary(DiaryDTO diaryDTO) throws ExecutionException, InterruptedException {
        Diary diary = new Diary();
        diary.setDiaryDate(diaryDTO.getDiaryDate());
        diary.setMyDiary(diaryDTO.getMyDiary());
        diary.setOtherDiary(diaryDTO.getOtherDiary());
//        diary.setFileName(diaryDTO.getFileName());
//        diary.setFileOriName(diary.getFileOriName());
//        diary.setFileUrl(diary.getFileUrl());
        diary.setCouple(coupleRepository.findByCoupleID(diaryDTO.getCoupleID()));
        diaryRepository.save(diary);
        return diaryDTO;
    }

    // 일기 수정 ============================================================
    @Override
    public DiaryDTO modifyDiary(DiaryDTO diaryDTO) throws ExecutionException, InterruptedException {
        Diary diary = diaryRepository.findByDiaryID(diaryDTO.getCoupleID(), diaryDTO.getDiaryID());
        System.out.println("modifyDiary:: " + diary);
        if (diary == null) return null;

        diary.setDiaryDate(diaryDTO.getDiaryDate());
        diary.setMyDiary(diary.getMyDiary());
        diary.setOtherDiary(diary.getOtherDiary());

        diaryRepository.save(Diary.toEntity(diaryDTO));
        return diaryDTO;
    }
}
