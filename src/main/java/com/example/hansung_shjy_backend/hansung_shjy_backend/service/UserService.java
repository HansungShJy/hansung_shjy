package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import java.util.concurrent.ExecutionException;

public interface UserService {

    //아이디 중복체크
    String verifyID(String id) throws ExecutionException, InterruptedException;



}
