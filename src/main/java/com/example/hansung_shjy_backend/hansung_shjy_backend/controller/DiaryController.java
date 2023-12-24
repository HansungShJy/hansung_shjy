package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.CoupleRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private CoupleRepository coupleRepository;


    // 홈 화면 ============================================================================
    @GetMapping("/diary")
    public ResponseEntity<Object> diaryFirst(@RequestParam(value = "couple_id", required = false) Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("diary couple_id:: " + couple_id);
        List<Diary> diaryDTO = diaryService.listDiary(couple_id);
        System.out.println("diaryDTO:: " + diaryDTO);
        return ResponseEntity.ok().body(diaryDTO);
    }

    // 일기 저장 ===========================================================================
    @PostMapping("/diary/save/{couple_id}")
    public ResponseEntity<Object> createDiary(@PathVariable Integer couple_id, @RequestBody DiaryDTO diaryDTO) throws ExecutionException, InterruptedException {
        System.out.println("create Diary couple_id:: " + couple_id);
        System.out.println("create Diary:: " + diaryDTO);
        DiaryDTO diary = diaryService.createDiary(diaryDTO);

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
        List<Diary> diaryDTO = diaryService.listDiary(couple_id);
        System.out.println("diary listAll:: " + diaryDTO);
        if (diaryDTO.isEmpty()) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(diaryDTO);
    }
}
