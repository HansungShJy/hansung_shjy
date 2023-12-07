package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.BankDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BankService {

    // 우리의 지출 첫 화면 =========================================================
    List<Bank> listBank(Integer user_id) throws ExecutionException, InterruptedException;

    // 우리의 지출 모달창 (목록) ====================================================
    List<Bank> modalBank(Integer user_id, LocalDate bank_date) throws ExecutionException, InterruptedException;

    // 우리의 지출 등록 ============================================================
    BankDTO createBank(BankDTO bankDTO) throws ExecutionException, InterruptedException;

    // 우리의 지출 수정 ============================================================
    BankDTO modifyBank(BankDTO bankDTO) throws ExecutionException, InterruptedException;

    // 우리의 지출 삭제 ============================================================
    void deleteBank(Integer bank_id) throws ExecutionException, InterruptedException;


}
