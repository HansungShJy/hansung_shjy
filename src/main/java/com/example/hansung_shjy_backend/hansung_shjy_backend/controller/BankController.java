package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.BankDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.BankRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.BankService;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankRepository bankRepository;


    // 우리의 지출 첫 화면 ===================================================================
    @GetMapping("/pay")
    public ResponseEntity<Object> firstBank(@RequestParam("coupleID") Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("bank couple_id:: " + couple_id);
        List<Bank> bankList = bankService.listBank(couple_id);
        System.out.println("bankList:: " + bankList);

        List<BankDTO> bankDTOList = bankList.stream()
                .map(bank -> new BankDTO(bank.getBankID(), bank.getBankDate(), bank.getPayMethod(), bank.getBankTitle(), bank.getMoney(), bank.getCouple().getCoupleID()))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(bankDTOList);
    }

    // 우리의 지출 모달창 ====================================================================
    @GetMapping("/pay/detail/{bankDate}")
    public ResponseEntity<Object> modalBank(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate bankDate, @RequestParam Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("bankDate:: " + bankDate + ", " + couple_id);
        List<Bank> bankList = bankService.modalBank(couple_id, bankDate);

        List<BankDTO> bankDTOList = bankList.stream()
                .map(bank -> new BankDTO(bank.getBankID(), bank.getBankDate(), bank.getPayMethod(), bank.getBankTitle(), bank.getMoney(), bank.getCouple().getCoupleID()))
                .collect(Collectors.toList());
        System.out.println("bankList_modal:: " + bankDTOList);
        return ResponseEntity.ok().body(bankDTOList);
    }

    // 우리의 지출 등록 =====================================================================
    @PostMapping("/pay/save")
    public ResponseEntity<Object> createBank(@RequestBody BankDTO bankDTO) throws ExecutionException, InterruptedException {
        BankDTO bank = bankService.createBank(bankDTO);
        System.out.println("createBank:: " + bank);
        if (bank == null) return new ResponseEntity<Object>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(bank);
    }

    // 우리의 지출 수정 =====================================================================
    @PatchMapping("/pay/edit/{bank_id}")
    public ResponseEntity<Object> modifyBank(@PathVariable Integer bank_id, @RequestBody BankDTO bankDTO) throws ExecutionException, InterruptedException {
        System.out.println("bankmodifyId:: " + bank_id + ", " + bankDTO);
        Bank bank = bankService.modifyBank(bank_id);

        if (bank == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);

        bank.setBankDate(bankDTO.getBankDate());
        bank.setBankTitle(bankDTO.getBankTitle());
        bank.setPayMethod(bankDTO.getPayMethod());
        bank.setMoney(bankDTO.getMoney());

        bankRepository.save(bank);

        return ResponseEntity.ok().body(bank);
    }

    // 우리의 지출 삭제
    @DeleteMapping("/pay/delete/{bank_id}")
    public ResponseEntity<Object> deleteBank(@PathVariable Integer bank_id) throws ExecutionException, InterruptedException {
        System.out.println("deleteBankID:: " + bank_id);
        bankService.deleteBank(bank_id);

        if (bank_id == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body("bank delete");
    }
}
