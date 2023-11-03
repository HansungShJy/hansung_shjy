package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Integer>, JpaSpecificationExecutor<Bank> {

    // Bank All
    @Query(value = "SELECT * FROM Bank b WHERE b.userID = :user_id", nativeQuery = true)
    List<Bank> findByUserID(@Param("user_id") Integer user_id);

    // Bank Modal
    @Query(value = "SELECT * FROM Bank b WHERE b.userID = :user_id AND b.bankDate = :bank_date", nativeQuery = true)
    List<Bank> findAllByUserIDAndBankDate(@Param("user_id") Integer user_id, @Param("bank_date") Date bank_date);

    // Bank Modify
    @Query(value = "SELECT * FROM Bank b WHERE b.userID = :user_id AND b.bankID = :bank_id", nativeQuery = true)
    Bank findAllByUserIDAndBankID(@Param("user_id") Integer user_id, @Param("bank_id") Integer bank_id);

    // Bank Delete
    @Query(value = "DELETE * FROM Bank b WHERE b.bankID = :bank_id", nativeQuery = true)
    Bank findByBankID(@Param("bank_id") Integer bank_id);
}
