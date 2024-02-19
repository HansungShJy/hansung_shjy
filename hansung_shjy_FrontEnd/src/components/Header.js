import React, { useState } from "react";
import Nav from "./\bNav";
import "./Header.css";
import { Cookies } from "react-cookie";
import homelogo from "../assets/homelogoset.png";

function Header() {
  const cookie = new Cookies();
  const encodedNickname = cookie.get("nickname");
  cookie.get("nickname", decodeURIComponent(encodedNickname));

  function onClickLogout() {
    //cookie remove .. 하고 로그인창으로 ㄱㄱ
    cookie.remove("id");
    cookie.remove("user_id");
    cookie.remove("nickname");

    document.location.href = "/";
  }

  return (
    <div>
      <div className="header-logo">
        <img src={homelogo} alt="homelogoset.png" width="340" />
      </div>
      <div className="label_name">
        <label
          style={{
            fontSize: "15px",
            fontWeight: "bold",
            color: "000000",
          }}
        >
          {decodeURIComponent(encodedNickname)}
        </label>
        <label
          onClick={onClickLogout}
          style={{
            fontSize: "15px",
            fontWeight: "bold",
            color: "000000",
          }}
        >
          님 | 로그아웃
        </label>
      </div>
      <Nav />
    </div>
  );
}

export default Header;
