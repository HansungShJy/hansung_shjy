import Header from "./Header";
import { useCookies } from "react-cookie";
import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Qna.css";
import { useNavigate } from "react-router-dom";

function Qna() {
  const [cookies] = useCookies(["couple_id"]);
  const couple_id = cookies.couple_id;

  const navigate = useNavigate();
  const [questions, setQuestions] = useState([
    {
      id: "1",
      text: "오늘 저녁, 그 사람과 손잡고 걷고 싶은 산책 코스는 어디인가요?",
    },
    {
      id: "2",
      text: "연인들이 이별하는 가장 큰 이유는 무엇이라고 생각하나요?",
    },
    {
      id: "3",
      text: "만 원으로 종일 데이트를 한다면 어떤 하루를 보낼 수 있을까요?",
    },
  ]);

  const handleQuestionClick = (question) => {
    navigate(`/qnadetail/${question.id}`, {
      state: { questionText: question.text },
    });
  };

  useEffect(() => {
    axios
      .get(`http://localhost:3000/qna`, {
        params: {
          coupleID: couple_id,
        },
      })
      .then((res) => {
        console.log(res + "::res");
      })
      .catch((err) => {
        console.log(err + "::err");
      });
  }, [couple_id]);

  return (
    <div>
      <Header />
      <div className="QnaTop">
        {/* onclick을 만든 후 key를 question.id로 지정. 넘길때 title 보내주기 */}
        {questions.map((question, index) => (
          <div key={index}>
            <div
              className="Qnaborder"
              onClick={() => handleQuestionClick(question)}
            >
              <label className="Qnaindex_lb">#{index + 1}. </label>
              {question.text}
            </div>

            {index < questions.length - 1 && (
              <hr className="Qnahr" color="#d9d9d9" />
            )}
          </div>
        ))}
      </div>
    </div>
  );
}

export default Qna;
