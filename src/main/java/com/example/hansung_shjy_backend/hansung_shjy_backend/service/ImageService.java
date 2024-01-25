package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.ImageDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;

import java.util.concurrent.ExecutionException;

public interface ImageService {

    ImageDTO saveImage(ImageDTO imageDTO, Diary diary) throws ExecutionException, InterruptedException;

    Image detailImage(Integer diaryID) throws ExecutionException, InterruptedException;
}
