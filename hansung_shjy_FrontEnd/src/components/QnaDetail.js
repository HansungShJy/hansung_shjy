import Header from "./Header";
import { useCookies } from "react-cookie";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import { useLocation } from "react-router-dom";
import "./QnaDetail.css";
import editlogo from "../assets/edit_icon.png";

const QnaDetail = () => {
  const location = useLocation();
  const questionText = location.state?.questionText;
  const questionID = location.state?.questionID;
  const { qna_id } = useParams();
  const [cookies] = useCookies(["couple_id", "user_id", "nickname"]);
  const couple_id = cookies.couple_id;
  const user_id = cookies.user_id;

  const encodedNickname = decodeURIComponent(cookies.nickname);
  const [userNN, setUserNN] = useState("");
  const [otherNN, setOtherNN] = useState("");
  const [userAnswer, setUserAnswer] = useState("");
  const [otherAnswer, setOtherAnswer] = useState("");
  const [userqna, setUserQna] = useState("");
  const [otherqna, setOtherQna] = useState("");
  const [userID1, setUserID1] = useState("");
  const [userID2, setUserID2] = useState("");

  const maxChar = 150;

  const [isEditing, setIsEditing] = useState(false);

  const toggleEditing = () => {
    setIsEditing((prevEditing) => !prevEditing);
  };

  useEffect(() => {
    axios
      .get(`http://localhost:3000/qna/detail/${qna_id}`, {
        params: {
          coupleID: couple_id,
        },
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + "::res");

        setUserNN(res.data.nickname1);
        setOtherNN(res.data.nickname2);
        setUserQna(res.data.qnaDetail.myAnswer);
        setOtherQna(res.data.qnaDetail.otherAnswer);

        setUserID1(res.data.userID1);
        setUserID2(res.data.userID2);
      })
      .catch((err) => {
        console.log(err + "::err");
      });
  }, [qna_id, couple_id]);

  const handleUserChange = (event) => {
    const inputText = event.target.value;
    setUserAnswer(inputText.slice(0, maxChar));
  };

  const handleOtherChange = (event) => {
    const inputText = event.target.value;
    setOtherAnswer(inputText.slice(0, maxChar));
  };

  const QnaAnswerSave = () => {
    console.log(questionID);
    axios
      .post(`http://localhost:3000/qna/save`, {
        qnaDate: new Date(),
        myAnswer: userAnswer,
        userID: user_id,
        coupleID: couple_id,
        qnaNumber: questionID,
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + ":: save res");
        alert("저장이 완료되었습니다.");
      })
      .catch((err) => {
        console.log(err + "save err");
        alert("저장 오류. 다시 시도하세요.");
      });
  };

  const QnaAnswerEdit = () => {
    axios
      .patch(`http://localhost:3000/qna/edit/${qna_id}`, {
        qnaDate: new Date(),
        myAnswer: userAnswer,
        user_id: user_id,
        coupleID: couple_id,
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + ":: save res");
        toggleEditing();
      })
      .catch((err) => {
        console.log(err + "save err");
      });
  };

  const charCount = userAnswer.length;
  const charCount2 = otherAnswer.length;

  var today = new Date();
  var year = today.getFullYear();
  var month = ("0" + (today.getMonth() + 1)).slice(-2);
  var day = ("0" + today.getDate()).slice(-2);
  var dateString = year + "." + month + "." + day;

  return (
    <div>
      <Header />
      <div className="QnaD">
        <label className="QnaD_date">{dateString}</label>
        <br />
        <div>
          <label className="Qnatext">{questionText}</label>
        </div>
        <br />
        <label className="QnaD_lb">
          {" "}
          {decodeURIComponent(encodedNickname)} 님의 답변
        </label>
        <img
          className="edit_logo"
          src={editlogo}
          alt="edit_icon.png"
          width="25"
          onClick={toggleEditing}
        />
        <br />
        <textarea
          className="QnaD_tx"
          value={userAnswer}
          placeholder={Number(userID2) === user_id ? otherqna : userqna}
          onChange={handleUserChange}
          maxLength={maxChar}
        ></textarea>
        <br />
        <label className="countChar">{`${charCount}/${maxChar}`}</label>
        <br />
        <label className="QnaD_lb">
          {encodedNickname === userNN ? otherNN : userNN} 님의 답변
        </label>

        <br />
        <textarea
          className="QnaD_tx"
          disabled
          value={otherAnswer}
          placeholder={Number(userID1) === user_id ? otherqna : userqna}
          onChange={handleOtherChange}
          maxLength={maxChar}
        ></textarea>
        <br />
        <label className="countChar">{`${charCount2}/${maxChar}`}</label>
        <br />
        {isEditing ? (
          <button className="QnaD_btn" onClick={QnaAnswerEdit}>
            수정
          </button>
        ) : (
          <button className="QnaD_btn" onClick={QnaAnswerSave}>
            저장
          </button>
        )}
      </div>
    </div>
  );
};

export default QnaDetail;
