import Header from "./Header";
import { useCookies } from "react-cookie";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import "./QnaDetail.css";

const QnaDetail = ({ questions }) => {
  const { qna_id } = useParams();
  const [cookies] = useCookies(["user_id"]);
  const userid = cookies.user_id;
  const [userAnswer, setUserAnswer] = useState("");
  const [otherAnswer, setOtherAnswer] = useState("");
  const maxChar = 150;

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
          userid: userid,
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
  }, [qna_id]);

  const handleUserChange = (event) => {
    const inputText = event.target.value;
    setUserAnswer(inputText.slice(0, maxChar)); // 최대 글자 수까지만 허용
  };

  const handleOtherChange = (event) => {
    const inputText = event.target.value;
    setOtherAnswer(inputText.slice(0, maxChar)); // 최대 글자 수까지만 허용
  };

  const QnaAnswerSave = () => {
    axios
      .post(`http://localhost:3000/qna/save`, {
        // qnaTitle: ,
        qnaDate: new Date(),
        myAnswer: userAnswer,
        otherID: otherNickname,
        userID: userid,
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + ":: save res");
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
        <button className="QnaD_btn" onClick={QnaAnswerSave}>
          저장
        </button>
      </div>
    </div>
  );
};

export default QnaDetail;
