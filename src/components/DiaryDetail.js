import React, { useState, useEffect } from "react";
import axios from "axios";
import Header from "./Header";
import "./DiaryDetail.css";
import { useCookies } from "react-cookie";

function DiaryDetail() {
  const [cookies] = useCookies(["couple_id", "user_id", "nickname"]);
  const couple_id = cookies.couple_id;
  const user_id = cookies.user_id;
  const [diarydate, setDiaryDate] = useState(Date);
  const [usercontent, setUserContent] = useState("");
  const [othercontent, setOtherContent] = useState("");
  const [userNN, setUserNN] = useState("");
  const [otherNN, setOtherNN] = useState("");
  const [userID1, setUserID1] = useState("");
  const [userID2, setUserID2] = useState("");
  const encodedNickname = decodeURIComponent(cookies.nickname);

  const onDiaryDate = (e) => {
    setDiaryDate(e.target.value);
  };
  const onDiaryUser = (e) => {
    setUserContent(e.target.value);
  };
  const onDiaryOther = (e) => {
    setOtherContent(e.target.value);
  };

  const DiarySave = (e) => {
    axios
      .post(`http://localhost:3000/diary/save/${couple_id}`, {
        diaryDate: diarydate,
        myDiary: usercontent,
        otherDiary: othercontent,
        // filename:
        // fileOriName:
        // fileUrl:
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <Header />

      <div>
        <button className="diarysave_btn" onClick={DiarySave}>
          일기 등록
        </button>
        <div className="diary_div">
          <h4 className="diarydate_lb">오늘의 날짜</h4>
          <input
            type="date"
            className="diarydate_ip"
            value={diarydate}
            onChange={onDiaryDate}
          />
        </div>

        <h4 className="diaryphoto_lb">사진 등록</h4>
        <form className="file_upload" name="form" method="post">
          <input
            type="file"
            name="image"
            accept="image/png, image/jpeg, image/jpg"
          />
        </form>
        <br />
        <h4 className="diary_lb">오늘의 일기</h4>
        <textarea
          className="diaryuser_txt"
          placeholder="일기는 수정할 수 없으니 등록하기 전에 확인 바랍니다."
          value={usercontent}
          onChange={onDiaryUser}
        />
        <textarea
          className="diaryother_txt"
          placeholder="일기는 수정할 수 없으니 등록하기 전에 확인 바랍니다."
          value={othercontent}
          onChange={onDiaryOther}
        />
      </div>
    </div>
  );
}

export default DiaryDetail;
