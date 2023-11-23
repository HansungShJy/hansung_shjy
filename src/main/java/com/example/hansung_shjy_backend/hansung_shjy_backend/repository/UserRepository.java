package com.example.hansung_shjy_backend.hansung_shjy_backend.repository;

import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findUserById(String id);

    User findUserByEmail(String email);

    User findUserByUserID(Integer user_id);

    User findUserByNickname(String nickname);


    @Query(value = "SELECT nickname FROM user u WHERE u.email = :email", nativeQuery = true)
    String findAllByEmail(@Param("email") String email);

    @Query(value = "SELECT userid FROM user u WHERE u.userid = :userid", nativeQuery = true)
    User findByUserID(@Param("userid") Integer userid);
}
