package com.example.hansung_shjy_backend.hansung_shjy_backend.controller;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiaryRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.DiarySaveRequest;
import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.ImageDTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Couple;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Diary;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.Image;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.CoupleRepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.DiaryService;
import com.example.hansung_shjy_backend.hansung_shjy_backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CoupleRepository coupleRepository;


    // 홈 화면 ============================================================================
    @GetMapping("/diary")
    public ResponseEntity<Object> diaryFirst(@RequestParam("couple_id") Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("diary couple_id:: " + couple_id);

        User me = coupleRepository.findByCoupleID(couple_id).getMe();
        String myNickname = me.getNickname();
        String otherNickname = me.getOtherID();
        Integer coupleID = me.getCouple().getCoupleID();
        Couple couple = coupleRepository.findByCoupleID(coupleID);
        Integer myUserID = couple.getMe().getUserID();
        Integer otherUserID = couple.getOther().getUserID();

        Map<Diary, Image> diaryImageMap = diaryService.listDiary(couple_id);

        List<DiaryRequest> diaryDTOList = diaryImageMap.entrySet().stream()
                .map(entry -> {
                    Diary diary = entry.getKey();
                    Image image = entry.getValue();

                    DiaryRequest diaryRequest = new DiaryRequest();
                    diaryRequest.setDiary(diary);
                    diaryRequest.setImage(image);
                    return diaryRequest;    // diary & image 묶어서 같이 보내주기
                })
                .toList();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("nickname1", myNickname);
        resultMap.put("nickname2", otherNickname);
        resultMap.put("userID1", myUserID);
        resultMap.put("userID2", otherUserID);
        resultMap.put("diaryDetail", diaryImageMap);

        System.out.println("diaryDTO:: " + diaryDTOList);
        return ResponseEntity.ok().body(resultMap);
    }

    // 일기 저장 ===========================================================================
    @PostMapping("/diary/save/{couple_id}")
    public ResponseEntity<Object> createDiary(@PathVariable Integer couple_id, @RequestBody DiarySaveRequest diarySaveRequest) throws ExecutionException, InterruptedException {
        System.out.println("create Diary couple_id:: " + couple_id);
        System.out.println("create diaryDTO:: " + diarySaveRequest.getDiaryDTO() + ", imageDTO:: " + diarySaveRequest.getImageDTO());

        Map<String, Object> resultMap = new HashMap<>();

        Couple couple = coupleRepository.findByCoupleID(couple_id);  // diary 저장

        DiaryDTO diary = diaryService.createDiary(couple, diarySaveRequest.getDiaryDTO());

        ImageDTO imgDto = new ImageDTO();

        MultipartFile image = diarySaveRequest.getImageDTO();

        // 폴더 생성과 파일명 새로 부여를 위한 현재 시간 알아내기
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int millis = now.get(ChronoField.MILLI_OF_SECOND);

        String absolutePath = new File("/Users/project/images/").getAbsolutePath() + "/"; // 파일이 저장될 절대 경로
        String newFileName = "image" + hour + minute + second + millis; // 새로 부여한 이미지명
        String fileExtension = '.' + image.getOriginalFilename().replaceAll("^.*\\.(.*)$", "$1"); // 정규식 이용하여 확장자만 추출
        String path = "images/" + year + "/" + month + "/" + day; // 저장될 폴더 경로

        try {
            if(!image.isEmpty()) {
                File file = new File(absolutePath + path);
                if(!file.exists()){
                    file.mkdirs(); // mkdir()과 다르게 상위 폴더가 없을 때 상위폴더까지 생성
                }

                file = new File(absolutePath + path + "/" + newFileName + fileExtension);
//                imageV1.transferTo(file);

                imgDto = ImageDTO.builder()
                        .imageName((newFileName + fileExtension))
                        .imageOriName(image.getOriginalFilename())
                        .imageUrl(path)
                        .diaryID(diary.getDiaryID())
                        .build();

                imageService.saveImage(imgDto, diary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        resultMap.put("diary", diary);
        resultMap.put("image", imgDto);

        if (diary == null || image == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(resultMap);
    }

    // 일기 세부 ===========================================================================
    @GetMapping("/diary/detail/{diary_id}")
    public ResponseEntity<Object> editDiary(@PathVariable Integer diary_id, @RequestParam Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("diaryDetail diary_id:: " + diary_id);
        System.out.println("diaryDetail couple_id:: " + couple_id);

        Image image = imageService.detailImage(diary_id);

        if (image == null) return new ResponseEntity<>("null exception", HttpStatus.BAD_REQUEST);
        else return ResponseEntity.ok().body(image);
    }

    // 일기 전체 보기 리스트 =================================================================
    @GetMapping("/diary/list/{couple_id}")
    public ResponseEntity<Object> listAllDiary(@PathVariable Integer couple_id) throws ExecutionException, InterruptedException {
        System.out.println("<diary> couple_id::" + couple_id);

        Map<Diary, Image> diaryImageMap = diaryService.listDiary(couple_id);

        if (diaryImageMap.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<DiaryRequest> diaryDTOList = diaryImageMap.entrySet().stream()
                .map(entry -> {
                    Diary diary = entry.getKey();
                    Image image = entry.getValue();

                    DiaryRequest diaryRequest = new DiaryRequest();
                    diaryRequest.setDiary(diary);
                    diaryRequest.setImage(image);
                    return diaryRequest;    // diary & image 묶어서 같이 보내주기
                })
                .toList();

        System.out.println("diary listAll:: " + diaryDTOList);

        return ResponseEntity.ok().body(diaryDTOList);
    }
}
