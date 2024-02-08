import React, { useState, useEffect } from "react";
import axios from "axios";
import Header from "./Header";
import "./DiaryList.css";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";

function DiaryList() {
  const [curDate, setCurDate] = useState(new Date());
  const [searchkey, setSearchKey] = useState("");
  const navigate = useNavigate();
  const [cookies] = useCookies(["user_id", "couple_id"]);
  const couple_id = cookies.couple_id;
  const [diaryArray, setDiaryArray] = useState([
    { diaryID: "", diaryDate: "", myDiary: "", otherDiary: "" },
  ]);
  const [diarySearch, setDiarySearch] = useState([]);
  const [copy, setCopy] = useState([]);

  const increaseMonth = () => {
    setCurDate(new Date(curDate.getFullYear(), curDate.getMonth() + 1));
  };

  const decreaseMonth = () => {
    setCurDate(new Date(curDate.getFullYear(), curDate.getMonth() - 1));
  };

  const headText = `${curDate.getFullYear()}년 ${curDate.getMonth() + 1}월`;

  const onkeyword = (e) => {
    setSearchKey(e.target.value);
  };

  const diaryClick = (diaryID) => {
    navigate(`/editdiary/${diaryID}`, {
      state: {
        diary_id: diaryID,
      },
    });
  };

  useEffect(() => {
    axios
      .get(`http://localhost:3000/diary/list/${couple_id}`, {
        params: {
          couple_id: couple_id,
        },
      })
      .then((res) => {
        console.log(JSON.stringify(res.data) + "::first res");
        const diaryArray = res.data.map((data) => ({
          diaryID: data.diaryID,
          diaryDate: data.diaryDate,
          myDiary: data.myDiary,
          otherDiary: data.otherDiary,
        }));
        setDiaryArray(diaryArray);
        setCopy(res.data);
        setDiarySearch(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [couple_id]);

  useEffect(() => {
    setDiarySearch(
      copy.filter(
        (e) =>
          e.myDiary.includes(searchkey) ||
          e.otherDiary.includes(searchkey) ||
          e.diaryDate.includes(searchkey)
      )
    );
  }, [searchkey, copy]);

  return (
    <div>
      <Header />
      <div className="month_header">
        <button className="month_btn1" onClick={decreaseMonth}>
          {"<"}
        </button>
        {headText}
        <button className="month_btn2" onClick={increaseMonth}>
          {">"}
        </button>
      </div>

      {!diarySearch ? (
        <div>
          {diaryArray.map((diary, index) => (
            <div key={index}>
              <div
                className="Diaryborder"
                onClick={() => diaryClick(diary.diaryID)}
              >
                {diary.diaryDate}
              </div>
            </div>
          ))}
        </div>
      ) : (
        <div>
          {diarySearch.map((e) => (
            <div key={e.diaryID}>
              <div
                className="Diaryborder"
                onClick={() => diaryClick(e.diaryID)}
              >
                {e.diaryDate}
              </div>
            </div>
          ))}{" "}
        </div>
      )}

      <div className="search_bar">
        <input
          className="search_ip"
          onChange={onkeyword}
          placeholder="내용을 검색하세요"
        />
      </div>
    </div>
  );
}

export default DiaryList;
