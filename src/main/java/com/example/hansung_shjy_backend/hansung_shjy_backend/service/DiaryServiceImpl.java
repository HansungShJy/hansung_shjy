package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.CoupleRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.DiaryRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.ImageRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    ImageRepository imageRepository;

    @PersistenceContext
    private EntityManager entityManager;


    // 홈 화면 & 전체 보기 리스트 =============================================================
    @Override
    public Map<Diary, Image> listDiary(Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("listDiary:: " + couple_id);
        // Fetch diaries and their associated images using JPA join
        List<Object[]> results = entityManager.createQuery(
                        "SELECT d, i FROM Diary d LEFT JOIN Image i ON d.diaryID = i.diary.diaryID WHERE d.couple.coupleID = :couple_id", Object[].class)
                .setParameter("couple_id", couple_id)
                .getResultList();

        Map<Diary, Image> diaryImageMap = new HashMap<>();
        for (Object[] result : results) {
            Diary diary = (Diary) result[0];
            Image image = (Image) result[1];
            diaryImageMap.put(diary, image);
        }

        return diaryImageMap;
    }

    // 일기 저장 ============================================================
    @Override
    public Diary createDiary(Couple couple, Date diaryDate) throws ExecutionException, InterruptedException {

        Diary diary = new Diary();
        diary.setDiaryDate(diaryDate);
        diary.setCouple(couple);

        return diary;
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
