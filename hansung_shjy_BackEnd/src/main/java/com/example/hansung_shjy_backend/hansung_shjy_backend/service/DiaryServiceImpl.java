package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.CoupleRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.DiaryRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

    @PersistenceContext
    private EntityManager entityManager;


    // 홈 화면 & 전체 보기 리스트 =============================================================
    @Override
    public List<Diary> listDiary(Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("listDiary:: " + couple_id);

        List<Diary> results = diaryRepository.findDiaryByCouple(couple_id);
        System.out.println("resulttltt:: " + results);

        return results;
    }

    // 일기 저장 ============================================================
    @Override
    public Diary createDiary(Couple couple, String diaryDate) throws ExecutionException, InterruptedException {

        Diary diary = new Diary();
        diary.setDiaryDate((java.sql.Date.valueOf(diaryDate)));
        diary.setCouple(couple);

        return diary;
    }

}
