package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.BankDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BankService {

    // 우리의 지출 첫 화면 =========================================================
    List<Bank> listBank(Integer couple_id) throws ExecutionException, InterruptedException;

    // 우리의 지출 모달창 (목록) ====================================================
    List<Bank> modalBank(Integer couple_id, LocalDate bank_date) throws ExecutionException, InterruptedException;

    // 우리의 지출 등록 ============================================================
    BankDTO createBank(BankDTO bankDTO) throws ExecutionException, InterruptedException;

    // 우리의 지출 수정 ============================================================
    Bank modifyBank(Integer bank_id, BankDTO bankDTO) throws ExecutionException, InterruptedException;

    // 우리의 지출 삭제 ============================================================
    String deleteBank(Integer bank_id) throws ExecutionException, InterruptedException;


}
