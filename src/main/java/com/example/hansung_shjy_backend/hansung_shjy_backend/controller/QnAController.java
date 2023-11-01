package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.QnAService;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class QnAController {

    private QnAController qnAController;

    private QnAService qnAService;

    private UserService userService;


    // 오늘의 질문 첫 화면 ==========================================================
    @GetMapping("/qna")
    public ResponseEntity<Object> qnaFirst(@RequestBody Integer user_id) throws ExecutionException, InterruptedException {
        System.out.println("qna userID:: " + user_id);
        List<QnA> qnaDTO = qnAService.listQnA(user_id);
        System.out.println("qnaDTO:: " + qnaDTO);
        return ResponseEntity.ok().body(qnaDTO);
    }

    // 오늘의 질문 세부화면 =========================================================
    // 여기도 고쳐야됨 ****************
    @GetMapping("/qna/detail/{qnaID}")
    public ResponseEntity<Object> qnaDetail(@PathVariable Integer qna_id) throws ExecutionException, InterruptedException {
        System.out.println("qna qnaID:: " + qna_id);
//        QnADTO qnADTO = qnAService.detailQnA(qna_id);
        return null;
    }
}
