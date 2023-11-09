package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.DiaryService;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.UserService;
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
    private UserService userService;


    // 홈 화면 ============================================================================
    @GetMapping("/diary")
    public ResponseEntity<Object> diaryFirst(@RequestBody Integer userid) throws ExecutionException, InterruptedException {
        System.out.println("diary userID:: " + userid);
        List<Diary> diaryDTO = diaryService.listDiary(userid);
        System.out.println("diaryDTO:: " + diaryDTO);
        return ResponseEntity.ok().body(diaryDTO);
    }

    // 일기 저장 ===========================================================================
    @PostMapping("/diary/save/{user_id}")
    public ResponseEntity<Object> createDiary(@PathVariable Integer user_id, @RequestBody DiaryDTO diaryDTO) throws ExecutionException, InterruptedException {
        System.out.println("create Diary User:: " + user_id);
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
        diaryDTO.setUserID(userService.findUserByUserid(diaryDTO.getUserID()));
        DiaryDTO diary = diaryService.modifyDiary(diaryDTO);
        if (diary == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(diary);
    }

    // 일기 전체 보기 리스트 =================================================================
    @GetMapping("/diary/list/{user_id}")
    public ResponseEntity<Object> listAllDiary(@PathVariable Integer user_id) throws ExecutionException, InterruptedException {
        System.out.println("<diary> user_id::" + user_id);
        List<Diary> diaryDTO = diaryService.listDiary(user_id);
        System.out.println("diary listAll:: " + diaryDTO);
        if (diaryDTO.isEmpty()) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(diaryDTO);
    }
}
