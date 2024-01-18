import React, { useState, useEffect } from "react";
import axios from "axios";
import Header from "./Header";
import "./Diary.css";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";

function Diary() {
  const navigate = useNavigate();
  const [cookies] = useCookies(["user_id"]);
  const couple_id = cookies.user_id;
  useEffect(() => {
    axios
      .get(`http://localhost:3000/diary`, {
        params: {
          couple_id: couple_id,
        },
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + "::res");
      })
      .catch((err) => {
        console.log(err);
      });
  }, [couple_id]);

  const GotoDiary = () => {
    navigate("/diarydetail");
  };

  return (
    <div>
      <Header />
      <div>
        <button className="diarylist_btn" onClick={GotoDiary}>
          일기 리스트
        </button>
        <button className="diary_btn" onClick={GotoDiary}>
          일기 쓰기
        </button>
      </div>
    </div>
  );
}

export default Diary;
