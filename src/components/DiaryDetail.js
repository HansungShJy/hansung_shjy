import React, { useState, useEffect } from "react";
import axios from "axios";
import Header from "./Header";
import "./DiaryDetail.css";
import { useCookies } from "react-cookie";

function DiaryDetail() {
  const [cookies] = useCookies(["user_id"]);
  const couple_id = cookies.user_id;

  const [diarydate, setDiaryDate] = useState(Date);
  const [usercontent, setUserContent] = useState("");
  const [othercontent, setOtherContent] = useState("");

  const onDiaryDate = (e) => {
    setDiaryDate(e.target.value);
  };
  const onDiaryUser = (e) => {
    setUserContent(e.target.value);
  };
  const onDiaryOther = (e) => {
    setOtherContent(e.target.value);
  };

  return (
    <div>
      <Header />

      <div>
        <h4 className="diarydate_lb">오늘의 날짜</h4>

        <input
          type="date"
          className="diarydate_ip"
          value={diarydate}
          onChange={onDiaryDate}
        />

        <h4 className="diaryphoto_lb">사진 등록</h4>
        {/* 여기에 사진 등록하는 function 넣기 */}
        <br />
        <h4 className="diary_lb">오늘의 일기</h4>
        <textarea
          className="diaryuser_txt"
          placeholder="닉네임1"
          value={usercontent}
          onChange={onDiaryUser}
        />
        <textarea
          className="diaryother_txt"
          placeholder="닉네임2"
          value={othercontent}
          onChange={onDiaryOther}
        />
      </div>
    </div>
  );
}

export default DiaryDetail;
