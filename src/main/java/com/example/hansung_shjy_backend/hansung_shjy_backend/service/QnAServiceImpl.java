package com.example.hansung_shjy_backend.hansung_shjy_backend.service;

import com.example.hansung_shjy_backend.hansung_shjy_backend.dto.QnADTO;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.QnA;
import com.example.hansung_shjy_backend.hansung_shjy_backend.entity.User;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.QnARepository;
import com.example.hansung_shjy_backend.hansung_shjy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class QnAServiceImpl implements QnAService {

    @Autowired
    private QnARepository qnARepository;

    @Autowired
    private UserRepository userRepository;


    // 오늘의 질문 첫 화면
    @Override
    public List<QnA> listQnA(Integer user_id) throws ExecutionException, InterruptedException {
        System.out.println("listQnA:: " + user_id);
        if(user_id == null) return null;

        List<QnA> qnA = qnARepository.findAllByUserID(user_id);
        if (qnA == null) return null;
        else return qnA;
    }

    // 오늘의 질문 디테일 화면
    @Override
    public QnA detailQnA(Integer qnaID) throws ExecutionException, InterruptedException {
        System.out.println("detailQnADTO:: " + qnaID);
        if (qnaID == null) return null;

        QnA qnA = qnARepository.findQnAByQnaID(qnaID);
        System.out.println("detailQnA:: " + qnA);
        if (qnA == null) return null;
        else return qnA;
    }

    // 오늘의 질문 저장
    @Override
    public QnADTO saveQnA(QnADTO qnADTO) throws ExecutionException, InterruptedException {
        // db -> qna_id, qna_date, my_answer, other_answer, userid(Integer), otherid(String)
        QnA qnA = new QnA();
        qnA.setQnaDate(qnADTO.getQnaDate());
        qnA.setUserID(userRepository.findUserByUserID(qnADTO.getUserID()));
        qnA.setOtherID(userRepository.findUserByNickname(qnADTO.getOtherID()));

        User me = userRepository.findUserByUserID(qnADTO.getUserID());  // 나의 user 객체
        Integer myUserid = qnADTO.getUserID(); // 나의 userID
        String myNickname = me.getNickname();  // 나의 nickname
        String qnAOtherid = qnADTO.getOtherID(); // qnA otherID (Nickname)

        String otherNickname = me.getOtherID(); // 상대방 nickname
        User other = userRepository.findUserByNickname(otherNickname); // 상대방의 user 객체
        Integer otherUserid = other.getUserID(); // 상대방의 userID


        // 짝꿍과 서로 cross
        if (me.getOtherID().equals(other.getNickname()) && other.getOtherID().equals(me.getNickname())) {  // 내 user 객체에 otherid(nickname)이 상대방 user 객체의 nickname과 같으면
            // userid가 나랑 같냐 상대방과 같냐로 구분
            if (me.getUserID().equals(myUserid)) {  //내 userid와 qnaDTO에 있는 userid와 같으면 -> 내가 my_answer에 저장, 상대방이 other_answer에 저장
                qnA.setMyAnswer(qnADTO.getMyAnswer());
            } else if (myNickname.equals(qnAOtherid)) { //내 nickname과 qnaDTO에 있는 otherid와 같으면 --> 내가 other_answer에 저장, 상대방이 my_answer에 저장
                qnA.setOtherAnswer(qnADTO.getMyAnswer());
            }
        }

        QnA savedQnA = qnARepository.save(qnA);

        return qnADTO.toDTO(savedQnA);
    }

    // 오늘의 질문 수정
    @Override
    public QnADTO modifyQnA(QnADTO qnADTO) throws ExecutionException, InterruptedException {
        QnA qnA = qnARepository.findAllByUserIDAndQnaID(qnADTO.getUserID(), qnADTO.getQnaID());
        System.out.println("modifyQnA:: " + qnA);
        if (qnA == null) return null;

        qnA.setQnaDate(qnADTO.getQnaDate());
        qnA.setMyAnswer(qnA.getMyAnswer());
        qnA.setUserID(qnA.getUserID());
        qnARepository.save(QnA.toEntity(qnADTO));
        return qnADTO;
    }
}
