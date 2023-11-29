package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Bank;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Integer>, JpaSpecificationExecutor<Bank> {

    // Bank All
    @Query(value = "SELECT * FROM Bank b WHERE b.userID = :userID", nativeQuery = true)
    List<Bank> findBankByUserID(@Param("userID") Integer userID);

    // Bank Modal
    @Query(value = "SELECT * FROM Bank b WHERE b.userID = :userID AND b.bankDate = :bankDate", nativeQuery = true)
    List<Bank> findAllByUserIDAndBankDate(@Param("userID") Integer userID, @Param("bankDate") LocalDate bankDate);

    // Bank Modify
    @Query(value = "SELECT * FROM Bank b WHERE b.userID = :userID AND b.bankID = :bankID", nativeQuery = true)
    Bank findAllByUserIDAndBankID(@Param("userID") Integer userID, @Param("bankID") Integer bankID);

    // Bank Delete
    @Query(value = "DELETE * FROM Bank b WHERE b.bankID = :bankID", nativeQuery = true)
    Bank findByBankID(@Param("bankID") Integer bankID);
}
