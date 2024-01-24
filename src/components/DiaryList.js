import React, { useState, useEffect } from "react";
import axios from "axios";
import Header from "./Header";
import "./DiaryList.css";

function DiaryList() {
  const [curDate, setCurDate] = useState(new Date());
  const [searchkey, setSearchKey] = useState("");

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

  const onSearching = (e) => {
    setSearchKey(e.target.value);
  };

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
      <div className="search_bar">
        <input className="search_ip" onChange={onkeyword} />
        <button className="search_btn" onClick={onSearching}>
          검색
        </button>
      </div>
    </div>
  );
}

export default DiaryList;
