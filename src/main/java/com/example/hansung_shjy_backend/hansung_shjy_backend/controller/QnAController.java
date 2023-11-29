package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.QnAService;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class QnAController {

    @Autowired
    private QnAService qnAService;

    @Autowired
    private UserService userService;


    // 오늘의 질문 첫 화면 ==========================================================
    @GetMapping("/qna")
    public ResponseEntity<Object> firstQnA(@RequestBody Integer user_id) throws ExecutionException, InterruptedException {
        System.out.println("qna userID:: " + user_id);
        List<QnA> qnAList = qnAService.listQnA(user_id);
        System.out.println("qnAList:: " + qnAList);
        return ResponseEntity.ok().body(qnAList);
    }

    // 오늘의 질문 세부화면 =========================================================
    @GetMapping("/qna/detail/{qna_id}")
    public ResponseEntity<Object> detailQnA(@PathVariable Integer qna_id) throws ExecutionException, InterruptedException {
        System.out.println("qnaDetail qnaID:: " + qna_id);
        QnA qnA = qnAService.detailQnA(qna_id);
        return ResponseEntity.ok().body(qnA);
    }

    // 오늘의 질문 저장 ============================================================
    @PostMapping("/qna/save")
    public ResponseEntity<Object> saveQnA(@RequestBody QnADTO qnADTO) throws ExecutionException, InterruptedException {
        System.out.println("save QnA:: " + qnADTO);
        QnADTO qna = qnAService.saveQnA(qnADTO);
        if (qna == null) return new ResponseEntity<Object>("null exception", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(qna, HttpStatus.CREATED);
    }


    // 오늘의 질문 수정 ============================================================
    @PatchMapping("/qna/edit/{qna_id}")
    public ResponseEntity<Object> editQnA(@PathVariable Integer qna_id, @RequestBody QnADTO qnADTO) throws ExecutionException, InterruptedException {
        System.out.println("qnaEdit qna_id:: " + qna_id);
        System.out.println("qnaEdit qnaDTO:: " + qnADTO);
        qnADTO.setQnaID(qna_id);
        qnADTO.setUserID(userService.findUserByUserid(qnADTO.getUserID()));
        QnADTO qna = qnAService.modifyQnA(qnADTO);
        if (qna == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(qna);
    }
}
