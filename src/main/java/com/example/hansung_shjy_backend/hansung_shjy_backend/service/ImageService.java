package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.ImageDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ExecutionException;

public interface ImageService {

    ImageDTO saveImage(ImageDTO imageDTO, DiaryDTO diaryDTO) throws ExecutionException, InterruptedException;

    Image detailImage(Integer diaryID) throws ExecutionException, InterruptedException;
}
