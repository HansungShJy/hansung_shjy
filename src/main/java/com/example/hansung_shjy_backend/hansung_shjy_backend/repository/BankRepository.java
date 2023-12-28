package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Integer>, JpaSpecificationExecutor<Bank> {

    // Bank All
    @Query(value = "SELECT * FROM Bank b WHERE b.coupleID = :coupleID", nativeQuery = true)
    List<Bank> findBankByCouple(@Param("coupleID") Integer coupleID);

    // Bank Modal
    @Query(value = "SELECT * FROM Bank b WHERE b.coupleID = :coupleID AND b.bank_date = :bank_date", nativeQuery = true)
    List<Bank> findAllByCoupleDAndBankDate(@Param("coupleID") Integer coupleID, @Param("bank_date") LocalDate bank_date);

    // Bank Modify
//    User findUserByUserID(Integer user_id);
//    @Query(value = "SELECT * FROM Bank b WHERE b.bankID = :bankID", nativeQuery = true)
//    Bank findBankByBankID(@Param("bankID") Integer bankID);

    Bank findBankByBankID(Integer bank_id);

    // Bank Delete
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM Bank b WHERE b.bankID = :bankID", nativeQuery = true)
    Integer findByBankID(@Param("bankID") Integer bankID);
}
