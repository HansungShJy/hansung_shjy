package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findUserById(String id);

    User findUserByEmail(String email);

    User findUserByUserID(Integer user_id);

    User findUserByNickname(String nickname);

    @Query(value = "SELECT nickname FROM user u WHERE u.email = :email", nativeQuery = true)
    String findAllByEmail(@Param("email") String email);

    // 회원 탈퇴
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM User u WHERE u.userID = :userID", nativeQuery = true)
    Integer findByUserID(@Param("userID") Integer userID);
}
