package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findUserById(String id);

    User findUserByEmail(String email);

    User findUserByUserID(Integer user_id);

    User findUserByNickname(String nickname);

    @Query(value = "SELECT nickname FROM user u WHERE u.email = :email", nativeQuery = true)
    String findAllByEmail(@Param("email") String email);


    // nickname & birth 둘 다 수정
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE User u SET u.nickname = :nickname AND u.birth = :birth WHERE u.userID = :userID", nativeQuery = true)
    User updateUserByNicknameAndBirth(@Param("nickname") String nickname, @Param("birth") Date birth, @Param("userID") Integer userID);

    // nickname만 수정
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE User u SET u.nickname = :nickname WHERE u.userID = :userID", nativeQuery = true)
    User updateUserByNickname(@Param("nickname") String nickname, @Param("userID") Integer userID);

    // birth만 수정
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE User u SET u.birth = :birth WHERE u.userID = :userID", nativeQuery = true)
    User updateUserByBirth(@Param("birth") Date birth, @Param("userID") Integer userID);

    // 회원 탈퇴
    @Query(value = "DELETE FROM User u WHERE u.userID = :userID", nativeQuery = true)
    User findByUserID(@Param("userID") Integer userID);
}
