package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.DiaryRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.ImageRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    //// TODO - diaryDTO 안에 image가 있는데 image는 MultipartFile로 보내줘야함 ... ---> 결국 테이블 새로 파기 ...

//        Diary diary = new Diary();

//        String sourceFileName = diaryDTO.getFileOriName();
//        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
//        File destinationFile;
//        String destinationFileName;
//
//        // TODO - 상대경로 쓰면 안되고 새로 경로 팔 것
//
//        String fileUrl = "C:/Users/jang/IdeaProjects/hansung_shjy_BackEnd/src/main/resources/static/images/";
//
//        do {
//        		destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
//        		destinationFile = new File(fileUrl + destinationFileName);
//        } while (destinationFile.exists());
//
//        destinationFile.getParentFile().mkdirs();
////        files.transferTo(destinationFile);
//
//        diary.setFileName(destinationFileName);
//        diary.setFileOriName(sourceFileName);
//        diary.setFileUrl(fileUrl);
}
