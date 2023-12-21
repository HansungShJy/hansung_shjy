import Header from "./Header";
import { useCookies } from "react-cookie";
import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Qna.css";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

function Qna() {
  const [cookies] = useCookies(["user_id"]);
  const userid = cookies.user_id;

  const Questions = [
    {
      //onclick 함수를 만듦.
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
  ];
  const [questions, setQuestions] = useState(Questions);

  useEffect(() => {
    axios
      .get(`http://localhost:3000/qna`, {
        params: {
          userid: userid,
        },
      })
      .then((res) => {
        console.log(res + "::res");
      })
      .catch((err) => {
        console.log(err + "::err");
      });
  }, [userid]);

  return (
    <div>
      <Header />
      <div className="QnaTop">
        {/* onclick을 만든 후 key를 question.id로 지정. 넘길때 title 보내주기 */}
        {questions.map((question, index) => (
          <div key={index}>
            <Link to={`/qnadetail/${question.id}`}>
              <div className="Qnaborder">
                <label className="Qnaindex_lb">#{index + 1}. </label>
                {question.text}
              </div>
            </Link>
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
