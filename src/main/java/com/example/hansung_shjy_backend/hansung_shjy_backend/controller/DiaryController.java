package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.CoupleRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private CoupleRepository coupleRepository;


    // 홈 화면 ============================================================================
    @GetMapping("/diary")
    public ResponseEntity<Object> diaryFirst(@RequestParam("couple_id") Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("diary couple_id:: " + couple_id);

        User me = coupleRepository.findByCoupleID(couple_id).getMe();
        String myNickname = me.getNickname();
        String otherNickname = me.getOtherID();
        Integer coupleID = me.getCouple().getCoupleID();
        Couple couple = coupleRepository.findByCoupleID(coupleID);
        Integer myUserID = couple.getMe().getUserID();
        Integer otherUserID = couple.getOther().getUserID();

        Map<Diary, Image> diaryImageMap = diaryService.listDiary(couple_id);

        if (diaryImageMap.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<DiaryRequest> diaryDTOList = diaryImageMap.entrySet().stream()
                .map(entry -> {
                    Diary diary = entry.getKey();
                    Image image = entry.getValue();

                    DiaryRequest diaryRequest = new DiaryRequest();
                    diaryRequest.setDiary(diary);
                    diaryRequest.setImage(image);
                    return diaryRequest;    // diary & image 묶어서 같이 보내주기
                })
                .toList();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("nickname1", myNickname);
        resultMap.put("nickname2", otherNickname);
        resultMap.put("userID1", myUserID);
        resultMap.put("userID2", otherUserID);
        resultMap.put("diaryDetail", diaryImageMap);

        System.out.println("diaryDTO:: " + diaryDTOList);
        return ResponseEntity.ok().body(resultMap);
    }

    // 일기 저장 ===========================================================================
    @PostMapping("/diary/save/{couple_id}")
    public ResponseEntity<Object> createDiary(@PathVariable Integer couple_id, @RequestBody DiaryDTO diaryDTO) throws ExecutionException, InterruptedException {
        System.out.println("create Diary couple_id:: " + couple_id);
        System.out.println("create Diary:: " + diaryDTO);

        DiaryDTO diary = diaryService.createDiary(diaryDTO);

        // TODO - return을 image와 같이 보내야함

        if (diaryDTO == null || diary == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(diary);
    }

    // 일기 수정 ===========================================================================
    @PatchMapping("/diary/edit/{diary_id}")
    public ResponseEntity<Object> editDiary(@PathVariable Integer diary_id, @RequestBody DiaryDTO diaryDTO) throws ExecutionException, InterruptedException {
        System.out.println("diary_id:: " + diary_id);
        System.out.println("diayDTO:: " + diaryDTO);
        diaryDTO.setDiaryID(diary_id);
        diaryDTO.setCoupleID(coupleRepository.findByCoupleID(diaryDTO.getCoupleID()).getCoupleID());
        DiaryDTO diary = diaryService.modifyDiary(diaryDTO);
        if (diary == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(diary);
    }

    // 일기 전체 보기 리스트 =================================================================
    @GetMapping("/diary/list/{couple_id}")
    public ResponseEntity<Object> listAllDiary(@PathVariable Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("<diary> couple_id::" + couple_id);

        Map<Diary, Image> diaryImageMap = diaryService.listDiary(couple_id);

        if (diaryImageMap.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<DiaryRequest> diaryDTOList = diaryImageMap.entrySet().stream()
                .map(entry -> {
                    Diary diary = entry.getKey();
                    Image image = entry.getValue();

                    DiaryRequest diaryRequest = new DiaryRequest();
                    diaryRequest.setDiary(diary);
                    diaryRequest.setImage(image);
                    return diaryRequest;    // diary & image 묶어서 같이 보내주기
                })
                .toList();

        System.out.println("diary listAll:: " + diaryDTOList);

        return ResponseEntity.ok().body(diaryDTOList);
    }
}
