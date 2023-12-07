package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.BankDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.BankRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;


    // 우리의 지출 첫 화면
    @Override
    public List<Bank> listBank(Integer user_id) throws ExecutionException, InterruptedException {
        System.out.println("listBank user_id:: " + user_id);
        if (user_id == null) return null;

        List<Bank> bank = bankRepository.findBankByUserID(user_id);
        System.out.println("bank list:: " + bank);
        if (bank == null) return null;
        else return bank;
    }

    // 우리의 지출 모달창
    @Override
    public List<Bank> modalBank(Integer userid, LocalDate bankDate) throws ExecutionException, InterruptedException {
        System.out.println("modalBank:: " + userid + ", " + bankDate);
        if (userid == null || bankDate == null) return null;

        List<Bank> bankList = bankRepository.findAllByUserIDAndBankDate(userid, bankDate);
        System.out.println("bankList:: " + bankList);
        if (bankList == null) return null;
        else return bankList;
    }

    // 우리의 지출 등록
    @Override
    public BankDTO createBank(BankDTO bankDTO) throws ExecutionException, InterruptedException {
        Bank bank = new Bank();
        bank.setBankDate(bankDTO.getBankDate());
        bank.setBankTitle(bankDTO.getBankTitle());
        bank.setPayMethod(bankDTO.getPayMethod());
        bank.setMoney(bankDTO.getMoney());
        User user = userRepository.findUserByUserID((bankDTO.getUserID()));
        bank.setUserID(user);
        Bank savedBank = bankRepository.save(bank);
        return BankDTO.toDTO(savedBank);
    }

    // 우리의 지출 수정
    @Override
    public BankDTO modifyBank(BankDTO bankDTO) throws ExecutionException, InterruptedException {
        Bank bank = bankRepository.findAllByUserIDAndBankID (bankDTO.getUserID(), bankDTO.getBankID());
        System.out.println("modifyBank:: " + bank);
        if (bank == null) return null;

        bank.setBankDate(bankDTO.getBankDate());
        bank.setBankTitle(bankDTO.getBankTitle());
        bank.setPayMethod(bankDTO.getPayMethod());
        bank.setMoney(bankDTO.getMoney());
        bankRepository.save(Bank.toEntity(bankDTO));
        return bankDTO;
    }

    // 우리의 지출 삭제

    @Override
    @Transactional
    public String deleteBank(Integer bank_id) throws ExecutionException, InterruptedException {
        Integer deleteBank = bankRepository.findByBankID(bank_id);
        if (deleteBank == null) return null;
        else return "bank delete";
    }
}
