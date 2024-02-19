import React, { useState, useEffect } from "react";
import axios from "axios";
import Header from "./Header";
import "./Diary.css";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";

function Diary() {
  const navigate = useNavigate();
  const [cookies] = useCookies(["user_id", "couple_id"]);
  const [images, setImages] = useState([]);
  const couple_id = cookies.couple_id;
  const [diaryArray, setDiaryArray] = useState([]);

  useEffect(() => {
    axios
      .get(`http://localhost:3000/diary`, {
        params: {
          couple_id: couple_id,
        },
      })
      .then((res) => {
        //console.log(JSON.stringify(res.data) + "::first res");
        const diaryArray = res.data.map((item) => item || "");
        setDiaryArray(diaryArray);
      })
      .catch((err) => {
        console.log(err);
        setImages([]);
      });
  }, [couple_id]);

  const diary_detail = (diary_id) => {
    navigate(`/editdiary/${diary_id}`, {
      state: {
        diary_id: diary_id,
      },
    });
  };

  const GotoDiary = () => {
    navigate("/diarydetail");
  };

  const GotoList = () => {
    navigate("/diary/list");
  };

  return (
    <div>
      <Header />
      <div>
        <button className="diarylist_btn" onClick={GotoList}>
          일기 리스트
        </button>
        <button className="diary_btn" onClick={GotoDiary}>
          일기 쓰기
        </button>
        <br />
        <div className="image_div">
          {diaryArray.map((diary, index) => (
            <img
              key={diary.diary_id}
              className="outputImage"
              src={`data:image/png;base64,${diary.image}`}
              alt={`Image ${index}`}
              onClick={() => {
                diary_detail(diary.diary_id);
              }}
            />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Diary;
