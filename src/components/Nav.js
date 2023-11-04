import { Link } from "react-router-dom";
import React from "react";
import "./Nav.css";

function Nav() {
  return (
    <div>
      <div className="navbar">
        <Link className="navbarMenu" to={"/diary"}>
          캘린더
        </Link>
        <Link className="navbarMenu" to={"/qna"}>
          오늘의 질문
        </Link>
        <Link className="navbarMenu" to={"/pay"}>
          우리의 지출
        </Link>
        <Link className="navbarMenu" to={"/plan"}>
          우리의 여행 계획
        </Link>
        <Link className="navbarMenu" to={"/mypage"}>
          마이페이지
        </Link>
      </div>
    </div>
  );
}

export default Nav;
