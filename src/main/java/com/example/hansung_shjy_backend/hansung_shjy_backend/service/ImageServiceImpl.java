package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.ImageDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

import static com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary.toEntity;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageDTO saveImage(ImageDTO imageDTO, DiaryDTO diaryDTO) throws ExecutionException, InterruptedException {
        Image img = new Image();
        img.setImageName(imageDTO.getImageName());
        img.setImageOriName(imageDTO.getImageOriName());
        img.setImageUrl(imageDTO.getImageUrl());
        img.setDiary(toEntity(diaryDTO));
        imageRepository.save(img);
        return imageDTO;
    }

    @Override
    public Image detailImage(Integer diaryID) throws ExecutionException, InterruptedException {
        return imageRepository.findImageByDiary(diaryID);
    }
}
