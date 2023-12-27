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
  const { qna_id } = useParams();
  const [cookies] = useCookies(["couple_id", "user_id"]);
  const couple_id = cookies.couple_id;
  const user_id = cookies.user_id;
  const [userAnswer, setUserAnswer] = useState("");
  const [otherAnswer, setOtherAnswer] = useState("");
  const maxChar = 150;

  const [isEditing, setIsEditing] = useState(false);

  const toggleEditing = () => {
    setIsEditing((prevEditing) => !prevEditing);
  };

  const [nicknamelist, setNickNameList] = useState([
    {
      myNickname: "",
      otherNickname: "",
    },
  ]);

  useEffect(() => {
    axios
      .get(`http://localhost:3000/qna/detail/${qna_id}`, {
        params: {
          coupleID: couple_id,
        },
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + "::res");
        const username = res.data.myNickname;
        const othername = res.data.otherNickname;
        console.log(username, othername);
        setNickNameList({
          myNickname: username,
          otherNickname: othername,
        });
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
    axios
      .post(`http://localhost:3000/qna/save`, {
        qnaTitle: questionText,
        qnaDate: new Date(),
        myAnswer: userAnswer,
        user_id: user_id,
        coupleID: couple_id,
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + ":: save res");
      })
      .catch((err) => {
        console.log(err + "save err");
      });
  };

  const QnaAnswerEdit = () => {
    axios
      .patch(`http://localhost:3000/qna/edit/${qna_id}`, {
        qnaTitle: questionText,
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
  const { myNickname, otherNickname } = nicknamelist;

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
          <img
            className="edit_logo"
            src={editlogo}
            alt="edit_icon.png"
            width="25"
            onClick={toggleEditing}
          />
        </div>
        <br />
        <label className="QnaD_lb"> {myNickname} 님의 답변</label>
        <br />
        <textarea
          className="QnaD_tx"
          value={userAnswer}
          onChange={handleUserChange}
          maxLength={maxChar}
        ></textarea>
        <br />
        <label className="countChar">{`${charCount}/${maxChar}`}</label>
        <br />
        <label className="QnaD_lb">{otherNickname} 님의 답변</label>

        <br />
        <textarea
          className="QnaD_tx"
          disabled
          value={otherAnswer}
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
